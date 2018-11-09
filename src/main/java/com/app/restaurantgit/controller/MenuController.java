package com.app.restaurantgit.controller;

import com.app.restaurantgit.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MealService mealService;

    @GetMapping("all")
    public String getAllMeals(Model model){
        model.addAttribute("meal", mealService.findAll());
        return "menu/allMeals";
    }
}
