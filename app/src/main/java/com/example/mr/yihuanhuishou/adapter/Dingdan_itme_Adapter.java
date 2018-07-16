package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/7/5.
 */

public class Dingdan_itme_Adapter extends RecyclerView.Adapter<Dingdan_itme_Adapter.Viewholder> {
    Context context;
    List<String> mLise;
    private Onclickflag jiekou;
    private List<Integer>pos=new ArrayList<>();
    public Dingdan_itme_Adapter(Context context, List<String> mLise) {
        this.context = context;
        this.mLise = mLise;
    }
    public void getclick(Onclickflag jiekou){
        this.jiekou=jiekou;
    }
    @Override
    public Dingdan_itme_Adapter.Viewholder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.dingdan_itme_adapter, parent, false);
        Viewholder viewholder = new Viewholder(inflate);
        return viewholder;
    }


    @Override
    public void onBindViewHolder(Dingdan_itme_Adapter.Viewholder holder, final int position) {
      holder.xuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
          @Override
          public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
              if(b){
                  pos.add(position);
                  jiekou.flag(pos);
              }
          }
      });
    }


    @Override
    public int getItemCount() {
        return mLise.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder {

        public View view;
        public TextView bianhao;
        public TextView sort;
        public TextView weight;
        public CheckBox xuan;

        public Viewholder(View itemView) {
            super(itemView);
            view = itemView;
            xuan = view.findViewById(R.id.xuan);
            bianhao = view.findViewById(R.id.bianhao);
            sort = view.findViewById(R.id.sort);
            weight = view.findViewById(R.id.weight);
        }
    }
    public interface  Onclickflag{
        void flag(List<Integer>postion);
    }
}
