package com.itheima.mp.service.Impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.mapper.UserMapper;
import com.itheima.mp.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class IUserServiceImpl extends ServiceImpl<UserMapper,User> implements IUserService {

    @Override
    public void deductMoney(Long id, Integer money) {
        User user = getById(id);
        if (user == null || user.getStatus() != 1) {
            throw new RuntimeException("账户异常");
        }
        if (user.getBalance() < money) {
            throw new RuntimeException(" 账户余额不足！");
        }


/*        UpdateWrapper<User> wrapper = new UpdateWrapper<User>()
                .setSql("balance = balance - " + money) // SET balance = balance - 200
                .in("id", id); // WHERE id in (1, 2, 4)
        // 2.更新，注意第一个参数可以给null，也就是不填更新字段和数据，
        // 而是基于UpdateWrapper中的setSQL来更新
        baseMapper.update(null, wrapper);*/

        //简单的查询修改可以不用wrapper.
        baseMapper.deductMoney(id,money);
    }
}
