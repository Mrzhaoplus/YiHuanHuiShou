package com.example.mr.yihuanhuishou.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.weight.BaseSelectPopupWindow;
import com.example.mr.yihuanhuishou.driver.weight.CircleImageView;
import com.example.mr.yihuanhuishou.jsonbean.File_bean;
import com.example.mr.yihuanhuishou.jsonbean.Login_Bean;
import com.example.mr.yihuanhuishou.jsonbean.XiuGai_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
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
import butterknife.OnClick;

public class HomePersonInfoActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_more_bj)
    ImageView title_bj;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.face_iv)
    CircleImageView faceIv;
    @BindView(R.id.face_rl)
    RelativeLayout faceRl;

    @BindView(R.id.name_rl)
    RelativeLayout nameRl;
    @BindView(R.id.age_rl)
    RelativeLayout ageRl;
    @BindView(R.id.rbt_nan)
    RadioButton rbtNan;
    @BindView(R.id.rbt_nv)
    RadioButton rbtNv;
    @BindView(R.id.name_tv)
    EditText nameTv;
    @BindView(R.id.age_tv)
    EditText ageTv;
    @BindView(R.id.rg)
    RadioGroup rg;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private BaseSelectPopupWindow popWiw;// 昵称 编辑框
    private SharedPreferences sp;
    private String sex;
    private String user_name;
    private String age;
    private String xingbie=null;
    private File file;
    private String imags;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_person_info);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initView();
    }

    private void initView() {
        title_bj.setVisibility(View.VISIBLE);
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("个人信息");

        String imag = sp.getString(GGUtils.IMAGE_path, "");
        Glide.with(this).load(imag).into(faceIv);
        if(imag.equals("")){
            Glide.with(this).load(R.drawable.logo_xxxhdpi).into(faceIv);
        }

        user_name = sp.getString(GGUtils.USER_NAME, "");
        if (user_name.equals("")||user_name==null) {
            nameTv.setText("");
        } else {
            nameTv.setText(user_name);
        }
        age = sp.getString(GGUtils.AGE, "");
        if (age.equals("")||age==null) {
            ageTv.setText("");
        } else {
            ageTv.setText(age);
        }
        sex = sp.getString(GGUtils.SEX, "");
        if (sex.equals("0")) {
            rbtNan.setChecked(false);
            rbtNv.setChecked(true);

        } else if (sex.equals("1")) {
            rbtNan.setChecked(true);
            rbtNv.setChecked(false);

        } else {
            rbtNan.setChecked(false);
            rbtNv.setChecked(false);

        }
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbt_nan:
                        xingbie="1";
                        break;
                    case R.id.rbt_nv:
                        xingbie="0";
                        break;
                }
            }
        });


    }


    private void showCameraDialog() {
        mBuilder = new BaseDialog.Builder(this);
        mDialog = mBuilder.setViewId(R.layout.dialog_secltor_carmea)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(Gravity.BOTTOM)
                //设置动画
                .setAnimation(R.style.bottom_aniamtion)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        mDialog.setCancelable(true);
        mDialog.show();
        mDialog.getView(R.id.cancle_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
            }
        });
        mDialog.getView(R.id.camera_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                requestCamera();
            }
        });
        mDialog.getView(R.id.photo_tv).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mDialog.dismiss();
                requestPhoto();
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
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
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

    private void requestCamera() {
        PictureSelector.create(this)
                .openCamera(PictureMimeType.ofImage())// 单独拍照，也可录像或也可音频 看你传入的类型是图片or视频
                .theme(R.style.picture_default_style)// 主题样式设置 具体参考 values/styles
                .enableCrop(true)// 是否裁剪
                .compress(true)// 是否压缩
                .compressMode(PictureConfig.SYSTEM_COMPRESS_MODE)//系统自带 or 鲁班压缩 PictureConfig.SYSTEM_COMPRESS_MODE or LUBAN_COMPRESS_MODE
                .freeStyleCropEnabled(true)// 裁剪框是否可拖拽
                .circleDimmedLayer(true)// 是否圆形裁剪
                .showCropFrame(false)// 是否显示裁剪矩形边框 圆形裁剪时建议设为false
                .showCropGrid(false)// 是否显示裁剪矩形网格 圆形裁剪时建议设为false
                .scaleEnabled(false)// 裁剪是否可放大缩小图片
//                .glideOverride(160, 160)// glide 加载宽高，越小图片列表越流畅，但会影响列表图片浏览的清晰度
//                .selectionMedia(list)// 是否传入已选图片
//                .previewEggs(true)//预览图片时 是否增强左右滑动图片体验(图片滑动一半即可看到上一张是否选中)
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
                    Glide.with(this).load(cutPath).into(faceIv);
                    file = new File(cutPath);
                    infoview(file);
                    break;
            }
        }
    }

    private void infoview(File file) {
        OkGo.<File_bean>post(MyUrls.BASEURL + "/resident/upload")
                .tag(this)
                .params("images",file)
                .execute(new DialogCallback<File_bean>(HomePersonInfoActivity.this, File_bean.class) {
                        @Override
                        public void onSuccess(Response<File_bean> response) {
                            File_bean body = response.body();
                            List<String> data = body.getData();
                            imags = data.get(0);
                            Log.e("===================",imags);
                        }

                });


    }


    @OnClick({R.id.title_back_iv, R.id.face_rl, R.id.title_more_bj})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.face_rl:
                showCameraDialog();
                break;
            case R.id.title_more_bj:
                String name = nameTv.getText().toString().trim();
                String agetv = ageTv.getText().toString().trim();

                if(!user_name.equals(name)){
                    nameview(name);
                }
                if(!age.equals(agetv)){
                    ageview(agetv);
                }
                if((xingbie!=null&&!sex.equals(xingbie))){
                    sexview(xingbie);
                }
                if(!sp.getString(GGUtils.IMAGE_path,"").equals(imags)){
                    imagview(imags);
                }
                break;
        }
    }





    private void imagview(String imags) {
        HttpParams params3 = new HttpParams();
        params3.put("token",sp.getString(GGUtils.TOKEN,""));
        params3.put("updCode","photoPath");
        params3.put("content",imags);
        OkGo.<XiuGai_Bean>post(MyUrls.BASEURL + "/recyclers/update")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<XiuGai_Bean>(HomePersonInfoActivity.this, XiuGai_Bean.class) {
                    @Override
                    public void onSuccess(Response<XiuGai_Bean> response) {
                        XiuGai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(GGUtils.IMAGE_path,body.getData().getPhotoPath());
                            edit.commit();
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        }
                    }
                });

    }
    private void sexview(String xingbie) {
        HttpParams params2 = new HttpParams();
        params2.put("token",sp.getString(GGUtils.TOKEN,""));
        params2.put("updCode","sex");
        params2.put("content",xingbie);
        OkGo.<XiuGai_Bean>post(MyUrls.BASEURL + "/recyclers/update")
                .tag(this)
                .params(params2)
                .execute(new DialogCallback<XiuGai_Bean>(HomePersonInfoActivity.this, XiuGai_Bean.class) {
                    @Override
                    public void onSuccess(Response<XiuGai_Bean> response) {
                        XiuGai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(GGUtils.SEX,body.getData().getSex());
                            edit.commit();
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        }
                    }
                });
    }
    private void ageview(String age_nl) {
        HttpParams params1 = new HttpParams();
        params1.put("token",sp.getString(GGUtils.TOKEN,""));
        params1.put("updCode","age");
        params1.put("content",age_nl);
        OkGo.<XiuGai_Bean>post(MyUrls.BASEURL + "/recyclers/update")
                .tag(this)
                .params(params1)
                .execute(new DialogCallback<XiuGai_Bean>(HomePersonInfoActivity.this, XiuGai_Bean.class) {
                    @Override
                    public void onSuccess(Response<XiuGai_Bean> response) {
                        XiuGai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(GGUtils.AGE,body.getData().getAge()+"");
                            edit.commit();
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        }
                    }
                });

    }
    private void nameview(String name) {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("updCode","nickName");
        params.put("content",name);
        OkGo.<XiuGai_Bean>post(MyUrls.BASEURL + "/recyclers/update")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<XiuGai_Bean>(HomePersonInfoActivity.this, XiuGai_Bean.class) {
                    @Override
                    public void onSuccess(Response<XiuGai_Bean> response) {
                        XiuGai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(GGUtils.USER_NAME,body.getData().getNickname());
                            edit.commit();
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(HomePersonInfoActivity.this, body.getMsg());
                        }
                    }
                });


    }
}
