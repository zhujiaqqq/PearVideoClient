package com.example.pearvideoclient.author;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.AlbumContBean;
import com.scwang.smartrefresh.layout.api.RefreshLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-28 19:14
 * @ClassName: AlbumActivity
 */
public class AlbumActivity extends AppCompatActivity implements AlbumContract.View {

    private TextView mTvTitle;
    private RefreshLayout mRefreshLayout;
    private RecyclerView mRvAlbumList;

    private AlbumAdapter mAlbumAdapter;

    private AlbumContract.Presenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_album);
        initView();
        initData();
    }

    private void initView() {
        mTvTitle = findViewById(R.id.tv_title);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mRvAlbumList = findViewById(R.id.rv_album_list);

        findViewById(R.id.iv_back).setOnClickListener(v -> {
            finish();
        });
    }

    private void initData() {
        String albumId = getIntent().getStringExtra("albumId");

        new AlbumPresenter(this);

        mRvAlbumList.setLayoutManager(new GridLayoutManager(this, 2));
        mAlbumAdapter = new AlbumAdapter(R.layout.adapter_category_conts_item, new ArrayList<>());
        mRvAlbumList.setAdapter(mAlbumAdapter);

        mRefreshLayout.setOnLoadMoreListener(refreshLayout -> mPresenter.loadMoreAlbumConts());
        mRefreshLayout.setOnRefreshListener(refreshLayout -> mPresenter.refreshAlbumConts());

        mPresenter.subscribe();
        mPresenter.loadAlbumConts(albumId);
    }

    @Override
    public void setPresenter(AlbumContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void cancelLoading() {

    }

    @Override
    public void showErrorToast(String loadingFail) {

    }

    @Override
    public void setTitle(String name) {
        mTvTitle.setText(name);
    }

    @Override
    public void setAlbumCont(List<AlbumContBean.ContListBean> contList) {
        mAlbumAdapter.replaceData(contList);
    }

    @Override
    public void addAlbumCont(List<AlbumContBean.ContListBean> contList) {
        mAlbumAdapter.addData(contList);
    }

    @Override
    public void finishLoad(boolean b) {
        mRefreshLayout.finishLoadMore(b);
    }

    @Override
    public void finishRefresh(boolean b) {
        mRefreshLayout.finishRefresh(b);
    }
}
