package com.it.service.Imp;

import com.it.entity.Category;
import com.it.enums.CategoryStatus;
import com.it.mapper.ArticleCategoryRefMapper;
import com.it.mapper.CategoryMapper;
import com.it.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ICategoryService implements CategoryService {
    private final CategoryMapper categoryMapper;
    private final ArticleCategoryRefMapper articleCategoryRefMapper;

    @Autowired
    public ICategoryService(CategoryMapper categoryMapper, ArticleCategoryRefMapper articleCategoryRefMapper) {
        this.categoryMapper = categoryMapper;
        this.articleCategoryRefMapper = articleCategoryRefMapper;
    }

    @Override
    public Map<Category,List<Category>> MapCategory() {
        List<Category> categoryList = categoryMapper.listCategory();
        Map<Category,List<Category>> categoryMap=new HashMap<>();
        for (int i = 0; i < categoryList.size(); i++) {
            if (categoryList.get(i).getCategoryPid() == CategoryStatus.Stair.getCode()) {
                //集合的创建放到这这个位置的原因是，当发现一级菜单的时候，就需要寻找二级菜单，所以这个时候就可创建一个集合对象
                List<Category> list=new ArrayList<>();
                categoryList.get(i).setStair(CategoryStatus.Stair.getCode());
                for (int j = 0; j <categoryList.size() ; j++) {
                    if (categoryList.get(i).getCategoryId()==categoryList.get(j).getCategoryPid()){
                        categoryList.get(j).setStair(CategoryStatus.Second.getCode());
                        list.add(categoryList.get(j));
                    }
                }
                if(list.size()==0){
                    categoryList.get(i).setStair(CategoryStatus.Second.getCode());
                }
                categoryMap.put(categoryList.get(i),list);
            }

        }
        return categoryMap;
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

    @Override
    public List<Category> getCategoryByArticleId(Integer article) {
        return articleCategoryRefMapper.listCategoryByArticleId(article);
    }
}
