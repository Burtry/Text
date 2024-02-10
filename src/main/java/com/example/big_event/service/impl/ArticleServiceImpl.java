package com.example.big_event.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.big_event.pojo.Article;
import com.example.big_event.mapper.ArticleMapper;
import com.example.big_event.pojo.Category;
import com.example.big_event.pojo.PageQuery;
import com.example.big_event.pojo.dto.PageDTO;
import com.example.big_event.service.IArticleService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.big_event.utils.ThreadLocalUtil;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
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

    @Override
    public PageDTO<Article> pageQuery(PageQuery pageQuery) {

        //构建查询条件
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        Page<Article> page = Page.of(pageQuery.getPageNo(), pageQuery.getPageSize());
        //查询当前用户的文章列表
        QueryWrapper<Article> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("create_user",id);
        //构建排序条件
        if (pageQuery.getSortBy() != null) {
            new OrderItem(pageQuery.getSortBy(),pageQuery.getIsAsc());
        }else{
            // 默认按照更新时间排序
            page.addOrder(new OrderItem("update_time", false));
        }
        //查询
        page(page,queryWrapper);

        // 3.数据非空校验
        List<Article> records = page.getRecords();
        if (records == null || records.size() <= 0) {
            // 无数据，返回空结果
            return new PageDTO<>(page.getTotal(),page.getPages(), Collections.emptyList());
        }

        return new PageDTO<>(page.getTotal(),page.getPages(),records);
    }
}
