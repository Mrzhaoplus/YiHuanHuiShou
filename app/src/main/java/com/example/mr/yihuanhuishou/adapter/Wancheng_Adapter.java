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
import com.example.mr.yihuanhuishou.activity.Wait_wancheng_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Shangmen_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Wancheng_Adapter extends RecyclerView.Adapter<Wancheng_Adapter.Holder>{
    private Context context;
    private List<Recy_Shangmen_Bean.DataListBean>list=new ArrayList<>();

    public Wancheng_Adapter(Context context, List<Recy_Shangmen_Bean.DataListBean> list) {
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
        holder.yihuan.setText(list.get(position).getGiveCurrency()+"元");
        holder.price.setText(list.get(position).getTotalMoney()+"元");

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Wait_wancheng_DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("name",holder.name.getText().toString());
                intent.putExtra("address",list.get(position).getEecWasteinfo().getResidentaddr().getDetailAddr());
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {
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
            price = itemView.findViewById(R.id.price);
            state = itemView.findViewById(R.id.state);
            yihuan = itemView.findViewById(R.id.yuhuanbi);
        }
    }
}
