package com.example.big_event.service.impl;

import com.example.big_event.pojo.Category;
import com.example.big_event.mapper.CategoryMapper;
import com.example.big_event.service.ICategoryService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

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

}
