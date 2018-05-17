package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
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

public class Mymsg_Adapter extends RecyclerView.Adapter<Mymsg_Adapter.Holder>{
    private Context context;
    List<String> list=new ArrayList<>();

    public Mymsg_Adapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mymsg_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        //内容显示
        String s = holder.neirong.getText().toString();

         if(s.length()>15){
             holder.neirong.setText(s.substring(0,15)+"...");
         }else{
             holder.neirong.setText(s);
         }
         holder.view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 holder.dian.setVisibility(View.GONE);
             }
         });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder{

        public ImageView dian;
        public TextView neirong;
        public TextView name;
        public ImageView touxiang;
        public View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            touxiang = view.findViewById(R.id.touxiang);
            name = view.findViewById(R.id.jname);
            neirong = view.findViewById(R.id.neirong);
            dian = view.findViewById(R.id.dian);

        }
    }
}
