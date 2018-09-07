package com.example.mr.yihuanhuishou.jsonbean.siji;

/**
 * Created by Mr赵 on 2018/8/9.
 */

public class Driver_Huanxin_Bean {

    /**
     * msg : 获取用户信息成功！
     * code : 200
     * data : {"name":"，，","headImage":"http://ech.oss-cn-beijing.aliyuncs.com/4f962abb976f4bfbb8a4aaf5fa1dd447","imUser":"recyclers50229"}
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
         * name : ，，
         * headImage : http://ech.oss-cn-beijing.aliyuncs.com/4f962abb976f4bfbb8a4aaf5fa1dd447
         * imUser : recyclers50229
         */

        private String name;
        private String headImage;
        private String imUser;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getHeadImage() {
            return headImage;
        }

        public void setHeadImage(String headImage) {
            this.headImage = headImage;
        }

        public String getImUser() {
            return imUser;
        }

        public void setImUser(String imUser) {
            this.imUser = imUser;
        }
    }
}
