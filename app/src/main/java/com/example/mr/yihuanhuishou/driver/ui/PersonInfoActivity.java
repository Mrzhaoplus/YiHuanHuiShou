package com.example.mr.yihuanhuishou.driver.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
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
import com.example.mr.yihuanhuishou.activity.HomePersonInfoActivity;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.weight.BaseSelectPopupWindow;
import com.example.mr.yihuanhuishou.driver.weight.CircleImageView;
import com.example.mr.yihuanhuishou.jsonbean.huishou.File_bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driv_Xiugai_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Geren_Bean;
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

public class PersonInfoActivity extends BaseActivity {

    @BindView(R.id.title_back_iv)
    ImageView titleBackIv;
    @BindView(R.id.title_content_tv)
    TextView titleContentTv;
    @BindView(R.id.title_more_bj)
    ImageView title_bj;
    @BindView(R.id.face_iv)
    CircleImageView faceIv;
    @BindView(R.id.face_rl)
    RelativeLayout faceRl;
    @BindView(R.id.name_tv)
    EditText nameTv;
    @BindView(R.id.name_rl)
    RelativeLayout nameRl;
    @BindView(R.id.age_tv)
    EditText ageTv;
    @BindView(R.id.age_rl)
    RelativeLayout ageRl;
    @BindView(R.id.rbt_nan)
    RadioButton rbtNan;
    @BindView(R.id.rbt_nv)
    RadioButton rbtNv;
    @BindView(R.id.chepai_tv)
    TextView chepaiTv;
    @BindView(R.id.chepai_rl)
    RelativeLayout chepaiRl;
    @BindView(R.id.gongsi_tv)
    TextView gongsiTv;
    @BindView(R.id.gongsi_rl)
    RelativeLayout gongsiRl;
    @BindView(R.id.rg)
    RadioGroup rg;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private List<LocalMedia> selectList = new ArrayList<>();
    private String cutPath;
    private BaseSelectPopupWindow popWiw;// 昵称 编辑框
    private SharedPreferences sp;
    private boolean flag;
    private String imags;
    private boolean driverSex;
    private int xingbie;
    private int xingbie1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_person_info);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.DrSP_NAME, MODE_PRIVATE);
        initView();
        infoview();
        boolean aBoolean = sp.getBoolean(GGUtils.DrSEX, false);
        if(aBoolean){
            xingbie1=1;
        }else{
            xingbie1=0;
        }
    }
    private void infoview() {
        HttpParams params3 = new HttpParams();
        params3.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        OkGo.<Geren_Bean>get(MyUrls.BASEURL + "/driverMine/driverDetails")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Geren_Bean>(PersonInfoActivity.this, Geren_Bean.class) {
                    @Override
                    public void onSuccess(Response<Geren_Bean> response) {
                        Geren_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            String driverImage = body.getData().getDriver().getDriverImage();
                            if (TextUtils.isEmpty(driverImage)) {
                                Glide.with(PersonInfoActivity.this).load(R.drawable.test_face_iv).into(faceIv);
                            } else {
                                Glide.with(PersonInfoActivity.this).load(driverImage).into(faceIv);
                            }
                            String user_name = body.getData().getDriver().getDriverNicekname();
                            if (TextUtils.isEmpty(user_name)) {
                                nameTv.setText("");
                            } else {
                                nameTv.setText(user_name);
                            }
                            int age = body.getData().getDriver().getDriverAge();
                            if (age == 0) {
                                ageTv.setText("");
                            } else {
                                ageTv.setText(age + "");
                            }
                            driverSex = body.getData().getDriver().getDriverSex();
                            if (driverSex) {
                                rbtNan.setChecked(true);
                                rbtNv.setChecked(false);
                                xingbie=1;
                            } else {
                                rbtNan.setChecked(false);
                                rbtNv.setChecked(true);
                                xingbie=0;
                            }
                            chepaiTv.setText(body.getData().getUsedriverPlateNumber());

                            List<Geren_Bean.DataBean.DriverCompanyBean> driverCompany = body.getData().getDriverCompany();
                            if(driverCompany.size()>0){
                                gongsiTv.setText(driverCompany.get(0).getEecCompany().getCompanyTitle());
                            }else{
                                gongsiTv.setText("暂无合作公司！");
                            }
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        }
                    }
                });
    }

    private void initView() {
        titleBackIv.setVisibility(View.VISIBLE);
        titleContentTv.setText("个人信息");

        nameTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if (!sp.getString(GGUtils.DrUSER_NAME, "").equals(editable.toString())) {
                    title_bj.setVisibility(View.VISIBLE);
                } else {
                    title_bj.setVisibility(View.GONE);
                }
            }
        });
        ageTv.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void afterTextChanged(Editable editable) {
                if (!TextUtils.isEmpty(editable.toString())) {
                    int i = Integer.parseInt(editable.toString());
                    if (sp.getInt(GGUtils.DrAGE, 0) != i) {
                        title_bj.setVisibility(View.VISIBLE);
                    } else {
                        title_bj.setVisibility(View.GONE);
                    }
                }
            }
        });
        rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {


                switch (i){
                    case R.id.rbt_nan:
                        xingbie=1;
                        break;
                    case R.id.rbt_nv:
                        xingbie=0;
                        break;
                }
                if(xingbie!=xingbie1){
                    title_bj.setVisibility(View.VISIBLE);
                }else{
                    title_bj.setVisibility(View.GONE);
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
                    File file = new File(cutPath);
                    shangchuan(file);
                    break;
            }
        }
    }

    private void shangchuan(File file) {
        OkGo.<File_bean>post(MyUrls.BASEURL + "/resident/upload")
                .tag(this)
                .params("images",file)
                .execute(new DialogCallback<File_bean>(PersonInfoActivity.this, File_bean.class) {
                    @Override
                    public void onSuccess(Response<File_bean> response) {
                        File_bean body = response.body();
                        List<String> data = body.getData();
                        imags = data.get(0);
                        title_bj.setVisibility(View.VISIBLE);
                        Log.e("======================", imags);
                        SharedPreferences.Editor edit = sp.edit();
                        edit.putString(GGUtils.DrIMAGE_xuan, imags);
                        edit.commit();
                    }
                });
    }
    /*private void showNickName(final String type) {
        if (popWiw == null) {
            popWiw = new BaseSelectPopupWindow(this, R.layout.edit_data);
            // popWiw.setOpenKeyboard(true);
            popWiw.setInputMethodMode(PopupWindow.INPUT_METHOD_NEEDED);

            popWiw.setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_RESIZE);
            popWiw.setShowTitle(false);
        }
        popWiw.setFocusable(true);
        InputMethodManager im = (InputMethodManager)
                getSystemService(Context.INPUT_METHOD_SERVICE);
        im.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);

        final ImageView send = (ImageView) popWiw.getContentView().findViewById(R.id.query_iv);
        final EditText edt = (EditText) popWiw.getContentView().findViewById(R.id.edt_content);
        final ImageView close = (ImageView) popWiw.getContentView().findViewById(R.id.cancle_iv);
        if (type.equals("name")){
            if (!TextUtils.isEmpty(edt.getText().toString())){
                edt.getText().clear();
            }
            edt.setHint("请输入昵称");
            edt.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        }
        if (type.equals("age")){
            if (!TextUtils.isEmpty(edt.getText().toString())){
                edt.getText().clear();
            }
            edt.setHint("请输入年龄");
            edt.setInputType(EditorInfo.TYPE_CLASS_NUMBER);
        }
        if (type.equals("chepai")){
            if (!TextUtils.isEmpty(edt.getText().toString())){
                edt.getText().clear();
            }
            edt.setHint("请输入车牌号");
            edt.setInputType(EditorInfo.TYPE_CLASS_TEXT);
        }
//        edt.setImeOptions(EditorInfo.IME_ACTION_SEND);
        edt.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before,
                                      int count) {
                if (TextUtils.isEmpty(edt.getText())) {
                    send.setEnabled(false);
                } else {
                    send.setEnabled(true);
                }
            }
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count,
                                          int after) {
            }

            @Override
            public void afterTextChanged(Editable s) {


            }
        });

        send.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(edt.getText().toString().trim())) {
                    // 昵称
                    String content = edt.getText().toString().trim();
                    if (type.equals("name")) nameTv.setText(content);
                    if (type.equals("age")) ageTv.setText(content);
                    if (type.equals("chepai")) chepaiTv.setText(content);
                    popWiw.dismiss();
                }
            }
        });
        close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                popWiw.dismiss();
            }
        });

        popWiw.showAtLocation(nameRl, Gravity.BOTTOM
                | Gravity.CENTER_HORIZONTAL, 0, 0);
    }*/

    @OnClick({R.id.title_more_bj, R.id.title_back_iv, R.id.face_rl, R.id.name_rl, R.id.age_rl, R.id.chepai_rl, R.id.gongsi_rl})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.title_back_iv:
                finish();
                break;
            case R.id.face_rl:
                showCameraDialog();
                break;
           /* case R.id.name_rl:
                showNickName("name");
                break;
            case R.id.age_rl:
                showNickName("age");
                break;
            case R.id.chepai_rl:
                showNickName("chepai");
                break;*/
            case R.id.title_more_bj:
                String name = nameTv.getText().toString().trim();
                if (!sp.getString(GGUtils.DrUSER_NAME, "").equals(name)) {
                    nameview(name);
                }
                String agetv = ageTv.getText().toString().trim();
                if (!TextUtils.isEmpty(agetv)) {
                    int i = Integer.parseInt(agetv);
                    if (sp.getInt(GGUtils.DrAGE, 0) != i) {
                        ageview(agetv);
                    }
                }
                if(xingbie!=xingbie1){
                    if(xingbie==1){
                        sexview("true");
                    }else{
                        sexview("false");
                    }

                }
                if(!sp.getString(GGUtils.DrIMAGE_path,"").equals(sp.getString(GGUtils.DrIMAGE_xuan,""))){
                    imagview(imags);
                }
                break;
            case R.id.gongsi_rl:
                startActivity(new Intent(this, HuishouCompanyActivity.class));
                break;
        }
    }
//头像M
    private void imagview(String imags) {
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("updCode", "driverImage");
        params.put("content", imags);
        OkGo.<Driv_Xiugai_Bean>post(MyUrls.BASEURL + "/driverMine/update")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driv_Xiugai_Bean>(PersonInfoActivity.this, Driv_Xiugai_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driv_Xiugai_Bean> response) {
                        Driv_Xiugai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(GGUtils.DrIMAGE_path,body.getData().getDriverImage());
                            edit.commit();
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        }
                    }
                });
    }
//性别
    private void sexview(String s) {
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("updCode", "driverSex");
        params.put("content", s);
        OkGo.<Driv_Xiugai_Bean>post(MyUrls.BASEURL + "/driverMine/update")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driv_Xiugai_Bean>(PersonInfoActivity.this, Driv_Xiugai_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driv_Xiugai_Bean> response) {
                        Driv_Xiugai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                           edit.putBoolean(GGUtils.DrSEX,body.getData().getDriverSex());
                            edit.commit();
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        }
                    }
                });
    }
    //修改age
    private void ageview(String agetv) {
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("updCode", "driverAge");
        params.put("content", agetv);
        OkGo.<Driv_Xiugai_Bean>post(MyUrls.BASEURL + "/driverMine/update")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driv_Xiugai_Bean>(PersonInfoActivity.this, Driv_Xiugai_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driv_Xiugai_Bean> response) {
                        Driv_Xiugai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putInt(GGUtils.DrAGE, body.getData().getDriverAge());
                            edit.commit();
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        }
                    }
                });

    }

    //修改name
    private void nameview(String name) {
        HttpParams params = new HttpParams();
        params.put("token", sp.getString(GGUtils.DrTOKEN, ""));
        params.put("updCode", "driverNicekname");
        params.put("content", name);
        OkGo.<Driv_Xiugai_Bean>post(MyUrls.BASEURL + "/driverMine/update")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driv_Xiugai_Bean>(PersonInfoActivity.this, Driv_Xiugai_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driv_Xiugai_Bean> response) {
                        Driv_Xiugai_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            SharedPreferences.Editor edit = sp.edit();
                            edit.putString(GGUtils.DrUSER_NAME, body.getData().getDriverNicekname());
                            edit.commit();
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(PersonInfoActivity.this, body.getMsg());
                        }
                    }
                });

    }
}
