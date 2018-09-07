package com.example.mr.yihuanhuishou.jsonbean.siji;

/**
 * Created by Mr赵 on 2018/7/26.
 */

public class Driv_Xiugai_Bean {


    /**
     * msg : 修改成功！
     * code : 200
     * data : {"id":4,"driverNicekname":"bo","driverPhone":"17301373035","driverPassword":"76AD67469EEA66BBE1866B30339F6B21A64F70EF2DE9DD7623C3B824","driverIdentityCard":"130124199607070950","driverSex":false,"driverAge":15,"driverImage":"","driverRegistetTime":1532419028000,"driverBanlance":99898.9,"imUser":"driver4","imPassword":"123456","payid":"2088422229833564","token":"DoJfTiTR/bH9CRYiis6TQ==","driverPlateNumber":null}
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
         * id : 4
         * driverNicekname : bo
         * driverPhone : 17301373035
         * driverPassword : 76AD67469EEA66BBE1866B30339F6B21A64F70EF2DE9DD7623C3B824
         * driverIdentityCard : 130124199607070950
         * driverSex : false
         * driverAge : 15
         * driverImage :
         * driverRegistetTime : 1532419028000
         * driverBanlance : 99898.9
         * imUser : driver4
         * imPassword : 123456
         * payid : 2088422229833564
         * token : DoJfTiTR/bH9CRYiis6TQ==
         * driverPlateNumber : null
         */

        private int id;
        private String driverNicekname;
        private String driverPhone;
        private String driverPassword;
        private String driverIdentityCard;
        private boolean driverSex;
        private int driverAge;
        private String driverImage;
        private long driverRegistetTime;
        private double driverBanlance;
        private String imUser;
        private String imPassword;
        private String payid;
        private String token;
        private Object driverPlateNumber;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getDriverNicekname() {
            return driverNicekname;
        }

        public void setDriverNicekname(String driverNicekname) {
            this.driverNicekname = driverNicekname;
        }

        public String getDriverPhone() {
            return driverPhone;
        }

        public void setDriverPhone(String driverPhone) {
            this.driverPhone = driverPhone;
        }

        public String getDriverPassword() {
            return driverPassword;
        }

        public void setDriverPassword(String driverPassword) {
            this.driverPassword = driverPassword;
        }

        public String getDriverIdentityCard() {
            return driverIdentityCard;
        }

        public void setDriverIdentityCard(String driverIdentityCard) {
            this.driverIdentityCard = driverIdentityCard;
        }

        public boolean getDriverSex() {
            return driverSex;
        }

        public void setDriverSex(boolean driverSex) {
            this.driverSex = driverSex;
        }

        public int getDriverAge() {
            return driverAge;
        }

        public void setDriverAge(int driverAge) {
            this.driverAge = driverAge;
        }

        public String getDriverImage() {
            return driverImage;
        }

        public void setDriverImage(String driverImage) {
            this.driverImage = driverImage;
        }

        public long getDriverRegistetTime() {
            return driverRegistetTime;
        }

        public void setDriverRegistetTime(long driverRegistetTime) {
            this.driverRegistetTime = driverRegistetTime;
        }

        public double getDriverBanlance() {
            return driverBanlance;
        }

        public void setDriverBanlance(double driverBanlance) {
            this.driverBanlance = driverBanlance;
        }

        public String getImUser() {
            return imUser;
        }

        public void setImUser(String imUser) {
            this.imUser = imUser;
        }

        public String getImPassword() {
            return imPassword;
        }

        public void setImPassword(String imPassword) {
            this.imPassword = imPassword;
        }

        public String getPayid() {
            return payid;
        }

        public void setPayid(String payid) {
            this.payid = payid;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }

        public Object getDriverPlateNumber() {
            return driverPlateNumber;
        }

        public void setDriverPlateNumber(Object driverPlateNumber) {
            this.driverPlateNumber = driverPlateNumber;
        }
    }
}
