package com.itheima.mp.domain.vo;


import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
@Schema(name = "用户VO")
public class UserVO {

    @Schema(description = "用户id", type = "long")
    private Long id;

    @Schema(description = "用户名")
    private String username;

    @Schema(description = "用户信息", type = "String",hidden = true)
    private String info;

    @Schema(description = "用户状态", type = "inta")
    private Integer status;

    @Schema(description = "余额", type = "Integer")
    private Integer balance;

    @Schema(description = "用户地址", type = "List")
    //用户地址
    private List<AddressVO> addresses;
}
