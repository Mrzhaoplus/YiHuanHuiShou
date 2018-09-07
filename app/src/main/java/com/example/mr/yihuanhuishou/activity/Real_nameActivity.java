package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.File_bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Shiming_Detail;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.luck.picture.lib.PictureSelector;
import com.luck.picture.lib.compress.Luban;
import com.luck.picture.lib.config.PictureConfig;
import com.luck.picture.lib.config.PictureMimeType;
import com.luck.picture.lib.entity.LocalMedia;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Real_nameActivity extends BaseActivity implements View.OnClickListener {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.paizhao)
    ImageView paizhao;
    @BindView(R.id.img_sfz)
    ImageView imgSfz;
    @BindView(R.id.tijiao)
    Button tijiao;
    @BindView(R.id.back_cord)
    TextView backCord;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private String imagpath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_real_name);
        ButterKnife.bind(this);
        initdate();
    }

    @Override
    protected void onResume() {
        super.onResume();
        shiming_detail();
    }

    private void shiming_detail() {
        SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.TOKEN, ""));
        OkGo.<Shiming_Detail>post(MyUrls.BASEURL + "/recyclers/info/authenticationDetail")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Shiming_Detail>(Real_nameActivity.this, Shiming_Detail.class) {
                    @Override
                    public void onSuccess(Response<Shiming_Detail> response) {
                        Shiming_Detail body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            Shiming_Detail.DataBean data = body.getData();
                            if (data != null) {
                                Glide.with(Real_nameActivity.this).load(data.getIdcard()).into(imgSfz);
                                paizhao.setVisibility(View.GONE);
                                tijiao.setVisibility(View.GONE);
                                backCord.setVisibility(View.GONE);
                            }
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        }
                    }
                });


    }

    private void initdate() {
        beak.setOnClickListener(this);
        paizhao.setOnClickListener(this);
        tijiao.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.beak:
                finish();
                break;
            case R.id.paizhao:
                requestPhoto();
                break;
            case R.id.tijiao:
                if(TextUtils.isEmpty(imagpath)){
                    ToastUtils.getToast(Real_nameActivity.this,"请上传手持身份证照片。");
                }else{
                    initview();
                }
                break;
        }

    }

    private void initview() {
        SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.TOKEN, ""));
        params.put("idCard", imagpath);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/info/authentication")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(Real_nameActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Real_nameActivity.this, body.getMsg());
                        }
                    }
                });

    }

    private void requestPhoto() {
        // 进入相册 以下是例子：不需要的api可以不写
        PictureSelector.create(this)
                .openGallery(PictureMimeType.ofImage())// 全部.PictureMimeType.ofAll()、图片.ofImage()、视频.ofVideo()、音频.ofAudio()
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles   用法：R.style.picture.white.style
                .maxSelectNum(1)// 最大图片选择数量
                .minSelectNum(1)// 最小选择数量
                .imageSpanCount(4)// 每行显示个数
                .selectionMode(PictureConfig.SINGLE)// 多选 or 单选 PictureConfig.SINGLE
                .previewImage(true)// 是否可预览图片
                .previewVideo(false)// 是否可预览视频
                .enablePreviewAudio(false) // 是否可播放音频
                .compressGrade(Luban.THIRD_GEAR)// luban压缩档次，默认3档 Luban.FIRST_GEAR、Luban.CUSTOM_GEAR
                .isCamera(true)// 是否显示拍照按钮
                .isZoomAnim(true)// 图片列表点击 缩放效果 默认true
                //.setOutputCameraPath("/CustomPath")// 自定义拍照保存路径
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                //.sizeMultiplier(0.5f)// glide 加载图片大小 0~1之间 如设置 .glideOverride()无效
                .glideOverride(200, 200)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .withAspectRatio(aspect_ratio_x, aspect_ratio_y)// 裁剪比例 如16:9 3:2 3:4 1:1 可自定义
                .hideBottomControls(true)// 是否显示uCrop工具栏，默认不显示
                .isGif(false)// 是否显示gif图片
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
//               // .circleDimmedLayer(true)// 是否圆形裁剪
//               // .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
//               // .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .openClickSound(false)// 是否开启点击声音
//                .selectionMedia(list)// 是否传入已选图片
//                        .videoMaxSecond(15)
//                        .videoMinSecond(10)
                //.previewEggs(false)// 预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
                //.cropCompressQuality(90)// 裁剪压缩质量 默认100
                //.compressMaxKB()//压缩最大值kb compressGrade()为Luban.CUSTOM_GEAR有效
                //.compressWH() // 压缩宽高比 compressGrade()为Luban.CUSTOM_GEAR有效
                //.cropWH()// 裁剪宽高比，设置如果大于图片本身宽高则无效
                //.rotateEnabled() // 裁剪是否可旋转图片
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
                //.videoQuality()// 视频录制质量 0 or 1
                //.videoSecond()//显示多少秒以内的视频or音频也可适用
                //.recordVideoSecond()//录制视频秒数 默认60s
                .forResult(PictureConfig.CHOOSE_REQUEST);//结果回调onActivityResult code
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case PictureConfig.CHOOSE_REQUEST:
                    // 图片选择结果回调
                    selectList = PictureSelector.obtainMultipleResult(data);
                    cutPath = selectList.get(0).getCutPath();
                    Glide.with(this).load(cutPath).into(imgSfz);
                    paizhao.setVisibility(View.GONE);
                    File file = new File(cutPath);
                    infoview(file);
                    break;
            }
        }
    }

    private void infoview(File file) {
        OkGo.<File_bean>post(MyUrls.BASEURL + "/resident/upload")
                .tag(this)
                .params("images", file)
                .execute(new DialogCallback<File_bean>(Real_nameActivity.this, File_bean.class) {
                    @Override
                    public void onSuccess(Response<File_bean> response) {
                        File_bean body = response.body();
                        List<String> data = body.getData();
                        imagpath = data.get(0);
                        Log.e("======================", imagpath);
                    }
                });


    }
}
