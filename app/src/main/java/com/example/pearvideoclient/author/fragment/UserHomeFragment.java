package com.example.pearvideoclient.author.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserHomeFragment
 */
public class UserHomeFragment extends Fragment {
    public static UserHomeFragment newInstance() {
        Bundle bundle = new Bundle();
        UserHomeFragment fragment = new UserHomeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
