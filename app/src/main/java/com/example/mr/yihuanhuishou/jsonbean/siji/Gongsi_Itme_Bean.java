package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/26.
 */

public class Gongsi_Itme_Bean {

    /**
     * date : [{"id":10,"companyId":1,"recyclersId":4,"state":"1","vmCode":1,"type":"2","createDate":1532600457000,"eecCompany":{"id":10,"companyUser":"","companyPassword":"","payPassword":"","companyImg":"","companyTitle":"1","corporationName":"1","corporationPhone":"11","businesLicenseImg":"","companyAddr":"1","createTime":null,"isAudit":"","token":""}},{"id":11,"companyId":2,"recyclersId":4,"state":"1","vmCode":1,"type":"2","createDate":1532600475000,"eecCompany":{"id":11,"companyUser":"","companyPassword":"","payPassword":"","companyImg":"","companyTitle":"北京XXX公司","corporationName":"张三","corporationPhone":"17795591255","businesLicenseImg":"","companyAddr":"北京市朝阳区 XXX 30号","createTime":null,"isAudit":"","token":""}}]
     * code : 200
     */

    private String code;
    private List<DataBean> data;

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
         * id : 10
         * companyId : 1
         * recyclersId : 4
         * state : 1
         * vmCode : 1
         * type : 2
         * createDate : 1532600457000
         * eecCompany : {"id":10,"companyUser":"","companyPassword":"","payPassword":"","companyImg":"","companyTitle":"1","corporationName":"1","corporationPhone":"11","businesLicenseImg":"","companyAddr":"1","createTime":null,"isAudit":"","token":""}
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
             * id : 10
             * companyUser :
             * companyPassword :
             * payPassword :
             * companyImg :
             * companyTitle : 1
             * corporationName : 1
             * corporationPhone : 11
             * businesLicenseImg :
             * companyAddr : 1
             * createTime : null
             * isAudit :
             * token :
             */

            private int id;
            private String companyUser;
            private String companyPassword;
            private String payPassword;
            private String companyImg;
            private String companyTitle;
            private String corporationName;
            private String corporationPhone;
            private String businesLicenseImg;
            private String companyAddr;
            private Object createTime;
            private String isAudit;
            private String token;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCompanyUser() {
                return companyUser;
            }

            public void setCompanyUser(String companyUser) {
                this.companyUser = companyUser;
            }

            public String getCompanyPassword() {
                return companyPassword;
            }

            public void setCompanyPassword(String companyPassword) {
                this.companyPassword = companyPassword;
            }

            public String getPayPassword() {
                return payPassword;
            }

            public void setPayPassword(String payPassword) {
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

            public String getBusinesLicenseImg() {
                return businesLicenseImg;
            }

            public void setBusinesLicenseImg(String businesLicenseImg) {
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

            public String getIsAudit() {
                return isAudit;
            }

            public void setIsAudit(String isAudit) {
                this.isAudit = isAudit;
            }

            public String getToken() {
                return token;
            }

            public void setToken(String token) {
                this.token = token;
            }
        }
    }
}
