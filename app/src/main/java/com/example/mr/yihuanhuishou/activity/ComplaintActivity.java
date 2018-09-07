package com.example.mr.yihuanhuishou.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.ui.DriverYijianActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ComplaintActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.tel)
    LinearLayout tel;
    @BindView(R.id.liuyan)
    LinearLayout liuyan;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        ButterKnife.bind(this);
        initview();

    }

    private void initview() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("意见建议");
        titleBackIv.setOnClickListener(this);
        tel.setOnClickListener(this);
        liuyan.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.tel:
               /* Intent intent = new Intent(Intent.ACTION_CALL);
                Uri data = Uri.parse("tel:010-6236555");
                intent.setData(data);
                if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                startActivity(intent);*/
                Intent intent = new Intent(Intent.ACTION_DIAL);
                Uri data = Uri.parse("tel:010-6236555");
                intent.setData(data);
                startActivity(intent);
                break;
            case R.id.liuyan:
                Intent intent1 = new Intent(ComplaintActivity.this, DriverYijianActivity.class);
                intent1.putExtra("type",1);
                startActivity(intent1);

                break;
        }
    }
}
