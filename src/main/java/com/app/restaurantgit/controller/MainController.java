package com.app.restaurantgit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
    @GetMapping("/test")
    public String main(){
        return "order";
    }
}
