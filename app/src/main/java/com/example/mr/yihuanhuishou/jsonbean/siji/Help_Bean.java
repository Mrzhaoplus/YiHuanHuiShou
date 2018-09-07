package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/30.
 */

public class Help_Bean {

    /**
     * msg : 查询成功
     * code : 200
     * data : [{"id":1,"title":"如何成为最伟大的人","content":"工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，"}]
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
         * title : 如何成为最伟大的人
         * content : 工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，工地搬砖，
         */

        private int id;
        private String title;
        private String content;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
