package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/19.
 */

public class Recy_Daizhifu_Bean {

    /**
     * pageNo : 1
     * pageCount : 10
     * start : 0
     * end : 10
     * totalCount : 1
     * totalPageNo : 1
     * dataList : [{"id":13,"orderNumber":"20180712116144612607","wid":21,"recyclearsId":35,"count":50,"unitPrice":1,"totalMoney":50,"state":"3","cancelEeason":null,"cancelDescribe":null,"cancelDate":null,"paymentMethod":null,"assess":null,"grade":null,"giveCurrency":0,"createDate":1531377973000,"rid":116,"eecWasteinfo":{"id":21,"rid":116,"addrId":21,"varieties":"电脑","unit":"个","count":111,"detailInfo":"不穿了，给山城区的孩子们吧","type":null,"state":"1","comeType":null,"comeDate":1531180920000,"createDate":null,"residentaddr":{"id":21,"rid":116,"name":"fucheng","phoneNumber":"110","province":"北京市","city":null,"counry":"海淀区","village":null,"floor":null,"door":null,"detailAddr":"北京市海淀区海淀街道长春桥路","longitude":116.298087,"latitude":39.959649,"isDefault":"0","createDate":1531215093000,"isExit":1},"list":null,"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null},"comeType":0,"comeData":1531353660000,"assessDate":null}]
     * code : 200
     * msg : 查询成功！
     */

    private int pageNo;
    private int pageCount;
    private int start;
    private int end;
    private int totalCount;
    private int totalPageNo;
    private int code;
    private String msg;
    private List<DataListBean> dataList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageNo() {
        return totalPageNo;
    }

    public void setTotalPageNo(int totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * id : 13
         * orderNumber : 20180712116144612607
         * wid : 21
         * recyclearsId : 35
         * count : 50
         * unitPrice : 1
         * totalMoney : 50
         * state : 3
         * cancelEeason : null
         * cancelDescribe : null
         * cancelDate : null
         * paymentMethod : null
         * assess : null
         * grade : null
         * giveCurrency : 0
         * createDate : 1531377973000
         * rid : 116
         * eecWasteinfo : {"id":21,"rid":116,"addrId":21,"varieties":"电脑","unit":"个","count":111,"detailInfo":"不穿了，给山城区的孩子们吧","type":null,"state":"1","comeType":null,"comeDate":1531180920000,"createDate":null,"residentaddr":{"id":21,"rid":116,"name":"fucheng","phoneNumber":"110","province":"北京市","city":null,"counry":"海淀区","village":null,"floor":null,"door":null,"detailAddr":"北京市海淀区海淀街道长春桥路","longitude":116.298087,"latitude":39.959649,"isDefault":"0","createDate":1531215093000,"isExit":1},"list":null,"wasteinfoAddr":null,"longitude":0,"latitude":0,"orderNumber":null}
         * comeType : 0
         * comeData : 1531353660000
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
             * id : 21
             * rid : 116
             * addrId : 21
             * varieties : 电脑
             * unit : 个
             * count : 111
             * detailInfo : 不穿了，给山城区的孩子们吧
             * type : null
             * state : 1
             * comeType : null
             * comeDate : 1531180920000
             * createDate : null
             * residentaddr : {"id":21,"rid":116,"name":"fucheng","phoneNumber":"110","province":"北京市","city":null,"counry":"海淀区","village":null,"floor":null,"door":null,"detailAddr":"北京市海淀区海淀街道长春桥路","longitude":116.298087,"latitude":39.959649,"isDefault":"0","createDate":1531215093000,"isExit":1}
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
            private long comeDate;
            private long createDate;
            private ResidentaddrBean residentaddr;
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

            public static class ResidentaddrBean {
                /**
                 * id : 21
                 * rid : 116
                 * name : fucheng
                 * phoneNumber : 110
                 * province : 北京市
                 * city : null
                 * counry : 海淀区
                 * village : null
                 * floor : null
                 * door : null
                 * detailAddr : 北京市海淀区海淀街道长春桥路
                 * longitude : 116.298087
                 * latitude : 39.959649
                 * isDefault : 0
                 * createDate : 1531215093000
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
        }
    }
}
