package com.example.sqlitedemo;

public class User {
    /*
    *name 姓名
    * email 邮箱
    * phone 手机号（唯一）
     */
    private String name;
    private String email;
    private String phone;

    //默认构造函数
    public User(){

    }

    //带参构造函数
    public User(String name,String email,String phone){
        this.name=name;
        this.email=email;
        this.phone=phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
