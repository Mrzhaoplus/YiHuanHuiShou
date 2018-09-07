package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/27.
 */

public class Huishoucompany_Details_Bean {


    /**
     * msg : 查询成功
     * code : 200
     * data : {"isJoin":0,"CompanyName":"1","corporationPhone":"11","corporationName":"1","intro":"简介","imUser":"company1","CompanyAddr":"1","VarietiesmanageList":[{"id":19,"recycleCategoriesTitle":"塑料瓶","recycleCategoriesPrice":22,"companyId":1,"danwei":"kg","cateArray":null,"varieties":null},{"id":20,"recycleCategoriesTitle":"塑料瓶","recycleCategoriesPrice":2,"companyId":1,"danwei":"kg","cateArray":null,"varieties":null}],"companyImg":"https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2511100503,501373762&fm=27&gp=0.jpg"}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * isJoin : 0
         * CompanyName : 1
         * corporationPhone : 11
         * corporationName : 1
         * intro : 简介
         * imUser : company1
         * CompanyAddr : 1
         * VarietiesmanageList : [{"id":19,"recycleCategoriesTitle":"塑料瓶","recycleCategoriesPrice":22,"companyId":1,"danwei":"kg","cateArray":null,"varieties":null},{"id":20,"recycleCategoriesTitle":"塑料瓶","recycleCategoriesPrice":2,"companyId":1,"danwei":"kg","cateArray":null,"varieties":null}]
         * companyImg : https://ss1.bdstatic.com/70cFvXSh_Q1YnxGkpoWK1HF6hhy/it/u=2511100503,501373762&fm=27&gp=0.jpg
         */

        private int isJoin;
        private String CompanyName;
        private String corporationPhone;
        private String corporationName;
        private String intro;
        private String imUser;
        private String CompanyAddr;
        private String companyImg;
        private List<VarietiesmanageListBean> VarietiesmanageList;

        public int getIsJoin() {
            return isJoin;
        }

        public void setIsJoin(int isJoin) {
            this.isJoin = isJoin;
        }

        public String getCompanyName() {
            return CompanyName;
        }

        public void setCompanyName(String CompanyName) {
            this.CompanyName = CompanyName;
        }

        public String getCorporationPhone() {
            return corporationPhone;
        }

        public void setCorporationPhone(String corporationPhone) {
            this.corporationPhone = corporationPhone;
        }

        public String getCorporationName() {
            return corporationName;
        }

        public void setCorporationName(String corporationName) {
            this.corporationName = corporationName;
        }

        public String getIntro() {
            return intro;
        }

        public void setIntro(String intro) {
            this.intro = intro;
        }

        public String getImUser() {
            return imUser;
        }

        public void setImUser(String imUser) {
            this.imUser = imUser;
        }

        public String getCompanyAddr() {
            return CompanyAddr;
        }

        public void setCompanyAddr(String CompanyAddr) {
            this.CompanyAddr = CompanyAddr;
        }

        public String getCompanyImg() {
            return companyImg;
        }

        public void setCompanyImg(String companyImg) {
            this.companyImg = companyImg;
        }

        public List<VarietiesmanageListBean> getVarietiesmanageList() {
            return VarietiesmanageList;
        }

        public void setVarietiesmanageList(List<VarietiesmanageListBean> VarietiesmanageList) {
            this.VarietiesmanageList = VarietiesmanageList;
        }

        public static class VarietiesmanageListBean {
            /**
             * id : 19
             * recycleCategoriesTitle : 塑料瓶
             * recycleCategoriesPrice : 22
             * companyId : 1
             * danwei : kg
             * cateArray : null
             * varieties : null
             */

            private int id;
            private String recycleCategoriesTitle;
            private double recycleCategoriesPrice;
            private int companyId;
            private String danwei;
            private Object cateArray;
            private Object varieties;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getRecycleCategoriesTitle() {
                return recycleCategoriesTitle;
            }

            public void setRecycleCategoriesTitle(String recycleCategoriesTitle) {
                this.recycleCategoriesTitle = recycleCategoriesTitle;
            }

            public double getRecycleCategoriesPrice() {
                return recycleCategoriesPrice;
            }

            public void setRecycleCategoriesPrice(double recycleCategoriesPrice) {
                this.recycleCategoriesPrice = recycleCategoriesPrice;
            }

            public int getCompanyId() {
                return companyId;
            }

            public void setCompanyId(int companyId) {
                this.companyId = companyId;
            }

            public String getDanwei() {
                return danwei;
            }

            public void setDanwei(String danwei) {
                this.danwei = danwei;
            }

            public Object getCateArray() {
                return cateArray;
            }

            public void setCateArray(Object cateArray) {
                this.cateArray = cateArray;
            }

            public Object getVarieties() {
                return varieties;
            }

            public void setVarieties(Object varieties) {
                this.varieties = varieties;
            }
        }
    }
}
