package com.app.restaurantgit.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping()
public class DotPayController {

    @GetMapping("/dotpay")
    public String okStatus(@RequestParam("status") String status) {
        if (status.equals("OK"))
            return "dotpay/zamowienieOK";
        if (status.equals("FAIL"))
            return "dotpay/zamowienieFAIL";
        else
            return "dotpay/zamowienieINNE";
    }
    @GetMapping()
    public String index() {
        return "index";
    }
}
