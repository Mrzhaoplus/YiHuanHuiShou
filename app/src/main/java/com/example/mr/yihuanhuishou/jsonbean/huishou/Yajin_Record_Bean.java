package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/18.
 */

public class Yajin_Record_Bean {

    /**
     * msg : 成功！
     * code : 200
     * data : [{"id":29,"recyclersId":30,"type":"1","deposit":-199,"createDate":1531878660000},{"id":28,"recyclersId":30,"type":"0","deposit":199,"createDate":1531878656000},{"id":27,"recyclersId":30,"type":"1","deposit":-199,"createDate":1531878624000},{"id":26,"recyclersId":30,"type":"0","deposit":199,"createDate":1531878386000},{"id":25,"recyclersId":30,"type":"1","deposit":-398,"createDate":1531878375000},{"id":24,"recyclersId":30,"type":"0","deposit":199,"createDate":1531878358000},{"id":23,"recyclersId":30,"type":"0","deposit":199,"createDate":1531877797000},{"id":22,"recyclersId":30,"type":"1","deposit":-597,"createDate":1531877781000},{"id":21,"recyclersId":30,"type":"0","deposit":199,"createDate":1531877709000},{"id":20,"recyclersId":30,"type":"0","deposit":199,"createDate":1531876974000},{"id":19,"recyclersId":30,"type":"0","deposit":199,"createDate":1531876966000}]
     */

    private String msg;
    private int code;
    private List<DataBean> data;

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

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * id : 29
         * recyclersId : 30
         * type : 1
         * deposit : -199
         * createDate : 1531878660000
         */

        private int id;
        private int recyclersId;
        private String type;
        private String deposit;
        private long createDate;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getRecyclersId() {
            return recyclersId;
        }

        public void setRecyclersId(int recyclersId) {
            this.recyclersId = recyclersId;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getDeposit() {
            return deposit;
        }

        public void setDeposit(String deposit) {
            this.deposit = deposit;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }
    }
}
