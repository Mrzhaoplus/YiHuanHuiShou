package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/8/2.
 */

public class Baozhuang_all_N_Bean {

    /**
     * code : 200
     * data : {"count":6,"List":[{"list":[{"packingBagList":[{"id":3,"inboundOutbound":false,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":1,"recyclersId":3,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"衣服"},{"packingBagList":[{"id":9,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":2,"recyclersId":1,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"塑料瓶"},{"packingBagList":[{"id":8,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":3,"recyclersId":1,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"手机数码"},{"packingBagList":[{"id":12,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":5,"recyclersId":4,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"金属"}],"CreateDate":1534231586000},{"list":[{"packingBagList":[{"id":10,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":1,"recyclersId":2,"createDate":1530429986000,"releaseDate":1533108395000}],"eecVarietiesName":"衣服"},{"packingBagList":[{"id":11,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":4,"recyclersId":3,"createDate":1530429986000,"releaseDate":1533108395000}],"eecVarietiesName":"鞋类"}],"CreateDate":1530429986000}]}
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
         * count : 6
         * List : [{"list":[{"packingBagList":[{"id":3,"inboundOutbound":false,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":1,"recyclersId":3,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"衣服"},{"packingBagList":[{"id":9,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":2,"recyclersId":1,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"塑料瓶"},{"packingBagList":[{"id":8,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":3,"recyclersId":1,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"手机数码"},{"packingBagList":[{"id":12,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":5,"recyclersId":4,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"金属"}],"CreateDate":1534231586000},{"list":[{"packingBagList":[{"id":10,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":1,"recyclersId":2,"createDate":1530429986000,"releaseDate":1533108395000}],"eecVarietiesName":"衣服"},{"packingBagList":[{"id":11,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":4,"recyclersId":3,"createDate":1530429986000,"releaseDate":1533108395000}],"eecVarietiesName":"鞋类"}],"CreateDate":1530429986000}]
         */

        private int count;
        private java.util.List<ListBeanX> List;

        public int getCount() {
            return count;
        }

        public void setCount(int count) {
            this.count = count;
        }

        public List<ListBeanX> getList() {
            return List;
        }

        public void setList(List<ListBeanX> List) {
            this.List = List;
        }

        public static class ListBeanX {
            /**
             * list : [{"packingBagList":[{"id":3,"inboundOutbound":false,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":1,"recyclersId":3,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"衣服"},{"packingBagList":[{"id":9,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":2,"recyclersId":1,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"塑料瓶"},{"packingBagList":[{"id":8,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":3,"recyclersId":1,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"手机数码"},{"packingBagList":[{"id":12,"inboundOutbound":true,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":5,"recyclersId":4,"createDate":1534231586000,"releaseDate":1533108395000}],"eecVarietiesName":"金属"}]
             * CreateDate : 1534231586000
             */

            private long CreateDate;
            private java.util.List<ListBean> list;

            public long getCreateDate() {
                return CreateDate;
            }

            public void setCreateDate(long CreateDate) {
                this.CreateDate = CreateDate;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * packingBagList : [{"id":3,"inboundOutbound":false,"inboundTime":1532652644000,"outboundTime":1532652647000,"barCode":"14744","driverName":"2","driverIdentityCard":"130124199607070950","packingBoxId":1,"eecvarietiesId":1,"recyclersId":3,"createDate":1534231586000,"releaseDate":1533108395000}]
                 * eecVarietiesName : 衣服
                 */

                private String eecVarietiesName;
                private java.util.List<PackingBagListBean> packingBagList;

                public String getEecVarietiesName() {
                    return eecVarietiesName;
                }

                public void setEecVarietiesName(String eecVarietiesName) {
                    this.eecVarietiesName = eecVarietiesName;
                }

                public List<PackingBagListBean> getPackingBagList() {
                    return packingBagList;
                }

                public void setPackingBagList(List<PackingBagListBean> packingBagList) {
                    this.packingBagList = packingBagList;
                }

                public static class PackingBagListBean {
                    /**
                     * id : 3
                     * inboundOutbound : false
                     * inboundTime : 1532652644000
                     * outboundTime : 1532652647000
                     * barCode : 14744
                     * driverName : 2
                     * driverIdentityCard : 130124199607070950
                     * packingBoxId : 1
                     * eecvarietiesId : 1
                     * recyclersId : 3
                     * createDate : 1534231586000
                     * releaseDate : 1533108395000
                     */

                    private int id;
                    private boolean inboundOutbound;
                    private long inboundTime;
                    private long outboundTime;
                    private String barCode;
                    private String driverName;
                    private String driverIdentityCard;
                    private int packingBoxId;
                    private int eecvarietiesId;
                    private int recyclersId;
                    private long createDate;
                    private long releaseDate;

                    public int getId() {
                        return id;
                    }

                    public void setId(int id) {
                        this.id = id;
                    }

                    public boolean isInboundOutbound() {
                        return inboundOutbound;
                    }

                    public void setInboundOutbound(boolean inboundOutbound) {
                        this.inboundOutbound = inboundOutbound;
                    }

                    public long getInboundTime() {
                        return inboundTime;
                    }

                    public void setInboundTime(long inboundTime) {
                        this.inboundTime = inboundTime;
                    }

                    public long getOutboundTime() {
                        return outboundTime;
                    }

                    public void setOutboundTime(long outboundTime) {
                        this.outboundTime = outboundTime;
                    }

                    public String getBarCode() {
                        return barCode;
                    }

                    public void setBarCode(String barCode) {
                        this.barCode = barCode;
                    }

                    public String getDriverName() {
                        return driverName;
                    }

                    public void setDriverName(String driverName) {
                        this.driverName = driverName;
                    }

                    public String getDriverIdentityCard() {
                        return driverIdentityCard;
                    }

                    public void setDriverIdentityCard(String driverIdentityCard) {
                        this.driverIdentityCard = driverIdentityCard;
                    }

                    public int getPackingBoxId() {
                        return packingBoxId;
                    }

                    public void setPackingBoxId(int packingBoxId) {
                        this.packingBoxId = packingBoxId;
                    }

                    public int getEecvarietiesId() {
                        return eecvarietiesId;
                    }

                    public void setEecvarietiesId(int eecvarietiesId) {
                        this.eecvarietiesId = eecvarietiesId;
                    }

                    public int getRecyclersId() {
                        return recyclersId;
                    }

                    public void setRecyclersId(int recyclersId) {
                        this.recyclersId = recyclersId;
                    }

                    public long getCreateDate() {
                        return createDate;
                    }

                    public void setCreateDate(long createDate) {
                        this.createDate = createDate;
                    }

                    public long getReleaseDate() {
                        return releaseDate;
                    }

                    public void setReleaseDate(long releaseDate) {
                        this.releaseDate = releaseDate;
                    }
                }
            }
        }
    }
}
