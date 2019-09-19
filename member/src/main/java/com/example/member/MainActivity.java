package com.example.member;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.annotations.BindPath;
import com.example.route.Route;

/**
 * @author jiazhu
 */
@BindPath(value = "member/main")
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
