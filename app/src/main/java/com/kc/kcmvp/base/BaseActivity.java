package com.kc.kcmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

import com.kc.kcmvp.util.ToastUtil;
import com.tbruyelle.rxpermissions2.RxPermissions;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxAppCompatActivity;

/**
 * 基本界面类
 *
 * 实现了：
 * - 子类常用成员变量的声明
 * - rx请求和界面生命周期的同步
 * - 实现了基本view接口
 * - 隐藏onCreate方法
 * - 子类实现分配表达者方法
 * - 在生命周期中关联与断开表达者
 * */
public abstract class BaseActivity<P extends BaseContract.IBasePresenter> extends RxAppCompatActivity implements BaseContract.IBaseView {

    protected final String TAG = this.getClass().getSimpleName();

    protected P mPresenter;//通用表达者声明
    protected RxPermissions mRxPermissions;//权限请求

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置界面布局文件
        setContentView(getContentLayout());
        init();
    }

    @Override
    public void onResume() {
        super.onResume();
        //界面可以收到表达者返回数据
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        //界面断开接受数据
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

    //获得界面内容布局，由子类实现
    protected abstract int getContentLayout();
    //分配表达者，由子类实现
    protected abstract P assignPresenter();
    //界面初始化
    protected void init(){
        mPresenter = assignPresenter();
    }



    @Override
    public void onToast(String content) {
        ToastUtil.toast(this, content);
    }

    @Override
    public void onTitle(String text) {

    }

    @Override
    public void onLoading(boolean isLoading, String msg) {
        //显示默认加载
    }

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return bindToLifecycle();
    }
}
