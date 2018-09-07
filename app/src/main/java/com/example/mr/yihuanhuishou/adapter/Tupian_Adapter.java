package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.TPDetailsActivity;
import com.example.mr.yihuanhuishou.bean.Info;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Xueqiu_Adapter_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/7/19.
 */

public class Tupian_Adapter extends RecyclerView.Adapter<Tupian_Adapter.Viewholder> {
    Context context;
    List<Xueqiu_Adapter_Bean.DataBean.EecWasteinfoBean.ListBean> list=new ArrayList<>();
    List<String> mlist=new ArrayList<>();
    public Tupian_Adapter(Context context, List<Xueqiu_Adapter_Bean.DataBean.EecWasteinfoBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.xuqiu_adapter, parent, false);
        Viewholder viewholder = new Viewholder(inflate);
        return viewholder;
    }
    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        String content = list.get(position).getContent();
        Glide.with(context).load(content).into(holder.tu);
         mlist.add(list.get(position).getContent());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, TPDetailsActivity.class);
                intent.putExtra("size",list.size());
                Info info = new Info();
                info.imgs=mlist;
                intent.putExtra("imgs",  info);

                intent.putExtra("position",position);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Viewholder extends RecyclerView.ViewHolder {

        public ImageView tu;
        public View view;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            tu = itemView.findViewById(R.id.tu);
        }
    }
}
