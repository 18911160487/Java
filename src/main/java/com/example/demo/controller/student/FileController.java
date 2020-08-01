package com.example.demo.controller.student;

import com.example.demo.entity.Json;
import com.example.demo.packageMothed.PackageFun;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/file")
@SpringBootApplication
public class FileController {
    public PackageFun packageFun = new PackageFun();
    public Logger logger = LoggerFactory.getLogger( TestController.class );

    @RequestMapping("/upLoadImg")
    @ResponseBody
    public Object upLoadImg(HttpServletRequest request) {
        String savePath = "D:/img/upLoadImg/";
        String serverPath = "https://www.liuxiangpo.com/img/upLoadImg/";
        File file = new File( savePath );
        if (!file.exists()) {
            file.mkdirs();
        }
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        ServletFileUpload servletFileUpload = new ServletFileUpload( diskFileItemFactory );
        //构建上传返回结果集
        Map<String, String> resultMap = new HashMap<>();
        //接受前端传送文件
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
        try {
            for (Map.Entry<String, MultipartFile> map : fileMap.entrySet()) {
                MultipartFile multipartFile = map.getValue();
                //获取文件上传地址+路径
                Map<String, String> pathAndFileName = packageFun.productUploadPathAndFileName( multipartFile, savePath );
                //文件在服务器路径
                String filePath = pathAndFileName.get( "filePath" );
                File uplodeFile = new File( filePath );
                //写入流
                packageFun.writeFile( multipartFile.getInputStream(), uplodeFile );
                //上传文件读取路径
                String uploadPath = serverPath + pathAndFileName.get( "currentTime" ) + pathAndFileName.get( "extrName" );
                resultMap.put( multipartFile.getOriginalFilename(), uploadPath );
            }
        } catch (Exception e) {
            return new Json( "0002", "上传图片异常" );
        }
        return new Json( "0000", resultMap.size() + "", resultMap );
    }
}
