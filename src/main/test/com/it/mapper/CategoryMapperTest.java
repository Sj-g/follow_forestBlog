package com.it.mapper;

import com.it.base.BaseTest;
import com.it.entity.Category;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CategoryMapperTest extends BaseTest {
    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    public void insert() {
    }

    @Test
    public void update() {
    }

    @Test
    public void getCategoryById() {
    }

    @Test
    public void deleteCategory() {
    }

    @Test
    public void countCategory() {
    }

    @Test
    public void listCategory() {
        List<Category> list=categoryMapper.listCategory();
       for(Category category:list){
           System.out.println(category);
       }
    }

    @Test
    public void findChildCategory() {
    }

    @Test
    public void getCategoryByName() {
    }
}