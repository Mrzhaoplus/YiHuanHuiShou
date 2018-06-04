package com.example.mr.yihuanhuishou.bean;

import java.io.Serializable;

/**
 * Created by power on 2018/5/21.
 */

public class CompanyBean implements Serializable {
    private double latitude;
    private double longitude;
    private String type;
    private DataBean dataBean;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public static class DataBean implements Serializable{
        private String title;
        private String content;
        private String name;
        private String phone;
        private String address;

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone() {
            return phone;
        }

        public void setPhone(String phone) {
            this.phone = phone;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }
    }

    public CompanyBean(double latitude, double longitude, String type, DataBean dataBean) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.dataBean = dataBean;
    }

    public CompanyBean(double latitude, double longitude, String type) {
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
    }
}
