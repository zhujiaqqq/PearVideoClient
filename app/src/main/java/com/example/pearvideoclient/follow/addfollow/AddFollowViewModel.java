package com.example.pearvideoclient.follow.addfollow;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.example.apublic.CommonCallBack;
import com.example.pearvideoclient.Api;
import com.example.pearvideoclient.entity.DomainListBean;
import com.example.pearvideoclient.entity.FollowUsersBean;
import com.example.pearvideoclient.http.RetrofitManager;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

import static com.example.pearvideoclient.Constants.SUCCESS;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 16:48
 * @ClassName: AddFollowViewModel
 */
public class AddFollowViewModel extends AndroidViewModel {
    private CompositeDisposable mCompositeDisposable;

    public AddFollowViewModel(@NonNull Application application) {
        super(application);
        mCompositeDisposable = new CompositeDisposable();
    }

    public void getDomainList(CommonCallBack<List<DomainListBean.DomainBean>, Void> callBack) {
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class)
                .getDomainList("ALL")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(this::changeDomainList)
                .subscribe(callBack::todo);
        mCompositeDisposable.add(disposable);
    }

    @NotNull
    private List<DomainListBean.DomainBean> changeDomainList(DomainListBean domainListBean) {
        List<DomainListBean.DomainBean> domainList = domainListBean.getDomainList();
        for (DomainListBean.DomainBean domainBean : domainList) {
            domainBean.setType(3);
        }
        domainList.get(0).setChecked(true);
        return domainList;
    }

    public void getUsers(String domain, Integer type,
                         CommonCallBack<List<FollowUsersBean.UserBean>, Void> callBack) {
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class)
                .getUsers(domain, "", type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .map(FollowUsersBean::getUserList)
                .subscribe(callBack::todo);

        mCompositeDisposable.add(disposable);
    }


    public void toOptUserFollow(String opt, String userId) {
        Disposable disposable = RetrofitManager.getInstance().createReq(Api.class).optUserFollow(opt, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(userFollowBean -> {
                    if (SUCCESS.equals(userFollowBean.getResultMsg())) {

                    }
                });
        mCompositeDisposable.add(disposable);
    }

    public void dispose() {
        mCompositeDisposable.dispose();
    }


}
