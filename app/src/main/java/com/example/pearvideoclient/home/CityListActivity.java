package com.example.pearvideoclient.home;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.CityEntity;
import com.example.pearvideoclient.entity.CityListBean;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-23 22:36
 * @ClassName: CityListActivity
 */
public class CityListActivity extends AppCompatActivity {
    private static final String TAG = "CityListActivity";
    private TextView mTvChooseCity;
    private ImageView mIvback;
    private RecyclerView mRvCityList;

    private CityListAdapter mCityListAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city_list);
        initView();
        initData();
    }

    private void initView() {
        mTvChooseCity = findViewById(R.id.tv_choose_city);
        mIvback = findViewById(R.id.iv_back);
        mRvCityList = findViewById(R.id.rv_city_list);
    }

    private void initData() {
        try {
            Bundle bundle = getIntent().getExtras();
            CityListBean cityList = (CityListBean) bundle.getSerializable("cityList");
            CityListBean.ChannelBean autoLocalChannelInfo = cityList.getAutoLocalChannelInfo();
            List<CityListBean.ChannelBean> hotChannelList = cityList.getHotChannelList();
            List<CityListBean.ChannelBean> channelList = cityList.getChannelList();

            List<CityEntity> entities = new ArrayList<>();
            addAbleLocalCity(autoLocalChannelInfo, entities);
            addHotChannels(hotChannelList, entities);


        } catch (Exception e) {
            Log.e(TAG, "initData: " + e.getMessage());
        }

        mRvCityList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mCityListAdapter = new CityListAdapter(new ArrayList<>());
        mRvCityList.setAdapter(mCityListAdapter);
    }

    private void addHotChannels(List<CityListBean.ChannelBean> hotChannelList, List<CityEntity> entities) {
        CityEntity indexEntity = new CityEntity();
        indexEntity.setItemType(1);
        indexEntity.setIndex("热门城市");
        entities.add(indexEntity);
        for (CityListBean.ChannelBean channelBean : hotChannelList) {
            CityEntity entity = new CityEntity();
            entity.setChannelBean(channelBean);
            entity.setItemType(0);
            entities.add(entity);
        }
    }

    private void addAbleLocalCity(CityListBean.ChannelBean autoLocalChannelInfo, List<CityEntity> entities) {
        CityEntity indexEntity = new CityEntity();
        indexEntity.setItemType(1);
        indexEntity.setIndex("您当前可能在");
        entities.add(indexEntity);
        CityEntity cityEntity = new CityEntity();
        cityEntity.setItemType(0);
        cityEntity.setChannelBean(autoLocalChannelInfo);
    }
}
