package com.typemoon.strategy.impl;

import com.alibaba.fastjson2.JSON;
import com.typemoon.config.properties.QQConfigProperties;
import com.typemoon.constant.SocialLoginConstant;
import com.typemoon.enums.LoginTypeEnum;
import com.typemoon.exception.BizException;
import com.typemoon.model.dto.QQTokenDTO;
import com.typemoon.model.dto.QQUserInfoDTO;
import com.typemoon.model.dto.SocialTokenDTO;
import com.typemoon.model.dto.SocialUserInfoDTO;
import com.typemoon.model.vo.QQLoginVO;
import com.typemoon.util.CommonUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.typemoon.constant.SocialLoginConstant.*;
import static com.typemoon.enums.StatusCodeEnum.QQ_LOGIN_ERROR;

@Service("qqLoginStrategyImpl")
public class QQLoginStrategyImpl extends AbstractSocialLoginStrategyImpl {

    @Autowired
    private QQConfigProperties qqConfigProperties;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public SocialTokenDTO getSocialToken(String data) {
        QQLoginVO qqLoginVO = JSON.parseObject(data, QQLoginVO.class);
        checkQQToken(qqLoginVO);
        return SocialTokenDTO.builder()
                .openId(qqLoginVO.getOpenId())
                .accessToken(qqLoginVO.getAccessToken())
                .loginType(LoginTypeEnum.QQ.getType())
                .build();
    }

    @Override
    public SocialUserInfoDTO getSocialUserInfo(SocialTokenDTO socialTokenDTO) {
        Map<String, String> formData = new HashMap<>(3);
        formData.put(QQ_OPEN_ID, socialTokenDTO.getOpenId());
        formData.put(ACCESS_TOKEN, socialTokenDTO.getAccessToken());
        formData.put(OAUTH_CONSUMER_KEY, qqConfigProperties.getAppId());
        QQUserInfoDTO qqUserInfoDTO = JSON.parseObject(restTemplate.getForObject(qqConfigProperties.getUserInfoUrl(), String.class, formData), QQUserInfoDTO.class);
        return SocialUserInfoDTO.builder()
                .nickname(Objects.requireNonNull(qqUserInfoDTO).getNickname())
                .avatar(qqUserInfoDTO.getFigureurl_qq_1())
                .build();
    }

    private void checkQQToken(QQLoginVO qqLoginVO) {
        Map<String, String> qqData = new HashMap<>(1);
        qqData.put(SocialLoginConstant.ACCESS_TOKEN, qqLoginVO.getAccessToken());
        try {
            String result = restTemplate.getForObject(qqConfigProperties.getCheckTokenUrl(), String.class, qqData);
            QQTokenDTO qqTokenDTO = JSON.parseObject(CommonUtil.getBracketsContent(Objects.requireNonNull(result)), QQTokenDTO.class);
            if (!qqLoginVO.getOpenId().equals(qqTokenDTO.getOpenid())) {
                throw new BizException(QQ_LOGIN_ERROR);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new BizException(QQ_LOGIN_ERROR);
        }
    }

}
