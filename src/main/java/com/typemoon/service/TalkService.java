package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.Talk;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.TalkAdminDTO;
import com.typemoon.model.dto.TalkDTO;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.TalkVO;

import java.util.List;


public interface TalkService extends IService<Talk> {

    PageResultDTO<TalkDTO> listTalks();

    TalkDTO getTalkById(Integer talkId);

    void saveOrUpdateTalk(TalkVO talkVO);

    void deleteTalks(List<Integer> talkIdList);

    PageResultDTO<TalkAdminDTO> listBackTalks(ConditionVO conditionVO);

    TalkAdminDTO getBackTalkById(Integer talkId);

}
