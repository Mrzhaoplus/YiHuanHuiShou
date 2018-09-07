package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.DriverHomeActivity;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyContants;
import com.example.mr.yihuanhuishou.utils.SpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity implements View.OnClickListener {
      @BindView(R.id.huishou_img)
      ImageView huishou_img;
      @BindView(R.id.siji_img)
      ImageView siji_img;
      private long preTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //点击事件
        huishou_img.setOnClickListener(this);
        siji_img.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.huishou_img:
                Intent intent = new Intent(MainActivity.this, LogonActivity.class);
                intent.putExtra("type",1);
                startActivity(intent);
                break;
            case R.id.siji_img:
                Intent intent1 = new Intent(MainActivity.this, LogonActivity.class);
                intent1.putExtra("type",2);
                startActivity(intent1);
                break;
        }
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

}
