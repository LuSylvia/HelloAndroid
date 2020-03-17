package com.sylvia.listviewpractice;

public class Student {

    private String chineseName;//中文姓名
    private String englishName;//英文名
    private int id;
    private String info;//个人信息
    private int imgId;//图像id


    public Student(String ChineseName, int id, String info, int imgId,String EnglishName){
        this.chineseName = ChineseName;
        this.id=id;
        this.info=info;
        this.imgId=imgId;
        this.englishName =EnglishName;
    }

    public String getEnglishName() {
        return englishName;
    }

    public void setEnglishName(String englishName) {
        this.englishName = englishName;
    }



    public String getChineseName() {
        return chineseName;
    }

    public void setChineseName(String chineseName) {
        this.chineseName = chineseName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }


    public int getImgId() {
        return imgId;
    }

    public void setImgId(int imgId) {
        this.imgId = imgId;
    }

}
