package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Wait_zhifu_DetailsActivity;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class ZhiFu_Adapter extends RecyclerView.Adapter<ZhiFu_Adapter.Holder> {
    private Context context;
    private List<String> list = new ArrayList<>();
    public ZhiFu_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i = 0; i < 9; i++) {
            list.add("");
        }
    }
    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.zhifu_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context, "支付");
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Wait_zhifu_DetailsActivity.class);
                context.startActivity(intent);
            }
        });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Holder extends RecyclerView.ViewHolder {
        public LinearLayout quxiao;
        public LinearLayout shouhuo;
        public TextView state;
        public TextView price;
        public TextView sort;
        public TextView bianhao;
        public TextView name;
        public TextView tel;
        public TextView yihuan;
        public CheckBox xuan;
        public LinearLayout zhifu;
        public View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);
            bianhao = itemView.findViewById(R.id.bianhao);
            sort = itemView.findViewById(R.id.sort);
            price = itemView.findViewById(R.id.price);
            state = itemView.findViewById(R.id.state);
            yihuan = itemView.findViewById(R.id.yuhuanbi);
            xuan = itemView.findViewById(R.id.xuan);
            zhifu = itemView.findViewById(R.id.zhifu);
        }
    }
}
