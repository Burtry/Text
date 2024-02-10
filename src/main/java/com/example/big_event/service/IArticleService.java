package com.example.big_event.service;

import com.example.big_event.pojo.Article;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.big_event.pojo.PageQuery;
import com.example.big_event.pojo.dto.PageDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Burtry
 * @since 2024-02-10
 */
public interface IArticleService extends IService<Article> {

    void add(Article article);

    PageDTO<Article> pageQuery(PageQuery pageQuery);
}
