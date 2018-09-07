package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/8/7.
 */

public class Firm_Message_Itme_Bean {

    /**
     * code : 200
     * data : [{"id":19,"type":"3","sender":1,"receiver":4,"content":"2222","state":"1","createDate":1533528554000,"senderSign":"3","receiverSign":"2","status":2,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null},{"id":27,"type":"3","sender":1,"receiver":4,"content":"我很无奈","state":"0","createDate":1533629930000,"senderSign":"3","receiverSign":"2","status":0,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null},{"id":28,"type":"3","sender":1,"receiver":4,"content":"我很无奈","state":"0","createDate":1533629930000,"senderSign":"3","receiverSign":"2","status":1,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null},{"id":29,"type":"3","sender":1,"receiver":4,"content":"我很无奈","state":"0","createDate":1533629930000,"senderSign":"3","receiverSign":"2","status":4,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null},{"id":30,"type":"3","sender":1,"receiver":4,"content":"我很无奈","state":"0","createDate":1533629930000,"senderSign":"3","receiverSign":"2","status":5,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null}]
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
         * id : 19
         * type : 3
         * sender : 1
         * receiver : 4
         * content : 2222
         * state : 1
         * createDate : 1533528554000
         * senderSign : 3
         * receiverSign : 2
         * status : 2
         * idCard :
         * companyName :
         * pageNum : null
         * pageSize : null
         * driver : null
         * recyclers : null
         */

        private int id;
        private String type;
        private int sender;
        private int receiver;
        private String content;
        private String state;
        private long createDate;
        private String senderSign;
        private String receiverSign;
        private int status;
        private String idCard;
        private String companyName;
        private Object pageNum;
        private Object pageSize;
        private Object driver;
        private Object recyclers;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getSender() {
            return sender;
        }

        public void setSender(int sender) {
            this.sender = sender;
        }

        public int getReceiver() {
            return receiver;
        }

        public void setReceiver(int receiver) {
            this.receiver = receiver;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getSenderSign() {
            return senderSign;
        }

        public void setSenderSign(String senderSign) {
            this.senderSign = senderSign;
        }

        public String getReceiverSign() {
            return receiverSign;
        }

        public void setReceiverSign(String receiverSign) {
            this.receiverSign = receiverSign;
        }

        public int getStatus() {
            return status;
        }

        public void setStatus(int status) {
            this.status = status;
        }

        public String getIdCard() {
            return idCard;
        }

        public void setIdCard(String idCard) {
            this.idCard = idCard;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public Object getPageNum() {
            return pageNum;
        }

        public void setPageNum(Object pageNum) {
            this.pageNum = pageNum;
        }

        public Object getPageSize() {
            return pageSize;
        }

        public void setPageSize(Object pageSize) {
            this.pageSize = pageSize;
        }

        public Object getDriver() {
            return driver;
        }

        public void setDriver(Object driver) {
            this.driver = driver;
        }

        public Object getRecyclers() {
            return recyclers;
        }

        public void setRecyclers(Object recyclers) {
            this.recyclers = recyclers;
        }
    }
}
