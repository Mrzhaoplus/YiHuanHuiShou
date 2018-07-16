package com.example.mr.yihuanhuishou.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Mine_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Mine_bean;
import com.example.mr.yihuanhuishou.driver.ui.DriverSettingActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverWalletActivity;
import com.example.mr.yihuanhuishou.driver.weight.CircleImageView;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MineActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.grid_view)
    GridView gridView;
    @BindView(R.id.title_rl)
    RelativeLayout titleRl;
    List<Mine_bean> list = new ArrayList<>();
    @BindView(R.id.iv_grtx)
    CircleImageView ivGrtx;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.wallet_ll)
    LinearLayout walletLl;
    @BindView(R.id.yihuanbi_ll)
    LinearLayout yihuanbiLl;
    @BindView(R.id.yajin_ll)
    LinearLayout yajinLl;
    @BindView(R.id.gongsi_ll)
    LinearLayout gongsiLl;
    @BindView(R.id.sex_imag)
    ImageView sexImag;
    @BindView(R.id.xianjin)
    TextView xianjin;
    @BindView(R.id.yihuan)
    TextView yihuan;
    @BindView(R.id.yajin)
    TextView yajin;
    @BindView(R.id.fire_weight)
    TextView fireWeight;
    private SharedPreferences sp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mine);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initSystemBarTint();
        initdata();
    }

    @Override
    protected void onResume() {
        super.onResume();
        String imagpath = sp.getString(GGUtils.IMAGE_path, "");
        Glide.with(this).load(imagpath).into(ivGrtx);
        if(imagpath.equals("")){
            Glide.with(this).load(R.drawable.logo_xxxhdpi).into(ivGrtx);
        }
        String user_name = sp.getString(GGUtils.USER_NAME, "");
        if(user_name.equals("")){
            name.setText("暂无昵称！");
        }else{
            name.setText(user_name);
        }
        String sex = sp.getString(GGUtils.SEX, "");
        if(sex.equals("0")){
            Glide.with(this).load(R.drawable.icon_girl).into(sexImag);
        }else if(sex.equals("1")){
            Glide.with(this).load(R.drawable.icon_boy).into(sexImag);
        }else{
            sexImag.setVisibility(View.GONE);
        }
        xianjin.setText(sp.getInt(GGUtils.XIANJIN,0)+".00元");
        yihuan.setText(sp.getInt(GGUtils.YIHUAN,0)+".00元");
    }

    @Override
    protected boolean translucentStatusBar() {
        return true;
    }

    private int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

    private void initdata() {


        final int statusBarHeight = getStatusBarHeight(this);
        MyUtils.setMargins(titleRl, 0, statusBarHeight, 0, 0);

        list.add(new Mine_bean(R.drawable.wddd_iv, "我的订单"));
        list.add(new Mine_bean(R.drawable.ggb_xxxhdpi, "公告板"));
        list.add(new Mine_bean(R.drawable.wdxx_iv, "我的消息"));
        list.add(new Mine_bean(R.drawable.hsjg_iv, "回收价格"));
        list.add(new Mine_bean(R.drawable.bzdgl_iv, "包装袋管理"));
        list.add(new Mine_bean(R.drawable.cydz_iv, "常用地址"));
        list.add(new Mine_bean(R.drawable.smyz_iv, "实名验证"));
        list.add(new Mine_bean(R.drawable.yjjy_iv, "意见建议"));
        list.add(new Mine_bean(R.drawable.sz_iv, "设置"));

        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        Mine_Adapter mine_adapter = new Mine_Adapter(MineActivity.this, list);
        gridView.setAdapter(mine_adapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i) {
                    case 0:
                        startActivity(new Intent(MineActivity.this, OrderActivity.class));
                        break;
                    case 1:
                        startActivity(new Intent(MineActivity.this, NoticolActivity.class));
                        break;
                    case 2:
                        startActivity(new Intent(MineActivity.this, MyMsgActivity.class));
                        break;
                    case 3:
                        startActivity(new Intent(MineActivity.this, Recycle_priceActivity.class));
                        break;
                    case 4:
                        startActivity(new Intent(MineActivity.this, PackManageActivity.class));
                        break;
                    case 5:
                        startActivity(new Intent(MineActivity.this, Select_AddressActivity.class));
                        break;
                    case 6:
                        startActivity(new Intent(MineActivity.this, Real_nameActivity.class));
                        break;
                    case 7:
                        startActivity(new Intent(MineActivity.this, ComplaintActivity.class));
                        break;
                    case 8:
                        startActivity(new Intent(MineActivity.this, DriverSettingActivity.class));
                        break;
                }
            }
        });

    }

    @OnClick({R.id.iv_grtx, R.id.wallet_ll, R.id.yihuanbi_ll, R.id.yajin_ll, R.id.gongsi_ll})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.iv_grtx:
                startActivity(new Intent(MineActivity.this, HomePersonInfoActivity.class));
                break;
            case R.id.wallet_ll:
                startActivity(new Intent(MineActivity.this, DriverWalletActivity.class));
                break;
            case R.id.yihuanbi_ll:
                startActivity(new Intent(MineActivity.this, YihuanBiActivity.class));
                break;
            case R.id.yajin_ll:
                startActivity(new Intent(MineActivity.this, YaJinActivity.class));
                break;
            case R.id.gongsi_ll:
                startActivity(new Intent(MineActivity.this, Recycler_FirmActivity.class));
                break;
        }
    }
}
