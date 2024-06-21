package com.typemoon;

import com.typemoon.service.TokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TokenTest {

    @Autowired
    private TokenService tokenService;

    @Test
    public void testCreate() {
        System.out.println(tokenService.createToken("1"));
    }
}
