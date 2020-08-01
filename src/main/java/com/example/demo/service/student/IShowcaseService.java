package com.example.demo.service.student;

import com.example.demo.entity.student.Showcase;

import java.util.List;

public interface IShowcaseService {

    void insertShowcase(Showcase showcase, String tableName);

    void updateShowcase(Showcase showcase, String tableName);

    void deleteShowcase(String moduleType, String imgId, String tableName);

    List<Showcase> queryShowcase(String moduleType, String imgId, String tableName);

    List<Showcase> queryShowcases(String tableName);


}
