package com.example.demo.config.happyshop;
import javax.sql.DataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import com.alibaba.druid.pool.DruidDataSource;

@Configuration
//只从com.stu.test2dao包下读取dao文件，并且该报下的dao使用本datasource
@MapperScan(basePackages = "com.example.demo.dao.happyshop", sqlSessionTemplateRef = "happyshopSqlSessionTemplate")
public class HappyshopConfiguration {
    @Bean(name = "happyshopDataSource")
    @ConfigurationProperties(prefix = "spring.application.happyshop")
    public DataSource setDataSource(HappyshopConfig dbc) {
        DruidDataSource ds = new DruidDataSource();
        ds.setUrl(dbc.getUrl());
        ds.setUsername(dbc.getUsername());
        ds.setPassword(dbc.getPassword());
        ds.setDriverClassName(dbc.getDriverClassName());
        return ds;
    }

    @Bean(name = "happyshopSqlSessionFactory")
    public SqlSessionFactory setSqlSessionFactory(@Qualifier("happyshopDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/happyshop/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "happyshopSqlSessionTemplate")
    public SqlSessionTemplate setSqlSessionTemplate(@Qualifier("happyshopSqlSessionFactory") SqlSessionFactory sqlSessionFactory) throws Exception {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}
