package com.app.restaurantgit.service;

import com.app.restaurantgit.model.Customer;
import com.app.restaurantgit.model.Meal;
import com.app.restaurantgit.model.Order;
import com.app.restaurantgit.repository.CustomerRepository;
import com.app.restaurantgit.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class OrderService {
    @Autowired
    OrderService orderService;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    CustomerRepository customerRepository;

    public List<Meal> getAllMealsForOrder(Order order) {
        List<Meal> retList = new ArrayList<>();
        for (Meal m : order.getMeals()) {
            retList.add(m);
        }
        return retList;
    }
// test for heroku
    public BigDecimal priceForOrder(Order order) {
        BigDecimal price = new BigDecimal(0);
        for (Meal m : order.getMeals()) {
            price = price.add(m.getPrice());
        }
        return price;

    }

}
