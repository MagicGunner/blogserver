package com.typemoon.service;

import com.typemoon.model.dto.*;
import com.typemoon.model.vo.ConditionVO;
import com.typemoon.model.vo.PasswordVO;
import com.typemoon.model.vo.QQLoginVO;
import com.typemoon.model.vo.UserVO;

import java.util.List;

public interface UserAuthService {
    void sendCode(String username);

    List<UserAreaDTO> listUserAreas(ConditionVO conditionVO);

    void register(UserVO userVO);

    void updatePassword(UserVO userVO);

    void updateAdminPassword(PasswordVO passwordVO);

    PageResultDTO<UserAdminDTO> listUsers(ConditionVO condition);

    UserLogoutStatusDTO logout();

    UserInfoDTO qqLogin(QQLoginVO qqLoginVO);
}
