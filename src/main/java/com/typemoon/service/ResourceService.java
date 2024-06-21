package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Resource;
import com.typemoon.model.dto.LabelOptionDTO;
import com.typemoon.model.dto.ResourceDTO;
import com.typemoon.model.vo.ConditionVO;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    void importSwagger();

//    void saveOrUpdateResource(ResourceVO resourceVO);

    void deleteResource(Integer resourceId);

    List<ResourceDTO> listResources(ConditionVO conditionVO);

    List<LabelOptionDTO> listResourceOption();

}
