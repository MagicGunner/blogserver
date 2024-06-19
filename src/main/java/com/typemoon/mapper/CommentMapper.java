package com.typemoon.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.typemoon.entity.Comment;
import com.typemoon.model.dto.CommentAdminDTO;
import com.typemoon.model.dto.CommentCountDTO;
import com.typemoon.model.dto.CommentDTO;
import com.typemoon.model.dto.ReplyDTO;
import com.typemoon.model.vo.CommentVO;
import com.typemoon.model.vo.ConditionVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentMapper extends BaseMapper<Comment> {

    List<CommentDTO> listComments(@Param("current") Long current, @Param("size") Long size, @Param("commentVO") CommentVO commentVO);

    List<ReplyDTO> listReplies(@Param("commentIds") List<Integer> commentIdList);

    List<CommentDTO> listTopSixComments();

    Integer countComments(@Param("conditionVO") ConditionVO conditionVO);

    List<CommentAdminDTO> listCommentsAdmin(@Param("current") Long current, @Param("size") Long size, @Param("conditionVO") ConditionVO conditionVO);

    List<CommentCountDTO> listCommentCountByTypeAndTopicIds(@Param("type") Integer type, @Param("topicIds") List<Integer> topicIds);

    CommentCountDTO listCommentCountByTypeAndTopicId(@Param("type") Integer type, @Param("topicId") Integer topicId);

}
