package com.example.mr.yihuanhuishou.utils;

import android.app.Activity;
import android.view.View;

/**
 * Created by Mr赵 on 2018/5/2.
 */

public class MyContants {
    public static String FILENAME = "config.xml";
    public static String BASEURL="http://47.93.45.38/test/api.php?";
    public static String BASEURLS="http://114.215.83.139/danche/api.php?";
    public static String PHOTO="http://47.93.45.38";
    public static String HTML="http://47.93.45.38/server/html5/";

    public static  void windows(Activity activity){

        activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

    }
}
