package com.emrevarol.demo.service;

import com.emrevarol.demo.entity.Category;
import com.emrevarol.demo.models.CategoryDto;
import com.emrevarol.demo.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CategoryService {
    private final CategoryRepository categoryRepository;
    private final ModelMapper modelMapper = new ModelMapper();

    public Category save(CategoryDto categoryDto){
        Category category = modelMapper.map(categoryDto, Category.class);
        return categoryRepository.save(category);
    }

    public void deleteById(Long id){
           categoryRepository.deleteById(id);
    }

    public Category update(CategoryDto categoryDto,Long id){
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isPresent()){
            category.get().setName(categoryDto.getName());
            category.get().setDescription(categoryDto.getDescription());
            return categoryRepository.save(category.get());
        }
        throw new IllegalArgumentException("Category not found");
    }

    public Category findById(Long id){
        return categoryRepository.findById(id).orElseThrow();
    }

    public Page<Category> findByPagination(Pageable pageable){
        return categoryRepository.findAll(pageable);
    }
}
