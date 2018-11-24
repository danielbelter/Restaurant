package com.app.restaurantgit.controllers;

import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.model.Order;
import com.app.restaurantgit.repository.MealRepository;
import com.app.restaurantgit.repository.OrderRepository;
import com.app.restaurantgit.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    MealRepository mealRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;

    @GetMapping
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            model.addAttribute("meal", orderService.getAllMealsForOrder(order));
            return "myOrder";
        }
        return "menu/allMeals";
    }

    @GetMapping("/add{id}")
    public String addMealToOrder(@RequestParam("id") Long id, HttpSession session) {
        {
            if (session.getAttribute("order") == null) {
                Order order = new Order();
                List<Meal> mealList = new ArrayList<>();
                mealList.add(mealRepository.findById(id).orElseThrow(NullPointerException::new));
                order.setMeals(mealList);
                order.setRealizationDate(LocalDateTime.now());
                orderRepository.save(order);
                session.setAttribute("order", order);
            } else {
                Order order = (Order) session.getAttribute("order");
                List<Meal> mealList = order.getMeals();
                mealList.add(mealRepository.findById(id).orElseThrow(NullPointerException::new));
                order.setMeals(mealList);
                order.setRealizationDate(LocalDateTime.now());
                orderRepository.save(order);
                session.setAttribute("order", order);
            }
            return "redirect:/order";
        }
    }
/*
    @GetMapping("/complete")
    public String completeYourOrder(){

    }
    */

}
