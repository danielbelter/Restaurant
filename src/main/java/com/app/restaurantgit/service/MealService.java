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

    public List<Meal> findByCategory_Name(String category) {
        category = category.toLowerCase();
        /*
        toLoweCase - przyda sie jak w formularzu manager
        bedzie dodawal kategorie i bedzie uzywal roznych wielkosci liter
        i bedzie problem z uzyskaniem konkretnek kategori. 
         */
        return mealRepository.findByCategory_Name(category);
    }

    public void addMeal(Meal meal) {

        mealRepository.save(meal);
    }

}
