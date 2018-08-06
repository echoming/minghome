package com.ming;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ming.fragement.DashFragment;
import com.ming.network.ConstantsImageUrl;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SplashActivity extends AppCompatActivity {

    private ImageView ivad;
    private ImageView ivdefault;
    private TextView textViewJump;

    private MyHandler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ivad=(ImageView)findViewById(R.id.ivad);
        ivdefault=(ImageView)findViewById(R.id.ivdefault);
        textViewJump=(TextView)findViewById(R.id.textViewJump);
        showImage();
    }

    private void showImage(){
        // TODO: 2018/8/4 获取百度链接
        int i=new Random().nextInt(ConstantsImageUrl.TRANSITION_URLS.length);
        Glide.with(this)
                .load(ConstantsImageUrl.TRANSITION_URLS[i])
                .placeholder(R.drawable.splash_default)
                .error(R.drawable.splash_default)
                .into(ivad);
        textViewJump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toMainActivity();
            }
        });

        handler = new MyHandler(this);
        handler.sendEmptyMessageDelayed(0, 1500);
        handler.sendEmptyMessageDelayed(1, 3500);

    }

    static class MyHandler extends Handler {
        private WeakReference<SplashActivity> mOuter;

        MyHandler(SplashActivity activity) {
            mOuter = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            SplashActivity outer = mOuter.get();
            if (outer != null) {
                something(outer, msg);
            }
        }

        private void something(SplashActivity outer, Message msg) {
            switch (msg.what) {
                case 0:
                    outer.ivdefault.setVisibility(View.GONE);
                    break;
                case 1:
                    outer.toMainActivity();
                    break;
                default:
                    break;
            }
        }
    }
    private void toMainActivity(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.screen_zoom_in, R.anim.screen_zoom_out);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
            handler = null;
        }
    }
}

