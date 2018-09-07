package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Dingdan_itme_Adapter;
import com.example.mr.yihuanhuishou.adapter.ZhiFu_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Dingdan_itmeActivity extends BaseActivity implements Dingdan_itme_Adapter.Onclickflag, View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView recy_view;
    @BindView(R.id.sp_view)
    SpringView sp_view;
    @BindView(R.id.queding)
    Button queding;
    @BindView(R.id.all_xuan)
    CheckBox allXuan;
    List<String>mLise=new ArrayList<>();
    private List<Integer> pos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dingdan_itme);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        for (int i=0;i<10;i++){
            mLise.add("");
        }
        queding.setOnClickListener(this);
        beak.setOnClickListener(this);

        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));
        //适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(this));
        recy_view.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        Dingdan_itme_Adapter dingdan_itme_adapter = new Dingdan_itme_Adapter(this,mLise);
        recy_view.setAdapter(dingdan_itme_adapter);
        dingdan_itme_adapter.getclick(this);

    }

    @Override
    public void flag(List<Integer> postion) {
        pos =postion;
    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             case R.id.beak:
                 finish();
                 break;
             case R.id.queding:
                 if(pos.size()>0){
                     shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
                 }
                 break;
         }
    }
    //指派
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(this);
        final BaseDialog dialog = builder.setViewId(R.layout.dialog_jiechu)
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
        TextView text_pause = dialog.getView(R.id.text_pause);
        TextView ckdd = dialog.getView(R.id.ckdd);
        TextView cxzp = dialog.getView(R.id.cxzp);
        LinearLayout chenggong = dialog.getView(R.id.chenggong);
        LinearLayout shibai = dialog.getView(R.id.shibai);
        //重新指派
        cxzp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                finish();
            }
        });
        //查看订单
        ckdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 startActivity(new Intent(Dingdan_itmeActivity.this,Dingdan_DetailActivity.class));
            }
        });

        //返回首页
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

}
