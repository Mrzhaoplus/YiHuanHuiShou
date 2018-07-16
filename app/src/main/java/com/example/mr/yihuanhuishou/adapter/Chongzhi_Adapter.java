package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/20.
 */

public class Chongzhi_Adapter extends RecyclerView.Adapter<Chongzhi_Adapter.Viewholder> {
      private Context context;
      List<String> mList=new ArrayList<>();
      private int state;

    public Chongzhi_Adapter(Context context, List<String> mList, int state) {
        this.context = context;
        this.mList = mList;
        this.state = state;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.chongzhi_zhichu_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        if(state==1){
            holder.title.setText("您从支付宝向账户充值100元");
        }else{
            holder.title.setText("您从账户账支出100元");
        }

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{

        public View view;
        public TextView time;
        public TextView title;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
        }
    }
}
