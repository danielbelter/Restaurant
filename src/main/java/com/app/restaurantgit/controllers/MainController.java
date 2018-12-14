package com.app.restaurantgit.controllers;

import com.app.restaurantgit.repository.MealRepository;
import com.app.restaurantgit.service.CategoryService;
import com.app.restaurantgit.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/menu")
public class MainController {

    MealService mealService;
    CategoryService categoryService;

    public MainController(MealService mealService, CategoryService categoryService) {
        this.mealService = mealService;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String menu(Model model){
        model.addAttribute("meal",mealService.findAllMeals());
        return "menu/allMeals";
    }

    @GetMapping("/category/{category}")
    public String getMealsByCategory(@PathVariable String category, Model model){
        model.addAttribute("meal",categoryService.findByCategory_Name(category));
        return "menu/categoryMeals";
    }


    @GetMapping("/search")
    public String searchMealsByCategory(@RequestParam(name = "category") String name,Model model){
        model.addAttribute("meal",categoryService.findByName(name));
        return "menu/categoryMeals";
    }
    @GetMapping("/faq")
    public String faq(){
        return "Info/faq";
    }


    @GetMapping("/law")
    public String law(){
        return "Info/law";
    }
}
