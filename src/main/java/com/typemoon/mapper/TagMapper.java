package com.typemoon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.typemoon.entity.Tag;
import com.typemoon.model.dto.TagAdminDTO;
import com.typemoon.model.dto.TagDTO;
import com.typemoon.model.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {

    List<TagDTO> listTags();

    List<TagDTO> listTopTenTags();

    List<String> listTagNamesByArticleId(Integer articleId);

    List<TagAdminDTO> listTagsAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

}
