package com.example.hp.myrletter;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.myrletter.base.BaseActivity;
import com.example.hp.myrletter.user.LoginActivity;

public class MainActivity extends BaseActivity {
    private SharedPreferences preferences;
    @Override
    protected void initView(Bundle savedInstanceState) {
        setShowActionBar(false);
        preferences = getSharedPreferences("userdb", MODE_PRIVATE);
    }

    @Override
    protected void initData() {
        super.initData();
        boolean isChecked = preferences.getBoolean("isChecked", false);
        if (isChecked){
        startActivity(MainToActivity.class);
        }else {
            startActivity(LoginActivity.class);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }
}
