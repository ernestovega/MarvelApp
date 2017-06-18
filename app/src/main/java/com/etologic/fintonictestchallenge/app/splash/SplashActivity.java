package com.etologic.fintonictestchallenge.app.splash;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.etologic.fintonictestchallenge.R;

public final class SplashActivity extends AppCompatActivity implements ISplashView {

    @BindView (R.id.tvSplashActivityHello)
    TextView tvSplashActivityHello;

    private ISplashPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter = new SplashPresenter(this);

        tvSplashActivityHello.setText("Hello SplashActivity!");
    }
}
