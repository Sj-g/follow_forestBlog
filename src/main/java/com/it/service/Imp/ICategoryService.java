package com.it.service.Imp;

import com.it.entity.Category;
import com.it.mapper.CategoryMapper;
import com.it.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryService implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<Category> listCategory() {
        return categoryMapper.listCategory();
    }


    @Override
    public void deleteCategory(Integer id) {
        categoryMapper.deleteCategory(id);
    }

    @Override
    public Category getCategoryById(Integer id) {
        return categoryMapper.getCategoryById(id);
    }

    @Override
    public void insertCategory(Category category) {
        categoryMapper.insert(category);
    }

    @Override
    public void updateCategory(Category category) {
        categoryMapper.update(category);
    }

    @Override
    public Category getCategoryByName(String name) {
        return categoryMapper.getCategoryByName(name);
    }
}
