package com.example.mr.yihuanhuishou.base;

/**
 * Created by Mr赵 on 2018/6/5.
 */

public class Recycle_price_bean {
    private boolean flag;
    private String goods;

    public Recycle_price_bean(boolean flag, String goods) {
        this.flag = flag;
        this.goods = goods;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getGoods() {
        return goods;
    }

    public void setGoods(String goods) {
        this.goods = goods;
    }
}
