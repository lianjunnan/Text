package com.example.hp.myrletter.user;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hp.myrletter.MainToActivity;
import com.example.hp.myrletter.R;
import com.example.hp.myrletter.base.BaseActivity;
import com.hyphenate.EMCallBack;
import com.hyphenate.chat.EMClient;

import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.login_mobile)
    EditText login_mobile;
    @BindView(R.id.login_password)
    EditText login_password;
    @BindView(R.id.login_btn)
    Button btn;
    @BindView(R.id.login_reg)
    TextView reg;
    private SharedPreferences preferences;

    @Override
    protected void initView(Bundle savedInstanceState) {
        setShowActionBar(false);
        preferences = getSharedPreferences("userdb", MODE_PRIVATE);
    }

    @Override
    protected void initData() {
        super.initData();

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_login;
    }

    @OnClick({R.id.login_btn,R.id.login_reg})
    public void click(View v){
        switch (v.getId()){
            case R.id.login_btn:
                if (isFastClick()){
                    String mobile = login_mobile.getText().toString().trim();
                    String password = login_password.getText().toString().trim();
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
                    EMClient.getInstance().login(mobile,password,new EMCallBack() {//回调
                        @Override
                        public void onSuccess() {
                            EMClient.getInstance().groupManager().loadAllGroups();
                            EMClient.getInstance().chatManager().loadAllConversations();
                            preferences.edit().putBoolean("isChecked",true).commit();
                            startActivity(MainToActivity.class);
                            Log.d("main", "登录聊天服务器成功！");
                        }

                        @Override
                        public void onProgress(int progress, String status) {

                        }

                        @Override
                        public void onError(int code, String message) {
                            Log.d("main", "登录聊天服务器失败！");
                        }
                    });
                }
                break;
            case R.id.login_reg:
            startActivityForResult(new Intent(LoginActivity.this,RegActivity.class),111);
                break;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 111 && resultCode == 222) {

        }

    }
}
