package com.kc.kcmvp.base;

import android.content.Context;

/**
 * Created by peter on 2018/3/10.
 * 基本表达者类
 *
 * 实现了
 * - 界面的应用上下文
 * - 界面的实例
 * - 实现了基本表达者接口
 * - 根据界面的生命周期连接或断开界面的联系
 */

public class BasePresenter<V extends BaseContract.IBaseView> implements BaseContract.IBasePresenter {

    protected final String TAG = this.getClass().getSimpleName();
    protected Context mContext =null;
    protected BaseContract.IBaseView mView = null;

    @Override
    public void attachView(BaseContract.IBaseView view) {
        mView = view;
        mContext = view.getApplicationContext();
    }

    @Override
    public void detachView() {
        mView = null;
        mContext = null;
    }
}
