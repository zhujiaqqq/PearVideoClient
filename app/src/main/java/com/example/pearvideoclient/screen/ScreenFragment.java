package com.example.pearvideoclient.screen;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.pearvideoclient.R;

/**
 * @author zhujiaqqq
 * @date 2019-07-11
 */
public class ScreenFragment extends Fragment {

    public static ScreenFragment newInstance() {
        Bundle bundle = new Bundle();
        ScreenFragment fragment = new ScreenFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_screen, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //
    }
}
