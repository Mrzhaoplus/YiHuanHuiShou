package com.example.mr.yihuanhuishou.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.fragment.Shenhe_Fragment;
import com.example.mr.yihuanhuishou.fragment.TongGuo_Fragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Recycler_FirmActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.tongguo)
    TextView tongguo;
    @BindView(R.id.xian1)
    View xian1;
    @BindView(R.id.click_tongguo)
    RelativeLayout clickTongguo;
    @BindView(R.id.shenhe)
    TextView shenhe;
    @BindView(R.id.xian2)
    View xian2;
    @BindView(R.id.click_shenhe)
    RelativeLayout clickShenhe;
    @BindView(R.id.fl)
    FrameLayout fl;
    private Fragment currfit;
    private TongGuo_Fragment tongGuo_fragment;
    private Shenhe_Fragment shenhe_fragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycler__firm);
        ButterKnife.bind(this);
        initdata();
    }

    private void initdata() {
        beak.setOnClickListener(this);
        clickTongguo.setOnClickListener(this);
        clickShenhe.setOnClickListener(this);

        tongguo.setTextColor(getResources().getColor(R.color.colorAccent));
        xian1.setVisibility(View.VISIBLE);
        shenhe.setTextColor(getResources().getColor(R.color.color_69));
        xian2.setVisibility(View.GONE);
        if(tongGuo_fragment==null){
            tongGuo_fragment = new TongGuo_Fragment();
        }
        AddFragment(tongGuo_fragment);
    }

    @Override
    public void onClick(View view) {
     switch (view.getId()){
         case R.id.beak:
             finish();
             break;
         case R.id.click_tongguo:
             tongguo.setTextColor(getResources().getColor(R.color.colorAccent));
             xian1.setVisibility(View.VISIBLE);
             shenhe.setTextColor(getResources().getColor(R.color.color_69));
             xian2.setVisibility(View.GONE);
             if(tongGuo_fragment==null){
                 tongGuo_fragment = new TongGuo_Fragment();
             }
             AddFragment(tongGuo_fragment);
             break;
         case R.id.click_shenhe:
             shenhe.setTextColor(getResources().getColor(R.color.colorAccent));
             xian2.setVisibility(View.VISIBLE);
             tongguo.setTextColor(getResources().getColor(R.color.color_69));
             xian1.setVisibility(View.GONE);
             if(shenhe_fragment==null){
                 shenhe_fragment = new Shenhe_Fragment();
             }
             AddFragment(shenhe_fragment);
             break;
     }
    }

/*
        * 动态添加fragment方法
    * */
    public void AddFragment(Fragment f){
        FragmentManager supportFragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        if(currfit !=null){
            fragmentTransaction.hide(currfit);
        }
        if(!f.isAdded()){
            fragmentTransaction.add(R.id.fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;
    }
}
