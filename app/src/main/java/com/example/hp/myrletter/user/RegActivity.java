package com.example.hp.myrletter.user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.hp.myrletter.R;
import com.example.hp.myrletter.base.BaseActivity;
import com.hyphenate.chat.EMClient;
import com.hyphenate.exceptions.HyphenateException;

import butterknife.BindView;
import butterknife.OnClick;


public class RegActivity extends BaseActivity {
    @BindView(R.id.reg_mobile)
    EditText reg_mobile;
    @BindView(R.id.reg_password)
    EditText reg_password;
    @BindView(R.id.reg_btn)
    Button btn;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_reg;
    }

    @Override
    protected void initView(Bundle savedInstanceState) {
        setShowActionBar(false);
    }

    @Override
    protected void initData() {
        super.initData();

    }
@OnClick(R.id.reg_btn)
    public void click(View v){
        switch (v.getId()){
            case R.id.reg_btn:
                if (isFastClick()) {
                    try {
                        String mobile = reg_mobile.getText().toString().trim();
                        String password = reg_password.getText().toString().trim();
                        if (TextUtils.isEmpty(mobile)) {
                            showToast("请输入您的电话号码");
                            return;
                        }
                        if (TextUtils.isEmpty(password)) {
                            showToast("请输入您的密码");
                            return;
                        }
                        if (password.length() != 6) {
                            showToast("您的密码位数不正确");
                            return;
                        }
                        EMClient.getInstance().createAccount(mobile, password);
                    } catch (HyphenateException e) {
                        e.printStackTrace();
                    }finally {
                        Intent intent = getIntent();
                        setResult(222, intent);
                        finish();
                    }
                }
                break;
        }
}
}
