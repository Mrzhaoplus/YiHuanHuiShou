package com.example.mr.yihuanhuishou.jsonbean.huishou;

/**
 * Created by Mr赵 on 2018/7/23.
 */

public class Shiming_Detail {

    /**
     * msg : 成功！
     * code : 200
     * data : {"id":2,"userid":35,"usertype":"1","idcard":"tupian.jpg","state":"0","createdate":1532147636000}
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
         * id : 2
         * userid : 35
         * usertype : 1
         * idcard : tupian.jpg
         * state : 0
         * createdate : 1532147636000
         */

        private int id;
        private int userid;
        private String usertype;
        private String idcard;
        private String state;
        private long createdate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserid() {
            return userid;
        }

        public void setUserid(int userid) {
            this.userid = userid;
        }

        public String getUsertype() {
            return usertype;
        }

        public void setUsertype(String usertype) {
            this.usertype = usertype;
        }

        public String getIdcard() {
            return idcard;
        }

        public void setIdcard(String idcard) {
            this.idcard = idcard;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getCreatedate() {
            return createdate;
        }

        public void setCreatedate(long createdate) {
            this.createdate = createdate;
        }
    }
}
