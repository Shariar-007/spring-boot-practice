package com.blog.application.services.implementations;

import com.blog.application.entities.Category;
import com.blog.application.exceptions.ResourceNotFoundException;
import com.blog.application.payloads.CategoryDto;
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
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category localCategory = modelMapper.map(categoryDto, Category.class);
        Category newCategory = categoryRepository.save(localCategory);
        return modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        foundedCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        foundedCategory.setCategoryDetails(categoryDto.getCategoryDetails());
        Category updatedCategory = categoryRepository.save(foundedCategory);
        return modelMapper.map(updatedCategory, CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        categoryRepository.delete(foundedCategory);
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category foundedCategory = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        return modelMapper.map(foundedCategory, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<CategoryDto> categoryDtoList = categoryRepository.findAll().stream().map(category ->  modelMapper.map(category, CategoryDto.class)).collect(Collectors.toList());
        return categoryDtoList;
    }
}
