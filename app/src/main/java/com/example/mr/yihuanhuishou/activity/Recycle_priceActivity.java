package com.example.mr.yihuanhuishou.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Recycler_prive_Adapter;
import com.example.mr.yihuanhuishou.base.BaseActivity;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fengzhuang_Id;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Fengzhuang_Money;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Huishou_sort_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Xitong_Recycler_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.google.gson.Gson;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Recycle_priceActivity extends BaseActivity implements Recycler_prive_Adapter.Xuan {

    @BindView(R.id.beak)
    ImageView beak;
    @BindView(R.id.recy_view)
    RecyclerView recyView;
   // List<Recycle_price_bean> list = new ArrayList<>();
    List<Huishou_sort_Bean.DataBean> list = new ArrayList<>();
    List<Xitong_Recycler_Bean.DataBean> mlist = new ArrayList<>();
    @BindView(R.id.spring_view)
    SpringView sp_view;
    @BindView(R.id.tijiao)
    Button tijiao;
    private SharedPreferences sp;
    private Recycler_prive_Adapter recycler_prive_adapter;
    private List<Integer> money=new ArrayList<>();
    private List<Integer> id=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle_price);
        ButterKnife.bind(this);
        sp = getSharedPreferences(GGUtils.SP_NAME, MODE_PRIVATE);
        initdate();
        initview();
    }

    private void initview() {
        list.clear();
        HttpParams params3 = new HttpParams();
        params3.put("token",sp.getString(GGUtils.TOKEN,""));
        OkGo.<Huishou_sort_Bean>post(MyUrls.BASEURL + "/recyclers/info/userAllVarieties")
                .tag(this)
                .params(params3)
                .execute(new DialogCallback<Huishou_sort_Bean>(Recycle_priceActivity.this, Huishou_sort_Bean.class) {
                    @Override
                    public void onSuccess(Response<Huishou_sort_Bean> response) {
                        Huishou_sort_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Huishou_sort_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                list.addAll(data);
                            }
                            // recycler_prive_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        }
                    }
                });
        mlist.clear();
        OkGo.<Xitong_Recycler_Bean>post(MyUrls.BASEURL + "/recyclers/info/allVarieties")
                .tag(this)
                .execute(new DialogCallback<Xitong_Recycler_Bean>(Recycle_priceActivity.this, Xitong_Recycler_Bean.class) {
                    @Override
                    public void onSuccess(Response<Xitong_Recycler_Bean> response) {
                        Xitong_Recycler_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Xitong_Recycler_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mlist.addAll(data);
                            }
                            recycler_prive_adapter.notifyDataSetChanged();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        }

                    }
                });


    }

    private void initdate() {
        tijiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               /* finish();
                ToastUtils.getToast(Recycle_priceActivity.this,"以保存");*/
               /* Log.e("============",money.toString());
                Log.e("============",id.toString());*/
                   if(id.size()>0&&money.size()>0){
                       intoview();
                   }else{
                       ToastUtils.getToast(Recycle_priceActivity.this,"至少保留一种回收种类");
                   }
            }
        });
        //刷新
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }
        });
        sp_view.setFooter(new DefaultFooter(this));
        sp_view.setHeader(new DefaultHeader(this));
        beak.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        //适配器
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(this));
        recyView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));
        recycler_prive_adapter = new Recycler_prive_Adapter(Recycle_priceActivity.this, mlist,list);
        recyView.setAdapter(recycler_prive_adapter);
        recycler_prive_adapter.getXuan(this);

    }
    private void intoview() {
        Fengzhuang_Id fengzhuang_id = new Fengzhuang_Id();
        fengzhuang_id.setId(id);
        Fengzhuang_Money fengzhuang_money = new Fengzhuang_Money();
        fengzhuang_money.setPrice(money);
        Gson gson = new Gson();
        String idd = gson.toJson(fengzhuang_id);
        String mon = gson.toJson(fengzhuang_money);
        Log.e("=================",idd.substring(7,idd.length()-2));
        Log.e("=================",mon.substring(10,mon.length()-2));
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("vids",idd.substring(7,idd.length()-2));
        params.put("unitPrices",mon.substring(10,mon.length()-2));
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/info/toUpdateUserVarieties")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(Recycle_priceActivity.this, Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                            finish();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        }else if (code.equals("202")) {
                            ToastUtils.getToast(Recycle_priceActivity.this, body.getMsg());
                        }
                    }
                });
    }


    @Override
    public void getXuan(List<Integer> pos, List<Integer> price) {
        id =pos;
        money =price;
    }
}
