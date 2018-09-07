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

public class Back_card_Adapter extends RecyclerView.Adapter<Back_card_Adapter.Viewholder> {
     private Context context;
    List<Back_card_Bean.DataBean> list;

    public Back_card_Adapter(Context context, List<Back_card_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Back_card_Adapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.add_bank_card, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Back_card_Adapter.Viewholder holder, int position) {
        Glide.with(context).load(list.get(position).getEecBankcardtype().getBankImage()).into(holder.bank_iv);
        holder.bank_tv.setText(list.get(position).getEecBankcardtype().getBankName());
        String cardNumber = list.get(position).getCardNumber();
        holder.bank_num_tv.setText(cardNumber.substring(0,4)+" "+cardNumber.substring(4,8)+" "+"*******"+" "+cardNumber.substring(15,cardNumber.length()));
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public ImageView bank_iv;
        public TextView bank_num_tv;
        public TextView bank_tv;

        public Viewholder(View itemView) {
            super(itemView);
            bank_iv = itemView.findViewById(R.id.bank_iv);
            bank_tv = itemView.findViewById(R.id.bank_tv);
            bank_num_tv = itemView.findViewById(R.id.bank_num_tv);
        }
    }
}
