package com.example.mr.yihuanhuishou.driver.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.activity.Driver_DQH_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Driver_PSZ_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Driver_YWC_DetailsActivity;
import com.example.mr.yihuanhuishou.activity.Driver_qx_DetailsActivity;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.bean.Event_fragment;
import com.example.mr.yihuanhuishou.bean.PayResult;
import com.example.mr.yihuanhuishou.driver.weight.CommonPopupWindow;
import com.example.mr.yihuanhuishou.jsonbean.WX_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.ZFB_zhifu_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Danwei_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driver_Leibie_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Jiaoyan_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Order_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Order_PSZ_Bean;
import com.example.mr.yihuanhuishou.utils.API;
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
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import butterknife.Unbinder;

/**
 * Created by power on 2018/5/22.
 */

public class DriverOrder_PSZFragment extends BaseFragment implements BaseQuickAdapter.OnItemClickListener, View.OnClickListener {


    Unbinder unbinder;
    private BaseDialog mDialog;
    private BaseDialog.Builder mBuilder;
    private CommonPopupWindow popupWindow;
    private TextView leibieTv;
    private EditText shumuEt;
    private EditText zongjiaEt;
    private LinearLayout leibieLl;
    private TextView shumuTv;
    private List<Order_Bean.DataBean.DataListBean> mlist = new ArrayList<>();
    private List<Driver_Leibie_Bean.DataBean> list = new ArrayList<>();
    private List<Danwei_Bean.DataBean> zlist = new ArrayList<>();
    private RelativeLayout shumuRl;
    private TextView leftTv;
    private SharedPreferences sp;
  private int pageNo=1;
  private int pageCount=10;
    private AllOrderAdapter allOrderAdapter;
    private RecyclerView recyView;
    private SpringView spView;
    private SharedPreferences sp1;
    private int init_id;
    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    @Override
    protected int setContentView() {
        return R.layout.shangmen_fragment;
    }
    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        View contentView = getContentView();
        recyView = contentView.findViewById(R.id.recy_view);
        spView = contentView.findViewById(R.id.sp_view);
        sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);
        initdata();
        mlist.clear();
        list.clear();
        infoview();
    }
    private void initdata() {

        //刷新加载
        spView.setType(SpringView.Type.FOLLOW);
        spView.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        mlist.clear();
                        list.clear();
                        pageNo=1;
                        pageCount=10;
                        infoview();
                    }
                },0);
                spView.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        pageCount=10+pageCount;
                        infoview();
                    }
                },0);
                spView.onFinishFreshAndLoad();
            }
        });
        spView.setFooter(new DefaultFooter(getActivity()));
        spView.setHeader(new DefaultHeader(getActivity()));
        recyView.setNestedScrollingEnabled(false);
        recyView.setLayoutManager(new LinearLayoutManager(mContext));
        allOrderAdapter = new AllOrderAdapter(R.layout.item_all_order,mlist);
        recyView.setAdapter(allOrderAdapter);
        allOrderAdapter.setOnItemClickListener(this);
    }
    private void infoview() {
        sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);
            HttpParams params = new HttpParams();
            params.put("token",sp.getString(GGUtils.DrTOKEN,""));
            params.put("pageNo",pageNo);
            params.put("state",3);
            params.put("pageCount",pageCount);
            OkGo.<Order_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/list")
                    .tag(this)
                    .params(params)
                    .execute(new DialogCallback<Order_Bean>(getActivity(), Order_Bean.class) {
                        @Override
                        public void onSuccess(Response<Order_Bean> response) {
                            Order_Bean body = response.body();
                            String code = body.getCode()+"";
                            if (code.equals("200")) {
                                Log.e("==============","进来了111111111111");
                                List<Order_Bean.DataBean.DataListBean> dataList = body.getData().getDataList();
                                if(pageNo>1){
                                    if(dataList.size()>0){
                                        mlist.addAll(dataList);
                                    }
                                }else{
                                    mlist.addAll(dataList);
                                }
                                allOrderAdapter.notifyDataSetChanged();
                            } else {
                                ToastUtils.getToast(getActivity(), body.getMsg());
                            }
                        }
                    });

    }


    public class AllOrderAdapter extends BaseQuickAdapter<Order_Bean.DataBean.DataListBean,BaseViewHolder> {

        public AllOrderAdapter(int layoutResId, @Nullable List<Order_Bean.DataBean.DataListBean> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, final Order_Bean.DataBean.DataListBean item) {
            leftTv = helper.getView(R.id.item_left_tv);
            TextView name = helper.getView(R.id.name);
            TextView tel = helper.getView(R.id.tel);
            TextView order_tv = helper.getView(R.id.order_tv);
            TextView leibie_tv = helper.getView(R.id.leibie_tv);
            TextView address_tv = helper.getView(R.id.address_tv);
            TextView state_tv = helper.getView(R.id.state_tv);
            final TextView item_right_tv = helper.getView(R.id.item_right_tv);
            TextView item_left_tv = helper.getView(R.id.item_left_tv);
            name.setText(item.getEecRecyclersaddr().getName()+"：");
            tel.setText(item.getEecRecyclersaddr().getPhoneNumber());
            leibie_tv.setText(item.getVarieties());
            order_tv.setText(item.getOrderNumber());
            address_tv.setText(item.getEecRecyclersaddr().getDetailAddr());
                    helper.setText(R.id.state_tv,"配送中")
                            .setText(R.id.item_right_tv,"配送完成")
                            .setVisible(R.id.item_left_tv,false);

            item_right_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(item_right_tv.getText().toString().equals("配送完成")){
                        pswc(item.getId());
                    }
                }
            });
        }
    }
    //OrderDetailActivity.class
    @Override
    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent1 = new Intent(mContext, Driver_PSZ_DetailsActivity.class);
                intent1.putExtra("id", mlist.get(position).getId());
                intent1.putExtra("state", mlist.get(position).getState());
                startActivity(intent1);
    }
    private void pswc(int id) {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("id",id);
        OkGo.<Jiaoyan_Bean>post(MyUrls.BASEURL + "/driver/residentOrder/finsh")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Jiaoyan_Bean>(getActivity(), Jiaoyan_Bean.class) {
                    @Override
                    public void onSuccess(Response<Jiaoyan_Bean> response) {
                        Jiaoyan_Bean body = response.body();
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
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.cancle_tv:
                mDialog.dismiss();
                break;
        }
    }
    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
        EventBus.getDefault().removeAllStickyEvents();
    }
}
