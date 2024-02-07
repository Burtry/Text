package com.example.big_event.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author Burtry
 * @since 2024-02-07
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("category")
public class Category implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    @NotNull(groups = {Update.class})   //更新的时候需要id
    private Integer id;
    /**
     * 分类名称
     */
   @NotNull                             //其他添加相关注解的分成默认组
    private String categoryName;
    /**
     * 分类别名
     */
    @NotNull
    private String categoryAlias;
    /**
     * 创建人ID
     */
    //@JsonIgnore
    private Integer createUser;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

    /**
     * 修改时间
     */
    private LocalDateTime updateTime;

    public interface Add extends Default {
    }

    public interface Update extends Default{
    }


}
