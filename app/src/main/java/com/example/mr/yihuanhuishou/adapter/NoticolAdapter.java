package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Noticol_DrtailActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XitongMassage_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/7/4.
 */

public class NoticolAdapter extends RecyclerView.Adapter<NoticolAdapter.Viewholder> {
    Context context;
    List<XitongMassage_Bean.DataListBean> list=new ArrayList<>();

    public NoticolAdapter(Context context, List<XitongMassage_Bean.DataListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.noticol_adapter, parent, false);
        Viewholder viewholder = new Viewholder(inflate);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(final Viewholder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.dian.setVisibility(View.GONE);
                Intent intent = new Intent(context, Noticol_DrtailActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        int isRead = list.get(position).getIsRead();
        if(isRead==0){
            holder.dian.setVisibility(View.VISIBLE);
        }else if(isRead==1){
            holder.dian.setVisibility(View.GONE);
        }
        holder.neirong.setText(list.get(position).getTitle());
    }


    @Override
    public int getItemCount()    {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public ImageView dian;
        public TextView neirong;
        public View view;
        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            neirong = view.findViewById(R.id.neirong);
            dian = view.findViewById(R.id.dian);

        }
    }
}
