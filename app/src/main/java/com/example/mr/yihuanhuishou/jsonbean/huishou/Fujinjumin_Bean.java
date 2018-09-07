package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/24.
 */

public class Fujinjumin_Bean {


    /**
     * msg : 查询成功
     * code : 200
     * data : [{"name":"茶派","village":"中兴大厦","floor":"3","door":"306","longitude":116.332468,"latitude":39.973375,"num":4},{"name":"11","village":"次渠嘉园东里三区","floor":"10","door":"102","longitude":116.287544,"latitude":40.04689,"num":2}]
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
         * name : 茶派
         * village : 中兴大厦
         * floor : 3
         * door : 306
         * longitude : 116.332468
         * latitude : 39.973375
         * num : 4
         */

        private String name;
        private String village;
        private String floor;
        private String door;
        private double longitude;
        private double latitude;
        private int num;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
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

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }
    }
}
