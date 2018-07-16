package com.example.mr.yihuanhuishou.jsonbean;

/**
 * Created by Mr赵 on 2018/7/12.
 */

public class Order_Details_Bean {

    /**
     * msg : 查询成功！
     * eecDemandinfo : {"id":8,"recyclersId":30,"addrId":16,"state":"0","distribution":"0","barCode":"123456","varieties":"纸箱","unit":"5","count":20,"totalPrice":100,"comeType":"0","comeDate":1531294230000,"createDate":1531279668000,"detailAddress":"\u2018河北省\u2019石家庄裕华翟营大街凤凰绿都","longitude":23.55,"latitude":50}
     * code : 200
     * eecRecoveryOrder : {"id":5,"demandId":8,"ootherParty":null,"orderNumber":"2018071130112748098","state":"0","cancelEeason":null,"cancelDate":null,"distribution":"0","companyName":null,"carPlate":null,"phoneNumber":"17301373035","receiptDate":null,"barCode":"123456","varieties":"纸箱","unit":"5","count":20,"totalPrice":null,"paymentMethod":null,"createDate":1531279668000,"recyclersId":30,"isExit":null,"driverId":null,"endtime":null}
     */

    private String msg;
    private EecDemandinfoBean eecDemandinfo;
    private String code;
    private EecRecoveryOrderBean eecRecoveryOrder;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public EecDemandinfoBean getEecDemandinfo() {
        return eecDemandinfo;
    }

    public void setEecDemandinfo(EecDemandinfoBean eecDemandinfo) {
        this.eecDemandinfo = eecDemandinfo;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EecRecoveryOrderBean getEecRecoveryOrder() {
        return eecRecoveryOrder;
    }

    public void setEecRecoveryOrder(EecRecoveryOrderBean eecRecoveryOrder) {
        this.eecRecoveryOrder = eecRecoveryOrder;
    }

    public static class EecDemandinfoBean {
        /**
         * id : 8
         * recyclersId : 30
         * addrId : 16
         * state : 0
         * distribution : 0
         * barCode : 123456
         * varieties : 纸箱
         * unit : 5
         * count : 20
         * totalPrice : 100
         * comeType : 0
         * comeDate : 1531294230000
         * createDate : 1531279668000
         * detailAddress : ‘河北省’石家庄裕华翟营大街凤凰绿都
         * longitude : 23.55
         * latitude : 50
         */

        private int id;
        private int recyclersId;
        private int addrId;
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
        private int latitude;

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

        public int getAddrId() {
            return addrId;
        }

        public void setAddrId(int addrId) {
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

        public int getLatitude() {
            return latitude;
        }

        public void setLatitude(int latitude) {
            this.latitude = latitude;
        }
    }

    public static class EecRecoveryOrderBean {
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
        private long cancelDate;
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

        public long getCancelDate() {
            return cancelDate;
        }

        public void setCancelDate(long cancelDate) {
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
