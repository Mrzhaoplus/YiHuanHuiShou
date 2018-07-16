package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Wait_Designate_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.Order_Daijiedan_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class Designate_Adapter extends RecyclerView.Adapter<Designate_Adapter.Holder> {
     Context context;
     List<Order_Daijiedan_Bean.DataBean>list=new ArrayList<>();

    public Designate_Adapter(Context context, List<Order_Daijiedan_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.designate_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.bianhao.setText(list.get(position).getOrderNumber());
        holder.sort.setText(list.get(position).getVarieties());
        holder.weight.setText(list.get(position).getCount()+""+list.get(position).getUnit());

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Wait_Designate_DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {

        public TextView weight;
        public TextView sort;
        public TextView state;
        public TextView bianhao;
        public View view;
        public CheckBox xuan;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            bianhao = itemView.findViewById(R.id.desi_bh);
            state = itemView.findViewById(R.id.desi_state);
            sort = itemView.findViewById(R.id.desi_sort);
            weight = itemView.findViewById(R.id.desi_weight);
            xuan = itemView.findViewById(R.id.desi_xuan);
        }
    }
}
