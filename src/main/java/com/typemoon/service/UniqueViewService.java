package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.UniqueView;
import com.typemoon.model.dto.UniqueViewDTO;

import java.util.List;

public interface UniqueViewService extends IService<UniqueView> {

    List<UniqueViewDTO> listUniqueViews();

}
