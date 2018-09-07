package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/8/6.
 */

public class My_Massage_Bean {

    /**
     * code : 200
     * data : {"pageNo":1,"pageCount":10,"start":0,"end":10,"totalCount":2,"totalPageNo":1,"dataList":[{"id":19,"type":"3","sender":1,"receiver":4,"content":"2222","state":"1","createDate":1533528554000,"senderSign":"3","receiverSign":"2","status":2,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null},{"id":18,"type":"3","sender":1,"receiver":4,"content":"邀请加入","state":"0","createDate":1533528166000,"senderSign":"3","receiverSign":"2","status":3,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null}],"code":null,"msg":null}
     */

    private int code;
    private DataBean data;

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
         * pageNo : 1
         * pageCount : 10
         * start : 0
         * end : 10
         * totalCount : 2
         * totalPageNo : 1
         * dataList : [{"id":19,"type":"3","sender":1,"receiver":4,"content":"2222","state":"1","createDate":1533528554000,"senderSign":"3","receiverSign":"2","status":2,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null},{"id":18,"type":"3","sender":1,"receiver":4,"content":"邀请加入","state":"0","createDate":1533528166000,"senderSign":"3","receiverSign":"2","status":3,"idCard":"","companyName":"","pageNum":null,"pageSize":null,"driver":null,"recyclers":null}]
         * code : null
         * msg : null
         */

        private int pageNo;
        private int pageCount;
        private int start;
        private int end;
        private int totalCount;
        private int totalPageNo;
        private Object code;
        private Object msg;
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

        public Object getCode() {
            return code;
        }

        public void setCode(Object code) {
            this.code = code;
        }

        public Object getMsg() {
            return msg;
        }

        public void setMsg(Object msg) {
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
}
