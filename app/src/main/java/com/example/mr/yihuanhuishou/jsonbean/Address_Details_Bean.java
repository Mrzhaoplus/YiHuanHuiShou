package com.example.mr.yihuanhuishou.jsonbean;

/**
 * Created by Mr赵 on 2018/7/10.
 */

public class Address_Details_Bean {

    /**
     * msg : 查询成功！
     * code : 200
     * data : {"id":16,"recyclersId":30,"name":"赵伟","phoneNumber":"17301373035","province":"\u2018河北省\u2019","city":"石家庄","counry":"裕华区","detailAddr":"翟营大街凤凰绿都","longitude":25.3,"latitude":23.6,"isDefault":null,"createDate":1531202269000,"state":"0"}
     */

    private String msg;
    private String code;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 16
         * recyclersId : 30
         * name : 赵伟
         * phoneNumber : 17301373035
         * province : ‘河北省’
         * city : 石家庄
         * counry : 裕华区
         * detailAddr : 翟营大街凤凰绿都
         * longitude : 25.3
         * latitude : 23.6
         * isDefault : null
         * createDate : 1531202269000
         * state : 0
         */

        private int id;
        private int recyclersId;
        private String name;
        private String phoneNumber;
        private String province;
        private String city;
        private String counry;
        private String detailAddr;
        private double longitude;
        private double latitude;
        private String isDefault;
        private long createDate;
        private String state;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRecyclersId() {
            return recyclersId;
        }

        public void setRecyclersId(int recyclersId) {
            this.recyclersId = recyclersId;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
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

        public String getDetailAddr() {
            return detailAddr;
        }

        public void setDetailAddr(String detailAddr) {
            this.detailAddr = detailAddr;
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

        public String getIsDefault() {
            return isDefault;
        }

        public void setIsDefault(String isDefault) {
            this.isDefault = isDefault;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }
    }
}
