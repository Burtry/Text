package com.itheima.mp.controller.admin;

import cn.hutool.core.bean.BeanUtil;
import com.itheima.mp.domain.dto.UserFormDTO;
import com.itheima.mp.domain.po.User;
import com.itheima.mp.domain.query.UserQuery;
import com.itheima.mp.domain.vo.UserVO;
import com.itheima.mp.service.IUserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tags;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor //TODO 待向笔记中写入此用法
@RestController
@RequestMapping("/users")
@Tag(name = "用户Controller")
public class UserController {

    private final IUserService userService;



    @PostMapping
    @Operation(summary = "新增用户")
    public void saveUser(@RequestBody UserFormDTO userFormDTO){
        // 1.转换DTO为PO
        User user = BeanUtil.copyProperties(userFormDTO, User.class);
        // 2.新增
        userService.save(user);
    }






    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.removeById(id);
    }

    @GetMapping
    public List<UserVO> queryUserByIds(@RequestParam("ids") List<Long> ids){
        return userService.queryUserAndAddressByIds(ids);
    }

    @DeleteMapping("/{id}/deduction/{money}")
    public void deductMoneyById(@PathVariable Long id, @PathVariable Integer money) {
        userService.deductMoney(id,money);
    }

    @GetMapping("/list")
    public List<UserVO> queryUsers(UserQuery query){
        List<User> users = userService.queryUsers(query);
        return BeanUtil.copyToList(users, UserVO.class);
    }
    @GetMapping("/select")
    public UserVO queryUserAndAddressById(@RequestParam("id") Long id){
        return userService.queryUserAndAddressById(id);
    }

}
