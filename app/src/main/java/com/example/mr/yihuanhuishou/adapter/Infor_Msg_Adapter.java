package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Infor_Msg_DetailsActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.XitongMassage_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/14.
 */

public class Infor_Msg_Adapter extends RecyclerView.Adapter<Infor_Msg_Adapter.Holder> {
    private Context context;
    private List<XitongMassage_Bean.DataListBean>list=new ArrayList<>();

    public Infor_Msg_Adapter(Context context, List<XitongMassage_Bean.DataListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.infor_msg_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                holder.dian.setVisibility(View.GONE);
                Intent intent = new Intent(context, Infor_Msg_DetailsActivity.class);
                intent.putExtra("id",list.get(position).getId());
                context.startActivity(intent);
            }
        });
        int isRead = list.get(position).getIsRead();
        if(isRead==0){
            holder.dian.setVisibility(View.VISIBLE);
        }else if(isRead==1){
            holder.dian.setVisibility(View.GONE);
        }
        holder.title.setText(list.get(position).getTitle());
        String content = list.get(position).getContent();
            holder.neirong.setText(content);


    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public ImageView dian;
        public TextView title;
        public TextView neirong;
        public View view;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            neirong = view.findViewById(R.id.neirong);
            title = view.findViewById(R.id.title);
            dian = view.findViewById(R.id.dian);
        }
    }
}
