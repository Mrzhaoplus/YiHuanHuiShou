package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.huishou.JiLu_Bean;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/20.
 */

public class Chongzhi_Adapter extends RecyclerView.Adapter<Chongzhi_Adapter.Viewholder> {
      private Context context;
      List<JiLu_Bean.DataBean> mList=new ArrayList<>();
      private String state;

    public Chongzhi_Adapter(Context context, List<JiLu_Bean.DataBean> mList, String state) {
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

        long createDate = mList.get(position).getCreateDate();
        String dateToString = getDateToString(String.valueOf(createDate / 1000));
        String content = mList.get(position).getContent();
        holder.time.setText(dateToString);
        if(state.equals("1")){
            String[] split = content.split("，");
            holder.title.setText(split[1]+mList.get(position).getAmount()+"元");
        }else{
            holder.title.setText(content+mList.get(position).getAmount()+"元");
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
    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
}
