package com.example.big_event;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import java.util.concurrent.TimeUnit;

@SpringBootTest
public class redisText {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;
    @Test
    public void set() {
        ValueOperations<String, String> operations = stringRedisTemplate.opsForValue();
        operations.set("username", "burtry",15, TimeUnit.SECONDS);
        System.out.println(operations.get("username"));
    }
}
