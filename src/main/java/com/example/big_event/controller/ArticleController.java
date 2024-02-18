package com.example.big_event.controller;


import com.aliyuncs.utils.StringUtils;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.big_event.pojo.Article;
import com.example.big_event.pojo.Category;
import com.example.big_event.pojo.PageQuery;
import com.example.big_event.pojo.Result;
import com.example.big_event.pojo.dto.PageDTO;
import com.example.big_event.service.IArticleService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
@CrossOrigin
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

    @GetMapping("/getAll")
    public List<Article> listAll() {
        return articleService.list();
    }

    @GetMapping("/search")
    public List<Article> search(String content, String state) {

        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();

        // 如果 content 参数不为空，添加模糊查询条件
        if (!StringUtils.isEmpty(content)) {
            queryWrapper.like("content", content);
        }

        // 如果 state 参数不为空，添加精确查询条件
        if (!StringUtils.isEmpty(state)) {
            queryWrapper.eq("state", state);
        }

        // 查询数据库并返回结果
        return articleService.list(queryWrapper);
    }
}
