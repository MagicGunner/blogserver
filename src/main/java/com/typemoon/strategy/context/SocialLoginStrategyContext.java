package com.typemoon.strategy.context;

import com.typemoon.enums.LoginTypeEnum;
import com.typemoon.model.dto.UserInfoDTO;
import com.typemoon.strategy.SocialLoginStrategy;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class SocialLoginStrategyContext {

    private final Map<String, SocialLoginStrategy> socialLoginStrategyMap;

    public SocialLoginStrategyContext(Map<String, SocialLoginStrategy> socialLoginStrategyMap) {
        this.socialLoginStrategyMap = socialLoginStrategyMap;
    }

    public UserInfoDTO executeLoginStrategy(String data, LoginTypeEnum loginTypeEnum) {
        return socialLoginStrategyMap.get(loginTypeEnum.getStrategy()).login(data);
    }

}