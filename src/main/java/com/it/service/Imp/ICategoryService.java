package com.it.service.Imp;

import com.it.entity.Category;
import com.it.service.CategoryService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryService implements CategoryService {
    @Override
    public Integer countCategory() {
        return null;
    }

    @Override
    public List<Category> listCategory() {

        return null;
    }

    @Override
    public List<Category> listCategoryWithCount() {
        return null;
    }

    @Override
    public void deleteCategory(Integer id) {

    }

    @Override
    public Category getCategoryById(Integer id) {
        return null;
    }

    @Override
    public Category insertCategory(Category category) {
        return null;
    }

    @Override
    public void updateCategory(Category category) {

    }

    @Override
    public Category getCategoryByName(String name) {
        return null;
    }
}
