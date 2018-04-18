package com.example.hp.myrletter;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.hp.myrletter.base.BaseActivity;
import com.example.hp.myrletter.fragment.FragmentArBook;
import com.example.hp.myrletter.fragment.FragmentFind;
import com.example.hp.myrletter.fragment.FragmentHome;
import com.example.hp.myrletter.fragment.FragmentMy;
import com.hjm.bottomtabbar.BottomTabBar;

import butterknife.BindView;

public class MainToActivity extends BaseActivity {
    @BindView(R.id.bottom_tab_bar)
    BottomTabBar bottom_tab_bar;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_to;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setShowActionBar(false);
    }

    @Override
    protected void initData() {
        super.initData();
        bottom_tab_bar.init(getSupportFragmentManager())
                .setImgSize(70, 70)
                .setFontSize(14)
                .setTabPadding(4, 6, 10)
                .setChangeColor(Color.RED, Color.DKGRAY)
                .addTabItem("微信",R.mipmap.weixin_pressed,R.mipmap.weixin_normal, FragmentHome.class)
                .addTabItem("通信录",R.mipmap.contact_list_pressed,R.mipmap.contact_list_normal, FragmentArBook.class)
                .addTabItem("发现",R.mipmap.find_pressed,R.mipmap.find_normal, FragmentFind.class)
                .addTabItem("我的",R.mipmap.profile_pressed,R.mipmap.profile_normal, FragmentMy.class)
                .isShowDivider(false)
                .setOnTabChangeListener(new BottomTabBar.OnTabChangeListener() {
                    @Override
                    public void onTabChange(int position, String name) {
                    }
                });
    }
}
