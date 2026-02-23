package com.blog.application.services;

import com.blog.application.payloads.CategoryDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto category);

    CategoryDto updateCategory(CategoryDto category, Integer categoryId);

    void deleteCategory(Integer categoryId);

    CategoryDto getCategoryById(Integer categoryId);

    List<CategoryDto> getCategories();
}
