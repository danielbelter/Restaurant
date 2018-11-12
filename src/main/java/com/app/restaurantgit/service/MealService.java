package com.app.restaurantgit.service;

import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {

    @Autowired
    MealRepository mealRepository;

    public List<Meal> findAllMeals() {
        return mealRepository.findAll();
    }


}
