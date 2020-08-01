package com.example.demo.entity.student;

public class Showcase {
    private String moduleType;
    private String moduleName;
    private String imgId;
    private String imgName;
    private String imgUrl;
    private String imgDesc;
    private String showAndHide = "0"; //1.显示 0.隐藏
    private String updateTime;

    public Showcase() {
    }

    public Showcase(String moduleType, String moduleName, String imgId, String imgName, String imgUrl, String imgDesc, String showAndHide) {
        this.moduleType = moduleType;
        this.moduleName = moduleName;
        this.imgId = imgId;
        this.imgName = imgName;
        this.imgUrl = imgUrl;
        this.imgDesc = imgDesc;
        this.showAndHide = showAndHide;
    }


    public String getShowAndHide() {
        return showAndHide;
    }

    public void setShowAndHide(String showAndHide) {
        this.showAndHide = showAndHide;
    }

    public String getModuleType() {
        return moduleType;
    }

    public void setModuleType(String moduleType) {
        this.moduleType = moduleType;
    }

    public String getModuleName() {
        return moduleName;
    }

    public void setModuleName(String moduleName) {
        this.moduleName = moduleName;
    }

    public String getImgId() {
        return imgId;
    }

    public void setImgId(String imgId) {
        this.imgId = imgId;
    }

    public String getImgName() {
        return imgName;
    }

    public void setImgName(String imgName) {
        this.imgName = imgName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getImgDesc() {
        return imgDesc;
    }

    public void setImgDesc(String imgDesc) {
        this.imgDesc = imgDesc;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
