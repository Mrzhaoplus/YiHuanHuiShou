package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/26.
 */

public class Chepai_Cord_itme_bean {

    /**
     * code : 200
     * msg : 车牌号信息列表
     * date : {"usePlate":{"id":17,"driverPlateNumber":"冀AD8U86","isUse":"Y","driverId":4},"notPlatelist":[{"id":3,"driverPlateNumber":"1234567","isUse":"N","driverId":4}]}
     */

    private int code;
    private String msg;
    private DataBean data;

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

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * usePlate : {"id":17,"driverPlateNumber":"冀AD8U86","isUse":"Y","driverId":4}
         * notPlatelist : [{"id":3,"driverPlateNumber":"1234567","isUse":"N","driverId":4}]
         */

        private UsePlateBean usePlate;
        private List<NotPlatelistBean> notPlatelist;

        public UsePlateBean getUsePlate() {
            return usePlate;
        }

        public void setUsePlate(UsePlateBean usePlate) {
            this.usePlate = usePlate;
        }

        public List<NotPlatelistBean> getNotPlatelist() {
            return notPlatelist;
        }

        public void setNotPlatelist(List<NotPlatelistBean> notPlatelist) {
            this.notPlatelist = notPlatelist;
        }

        public static class UsePlateBean {
            /**
             * id : 17
             * driverPlateNumber : 冀AD8U86
             * isUse : Y
             * driverId : 4
             */

            private int id;
            private String driverPlateNumber;
            private String isUse;
            private int driverId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDriverPlateNumber() {
                return driverPlateNumber;
            }

            public void setDriverPlateNumber(String driverPlateNumber) {
                this.driverPlateNumber = driverPlateNumber;
            }

            public String getIsUse() {
                return isUse;
            }

            public void setIsUse(String isUse) {
                this.isUse = isUse;
            }

            public int getDriverId() {
                return driverId;
            }

            public void setDriverId(int driverId) {
                this.driverId = driverId;
            }
        }

        public static class NotPlatelistBean {
            /**
             * id : 3
             * driverPlateNumber : 1234567
             * isUse : N
             * driverId : 4
             */

            private int id;
            private String driverPlateNumber;
            private String isUse;
            private int driverId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getDriverPlateNumber() {
                return driverPlateNumber;
            }

            public void setDriverPlateNumber(String driverPlateNumber) {
                this.driverPlateNumber = driverPlateNumber;
            }

            public String getIsUse() {
                return isUse;
            }

            public void setIsUse(String isUse) {
                this.isUse = isUse;
            }

            public int getDriverId() {
                return driverId;
            }

            public void setDriverId(int driverId) {
                this.driverId = driverId;
            }
        }
    }
}
