package com.app.restaurantgit.service;

import com.app.restaurantgit.dao.IComponent;
import com.app.restaurantgit.dao.IMeal;
import com.app.restaurantgit.model.Component;
import com.app.restaurantgit.model.Meal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class MealService {
    @Autowired
    private IMeal mealRepository;
    private IComponent componentRepository;


    public List<Meal> findAll() {
        List<Meal> retList = new ArrayList<>();
        for (Meal meal : mealRepository.findAll()) {
            retList.add(meal);
        }
        return retList;
    }

    public List<Component> findComponentForMeal() {
        List<Component> components = new ArrayList<>();
        for(Meal m: mealRepository.findAll()){
            components.addAll(m.getComponents());
        }
        List retList = components.stream().map(component -> component.getName()).collect(Collectors.toList());
        //retList.stream().filter(component -> component.getId().equals(mealRepository.findById(component.getId())));
        return retList;
    }
    public void addMeal(Meal meal){
        mealRepository.save(meal);
    }


}
