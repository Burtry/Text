package com.itheima.mp.domain.dto;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;

@Data
//@ApiModel(description = "用户表单实体")
@Tag(name = "用户", description = "用户实体类")
public class UserFormDTO {

    @Schema(description = "主键",type = "long")
    //@ApiModelProperty("id")
    private Long id;

    @Schema(description = "用户名")
    //@ApiModelProperty("用户名")
    private String username;

    //@ApiModelProperty("密码")
    private String password;

    //@ApiModelProperty("注册手机号")
    private String phone;

    //@ApiModelProperty("详细信息，JSON风格")
    private String info;

    //@ApiModelProperty("账户余额")
    private Integer balance;
}
