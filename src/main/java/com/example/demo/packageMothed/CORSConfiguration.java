package com.example.demo.packageMothed;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CORSConfiguration implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**") //设置允许跨域的路径
                .allowedHeaders("*")
                .allowedMethods("*") //设置允许的方法
                .maxAge(18000) //跨域允许时间
                .allowedOrigins("*"); //设置允许跨域请求的域名，当**Credentials为true时，**Origin不能为星号
    }
}
