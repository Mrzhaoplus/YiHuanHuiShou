package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Massage_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/14.
 */

public class Jumin_msg_Adapter extends RecyclerView.Adapter<Jumin_msg_Adapter.Holder> {
    private Context context;
    List<Massage_Bean.DataListBean>list=new ArrayList<>();

    public Jumin_msg_Adapter(Context context, List<Massage_Bean.DataListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jumin_msg_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        String state = list.get(position).getState();
        if(state.equals("0")){
            holder.dian.setVisibility(View.VISIBLE);
        }else if(state.equals("1")){
            holder.dian.setVisibility(View.GONE);
        }
        holder.title.setText(list.get(position).getContent());
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.dian.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ImageView dian;
        public TextView title;
        public View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            title = view.findViewById(R.id.title);
            dian = view.findViewById(R.id.dian);
        }
    }
}
