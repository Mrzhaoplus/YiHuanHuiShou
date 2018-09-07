package com.example.mr.yihuanhuishou.fragment.huishou;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.adapter.Pop_Adapter;
import com.example.mr.yihuanhuishou.adapter.Shangmen_Adapter;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.jsonbean.huishou.Recy_Shangmen_Bean;
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

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Mr赵 on 2018/5/4.
 */

public class Shangmen_fragment extends BaseFragment implements Shangmen_Adapter.Queren, Shangmen_Adapter.QuXiao {

    private SpringView sp_view;
    private RecyclerView recy_view;
    private int pageNo=1;
    private int pageCount=10;
    List<Recy_Shangmen_Bean.DataListBean>list=new ArrayList<>();
    private Shangmen_Adapter shangmen_adapter;
    private PopupWindow popupWindow;
    private View inflate;
    private ListView list_view;
    private LinearLayout view02;
    private TextView unit;
    private int anInt;
    private int aaaa;

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
        sp_view = contentView.findViewById(R.id.sp_view);
        recy_view = contentView.findViewById(R.id.recy_view);
        initdata();

    }
    @Override
    public void onResume() {
        super.onResume();
        list.clear();
         infoview1();
    }
    private void infoview1() {
        SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("pageNo",pageNo);
        params.put("pageCount",pageCount);
        params.put("state","1");
        OkGo.<Recy_Shangmen_Bean>post(MyUrls.BASEURL + "/recyclers/order/wasteList")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Recy_Shangmen_Bean>(getActivity(), Recy_Shangmen_Bean.class) {
                    @Override
                    public void onSuccess(Response<Recy_Shangmen_Bean> response) {
                        Recy_Shangmen_Bean body = response.body();
                        String code = body.getCode() + "";
                        if (code.equals("200")) {
                            List<Recy_Shangmen_Bean.DataListBean> dataList = body.getDataList();
                            if(pageNo>1){
                                if(dataList.size()>0){
                                    list.addAll(dataList);
                                }
                            }else{
                                list.addAll(dataList);
                            }
                            shangmen_adapter.notifyDataSetChanged();
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

    private void initdata() {
        //刷新加载
        sp_view.setType(SpringView.Type.FOLLOW);
        sp_view.setListener(new SpringView.OnFreshListener() {
            @Override
            public void onRefresh() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo=1;
                        pageCount=10;
                        list.clear();
                        infoview1();
                    }
                },0);
                sp_view.onFinishFreshAndLoad();
            }

            @Override
            public void onLoadmore() {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        pageNo++;
                        pageCount=pageCount+10;

                        infoview1();
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
        shangmen_adapter = new Shangmen_Adapter(mContext,list);
        recy_view.setAdapter(shangmen_adapter);
        shangmen_adapter.queren(this);
        shangmen_adapter.quxiao(this);
    }

    @Override
    public void getquerenClick(int postion) {
         shumaDialog(Gravity.CENTER,R.style.Alpah_aniamtion,postion);
    }
    private void shumaDialog(int grary, int animationStyle, final int post) {

        BaseDialog.Builder builder = new BaseDialog.Builder(getActivity());
        final BaseDialog dialog = builder.setViewId(R.layout.qrsh_pop)
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
        final TextView shuliang = dialog.getView(R.id.shuliang);
        final TextView danjia = dialog.getView(R.id.danjia);
        final TextView zongjia = dialog.getView(R.id.zongjia);
        RadioGroup radiogroup = dialog.getView(R.id.rg);
        RadioButton rbt_shi = dialog.getView(R.id.rbt_shi);
        RadioButton rbt_fou = dialog.getView(R.id.rbt_fou);
        shuliang.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())&&!TextUtils.isEmpty(danjia.getText().toString())){
                    double v = Double.parseDouble(editable.toString());
                    double v1 = Double.parseDouble(danjia.getText().toString());
                    String string = doubleToString((v * v1));
                    zongjia.setText(string);
                }
            }
        });
        danjia.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                if(!TextUtils.isEmpty(editable.toString())&&!TextUtils.isEmpty(shuliang.getText().toString())){
                    double v = Double.parseDouble(editable.toString());
                    double v1 = Double.parseDouble(shuliang.getText().toString());
                    String string = doubleToString((v * v1));
                    zongjia.setText(string);
                }
            }
        });
        radiogroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i){
                    case R.id.rbt_shi:
                        aaaa = 1;
                        break;
                    case R.id.rbt_fou:
                        aaaa = 0;
                        break;
                }
            }
        });
        unit = dialog.getView(R.id.unit);
        view02 = dialog.getView(R.id.view_02);
        TextView text_pause = dialog.getView(R.id.text_pause);
        //pop框
        view02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                infoview();
            }
        });
        //知道了
        text_sure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("==================",aaaa+"");
                String shu = shuliang.getText().toString().trim();
                String dan = danjia.getText().toString().trim();
                String zong = zongjia.getText().toString().trim();
                String danwei = unit.getText().toString();
                if(!TextUtils.isEmpty(shu)&&!TextUtils.isEmpty(zong)&&!TextUtils.isEmpty(dan)){
                    /*int i = Integer.parseInt(shu);
                    int i1 = Integer.parseInt(dan);
                    if(i>0&&i1>0){
                    }else{
                        ToastUtils.getToast(getActivity(),"数量或者单价不可为空");
                    }*/
                    queren(post,shu,dan,zong,danwei,aaaa);
                    dialog.dismiss();
                }else{
                    ToastUtils.getToast(getActivity(),"格式不符");
                }

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

    private void queren(int post,String shu,String dan,String zong,String danwei,int aaaa) {
        SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("id",list.get(post).getId());
        params.put("count",shu);
        params.put("unitPrice",dan);
        params.put("totalMoney",zong);
        params.put("isGiveCurrency",aaaa);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/order/receipt")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(getActivity(), Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                            infoview1();
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

    private void infoview() {
        if (popupWindow == null) {
            inflate = LayoutInflater.from(getActivity()).inflate(R.layout.popwindow_listview, null);
            list_view = inflate.findViewById(R.id.list_view);
            popupWindow = new PopupWindow(inflate, 180, RadioGroup.LayoutParams.WRAP_CONTENT);
        }
        final List<String> list = new ArrayList<>();
        list.add("个");
        list.add("包");
        list.add("件");
        list.add("kg");
        list.add("g");
        list.add("mg");
        Pop_Adapter pop_adapter = new Pop_Adapter(getActivity(), list);
        list_view.setAdapter(pop_adapter);
        popupWindow.setFocusable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popupWindow.showAsDropDown(view02, 0, 0);
        list_view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                popupWindow.dismiss();
                unit.setText(list.get(i));
            }
        });
    }

    @Override
    public void getcanleClick(int postion) {
        anInt =postion;
        shumaDialog1(Gravity.CENTER,R.style.Alpah_aniamtion);
    }
    private void shumaDialog1(int grary, int animationStyle) {
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
                if(!TextUtils.isEmpty(trim)){
                    initview(trim,list.get(anInt).getId());
                    dialog.dismiss();
                }else{
                    ToastUtils.getToast(getActivity(),"取消原因不能为空！");
                }

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

    private void initview(String trim, int id) {
        SharedPreferences sp = getActivity().getSharedPreferences(GGUtils.SP_NAME, Context.MODE_PRIVATE);
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.TOKEN,""));
        params.put("id",id);
        params.put("cancelEeason",trim);
        OkGo.<Zhece_Bean>post(MyUrls.BASEURL + "/recyclers/order/recyclersCancelWasteOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Zhece_Bean>(getActivity(), Zhece_Bean.class) {
                    @Override
                    public void onSuccess(Response<Zhece_Bean> response) {
                        Zhece_Bean body = response.body();
                        String code = body.getCode();
                        if (code.equals("200")) {
                            ToastUtils.getToast(getActivity(), body.getMsg());
                            infoview1();
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
    public static String doubleToString(double num){
        //使用0.00不足位补0，#.##仅保留有效位
        return new DecimalFormat("0.00").format(num);
    }
}
