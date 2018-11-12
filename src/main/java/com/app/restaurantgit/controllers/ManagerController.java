package com.app.restaurantgit.controllers;

import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/manager")
public class ManagerController {


    @Autowired
    MealRepository mealRepository;


    @GetMapping()
    public String managerComponent(Model model){
        model.addAttribute("meal",mealRepository.findAll());
        return "manager/component";
    }

    @GetMapping("/add")
    public String addMealGet(Model model){
        model.addAttribute("meal",new Meal());
        return "manager/addMeal";
    }
    @PostMapping("/add")
    public String addMealPost(@ModelAttribute Meal meal,Model model){
        model.addAttribute("meal",meal);
        mealRepository.save(meal);

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


}
