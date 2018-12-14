package com.app.restaurantgit.service;

import com.app.restaurantgit.model.Category;
import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.repository.CategoryRepository;
import com.app.restaurantgit.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    MealRepository mealRepository;


    public void addCategory(Category category){
        category.setName(category.getName().toLowerCase());
        categoryRepository.save(category);
    }


    public List<Meal> findByCategory_Name(String category) {
        category = category.toLowerCase();
        return mealRepository.findByCategory_Name(category);
    }

    public Meal findByName(String name){
      return mealRepository.findByName(name);
    }



}
