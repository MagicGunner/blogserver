package com.typemoon.handler;

import com.alibaba.fastjson.JSON;
import com.typemoon.constant.CommonConstant;
import com.typemoon.entity.UserAuth;
import com.typemoon.mapper.UserAuthMapper;
import com.typemoon.model.dto.UserDetailsDTO;
import com.typemoon.model.dto.UserInfoDTO;
import com.typemoon.model.vo.ResultVO;
import com.typemoon.service.TokenService;
import com.typemoon.util.BeanCopyUtil;
import com.typemoon.util.UserUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Objects;


@Component
public class AuthenticationSuccessHandlerImpl implements AuthenticationSuccessHandler {

    @Autowired
    private UserAuthMapper userAuthMapper;

    @Autowired
    private TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        UserInfoDTO userLoginDTO = BeanCopyUtil.copyObject(UserUtil.getUserDetailsDTO(), UserInfoDTO.class);
        if (Objects.nonNull(authentication)) {
            UserDetailsDTO userDetailsDTO = (UserDetailsDTO) authentication.getPrincipal();
            String token = tokenService.createToken(userDetailsDTO);
            userLoginDTO.setToken(token);
        }
        response.setContentType(CommonConstant.APPLICATION_JSON);
        response.getWriter().write(JSON.toJSONString(ResultVO.ok(userLoginDTO)));
        updateUserInfo();
    }

    @Async
    public void updateUserInfo() {
        UserAuth userAuth = UserAuth.builder()
                .id(UserUtil.getUserDetailsDTO().getId())
                .ipAddress(UserUtil.getUserDetailsDTO().getIpAddress())
                .ipSource(UserUtil.getUserDetailsDTO().getIpSource())
                .lastLoginTime(UserUtil.getUserDetailsDTO().getLastLoginTime())
                .build();
        userAuthMapper.updateById(userAuth);
    }
}
