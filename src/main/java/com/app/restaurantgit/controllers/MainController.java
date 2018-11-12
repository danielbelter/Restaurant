package com.app.restaurantgit.controllers;

import com.app.restaurantgit.repository.MealRepository;
import com.app.restaurantgit.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MainController {
    @Autowired
    MealService mealService;

    @GetMapping()
    public String menu(Model model){
        model.addAttribute("meal",mealService.findAllMeals());
        return "menu/allMeals";
    }
}
