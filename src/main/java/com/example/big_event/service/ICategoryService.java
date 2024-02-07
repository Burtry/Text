package com.example.big_event.service;

import com.example.big_event.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.big_event.pojo.PageQuery;
import com.example.big_event.pojo.Result;
import com.example.big_event.pojo.dto.PageDTO;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Burtry
 * @since 2024-02-07
 */
public interface ICategoryService extends IService<Category> {

    void add(Category category);

    PageDTO<Category> queryPage(PageQuery pageQuery);
}
