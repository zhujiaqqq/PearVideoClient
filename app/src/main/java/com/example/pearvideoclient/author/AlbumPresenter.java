package com.example.pearvideoclient.author;

import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.Constants;
import com.example.pearvideoclient.entity.AlbumContBean;
import com.example.pearvideoclient.http.RetrofitManager;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 19:34
 * @ClassName: AlbumPresenter
 */
public class AlbumPresenter implements AlbumContract.Presenter {
    private AlbumContract.View mView;
    private int start;
    private String mAlbumId;
    private CompositeDisposable mCompositeDisposable;

    AlbumPresenter(AlbumContract.View view) {
        mView = view;
        mView.setPresenter(this);
        mCompositeDisposable = new CompositeDisposable();
    }

    @Override
    public void subscribe() {
        mCompositeDisposable.clear();
    }

    @Override
    public void unsubscribe() {
        mCompositeDisposable.dispose();
    }

    @Override
    public void loadAlbumConts(String albumId) {
        mAlbumId = albumId;
        start = 0;
        Disposable disposable = loadAlbumConts(mAlbumId, start, Constants.COMMON);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void loadMoreAlbumConts() {
        start += 10;
        Disposable disposable = loadAlbumConts(mAlbumId, start, Constants.LOAD_MORE);
        mCompositeDisposable.add(disposable);
    }

    @Override
    public void refreshAlbumConts() {
        start = 0;
        Disposable disposable = loadAlbumConts(mAlbumId, start, Constants.LOAD_REFRESH);
        mCompositeDisposable.add(disposable);
    }

    /**
     * 加载专辑列表
     *
     * @param albumId  专辑ID
     * @param start    索引
     * @param loadType 加载类型
     * @return disposable
     */
    private Disposable loadAlbumConts(String albumId, int start, @Constants.LoadType int loadType) {
        return RetrofitManager.getInstance().createReq(Api.class)
                .getAlbumConts(albumId, start)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(albumContBean -> {
                    List<AlbumContBean.ContListBean> contList = albumContBean.getContList();
                    if (Constants.COMMON == loadType) {
                        AlbumContBean.AlbumInfoBean albumInfo = albumContBean.getAlbumInfo();
                        mView.setTitle(albumInfo.getName());
                        mView.setAlbumCont(contList);
                    } else if (Constants.LOAD_REFRESH == loadType) {
                        mView.setAlbumCont(contList);
                    } else if (Constants.LOAD_MORE == loadType) {
                        mView.addAlbumCont(contList);
                    }
                }, throwable -> {
                    if (Constants.LOAD_MORE == loadType) {
                        mView.finishLoad(false);
                    } else if (Constants.LOAD_REFRESH == loadType) {
                        mView.finishRefresh(false);
                    }
                }, () -> {
                    if (Constants.LOAD_MORE == loadType) {
                        mView.finishLoad(true);
                    } else if (Constants.LOAD_REFRESH == loadType) {
                        mView.finishRefresh(true);
                    }
                });
    }
}
