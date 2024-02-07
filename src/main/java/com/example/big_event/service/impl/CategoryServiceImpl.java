package com.example.big_event.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.example.big_event.pojo.Category;
import com.example.big_event.mapper.CategoryMapper;
import com.example.big_event.pojo.PageQuery;
import com.example.big_event.pojo.Result;
import com.example.big_event.pojo.dto.PageDTO;
import com.example.big_event.service.ICategoryService;
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
 * @since 2024-02-07
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements ICategoryService {


    @Override
    public void add(Category category) {
        //补充创建人属性属性值
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        category.setCreateUser(id);
        save(category);
    }

    @Override
    public PageDTO<Category> queryPage(PageQuery pageQuery) {

        Map<String,Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        //构建查询条件
        Page<Category> page = Page.of(pageQuery.getPageNo(), pageQuery.getPageSize());
        QueryWrapper<Category> categoryQueryWrapper = new QueryWrapper<>();
        categoryQueryWrapper.eq("create_user",id);
        //构建排序条件
        if (pageQuery.getSortBy() != null) {
            new OrderItem(pageQuery.getSortBy(),pageQuery.getIsAsc());
        }else{
            // 默认按照更新时间排序
            page.addOrder(new OrderItem("update_time", false));
        }
        //查询

        page(page,categoryQueryWrapper);

        // 3.数据非空校验
        List<Category> records = page.getRecords();
        if (records == null || records.size() <= 0) {
            // 无数据，返回空结果
            return new PageDTO<>(page.getTotal(),page.getPages(),Collections.emptyList());
        }

        return new PageDTO<>(page.getTotal(),page.getPages(),records);
    }

}
