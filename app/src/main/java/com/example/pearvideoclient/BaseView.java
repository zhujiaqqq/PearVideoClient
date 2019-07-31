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
}
