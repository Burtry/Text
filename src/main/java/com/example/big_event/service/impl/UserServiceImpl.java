package com.example.big_event.service.impl;

import com.example.big_event.User;
import com.example.big_event.mapper.UserMapper;
import com.example.big_event.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author Burtry
 * @since 2024-02-04
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
