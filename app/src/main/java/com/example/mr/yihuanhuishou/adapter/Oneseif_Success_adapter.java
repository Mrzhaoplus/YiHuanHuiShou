package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Oneseif_Success_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Order_Daijiedan_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/10.
 */

public class Oneseif_Success_adapter extends RecyclerView.Adapter<Oneseif_Success_adapter.Holder> {
    Context context;
    List<Order_Daijiedan_Bean.DataBean>list=new ArrayList<>();

    public Oneseif_Success_adapter(Context context, List<Order_Daijiedan_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.oneseif_success_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.number.setText(list.get(position).getOrderNumber());
        holder.sort.setText(list.get(position).getVarieties());
        holder.price.setText(list.get(position).getTotalPrice()+"元");
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Oneseif_Success_DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
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
        public TextView price;
        public TextView sort;
        public TextView state;
        public TextView number;
        public TextView firm;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            firm = view.findViewById(R.id.firm);
            number = view.findViewById(R.id.bianhao);
            state = view.findViewById(R.id.state);
            sort = view.findViewById(R.id.sort);
            price = view.findViewById(R.id.price);
        }
    }
}
