package com.mohamed.blogball.services.Impl;

import com.mohamed.blogball.DTO.CategoryDto;
import com.mohamed.blogball.Repositories.CategoryRepository;
import com.mohamed.blogball.model.Category;
import com.mohamed.blogball.services.CategoryServices;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class CategoryServicesImpl implements CategoryServices {

    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        Category newCategory = categoryRepository.save(category);

        return modelMapper.map(newCategory, CategoryDto.class);
    }

    @Override
    public CategoryDto getCategory(Long categoryId) {
        Category category = categoryRepository
                .findById(categoryId)
                .orElseThrow( () -> new RuntimeException("Category Not Fount"));

        return modelMapper.map(category, CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getAllCategories() {
        List<Category> categories = categoryRepository.findAll();

        return categories
                .stream()
                .map((category) -> modelMapper.map(category, CategoryDto.class))
                .collect(Collectors.toList());
    }

    @Override
    public void deleteCategory(Long categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        category.ifPresent(categoryRepository::delete);
    }
}
