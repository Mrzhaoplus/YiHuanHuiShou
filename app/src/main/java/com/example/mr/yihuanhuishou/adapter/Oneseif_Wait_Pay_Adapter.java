package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class Oneseif_Wait_Pay_Adapter extends RecyclerView.Adapter<Oneseif_Wait_Pay_Adapter.Holder>{
       private Context context;
       List<String>list=new ArrayList<>();
    public Oneseif_Wait_Pay_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<8;i++){
            list.add("");
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.oneseif_wait_pay_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {

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
