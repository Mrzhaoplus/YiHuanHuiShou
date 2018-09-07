package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/18.
 */

public class Huishou_sort_Bean {

    /**
     * msg : 查询成功！
     * code : 200
     * data : [{"id":54,"userId":35,"userType":"1","code":1,"name":"衣服","unitPrice":10},{"id":55,"userId":35,"userType":"1","code":4,"name":"鞋类","unitPrice":30},{"id":56,"userId":35,"userType":"1","code":5,"name":"金属","unitPrice":50},{"id":57,"userId":35,"userType":"1","code":7,"name":"大家电","unitPrice":60}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 54
         * userId : 35
         * userType : 1
         * code : 1
         * name : 衣服
         * unitPrice : 10
         */

        private int id;
        private int userId;
        private String userType;
        private int code;
        private String name;
        private int unitPrice;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getCode() {
            return code;
        }

        public void setCode(int code) {
            this.code = code;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getUnitPrice() {
            return unitPrice;
        }

        public void setUnitPrice(int unitPrice) {
            this.unitPrice = unitPrice;
        }
    }
}
