package com.app.restaurantgit.controllers;

import com.app.restaurantgit.model.Order;
import com.app.restaurantgit.model.OrderStatus;
import com.app.restaurantgit.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dotpay")
public class DotPayController {

    OrderRepository orderRepository;

    public DotPayController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/dotpay")
    public String okStatus(@RequestParam("status") String status, HttpSession session) {

        if (status.equals("OK,OK")) {
            if (session.getAttribute("order") != null) {
                Order order = (Order) session.getAttribute("order");
                order.setOrderStatus(OrderStatus.OPLACONE);
                orderRepository.saveAndFlush(order);


            }
            session.removeAttribute("order");

            return "dotpay/zamowienieOK";
        } else if (status.equals("FAIL,FAIL"))
            return "dotpay/zamowienieFAIL";
        else return "index";


    }
}
