package com.typemoon.controller;

import com.typemoon.annotation.OptLog;
import com.typemoon.model.dto.UserInfoDTO;
import com.typemoon.model.vo.ResultVO;
import com.typemoon.model.vo.UserInfoVO;
import com.typemoon.service.UserInfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static com.typemoon.constant.OptTypeConstant.UPDATE;

@Api(tags = "用户信息模块")
@RestController
public class UserInfoController {

    @Autowired
    private UserInfoService userInfoService;

    @OptLog(optType = UPDATE)
    @ApiOperation("更新用户信息")
    @PutMapping("/users/info")
    public ResultVO<?> updateUserInfo(@Valid @RequestBody UserInfoVO userInfoVO) {
        userInfoService.updateUserInfo(userInfoVO);
        return ResultVO.ok();
    }

    @ApiOperation("根据id获取用户信息")
    @GetMapping("/users/info/{userInfoId}")
    public ResultVO<UserInfoDTO> getUserInfoById(@PathVariable("userInfoId") Integer userInfoId) {
        return ResultVO.ok(userInfoService.getUserInfoById(userInfoId));
    }
}
