package com.example.demo.controller.student;

import com.example.demo.service.happyshop.HappyshopUserService;
import com.example.demo.service.student.StudentUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StudentController {

    @Autowired
    StudentUserService studentUserService;

    @RequestMapping("/add")
    public String createUser(String name, Integer age) {
        studentUserService.createUser(name, age);
        return "ok";
    }
}