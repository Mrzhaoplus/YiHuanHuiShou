package com.example.mr.yihuanhuishou.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/5/21.
 */

public class LatLngBean implements Serializable {
    private double latitude;
    private double longitude;
    private String phone;
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public LatLngBean(double latitude, double longitude,String phone,String type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.phone = phone;
        this.type = type;
    }
}
