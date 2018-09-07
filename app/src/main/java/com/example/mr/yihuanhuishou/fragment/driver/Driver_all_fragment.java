package com.example.mr.yihuanhuishou.fragment.driver;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.amap.api.maps2d.CameraUpdateFactory;
import com.amap.api.maps2d.model.LatLng;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Driver_all_adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.bean.Event_dingwei;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Order_Daijiedan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Zhece_Bean;
import com.example.mr.yihuanhuishou.utils.BaseDialog;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.DividerItemDecoration;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.liaoinstan.springview.container.DefaultFooter;
import com.liaoinstan.springview.container.DefaultHeader;
import com.liaoinstan.springview.widget.SpringView;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/7.
 */

public class Driver_all_fragment extends BaseFragment implements Driver_all_adapter.QuXiao {

    private SpringView sp_view;
    private RecyclerView recy_view;
    private String state="";
    private Driver_all_adapter driver_all_adapter;
     List<Order_Daijiedan_Bean.DataBean> mList=new ArrayList<>();
    private SharedPreferences sp;
    private int anInt;
    boolean flag = true;
    @SuppressLint("ValidFragment")
    public Driver_all_fragment(String state) {
        this.state = state;
    }

    public Driver_all_fragment() {
    }
    @Override
    protected int setContentView() {
        return R.layout.shangmen_fragment;
    }
    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
    }

    @Override
    public void onResume() {
        super.onResume();
        View contentView = getContentView();
        sp_view = contentView.findViewById(R.id.sp_view);
        recy_view = contentView.findViewById(R.id.recy_view);
        initdata();
        mList.clear();
        infoview();
    }

    private void infoview() {
        sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        if(state.equals("")){
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.TOKEN,""));
            params.put("distribution","0");
            OkGo.<Order_Daijiedan_Bean>post(MyUrls.BASEURL + "/recyclers/order/recoveryList")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Order_Daijiedan_Bean>(getActivity(), Order_Daijiedan_Bean.class) {
                        @Override
                        public void onSuccess(Response<Order_Daijiedan_Bean> response) {
                            Order_Daijiedan_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Order_Daijiedan_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    mList.addAll(data);
                                }
                                driver_all_adapter.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            }
                        }
                    });
        }else{
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.TOKEN,""));
            params.put("distribution","0");
            params.put("state",state);
            OkGo.<Order_Daijiedan_Bean>post(MyUrls.BASEURL + "/recyclers/order/recoveryList")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Order_Daijiedan_Bean>(getActivity(), Order_Daijiedan_Bean.class) {
                        @Override
                        public void onSuccess(Response<Order_Daijiedan_Bean> response) {
                            Order_Daijiedan_Bean body = response.body();
                            String code = body.getCode();
                            if (code.equals("200")) {
                                List<Order_Daijiedan_Bean.DataBean> data = body.getData();
                                if(data.size()>0){
                                    mList.addAll(data);
                                }
                                driver_all_adapter.notifyDataSetChanged();
                            } else if (code.equals("201")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("500")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("404")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("203")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            } else if (code.equals("204")) {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            }
                        }
                    });

        }

    }
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void fangfa(Event_fragment eveen) {
        int msg = eveen.getMsg();
        if (msg == 1) {
           infoview();
        }

    }
    private void initdata() {
        if (flag) {
            //注册
            EventBus.getDefault().register(this);
            flag = false;
        }



        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mList.clear();
                        infoview();
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
        sp_view.setFooter(new DefaultFooter(getActivity()));
        sp_view.setHeader(new DefaultHeader(getActivity()));
        //适配器
        recy_view.setNestedScrollingEnabled(false);
        recy_view.setLayoutManager(new LinearLayoutManager(getActivity()));
        recy_view.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));
        driver_all_adapter = new Driver_all_adapter(getActivity(),mList);
        recy_view.setAdapter(driver_all_adapter);
        driver_all_adapter.getclick(this);
    }

    @Override
    public void getcanleClick(int postion) {
        anInt =postion;
        shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion);
    }
    private void shumaDialog(int grary, int animationStyle) {
        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.quxiao_pop)
                //设置dialogpadding
                .setPaddingdp(0, 10, 0, 10)
                //设置显示位置
                .setGravity(grary)
                //设置动画
                .setAnimation(animationStyle)
                //设置dialog的宽高
                .setWidthHeightpx(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
                //设置触摸dialog外围是否关闭
                .isOnTouchCanceled(false)
                //设置监听事件
                .builder();
        dialog.show();
        TextView text_sure = dialog.getView(R.id.text_sure);
        TextView text_pause = dialog.getView(R.id.text_pause);
        final EditText reson = dialog.getView(R.id.reason);
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String trim = reson.getText().toString().trim();
                initview(trim,mList.get(anInt).getId());
                dialog.dismiss();
            }
        });
        //取消
        text_pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }
    private void initview(String reson, int id) {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("id",id);
        params.put("reason",reson);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/order/cancel")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(getActivity(), Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                            infoview();
                        } else if (code.equals("201")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("500")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("404")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("203")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        } else if (code.equals("204")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                        }
                    }
                });

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //注销
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();

    }
}
