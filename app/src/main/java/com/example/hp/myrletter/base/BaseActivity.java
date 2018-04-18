package com.example.hp.myrletter.base;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import android.widget.Toast;

import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

public abstract class BaseActivity extends AutoLayoutActivity {

    private boolean isStatus = false;//沉浸式透明状态栏标示
    private boolean isShowActionBar = true;//是否隐藏actionbar
    private boolean isFullScreen = false;
    private static final int MIN_CLICK_DELAY_TIME = 2000;
    private static long lastClickTime;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        initView(savedInstanceState);
        initData();
    }

    /**
     * 初始化数据
     */
    protected void initData(){

    };

    /**
     * 初始化view控件
     */
    protected abstract void initView(Bundle savedInstanceState);

    /**
     * 绑定布局的方法
     * @return
     */
    protected abstract int getLayoutId();

    /**
     * 无参跳转
     * @param clz
     */
    public void startActivity(Class<?> clz){
        startActivity(new Intent(this,clz));
    }

    /**
     * 有值跳转
     * @param clz
     * @param bundle
     */
    public void startActivity(Class<?> clz,Bundle bundle){
        Intent intent = new Intent(this, clz);
        intent.putExtras(bundle);
        startActivity(intent);
    }


    /**
     * 两次点击按钮之间的点击间隔不能少于2000毫秒
     * @return
     */
    public static boolean isFastClick() {
        boolean flag = false;
        long curClickTime = System.currentTimeMillis();
        if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
            flag = true;
        }
        lastClickTime = curClickTime;
        return flag;
    }

    /**
     * 沉浸式透明状态栏标示
     * @param status
     */
    public void setStatus(boolean status) {
        isStatus = status;
        if (isStatus){
            //判断当前设备的版本号》=19的时候，走这个代码块，这个用于版本适配
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.KITKAT){

                getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            }
        }
    }

    /**
     * 是否隐藏actionbar
     * @param showActionBar
     */
    public void setShowActionBar(boolean showActionBar) {
        isShowActionBar = showActionBar;

        if (isShowActionBar){
            getSupportActionBar().show();
        }else{
            getSupportActionBar().hide();
        }
    }

    /**
     * 是全屏的时候
     * @param fullScreen
     */
    public void setFullScreen(boolean fullScreen) {
        isFullScreen = fullScreen;
        if (isFullScreen){//是全屏的时候
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        }
    }

    public void showToast(String msg){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}

