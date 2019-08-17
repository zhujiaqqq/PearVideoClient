package com.example.pearvideoclient.channel;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.ImageView;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.entity.CategoryBean;

import java.util.ArrayList;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-07-19 09:57
 * @ClassName: ReSortCategoryActivity
 */
public class ReSortCategoryActivity extends AppCompatActivity {

    private ImageView mIvClose;
    private RecyclerView mRvCategoryList;

    private CategoryListAdapter mCategoryListAdapter;
    private ItemTouchHelper mItemTouchHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resort_category);
        initView();
        initData();
    }

    private void initView() {

        mIvClose = findViewById(R.id.iv_close);
        mRvCategoryList = findViewById(R.id.rv_category_list);
    }

    private void initData() {
        ArrayList<CategoryBean.CategoryListBean> categoryList = getIntent().getParcelableArrayListExtra("categoryList");

        mRvCategoryList.setLayoutManager(new GridLayoutManager(ReSortCategoryActivity.this, 5));
        mCategoryListAdapter = new CategoryListAdapter(ReSortCategoryActivity.this, categoryList);
        mRvCategoryList.setAdapter(mCategoryListAdapter);

        mItemTouchHelper = new ItemTouchHelper(new IHCallback(mCategoryListAdapter));
        mItemTouchHelper.attachToRecyclerView(mRvCategoryList);
        mIvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putParcelableArrayListExtra("categoryList", categoryList);
                setResult(0, intent);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
