package com.mohamed.blogball.services;

import com.mohamed.blogball.DTO.CategoryDto;

import java.util.List;

public interface CategoryServices {

    CategoryDto addCategory(CategoryDto categoryDto);

    CategoryDto getCategory(Long categoryId);

    List<CategoryDto> getAllCategories();

    void deleteCategory(Long categoryId);
}
