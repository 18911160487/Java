package com.example.demo.service.student;

import com.example.demo.dao.student.StudentUserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class StudentUserService {
    @Autowired
    StudentUserDao studentUserDao;

    public void createUser(String name, Integer age) {
        studentUserDao.insert(name, age);
    }
}
