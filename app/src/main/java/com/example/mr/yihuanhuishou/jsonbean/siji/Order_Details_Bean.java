package com.example.mr.yihuanhuishou.jsonbean.siji;

/**
 * Created by Mr赵 on 2018/7/30.
 */

public class Order_Details_Bean {


    /**
     * msg : 查询成功！
     * code : 200
     * data : {"id":40,"demandId":22,"ootherParty":4,"orderNumber":"2018073042090111541","state":"0","cancelEeason":"测试","cancelDate":1532917251000,"distribution":"0","companyName":"1","carPlate":"11","phoneNumber":"15321859174","receiptDate":1532917267000,"barCode":"11111111111111","varieties":"衣物","unit":"包","count":5,"totalPrice":5,"paymentMethod":"","createDate":1532912472000,"recyclersId":42,"isExit":null,"driverId":4,"endtime":1532917278000,"addrId":19,"comeDate":1531982877000,"paytime":1532917281000,"eecRecyclersaddr":{"id":19,"recyclersId":31,"name":"赵伟博","phoneNumber":"15301373035","province":"河北省","city":"郑州市","counry":"长安区","detailAddr":"上地七街","longitude":116.2871623,"latitude":40.0469916,"isDefault":"1","createDate":1531387169000,"state":"0"},"distance":0}
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
         * id : 40
         * demandId : 22
         * ootherParty : 4
         * orderNumber : 2018073042090111541
         * state : 0
         * cancelEeason : 测试
         * cancelDate : 1532917251000
         * distribution : 0
         * companyName : 1
         * carPlate : 11
         * phoneNumber : 15321859174
         * receiptDate : 1532917267000
         * barCode : 11111111111111
         * varieties : 衣物
         * unit : 包
         * count : 5
         * totalPrice : 5
         * paymentMethod :
         * createDate : 1532912472000
         * recyclersId : 42
         * isExit : null
         * driverId : 4
         * endtime : 1532917278000
         * addrId : 19
         * comeDate : 1531982877000
         * paytime : 1532917281000
         * eecRecyclersaddr : {"id":19,"recyclersId":31,"name":"赵伟博","phoneNumber":"15301373035","province":"河北省","city":"郑州市","counry":"长安区","detailAddr":"上地七街","longitude":116.2871623,"latitude":40.0469916,"isDefault":"1","createDate":1531387169000,"state":"0"}
         * distance : 0
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
        private String driverPay;
        private long createDate;
        private int recyclersId;
        private String isExit;
        private String imUser;
        private String companyPhone;
        private int driverId;
        private long endtime;
        private int addrId;
        private long comeDate;
        private long paytime;
        private long picktime;
        private EecRecyclersaddrBean eecRecyclersaddr;
        private int distance;

        public long getPicktime() {
            return picktime;
        }

        public void setPicktime(long picktime) {
            this.picktime = picktime;
        }

        public String getDriverPay() {
            return driverPay;
        }

        public void setDriverPay(String driverPay) {
            this.driverPay = driverPay;
        }

        public String getCompanyPhone() {
            return companyPhone;
        }

        public void setCompanyPhone(String companyPhone) {
            this.companyPhone = companyPhone;
        }

        public String getImUser() {
            return imUser;
        }

        public void setImUser(String imUser) {
            this.imUser = imUser;
        }

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

        public int getAddrId() {
            return addrId;
        }

        public void setAddrId(int addrId) {
            this.addrId = addrId;
        }

        public long getComeDate() {
            return comeDate;
        }

        public void setComeDate(long comeDate) {
            this.comeDate = comeDate;
        }

        public long getPaytime() {
            return paytime;
        }

        public void setPaytime(long paytime) {
            this.paytime = paytime;
        }

        public EecRecyclersaddrBean getEecRecyclersaddr() {
            return eecRecyclersaddr;
        }

        public void setEecRecyclersaddr(EecRecyclersaddrBean eecRecyclersaddr) {
            this.eecRecyclersaddr = eecRecyclersaddr;
        }

        public int getDistance() {
            return distance;
        }

        public void setDistance(int distance) {
            this.distance = distance;
        }

        public static class EecRecyclersaddrBean {
            /**
             * id : 19
             * recyclersId : 31
             * name : 赵伟博
             * phoneNumber : 15301373035
             * province : 河北省
             * city : 郑州市
             * counry : 长安区
             * detailAddr : 上地七街
             * longitude : 116.2871623
             * latitude : 40.0469916
             * isDefault : 1
             * createDate : 1531387169000
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
}
