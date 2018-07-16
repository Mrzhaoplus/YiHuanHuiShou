package com.example.mr.yihuanhuishou.jsonbean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/12.
 */

public class Order_Daijiedan_Bean {

    /**
     * msg : 查询成功！
     * code : 200
     * data : [{"id":5,"demandId":8,"ootherParty":null,"orderNumber":"2018071130112748098","state":"0","cancelEeason":null,"cancelDate":null,"distribution":"0","companyName":null,"carPlate":null,"phoneNumber":"17301373035","receiptDate":null,"barCode":"123456","varieties":"纸箱","unit":"5","count":20,"totalPrice":null,"paymentMethod":null,"createDate":1531279668000,"recyclersId":30,"isExit":null,"driverId":null,"endtime":null},{"id":6,"demandId":9,"ootherParty":null,"orderNumber":"2018071130152732316","state":"0","cancelEeason":null,"cancelDate":null,"distribution":"0","companyName":null,"carPlate":null,"phoneNumber":"17301373035","receiptDate":null,"barCode":"6903244984102","varieties":"金属","unit":"个","count":2,"totalPrice":null,"paymentMethod":null,"createDate":1531294052000,"recyclersId":30,"isExit":null,"driverId":null,"endtime":null},{"id":7,"demandId":10,"ootherParty":null,"orderNumber":"2018071130152812527","state":"0","cancelEeason":null,"cancelDate":null,"distribution":"0","companyName":null,"carPlate":null,"phoneNumber":"17301373035","receiptDate":null,"barCode":"6903244984102","varieties":"金属","unit":"个","count":2,"totalPrice":null,"paymentMethod":null,"createDate":1531294093000,"recyclersId":30,"isExit":null,"driverId":null,"endtime":null}]
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
         * id : 5
         * demandId : 8
         * ootherParty : null
         * orderNumber : 2018071130112748098
         * state : 0
         * cancelEeason : null
         * cancelDate : null
         * distribution : 0
         * companyName : null
         * carPlate : null
         * phoneNumber : 17301373035
         * receiptDate : null
         * barCode : 123456
         * varieties : 纸箱
         * unit : 5
         * count : 20
         * totalPrice : null
         * paymentMethod : null
         * createDate : 1531279668000
         * recyclersId : 30
         * isExit : null
         * driverId : null
         * endtime : null
         */

        private int id;
        private int demandId;
        private int ootherParty;
        private String orderNumber;
        private String state;
        private String cancelEeason;
        private String cancelDate;
        private String distribution;
        private String companyName;
        private String carPlate;
        private String phoneNumber;
        private String receiptDate;
        private String barCode;
        private String varieties;
        private String unit;
        private int count;
        private String totalPrice;
        private String paymentMethod;
        private long createDate;
        private int recyclersId;
        private String isExit;
        private int driverId;
        private String endtime;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getDemandId() {
            return demandId;
        }

        public void setDemandId(int demandId) {
            this.demandId = demandId;
        }

        public int getOotherParty() {
            return ootherParty;
        }

        public void setOotherParty(int ootherParty) {
            this.ootherParty = ootherParty;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public String getCancelEeason() {
            return cancelEeason;
        }

        public void setCancelEeason(String cancelEeason) {
            this.cancelEeason = cancelEeason;
        }

        public String getCancelDate() {
            return cancelDate;
        }

        public void setCancelDate(String cancelDate) {
            this.cancelDate = cancelDate;
        }

        public String getDistribution() {
            return distribution;
        }

        public void setDistribution(String distribution) {
            this.distribution = distribution;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public String getCarPlate() {
            return carPlate;
        }

        public void setCarPlate(String carPlate) {
            this.carPlate = carPlate;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(String receiptDate) {
            this.receiptDate = receiptDate;
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

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getRecyclersId() {
            return recyclersId;
        }

        public void setRecyclersId(int recyclersId) {
            this.recyclersId = recyclersId;
        }

        public String getIsExit() {
            return isExit;
        }

        public void setIsExit(String isExit) {
            this.isExit = isExit;
        }

        public int getDriverId() {
            return driverId;
        }

        public void setDriverId(int driverId) {
            this.driverId = driverId;
        }

        public String getEndtime() {
            return endtime;
        }

        public void setEndtime(String endtime) {
            this.endtime = endtime;
        }
    }
}
