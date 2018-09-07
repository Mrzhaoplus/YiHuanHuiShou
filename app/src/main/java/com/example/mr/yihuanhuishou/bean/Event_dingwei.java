package com.example.mr.yihuanhuishou.bean;

/**
 * Created by Mr赵 on 2018/5/3.
 */

public class Event_dingwei {
    private String city;
    private  int flag;


    public Event_dingwei(String city, int flag) {
        this.city = city;
        this.flag = flag;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }
}
