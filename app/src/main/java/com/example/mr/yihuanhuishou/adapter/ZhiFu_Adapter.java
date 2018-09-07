package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Pay_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Wait_zhifu_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Shangmen_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class ZhiFu_Adapter extends RecyclerView.Adapter<ZhiFu_Adapter.Holder> {
    private Context context;
    private List<Recy_Shangmen_Bean.DataListBean> list = new ArrayList<>();
    List<Boolean> pro=new ArrayList<>();
    private Checkbox_click jiekou;
    List<String>bianhaonum=new ArrayList<>();
  private double tem=0;
  private double yi=0;
    //创建一个集合 去记录选中与未选中的状态


    public ZhiFu_Adapter(Context context, List<Recy_Shangmen_Bean.DataListBean> list, List<Boolean> pro) {
        this.context = context;
        this.list = list;
        this.pro = pro;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.zhifu_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.xuan.setChecked(pro.get(position));
        holder.name.setText(list.get(position).getEecWasteinfo().getResidentaddr().getName());
        holder.tel.setText(list.get(position).getEecWasteinfo().getResidentaddr().getPhoneNumber());
        holder.bianhao.setText(list.get(position).getOrderNumber());
        holder.sort.setText(list.get(position).getEecWasteinfo().getVarieties());
       // holder.weight.setText(list.get(position).getEecWasteinfo().getCount()+""+list.get(position).getEecWasteinfo().getUnit());
        holder.price.setText(list.get(position).getTotalMoney()+"元");
        holder.zhifu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Pay_DetailsActivity.class);
                intent.putExtra("ids",list.get(position).getId());
                intent.putExtra("dingdan",list.get(position).getOrderNumber());
                intent.putExtra("money",list.get(position).getTotalMoney());
                intent.putExtra("yihuan",list.get(position).getGiveCurrency());
                context.startActivity(intent);
            }
        });
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Wait_zhifu_DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                intent.putExtra("name",holder.name.getText().toString());
                intent.putExtra("address",list.get(position).getEecWasteinfo().getResidentaddr().getDetailAddr());
                context.startActivity(intent);
            }
        });
        holder.xuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    bianhaonum.add(list.get(position).getOrderNumber());
                    tem=tem+list.get(position).getTotalMoney();
                    yi=yi+list.get(position).getGiveCurrency();

                    jiekou.getclick(bianhaonum,tem,yi);
                }else{
                    for (int i=0;i<bianhaonum.size();i++){
                        if(list.get(position).getOrderNumber().equals(bianhaonum.get(i))){
                            bianhaonum.remove(i);
                            tem=tem-list.get(position).getTotalMoney();
                            yi=yi-list.get(position).getGiveCurrency();
                        }
                    }
                    jiekou.getclick(bianhaonum,tem,yi);
                }
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
    public interface Checkbox_click{
        void getclick(List<String> biannum,double price,double yihuan);
    }
    public void getclick(Checkbox_click jiekou){
        this.jiekou=jiekou;
    }

}
