package com.example.demo.controller.student;

import com.example.demo.entity.Json;
import com.example.demo.packageMothed.PackageFun;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Base64;

@Controller
@RequestMapping("/test")
@SpringBootApplication
class TestController {
    public PackageFun packageFun = new PackageFun();
    public Logger logger = LoggerFactory.getLogger(TestController.class);

    @RequestMapping("/hello")
    @ResponseBody
    public Object home(HttpServletRequest request) {
        logger.info("This is an info message");

        return new Json("0000");
    }
}
