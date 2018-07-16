package com.example.mr.yihuanhuishou.jsonbean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/12.
 */

public class Xitong_Recycler_Bean {

    /**
     * msg : 查询成功！
     * code : 200
     * data : [{"id":1,"name":"衣服","sort":1},{"id":2,"name":"塑料瓶","sort":2},{"id":3,"name":"手机数码","sort":3},{"id":4,"name":"鞋类","sort":4},{"id":5,"name":"金属","sort":5},{"id":6,"name":"纸箱","sort":6},{"id":7,"name":"大家电","sort":7}]
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
         * id : 1
         * name : 衣服
         * sort : 1
         */

        private int id;
        private String name;
        private int sort;

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
    }
}
