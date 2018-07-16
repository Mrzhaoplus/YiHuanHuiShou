package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.HomeCompanyDetailActivity;
import com.example.mr.yihuanhuishou.activity.Home_detailActivity;
import com.example.mr.yihuanhuishou.activity.LogonActivity;
import com.example.mr.yihuanhuishou.activity.Shenhe_Frim_DetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/21.
 */

public class Tongguo_Fragment_Adapter extends RecyclerView.Adapter<Tongguo_Fragment_Adapter.Viewholder> {
    private  Context context;
    private List<String> list=new ArrayList<>();
    private int state;

    public Tongguo_Fragment_Adapter(Context context, List<String> list, int state) {
        this.context = context;
        this.list = list;
        this.state = state;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.tongguo_adapter, parent, false);
        Viewholder viewholder = new Viewholder(inflate);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state==1){
                    context.startActivity(new Intent(context,HomeCompanyDetailActivity.class));
                }else{
                    context.startActivity(new Intent(context,Shenhe_Frim_DetailsActivity.class));
                }

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public View view;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
