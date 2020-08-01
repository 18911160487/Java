package com.example.demo.service.happyshop;

import com.example.demo.dao.happyshop.HappyshopUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class HappyshopUserService {
    @Autowired
    HappyshopUserDao happyshopUserDao;

    public void createUser(String name, Integer age) {
        happyshopUserDao.insert(name, age);
    }
}
