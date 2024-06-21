package com.typemoon.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.typemoon.entity.Photo;
import com.typemoon.entity.PhotoAlbum;
import com.typemoon.exception.BizException;
import com.typemoon.mapper.PhotoMapper;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.PhotoAdminDTO;
import com.typemoon.model.dto.PhotoDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.DeleteVO;
import com.typemoon.model.vo.PhotoInfoVO;
import com.typemoon.model.vo.PhotoVO;
import com.typemoon.service.PhotoAlbumService;
import com.typemoon.service.PhotoService;
import com.typemoon.util.BeanCopyUtil;
import com.typemoon.util.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static com.typemoon.constant.CommonConstant.FALSE;
import static com.typemoon.enums.PhotoAlbumStatusEnum.PUBLIC;

@Service
public class PhotoServiceImpl extends ServiceImpl<PhotoMapper, Photo> implements PhotoService {

    @Autowired
    private PhotoMapper photoMapper;

    @Autowired
    private PhotoAlbumService photoAlbumService;

    @Override
    public PageResultDTO<PhotoAdminDTO> listPhotos(ConditionVO conditionVO) {
        Page<Photo> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        Page<Photo> photoPage = photoMapper.selectPage(page, new LambdaQueryWrapper<Photo>()
                .eq(Objects.nonNull(conditionVO.getAlbumId()), Photo::getAlbumId, conditionVO.getAlbumId())
                .eq(Photo::getIsDelete, conditionVO.getIsDelete())
                .orderByDesc(Photo::getId)
                .orderByDesc(Photo::getUpdateTime));
        List<PhotoAdminDTO> photos = BeanCopyUtil.copyList(photoPage.getRecords(), PhotoAdminDTO.class);
        return new PageResultDTO<>(photos, (int) photoPage.getTotal());
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhoto(PhotoInfoVO photoInfoVO) {
        Photo photo = BeanCopyUtil.copyObject(photoInfoVO, Photo.class);
        photoMapper.updateById(photo);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void savePhotos(PhotoVO photoVO) {
        List<Photo> photoList = photoVO.getPhotoUrls().stream().map(item -> Photo.builder()
                        .albumId(photoVO.getAlbumId())
                        .photoName(IdWorker.getIdStr())
                        .photoSrc(item)
                        .build())
                .collect(Collectors.toList());
        this.saveBatch(photoList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhotosAlbum(PhotoVO photoVO) {
        List<Photo> photoList = photoVO.getPhotoIds().stream().map(item -> Photo.builder()
                        .id(item)
                        .albumId(photoVO.getAlbumId())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(photoList);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updatePhotoDelete(DeleteVO deleteVO) {
        List<Photo> photoList = deleteVO.getIds().stream().map(item -> Photo.builder()
                        .id(item)
                        .isDelete(deleteVO.getIsDelete())
                        .build())
                .collect(Collectors.toList());
        this.updateBatchById(photoList);
        if (deleteVO.getIsDelete().equals(FALSE)) {
            List<PhotoAlbum> photoAlbumList = photoMapper.selectList(new LambdaQueryWrapper<Photo>()
                            .select(Photo::getAlbumId)
                            .in(Photo::getId, deleteVO.getIds())
                            .groupBy(Photo::getAlbumId))
                    .stream()
                    .map(item -> PhotoAlbum.builder()
                            .id(item.getAlbumId())
                            .isDelete(FALSE)
                            .build())
                    .collect(Collectors.toList());
            photoAlbumService.updateBatchById(photoAlbumList);
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void deletePhotos(List<Integer> photoIds) {
        photoMapper.deleteBatchIds(photoIds);
    }

    @Override
    public PhotoDTO listPhotosByAlbumId(Integer albumId) {
        PhotoAlbum photoAlbum = photoAlbumService.getOne(new LambdaQueryWrapper<PhotoAlbum>()
                .eq(PhotoAlbum::getId, albumId)
                .eq(PhotoAlbum::getIsDelete, FALSE)
                .eq(PhotoAlbum::getStatus, PUBLIC.getStatus()));
        if (Objects.isNull(photoAlbum)) {
            throw new BizException("相册不存在");
        }
        Page<Photo> page = new Page<>(PageUtil.getCurrent(), PageUtil.getSize());
        List<String> photos = photoMapper.selectPage(page, new LambdaQueryWrapper<Photo>()
                        .select(Photo::getPhotoSrc)
                        .eq(Photo::getAlbumId, albumId)
                        .eq(Photo::getIsDelete, FALSE)
                        .orderByDesc(Photo::getId))
                .getRecords()
                .stream()
                .map(Photo::getPhotoSrc)
                .collect(Collectors.toList());
        return PhotoDTO.builder()
                .photoAlbumCover(photoAlbum.getAlbumCover())
                .photoAlbumName(photoAlbum.getAlbumName())
                .photos(photos)
                .build();
    }

}
