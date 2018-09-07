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
import com.example.mr.yihuanhuishou.activity.Dingdan_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Shangmen_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Shangmen_Adapter extends RecyclerView.Adapter<Shangmen_Adapter.Holder>{
    private Context context;
    private List<Recy_Shangmen_Bean.DataListBean>list=new ArrayList<>();

    private Queren queren;
    private QuXiao quxiao;

    public void queren(Queren queren){
        this.queren=queren;
    }
    public void quxiao(QuXiao quxiao){
        this.quxiao=quxiao;
    }

    public Shangmen_Adapter(Context context, List<Recy_Shangmen_Bean.DataListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shangmen_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.name.setText(list.get(position).getEecWasteinfo().getResidentaddr().getName());
        holder.tel.setText(list.get(position).getEecWasteinfo().getResidentaddr().getPhoneNumber());
        holder.bianhao.setText(list.get(position).getOrderNumber());
        holder.sort.setText(list.get(position).getEecWasteinfo().getVarieties());
        holder.weight.setText(list.get(position).getEecWasteinfo().getCount()+""+list.get(position).getEecWasteinfo().getUnit());

      holder.shouhuo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {

              queren.getquerenClick(position);
          }
      });

        holder.quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                quxiao.getcanleClick(position);
            }
        });

        //点击条目跳转详情
        holder.view.setOnClickListener(new View.OnClickListener() {



            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Dingdan_DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("name",holder.name.getText().toString());
                intent.putExtra("address",list.get(position).getEecWasteinfo().getResidentaddr().getDetailAddr());
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

    public interface QuXiao{
        void getcanleClick(int postion);
    }
    public interface Queren{
        void getquerenClick(int postion);
    }




}
