package com.ShopGameBD.ShopGamePostgre.controller;


import com.ShopGameBD.ShopGamePostgre.dto.CategoryDTO;
import com.ShopGameBD.ShopGamePostgre.dto.CategoryRequestDTO;
import com.ShopGameBD.ShopGamePostgre.entity.Category;
import com.ShopGameBD.ShopGamePostgre.service.CategoryService;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    private CategoryService categoryService;

    public CategoryController(CategoryService categoryService){
        this.categoryService = categoryService;
    }

    @GetMapping
    public List<CategoryDTO> allCategory(){
        List<CategoryDTO> getCategoryAll = categoryService.getAllCategory();

        return getCategoryAll;
    }

    @PostMapping
    public ResponseEntity<CategoryDTO> createCategory(@Valid  @RequestBody CategoryRequestDTO dto){
        CategoryDTO createNewCategory = categoryService.createCategory(dto);
        return ResponseEntity.ok(createNewCategory);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<CategoryDTO> patchCategory(@PathVariable Integer id,@Valid @RequestBody CategoryRequestDTO dto){
        CategoryDTO patchCategory = categoryService.patchCategory(id, dto);

        return ResponseEntity.ok(patchCategory);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Integer id){
        String deleteCategory = categoryService.deleteCategory(id);
        return ResponseEntity.ok(deleteCategory);
    }

}
