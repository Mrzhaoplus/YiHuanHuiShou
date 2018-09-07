package com.example.mr.yihuanhuishou.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.amap.api.maps2d.model.BitmapDescriptorFactory;
import com.amap.api.maps2d.model.LatLng;
import com.amap.api.maps2d.model.MarkerOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.example.mr.yihuanhuishou.R;
import com.example.mr.yihuanhuishou.base.BaseFragment;
import com.example.mr.yihuanhuishou.driver.DriverHomeActivity;
import com.example.mr.yihuanhuishou.driver.fragment.HomeListFragment;
import com.example.mr.yihuanhuishou.driver.ui.HuiShouCompanyDetailActivity;
import com.example.mr.yihuanhuishou.jsonbean.siji.Deiver_Gongsi_shuishou_Bean;
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

/**
 * Created by Mr赵 on 2018/7/31.
 */

public class Driver_home_frim_fragment extends BaseFragment{
    /**
     * 设置Fragment要显示的布局
     *
     * @return 布局的layoutId
     */
    List<Deiver_Gongsi_shuishou_Bean.DataBean> mlist = new ArrayList<>();
    List<Deiver_Gongsi_shuishou_Bean.DataBean> list = new ArrayList<>();
    private RecyclerView recyclerView;
    private EditText search_ed;
    private TextView search_tv;
    private SharedPreferences sp;
    private HomeListAdapter listAdapter;

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
        sp = getActivity().getSharedPreferences(GGUtils.DrSP_NAME, Context.MODE_PRIVATE);
        mlist.clear();
        indata();
        infoview();
    }

    private void infoview() {

        HttpParams params = new HttpParams();
        params.put("token",sp.getString(GGUtils.DrTOKEN,""));
        OkGo.<Deiver_Gongsi_shuishou_Bean>get(MyUrls.BASEURL + "/driver/home/CompanyList")
                .tag(this)
                .params(params)
                .execute(new DialogCallback<Deiver_Gongsi_shuishou_Bean>(getActivity(), Deiver_Gongsi_shuishou_Bean.class) {
                    @Override
                    public void onSuccess(Response<Deiver_Gongsi_shuishou_Bean> response) {
                        Deiver_Gongsi_shuishou_Bean body = response.body();
                        String code = body.getCode()+"";
                        if (code.equals("200")) {
                            List<Deiver_Gongsi_shuishou_Bean.DataBean> data = body.getData();
                            if(data.size()>0){
                                mlist.addAll(data);
                                list.addAll(data);
                            }
                            listAdapter.notifyDataSetChanged();
                        }
                    }
                });

    }

    private void indata() {
        search_ed.setHint("请输入回收公司名称或类型");
        search_tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mlist.clear();
                String string = search_ed.getText().toString();

                if(TextUtils.isEmpty(string)){
                    mlist.addAll(list);
                }else{
                    for (int i=0;i<list.size();i++){
                        String name = list.get(i).getCompany().getCompanyTitle();
                        String varieties = list.get(i).getTypeStr();
                        if(name.indexOf(string)!=-1){
                            mlist.add(list.get(i));
                        }else if(varieties.indexOf(string)!=-1){
                            mlist.add(list.get(i));
                        }
                    }
                }
                listAdapter.notifyDataSetChanged();
            }
        });
        recyclerView.setNestedScrollingEnabled(false);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        listAdapter = new HomeListAdapter(R.layout.item_home_list, mlist);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                Intent intent = new Intent(mContext, HuiShouCompanyDetailActivity.class);
                intent.putExtra("id", mlist.get(position).getCompany().getId());
                startActivity(intent);
            }
        });
    }

    private class HomeListAdapter extends BaseQuickAdapter<Deiver_Gongsi_shuishou_Bean.DataBean, BaseViewHolder> {

        public HomeListAdapter(int layoutResId, @Nullable List<Deiver_Gongsi_shuishou_Bean.DataBean> data) {
            super(layoutResId, data);
        }
        @Override
        protected void convert(BaseViewHolder helper, Deiver_Gongsi_shuishou_Bean.DataBean item) {
            TextView name = helper.getView(R.id.name);
            TextView phone = helper.getView(R.id.phone);
            TextView sort = helper.getView(R.id.sort);
            TextView add = helper.getView(R.id.addrdss);
            name.setText(item.getCompany().getCompanyTitle());
            phone.setText(item.getCompany().getCorporationPhone());
            add.setText(item.getCompany().getCompanyAddr());
            sort.setText(item.getTypeStr());
        }
    }
}
