package com.example.demo.packageMothed;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.StringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.*;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class PackageFun {
    public Object resizeObjAttribute(Object obj, HttpServletRequest request)
            throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Field[] field = obj.getClass().getDeclaredFields();
        for (int j = 0; j < field.length; j++) {     //遍历所有属性
            String attributeName = field[j].getName();    //获取属性的名字
            String updateAttributeName = attributeName.substring( 0, 1 ).toUpperCase() + attributeName.substring( 1 ); //将属性的首字符大写，方便构造get，set方法
            String type = field[j].getGenericType().toString();    //获取属性的类型
            String parameterName = request.getParameter( attributeName );
            if (parameterName != null && !"".equals( parameterName ) && !"id".equals( attributeName )) {
                if (type.equals( "class java.lang.String" )) {   //如果type是类类型，则前面包含"class "，后面跟类名
                    Method m = obj.getClass().getDeclaredMethod( "set" + updateAttributeName, String.class );
                    String value = (String) m.invoke( obj, parameterName );   //调用setter方法获取属性值
                }
                if (type.equals( "int" )) {
                    Method m = obj.getClass().getDeclaredMethod( "set" + updateAttributeName, int.class );
                    Integer value = (int) m.invoke( obj, parameterName );
                }
                if (type.equals( "class java.util.Date" )) {
                    Method m = obj.getClass().getDeclaredMethod( "set" + updateAttributeName, Date.class );
                    Date value = (Date) m.invoke( obj, parameterName );
                }
                if (type.equals( "class java.lang.Integer" )) {
                    Method m = obj.getClass().getDeclaredMethod( "set" + updateAttributeName, Integer.class );
                    Integer value = (Integer) m.invoke( obj );
                }
                if (type.equals( "class java.lang.Short" )) {
                    Method m = obj.getClass().getDeclaredMethod( "set" + updateAttributeName, Short.class );
                    Short value = (Short) m.invoke( obj );
                }
                if (type.equals( "class java.lang.Double" )) {
                    Method m = obj.getClass().getDeclaredMethod( "set" + updateAttributeName, Double.class );
                    Double value = (Double) m.invoke( obj );
                }
                if (type.equals( "class java.lang.Boolean" )) {
                    Method m = obj.getClass().getDeclaredMethod( "set" + updateAttributeName, Boolean.class );
                    Boolean value = (Boolean) m.invoke( obj );
                }
            }
        }
        return obj;
    }

    public String requestInterface(String url) throws Exception {
        String Param = "{\"id\":1001}";
        JSONObject date = (JSONObject) JSONObject.parse( Param );
        HttpClient client = HttpClients.createDefault();
        HttpPost post = new HttpPost( url );
        StringEntity s = new StringEntity( date.toString() );
        s.setContentEncoding( "UTF-8" );
        s.setContentType( "application/json" );
        post.setEntity( s );
        post.addHeader( "content-type", "text/xml" );
        HttpResponse res = client.execute( post );
        return EntityUtils.toString( res.getEntity() );
    }

    public String operationFile(String route, String imgStr) throws Exception { //base64 图片资源写入文件
        Base64 base64 = new Base64();
        // Base64解码
        byte[] bytes = base64.decode(imgStr);
        for (int i = 0; i < bytes.length; ++i) {
            if (bytes[i] < 0) {// 调整异常数据
                bytes[i] += 256;
            }
        }
        File file = new File( route.substring( 0, route.lastIndexOf( "/" ) ) );
        if (!file.exists()) {
            file.mkdirs();
        }
        // 生成jpeg图片
        OutputStream out = new FileOutputStream( route );
        out.write( bytes );
        out.flush();
        out.close();
        return route;
    }

    public void deleteAllFilesOfDir(File path) { //删除文件和图片
        if (null != path) {
            if (!path.exists())
                return;
            if (path.isFile()) {
                boolean result = path.delete();
                int tryCount = 0;
                while (!result && tryCount++ < 10) {
                    System.gc(); // 回收资源
                    result = path.delete();
                }
            }
            File[] files = path.listFiles();
            if (null != files) {
                for (int i = 0; i < files.length; i++) {
                    deleteAllFilesOfDir( files[i] );
                }
            }
            path.delete();
        }
    }

    //生成文件上传地址+名称
    public static Map<String, String> productUploadPathAndFileName(MultipartFile multipartFile, String savePath) throws InterruptedException {
        Thread.sleep(1);
        String fileName = multipartFile.getOriginalFilename();
        //默认后缀名
        String extrName = "unknown";
        if (StringUtils.isNotBlank( fileName )) {
            extrName = fileName.substring( fileName.lastIndexOf( "." ) );
        }
        String currentTime = System.currentTimeMillis() + "";
        String filePath = savePath + currentTime + extrName;
        Map<String, String> map = new HashMap<>();
        map.put( "currentTime", currentTime );
        map.put( "extrName", extrName );
        map.put( "filePath", filePath );
        return map;
    }

    //将file文件写入到服务器
    public static void writeFile(InputStream inputStream, File file) throws IOException {
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream( file );
            byte[] data = new byte[1024];
            int len = 0;
            while ((len = inputStream.read( data, 0, 1024 )) != -1) {
                outputStream.write( data );
            }
        } finally {
            if (inputStream != null) {
                inputStream.close();
            }
            if (outputStream != null) {
                outputStream.close();
            }
        }
    }

}
