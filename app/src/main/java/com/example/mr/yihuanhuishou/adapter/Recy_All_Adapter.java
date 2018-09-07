package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Dingdan_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Wait_wancheng_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Wait_zhifu_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Shangmen_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Recy_All_Adapter extends RecyclerView.Adapter<Recy_All_Adapter.Holder>{
    private Context context;
    private List<Recy_Shangmen_Bean.DataListBean>list=new ArrayList<>();
    public Recy_All_Adapter(Context context, List<Recy_Shangmen_Bean.DataListBean> list) {
        this.context = context;
        this.list = list;
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.wancheng_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.name.setText(list.get(position).getEecWasteinfo().getResidentaddr().getName());
        holder.tel.setText(list.get(position).getEecWasteinfo().getResidentaddr().getPhoneNumber());
        holder.bianhao.setText(list.get(position).getOrderNumber());
        holder.sort.setText(list.get(position).getEecWasteinfo().getVarieties());
       String state = list.get(position).getState();
        if(state.equals("1")){
            holder.xianjin.setVisibility(View.GONE);
            holder.price.setVisibility(View.GONE);
            holder.state.setText("待上门");
            holder.til_weight.setText("废品重量:");
            holder.yihuan.setText(list.get(position).getCount()+""+list.get(position).getEecWasteinfo().getUnit());
        }else if(state.equals("2")){
            holder.xianjin.setVisibility(View.GONE);
            holder.price.setVisibility(View.GONE);
            holder.state.setText("已取消");
            holder.til_weight.setText("废品重量：");
            holder.yihuan.setText(list.get(position).getCount()+""+list.get(position).getEecWasteinfo().getUnit());
        } else if(state.equals("3")){
            holder.xianjin.setVisibility(View.GONE);
            holder.price.setVisibility(View.GONE);
            holder.state.setText("待支付");
            holder.til_weight.setText("现金：");
            holder.yihuan.setText(list.get(position).getTotalMoney()+"元");
        }else if(state.equals("4")){
            holder.state.setText("已完成");
            holder.yihuan.setText(list.get(position).getGiveCurrency()+"元");
            holder.price.setText(list.get(position).getTotalMoney()+"元");
        }
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = list.get(position).getState();
                if(state.equals("1")){
                    Intent intent = new Intent(context, Dingdan_DetailsActivity.class);
                    intent.putExtra("id",list.get(position).getId());
                    intent.putExtra("name",holder.name.getText().toString());
                    intent.putExtra("address",list.get(position).getEecWasteinfo().getResidentaddr().getDetailAddr());
                    context.startActivity(intent);
                }else if(state.equals("3")){
                    Intent intent = new Intent(context, Wait_zhifu_DetailsActivity.class);
                    intent.putExtra("id",list.get(position).getId());
                    intent.putExtra("name",holder.name.getText().toString());
                    intent.putExtra("address",list.get(position).getEecWasteinfo().getResidentaddr().getDetailAddr());
                    context.startActivity(intent);
                }else if(state.equals("4")){
                    Intent intent = new Intent(context, Wait_wancheng_DetailsActivity.class);
                    intent.putExtra("id",list.get(position).getId());
                    intent.putExtra("name",holder.name.getText().toString());
                    intent.putExtra("address",list.get(position).getEecWasteinfo().getResidentaddr().getDetailAddr());
                    context.startActivity(intent);
                }
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        public TextView xianjin;
        public TextView til_weight;
        public TextView state;
        public TextView price;
        public TextView sort;
        public TextView bianhao;
        public TextView name;
        public TextView tel;
        public TextView yihuan;
        public CheckBox xuan;
        public LinearLayout zhifu;
        public View view;
        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);
            bianhao = itemView.findViewById(R.id.bianhao);
            sort = itemView.findViewById(R.id.sort);
            xianjin = itemView.findViewById(R.id.xianjin);
            price = itemView.findViewById(R.id.price);
            state = itemView.findViewById(R.id.state);
            til_weight = itemView.findViewById(R.id.til_weight);
            yihuan = itemView.findViewById(R.id.yuhuanbi);
        }
    }
}
