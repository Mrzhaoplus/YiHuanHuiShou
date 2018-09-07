package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Xuqiu_DetailActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fujin_Order_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/3.
 */

public class Bill_Adapter extends RecyclerView.Adapter<Bill_Adapter.Holder>{
    Context context;
    List<Fujin_Order_Bean.DataBean> list=new ArrayList<>();
    private double lotitude;
    private double longitude;
    private GetQiangdan jiekou;

    public Bill_Adapter(Context context, List<Fujin_Order_Bean.DataBean> list, double longitude, double lotitude) {
        this.context = context;
        this.list = list;
        this.lotitude = lotitude;
        this.longitude = longitude;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.bill_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, final int position) {
        holder.name.setText(list.get(position).getEecWasteinfo().getResidentaddr().getName());
        holder.address.setText(list.get(position).getEecWasteinfo().getResidentaddr().getDetailAddr());
        holder.leixing.setText(list.get(position).getEecWasteinfo().getVarieties());
        holder.qiangdan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                jiekou.click(position);
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Xuqiu_DetailActivity.class);
                int id = list.get(position).getId();
                intent.putExtra("id",id);
                intent.putExtra("lon",longitude);
                intent.putExtra("lat",lotitude);

                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{
        public  TextView name;
        public  TextView leixing;
        public LinearLayout qiangdan;
        public TextView address;
        public  View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name);
            leixing = itemView.findViewById(R.id.leixing);
            qiangdan = itemView.findViewById(R.id.qiangdan);
             address = itemView.findViewById(R.id.address);
        }
    }
    public interface GetQiangdan{
        void click(int postion);
    }
    public void getclick(GetQiangdan jiekou){
        this.jiekou=jiekou;
    }
}
