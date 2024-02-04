package com.example.big_event.mapper;

import com.example.big_event.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

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

}
