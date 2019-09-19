package com.kc.kcmvp.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.kc.kcmvp.util.ToastUtil;
import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.RxFragment;


/**
 * 基本片段类
 *
 * 实现了：
 * - 子类常用成员变量的声明
 * - rx请求和界面生命周期的同步
 * - 实现了基本view接口
 * - 隐藏onCreateView方法
 * - 子类实现分配表达者方法
 * - 在生命周期中关联与断开表达者
 * */
public abstract class BaseFragment<P extends BaseContract.IBasePresenter> extends RxFragment implements BaseContract.IBaseView{

    protected final String TAG = this.getClass().getSimpleName();

    private View mView;//界面

    protected P mPresenter;//通用表达者声明

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null){
            mView = inflater.inflate(getContentLayout(), null);
            init(mView);
        }
        return mView;
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

    //获得布局文件
    protected abstract int getContentLayout();
    //分配表达者，由子类实现
    protected abstract P assignPresenter();
    /**
     * 初始化
     * @param view 界面
     * */
    protected void init(View view){
        mPresenter = assignPresenter();
    }

    @Override
    public void onToast(String content) {
        ToastUtil.toast(getActivity(), content);
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
