package com.blog.application.services;

import com.blog.application.payloads.CategoryDao;

import java.util.List;

public interface CategoryService {
    CategoryDao createCategory(CategoryDao category);

    CategoryDao updateCategory(CategoryDao category, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDao getCategoryById(Integer categoryId);

    List<CategoryDao> getCategories();
}
