package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/18.
 */

public class Recy_Dingdan_Details_Bean {


    /**
     * msg : 查询成功
     * code : 200
     * data : {"id":296,"orderNumber":"20180806129164823730","wid":249,"recyclearsId":50,"count":1,"unitPrice":0.01,"totalMoney":0.01,"state":"4","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":"0","assess":null,"grade":null,"giveCurrency":15,"createDate":1533545304000,"rid":129,"eecWasteinfo":{"id":249,"rid":129,"addrId":62,"varieties":"手机数码","unit":"包","count":1,"detailInfo":"11111","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1533545267000,"residentaddr":{"id":62,"rid":129,"name":"杨珂","phoneNumber":"18972981819","province":"北京市","city":"北京市","counry":"大兴区","village":"武夷公寓","floor":"A","door":"102","detailAddr":"北京市大兴区北京经济技术开发区鸿坤云时代","longitude":116.575984,"latitude":39.78977,"isDefault":"0","createDate":1533112986000,"isExit":0,"pageNum":null,"pageSize":null},"list":[{"id":286,"wid":249,"content":"http://ech.oss-cn-beijing.aliyuncs.com/cf2572b0465b49809e7f8395ae8f0a67","creatDate":1533545268000}],"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null},"comeType":1,"comeData":1533545304000,"assessDate":null,"city":null,"addrId":62}
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
         * id : 296
         * orderNumber : 20180806129164823730
         * wid : 249
         * recyclearsId : 50
         * count : 1
         * unitPrice : 0.01
         * totalMoney : 0.01
         * state : 4
         * cancelEeason : null
         * cancelDescribe : null
         * cancelDate : null
         * paymentMethod : 0
         * assess : null
         * grade : null
         * giveCurrency : 15
         * createDate : 1533545304000
         * rid : 129
         * eecWasteinfo : {"id":249,"rid":129,"addrId":62,"varieties":"手机数码","unit":"包","count":1,"detailInfo":"11111","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1533545267000,"residentaddr":{"id":62,"rid":129,"name":"杨珂","phoneNumber":"18972981819","province":"北京市","city":"北京市","counry":"大兴区","village":"武夷公寓","floor":"A","door":"102","detailAddr":"北京市大兴区北京经济技术开发区鸿坤云时代","longitude":116.575984,"latitude":39.78977,"isDefault":"0","createDate":1533112986000,"isExit":0,"pageNum":null,"pageSize":null},"list":[{"id":286,"wid":249,"content":"http://ech.oss-cn-beijing.aliyuncs.com/cf2572b0465b49809e7f8395ae8f0a67","creatDate":1533545268000}],"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null}
         * comeType : 1
         * comeData : 1533545304000
         * assessDate : null
         * city : null
         * addrId : 62
         */

        private int id;
        private String orderNumber;
        private int wid;
        private int recyclearsId;
        private int count;
        private double unitPrice;
        private double totalMoney;
        private String state;
        private String cancelEeason;
        private String cancelDescribe;
        private long cancelDate;
        private String paymentMethod;
        private String assess;
        private String grade;
        private float giveCurrency;
        private long createDate;
        private int rid;
        private EecWasteinfoBean eecWasteinfo;
        private int comeType;
        private long comeData;
        private long assessDate;
        private String city;
        private int addrId;

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

        public double getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
        }

        public double getTotalMoney() {
            return totalMoney;
        }

        public void setTotalMoney(double totalMoney) {
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

        public long getCancelDate() {
            return cancelDate;
        }

        public void setCancelDate(long cancelDate) {
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

        public String getGrade() {
            return grade;
        }

        public void setGrade(String grade) {
            this.grade = grade;
        }

        public float getGiveCurrency() {
            return giveCurrency;
        }

        public void setGiveCurrency(float giveCurrency) {
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

        public long getAssessDate() {
            return assessDate;
        }

        public void setAssessDate(long assessDate) {
            this.assessDate = assessDate;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public int getAddrId() {
            return addrId;
        }

        public void setAddrId(int addrId) {
            this.addrId = addrId;
        }

        public static class EecWasteinfoBean {
            /**
             * id : 249
             * rid : 129
             * addrId : 62
             * varieties : 手机数码
             * unit : 包
             * count : 1
             * detailInfo : 11111
             * type : null
             * state : 1
             * comeType : null
             * comeDate : null
             * createDate : 1533545267000
             * residentaddr : {"id":62,"rid":129,"name":"杨珂","phoneNumber":"18972981819","province":"北京市","city":"北京市","counry":"大兴区","village":"武夷公寓","floor":"A","door":"102","detailAddr":"北京市大兴区北京经济技术开发区鸿坤云时代","longitude":116.575984,"latitude":39.78977,"isDefault":"0","createDate":1533112986000,"isExit":0,"pageNum":null,"pageSize":null}
             * list : [{"id":286,"wid":249,"content":"http://ech.oss-cn-beijing.aliyuncs.com/cf2572b0465b49809e7f8395ae8f0a67","creatDate":1533545268000}]
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
            private long comeDate;
            private long createDate;
            private ResidentaddrBean residentaddr;
            private String wasteinfoAddr;
            private int longitude;
            private int latitude;
            private String orderNumber;
            private List<ListBean> list;

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

            public ResidentaddrBean getResidentaddr() {
                return residentaddr;
            }

            public void setResidentaddr(ResidentaddrBean residentaddr) {
                this.residentaddr = residentaddr;
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

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ResidentaddrBean {
                /**
                 * id : 62
                 * rid : 129
                 * name : 杨珂
                 * phoneNumber : 18972981819
                 * province : 北京市
                 * city : 北京市
                 * counry : 大兴区
                 * village : 武夷公寓
                 * floor : A
                 * door : 102
                 * detailAddr : 北京市大兴区北京经济技术开发区鸿坤云时代
                 * longitude : 116.575984
                 * latitude : 39.78977
                 * isDefault : 0
                 * createDate : 1533112986000
                 * isExit : 0
                 * pageNum : null
                 * pageSize : null
                 */

                private int id;
                private int rid;
                private String name;
                private String phoneNumber;
                private String province;
                private String city;
                private String counry;
                private String village;
                private String floor;
                private String door;
                private String detailAddr;
                private double longitude;
                private double latitude;
                private String isDefault;
                private long createDate;
                private int isExit;
                private String pageNum;
                private String pageSize;

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

                public String getVillage() {
                    return village;
                }

                public void setVillage(String village) {
                    this.village = village;
                }

                public String getFloor() {
                    return floor;
                }

                public void setFloor(String floor) {
                    this.floor = floor;
                }

                public String getDoor() {
                    return door;
                }

                public void setDoor(String door) {
                    this.door = door;
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

                public int getIsExit() {
                    return isExit;
                }

                public void setIsExit(int isExit) {
                    this.isExit = isExit;
                }

                public String getPageNum() {
                    return pageNum;
                }

                public void setPageNum(String pageNum) {
                    this.pageNum = pageNum;
                }

                public String getPageSize() {
                    return pageSize;
                }

                public void setPageSize(String pageSize) {
                    this.pageSize = pageSize;
                }
            }

            public static class ListBean {
                /**
                 * id : 286
                 * wid : 249
                 * content : http://ech.oss-cn-beijing.aliyuncs.com/cf2572b0465b49809e7f8395ae8f0a67
                 * creatDate : 1533545268000
                 */

                private int id;
                private int wid;
                private String content;
                private long creatDate;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public int getWid() {
                    return wid;
                }

                public void setWid(int wid) {
                    this.wid = wid;
                }

                public String getContent() {
                    return content;
                }

                public void setContent(String content) {
                    this.content = content;
                }

                public long getCreatDate() {
                    return creatDate;
                }

                public void setCreatDate(long creatDate) {
                    this.creatDate = creatDate;
                }
            }
        }
    }
}
