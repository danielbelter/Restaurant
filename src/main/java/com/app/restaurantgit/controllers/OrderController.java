package com.app.restaurantgit.controllers;

import com.app.restaurantgit.model.*;
import com.app.restaurantgit.repository.CustomerRepository;
import com.app.restaurantgit.repository.MealRepository;
import com.app.restaurantgit.repository.OrderRepository;
import com.app.restaurantgit.service.OrderService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    MealRepository mealRepository;
    @Autowired
    OrderRepository orderRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    CustomerRepository customerRepository;

    @GetMapping
    public String index(Model model, HttpSession session) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            model.addAttribute("meal", orderService.getAllMealsForOrder(order));
            return "order/myOrder";
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
                order.setOrderStatus(OrderStatus.ZAMOWIONE);
                order.setPriority(0);
                orderRepository.save(order);
                session.setAttribute("order", order);
            } else {
                Order order = (Order) session.getAttribute("order");
                List<Meal> mealList = order.getMeals();
                mealList.add(mealRepository.findById(id).orElseThrow(NullPointerException::new));
                order.setMeals(mealList);
                order.setRealizationDate(LocalDateTime.now());
                order.setPriority(0);
                order.setOrderStatus(OrderStatus.ZAMOWIONE);
                orderRepository.save(order);
                session.setAttribute("order", order);
            }
            return "redirect:/order";
        }
    }

    @GetMapping("/complete")
    public String completeYourOrder(HttpSession session, Model model) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            model.addAttribute("customer", new Customer());
            model.addAttribute("address", new Address());

            return "customer/addCustomer";
        }
        return "customer/addCustomer";
    }

    @PostMapping("/complete")
    public String completeYourOrderPOST(HttpSession session, @ModelAttribute Customer customer, @ModelAttribute Address address) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            customer.setAddress(address);
            order.setCustomer(customer);

            customerRepository.save(customer);
            orderRepository.save(order);

            return "redirect:/order/payment";
        }
        return "redirect:/order";
    }

    @GetMapping("/payment")
    public String paymentGET(HttpSession session, Model model) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            model.addAttribute("payment", new PaymentDetail());
            model.addAttribute("order", order);
            model.addAttribute("price", orderService.priceForOrder(order));

            return "order/completeOrder";
        }
        return "order/completeOrder";
    }

    @GetMapping("/priority")
    public String priorityGET(HttpSession session, Model model) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            model.addAttribute(orderRepository.findById(order.getId()).orElse(new Order()));
            return "order/priorityOrder";
        }
        return "order/completeOrder";
    }

    @GetMapping("/priorityorder{priority}")
    public String prioritySetGET(@RequestParam("priority") Integer priority, HttpSession session) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            order.setOrderStatus(OrderStatus.ZAMOWIONE);
            order.setPriority(priority);
            orderRepository.saveAndFlush(order);
            return "redirect:/order/dotpay";
        }
        return "order/completeOrder";
    }

    @GetMapping("/dotpay")
    public String moveToDotpay(@RequestHeader String host,HttpSession session) {
        if (session.getAttribute("order") != null) {
            Order order = (Order) session.getAttribute("order");
            BigDecimal amount = BigDecimal.valueOf(0);
            int prior = order.getPriority();
            if (prior == 1) {
                amount = orderService.priceForOrder(order).add(new BigDecimal(10));
            } else {
                amount = orderService.priceForOrder(order);
            }
            String orderNumber = order.getId().toString() + order.getRealizationDate();

            String toSha256 = "2yMortk7dQcD4XKoriPEUPCTQO5IOxY8" + "dev" + "738082" + amount + "PLN" + orderNumber +host+ 0;
            String sha256hex = DigestUtils.sha256Hex(toSha256);
            String paymentUri = "https://ssl.dotpay.pl/test_payment/?api_version=dev&id=738082&amount=" + amount + "&currency=PLN&description=" + orderNumber + "&URL=guarded-earth-39191.herokuapp.com/dotpay/dotpay" + "&type=0" + "&chk=" + sha256hex;

            return "redirect:" + paymentUri;
        }
        return "redirect:/order";
    }

}
