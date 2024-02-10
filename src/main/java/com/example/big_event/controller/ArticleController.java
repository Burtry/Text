package com.example.big_event.controller;


import com.example.big_event.pojo.Article;
import com.example.big_event.pojo.Result;
import com.example.big_event.service.IArticleService;
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
 * @since 2024-02-10
 */
@RestController
@RequestMapping("/article")
@AllArgsConstructor
public class ArticleController {

    private final IArticleService articleService;

    @PostMapping("/add")
    public Result add(@RequestBody Article article) {
        articleService.add(article);
        return Result.success();
    }
}
