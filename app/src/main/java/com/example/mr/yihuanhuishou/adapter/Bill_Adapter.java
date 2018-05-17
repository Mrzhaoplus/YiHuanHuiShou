package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/3.
 */

public class Bill_Adapter extends RecyclerView.Adapter<Bill_Adapter.Holder>{
    Context context;
    List<String> list=new ArrayList<>();
    public Bill_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<10;i++){
            list.add("");
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.qiangdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Intent();
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        public  TextView name;
        public LinearLayout qiangdan;
        public TextView address;

        public Holder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            qiangdan = itemView.findViewById(R.id.qiangdan);
            address = itemView.findViewById(R.id.address);
        }
    }
}
