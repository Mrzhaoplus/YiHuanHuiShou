package com.example.mr.yihuanhuishou.bean;

/**
 * Created by Mr赵 on 2018/6/20.
 */

public class Address_bean {
    private String name;
    private String tel;
    private String address;

    public Address_bean(String name, String tel, String address) {
        this.name = name;
        this.tel = tel;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
