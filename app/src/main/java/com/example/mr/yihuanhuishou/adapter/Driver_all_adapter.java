package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/7.
 */

public class Driver_all_adapter extends RecyclerView.Adapter<Driver_all_adapter.Holder> {
    private  String state;
    Context context;
    List<String>list=new ArrayList<>();
    public Driver_all_adapter(Context context,String state) {
        this.state=state;
        this.context = context;
        data();
    }
    private void data() {
       for(int i=0;i<8;i++){
            list.add("");
        }
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.driver_all_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(Holder holder, final int position) {
       //取消
        holder.quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context,"取消订单");
            }
        });
        holder.state.setText(state);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Holder extends RecyclerView.ViewHolder{
        public View view;
        public TextView bian;
        public TextView state;
        public TextView sort;
        public TextView zhong;
        public LinearLayout quxiao;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            bian = itemView.findViewById(R.id.jie_bh);
            state = itemView.findViewById(R.id.jie_state);
            sort = itemView.findViewById(R.id.jie_lx);
            zhong = itemView.findViewById(R.id.jie_zl);
            quxiao = itemView.findViewById(R.id.jie_qx);
        }
    }
}
