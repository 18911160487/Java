package com.example.demo.dao.student;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "StudentUserDao")
public interface StudentUserDao {
    void insert(@Param("name") String name, @Param("age") Integer age);
}
