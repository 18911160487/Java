package com.example.demo.dao.happyshop;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

@Mapper
@Component(value = "HappyshopUserDao")
public interface HappyshopUserDao {
    void insert( @Param("name") String name, @Param("age") Integer age);
}
