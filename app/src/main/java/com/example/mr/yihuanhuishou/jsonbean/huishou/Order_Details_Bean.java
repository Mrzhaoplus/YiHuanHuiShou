package com.example.mr.yihuanhuishou.jsonbean.huishou;

/**
 * Created by Mr赵 on 2018/7/12.
 */

public class Order_Details_Bean {


    /**
     * msg : 查询成功！
     * eecDemandinfo : {"id":132,"recyclersId":50,"addrId":null,"state":"0","distribution":"0","barCode":"69032123123123123123","varieties":"地址","unit":"个","count":66,"totalPrice":77,"comeType":"1","comeDate":1534753664000,"createDate":1534753664000,"detailAddress":"北京市海淀区上地街道中关村软件园","longitude":116.286,"latitude":40.045}
     * code : 200
     * eecRecoveryOrder : {"id":243,"demandId":132,"ootherParty":null,"orderNumber":"2018082050162743822","state":"3","cancelEeason":null,"cancelDate":null,"distribution":"0","companyName":null,"carPlate":null,"phoneNumber":"17301373035","receiptDate":1534845734000,"barCode":"69032123123123123123","varieties":"衣服","unit":"件","count":1,"totalPrice":0.01,"paymentMethod":"微信支付","createDate":1534753664000,"recyclersId":50,"isExit":null,"driverId":18,"endtime":null,"paytime":1534857151000,"status":null,"driverPay":"1","recyclersName":null,"driverName":null,"address":null,"exit":null}
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
         * id : 132
         * recyclersId : 50
         * addrId : null
         * state : 0
         * distribution : 0
         * barCode : 69032123123123123123
         * varieties : 地址
         * unit : 个
         * count : 66
         * totalPrice : 77
         * comeType : 1
         * comeDate : 1534753664000
         * createDate : 1534753664000
         * detailAddress : 北京市海淀区上地街道中关村软件园
         * longitude : 116.286
         * latitude : 40.045
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
        private double totalPrice;
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

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
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

    public static class EecRecoveryOrderBean {
        /**
         * id : 243
         * demandId : 132
         * ootherParty : null
         * orderNumber : 2018082050162743822
         * state : 3
         * cancelEeason : null
         * cancelDate : null
         * distribution : 0
         * companyName : null
         * carPlate : null
         * phoneNumber : 17301373035
         * receiptDate : 1534845734000
         * barCode : 69032123123123123123
         * varieties : 衣服
         * unit : 件
         * count : 1
         * totalPrice : 0.01
         * paymentMethod : 微信支付
         * createDate : 1534753664000
         * recyclersId : 50
         * isExit : null
         * driverId : 18
         * endtime : null
         * paytime : 1534857151000
         * status : null
         * driverPay : 1
         * recyclersName : null
         * driverName : null
         * address : null
         * exit : null
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
        private long receiptDate;
        private String barCode;
        private String varieties;
        private String unit;
        private int count;
        private double totalPrice;
        private String paymentMethod;
        private long createDate;
        private int recyclersId;
        private String isExit;
        private int driverId;
        private long endtime;
        private long paytime;
        private String status;
        private String driverPay;
        private String recyclersName;
        private String driverName;
        private String address;
        private String exit;

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

        public long getReceiptDate() {
            return receiptDate;
        }

        public void setReceiptDate(long receiptDate) {
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

        public double getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(double totalPrice) {
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

        public long getEndtime() {
            return endtime;
        }

        public void setEndtime(long endtime) {
            this.endtime = endtime;
        }

        public long getPaytime() {
            return paytime;
        }

        public void setPaytime(long paytime) {
            this.paytime = paytime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getDriverPay() {
            return driverPay;
        }

        public void setDriverPay(String driverPay) {
            this.driverPay = driverPay;
        }

        public String getRecyclersName() {
            return recyclersName;
        }

        public void setRecyclersName(String recyclersName) {
            this.recyclersName = recyclersName;
        }

        public String getDriverName() {
            return driverName;
        }

        public void setDriverName(String driverName) {
            this.driverName = driverName;
        }

        public String getAddress() {
            return address;
        }

        public void setAddress(String address) {
            this.address = address;
        }

        public String getExit() {
            return exit;
        }

        public void setExit(String exit) {
            this.exit = exit;
        }
    }
}
