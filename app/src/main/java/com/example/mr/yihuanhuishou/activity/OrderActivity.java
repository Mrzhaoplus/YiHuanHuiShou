package com.example.mr.yihuanhuishou.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.fragment.Driver_fragment;
import com.example.mr.yihuanhuishou.fragment.HuiShou_fragment;
import com.example.mr.yihuanhuishou.fragment.Oneseif_fragment;
import butterknife.BindView;
import butterknife.ButterKnife;

public class OrderActivity extends BaseActivity implements View.OnClickListener {
    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.dd_hs)
    RelativeLayout dd_hs;
    @BindView(R.id.dd_siji)
    RelativeLayout siji;
    @BindView(R.id.dd_zisong)
    RelativeLayout zisend;
    @BindView(R.id.dd_fl)
    FrameLayout fl;
    @BindView(R.id.jiao1)
    ImageView jiao1;
    @BindView(R.id.jiao2)
    ImageView jiao2;
    @BindView(R.id.jiao3)
    ImageView jiao3;
    private Fragment currfit;
    private HuiShou_fragment huiShou_fragment;
    private Driver_fragment driver_fragment;
    private Oneseif_fragment oneseif_fragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        ButterKnife.bind(this);
        int state = getIntent().getIntExtra("state", 0);
        beak.setOnClickListener(this);
        dd_hs.setOnClickListener(this);
        siji.setOnClickListener(this);
        zisend.setOnClickListener(this);
        if(huiShou_fragment==null){
            huiShou_fragment = new HuiShou_fragment();
        }
        AddFragment(huiShou_fragment);
        if(state==1){
            if(driver_fragment==null){
                driver_fragment = new Driver_fragment();
            }
            AddFragment(driver_fragment);
            jiao1.setVisibility(View.GONE);
            jiao2.setVisibility(View.VISIBLE);
            jiao3.setVisibility(View.GONE);
        }else if(state==2){
            if(oneseif_fragment==null){
                oneseif_fragment = new Oneseif_fragment();
            }
            AddFragment(oneseif_fragment);
            jiao1.setVisibility(View.GONE);
            jiao2.setVisibility(View.GONE);
            jiao3.setVisibility(View.VISIBLE);
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.beak:
                finish();
                break;
            case R.id.dd_hs:
                if(huiShou_fragment==null){
                    huiShou_fragment = new HuiShou_fragment();
                }
                AddFragment(huiShou_fragment);
                jiao1.setVisibility(View.VISIBLE);
                jiao2.setVisibility(View.GONE);
                jiao3.setVisibility(View.GONE);
                break;
            case R.id.dd_siji:
                if(driver_fragment==null){
                    driver_fragment = new Driver_fragment();
                }
                AddFragment(driver_fragment);
                jiao1.setVisibility(View.GONE);
                jiao2.setVisibility(View.VISIBLE);
                jiao3.setVisibility(View.GONE);
                break;
            case R.id.dd_zisong:
                if(oneseif_fragment==null){
                    oneseif_fragment = new Oneseif_fragment();
                }
                AddFragment(oneseif_fragment);
                jiao1.setVisibility(View.GONE);
                jiao2.setVisibility(View.GONE);
                jiao3.setVisibility(View.VISIBLE);
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
            fragmentTransaction.add(R.id.dd_fl,f);
        }
        fragmentTransaction.show(f);
        fragmentTransaction.commit();
        currfit =f;
    }
}
