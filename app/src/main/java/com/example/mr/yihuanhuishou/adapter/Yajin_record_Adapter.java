package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Yajin_Record_Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/20.
 */

public class Yajin_record_Adapter extends RecyclerView.Adapter<Yajin_record_Adapter.Viewholder> {
   Context context;
   List<Yajin_Record_Bean.DataBean>list=new ArrayList<>();

    public Yajin_record_Adapter(Context context, List<Yajin_Record_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.yajin_record_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Viewholder holder, int position) {
        String type = list.get(position).getType();
        if(type.equals("0")){
            holder.title.setText("缴纳押金");
            holder.state.setText("+"+list.get(position).getDeposit());
        }else{
            holder.title.setText("押金退款");
            holder.state.setText(list.get(position).getDeposit()+"");
        }
        long createDate = list.get(position).getCreateDate();
        String dateToString = getDateToString(String.valueOf(createDate / 1000));
        holder.time.setText(dateToString);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder  extends RecyclerView.ViewHolder{

        public TextView state;
        public TextView time;
        public TextView title;

        public Viewholder(View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            time = itemView.findViewById(R.id.time);
            state = itemView.findViewById(R.id.state);
        }
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
