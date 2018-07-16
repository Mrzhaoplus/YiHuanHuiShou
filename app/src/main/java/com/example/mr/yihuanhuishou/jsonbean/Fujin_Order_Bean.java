package com.example.mr.yihuanhuishou.jsonbean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/13.
 */

public class Fujin_Order_Bean {

    /**
     * msg : 查询成功！
     * code : 200
     * data : [{"id":27,"orderNumber":"20180713116183901586","wid":40,"recyclearsId":null,"count":1,"unitPrice":0,"totalMoney":0,"state":"0","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":null,"assess":null,"grade":null,"giveCurrency":10,"createDate":1531478342000,"rid":116,"eecWasteinfo":{"id":40,"rid":116,"addrId":28,"varieties":"塑料瓶","unit":"kg","count":1,"detailInfo":"dfdg","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531478338000,"residentaddr":null,"list":null,"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null},"comeType":0,"comeData":1531440000000,"assessDate":null},{"id":26,"orderNumber":"20180713116183746216","wid":39,"recyclearsId":null,"count":123,"unitPrice":0,"totalMoney":0,"state":"0","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":null,"assess":null,"grade":null,"giveCurrency":1845,"createDate":1531478266000,"rid":116,"eecWasteinfo":{"id":39,"rid":116,"addrId":28,"varieties":"手机数码","unit":"kg","count":123,"detailInfo":"qwe","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531477981000,"residentaddr":null,"list":null,"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null},"comeType":0,"comeData":1531440000000,"assessDate":null},{"id":25,"orderNumber":"20180713116183016500","wid":38,"recyclearsId":null,"count":11,"unitPrice":0,"totalMoney":0,"state":"0","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":null,"assess":null,"grade":null,"giveCurrency":110,"createDate":1531477816000,"rid":116,"eecWasteinfo":{"id":38,"rid":116,"addrId":28,"varieties":"塑料瓶","unit":"件","count":11,"detailInfo":"561651569","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531477802000,"residentaddr":null,"list":null,"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null},"comeType":0,"comeData":1562976180000,"assessDate":null},{"id":24,"orderNumber":"20180713116182558378","wid":37,"recyclearsId":null,"count":15,"unitPrice":0,"totalMoney":0,"state":"0","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":null,"assess":null,"grade":null,"giveCurrency":300,"createDate":1531477558000,"rid":116,"eecWasteinfo":{"id":37,"rid":116,"addrId":28,"varieties":"鞋类","unit":"个","count":15,"detailInfo":"this is a apple","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531477386000,"residentaddr":null,"list":null,"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null},"comeType":0,"comeData":1531440060000,"assessDate":null},{"id":22,"orderNumber":"20180713115111729438","wid":36,"recyclearsId":null,"count":111111111,"unitPrice":0,"totalMoney":0,"state":"0","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":null,"assess":null,"grade":null,"giveCurrency":1111111110,"createDate":1531451849000,"rid":115,"eecWasteinfo":{"id":36,"rid":115,"addrId":24,"varieties":"塑料瓶","unit":"g","count":111111111,"detailInfo":"0000","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531451835000,"residentaddr":null,"list":null,"wasteinfoAddr":null,"longitude":116.28684271918402,"latitude":40.0466650390625,"orderNumber":null},"comeType":0,"comeData":1531440000000,"assessDate":null}]
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
         * id : 27
         * orderNumber : 20180713116183901586
         * wid : 40
         * recyclearsId : null
         * count : 1
         * unitPrice : 0
         * totalMoney : 0
         * state : 0
         * cancelEeason : null
         * cancelDescribe : null
         * cancelDate : null
         * paymentMethod : null
         * assess : null
         * grade : null
         * giveCurrency : 10
         * createDate : 1531478342000
         * rid : 116
         * eecWasteinfo : {"id":40,"rid":116,"addrId":28,"varieties":"塑料瓶","unit":"kg","count":1,"detailInfo":"dfdg","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531478338000,"residentaddr":null,"list":null,"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null}
         * comeType : 0
         * comeData : 1531440000000
         * assessDate : null
         */

        private int id;
        private String orderNumber;
        private int wid;
        private int recyclearsId;
        private int count;
        private int unitPrice;
        private int totalMoney;
        private String state;
        private String cancelEeason;
        private String cancelDescribe;
        private String cancelDate;
        private String paymentMethod;
        private String assess;
        private Object grade;
        private int giveCurrency;
        private long createDate;
        private int rid;
        private EecWasteinfoBean eecWasteinfo;
        private int comeType;
        private long comeData;
        private String assessDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(String orderNumber) {
            this.orderNumber = orderNumber;
        }

        public int getWid() {
            return wid;
        }

        public void setWid(int wid) {
            this.wid = wid;
        }

        public int getRecyclearsId() {
            return recyclearsId;
        }

        public void setRecyclearsId(int recyclearsId) {
            this.recyclearsId = recyclearsId;
        }

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }

        public int getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(int totalMoney) {
            this.totalMoney = totalMoney;
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

        public String getCancelDescribe() {
            return cancelDescribe;
        }

        public void setCancelDescribe(String cancelDescribe) {
            this.cancelDescribe = cancelDescribe;
        }

        public String getCancelDate() {
            return cancelDate;
        }

        public void setCancelDate(String cancelDate) {
            this.cancelDate = cancelDate;
        }

        public String getPaymentMethod() {
            return paymentMethod;
        }

        public void setPaymentMethod(String paymentMethod) {
            this.paymentMethod = paymentMethod;
        }

        public String getAssess() {
            return assess;
        }

        public void setAssess(String assess) {
            this.assess = assess;
        }

        public Object getGrade() {
            return grade;
        }

        public void setGrade(Object grade) {
            this.grade = grade;
        }

        public int getGiveCurrency() {
            return giveCurrency;
        }

        public void setGiveCurrency(int giveCurrency) {
            this.giveCurrency = giveCurrency;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public int getRid() {
            return rid;
        }

        public void setRid(int rid) {
            this.rid = rid;
        }

        public EecWasteinfoBean getEecWasteinfo() {
            return eecWasteinfo;
        }

        public void setEecWasteinfo(EecWasteinfoBean eecWasteinfo) {
            this.eecWasteinfo = eecWasteinfo;
        }

        public int getComeType() {
            return comeType;
        }

        public void setComeType(int comeType) {
            this.comeType = comeType;
        }

        public long getComeData() {
            return comeData;
        }

        public void setComeData(long comeData) {
            this.comeData = comeData;
        }

        public String getAssessDate() {
            return assessDate;
        }

        public void setAssessDate(String assessDate) {
            this.assessDate = assessDate;
        }

        public static class EecWasteinfoBean {
            /**
             * id : 40
             * rid : 116
             * addrId : 28
             * varieties : 塑料瓶
             * unit : kg
             * count : 1
             * detailInfo : dfdg
             * type : null
             * state : 1
             * comeType : null
             * comeDate : null
             * createDate : 1531478338000
             * residentaddr : null
             * list : null
             * wasteinfoAddr : null
             * longitude : 0
             * latitude : 0
             * orderNumber : null
             */

            private int id;
            private int rid;
            private int addrId;
            private String varieties;
            private String unit;
            private int count;
            private String detailInfo;
            private String type;
            private String state;
            private String comeType;
            private String comeDate;
            private long createDate;
            private String residentaddr;
            private String list;
            private String wasteinfoAddr;
            private int longitude;
            private int latitude;
            private String orderNumber;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getRid() {
                return rid;
            }

            public void setRid(int rid) {
                this.rid = rid;
            }

            public int getAddrId() {
                return addrId;
            }

            public void setAddrId(int addrId) {
                this.addrId = addrId;
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

            public String getDetailInfo() {
                return detailInfo;
            }

            public void setDetailInfo(String detailInfo) {
                this.detailInfo = detailInfo;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public String getComeType() {
                return comeType;
            }

            public void setComeType(String comeType) {
                this.comeType = comeType;
            }

            public String getComeDate() {
                return comeDate;
            }

            public void setComeDate(String comeDate) {
                this.comeDate = comeDate;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getResidentaddr() {
                return residentaddr;
            }

            public void setResidentaddr(String residentaddr) {
                this.residentaddr = residentaddr;
            }

            public String getList() {
                return list;
            }

            public void setList(String list) {
                this.list = list;
            }

            public String getWasteinfoAddr() {
                return wasteinfoAddr;
            }

            public void setWasteinfoAddr(String wasteinfoAddr) {
                this.wasteinfoAddr = wasteinfoAddr;
            }

            public int getLongitude() {
                return longitude;
            }

            public void setLongitude(int longitude) {
                this.longitude = longitude;
            }

            public int getLatitude() {
                return latitude;
            }

            public void setLatitude(int latitude) {
                this.latitude = latitude;
            }

            public String getOrderNumber() {
                return orderNumber;
            }

            public void setOrderNumber(String orderNumber) {
                this.orderNumber = orderNumber;
            }
        }
    }
}
