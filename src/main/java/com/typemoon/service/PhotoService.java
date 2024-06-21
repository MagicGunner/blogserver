package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Photo;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.PhotoAdminDTO;
import com.typemoon.model.dto.PhotoDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.DeleteVO;
import com.typemoon.model.vo.PhotoInfoVO;
import com.typemoon.model.vo.PhotoVO;

import java.util.List;

public interface PhotoService extends IService<Photo> {

    PageResultDTO<PhotoAdminDTO> listPhotos(ConditionVO conditionVO);

    void updatePhoto(PhotoInfoVO photoInfoVO);

    void savePhotos(PhotoVO photoVO);

    void updatePhotosAlbum(PhotoVO photoVO);

    void updatePhotoDelete(DeleteVO deleteVO);

    void deletePhotos(List<Integer> photoIds);

    PhotoDTO listPhotosByAlbumId(Integer albumId);

}
