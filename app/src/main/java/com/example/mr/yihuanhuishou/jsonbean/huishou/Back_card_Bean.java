package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/17.
 */

public class Back_card_Bean  {

    /**
     * msg : 查询成功
     * code : 200
     * data : [{"id":5,"userId":34,"userType":"1","type":3,"cardNumber":"6217000130024404344","name":"1","address":null,"branchName":null,"state":"1","createDate":1531795526000,"eecBankcardtype":{"id":3,"bankName":"中国银行","bankId":1026,"bankImage":"https://ech.oss-cn-beijing.aliyuncs.com/bank_logo/%E4%B8%AD%E5%9B%BD%E9%93%B6%E8%A1%8C.png","code":"BOC"}},{"id":4,"userId":34,"userType":"1","type":1,"cardNumber":"6217000130024404344","name":"1","address":null,"branchName":null,"state":"1","createDate":1531794815000,"eecBankcardtype":{"id":1,"bankName":"工商银行","bankId":1002,"bankImage":"https://ech.oss-cn-beijing.aliyuncs.com/bank_logo/%E5%B7%A5%E5%95%86%E9%93%B6%E8%A1%8C.png","code":"ICBC"}},{"id":3,"userId":34,"userType":"1","type":4,"cardNumber":"6217000130024404344","name":"1","address":null,"branchName":null,"state":"1","createDate":1531794800000,"eecBankcardtype":{"id":4,"bankName":"建设银行","bankId":1003,"bankImage":"https://ech.oss-cn-beijing.aliyuncs.com/bank_logo/%E5%BB%BA%E8%AE%BE%E9%93%B6%E8%A1%8C.png","code":"CCB"}}]
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
         * id : 5
         * userId : 34
         * userType : 1
         * type : 3
         * cardNumber : 6217000130024404344
         * name : 1
         * address : null
         * branchName : null
         * state : 1
         * createDate : 1531795526000
         * eecBankcardtype : {"id":3,"bankName":"中国银行","bankId":1026,"bankImage":"https://ech.oss-cn-beijing.aliyuncs.com/bank_logo/%E4%B8%AD%E5%9B%BD%E9%93%B6%E8%A1%8C.png","code":"BOC"}
         */

        private int id;
        private int userId;
        private String userType;
        private int type;
        private String cardNumber;
        private String name;
        private Object address;
        private Object branchName;
        private String state;
        private long createDate;
        private EecBankcardtypeBean eecBankcardtype;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getCardNumber() {
            return cardNumber;
        }

        public void setCardNumber(String cardNumber) {
            this.cardNumber = cardNumber;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Object getAddress() {
            return address;
        }

        public void setAddress(Object address) {
            this.address = address;
        }

        public Object getBranchName() {
            return branchName;
        }

        public void setBranchName(Object branchName) {
            this.branchName = branchName;
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

        public EecBankcardtypeBean getEecBankcardtype() {
            return eecBankcardtype;
        }

        public void setEecBankcardtype(EecBankcardtypeBean eecBankcardtype) {
            this.eecBankcardtype = eecBankcardtype;
        }

        public static class EecBankcardtypeBean {
            /**
             * id : 3
             * bankName : 中国银行
             * bankId : 1026
             * bankImage : https://ech.oss-cn-beijing.aliyuncs.com/bank_logo/%E4%B8%AD%E5%9B%BD%E9%93%B6%E8%A1%8C.png
             * code : BOC
             */

            private int id;
            private String bankName;
            private int bankId;
            private String bankImage;
            private String code;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getBankName() {
                return bankName;
            }

            public void setBankName(String bankName) {
                this.bankName = bankName;
            }

            public int getBankId() {
                return bankId;
            }

            public void setBankId(int bankId) {
                this.bankId = bankId;
            }

            public String getBankImage() {
                return bankImage;
            }

            public void setBankImage(String bankImage) {
                this.bankImage = bankImage;
            }

            public String getCode() {
                return code;
            }

            public void setCode(String code) {
                this.code = code;
            }
        }
    }
}
