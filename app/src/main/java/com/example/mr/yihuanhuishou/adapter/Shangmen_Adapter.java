package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Dingdan_DetailsActivity;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Shangmen_Adapter extends RecyclerView.Adapter<Shangmen_Adapter.Holder>{
    private Context context;
    private List<String>list=new ArrayList<>();
    public Shangmen_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<10;i++){
            list.add("");
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shangmen_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, int position) {
      holder.shouhuo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              ToastUtils.getToast(context,"确认收货");
          }
      });

        holder.quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context,"取消订单");
            }
        });

        //点击条目跳转详情
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Dingdan_DetailsActivity.class);
                intent.putExtra("name",holder.name.getText().toString());
                intent.putExtra("state",holder.state.getText().toString());
                intent.putExtra("sort",holder.sort.getText().toString());
                intent.putExtra("weight",holder.weight.getText().toString());
                intent.putExtra("bianhao",holder.bianhao.getText().toString());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public  LinearLayout quxiao;
        public LinearLayout shouhuo;
        public TextView state;
        public TextView weight;
        public TextView sort;
        public TextView bianhao;
        public TextView name;
        public TextView tel;
        public View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);
            bianhao = itemView.findViewById(R.id.bianhao);
            sort = itemView.findViewById(R.id.sort);
            weight = itemView.findViewById(R.id.weight);
            state = itemView.findViewById(R.id.state);
            shouhuo = itemView.findViewById(R.id.shouhuo);
            quxiao = itemView.findViewById(R.id.quxiao);
        }
    }
}
