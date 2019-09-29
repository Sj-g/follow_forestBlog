package com.it.service.Imp;

import com.it.entity.Category;
import com.it.enums.CategoryStair;
import com.it.mapper.CategoryMapper;
import com.it.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ICategoryService implements CategoryService {
    private final CategoryMapper categoryMapper;

    @Autowired
    public ICategoryService(CategoryMapper categoryMapper) {
        this.categoryMapper = categoryMapper;
    }

    @Override
    public List<Category> listCategory() {
        List<Category> categoryList = categoryMapper.listCategory();
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategoryPid() == CategoryStair.Stair.getCode()) {
                categoryList.get(i).setStair(CategoryStair.Stair.getCode());
            } else {
                categoryList.get(i).setStair(CategoryStair.Second.getCode());
            }
        }
        return categoryList;
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

    @Override
    public List<Category> getCategoryBypId(Integer pId) {
        return categoryMapper.findChildCategory(pId);
    }
}
