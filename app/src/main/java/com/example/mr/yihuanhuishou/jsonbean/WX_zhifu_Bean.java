package com.example.mr.yihuanhuishou.jsonbean;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Mr赵 on 2018/8/1.
 */

public class WX_zhifu_Bean {

    /**
     * msg : 统一下单成功!
     * code : 200
     * datas : {"package":"Sign=WXPay","appid":"wxf32e64cfe4bd433c","sign":"2559BFD64620D817092E15E583730659","partnerid":"1510221461","prepayid":"wx01171559517796e1e25e0c8e3522670281","noncestr":"W6fOIi3mlO1O0Eu5SbgM70k719WJO22","timestamp":"1533114959554"}
     */

    private String msg;
    private String code;
    private DatasBean datas;

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

    public DatasBean getDatas() {
        return datas;
    }

    public void setDatas(DatasBean datas) {
        this.datas = datas;
    }

    public static class DatasBean {
        /**
         * package : Sign=WXPay
         * appid : wxf32e64cfe4bd433c
         * sign : 2559BFD64620D817092E15E583730659
         * partnerid : 1510221461
         * prepayid : wx01171559517796e1e25e0c8e3522670281
         * noncestr : W6fOIi3mlO1O0Eu5SbgM70k719WJO22
         * timestamp : 1533114959554
         */

        @SerializedName("package")
        private String packageX;
        private String appid;
        private String sign;
        private String partnerid;
        private String prepayid;
        private String noncestr;
        private String timestamp;

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }
    }
}
