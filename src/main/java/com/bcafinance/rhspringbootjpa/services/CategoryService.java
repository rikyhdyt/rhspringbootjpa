package com.bcafinance.rhspringbootjpa.services;/*
Created by IntelliJ IDEA 2022.2.3 (Community Edition)
Build #IC-222.4345.14, built on October 5, 2022
@Author Kyoto a.k.a Riky Hidayat
Java Developer
Created on 03/12/2022
@Last Modified 03/12/2022 17:49
Version 1.0
*/

import com.bcafinance.rhspringbootjpa.handler.ResourceNotFoundException;
import com.bcafinance.rhspringbootjpa.models.Category;
import com.bcafinance.rhspringbootjpa.repos.CategoryRepo;
import com.bcafinance.rhspringbootjpa.utils.ConstantMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class CategoryService {

    private CategoryRepo categoryRepo;

    @Autowired

    public CategoryService(CategoryRepo categoryRepo){
        this.categoryRepo=categoryRepo;
    }

    public List<Category> findAllCategory()
    {
        return categoryRepo.findAll();
    }

    public Category findByIdCategory(Long id) throws Exception
    {
        return categoryRepo.findById(id).
                orElseThrow(() -> new ResourceNotFoundException(ConstantMessage.WARNING_NOT_FOUND));
    }

//    public List<Category> findByCatName(String catName) { return categoryRepo.findCategoriesByCategoryName(catName);}

    public void saveCategory(Category category) throws Exception {
//        if (category.getCatName() == null)
//            throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);
//        if (category.getCatDesc() == null)
//            throw new DataIntegrityViolationException(ConstantMessage.ERROR_DATA_INVALID);

        categoryRepo.save(category);
    }

    @Transactional
    public void updateCategoryById(Category c) throws Exception {
        Category category = categoryRepo.findById(c.getId()).orElseThrow(() ->
                new ResourceNotFoundException(ConstantMessage.WARNING_CAR_NOT_FOUND));
        category.setModifiedBy("1");
        category.setModifiedDate(new Date());

        if(c.getCatName() != null
                && !Objects.equals(category.getCatName(),c.getCatName())
                && !c.getCatName().equals(""))
        {
            category.setCatName(c.getCatName());//BERARTI ADA PERUBAHAN DI SINI
        }

        if(c.getCatDesc() != null
                && !Objects.equals(category.getCatDesc(),c.getCatDesc())
                && !c.getCatDesc().equals(""))
        {
            category.setCatDesc(c.getCatDesc());//BERARTI ADA PERUBAHAN DI SINI
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    public void saveAllCategory(List<Category> ls)
    {
        categoryRepo.saveAll(ls);
    }
}
