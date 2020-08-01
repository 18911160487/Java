package com.example.demo.dao.student;

import com.example.demo.entity.student.Showcase;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component(value = "ShowCaseMapper")
public interface ShowCaseDao {
    int insertShowcase(@Param("showcase") Showcase showcase, @Param("tableName") String tableName);

    void updateShowcase(@Param("showcase") Showcase showcase, @Param("tableName") String tableName);

    void deleteShowcase(@Param("moduleType") String moduleType, @Param("imgId") String imgId, @Param("tableName") String tableName);

    List<Showcase> queryShowcase(@Param("moduleType") String moduleType, @Param("imgId") String imgId, @Param("tableName") String tableName);

    List<Showcase> queryShowcases(@Param("tableName") String tableName);
}
