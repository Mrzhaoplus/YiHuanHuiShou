package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.PackManage_DetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/25.
 */

public class PackManage_Adapter extends RecyclerView.Adapter<PackManage_Adapter.Viewholder> {
   private Context context;
   List<String>list=new ArrayList<>();

    public PackManage_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.packmanage_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
           holder.view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   context.startActivity(new Intent(context, PackManage_DetailsActivity.class));
               }
           });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public View view;
        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
        }
    }
}
