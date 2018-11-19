package com.app.restaurantgit.service;

import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.repository.CategoryRepository;
import com.app.restaurantgit.repository.MealRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MealService {

    MealRepository mealRepository;
    CategoryRepository categoryRepository;

    public MealService(MealRepository mealRepository, CategoryRepository categoryRepository) {
        this.mealRepository = mealRepository;
        this.categoryRepository = categoryRepository;
    }

    public List<Meal> findAllMeals() {
        return mealRepository.findAll();
    }

    public void addMeal(Meal meal) {

        mealRepository.save(meal);
    }

}
