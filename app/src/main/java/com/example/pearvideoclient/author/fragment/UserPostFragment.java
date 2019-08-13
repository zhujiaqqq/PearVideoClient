package com.example.pearvideoclient.author.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserPostFragment
 */
public class UserPostFragment extends Fragment {

    public static UserPostFragment newInstance() {
        Bundle bundle = new Bundle();
        UserPostFragment fragment = new UserPostFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
