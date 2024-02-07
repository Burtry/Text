package com.example.big_event.controller;


import com.example.big_event.pojo.Category;
import com.example.big_event.pojo.PageBean;
import com.example.big_event.pojo.Result;
import com.example.big_event.service.ICategoryService;
import com.example.big_event.utils.ThreadLocalUtil;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Burtry
 * @since 2024-02-07
 */
@RestController
@RequestMapping("/category")
@AllArgsConstructor
@Tag(name = "文章分类管理")
public class CategoryController {

    private final ICategoryService categoryService;

    @Operation(summary = "新增文章")
    @PostMapping()
    public Result add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Result.success();
    }

    /**
     * 查询当前用户的所有文章
     * @return
     */
    @GetMapping()
    public Result<List<Category>> list() {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        List<Category> list = categoryService.lambdaQuery().eq(Category::getCreateUser,id).list();
        return Result.success(list);
    }

}
