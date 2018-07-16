package com.example.mr.yihuanhuishou.utils;

import android.app.Activity;

import com.example.mr.yihuanhuishou.R;
import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;
import com.lzy.okgo.request.base.Request;

import java.lang.reflect.Type;

import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * Created by Mr赵 on 2018/7/4.
 */

public abstract class DialogCallback<T> extends JsonCallback<T> {

    private LoadingDialog dialog;
    private Class<T> clazz;
    private Type type;


    private void initDialog(Activity activity) {
        dialog = new LoadingDialog(activity,R.style.dialog);
    }
    public DialogCallback(Class<T> clazz) {
        super();
        this.clazz = clazz;
        initDialog(activity);
    }
    public DialogCallback(Activity activity, Type type) {
        super();
        this.type = type;
        this.activity=activity;
        initDialog(activity);
    }

    @Override
    public void onStart(Request<T, ? extends Request> request) {
        if (dialog != null && !dialog.isShowing()) {
            dialog.show();
            dialog.setCanceledOnTouchOutside(false);
        }
        // 主要用于在所有请求之前添加公共的请求头或请求参数
        // 例如登录授权的 token
        // 使用的设备信息
        // 可以随意添加,也可以什么都不传
        // 还可以在这里对所有的参数进行加密，均在这里实现
        /*request.headers("header1", "HeaderValue1")//
                .params("params1", "ParamsValue1")//
                .params("token", "3215sdf13ad1f65asd4f3ads1f");*/
    }

    @Override
    public T convertResponse(Response response) throws Throwable {
        ResponseBody body = response.body();
        if (body == null) return null;

        T data = null;
        Gson gson = new Gson();
        JsonReader jsonReader = new JsonReader(body.charStream());
        if (type != null) data = gson.fromJson(jsonReader,type);
        if (clazz != null) data = gson.fromJson(jsonReader,clazz);
        return data;
    }

    @Override
    public void onFinish() {

        //网络请求结束后关闭对话框
        if (dialog != null && dialog.isShowing()) {
            dialog.dismiss();
        }
    }
}
