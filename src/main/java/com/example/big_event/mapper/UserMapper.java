package com.example.big_event.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.big_event.pojo.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;

/**
 * <p>
 * 用户表 Mapper 接口
 * </p>
 *
 * @author Burtry
 * @since 2024-02-04
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

    @Insert("")
    void add(User newUser);

}
