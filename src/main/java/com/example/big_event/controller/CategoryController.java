package com.example.big_event.controller;


import com.example.big_event.pojo.Category;
import com.example.big_event.pojo.Result;
import com.example.big_event.service.ICategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/add")
    public Result add(@RequestBody Category category) {
        categoryService.add(category);
        return Result.success();
    }

}
