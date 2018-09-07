package com.example.mr.yihuanhuishou.jsonbean.siji;

import java.util.List;

/**
 * Created by Mr赵 on 2018/8/7.
 */

public class Sendpackgr_Bean {

    private DataList list;

    public Sendpackgr_Bean(DataList list) {
        this.list = list;
    }

    public DataList getList() {
        return list;
    }

    public void setList(DataList list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Sendpackgr_Bean{" +
                "list=" + list +
                '}';
    }
}
