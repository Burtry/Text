package com.example.big_event.controller;


import com.example.big_event.pojo.Result;
import com.example.big_event.pojo.User;
import com.example.big_event.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Burtry
 * @since 2024-02-04
 */
@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
@Validated
@Tag(name = "用户端接口")
public class UserController {

    private final IUserService userService;

    @PostMapping("/register")
    @Operation(summary = "注册")
    public Result register(@Pattern(regexp = "^\\d{5,16}$") @Parameter(description = "用户名") String username,@Pattern(regexp = "^\\d{5,16}$") @Parameter(description = "密码") String password) {

        User user = userService.lambdaQuery().eq(User::getUsername, username).one();
        //System.out.println(user);
        if (user == null) {
            //注册
            userService.register(username,password);
            return Result.success();
        }
        return Result.error("用户已存在!");
    }
}
