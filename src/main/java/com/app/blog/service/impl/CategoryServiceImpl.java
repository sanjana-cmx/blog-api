package com.app.blog.service.impl;

import com.app.blog.entity.Category;
import com.app.blog.exceptions.ResourceNotFoundException;
import com.app.blog.payloads.CategoryDto;
import com.app.blog.repository.CategoryRepo;
import com.app.blog.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service

public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = this.modelMapper.map(categoryDto, Category.class);
        Category addedCategory = this.categoryRepo.save(category);
        CategoryDto savedDto = this.modelMapper.map(addedCategory, CategoryDto.class);
        return savedDto;
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category ID",categoryId));
        category.setCategoryTitle(categoryDto.getCategoryTitle());
        category.setCategoryDescription(categoryDto.getCategoryDescription());
        Category updatedCategory = this.categoryRepo.save(category);
        return this.modelMapper.map(updatedCategory,CategoryDto.class);
    }

    @Override
    public void deleteCategory(Integer categoryId) {
        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category ID",categoryId));
        this.categoryRepo.delete(category);

    }

    @Override
    public CategoryDto getCategory(Integer categoryId) {

        Category category = this.categoryRepo.findById(categoryId).orElseThrow(()->new ResourceNotFoundException("Category","Category ID",categoryId));

        return this.modelMapper.map(category,CategoryDto.class);
    }

    @Override
    public List<CategoryDto> getCategories() {

        List<Category> categories = this.categoryRepo.findAll();
        List<CategoryDto> categoryDtoList = categories.stream().map((category -> this.modelMapper.map(category,CategoryDto.class))).collect(Collectors.toList());
        return categoryDtoList;
    }
}
