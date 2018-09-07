package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/24.
 */

public class Fujin_jumin_Details_Bean {

    /**
     * msg : 查询成功
     * code : 200
     * data : [{"id":28,"rid":116,"name":"思聪弟弟","phone_number":"11011001100","province":"北京市","city":"北京市","counry":"海淀区","village":"中央公园","floor":"9","door":"2101","detail_addr":"北京市海淀区上地街道软件园四号路中关村软件园(软件园三号路)","longitude":116.2868427,"latitude":40.046665,"is_default":"0","create_date":1531477344000,"is_exit":false,"varieties":"鞋类","orderId":28}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 28
         * rid : 116
         * name : 思聪弟弟
         * phone_number : 11011001100
         * province : 北京市
         * city : 北京市
         * counry : 海淀区
         * village : 中央公园
         * floor : 9
         * door : 2101
         * detail_addr : 北京市海淀区上地街道软件园四号路中关村软件园(软件园三号路)
         * longitude : 116.2868427
         * latitude : 40.046665
         * is_default : 0
         * create_date : 1531477344000
         * is_exit : false
         * varieties : 鞋类
         * orderId : 28
         */

        private int id;
        private int rid;
        private String name;
        private String phone_number;
        private String province;
        private String city;
        private String counry;
        private String village;
        private String floor;
        private String door;
        private String detail_addr;
        private double longitude;
        private double latitude;
        private String is_default;
        private long create_date;
        private boolean is_exit;
        private String varieties;
        private int orderId;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhone_number() {
            return phone_number;
        }

        public void setPhone_number(String phone_number) {
            this.phone_number = phone_number;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getCounry() {
            return counry;
        }

        public void setCounry(String counry) {
            this.counry = counry;
        }

        public String getVillage() {
            return village;
        }

        public void setVillage(String village) {
            this.village = village;
        }

        public String getFloor() {
            return floor;
        }

        public void setFloor(String floor) {
            this.floor = floor;
        }

        public String getDoor() {
            return door;
        }

        public void setDoor(String door) {
            this.door = door;
        }

        public String getDetail_addr() {
            return detail_addr;
        }

        public void setDetail_addr(String detail_addr) {
            this.detail_addr = detail_addr;
        }

        public double getLongitude() {
            return longitude;
        }

        public void setLongitude(double longitude) {
            this.longitude = longitude;
        }

        public double getLatitude() {
            return latitude;
        }

        public void setLatitude(double latitude) {
            this.latitude = latitude;
        }

        public String getIs_default() {
            return is_default;
        }

        public void setIs_default(String is_default) {
            this.is_default = is_default;
        }

        public long getCreate_date() {
            return create_date;
        }

        public void setCreate_date(long create_date) {
            this.create_date = create_date;
        }

        public boolean isIs_exit() {
            return is_exit;
        }

        public void setIs_exit(boolean is_exit) {
            this.is_exit = is_exit;
        }

        public String getVarieties() {
            return varieties;
        }

        public void setVarieties(String varieties) {
            this.varieties = varieties;
        }

        public int getOrderId() {
            return orderId;
        }

        public void setOrderId(int orderId) {
            this.orderId = orderId;
        }
    }
}
