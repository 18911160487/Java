package com.example.demo.controller.student;

import com.example.demo.entity.Json;
import com.example.demo.entity.student.Showcase;
import com.example.demo.packageMothed.PackageFun;
import com.example.demo.service.student.IShowcaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.util.List;

@Controller
@RequestMapping("showcase")
public class ShowcaseController {
    @Autowired
    private IShowcaseService showcaseService;
    public PackageFun packageFun = new PackageFun();

    private static String localPath = "D:/img/";
    private static String officialPath = "https://www.liuxiangpo.com/img/";

    @RequestMapping("insertShowcase")
    @ResponseBody
    public Json insertShowcase(HttpServletRequest request,
                               @RequestParam(value = "moduleType") String moduleType,
                               @RequestParam(value = "imgId") String imgId,
                               @RequestParam(value = "imgUrl") String imgUrl,
                               @RequestParam(value = "tableName") String tableName) {
        String moduleName = request.getParameter( "moduleName" );
        String imgName = request.getParameter( "imgName" );
        String imgDesc = request.getParameter( "imgDesc" );
        String showAndHide = request.getParameter( "showAndHide" );

        List<Showcase> showcases = showcaseService.queryShowcase( moduleType, imgId, tableName );
        if (showcases.size() > 0) {
            return new Json( "0001", "添加失败，模块类型或者图片id重复", showcaseService.queryShowcase( moduleType, imgId, tableName ) );
        }

        String route = localPath + tableName + "Img/" + moduleType + "/" + System.currentTimeMillis() + ".png";
        if (imgUrl != null && !"".equals( imgUrl )) {
            try {
                route = packageFun.operationFile( route, imgUrl.split( "base64," )[1] );
            } catch (Exception e) {
                return new Json( "0001", "数据异常" );
            }
        }
        String storagePath = officialPath + route.replace( localPath, "" );
        showcaseService.insertShowcase( new Showcase( moduleType, moduleName, imgId, imgName, storagePath, imgDesc, showAndHide ), tableName );
        List<Showcase> showcase = showcaseService.queryShowcase( moduleType, imgId, tableName );
        return new Json( "0000", "成功了", showcase );
    }

    @RequestMapping("queryShowcase")
    @ResponseBody
    public Json queryShowcase(HttpServletRequest request,
                              @RequestParam(value = "tableName") String tableName) {
        String moduleType = request.getParameter( "moduleType" );
        String imgId = request.getParameter( "imgId" );
        return new Json( "0000", "查询成功了",
                null != moduleType && !"".equals( moduleType ) && null != imgId && !"".equals( imgId ) ?
                        showcaseService.queryShowcase( moduleType, imgId, tableName ) :
                        showcaseService.queryShowcases( tableName ) );
    }

    @RequestMapping("updateShowcase")
    @ResponseBody
    public Json updateShowcase(HttpServletRequest request,
                               @RequestParam(value = "moduleType") String moduleType,
                               @RequestParam(value = "imgId") String imgId,
                               @RequestParam(value = "imgUrl") String imgUrl,
                               @RequestParam(value = "tableName") String tableName) {
        Showcase showcase = showcaseService.queryShowcase( moduleType, imgId, tableName ).get( 0 );
        try {
            if (imgUrl.split( "base64," ).length > 1)
                packageFun.deleteAllFilesOfDir( new File( localPath + showcase.getImgUrl().replace( officialPath, "" ) ) );

            showcase = (Showcase) packageFun.resizeObjAttribute( showcase, request );
            if (imgUrl.split( "base64," ).length > 1) {
                String route = localPath + tableName + "Img/" + moduleType + "/" + System.currentTimeMillis() + ".png";
                route = packageFun.operationFile( route, imgUrl.split( "base64," )[1] );
                showcase.setImgUrl( officialPath + route.replace( localPath, "" ) );
            }
        } catch (Exception e) {
            e.printStackTrace();
            return new Json( "0001", "数据异常" );
        }
        showcaseService.updateShowcase( showcase, tableName );
        return new Json( "0000", "成功了", showcaseService.queryShowcase( moduleType, imgId, tableName ) );
    }

    @RequestMapping("deleteShowcase")
    @ResponseBody
    public Json deleteShowcase(@RequestParam(value = "moduleType") String moduleType,
                               @RequestParam(value = "imgId") String imgId,
                               @RequestParam(value = "tableName") String tableName) {
        List<Showcase> showcases = showcaseService.queryShowcase( moduleType, imgId, tableName );
        if (showcases.size() < 0) {
            return new Json( "0001", "该模块的图片不存在" );
        }
        showcaseService.deleteShowcase( moduleType, imgId, tableName );
        String deletePath = localPath + showcases.get( 0 ).getImgUrl().replace( officialPath, "" );
        packageFun.deleteAllFilesOfDir( new File( deletePath ) );

        while ( new File( deletePath.substring( 0, deletePath.lastIndexOf( "/" ) ) ).listFiles().length <= 0 && deletePath.length() >= 10) {
            packageFun.deleteAllFilesOfDir( new File( deletePath.substring( 0, deletePath.lastIndexOf( "/" ) ) ) );
            deletePath = deletePath.substring( 0, deletePath.lastIndexOf( "/" ) );
        }
        return new Json( "0000", "成功删除了" );
    }

}
