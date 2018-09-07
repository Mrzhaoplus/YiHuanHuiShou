package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Huishou_sort_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Xitong_Recycler_Bean;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/6/5.
 */

public class Recycler_prive_Adapter extends RecyclerView.Adapter<Recycler_prive_Adapter.ViewHolser> {
    private Context context;
    private List<Xitong_Recycler_Bean.DataBean>mlist=new ArrayList<>();
    List<Huishou_sort_Bean.DataBean> list = new ArrayList<>();
    private Xuan xuan;
    private List<Integer>pos=new ArrayList<>();
    private List<Integer>price=new ArrayList<>();

    public Recycler_prive_Adapter(Context context, List<Xitong_Recycler_Bean.DataBean> mlist, List<Huishou_sort_Bean.DataBean> list) {
        this.context = context;
        this.mlist = mlist;
        this.list = list;
    }

    public void getXuan(Xuan xuan){
        this.xuan=xuan;
   }
    @Override
    public ViewHolser onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.recycle_price_adapter, parent, false);
        ViewHolser viewHolser = new ViewHolser(view);
        return viewHolser;
    }
    @Override
    public void onBindViewHolder(final ViewHolser holder, final int position) {
        holder.stort.setText(mlist.get(position).getName());
        for (int i=0;i<list.size();i++) {
            if (mlist.get(position).getId() == list.get(i).getCode()) {
                holder.xuan.setChecked(true);
                holder.edit_text.setText(list.get(i).getUnitPrice()+"");
            }
        }
        boolean checked = holder.xuan.isChecked();
        if(checked==true){
            String trim = holder.edit_text.getText().toString().trim();
            int i = Integer.parseInt(trim);
            pos.add(mlist.get(position).getId());
            price.add(i);
            xuan.getXuan(pos,price);
            holder.edit_text.setFocusable(false);
            holder.edit_text.setFocusableInTouchMode(false);

            holder.edit_text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ToastUtils.getToast(context,"选中后不可编辑金额！");
                }
            });
        }



        holder.xuan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
       @Override
       public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
           if(b){
               String trim = holder.edit_text.getText().toString().trim();
               if(TextUtils.isEmpty(trim)){
                   holder.xuan.setChecked(false);
                   ToastUtils.getToast(context,"价格为空");

               }else{
                   int i = Integer.parseInt(trim);
                   pos.add(mlist.get(position).getId());
                   price.add(i);
                   xuan.getXuan(pos,price);
               }
           }else{

               for (int i=0;i<pos.size();i++){
                   if(position+1==pos.get(i)){
                       holder.edit_text.setFocusable(true);
                       holder.edit_text.setFocusableInTouchMode(true);
                       pos.remove(i);
                       price.remove(i);
                   }
               }
               xuan.getXuan(pos,price);
           }
       }
   });


    }
    @Override
    public int getItemCount() {
        return mlist.size();
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
    public interface Xuan{
        void getXuan(List<Integer>pos,List<Integer>price);
    }
}
