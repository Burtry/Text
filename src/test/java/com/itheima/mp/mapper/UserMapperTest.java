package com.itheima.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.itheima.mp.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class UserMapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testInsert() {
        User user = new User();
        //user.setId(5L);
        user.setUsername("Lucy3");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(2002);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.saveUser(user);
    }

    @Test
    void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    void testQueryByIds() {
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    void testUpdateById() {
        User user = new User();
        //user.setId(5L);
        user.setBalance(20000);
        userMapper.updateUser(user);
    }

    @Test
    void testDeleteUser() {
        userMapper.deleteUser(5L);
    }

    @Test
    void name() {
        //构建查询条件wrapper

        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<User>().select(User::getId, User::getUsername, User::getInfo, User::getBalance)
                .like(User::getUsername, "o")
                .ge(User::getBalance, "1000");
        List<User> users1 = userMapper.selectList(wrapper);
        users1.forEach(System.out::println);
    }

    @Test
    void textUpdateQueryWrapper() {
        List<Long> ids = List.of(1L,2L,4L);
        int amount = 200;
        UpdateWrapper<User> wrapper = new UpdateWrapper<User>().in("id",ids);
        userMapper.updateBalanceByIds(wrapper,amount);
    }
}