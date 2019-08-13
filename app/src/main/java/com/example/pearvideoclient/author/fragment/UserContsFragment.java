package com.example.pearvideoclient.author.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:45
 * @ClassName: UserContsFragment
 */
public class UserContsFragment extends Fragment {
    public static UserContsFragment newInstance() {
        Bundle bundle = new Bundle();
        UserContsFragment fragment = new UserContsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
