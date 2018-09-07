package com.example.mr.yihuanhuishou.activity;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Tupian_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.driver.weight.CircleImageView;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Xueqiu_Adapter_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Xuqiu_DetailActivity extends BaseActivity {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.touxiang)
    CircleImageView touxiang;
    @BindView(R.id.name)
    TextView name;
    @BindView(R.id.jili)
    TextView jili;
    @BindView(R.id.leixing)
    TextView leixing;
    @BindView(R.id.zhong)
    TextView zhong;
    @BindView(R.id.shijian)
    TextView shijian;
    @BindView(R.id.szdz)
    TextView szdz;
    @BindView(R.id.dizhi)
    TextView dizhi;
    @BindView(R.id.miaoshu)
    TextView miaoshu;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
    @BindView(R.id.qiang)
    Button qiang;
    @BindView(R.id.dizhi_rel)
    RelativeLayout dizhiRel;
    private int id;
    List<Xueqiu_Adapter_Bean.DataBean.EecWasteinfoBean.ListBean> list = new ArrayList<>();
    private Tupian_Adapter tupian_adapter;
    private double lat;
    private double lon;
    private double longitude;
    private double latitude;
    private String city;
    private String detailAddr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_xuqiu__detail);
        ButterKnife.bind(this);
        id = getIntent().getIntExtra("id", 0);
        lon = getIntent().getDoubleExtra("lon", 0);
        lat = getIntent().getDoubleExtra("lat", 0);
        list.clear();
        initdata();
        infoview();

        qiang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
                HttpParams params = new HttpParams();
                params.put("id", id);
                params.put("token", sp.getString(GGUtils.TOKEN, ""));
                OkGo.<Zhece_Bean>get(MyUrls.BASEURL + "/recyclers/order/robbingWasteOrder")
                        .tag(this)
                        .params(params)
                        .execute(new DialogCallback<Zhece_Bean>(Xuqiu_DetailActivity.this, Zhece_Bean.class) {
                            @Override
                            public void onSuccess(Response<Zhece_Bean> response) {
                                Zhece_Bean body = response.body();
                                String code = body.getCode() + "";
                                if (code.equals("200")) {
                                    Intent intent = new Intent(Xuqiu_DetailActivity.this, QiangDan_DetailsActivity.class);
                                    intent.putExtra("msg", body.getMsg());
                                    startActivity(intent);
                                    ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                                } else if (code.equals("201")) {
                                    ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                                } else if (code.equals("500")) {
                                    ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                                } else if (code.equals("404")) {
                                    ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                                } else if (code.equals("203")) {
                                    ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                                } else if (code.equals("204")) {
                                    ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                                }
                            }
                        });
            }
        });
    }
    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("id", id);
        params.put("longitude", lon);
        params.put("latitude", lat);
        OkGo.<Xueqiu_Adapter_Bean>get(MyUrls.BASEURL + "/resident/residentOrder/recyclersMapOrderInfo")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Xueqiu_Adapter_Bean>(Xuqiu_DetailActivity.this, Xueqiu_Adapter_Bean.class) {
                    @Override
                    public void onSuccess(Response<Xueqiu_Adapter_Bean> response) {
                        Xueqiu_Adapter_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            Xueqiu_Adapter_Bean.DataBean data = body.getData();
                            name.setText(data.getEecWasteinfo().getResidentaddr().getName());
                            leixing.setText(data.getEecWasteinfo().getVarieties());
                            zhong.setText(data.getEecWasteinfo().getCount() + "" + data.getEecWasteinfo().getUnit());
                            detailAddr = data.getEecWasteinfo().getResidentaddr().getDetailAddr();
                            dizhi.setText(data.getEecWasteinfo().getResidentaddr().getDetailAddr());
                            city = data.getEecWasteinfo().getResidentaddr().getCity();
                            miaoshu.setText(data.getEecWasteinfo().getDetailInfo());
                            longitude = body.getData().getEecWasteinfo().getResidentaddr().getLongitude();
                            latitude = body.getData().getEecWasteinfo().getResidentaddr().getLatitude();
                            List<Xueqiu_Adapter_Bean.DataBean.EecWasteinfoBean.ListBean> datalist = data.getEecWasteinfo().getList();
                            if (datalist.size() > 0) {
                                list.addAll(datalist);
                            }
                            tupian_adapter.notifyDataSetChanged();
                            Glide.with(Xuqiu_DetailActivity.this).load(body.getTouXiang()).into(touxiang);
                            int comeType = body.getData().getComeType();
                            if (comeType == 0) {
                                long comeData = body.getData().getComeData();
                                String dateToString = getDateToString(String.valueOf(comeData / 1000));
                                shijian.setText(dateToString);
                            } else if (comeType == 1) {
                                shijian.setText("立即上门");
                            }
                            int distance = body.getDistance();
                            Double aDouble =(double) distance / 1000;
                            String string = doubleToString(aDouble);
                            jili.setText(string+"Km");
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Xuqiu_DetailActivity.this, body.getMsg());
                        }
                    }
                });
    }

 /*   private static final double EARTH_RADIUS = 6378.137;//地球半径,单位千米

    private static double rad(double d) {
        return d * Math.PI / 180.0;
    }

    public static double getDistance(double lat1, double lng1, double lat2, double lng2) {
        double radLat1 = rad(lat1);
        double radLat2 = rad(lat2);
        double a = radLat1 - radLat2;
        double b = rad(lng1) - rad(lng2);
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) +
                Math.cos(radLat1) * Math.cos(radLat2) * Math.pow(Math.sin(b / 2), 2)));
        s = s * EARTH_RADIUS;
        s = Math.round(s * 10000) / 10000;
        return s;

    }
    *//*
     * 根据经纬度计算两点之间的距离（单位米）
     * *//*
    public static int algorithm(double longitude1, double latitude1, double longitude2, double latitude2) {
        double Lat1 = rad(latitude1); // 纬度
        double Lat2 = rad(latitude2);
        double a = Lat1 - Lat2;//两点纬度之差
        double b = rad(longitude1) - rad(longitude2); //经度之差
        double s = 2 * Math.asin(Math.sqrt(Math.pow(Math.sin(a / 2), 2) + Math.cos(Lat1) * Math.cos(Lat2) * Math.pow(Math.sin(b / 2), 2)));//计算两点距离的公式
        s = s * 6378137.0;//弧长乘地球半径（半径为米）
        s = Math.round(s * 10000d) / 10000d;//精确距离的数值
//        s = s/1000;//将单位转换为km，如果想得到以米为单位的数据 就除以1000
//        //四舍五入 保留一位小数
        DecimalFormat df = new DecimalFormat("0");
        return Integer.parseInt(df.format(s));
    }*/


    private void initdata() {
        dizhiRel.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View view) {
               showFXDialog(Gravity.BOTTOM, R.style.Bottom_Top_aniamtion);
           }

        });
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        recyView.setLayoutManager(new GridLayoutManager(this, 3));
        recyView.setNestedScrollingEnabled(false);
        tupian_adapter = new Tupian_Adapter(this, list);
        recyView.setAdapter(tupian_adapter);
    }

    //  时间戳转为日期  /年/月/日
    public static String getDateToString(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        long lcc_time = Long.valueOf(time);
        String format = sdf.format(new Date(lcc_time * 1000L));
        return format;
    }
   /* 检查手机上是否安装了指定的软件
     * @param context
     * @param packageName：应用包名
     * @return
             */
    public static boolean isAvilible(Context context, String packageName){
        //获取packagemanager
        final PackageManager packageManager = context.getPackageManager();
        //获取所有已安装程序的包信息
        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
        //用于存储所有已安装程序的包名
        List<String> packageNames = new ArrayList<String>();
        //从pinfo中将包名字逐一取出，压入pName list中
        if(packageInfos != null){
            for(int i = 0; i < packageInfos.size(); i++){
                String packName = packageInfos.get(i).packageName;
                packageNames.add(packName);
            }
        }
        //判断packageNames中是否有目标程序的包名，有TRUE，没有FALSE
        return packageNames.contains(packageName);
    }



    private void showFXDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(Xuqiu_DetailActivity.this);
        //设置触摸dialog外围是否关闭
        //设置监听事件
        final Dialog dialog = builder.setViewId(R.layout.xuan_ditu_pop)
                //设置dialogpadding
                .setPaddingdp(0, 0, 0, 0)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(true)
                //设置监听事件
                .builder();
        dialog.show();
        TextView baidu = dialog.findViewById(R.id.baidu);
        TextView gaode = dialog.findViewById(R.id.gaode);
        TextView guge = dialog.findViewById(R.id.guge);
        TextView cancle_tv = dialog.findViewById(R.id.cancle_tv);
        //唤醒百度地图
          baidu.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  dialog.dismiss();
                       if(isAvilible(Xuqiu_DetailActivity.this,"com.baidu.BaiduMap")){//传入指定应用包名
                    try {
                        Intent intent = Intent.getIntent("intent://map/direction?" +"destination=latlng:"+latitude+","+
                                longitude+"|name:"+detailAddr+        //终点
                                "&mode=driving&" +          //导航路线方式
                                "region="+city+
                                "&src="+detailAddr+"#Intent;scheme=bdapp;package=com.baidu.BaiduMap;end");
                        startActivity(intent); //启动调用
                    } catch (Exception e) {
                        Log.e("intent", e.getMessage());
                    }
                }else {//未安装
                    //market为路径，id为包名
                    //显示手机上所有的market商店
                    Toast.makeText(Xuqiu_DetailActivity.this, "您尚未安装百度地图", Toast.LENGTH_LONG).show();
                    Uri uri = Uri.parse("market://details?id=com.baidu.BaiduMap");
                    Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
              }
          });
          //唤醒高德地图
          gaode.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View view) {
                  dialog.dismiss();

                  if (isAvilible(Xuqiu_DetailActivity.this, "com.autonavi.minimap")) {
                      try{
                          Intent intent = Intent.getIntent("androidamap://navi?sourceApplication="+detailAddr+
                                  "&poiname="+detailAddr+"&lat="+latitude+"&lon="+longitude+"&dev=0");
                          startActivity(intent);
                      } catch (Exception e)
                      {e.printStackTrace(); }
                  }else{
                      Toast.makeText(Xuqiu_DetailActivity.this, "您尚未安装高德地图", Toast.LENGTH_LONG).show();
                      Uri uri = Uri.parse("market://details?id=com.autonavi.minimap");
                      Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                      startActivity(intent);
                  }
              }
          });

        //唤醒谷歌地图
        guge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
                if (isAvilible(Xuqiu_DetailActivity.this,"com.google.android.apps.maps")) {
                    Uri gmmIntentUri = Uri.parse("google.navigation:q="+latitude+","+longitude +", + Sydney +Australia");
                    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
                    mapIntent.setPackage("com.google.android.apps.maps");
                    startActivity(mapIntent);
                }else {
                    Toast.makeText(Xuqiu_DetailActivity.this, "您尚未安装谷歌地图", Toast.LENGTH_LONG).show();
                    Uri uri = Uri.parse("market://details?id=com.google.android.apps.maps");
                    Intent  intent = new Intent(Intent.ACTION_VIEW, uri);
                    startActivity(intent);
                }
            }
        });
        //取消
        cancle_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                 dialog.dismiss();
            }
        });
    }

    /**
     * double转String,保留小数点后两位
     * @param num
     * @return
     */
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
