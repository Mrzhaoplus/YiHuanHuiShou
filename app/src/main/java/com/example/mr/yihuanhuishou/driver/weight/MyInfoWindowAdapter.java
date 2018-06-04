package com.example.mr.yihuanhuishou.driver.weight;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.amap.api.maps2d.AMap;
import com.amap.api.maps2d.model.Marker;
import com.example.mr.yihuanhuishou.R;

/**
 * Created by power on 2018/5/21.
 */

public class MyInfoWindowAdapter implements AMap.InfoWindowAdapter {
    View infoWindow = null;
    Context context;
    String phone;

    public MyInfoWindowAdapter(Context context) {
        this.context = context;
    }

    @Override
    public View getInfoWindow(Marker marker) {
        if(infoWindow == null) {
            infoWindow = LayoutInflater.from(context).inflate(R.layout.view_qipao, null);
        }
        render(marker, infoWindow,phone);
        return infoWindow;
    }

    @Override
    public View getInfoContents(Marker marker) {
        return null;
    }

    /**
     * 自定义infowinfow窗口
     */
    public void render(Marker marker, View view,String phone) {
    //如果想修改自定义Infow中内容，请通过view找到它并修改
        TextView phoneTv = view.findViewById(R.id.item_phone_tv);
        phoneTv.setText(phone);
        this.phone = phone;
    }
}
