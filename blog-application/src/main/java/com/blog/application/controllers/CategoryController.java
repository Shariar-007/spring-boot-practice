package com.blog.application.controllers;

import com.blog.application.payloads.ApiResponse;
import com.blog.application.payloads.CategoryDao;
import com.blog.application.services.CategoryService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
@CrossOrigin("*")
@Tag(name = "Api/Category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping
    public ResponseEntity<CategoryDao> createCategory(@Valid @RequestBody CategoryDao categoryDao) {
        CategoryDao createdCategory = categoryService.createCategory(categoryDao);
        return new ResponseEntity<>(createdCategory, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDao> updateCategory(@Valid @RequestBody CategoryDao categoryDao, @PathVariable("categoryId") Integer categoryId) {
        CategoryDao updatedCategory = categoryService.updateCategory(categoryDao, categoryId);
        return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> removeCategory(@PathVariable("categoryId") Integer categoryId) {
        categoryService.deleteCategory(categoryId);
        return new ResponseEntity<>(new ApiResponse("User Deleted Successfully", true), HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<CategoryDao>> getAllCategory() {
        return ResponseEntity.ok(categoryService.getCategories());
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDao> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
        CategoryDao foundedCategory = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(foundedCategory, HttpStatus.OK);
    }
}
