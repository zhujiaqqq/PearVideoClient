package com.example.pearvideoclient;

public interface BaseView<T> {

    void setPresenter(T presenter);

    /**
     * 显示loading
     */
    void showLoading();

    /**
     * 关闭loading
     */
    void cancelLoading();

    /**
     * 显示错误弹框
     *
     * @param loadingFail 错误文案
     */
    void showErrorToast(String loadingFail);
}
