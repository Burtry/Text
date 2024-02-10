package com.example.big_event.service.impl;

import com.example.big_event.pojo.Article;
import com.example.big_event.mapper.ArticleMapper;
import com.example.big_event.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.big_event.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Burtry
 * @since 2024-02-10
 */
@Service
public class ArticleServiceImpl extends ServiceImpl<ArticleMapper, Article> implements IArticleService {


    @Override
    public void add(Article article) {
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        article.setCreateUser(id);
        save(article);
    }
}
