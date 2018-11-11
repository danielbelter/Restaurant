package com.app.restaurantgit.controller;

import com.app.restaurantgit.model.Component;
import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequestMapping("/menu")
public class MenuController {
    @Autowired
    MealService mealService;

    @GetMapping("all")
    public String getAllMeals(Model model) {
        model.addAttribute("meal", mealService.findAll());
        model.addAttribute("component", mealService.findComponentForMeal());
        return "menu/allMeals";
    }

    @GetMapping("/add")
    public String addMeal(Model model) {
        try {
            model.addAttribute("meal", new Meal());
            return "manager/addMeal";
        } catch (Exception e) {
            throw new NullPointerException("ERROR");
        }
    }
    @PostMapping("/add")
    public String addMealPost(@ModelAttribute Meal meal){
        mealService.addMeal(meal);
        return "redirect:/menu";
    }
}
