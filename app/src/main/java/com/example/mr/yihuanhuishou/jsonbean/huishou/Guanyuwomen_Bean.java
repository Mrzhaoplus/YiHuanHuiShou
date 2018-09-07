package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/19.
 */

public class Guanyuwomen_Bean {

    /**
     * msg : 查询成功
     * code : 200
     * data : [{"id":1,"state":1,"content":"010-6236555"},{"id":2,"state":2,"content":"wx6165163"},{"id":3,"state":3,"content":"a1958@163.com"},{"id":4,"state":4,"content":"www.5c6c7c.com"},{"id":5,"state":5,"content":"6472000"}]
     */

    private String msg;
    private String code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 1
         * state : 1
         * content : 010-6236555
         */

        private int id;
        private int state;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
