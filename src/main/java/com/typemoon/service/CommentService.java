package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Comment;
import com.typemoon.model.dto.CommentAdminDTO;
import com.typemoon.model.dto.CommentDTO;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.ReplyDTO;
import com.typemoon.model.vo.CommentVO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.ReviewVO;

import java.util.List;

public interface CommentService extends IService<Comment> {

    void saveComment(CommentVO commentVO);

    PageResultDTO<CommentDTO> listComments(CommentVO commentVO);

    List<ReplyDTO> listRepliesByCommentId(Integer commentId);

    List<CommentDTO> listTopSixComments();

    PageResultDTO<CommentAdminDTO> listCommentsAdmin(ConditionVO conditionVO);

    void updateCommentsReview(ReviewVO reviewVO);

}
