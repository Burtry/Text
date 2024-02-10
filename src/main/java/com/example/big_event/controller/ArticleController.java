package com.example.big_event.controller;


import com.example.big_event.pojo.Article;
import com.example.big_event.pojo.PageQuery;
import com.example.big_event.pojo.Result;
import com.example.big_event.pojo.dto.PageDTO;
import com.example.big_event.service.IArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }


    @GetMapping("/page")
    public Result<PageDTO<Article>> pageArticle(PageQuery pageQuery) {
        PageDTO<Article> pageDTO = articleService.pageQuery(pageQuery);
        return Result.success(pageDTO);
    }
}
