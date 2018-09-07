package com.example.mr.yihuanhuishou.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driver_Huanxin_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.hyphenate.chat.EMClient;
import com.hyphenate.chat.EMConversation;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by Mr赵 on 2018/5/14.
 */

public class Mymsg_Adapter extends RecyclerView.Adapter<Mymsg_Adapter.Holder>{
    private Context context;
    List<String> list=new ArrayList<>();
    Map<String, EMConversation> map;
    List<String>last=new ArrayList<>();

    private OnMyItemClickListener listener ;
    public void setOnMyItemClickListener(OnMyItemClickListener listener){
        this.listener = listener;

    }

    public interface OnMyItemClickListener{
        void myClick(View v,int pos);
    }

    public Mymsg_Adapter(Context context, List<String> list, Map<String, EMConversation> map) {
        this.context = context;
        this.list = list;
        this.map = map;
        Set<Map.Entry<String, EMConversation>> set = map.entrySet();
        for (Map.Entry<String, EMConversation> me:set){
            last.add(me.getValue().getLastMessage().getBody().toString());
        }


    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.mymsg_adapter, parent, false);
        Holder holder = new Holder(view);
        return holder;
    }
    @Override
    public void onBindViewHolder(final Holder holder, final int position) {
        HttpParams params = new HttpParams();
        params.put("imUser",list.get(position));
        OkGo.<Driver_Huanxin_Bean>post(MyUrls.BASEURL + "/app/company/getPersonInfoByHX")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driver_Huanxin_Bean>((Activity) context, Driver_Huanxin_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driver_Huanxin_Bean> response) {
                        Driver_Huanxin_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                           String photoPath = body.getData().getHeadImage();
                            holder.name.setText(body.getData().getName());
                            Glide.with(context).load(photoPath).into(holder.touxiang);
                        } else{
                            ToastUtils.getToast(context, body.getMsg());
                        }
                    }
                });


                holder.neirong.setText(last.get(position).substring(5,last.get(position).length()-1));
                if(list.size()>0){
                    EMConversation conversation = EMClient.getInstance().chatManager().getConversation(list.get(position));
                    int unreadMsgCount = conversation.getUnreadMsgCount();
                    if(unreadMsgCount!=0){
                        holder.dian.setVisibility(View.VISIBLE);
                    }else{
                        holder.dian.setVisibility(View.GONE);
                    }



    }
         holder.view.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 holder.dian.setVisibility(View.GONE);
                 if (listener != null){
                     listener.myClick(view,position);
                 }
             }
         });
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class Holder extends RecyclerView.ViewHolder{
        public ImageView dian;
        public TextView neirong;
        public TextView name;
        public ImageView touxiang;
        public View view;
        public RelativeLayout clickRl;

        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            touxiang = view.findViewById(R.id.touxiang);
            name = view.findViewById(R.id.jname);
            neirong = view.findViewById(R.id.neirong);
            dian = view.findViewById(R.id.dian);
            clickRl = view.findViewById(R.id.click_rl);
        }
    }

}
