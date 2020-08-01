package com.example.demo.controller.happyshop;

import com.example.demo.service.happyshop.HappyshopUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HappyshopController {

    @Autowired
    HappyshopUserService happyshopUserService;

    @RequestMapping("/add2")
    public String createUser2(String name, Integer age) {
        happyshopUserService.createUser(name, age);
        return "ok";
    }
}