package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.Xitong_Recycler_Bean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/22.
 */

public class ZhiPai_wupin_Adapter extends RecyclerView.Adapter<ZhiPai_wupin_Adapter.Viewholder> {
    private Context context;
    private List<Xitong_Recycler_Bean.DataBean>list=new ArrayList<>();
    private List<Integer>pos=new ArrayList<>();
    private Onclickflag jiekou;

    public ZhiPai_wupin_Adapter(Context context, List<Xitong_Recycler_Bean.DataBean> list) {
        this.context = context;
        this.list = list;
    }
    public void getclick(Onclickflag jiekou){
        this.jiekou=jiekou;
    }

    @Override
    public Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.zhipaiwupin_adapter, parent, false);
        Viewholder viewholder = new Viewholder(view);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Viewholder holder, final int position) {
        holder.checkBox.setText(list.get(position).getName());
          holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
              @Override
              public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                  if(b){
                      pos.add(position);
                      jiekou.flag(pos);
                  }else{
                      pos.remove(position);
                  }
              }
          });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public CheckBox checkBox;
        public View view;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            checkBox = itemView.findViewById(R.id.check_wupin);
        }
    }

    public interface  Onclickflag{
        void flag(List<Integer>postion);
    }


}
