package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.bean.Mine_bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/6/3.
 */

public class Mine_Adapter extends BaseAdapter {
    Context context;
    List<Mine_bean> list;

    public Mine_Adapter(Context context, List<Mine_bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewholder vh;
        if(view==null){
            vh=new Viewholder();
            view = View.inflate(context, R.layout.mine_icon_adapter, null);
            vh.img_view = view.findViewById(R.id.img_view);
            vh.text_view = view.findViewById(R.id.text_view);
            view.setTag(vh);
        }else{
            vh= (Viewholder) view.getTag();
        }
        Glide.with(context).load(list.get(i).getImg_view()).into(vh.img_view);
        vh.text_view.setText(list.get(i).getText_view());
        return view;
    }

    class Viewholder{
        ImageView img_view;
        TextView text_view;
    }




}
