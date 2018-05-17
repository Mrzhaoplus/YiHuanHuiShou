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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class Designate_Adapter extends RecyclerView.Adapter<Designate_Adapter.Holder> {
     Context context;
     List<String>list=new ArrayList<>();

    public Designate_Adapter(Context context) {
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
        View view = LayoutInflater.from(context).inflate(R.layout.designate_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(Holder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Wait_Designate_DetailsActivity.class);
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
