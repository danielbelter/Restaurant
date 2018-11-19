package com.app.restaurantgit.controllers;

import com.app.restaurantgit.model.Category;
import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.repository.CategoryRepository;
import com.app.restaurantgit.repository.MealRepository;
import com.app.restaurantgit.service.CategoryService;
import com.app.restaurantgit.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/manager")
public class ManagerController {

    MealService service;
    MealRepository mealRepository;
    CategoryRepository categoryRepository;
    CategoryService categoryService;

    public ManagerController(MealService service, MealRepository mealRepository,CategoryService categoryService, CategoryRepository categoryRepository) {
        this.service = service;
        this.mealRepository = mealRepository;
        this.categoryRepository = categoryRepository;
        this.categoryService = categoryService;
    }

    @GetMapping()
    public String managerComponent(Model model){
        model.addAttribute("meal",mealRepository.findAll());
        return "manager/component";
    }

    @GetMapping("/add")
    public String addMealGet(Model model){
        model.addAttribute("meal",new Meal());
        model.addAttribute("category",categoryRepository.findAll());
        return "manager/addMeal";
    }
    @PostMapping("/add")
    public String addMealPost(@ModelAttribute Meal meal){
        service.addMeal(meal);
        return "redirect:/manager/add";
    }


    @GetMapping("/edit/{id}")
    public String workersEdit(Model model, @PathVariable Long id) {
        try {
            model.addAttribute("meal", mealRepository.findById(id).orElseThrow(NullPointerException::new));
            return "manager/editMeal";
        } catch (Exception e) {
            throw new NullPointerException("error");
        }
    }

    @PostMapping("/edit")
    public String workersEditPost(@ModelAttribute Meal meal) {
        try {
            mealRepository.save(meal);
            return "redirect:/manager";
        } catch (Exception e) {
            throw new NullPointerException("error");
        }
    }


    @PostMapping("/remove")
    public String workersRemovePost(@RequestParam Long id) {
        try {
            mealRepository.deleteById(id);
            return "redirect:/manager";
        } catch (Exception e) {
            throw new NullPointerException("error");
        }
    }

    @GetMapping("/category/add")
    public String addCategoryGet(Model model){
        model.addAttribute("category",new Category());
        return "manager/addCategory";
    }
    @PostMapping("/category/add")
    public String addCategoryPost(@ModelAttribute Category category){
        categoryService.addCategory(category);
        return "redirect:/manager/category/add";
    }

}
