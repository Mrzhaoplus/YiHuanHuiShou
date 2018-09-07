package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/31.
 */

public class Driver_Leibie_Bean {

    /**
     * code : 200
     * data : [{"id":1,"name":"衣服","sort":1,"currency":5},{"id":2,"name":"塑料瓶","sort":2,"currency":10},{"id":3,"name":"手机数码","sort":3,"currency":15},{"id":4,"name":"鞋类","sort":4,"currency":20},{"id":5,"name":"金属","sort":5,"currency":25},{"id":6,"name":"纸箱","sort":6,"currency":30},{"id":7,"name":"大家电","sort":7,"currency":35}]
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
         * id : 1
         * name : 衣服
         * sort : 1
         * currency : 5
         */

        private int id;
        private String name;
        private int sort;
        private int currency;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getSort() {
            return sort;
        }

        public void setSort(int sort) {
            this.sort = sort;
        }

        public int getCurrency() {
            return currency;
        }

        public void setCurrency(int currency) {
            this.currency = currency;
        }
    }
}
