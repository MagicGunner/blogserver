package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.PhotoAlbum;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.PhotoAlbumAdminDTO;
import com.typemoon.model.dto.PhotoAlbumDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.PhotoAlbumVO;

import java.util.List;

public interface PhotoAlbumService extends IService<PhotoAlbum> {

    void saveOrUpdatePhotoAlbum(PhotoAlbumVO photoAlbumVO);

    PageResultDTO<PhotoAlbumAdminDTO> listPhotoAlbumsAdmin(ConditionVO condition);

    List<PhotoAlbumDTO> listPhotoAlbumInfosAdmin();

    PhotoAlbumAdminDTO getPhotoAlbumByIdAdmin(Integer albumId);

    void deletePhotoAlbumById(Integer albumId);

    List<PhotoAlbumDTO> listPhotoAlbums();

}
