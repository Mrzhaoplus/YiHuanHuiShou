package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/25.
 */

public class Geren_Bean {


    /**
     * msg : 查询成功
     * code : 200
     * data : {"usedriverPlateNumber":"1234","driverCompany":[{"id":11,"companyId":2,"recyclersId":4,"state":"1","vmCode":1,"type":"2","createDate":1532600475000,"eecCompany":{"id":11,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":"","companyTitle":"北京XXX公司","corporationName":"张三","corporationPhone":"17795591255","businesLicenseImg":null,"companyAddr":"北京市朝阳区 XXX 30号","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":null,"latitude":null,"state":null,"reason":null,"imUser":null,"imPassword":null}}],"driver":{"id":4,"driverNicekname":"","driverPhone":"17301373035","driverPassword":"76AD67469EEA66BBE1866B30339F6B21A64F70EF2DE9DD7623C3B824","driverIdentityCard":"130124199607070950","driverSex":false,"driverAge":15,"driverImage":"","driverRegistetTime":1532419028000,"driverBanlance":99898.9,"imUser":"driver4","imPassword":"123456","payid":"2088422229833564","token":"DoJfTiTR/bH9CRYiis6TQ==","driverPlateNumber":"130124199607070950"}}
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
         * usedriverPlateNumber : 1234
         * driverCompany : [{"id":11,"companyId":2,"recyclersId":4,"state":"1","vmCode":1,"type":"2","createDate":1532600475000,"eecCompany":{"id":11,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":"","companyTitle":"北京XXX公司","corporationName":"张三","corporationPhone":"17795591255","businesLicenseImg":null,"companyAddr":"北京市朝阳区 XXX 30号","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":null,"latitude":null,"state":null,"reason":null,"imUser":null,"imPassword":null}}]
         * driver : {"id":4,"driverNicekname":"","driverPhone":"17301373035","driverPassword":"76AD67469EEA66BBE1866B30339F6B21A64F70EF2DE9DD7623C3B824","driverIdentityCard":"130124199607070950","driverSex":false,"driverAge":15,"driverImage":"","driverRegistetTime":1532419028000,"driverBanlance":99898.9,"imUser":"driver4","imPassword":"123456","payid":"2088422229833564","token":"DoJfTiTR/bH9CRYiis6TQ==","driverPlateNumber":"130124199607070950"}
         */

        private String usedriverPlateNumber;
        private DriverBean driver;
        private List<DriverCompanyBean> driverCompany;

        public String getUsedriverPlateNumber() {
            return usedriverPlateNumber;
        }

        public void setUsedriverPlateNumber(String usedriverPlateNumber) {
            this.usedriverPlateNumber = usedriverPlateNumber;
        }

        public DriverBean getDriver() {
            return driver;
        }

        public void setDriver(DriverBean driver) {
            this.driver = driver;
        }

        public List<DriverCompanyBean> getDriverCompany() {
            return driverCompany;
        }

        public void setDriverCompany(List<DriverCompanyBean> driverCompany) {
            this.driverCompany = driverCompany;
        }

        public static class DriverBean {
            /**
             * id : 4
             * driverNicekname :
             * driverPhone : 17301373035
             * driverPassword : 76AD67469EEA66BBE1866B30339F6B21A64F70EF2DE9DD7623C3B824
             * driverIdentityCard : 130124199607070950
             * driverSex : false
             * driverAge : 15
             * driverImage :
             * driverRegistetTime : 1532419028000
             * driverBanlance : 99898.9
             * imUser : driver4
             * imPassword : 123456
             * payid : 2088422229833564
             * token : DoJfTiTR/bH9CRYiis6TQ==
             * driverPlateNumber : 130124199607070950
             */

            private int id;
            private String driverNicekname;
            private String driverPhone;
            private String driverPassword;
            private String driverIdentityCard;
            private boolean driverSex;
            private int driverAge;
            private String driverImage;
            private long driverRegistetTime;
            private double driverBanlance;
            private String imUser;
            private String imPassword;
            private String payid;
            private String token;
            private String driverPlateNumber;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDriverNicekname() {
                return driverNicekname;
            }

            public void setDriverNicekname(String driverNicekname) {
                this.driverNicekname = driverNicekname;
            }

            public String getDriverPhone() {
                return driverPhone;
            }

            public void setDriverPhone(String driverPhone) {
                this.driverPhone = driverPhone;
            }

            public String getDriverPassword() {
                return driverPassword;
            }

            public void setDriverPassword(String driverPassword) {
                this.driverPassword = driverPassword;
            }

            public String getDriverIdentityCard() {
                return driverIdentityCard;
            }

            public void setDriverIdentityCard(String driverIdentityCard) {
                this.driverIdentityCard = driverIdentityCard;
            }

            public boolean getDriverSex() {
                return driverSex;
            }

            public void setDriverSex(boolean driverSex) {
                this.driverSex = driverSex;
            }

            public int getDriverAge() {
                return driverAge;
            }

            public void setDriverAge(int driverAge) {
                this.driverAge = driverAge;
            }

            public String getDriverImage() {
                return driverImage;
            }

            public void setDriverImage(String driverImage) {
                this.driverImage = driverImage;
            }

            public long getDriverRegistetTime() {
                return driverRegistetTime;
            }

            public void setDriverRegistetTime(long driverRegistetTime) {
                this.driverRegistetTime = driverRegistetTime;
            }

            public double getDriverBanlance() {
                return driverBanlance;
            }

            public void setDriverBanlance(double driverBanlance) {
                this.driverBanlance = driverBanlance;
            }

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

            public String getPayid() {
                return payid;
            }

            public void setPayid(String payid) {
                this.payid = payid;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }

            public String getDriverPlateNumber() {
                return driverPlateNumber;
            }

            public void setDriverPlateNumber(String driverPlateNumber) {
                this.driverPlateNumber = driverPlateNumber;
            }
        }

        public static class DriverCompanyBean {
            /**
             * id : 11
             * companyId : 2
             * recyclersId : 4
             * state : 1
             * vmCode : 1
             * type : 2
             * createDate : 1532600475000
             * eecCompany : {"id":11,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":"","companyTitle":"北京XXX公司","corporationName":"张三","corporationPhone":"17795591255","businesLicenseImg":null,"companyAddr":"北京市朝阳区 XXX 30号","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":null,"latitude":null,"state":null,"reason":null,"imUser":null,"imPassword":null}
             */

            private int id;
            private int companyId;
            private int recyclersId;
            private String state;
            private int vmCode;
            private String type;
            private long createDate;
            private EecCompanyBean eecCompany;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public int getRecyclersId() {
                return recyclersId;
            }

            public void setRecyclersId(int recyclersId) {
                this.recyclersId = recyclersId;
            }

            public String getState() {
                return state;
            }

            public void setState(String state) {
                this.state = state;
            }

            public int getVmCode() {
                return vmCode;
            }

            public void setVmCode(int vmCode) {
                this.vmCode = vmCode;
            }

            public String getType() {
                return type;
            }

            public void setType(String type) {
                this.type = type;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public EecCompanyBean getEecCompany() {
                return eecCompany;
            }

            public void setEecCompany(EecCompanyBean eecCompany) {
                this.eecCompany = eecCompany;
            }

            public static class EecCompanyBean {
                /**
                 * id : 11
                 * companyUser : null
                 * companyPassword : null
                 * payPassword : null
                 * companyImg :
                 * companyTitle : 北京XXX公司
                 * corporationName : 张三
                 * corporationPhone : 17795591255
                 * businesLicenseImg : null
                 * companyAddr : 北京市朝阳区 XXX 30号
                 * createTime : null
                 * isAudit : null
                 * token : null
                 * intro : null
                 * longitude : null
                 * latitude : null
                 * state : null
                 * reason : null
                 * imUser : null
                 * imPassword : null
                 */

                private int id;
                private Object companyUser;
                private Object companyPassword;
                private Object payPassword;
                private String companyImg;
                private String companyTitle;
                private String corporationName;
                private String corporationPhone;
                private Object businesLicenseImg;
                private String companyAddr;
                private Object createTime;
                private Object isAudit;
                private Object token;
                private Object intro;
                private Object longitude;
                private Object latitude;
                private Object state;
                private Object reason;
                private Object imUser;
                private Object imPassword;

                public int getId() {
                    return id;
                }

                public void setId(int id) {
                    this.id = id;
                }

                public Object getCompanyUser() {
                    return companyUser;
                }

                public void setCompanyUser(Object companyUser) {
                    this.companyUser = companyUser;
                }

                public Object getCompanyPassword() {
                    return companyPassword;
                }

                public void setCompanyPassword(Object companyPassword) {
                    this.companyPassword = companyPassword;
                }

                public Object getPayPassword() {
                    return payPassword;
                }

                public void setPayPassword(Object payPassword) {
                    this.payPassword = payPassword;
                }

                public String getCompanyImg() {
                    return companyImg;
                }

                public void setCompanyImg(String companyImg) {
                    this.companyImg = companyImg;
                }

                public String getCompanyTitle() {
                    return companyTitle;
                }

                public void setCompanyTitle(String companyTitle) {
                    this.companyTitle = companyTitle;
                }

                public String getCorporationName() {
                    return corporationName;
                }

                public void setCorporationName(String corporationName) {
                    this.corporationName = corporationName;
                }

                public String getCorporationPhone() {
                    return corporationPhone;
                }

                public void setCorporationPhone(String corporationPhone) {
                    this.corporationPhone = corporationPhone;
                }

                public Object getBusinesLicenseImg() {
                    return businesLicenseImg;
                }

                public void setBusinesLicenseImg(Object businesLicenseImg) {
                    this.businesLicenseImg = businesLicenseImg;
                }

                public String getCompanyAddr() {
                    return companyAddr;
                }

                public void setCompanyAddr(String companyAddr) {
                    this.companyAddr = companyAddr;
                }

                public Object getCreateTime() {
                    return createTime;
                }

                public void setCreateTime(Object createTime) {
                    this.createTime = createTime;
                }

                public Object getIsAudit() {
                    return isAudit;
                }

                public void setIsAudit(Object isAudit) {
                    this.isAudit = isAudit;
                }

                public Object getToken() {
                    return token;
                }

                public void setToken(Object token) {
                    this.token = token;
                }

                public Object getIntro() {
                    return intro;
                }

                public void setIntro(Object intro) {
                    this.intro = intro;
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

                public Object getState() {
                    return state;
                }

                public void setState(Object state) {
                    this.state = state;
                }

                public Object getReason() {
                    return reason;
                }

                public void setReason(Object reason) {
                    this.reason = reason;
                }

                public Object getImUser() {
                    return imUser;
                }

                public void setImUser(Object imUser) {
                    this.imUser = imUser;
                }

                public Object getImPassword() {
                    return imPassword;
                }

                public void setImPassword(Object imPassword) {
                    this.imPassword = imPassword;
                }
            }
        }
    }
}
