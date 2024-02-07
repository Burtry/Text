package com.example.big_event.service.impl;

import com.example.big_event.pojo.Category;
import com.example.big_event.mapper.CategoryMapper;
import com.example.big_event.service.ICategoryService;
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
}
