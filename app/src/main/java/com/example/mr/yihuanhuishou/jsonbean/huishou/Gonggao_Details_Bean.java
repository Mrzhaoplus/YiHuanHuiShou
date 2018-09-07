package com.example.mr.yihuanhuishou.jsonbean.huishou;

/**
 * Created by Mr赵 on 2018/7/25.
 */

public class Gonggao_Details_Bean {

    /**
     * msg : 成功！
     * code : 200
     * data : {"id":2,"title":"标题2","state":"0","content":"内容2"}
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
         * title : 标题2
         * state : 0
         * content : 内容2
         */

        private int id;
        private String title;
        private String state;
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

        public String getState() {
            return state;
        }

        public void setState(String state) {
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
