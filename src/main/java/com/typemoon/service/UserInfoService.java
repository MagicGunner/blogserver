package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.UserInfo;
import com.typemoon.model.dto.UserInfoDTO;
import com.typemoon.model.vo.UserInfoVO;

public interface UserInfoService extends IService<UserInfo> {
    UserInfoDTO getUserInfoById(Integer userInfoId);

    void updateUserInfo(UserInfoVO userInfoVO);
}
