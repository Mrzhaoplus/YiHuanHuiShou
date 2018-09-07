package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.ZXingUtils;
import butterknife.BindView;
import butterknife.ButterKnife;

public class Bar_CodeActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.textView2)
    TextView textView2;
    @BindView(R.id.cord)
    ImageView cord;
    private SharedPreferences sp;
    private String content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bar__code);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        content = sp.getString(GGUtils.USER_NAME, "")+","+sp.getInt(GGUtils.USER_id,0);
        initdata();
    }


    private void initdata() {
        Bitmap bitmap = ZXingUtils.createImage(content, 300, 300, null);
        cord.setImageBitmap(bitmap);
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

    }
}
