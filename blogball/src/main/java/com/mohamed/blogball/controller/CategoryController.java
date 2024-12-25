package com.mohamed.blogball.controller;

import com.mohamed.blogball.DTO.CategoryDto;
import com.mohamed.blogball.security.AdminAccess;
import com.mohamed.blogball.services.CategoryServices;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("categories")
@RequiredArgsConstructor
@Tag(name = "categories")
public class CategoryController {

    private final CategoryServices categoryServices;

    @GetMapping("/all-categories")
    public ResponseEntity<List<CategoryDto>> getCategories() {
        return ResponseEntity.ok(categoryServices.getAllCategories());
    }

    @GetMapping("/category/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory(@PathVariable Long categoryId){
        return ResponseEntity.ok(categoryServices.getCategory(categoryId));
    }

    @PostMapping("/add-category")
    @AdminAccess
    public ResponseEntity<CategoryDto> addCategory(@RequestBody CategoryDto categoryDto) {
        CategoryDto newCategory = categoryServices.addCategory(categoryDto);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping("/update-category/{categoryId}")
    @AdminAccess
    public ResponseEntity<CategoryDto> updateCategory(
            @RequestBody CategoryDto categoryDto, @PathVariable Long categoryId
    ) {
    return ResponseEntity.ok(categoryServices.updateCategory(categoryDto, categoryId));
    }

    @DeleteMapping("/delete-category/{categoryId}")
    @AdminAccess
    public ResponseEntity<Void> deleteCategory(@PathVariable Long categoryId){
        categoryServices.deleteCategory(categoryId);
        return ResponseEntity.noContent().build();
    }

}
