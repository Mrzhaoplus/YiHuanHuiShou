package com.example.mr.yihuanhuishou.jsonbean.huishou;

/**
 * Created by Mr赵 on 2018/7/16.
 */

public class Location_Bean {
    private int status;
    private int count;
    private String info;
    private Geocodes geocodes;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public Geocodes getGeocodes() {
        return geocodes;
    }

    public void setGeocodes(Geocodes geocodes) {
        this.geocodes = geocodes;
    }
    public static class Geocodes{
        private  String location;

        public String getLocation() {
            return location;
        }

        public void setLocation(String location) {
            this.location = location;
        }
    }
}
