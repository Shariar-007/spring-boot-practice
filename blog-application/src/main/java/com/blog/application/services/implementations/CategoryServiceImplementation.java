package com.blog.application.services.implementations;

import com.blog.application.entities.Category;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.CategoryDao;
import com.blog.application.repositories.CategoryRepository;
import com.blog.application.services.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImplementation implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDao createCategory(CategoryDao categoryDao) {
        Category localCategory = modelMapper.map(categoryDao, Category.class);
        Category newCategory = categoryRepository.save(localCategory);
        return modelMapper.map(newCategory, CategoryDao.class);
    }

    @Override
    public CategoryDao updateCategory(CategoryDao categoryDao, Integer categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        foundedCategory.setCategoryTitle(categoryDao.getCategoryTitle());
        foundedCategory.setCategoryDetails(categoryDao.getCategoryDetails());
        Category updatedCategory = categoryRepository.save(foundedCategory);
        return modelMapper.map(updatedCategory, CategoryDao.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(foundedCategory);
    }

    @Override
    public CategoryDao getCategoryById(Integer categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return modelMapper.map(foundedCategory, CategoryDao.class);
    }

    @Override
    public List<CategoryDao> getCategories() {
        List<CategoryDao> categoryDaoList = categoryRepository.findAll().stream().map(category ->  modelMapper.map(category, CategoryDao.class)).collect(Collectors.toList());
        return categoryDaoList;
    }
}
