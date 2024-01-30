package com.itheima.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/users")
@Tag(name = "用户管理端")
public class UserController {

    private final IUserService userService;

    @Operation(summary = "新增用户")
    @PostMapping
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        // 1.转换DTO为PO
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        // 2.新增
        userService.save(user);
    }

    @Operation(summary = "删除用户")
    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.removeById(id);
    }

    @Operation(summary = "根据ids批量查询用户")
    @GetMapping
    public List<UserVO> queryUserByIds(@RequestParam("ids") List<Long> ids){
        List<User> users = userService.listByIds(ids);
        return BeanUtil.copyToList(users, UserVO.class);
    }

    @Operation(summary = "根据id扣除费用")
    @DeleteMapping("/{id}/deduction/{money}")
    public void deductMoneyById(@PathVariable Long id, @PathVariable Integer money) {
        userService.deductMoney(id,money);
    }

    @Operation(summary = "根据复杂条件批量查询用户")
    @GetMapping("/list")
    public List<UserVO> queryUsers(UserQuery query){
        List<User> users = userService.queryUsers(query);
        return BeanUtil.copyToList(users, UserVO.class);
    }

}
