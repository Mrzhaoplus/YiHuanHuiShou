package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/19.
 */

public class Massage_Bean {

    /**
     * pageNo : 1
     * pageCount : 10
     * start : 0
     * end : 10
     * totalCount : 2
     * totalPageNo : 1
     * dataList : [{"id":2,"type":"2","sender":1,"receiver":35,"content":"那一个人","state":"1","createDate":1531911403000,"senderSign":"0","receiverSign":"1"},{"id":1,"type":"1","sender":1,"receiver":35,"content":"内容","state":"0","createDate":1531910746000,"senderSign":"0","receiverSign":"1"}]
     * code : 200
     * msg : 成功！
     */

    private int pageNo;
    private int pageCount;
    private int start;
    private int end;
    private int totalCount;
    private int totalPageNo;
    private int code;
    private String msg;
    private List<DataListBean> dataList;

    public int getPageNo() {
        return pageNo;
    }

    public void setPageNo(int pageNo) {
        this.pageNo = pageNo;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPageNo() {
        return totalPageNo;
    }

    public void setTotalPageNo(int totalPageNo) {
        this.totalPageNo = totalPageNo;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<DataListBean> getDataList() {
        return dataList;
    }

    public void setDataList(List<DataListBean> dataList) {
        this.dataList = dataList;
    }

    public static class DataListBean {
        /**
         * id : 2
         * type : 2
         * sender : 1
         * receiver : 35
         * content : 那一个人
         * state : 1
         * createDate : 1531911403000
         * senderSign : 0
         * receiverSign : 1
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
    }
}
