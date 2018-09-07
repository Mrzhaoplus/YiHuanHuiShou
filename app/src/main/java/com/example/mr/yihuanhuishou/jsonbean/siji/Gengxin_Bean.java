package com.example.mr.yihuanhuishou.jsonbean.siji;

/**
 * Created by Mr赵 on 2018/7/27.
 */

public class Gengxin_Bean {


    /**
     * msg : 查询成功
     * code : 200
     * data : {"id":1,"versionnumber":"1","versionname":"初版","versionpath":"www.baidu.com","state":"0","createdate":1532673242000}
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
         * id : 1
         * versionnumber : 1
         * versionname : 初版
         * versionpath : www.baidu.com
         * state : 0
         * createdate : 1532673242000
         */

        private int id;
        private String versionnumber;
        private String versionname;
        private String versionpath;
        private String state;
        private long createdate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getVersionnumber() {
            return versionnumber;
        }

        public void setVersionnumber(String versionnumber) {
            this.versionnumber = versionnumber;
        }

        public String getVersionname() {
            return versionname;
        }

        public void setVersionname(String versionname) {
            this.versionname = versionname;
        }

        public String getVersionpath() {
            return versionpath;
        }

        public void setVersionpath(String versionpath) {
            this.versionpath = versionpath;
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
