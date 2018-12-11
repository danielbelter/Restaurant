package com.app.restaurantgit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/dotpay")
public class DotPayController {

    @PostMapping("/dotpay")
    public String okStatus(@RequestParam("status") String status, HttpSession session) {

        if (status.equals("OK,OK")) {
            session.removeAttribute("order");

            return "dotpay/zamowienieOK";
        } else if (status.equals("FAIL,FAIL"))
            return "dotpay/zamowienieFAIL";
        else return "index";


    }
}
