package com.example.mr.yihuanhuishou.jsonbean;

/**
 * Created by Mr赵 on 2018/8/3.
 */

public class ZFB_zhifu_Bean {

    /**
     * msg : 请求成功
     * code : 200
     * datas : alipay_sdk=alipay-sdk-java-3.1.0&app_id=2018071760702316&biz_content=%7B%22body%22%3A%22%E6%8A%BC%E9%87%91%E7%9A%84%E7%BC%B4%E7%BA%B3%22%2C%22out_trade_no%22%3A%2220180720118110231206%22%2C%22product_code%22%3A%22QUICK_MSECURITY_PAY%22%2C%22subject%22%3A%22%E6%8A%BC%E9%87%91%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%221%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=%2Falipay%2FcompanyReply&sign=e%2F4fj%2FU4MEhWZ13mK6Abi3IUczeEK8uvz83j7NKQlkQhYKeE0O6xzLL%2FaH%2BeRWgaz0x84J008pMjFU1BlioCqLIvCvRlhzvK7lrZwbgsAeqd0%2BkAib7zNbOEhlXXpiqxPcejjnSdJPwjESEX9RuR5xCXs9sSxEeAyV8NpHjPBTnwAjZM2V9h1kV2RUPdaX5c0H6ajT6RFbXA%2FAub9PyVXYfFsPlY0ipQhNuMFZyC%2B8XdIn8LZ7KppvAFxB6ZCgLYlNmzcC0jerzEoGreXnOirhqS0scNMowPHW5mhMaJxjxI3pgGwjMNq2CLMdeb6cUqIQLQiX%2FXijNFhP75xFiL4w%3D%3D&sign_type=RSA2&timestamp=2018-08-03+18%3A44%3A15&version=1.0
     */

    private String msg;
    private String code;
    private String datas;

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

    public String getDatas() {
        return datas;
    }

    public void setDatas(String datas) {
        this.datas = datas;
    }
}
