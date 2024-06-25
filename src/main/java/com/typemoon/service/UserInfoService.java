package com.typemoon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.typemoon.entity.UserInfo;
import com.typemoon.model.dto.PageResultDTO;
import com.typemoon.model.dto.UserInfoDTO;
import com.typemoon.model.dto.UserOnlineDTO;
import com.typemoon.model.vo.*;
import org.springframework.web.multipart.MultipartFile;

public interface UserInfoService extends IService<UserInfo> {
    void updateUserInfo(UserInfoVO userInfoVO);

    String updateUserAvatar(MultipartFile file);

    void saveUserEmail(EmailVO emailVO);

    void updateUserSubscribe(SubscribeVO subscribeVO);

    void updateUserRole(UserRoleVO userRoleVO);

    void updateUserDisable(UserDisableVO userDisableVO);

    PageResultDTO<UserOnlineDTO> listOnlineUsers(ConditionVO conditionVO);

    void removeOnlineUser(Integer userInfoId);

    UserInfoDTO getUserInfoById(Integer id);
}
