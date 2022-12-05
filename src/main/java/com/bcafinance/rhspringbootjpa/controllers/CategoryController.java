package com.bcafinance.rhspringbootjpa.controllers;

import com.bcafinance.rhspringbootjpa.dto.CarsDTO;
import com.bcafinance.rhspringbootjpa.dto.CategoryDTO;
import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.handler.ResponseHandler;
import com.bcafinance.rhspringbootjpa.models.Brands;
import com.bcafinance.rhspringbootjpa.models.Cars;
import com.bcafinance.rhspringbootjpa.models.Category;
import com.bcafinance.rhspringbootjpa.services.CategoryService;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import lombok.Getter;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.modelmapper.ModelMapper;

import javax.validation.Valid;
import java.util.List;

/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 03/12/2022
@Last Modified 03/12/2022 18:19
Version 1.0
*/
@RestController
@RequestMapping("api/")
public class CategoryController {

    @Getter
    private CategoryService categoryService;

    public CategoryController(){}

    @Autowired
    public CategoryController(CategoryService categoryService){
        this.categoryService=categoryService;
    }

    @Autowired
    private ModelMapper modelMapper;

    @PostMapping("/category")
    public ResponseEntity<Object>
    saveCategory(@Valid @RequestBody Category category
    ) throws Exception {
        categoryService.saveCategory(category);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

    @GetMapping("/category/dto/get")
    public ResponseEntity<Object> findAllCategoryDTO() throws Exception {

        int data = 0;
        List<Category> lsCategory = categoryService.findAllCategory();

        if (lsCategory.size() == 0) {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
        }
        List<CategoryDTO> lsCategoryDTO = modelMapper.map(lsCategory, new TypeToken<List<CategoryDTO>>() {}.getType());

        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsCategoryDTO, null, null);
    }

    @GetMapping("/category/dto/{id}")
    public ResponseEntity<Object> getCatByIdDTO(@PathVariable("id") long id) throws Exception {
        Category category = categoryService.findByIdCategory(id);

        if (category != null) {
            CategoryDTO categoryDTO = modelMapper.map(category,CategoryDTO.class);
            return new ResponseHandler().
                    generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK,categoryDTO,null,null);

        } else {
            throw new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND);
        }
    }

//    @GetMapping("/category/dto/like/{catName}")
//    public ResponseEntity<Object> findCategoryContainingDTO(@PathVariable("catName") String catName) throws Exception {
//
//        int data = 0;
//        List<Category> lsCategory = categoryService.findByCatName(catName);
//
//        if (lsCategory.size() == 0) {
//            throw new ResourceNotFoundException(ConstantMessage.WARNING_DATA_EMPTY);
//        }
//        List<CategoryDTO> lsCategoryDTO = modelMapper.map(lsCategory, new TypeToken<List<CategoryDTO>>() {}.getType());
//
//        return new ResponseHandler().
//                generateResponse(ConstantMessage.SUCCESS_FIND_BY, HttpStatus.OK, lsCategoryDTO, null, null);
//    }

    @PutMapping("/category/updt")
    public ResponseEntity<Object> updateCategoryByID(@RequestBody Category category)throws Exception{
        categoryService.updateCategoryById(category);
        return new ResponseHandler().
                generateResponse(ConstantMessage.SUCCESS_FIND_BY,HttpStatus.OK,"",null,null);
    }

    @PostMapping("/category/batch")
    public ResponseEntity<Object>
    saveAllCategory(@Valid @RequestBody List<Category> listCategory
    ) throws Exception {
        categoryService.saveAllCategory(listCategory);
        return new ResponseHandler().generateResponse(ConstantMessage.SUCCESS_SAVE,HttpStatus.CREATED,null,null,null);
    }

}
