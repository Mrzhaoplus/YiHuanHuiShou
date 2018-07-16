package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
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

public class Driver_all_adapter extends RecyclerView.Adapter<Driver_all_adapter.Holder> {

    Context context;
    List<Order_Daijiedan_Bean.DataBean>list=new ArrayList<>();
    private QuXiao click;

    public Driver_all_adapter(Context context,List<Order_Daijiedan_Bean.DataBean>list) {
        this.context = context;
        this.list=list;
    }
    public void getclick(QuXiao jiekou){
        this.click=jiekou;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_all_adapter, parent, false);
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
                click.getcanleClick(position);
            }
        });

        final String state = list.get(position).getState();
        if(state.equals("0")){
            holder.state.setText("待接单");
            holder.til_price.setVisibility(View.GONE);
        }else if(state.equals("1")){
            holder.state.setText("取货中");
            holder.til_price.setVisibility(View.GONE);
        }else if(state.equals("3")){
            holder.state.setText("配送中");
            holder.til_price.setVisibility(View.GONE);
            holder.clo_bill.setVisibility(View.GONE);
        }else if(state.equals("4")){
            holder.state.setText("配送完");
            holder.til_price.setVisibility(View.GONE);
            holder.clo_bill.setVisibility(View.GONE);
        }else if(state.equals("11")){
            holder.state.setText("待支付");
            holder.clo_bill.setVisibility(View.GONE);
        }else if(state.equals("12")) {
            holder.state.setText("已支付");
            holder.clo_bill.setVisibility(View.GONE);
        }else if(state.equals("5")){
            holder.state.setText("已取消");
            holder.til_price.setVisibility(View.GONE);
            holder.clo_bill.setVisibility(View.GONE);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int id = list.get(position).getId();
                if(state.equals("0")){
                    Intent intent = new Intent(context, Driver_dingdan_DetailsActivity.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }else if(state.equals("1")){
                    Intent intent = new Intent(context, Clame_Goods_DetailsActivity.class);
                    context.startActivity(intent);
                }else if(state.equals("3")){
                    Intent intent = new Intent(context, Distrbstion_DetailActivity.class);
                    context.startActivity(intent);
                }else if(state.equals("4")){
                    Intent intent = new Intent(context, Distribution_success_DetailsActivity.class);
                    context.startActivity(intent);
                }else if(state.equals("11")){
                    Intent intent = new Intent(context,Driver_Wait_zhifu_DetailsActivity.class);
                    context.startActivity(intent);
                }else if(state.equals("12")){
                    Intent intent = new Intent(context,Driver_zhifu_success_DetailsActivity.class);
                    context.startActivity(intent);
                }else if(state.equals("5")){
                    Intent intent = new Intent(context, Driver_Cancle_DetailsActivity.class);
                    intent.putExtra("id",id);
                    context.startActivity(intent);
                }
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
        public TextView price;
        public LinearLayout til_price;
        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            bian = itemView.findViewById(R.id.jie_bh);
            state = itemView.findViewById(R.id.jie_state);
            sort = itemView.findViewById(R.id.jie_lx);
            zhong = itemView.findViewById(R.id.jie_zl);
            quxiao = itemView.findViewById(R.id.jie_qx);
            til_price = itemView.findViewById(R.id.til_price);
            price = itemView.findViewById(R.id.jie_price);
            clo_bill = itemView.findViewById(R.id.clo_bill);
        }
    }
    public interface QuXiao{
        void getcanleClick(int postion);
    }
}
