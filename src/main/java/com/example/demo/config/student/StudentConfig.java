package com.example.demo.config.student;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
@Component
/*指定配置文件名，默认从classpath下寻找该文件，也就是等同于classpath:dataSource.properties
 * 可以指定多个文件
 */

@PropertySource(value = { "application.yml" })
/*
 * 指定前缀，读取的配置信息项必须包含该前缀，且除了前缀外，剩余的字段必须和实体类的属性名相同，
 * 才能完成银映射
 */
@ConfigurationProperties(prefix = "spring.datasource.student")
public class StudentConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public void setDriverClassName(String driverClassName) {
        this.driverClassName = driverClassName;
    }
}
