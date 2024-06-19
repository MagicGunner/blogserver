package com.typemoon.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.typemoon.entity.UserInfo;
import com.typemoon.mapper.UserInfoMapper;
import com.typemoon.model.dto.UserInfoDTO;
import com.typemoon.model.vo.UserInfoVO;
import com.typemoon.service.UserInfoService;
import com.typemoon.util.BeanCopyUtil;
import com.typemoon.util.UserUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @Override
    public UserInfoDTO getUserInfoById(Integer userInfoId) {
        UserInfo userInfo = userInfoMapper.selectById(userInfoId);
        return BeanCopyUtil.copyObject(userInfo, UserInfoDTO.class);
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void updateUserInfo(UserInfoVO userInfoVO) {
        UserInfo userInfo = UserInfo.builder()
                .id(UserUtil.getUserDetailsDTO().getUserInfoId())
                .nickname(userInfoVO.getNickname())
                .intro(userInfoVO.getIntro())
                .website(userInfoVO.getWebsite())
                .build();
        userInfoMapper.updateById(userInfo);
    }
}
