package com.example.pearvideoclient.author.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * @Description: java类作用描述
 * @Author: jiazhu
 * @Date: 2019-08-13 15:46
 * @ClassName: UserAlbumsFragment
 */
public class UserAlbumsFragment extends Fragment {

    public static UserAlbumsFragment newInstance() {
        Bundle bundle = new Bundle();
        UserAlbumsFragment fragment = new UserAlbumsFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
