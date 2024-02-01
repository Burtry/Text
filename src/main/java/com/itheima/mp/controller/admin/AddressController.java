package com.itheima.mp.controller.admin;


import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Burtry
 * @since 2024-01-31
 */
@RestController
@RequestMapping("/address")
@Tag(name = "管理端Controller", description = "地址控制层")
@RequiredArgsConstructor    //添加这个注解就可以不需要构造函数注入ICO容器了
public class AddressController {


    private final IUserService userService;

    @PostMapping
    @Operation(summary = "新增用户")
    public void saveUser(@Parameter(description = "UserFormDTO对象", name = "用户DTO对象") @RequestBody UserFormDTO userFormDTO){
        // 1.转换DTO为PO
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        // 2.新增
        userService.save(user);
    }
}
