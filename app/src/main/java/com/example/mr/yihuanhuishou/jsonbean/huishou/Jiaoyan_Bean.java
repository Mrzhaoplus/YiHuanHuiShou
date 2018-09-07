package com.example.mr.yihuanhuishou.jsonbean.huishou;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/17.
 */

public class Jiaoyan_Bean {

    /**
     * msg : 校验成功
     * code : 200
     * data : {"validated":false,"key":"62170001300244043445","stat":"ok","messages":[{"errorCodes":"PARAM_ILLEGAL","name":"cardNo"}]}
     * title : null
     */

    private String msg;
    private String code;
    private DataBean data;
    private String title;

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public static class DataBean {
        /**
         * validated : false
         * key : 62170001300244043445
         * stat : ok
         * messages : [{"errorCodes":"PARAM_ILLEGAL","name":"cardNo"}]
         */

        private boolean validated;
        private String key;
        private String stat;
        private String bank;
        private String cardType;
        private List<MessagesBean> messages;

        public String getBank() {
            return bank;
        }

        public void setBank(String bank) {
            this.bank = bank;
        }

        public String getCardType() {
            return cardType;
        }

        public void setCardType(String cardType) {
            this.cardType = cardType;
        }

        public boolean isValidated() {
            return validated;
        }

        public void setValidated(boolean validated) {
            this.validated = validated;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getStat() {
            return stat;
        }

        public void setStat(String stat) {
            this.stat = stat;
        }

        public List<MessagesBean> getMessages() {
            return messages;
        }

        public void setMessages(List<MessagesBean> messages) {
            this.messages = messages;
        }

        public static class MessagesBean {
            /**
             * errorCodes : PARAM_ILLEGAL
             * name : cardNo
             */

            private String errorCodes;
            private String name;

            public String getErrorCodes() {
                return errorCodes;
            }

            public void setErrorCodes(String errorCodes) {
                this.errorCodes = errorCodes;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }
}
