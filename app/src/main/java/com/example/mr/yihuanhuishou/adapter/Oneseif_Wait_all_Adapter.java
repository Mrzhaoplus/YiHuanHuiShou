package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Oneseif_Success_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Oneseif_Wait_pay_detailsActivity;
import com.example.mr.yihuanhuishou.activity.Wait_Designate_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Order_Daijiedan_Bean;

import java.util.List;

/**
 * Created by Mr赵 on 2018/5/9.
 */

public class Oneseif_Wait_all_Adapter extends RecyclerView.Adapter<Oneseif_Wait_all_Adapter.Holder>{
       private Context context;
    List<Order_Daijiedan_Bean.DataBean>mlist;

    public Oneseif_Wait_all_Adapter(Context context, List<Order_Daijiedan_Bean.DataBean> mlist) {
        this.context = context;
        this.mlist = mlist;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.oneseif_all_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.number.setText(mlist.get(position).getOrderNumber());
        holder.sort.setText(mlist.get(position).getVarieties());
        final String state = mlist.get(position).getState();
        if(state.equals("10")){
            holder.state.setText("待选定");
            holder.ziti.setText("废品重量：");
            holder.file.setVisibility(View.GONE);
            holder.huo.setBackgroundColor(context.getResources().getColor(R.color.white));
            holder.price.setText(mlist.get(position).getCount()+""+mlist.get(position).getUnit());
        }else if(state.equals("11")){
            holder.state.setText("待支付");
            holder.file.setVisibility(View.VISIBLE);
            holder.firm.setText(mlist.get(position).getCompanyName());
            holder.ziti.setText("待支付金额：");
            holder.price.setText(mlist.get(position).getTotalPrice()+"元");
        }else if(state.equals("12")){
            holder.state.setText("已完成");
            holder.ziti.setText("待支付金额：");
            holder.price.setText(mlist.get(position).getTotalPrice()+"元");
            holder.file.setVisibility(View.VISIBLE);
            holder.firm.setText(mlist.get(position).getCompanyName());
        }
        //点击跳转详情页
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String state = mlist.get(position).getState();
                if(state.equals("10")){
                    Intent intent = new Intent(context, Wait_Designate_DetailsActivity.class);
                    intent.putExtra("id",mlist.get(position).getId());
                    context.startActivity(intent);
                }else if(state.equals("11")){
                    Intent intent = new Intent(context, Oneseif_Wait_pay_detailsActivity.class);
                    intent.putExtra("id",mlist.get(position).getId());
                    context.startActivity(intent);
                }else if(state.equals("12")){
                    Intent intent = new Intent(context, Oneseif_Success_DetailsActivity.class);
                    intent.putExtra("id",mlist.get(position).getId());
                    context.startActivity(intent);
                }


            }
        });




    }
    @Override
    public int getItemCount() {
        return mlist.size();
    }

    public class Holder  extends RecyclerView.ViewHolder{

        public TextView firm;
        public View view;
        public TextView number;
        public TextView state;
        public TextView sort;
        public TextView price;
        public TextView ziti;
        public LinearLayout file;
        public LinearLayout huo;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            number = itemView.findViewById(R.id.bianhao);
            state = itemView.findViewById(R.id.state);
            sort = itemView.findViewById(R.id.sort);
            price = itemView.findViewById(R.id.price);
            ziti = itemView.findViewById(R.id.ziti);
            firm = itemView.findViewById(R.id.firm);
            file = itemView.findViewById(R.id.file);
            huo = itemView.findViewById(R.id.huo);
        }
    }
}
