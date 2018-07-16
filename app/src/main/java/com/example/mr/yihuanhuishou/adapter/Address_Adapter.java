package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Address_DetailsActivity;
import com.example.mr.yihuanhuishou.bean.Address_bean;
import com.example.mr.yihuanhuishou.jsonbean.Address_List_Bean;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.List;

/**
 * Created by Mr赵 on 2018/6/20.
 */

public class Address_Adapter extends RecyclerView.Adapter<Address_Adapter.Viewholser>{
    Context context;
    List<Address_List_Bean.DataBean> list;
    private int state;
    private getclick click;

    public Address_Adapter(Context context, List<Address_List_Bean.DataBean> list,int state) {
        this.context = context;
        this.list = list;
        this.state=state;
    }

    public void getjiekou(getclick click){
        this.click=click;
    }

    @Override
    public Viewholser onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.address_adapter, parent, false);
        Viewholser viewholser = new Viewholser(view);
        return viewholser;
    }


    @Override
    public void onBindViewHolder(Viewholser holder, final int position) {

          holder.name.setText(list.get(position).getName());
          holder.address.setText(list.get(position).getProvince()+list.get(position).getCity()+list.get(position).getCounry()+list.get(position).getDetailAddr());
          holder.tel.setText(list.get(position).getPhoneNumber());
        String isDefault = list.get(position).getIsDefault();
        if(isDefault.equals("0")){
            holder.moren.setVisibility(View.GONE);
            holder.xian.setVisibility(View.GONE);
        }else if(isDefault.equals("1")){
            holder.moren.setVisibility(View.VISIBLE);
            holder.xian.setVisibility(View.VISIBLE);
        }

        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(state==1){
                     click.click(position);
                }else{
                    Intent intent = new Intent(context, Address_DetailsActivity.class);
                    intent.putExtra("addid",list.get(position).getId());
                    context.startActivity(intent);
                }
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholser extends RecyclerView.ViewHolder{

        public TextView moren;
        public View view;
        public TextView tel;
        public TextView address;
        public TextView name;
        public View xian;

        public Viewholser(View itemView) {
            super(itemView);
            view = itemView;
            name = view.findViewById(R.id.name);
            address = view.findViewById(R.id.address);
            tel = view.findViewById(R.id.tel);
            moren = view.findViewById(R.id.moren);
            xian = view.findViewById(R.id.xian);
        }
    }

    public interface getclick{
        void click(int postion);
    }
}
