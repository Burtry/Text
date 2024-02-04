package com.example.big_event.service.impl;

import com.example.big_event.mapper.UserMapper;
import com.example.big_event.pojo.User;
import com.example.big_event.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.big_event.utils.Md5Util;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Collections;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Burtry
 * @since 2024-02-04
 */
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

    private final UserMapper userMapper;
    /**
     * 注册用户
     * @param username
     * @param password
     */
    @Override
    public void register(String username, String password) {
        String md5PassWord = Md5Util.getMD5String(password);
        User newUser = new User();
        newUser.setUsername(username);
        newUser.setPassword(md5PassWord);
        newUser.setUpdateTime(LocalDateTime.now());
        newUser.setCreateTime(LocalDateTime.now());
        save(newUser);
        //userMapper.add(newUser);
    }
}
