package com.example.demo.config.student;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
//只从com.stu.dao包下读取dao文件，并且该报下的dao使用本datasource
@MapperScan(basePackages = "com.example.demo.dao.student", sqlSessionTemplateRef = "studentSqlSessionTemplate")
public class StudentConfiguration {
    @Bean(name = "studentDataSource")
    //对于多数据源，必须制定primary，否则报错有2个datasource，并且，只能制定一个primary
    @Primary
    //从DB1Config从获取配置信息
    public DataSource setDataSource(StudentConfig dbc) {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(dbc.getUrl());
        ds.setUsername(dbc.getUsername());
        ds.setPassword(dbc.getPassword());
        ds.setDriverClassName(dbc.getDriverClassName());
        return ds;
    }

    @Bean(name = "studentSqlSessionFactory")
    @Primary
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("studentDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        //指定mapper.xml文件存放位置
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/student/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "studentSqlSessionTemplate")
    @Primary
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("studentSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}