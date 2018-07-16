package com.example.mr.yihuanhuishou.adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Dingdan_DetailsActivity;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Shangmen_Adapter extends RecyclerView.Adapter<Shangmen_Adapter.Holder>{
    private Context context;
    private List<String>list=new ArrayList<>();
    private PopupWindow popupWindow;
    private View inflate;
    private ListView list_view;
    private LinearLayout view02;
    private TextView unit;

    public Shangmen_Adapter(Context context) {
        this.context = context;
        data();
    }

    private void data() {
        for (int i=0;i<10;i++){
            list.add("");
        }
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.shangmen_adapter, parent, false);
        Holder holder = new Holder(inflate);
        return holder;
    }


    @Override
    public void onBindViewHolder(final Holder holder, int position) {
      holder.shouhuo.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
          }
      });

        holder.quxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ToastUtils.getToast(context,"取消订单");
            }
        });

        //点击条目跳转详情
        holder.view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, Dingdan_DetailsActivity.class);
                intent.putExtra("name",holder.name.getText().toString());
                intent.putExtra("state",holder.state.getText().toString());
                intent.putExtra("sort",holder.sort.getText().toString());
                intent.putExtra("weight",holder.weight.getText().toString());
                intent.putExtra("bianhao",holder.bianhao.getText().toString());
                context.startActivity(intent);
            }
        });
    }


    @Override
    public int getItemCount() {
        return list.size();
    }

    public class Holder extends RecyclerView.ViewHolder {

        public  LinearLayout quxiao;
        public LinearLayout shouhuo;
        public TextView state;
        public TextView weight;
        public TextView sort;
        public TextView bianhao;
        public TextView name;
        public TextView tel;
        public View view;
        public Holder(View itemView) {
            super(itemView);
            view = itemView;
            name = itemView.findViewById(R.id.name);
            tel = itemView.findViewById(R.id.tel);
            bianhao = itemView.findViewById(R.id.bianhao);
            sort = itemView.findViewById(R.id.sort);
            weight = itemView.findViewById(R.id.weight);
            state = itemView.findViewById(R.id.state);
            shouhuo = itemView.findViewById(R.id.shouhuo);
            quxiao = itemView.findViewById(R.id.quxiao);
        }
    }


    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(context);
        final BaseDialog dialog = builder.setViewId(R.layout.qrsh_pop)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
       TextView text_sure = dialog.getView(R.id.text_sure);
        unit = dialog.getView(R.id.unit);
        view02 = dialog.getView(R.id.view_02);
       TextView text_pause = dialog.getView(R.id.text_pause);
        //pop框
        view02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoview();
            }
        });
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();

            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private void infoview() {
        if (popupWindow == null) {
            inflate = LayoutInflater.from(context).inflate(R.layout.popwindow_listview, null);
            list_view = inflate.findViewById(R.id.list_view);
            popupWindow = new PopupWindow(inflate, 180, RadioGroup.LayoutParams.WRAP_CONTENT);
        }
        final List<String> list = new ArrayList<>();
        list.add("个");
        list.add("包");
        list.add("Kg");
        Pop_Adapter pop_adapter = new Pop_Adapter(context, list);
        list_view.setAdapter(pop_adapter);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view02, 0, 0);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupWindow.dismiss();
                unit.setText(list.get(i));
            }
        });
    }
}
