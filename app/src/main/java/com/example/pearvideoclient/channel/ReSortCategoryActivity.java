package com.example.pearvideoclient.channel;

import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.ItemTouchHelper;
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
        CategoryListAdapter categoryListAdapter = new CategoryListAdapter(ReSortCategoryActivity.this, categoryList);
        mRvCategoryList.setAdapter(categoryListAdapter);

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(new IHCallback(categoryListAdapter));
        itemTouchHelper.attachToRecyclerView(mRvCategoryList);
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
}
