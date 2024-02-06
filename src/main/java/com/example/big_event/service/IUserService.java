package com.example.big_event.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.big_event.pojo.User;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Burtry
 * @since 2024-02-04
 */
public interface IUserService extends IService<User> {

    /**
     * 注册
     * @param username
     * @param password
     */
    void register(String username, String password);

    void updateUser(User user);


    void updateurl(String avatarUrl, Integer id);
}
