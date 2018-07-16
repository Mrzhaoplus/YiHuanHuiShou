package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/22.
 */

public class Pop_Adapter extends BaseAdapter {
    Context context;
    List<String> list=new ArrayList<>();

    public Pop_Adapter(Context context, List<String> list) {
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
            view = View.inflate(context, R.layout.pop_adapter, null);
            vh=new Viewholder();
            vh.title = view.findViewById(R.id.title);
            view.setTag(vh);
        }else{
            vh= (Viewholder) view.getTag();
        }
        vh.title.setText(list.get(i));
        return view;
    }
    class Viewholder{
        TextView title;
    }

}
