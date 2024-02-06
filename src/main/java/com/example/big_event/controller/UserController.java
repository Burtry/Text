package com.example.big_event.controller;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.toolkit.CollectionUtils;
import com.example.big_event.pojo.Result;
import com.example.big_event.pojo.User;
import com.example.big_event.service.IUserService;
import com.example.big_event.utils.JwtUtil;
import com.example.big_event.utils.Md5Util;
import com.example.big_event.utils.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.URL;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

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
    public Result register(@Pattern(regexp = "^[a-zA-Z0-9]{5,16}$") @Parameter(description = "用户名") String username,@Pattern(regexp = "^[a-zA-Z0-9]{5,16}$") @Parameter(description = "密码") String password) {

        User user = userService.lambdaQuery().eq(User::getUsername, username).one();
        //System.out.println(user);
        if (user == null) {
            //注册
            userService.register(username,password);
            return Result.success();
        }
        return Result.error("用户已存在!");
    }


    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^[a-zA-Z0-9]{5,16}$") String username, @Pattern(regexp = "^[a-zA-Z0-9]{5,16}$") String password) {
        User loginUser = userService.lambdaQuery().eq(User::getUsername, username).one();
        //判断用户
        if (loginUser == null) {
            return Result.error("用户名错误！");
        }
        //判断密码
        if (!Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            return Result.error("密码错误！");
        }

        //生成JWT令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put("username", loginUser.getUsername());
        claims.put("id", loginUser.getId());
        String token = JwtUtil.genToken(claims);

        return Result.success(token);

    }

/*    @GetMapping("/userInfo")
    public Result<User> getUserInfo(@RequestHeader(name = "token") String token) {
        Map<String, Object> claims = JwtUtil.parseToken(token);
        Integer userId = (Integer) claims.get("id");
        User user = userService.getById(userId);
        return Result.success(user);
    }*/

    //在ThreadLocal获取用户信息
    
    @GetMapping("/userInfo")
    public Result<User> getUserInfo() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        User user = userService.getById(id);
        return Result.success(user);
    }




    @GetMapping("/{id}")
    public Result<User> getById(@PathVariable Long id) {
        User user = userService.getById(id);
        return Result.success(user);
    }


    @PostMapping("/update")
    public Result updateUser(@RequestBody @Validated User user) {
        userService.updateUser(user);
        return Result.success();
    }

    @PostMapping("/updateavatarUrl")
    public Result updateAvatarUrl(@RequestParam @URL String avatarUrl) {
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        userService.updateurl(avatarUrl,id);
        return Result.success();
    }
}
