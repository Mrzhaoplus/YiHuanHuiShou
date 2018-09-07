package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/19.
 */

public class XitongMassage_Bean {

    /**
     * pageNo : 1
     * pageCount : 10
     * start : 0
     * end : 10
     * totalCount : 2
     * totalPageNo : 1
     * dataList : [{"id":2,"title":"标题2","content":"内容2","state":"0","isRead":0},{"id":1,"title":"标题1","content":"内容1","state":"0","isRead":1}]
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
         * title : 标题2
         * content : 内容2
         * state : 0
         * isRead : 0
         */

        private int id;
        private String title;
        private String content;
        private String state;
        private int isRead;

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

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public int getIsRead() {
            return isRead;
        }

        public void setIsRead(int isRead) {
            this.isRead = isRead;
        }
    }
}
