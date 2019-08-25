package com.example.pearvideoclient.home;

import android.content.Intent;
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

import static com.example.pearvideoclient.home.HomeFragment.RESULT_CODE;

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
            String currentCity = bundle.getString("currentCity");
            CityListBean.ChannelBean autoLocalChannelInfo = cityList.getAutoLocalChannelInfo();
            List<CityListBean.ChannelBean> hotChannelList = cityList.getHotChannelList();
            List<CityListBean.ChannelBean> channelList = cityList.getChannelList();

            List<CityEntity> entities = new ArrayList<>();
            addAbleLocalCity(autoLocalChannelInfo, entities);
            addHotChannels(hotChannelList, entities);
            addChannels(channelList, entities);


            mRvCityList.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
            mCityListAdapter = new CityListAdapter(entities);
            mCityListAdapter.setOnItemChildClickListener((adapter, view, position) -> {
                if (view.getId() == R.id.rl_parent) {
                    CityEntity entity = entities.get(position);
                    CityListBean.ChannelBean channelBean = entity.getChannelBean();
                    Intent intent = new Intent();
                    intent.putExtra("city", channelBean);
                    setResult(RESULT_CODE, intent);
                    finish();
                }
            });
            mRvCityList.setAdapter(mCityListAdapter);

            mTvChooseCity.setText(getString(R.string.tv_current_city, currentCity));

            mIvback.setOnClickListener(v -> finish());

        } catch (Exception e) {
            Log.e(TAG, "initData: " + e.getMessage());
        }
    }

    private void addChannels(List<CityListBean.ChannelBean> channelList, List<CityEntity> entities) {
        String currentIndex = null;
        for (CityListBean.ChannelBean channel : channelList) {
            String temp = channel.getNameSpell().substring(0, 1);
            if (!temp.equals(currentIndex)) {
                CityEntity entity = new CityEntity();
                entity.setItemType(1);
                entity.setIndex(temp);
                entities.add(entity);
                currentIndex = temp;
            }
            CityEntity cityEntity = new CityEntity();
            cityEntity.setItemType(0);
            cityEntity.setChannelBean(channel);
            entities.add(cityEntity);
        }
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
        entities.add(cityEntity);
    }
}
