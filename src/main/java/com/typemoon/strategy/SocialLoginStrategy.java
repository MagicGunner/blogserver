package com.typemoon.strategy;

import com.typemoon.model.dto.UserInfoDTO;

public interface SocialLoginStrategy {

    UserInfoDTO login(String data);

}