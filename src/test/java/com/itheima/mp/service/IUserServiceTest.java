package com.itheima.mp.service;

import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IUserServiceTest {

    @Autowired
    private IUserService userService;

    @Test
    void textSaveUser() {
        User user = new User();
        //user.setId(5L);
        user.setUsername("Lucy1111");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(2002);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userService.save(user);
    }

    @Test
    void textQuery() {
        List<Long> users = List.of(1L,2L,3L);

        List<User> users1 = userService.listByIds(users);
        users1.forEach(System.out::println);

    }
}