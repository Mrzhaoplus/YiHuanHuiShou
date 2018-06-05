package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.Recycle_price_bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/5.
 */

public class Recycler_prive_Adapter extends RecyclerView.Adapter<Recycler_prive_Adapter.ViewHolser> {
    private Context context;
    private List<Recycle_price_bean>list=new ArrayList<>();

    public Recycler_prive_Adapter(Context context, List<Recycle_price_bean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public ViewHolser onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_price_adapter, parent, false);
        ViewHolser viewHolser = new ViewHolser(view);
        return viewHolser;
    }


    @Override
    public void onBindViewHolder(ViewHolser holder, int position) {
        holder.xuan.setChecked(list.get(position).isFlag());
        holder.stort.setText(list.get(position).getGoods());
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolser  extends RecyclerView.ViewHolder{

        public CheckBox xuan;
        public TextView stort;
        public EditText edit_text;

        public ViewHolser(View itemView) {
            super(itemView);
            xuan = itemView.findViewById(R.id.xuan);
            stort = itemView.findViewById(R.id.recycle_sort);
            edit_text = itemView.findViewById(R.id.edit_text);
        }
    }
}
