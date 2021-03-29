package com.example.ustimova.controller;

import com.example.ustimova.entity.Category;
import com.example.ustimova.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categorys")
public class CategoryController {
    private final CategoryService categoryService;

    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @GetMapping
    public ResponseEntity<List<Category>> readAll(){
        final List<Category> categorysList = categoryService.findAll();
        return categorysList != null && !categorysList.isEmpty()
                ? new ResponseEntity<>(categorysList, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Category> getOne(@PathVariable(name = "id") Category category) {
        final Category currentCategory = category;
        return category != null
                ? new ResponseEntity<>(currentCategory, HttpStatus.OK)
                : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    private ResponseEntity<?> create(@RequestBody Category category){
        Category newCategory = categoryService.create(category);
        return new ResponseEntity<>(newCategory, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<?> put(@PathVariable(name = "id") Category categoryFromDB,
                                 @RequestBody Category category) {
        Category updatedCategory = categoryService.update(category, categoryFromDB);
        if (updatedCategory != null) {
            return new ResponseEntity<>(updatedCategory, HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<List<Category>> delete(@PathVariable(name = "id") Category category) {
        if (categoryService.delete(category)) {
            return new ResponseEntity<>(HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}
