package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.PackManage_DetailsActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/25.
 */

public class PackManage_Details_Adapter extends RecyclerView.Adapter<PackManage_Details_Adapter.Viewholder> {
   private Context context;
   List<String>list=new ArrayList<>();

    public PackManage_Details_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.packmanage_details_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.xh_code.setText("0"+(position+1));

    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public View view;
        public TextView bh_code;
        public TextView xh_code;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            xh_code = view.findViewById(R.id.xh_code);
            bh_code = view.findViewById(R.id.bh_code);

        }
    }
}
