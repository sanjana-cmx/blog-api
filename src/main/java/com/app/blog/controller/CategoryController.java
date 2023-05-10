package com.app.blog.controller;

import com.app.blog.payloads.ApiResponse;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/")
    public ResponseEntity<CategoryDto> createCategory (@RequestBody CategoryDto categoryDto){

        CategoryDto createdDto = this.categoryService.createCategory(categoryDto);

        return new ResponseEntity<>(createdDto, HttpStatus.CREATED);

    }

    @PutMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> updateCategory (@RequestBody CategoryDto categoryDto,@PathVariable Integer categoryId){

        CategoryDto updatedDto = this.categoryService.updateCategory(categoryDto,categoryId);

        return new ResponseEntity<>(updatedDto, HttpStatus.OK);

    }
    @DeleteMapping("/{categoryId}")
    public ResponseEntity<ApiResponse> deleteCategory (@PathVariable Integer categoryId){

        this.categoryService.deleteCategory(categoryId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Category deleted Successfully",true),HttpStatus.OK);

    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<CategoryDto> getCategory (@PathVariable Integer categoryId){

        CategoryDto getDto = this.categoryService.getCategory(categoryId);

        return new ResponseEntity<>(getDto, HttpStatus.OK);

    }

    @GetMapping("/")
    public ResponseEntity<List<CategoryDto>> getAllCategory (){

        List<CategoryDto> categoryDtoList = this.categoryService.getCategories();

        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);

    }
}
