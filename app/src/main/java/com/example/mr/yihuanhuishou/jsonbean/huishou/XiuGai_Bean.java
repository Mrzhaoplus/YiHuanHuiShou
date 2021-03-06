package com.example.mr.yihuanhuishou.jsonbean.huishou;

/**
 * Created by Mr赵 on 2018/7/9.
 */

public class XiuGai_Bean {

    /**
     * msg : 修改成功！
     * code : 200
     * data : {"id":30,"photoPath":null,"nickname":"ZHAOWEIBO","age":20,"sex":"0","idCard":"130124199607070950","phoneNumber":"17301373035","password":"57958FEB77368A646CE116644E541CD6F7396713A050BD628D84E9B9","balance":0,"currency":0,"totalCurrency":0,"wechat":null,"registerDate":1531104023000,"token":"eTp3F466T1LhfM0oXA8bPQ=="}
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
         * id : 30
         * photoPath : null
         * nickname : ZHAOWEIBO
         * age : 20
         * sex : 0
         * idCard : 130124199607070950
         * phoneNumber : 17301373035
         * password : 57958FEB77368A646CE116644E541CD6F7396713A050BD628D84E9B9
         * balance : 0
         * currency : 0
         * totalCurrency : 0
         * wechat : null
         * registerDate : 1531104023000
         * token : eTp3F466T1LhfM0oXA8bPQ==
         */

        private int id;
        private String photoPath;
        private String nickname;
        private int age;
        private String sex;
        private String idCard;
        private String phoneNumber;
        private String password;
        private int balance;
        private int currency;
        private int totalCurrency;
        private String wechat;
        private String aliPay;
        private String imUser;
        private String imPassword;
        private long registerDate;
        private String token;

        public String getAliPay() {
            return aliPay;
        }

        public void setAliPay(String aliPay) {
            this.aliPay = aliPay;
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

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getPhotoPath() {
            return photoPath;
        }

        public void setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
        }

        public String getNickname() {
            return nickname;
        }

        public void setNickname(String nickname) {
            this.nickname = nickname;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getSex() {
            return sex;
        }

        public void setSex(String sex) {
            this.sex = sex;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getCurrency() {
            return currency;
        }

        public void setCurrency(int currency) {
            this.currency = currency;
        }

        public int getTotalCurrency() {
            return totalCurrency;
        }

        public void setTotalCurrency(int totalCurrency) {
            this.totalCurrency = totalCurrency;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public long getRegisterDate() {
            return registerDate;
        }

        public void setRegisterDate(long registerDate) {
            this.registerDate = registerDate;
        }

        public String getToken() {
            return token;
        }

        public void setToken(String token) {
            this.token = token;
        }
    }
}
