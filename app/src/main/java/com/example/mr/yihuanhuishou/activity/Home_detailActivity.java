package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.utils.MyContants;
import com.example.mr.yihuanhuishou.utils.ToastUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class Home_detailActivity extends AppCompatActivity implements View.OnClickListener {
    @BindView(R.id.home_dingdan)
    ImageView dingdan;
    @BindView(R.id.home_address)
    TextView address;
    @BindView(R.id.jiao)
    LinearLayout jiao;
    @BindView(R.id.home_msg)
    ImageView msg;
    @BindView(R.id.home_lianxiren)
    ImageView lianxiren;
    @BindView(R.id.home_dingwei)
    ImageView dingwei;
    @BindView(R.id.home_xiangqing)
    ImageView xiangqing;
    @BindView(R.id.home_send)
    Button send;
    @BindView(R.id.home_jm)
    ImageView jumin;
    @BindView(R.id.home_card)
    ImageView card;
    @BindView(R.id.home_firm)
    ImageView firm;
    @BindView(R.id.home_ddxx)
    LinearLayout dd_msg;
    @BindView(R.id.home_qd)
    TextView qd;

    private long preTime;
    boolean flag=true;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_detail);
        ButterKnife.bind(this);
        //点击事件
        dingdan.setOnClickListener(this);
        jiao.setOnClickListener(this);
        msg.setOnClickListener(this);
        lianxiren.setOnClickListener(this);
        dingwei.setOnClickListener(this);
        xiangqing.setOnClickListener(this);
        send.setOnClickListener(this);
        jumin.setOnClickListener(this);
        card.setOnClickListener(this);
        firm.setOnClickListener(this);
        qd.setOnClickListener(this);
        if(flag){
            //注册
            EventBus.getDefault().register(this);
            flag=false;
        }
    }
    @Subscribe(threadMode = ThreadMode.MAIN,sticky = true)
    public void fangfa(Event_dingwei eveen){
        String city = eveen.getCity();
        address.setText(city);
    }

    /*
      返回键退出程序
    */
    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > preTime + 2000) {
            Toast.makeText(this, "再按一次退出", Toast.LENGTH_SHORT).show();
            preTime = System.currentTimeMillis();
        } else {
            super.onBackPressed();//相当于finish()
            BaseActivity.realBack();//删除所有引用
        }

    }

    @Override
    public void onClick(View view) {
         switch (view.getId()){
             //公司
             case R.id.home_firm:
                 firm.setImageResource(R.drawable.home_firm_xuanzhong);
                 card.setImageResource(R.drawable.home_qiche_ccchdpi);
                 jumin.setImageResource(R.drawable.home_jm_xxxhdpi);
                 send.setVisibility(View.VISIBLE);
                 dd_msg.setVisibility(View.GONE);
                 break;
             //汽车
             case R.id.home_card:
                 firm.setImageResource(R.drawable.home_gongsi_xxxhdpi);
                 card.setImageResource(R.drawable.home_card_icon_xxxhdpi);
                 jumin.setImageResource(R.drawable.home_jm_xxxhdpi);
                 break;
             //居民
             case R.id.home_jm:
                 firm.setImageResource(R.drawable.home_gongsi_xxxhdpi);
                 card.setImageResource(R.drawable.home_qiche_ccchdpi);
                 jumin.setImageResource(R.drawable.home_jm_icon_xxxhdpi);
                 send.setVisibility(View.GONE);
                 dd_msg.setVisibility(View.VISIBLE);
                 break;
             //发送配送消息
             case R.id.home_send:
                 ToastUtils.getToast(Home_detailActivity.this,"发送");
                 break;
             //订单
             case R.id.home_dingdan:
                 Intent intent2 = new Intent(Home_detailActivity.this, OrderActivity.class);
                 startActivity(intent2);
                 break;
             //消息
             case R.id.home_msg:
                 ToastUtils.getToast(Home_detailActivity.this,"消息");
                 break;
             //联系人
             case R.id.home_lianxiren:
                 ToastUtils.getToast(Home_detailActivity.this,"联系人");
                 break;
             //定位
             case R.id.home_dingwei:
                 ToastUtils.getToast(Home_detailActivity.this,"定位");

                 break;
             //详情
             case R.id.home_xiangqing:
                 ToastUtils.getToast(Home_detailActivity.this,"详情");
                 break;
             //抢单
             case R.id.home_qd:
                 Intent intent1 = new Intent(Home_detailActivity.this, Bill_DetailActivity.class);
                 startActivity(intent1);
                 break;
             //点击坐标
             case R.id.jiao:
                 Intent intent = new Intent(Home_detailActivity.this, LocationActivity.class);
                 intent.putExtra("address",address.getText().toString());
                 startActivity(intent);
                 break;
         }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
