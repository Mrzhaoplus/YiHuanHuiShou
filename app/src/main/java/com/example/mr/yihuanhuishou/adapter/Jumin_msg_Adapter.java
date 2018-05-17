package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/14.
 */

public class Jumin_msg_Adapter extends RecyclerView.Adapter<Jumin_msg_Adapter.Holder> {
    private Context context;
    List<String>list=new ArrayList<>();

    public Jumin_msg_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.jumin_msg_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.title.setText("恭喜您，抢单成功!");
                holder.dian.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ImageView dian;
        public TextView title;
        public View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            title = view.findViewById(R.id.title);
            dian = view.findViewById(R.id.dian);
        }
    }
}
