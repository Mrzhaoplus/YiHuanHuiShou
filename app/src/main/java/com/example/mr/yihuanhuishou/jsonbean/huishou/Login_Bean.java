package com.example.mr.yihuanhuishou.jsonbean.huishou;

/**
 * Created by Mr赵 on 2018/7/9.
 */

public class Login_Bean {


    /**
     * msg : 登录成功！
     * code : 200
     * data : {"id":30,"photoPath":"http://ech.oss-cn-beijing.aliyuncs.com/0c82716b82244cf197132616ddaf74b1","nickname":"赵伟博","age":50,"sex":"1","idCard":"130124199607070950","phoneNumber":"17301373035","password":"A5DB8F1DC57AAEAFC8B56FAB7C0A609E9BA461D560AE33B38931CA67","balance":1791.05,"currency":160,"totalCurrency":0,"deposit":"0","wechat":null,"aliPay":null,"registerDate":1533277458000,"token":"5a1wnre5xnXYFAp0DOUf6g==","longitude":null,"latitude":null}
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
         * photoPath : http://ech.oss-cn-beijing.aliyuncs.com/0c82716b82244cf197132616ddaf74b1
         * nickname : 赵伟博
         * age : 50
         * sex : 1
         * idCard : 130124199607070950
         * phoneNumber : 17301373035
         * password : A5DB8F1DC57AAEAFC8B56FAB7C0A609E9BA461D560AE33B38931CA67
         * balance : 1791.05
         * currency : 160
         * totalCurrency : 0
         * deposit : 0
         * wechat : null
         * aliPay : null
         * registerDate : 1533277458000
         * token : 5a1wnre5xnXYFAp0DOUf6g==
         * longitude : null
         * latitude : null
         */

        private int id;
        private String photoPath;
        private String nickname;
        private int age;
        private String sex;
        private String idCard;
        private String phoneNumber;
        private String password;
        private double balance;
        private float currency;
        private float totalCurrency;
        private String deposit;
        private String imUser;
        private String imPassword;
        private String wechat;
        private String aliPay;
        private long registerDate;
        private String token;
        private Object longitude;
        private Object latitude;

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

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public float getCurrency() {
            return currency;
        }

        public void setCurrency(float currency) {
            this.currency = currency;
        }

        public float getTotalCurrency() {
            return totalCurrency;
        }

        public void setTotalCurrency(float totalCurrency) {
            this.totalCurrency = totalCurrency;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public String getWechat() {
            return wechat;
        }

        public void setWechat(String wechat) {
            this.wechat = wechat;
        }

        public String getAliPay() {
            return aliPay;
        }

        public void setAliPay(String aliPay) {
            this.aliPay = aliPay;
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

        public Object getLongitude() {
            return longitude;
        }

        public void setLongitude(Object longitude) {
            this.longitude = longitude;
        }

        public Object getLatitude() {
            return latitude;
        }

        public void setLatitude(Object latitude) {
            this.latitude = latitude;
        }
    }
}
