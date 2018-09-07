package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/31.
 */

public class Deiver_Gongsi_shuishou_Bean {

    /**
     * code : 200
     * data : [{"isJoin":false,"typeStr":"纸箱 衣服 ","Company":{"id":1,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":null,"companyTitle":"1","corporationName":null,"corporationPhone":"11","businesLicenseImg":null,"companyAddr":"1","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":116.392925,"latitude":39.904462,"state":null}},{"isJoin":true,"typeStr":"","Company":{"id":2,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":null,"companyTitle":"北京XXX公司","corporationName":null,"corporationPhone":"17795591255","businesLicenseImg":null,"companyAddr":"北京市朝阳区 XXX 30号","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":116.294048,"latitude":39.968694,"state":null}},{"isJoin":false,"typeStr":"","Company":{"id":3,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":null,"companyTitle":"北京XXX公司","corporationName":null,"corporationPhone":"17789562314","businesLicenseImg":null,"companyAddr":"北京市朝阳区 XXX 30号","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":116.287651,"latitude":40.046725,"state":null}},{"isJoin":false,"typeStr":"","Company":{"id":16,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":null,"companyTitle":"aaaaaaa","corporationName":null,"corporationPhone":"13370133060","businesLicenseImg":null,"companyAddr":"aaaaaa","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":116.273403,"latitude":40.044031,"state":null}}]
     */

    private int code;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * isJoin : false
         * typeStr : 纸箱 衣服
         * Company : {"id":1,"companyUser":null,"companyPassword":null,"payPassword":null,"companyImg":null,"companyTitle":"1","corporationName":null,"corporationPhone":"11","businesLicenseImg":null,"companyAddr":"1","createTime":null,"isAudit":null,"token":null,"intro":null,"longitude":116.392925,"latitude":39.904462,"state":null}
         */

        private boolean isJoin;
        private String typeStr;
        private CompanyBean Company;

        public boolean getIsJoin() {
            return isJoin;
        }

        public void setIsJoin(boolean isJoin) {
            this.isJoin = isJoin;
        }

        public String getTypeStr() {
            return typeStr;
        }

        public void setTypeStr(String typeStr) {
            this.typeStr = typeStr;
        }

        public CompanyBean getCompany() {
            return Company;
        }

        public void setCompany(CompanyBean Company) {
            this.Company = Company;
        }

        public static class CompanyBean {
            /**
             * id : 1
             * companyUser : null
             * companyPassword : null
             * payPassword : null
             * companyImg : null
             * companyTitle : 1
             * corporationName : null
             * corporationPhone : 11
             * businesLicenseImg : null
             * companyAddr : 1
             * createTime : null
             * isAudit : null
             * token : null
             * intro : null
             * longitude : 116.392925
             * latitude : 39.904462
             * state : null
             */

            private int id;
            private Object companyUser;
            private Object companyPassword;
            private Object payPassword;
            private Object companyImg;
            private String companyTitle;
            private Object corporationName;
            private String corporationPhone;
            private Object businesLicenseImg;
            private String companyAddr;
            private Object createTime;
            private Object isAudit;
            private Object token;
            private Object intro;
            private double longitude;
            private double latitude;
            private Object state;

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

            public Object getCompanyImg() {
                return companyImg;
            }

            public void setCompanyImg(Object companyImg) {
                this.companyImg = companyImg;
            }

            public String getCompanyTitle() {
                return companyTitle;
            }

            public void setCompanyTitle(String companyTitle) {
                this.companyTitle = companyTitle;
            }

            public Object getCorporationName() {
                return corporationName;
            }

            public void setCorporationName(Object corporationName) {
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

            public Object getState() {
                return state;
            }

            public void setState(Object state) {
                this.state = state;
            }
        }
    }
}
