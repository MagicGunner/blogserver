package com.typemoon.service;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.typemoon.model.dto.UserDetailsDTO;

import jakarta.servlet.http.HttpServletRequest;


public interface TokenService {

    String createToken(UserDetailsDTO userDetailsDTO);

    String createToken(String subject);

    void refreshToken(UserDetailsDTO userDetailsDTO);

    void renewToken(UserDetailsDTO userDetailsDTO);

    DecodedJWT parseToken(String token);

    UserDetailsDTO getUserDetailDTO(HttpServletRequest request);

    void delLoginUser(Integer userId);

}