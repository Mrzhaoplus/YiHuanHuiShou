package com.example.mr.yihuanhuishou.bean;

/**
 * Created by Mr赵 on 2018/6/3.
 */

public class Mine_bean {
    private int img_view;
    private String text_view;

    public Mine_bean(int img_view, String text_view) {
        this.img_view = img_view;
        this.text_view = text_view;
    }

    public int getImg_view() {
        return img_view;
    }

    public void setImg_view(int img_view) {
        this.img_view = img_view;
    }

    public String getText_view() {
        return text_view;
    }

    public void setText_view(String text_view) {
        this.text_view = text_view;
    }
}
