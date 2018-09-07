package com.example.mr.yihuanhuishou.jsonbean.huishou;

/**
 * Created by Mr赵 on 2018/8/14.
 */

public class Send_xuqiu_Bean {

    /**
     * msg : 操作成功！
     * code : 1
     * data : {"id":65,"recyclersId":50,"addrId":null,"state":"0","distribution":"0","barCode":"6903244984102","varieties":"塑料瓶","unit":"个","count":10,"totalPrice":20,"comeType":"1","comeDate":1534243228572,"createDate":1534243228572,"detailAddress":"北京市海淀区上地街道中关村软件园","longitude":40.046168,"latitude":116.286705}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * id : 65
         * recyclersId : 50
         * addrId : null
         * state : 0
         * distribution : 0
         * barCode : 6903244984102
         * varieties : 塑料瓶
         * unit : 个
         * count : 10
         * totalPrice : 20
         * comeType : 1
         * comeDate : 1534243228572
         * createDate : 1534243228572
         * detailAddress : 北京市海淀区上地街道中关村软件园
         * longitude : 40.046168
         * latitude : 116.286705
         */

        private int id;
        private int recyclersId;
        private Object addrId;
        private String state;
        private String distribution;
        private String barCode;
        private String varieties;
        private String unit;
        private int count;
        private int totalPrice;
        private String comeType;
        private long comeDate;
        private long createDate;
        private String detailAddress;
        private double longitude;
        private double latitude;

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

        public Object getAddrId() {
            return addrId;
        }

        public void setAddrId(Object addrId) {
            this.addrId = addrId;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getDistribution() {
            return distribution;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }

        public String getBarCode() {
            return barCode;
        }

        public void setBarCode(String barCode) {
            this.barCode = barCode;
        }

        public String getVarieties() {
            return varieties;
        }

        public void setVarieties(String varieties) {
            this.varieties = varieties;
        }

        public String getUnit() {
            return unit;
        }

        public void setUnit(String unit) {
            this.unit = unit;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(int totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getComeType() {
            return comeType;
        }

        public void setComeType(String comeType) {
            this.comeType = comeType;
        }

        public long getComeDate() {
            return comeDate;
        }

        public void setComeDate(long comeDate) {
            this.comeDate = comeDate;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getDetailAddress() {
            return detailAddress;
        }

        public void setDetailAddress(String detailAddress) {
            this.detailAddress = detailAddress;
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
    }
}
