package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Distrbstion_DetailActivity;
import com.example.mr.yihuanhuishou.activity.Driver_dingdan_DetailsActivity;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/7.
 */

public class Driver_peisong_adapter extends RecyclerView.Adapter<Driver_peisong_adapter.Holder> {
    Context context;
    List<String>list=new ArrayList<>();
    public Driver_peisong_adapter(Context context) {

        this.context = context;
        data();
    }
    private void data() {
       for(int i=0;i<8;i++){
            list.add("");
        }
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_peisong_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(Holder holder, final int position) {






        //点击事件
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Distrbstion_DetailActivity.class);
                context.startActivity(intent);

            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Holder extends RecyclerView.ViewHolder{
        public View view;
        public TextView bian;
        public TextView state;
        public TextView sort;
        public TextView zhong;
        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            bian = itemView.findViewById(R.id.jie_bh);
            state = itemView.findViewById(R.id.jie_state);
            sort = itemView.findViewById(R.id.jie_lx);
            zhong = itemView.findViewById(R.id.jie_zl);
        }
    }
}
