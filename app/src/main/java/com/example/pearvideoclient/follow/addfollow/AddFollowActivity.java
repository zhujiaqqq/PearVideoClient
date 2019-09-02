package com.example.pearvideoclient.follow.addfollow;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.example.pearvideoclient.R;
import com.example.pearvideoclient.databinding.ActivityAddFollowBinding;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-09-02 16:34
 * @ClassName: AddFollowActivity
 */
public class AddFollowActivity extends AppCompatActivity {

    private ActivityAddFollowBinding binding;
    private AddFollowCategoryAdapter mCategoryAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        binding = DataBindingUtil.setContentView(this, R.layout.activity_add_follow);
//        binding.rvCategoryList.setAdapter();
//        binding.setIsLoading(true);
    }
}
