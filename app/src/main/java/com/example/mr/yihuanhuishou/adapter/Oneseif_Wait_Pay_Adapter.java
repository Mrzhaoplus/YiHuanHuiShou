package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Oneseif_Wait_pay_detailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.Order_Daijiedan_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class Oneseif_Wait_Pay_Adapter extends RecyclerView.Adapter<Oneseif_Wait_Pay_Adapter.Holder>{
       private Context context;
       List<Order_Daijiedan_Bean.DataBean>list=new ArrayList<>();

    public Oneseif_Wait_Pay_Adapter(Context context, List<Order_Daijiedan_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.oneseif_wait_pay_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }
    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.number.setText(list.get(position).getOrderNumber());
        holder.sort.setText(list.get(position).getVarieties());
        holder.price.setText(list.get(position).getTotalPrice()+"元");
        //点击跳转详情页
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Oneseif_Wait_pay_detailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder  extends RecyclerView.ViewHolder{

        public TextView firm;
        public View view;
        public TextView number;
        public TextView state;
        public TextView sort;
        public TextView price;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            number = itemView.findViewById(R.id.bianhao);
            state = itemView.findViewById(R.id.state);
            sort = itemView.findViewById(R.id.sort);
            price = itemView.findViewById(R.id.price);
            firm = itemView.findViewById(R.id.firm);
        }
    }
}
