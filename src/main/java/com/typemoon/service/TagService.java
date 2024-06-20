package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Tag;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.TagAdminDTO;
import com.typemoon.model.dto.TagDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.TagVO;

import java.util.List;

public interface TagService extends IService<Tag> {

    List<TagDTO> listTags();

    List<TagDTO> listTopTenTags();

    PageResultDTO<TagAdminDTO> listTagsAdmin(ConditionVO conditionVO);

    List<TagAdminDTO> listTagsAdminBySearch(ConditionVO conditionVO);

    void saveOrUpdateTag(TagVO tagVO);

    void deleteTag(List<Integer> tagIds);

}
