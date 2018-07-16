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
import com.example.mr.yihuanhuishou.activity.Clame_Goods_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Distrbstion_DetailActivity;
import com.example.mr.yihuanhuishou.activity.Distribution_success_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Driver_Cancle_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Driver_Wait_zhifu_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Driver_dingdan_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Driver_zhifu_success_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.Order_Daijiedan_Bean;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/7.
 */

public class Driver_daijiedan_adapter extends RecyclerView.Adapter<Driver_daijiedan_adapter.Holder> {
    Context context;
    List<Order_Daijiedan_Bean.DataBean>list=new ArrayList<>();
    public Driver_daijiedan_adapter(Context context,List<Order_Daijiedan_Bean.DataBean>list) {
        this.context = context;
        this.list=list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_daijiedan_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.bian.setText(list.get(position).getOrderNumber());
        holder.sort.setText(list.get(position).getVarieties());
        holder.zhong.setText(list.get(position).getCount()+""+list.get(position).getUnit());
       //取消
        holder.quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context,"取消订单");
            }
        });
        //点击事件
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Driver_dingdan_DetailsActivity.class);
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
        public LinearLayout quxiao;
        public RelativeLayout clo_bill;
        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            bian = itemView.findViewById(R.id.jie_bh);
            state = itemView.findViewById(R.id.jie_state);
            sort = itemView.findViewById(R.id.jie_lx);
            zhong = itemView.findViewById(R.id.jie_zl);
            quxiao = itemView.findViewById(R.id.jie_qx);
            clo_bill = itemView.findViewById(R.id.clo_bill);
        }
    }
}
