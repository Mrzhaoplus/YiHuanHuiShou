package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Back_card_Bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/7/17.
 */

public class Pop_card_Adapter extends RecyclerView.Adapter<Pop_card_Adapter.Viewholder> {
     private Context context;
    List<Back_card_Bean.DataBean> list;
    private Backclick back;

    public Pop_card_Adapter(Context context, List<Back_card_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    public void clickname(Backclick backclick){
        this.back=backclick;
    }

    @Override
    public Pop_card_Adapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.pop_bank_card, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Pop_card_Adapter.Viewholder holder, final int position) {
        Glide.with(context).load(list.get(position).getEecBankcardtype().getBankImage()).into(holder.bank_iv);
        holder.bank_tv.setText(list.get(position).getEecBankcardtype().getBankName());
         holder.view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 back.getclick(list.get(position).getEecBankcardtype().getBankName());
             }
         });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public ImageView bank_iv;
        public TextView bank_tv;
        public View view;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            bank_iv = itemView.findViewById(R.id.bank_iv);
            bank_tv = itemView.findViewById(R.id.bank_tv);
        }
    }
    public interface Backclick{
        void getclick(String backname);
    }
}
