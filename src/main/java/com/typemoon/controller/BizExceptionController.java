package com.typemoon.controller;

import com.typemoon.exception.BizException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import jakarta.servlet.http.HttpServletRequest;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Api(tags = "异常处理模块")
@RestController
public class BizExceptionController {

    @SneakyThrows
    @ApiOperation("/处理BizException")
    @RequestMapping("/bizException")
    public void handleBizException(HttpServletRequest request) {
        if (request.getAttribute("bizException") instanceof BizException) {
            System.out.println(request.getAttribute("bizException"));
            throw ((BizException) request.getAttribute("bizException"));
        } else {
            throw new Exception();
        }
    }

}