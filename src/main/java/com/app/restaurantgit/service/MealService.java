package com.app.restaurantgit.service;

import com.app.restaurantgit.dao.IMeal;
import com.app.restaurantgit.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MealService {
    @Autowired
    private IMeal mealRepository;
    public List<Meal> findAll(){
        List<Meal> retList = new ArrayList<>();
        for (Meal meal : mealRepository.findAll()) {
            retList.add(meal);
        }
        return retList;
    }


}
