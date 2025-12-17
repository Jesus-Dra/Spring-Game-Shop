package com.ShopGameBD.ShopGamePostgre.service;

import com.ShopGameBD.ShopGamePostgre.dto.CategoryDTO;
import com.ShopGameBD.ShopGamePostgre.dto.CategoryRequestDTO;
import com.ShopGameBD.ShopGamePostgre.entity.Category;
import com.ShopGameBD.ShopGamePostgre.exception.CategoryAlreadyExistException;
import com.ShopGameBD.ShopGamePostgre.exception.CategoryNotFound;
import com.ShopGameBD.ShopGamePostgre.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    public CategoryDTO EntityToDto(Category convert){
        CategoryDTO convertToDto = new CategoryDTO();
        convertToDto.setName(convert.getName());
        return convertToDto;
    }

    public Category dtoToCategory(CategoryRequestDTO dto){
        Category categoryConvert = new Category();
        categoryConvert.setName(dto.getName());
        return categoryConvert;
    }

    public List<CategoryDTO> getAllCategory(){
        List<Category> allCategory = categoryRepository.findAll();

        List<CategoryDTO> convertListGame = new ArrayList<>();

        for(Category category: allCategory){
            convertListGame.add(EntityToDto(category));
        }

        return convertListGame;
    }

    public CategoryDTO createCategory(CategoryRequestDTO dto){
        categoryRepository.findByName(dto.getName()).ifPresent(category -> {
            throw new CategoryAlreadyExistException("Ya hay una categoria con ese nombre: "+dto.getName());
        });

        Category addCategoryBD = dtoToCategory(dto);
        Category saveCategoryBD = categoryRepository.save(addCategoryBD);

        return EntityToDto(saveCategoryBD);
    }

    public CategoryDTO patchCategory(Integer id, CategoryRequestDTO newData){
        Category existing = categoryRepository.findById(id).
                orElseThrow(() -> new CategoryNotFound("Categoria no encontrada: "+newData.getName()));

        if(newData.getName() != null){
            existing.setName(newData.getName());
        }

        Category update = categoryRepository.save(existing);

        return EntityToDto(update);
    }

    public String deleteCategory(Integer id){
        Category existing = categoryRepository.findById(id).
                orElseThrow(()-> new CategoryNotFound("No se encontro la categoria"));

        String nameCategory = existing.getName();

        categoryRepository.deleteById(existing.getId());

        return nameCategory;
    }

}
