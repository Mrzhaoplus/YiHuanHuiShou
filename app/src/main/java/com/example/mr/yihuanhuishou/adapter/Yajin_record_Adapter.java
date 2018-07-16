package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/20.
 */

public class Yajin_record_Adapter extends RecyclerView.Adapter<Yajin_record_Adapter.Viewholder> {
   Context context;
   List<String>list=new ArrayList<>();

    public Yajin_record_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yajin_record_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Viewholder holder, int position) {

    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder  extends RecyclerView.ViewHolder{
        public Viewholder(View itemView) {
            super(itemView);
        }
    }
}
