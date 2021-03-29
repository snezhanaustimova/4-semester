package com.example.ustimova.service;

import com.example.ustimova.entity.Category;
import com.example.ustimova.repository.CategoryRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepo categoryRepo;

    public List<Category> findAll() {
        return categoryRepo.findAll();
    }

    public Category create(Category category) {
        return categoryRepo.save(category);
    }

    public Category update(Category category, Category categoryFromDB) {
        BeanUtils.copyProperties(category, categoryFromDB, "id");
        return categoryRepo.save(categoryFromDB);
    }

    public boolean delete (Category category) {
        if (category != null) {
            categoryRepo.delete(category);
            return true;
        }
        return false;
    }
}