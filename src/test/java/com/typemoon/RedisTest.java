package com.typemoon;

import jakarta.annotation.Resource;
import lombok.val;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
public class RedisTest {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Test
    public void testSet() {
        redisTemplate.opsForValue().set("name", "MissBlue");
    }

    @Test
    public void testGet() {
        val token = redisTemplate.opsForHash().get("login_user", "1");
        System.out.println(token);
    }
}
