package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/19.
 */

public class Xueqiu_Adapter_Bean {

    /**
     * msg : 查询成功
     * code : 200
     * data : {"id":32,"orderNumber":"20180719115163542801","wid":45,"recyclearsId":null,"count":11,"unitPrice":0,"totalMoney":0,"state":"0","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":null,"assess":null,"grade":null,"giveCurrency":110,"createDate":1531989343000,"rid":115,"eecWasteinfo":{"id":45,"rid":115,"addrId":30,"varieties":"塑料瓶","unit":"包","count":11,"detailInfo":"民工","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531988890000,"residentaddr":{"id":30,"rid":118,"name":"刘恩祥","phoneNumber":"13651143131","province":"北京市","city":"","counry":"海淀区","village":null,"floor":null,"door":null,"detailAddr":"北京市海淀区上地街道东北旺中路105号中关村软件园(软件园三号路)","longitude":116.285198,"latitude":40.046487,"isDefault":"1","createDate":1531988722000,"isExit":1},"list":[{"id":75,"wid":45,"content":"http://ech.oss-cn-beijing.aliyuncs.com/75db4ffc809e481ba0a193aa31a188e1","creatDate":1531988890000}],"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null},"comeType":1,"comeData":null,"assessDate":null}
     */

    private String msg;
    private String code;
    private String touXiang;
    private int distance;
    private DataBean data;

    public String getTouXiang() {
        return touXiang;
    }

    public void setTouXiang(String touXiang) {
        this.touXiang = touXiang;
    }

    public int getDistance() {
        return distance;
    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

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
         * id : 32
         * orderNumber : 20180719115163542801
         * wid : 45
         * recyclearsId : null
         * count : 11
         * unitPrice : 0
         * totalMoney : 0
         * state : 0
         * cancelEeason : null
         * cancelDescribe : null
         * cancelDate : null
         * paymentMethod : null
         * assess : null
         * grade : null
         * giveCurrency : 110
         * createDate : 1531989343000
         * rid : 115
         * eecWasteinfo : {"id":45,"rid":115,"addrId":30,"varieties":"塑料瓶","unit":"包","count":11,"detailInfo":"民工","type":null,"state":"1","comeType":null,"comeDate":null,"createDate":1531988890000,"residentaddr":{"id":30,"rid":118,"name":"刘恩祥","phoneNumber":"13651143131","province":"北京市","city":"","counry":"海淀区","village":null,"floor":null,"door":null,"detailAddr":"北京市海淀区上地街道东北旺中路105号中关村软件园(软件园三号路)","longitude":116.285198,"latitude":40.046487,"isDefault":"1","createDate":1531988722000,"isExit":1},"list":[{"id":75,"wid":45,"content":"http://ech.oss-cn-beijing.aliyuncs.com/75db4ffc809e481ba0a193aa31a188e1","creatDate":1531988890000}],"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null}
         * comeType : 1
         * comeData : null
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
        private long cancelDate;
        private String paymentMethod;
        private String assess;
        private float grade;
        private int giveCurrency;
        private long createDate;
        private int rid;
        private EecWasteinfoBean eecWasteinfo;
        private int comeType;
        private long comeData;
        private long assessDate;

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

        public float getGrade() {
            return grade;
        }

        public void setGrade(float grade) {
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

        public long getAssessDate() {
            return assessDate;
        }

        public void setAssessDate(long assessDate) {
            this.assessDate = assessDate;
        }

        public static class EecWasteinfoBean {
            /**
             * id : 45
             * rid : 115
             * addrId : 30
             * varieties : 塑料瓶
             * unit : 包
             * count : 11
             * detailInfo : 民工
             * type : null
             * state : 1
             * comeType : null
             * comeDate : null
             * createDate : 1531988890000
             * residentaddr : {"id":30,"rid":118,"name":"刘恩祥","phoneNumber":"13651143131","province":"北京市","city":"","counry":"海淀区","village":null,"floor":null,"door":null,"detailAddr":"北京市海淀区上地街道东北旺中路105号中关村软件园(软件园三号路)","longitude":116.285198,"latitude":40.046487,"isDefault":"1","createDate":1531988722000,"isExit":1}
             * list : [{"id":75,"wid":45,"content":"http://ech.oss-cn-beijing.aliyuncs.com/75db4ffc809e481ba0a193aa31a188e1","creatDate":1531988890000}]
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
            private double longitude;
            private double latitude;
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
                 * id : 30
                 * rid : 118
                 * name : 刘恩祥
                 * phoneNumber : 13651143131
                 * province : 北京市
                 * city :
                 * counry : 海淀区
                 * village : null
                 * floor : null
                 * door : null
                 * detailAddr : 北京市海淀区上地街道东北旺中路105号中关村软件园(软件园三号路)
                 * longitude : 116.285198
                 * latitude : 40.046487
                 * isDefault : 1
                 * createDate : 1531988722000
                 * isExit : 1
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
            }

            public static class ListBean {
                /**
                 * id : 75
                 * wid : 45
                 * content : http://ech.oss-cn-beijing.aliyuncs.com/75db4ffc809e481ba0a193aa31a188e1
                 * creatDate : 1531988890000
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
