package com.example.mr.yihuanhuishou.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.driver.fragment.HomeListFragment;
import com.example.mr.yihuanhuishou.driver.ui.QiangdanDetailActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Deiver_Gongsi_shuishou_Bean;
import com.example.mr.yihuanhuishou.jsonbean.siji.Driver_Recyclers_Bean;
import com.example.mr.yihuanhuishou.utils.DialogCallback;
import com.example.mr.yihuanhuishou.utils.GGUtils;
import com.example.mr.yihuanhuishou.utils.MyUrls;
import com.example.mr.yihuanhuishou.utils.TUtils;
import com.example.mr.yihuanhuishou.utils.ToastUtils;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.model.Response;

import java.util.ArrayList;
import java.util.List;

import butterknife.OnClick;

/**
 * Created by Mr赵 on 2018/7/31.
 */

public class Driver_home_person_fragment extends BaseFragment{
    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */

    List<Driver_Recyclers_Bean.DataBean> mlist = new ArrayList<>();
    List<Driver_Recyclers_Bean.DataBean> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText search_ed;
    private TextView search_tv;
    private double lat;
    private double lon;
    private String city;
    private SharedPreferences sp;
    private HomeListAdapter listAdapter;

    @SuppressLint("ValidFragment")
    public Driver_home_person_fragment(double lat, double lon,String city) {
        this.lat = lat;
        this.lon = lon;
        this.city = city;
    }

    public Driver_home_person_fragment() {
    }

    @Override
    protected int setContentView() {
        return R.layout.driver_home_person_fragment;
    }

    /**
     * 当视图初始化并且对用户可见的时候去真正的加载数据
     */
    @Override
    protected void lazyLoad() {
        View view = getContentView();
        recyclerView = view.findViewById(R.id.recyclerView);
        search_ed = view.findViewById(R.id.search_et);
        search_tv = view.findViewById(R.id.search_tv);
        indata();
        sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);
        list.clear();
        mlist.clear();
        infoview();
    }

    @Override
    public void onResume() {
        super.onResume();

    }
    private void infoview() {
        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        params.put("city",city);
        params.put("latitude",lat);
        params.put("longitude",lon);
        OkGo.<Driver_Recyclers_Bean>get(MyUrls.BASEURL + "/driver/residentOrder/getNearbyRecoveryOrder")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Driver_Recyclers_Bean>(getActivity(), Driver_Recyclers_Bean.class) {
                    @Override
                    public void onSuccess(Response<Driver_Recyclers_Bean> response) {
                        Driver_Recyclers_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Driver_Recyclers_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                list.addAll(data);
                                mlist.addAll(data);
                            }
                            listAdapter.notifyDataSetChanged();
                        }
                    }
                });
    }

    private void indata() {
        search_ed.setHint("请输入回收人员名称或类型");
        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                list.clear();
                String string = search_ed.getText().toString();
                if(TextUtils.isEmpty(string)){
                    list.addAll(mlist);
                }else{
                    for (int i=0;i<mlist.size();i++){
                        String name = mlist.get(i).getEecRecyclersaddr().getName();
                        String varieties = mlist.get(i).getVarieties();
                        if(name.indexOf(string)!=-1){
                            list.add(mlist.get(i));
                        }else if(varieties.indexOf(string)!=-1){
                            list.add(mlist.get(i));
                        }
                    }
                }
                listAdapter.notifyDataSetChanged();
            }

        });

        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        listAdapter = new HomeListAdapter(R.layout.item_home_list, list);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, QiangdanDetailActivity.class);
                Log.e("================",list.get(position).getId()+"");
                intent.putExtra("id",list.get(position).getId());
                startActivity(intent);
            }
        });
    }

    private class HomeListAdapter extends BaseQuickAdapter<Driver_Recyclers_Bean.DataBean, BaseViewHolder> {

        public HomeListAdapter(int layoutResId, @Nullable List<Driver_Recyclers_Bean.DataBean> data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, Driver_Recyclers_Bean.DataBean item) {
            TextView name = helper.getView(R.id.name);
            TextView phone = helper.getView(R.id.phone);
            TextView sort = helper.getView(R.id.sort);
            TextView add = helper.getView(R.id.addrdss);

            name.setText(item.getEecRecyclersaddr().getName());
            phone.setText(item.getEecRecyclersaddr().getPhoneNumber());
            sort.setText(item.getVarieties());
            add.setText(item.getEecRecyclersaddr().getDetailAddr());
        }
    }


}
