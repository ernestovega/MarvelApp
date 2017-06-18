package com.etologic.fintonictestchallenge.app.splash;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

import com.etologic.fintonictestchallenge.R;
import com.etologic.fintonictestchallenge.app.base.Injection;
import com.etologic.fintonictestchallenge.app.main.MainActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class SplashActivity extends AppCompatActivity implements ISplashView {

    //region Fields

    @BindView (R.id.pbSplash)
    ProgressBar pbSplash;

    private ISplashPresenter mPresenter;

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        mPresenter = new SplashPresenter(this,
                Injection.provideUseCaseHandler(),
                Injection.provideDownloadHeroesUseCase(this),
                Injection.provideSaveHeroesUseCase(this));
        mPresenter.initData();
    }

    //endregion

    //region ISplashView implementation

    @Override
    public void goToMain() {
        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

    @Override
    public void showErrorSnackbar() {
        Snackbar.make(pbSplash, R.string.busy_heroes, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        mPresenter.initData();
                    }
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .show();
    }

    @Override
    public void showProgressBar() {
        pbSplash.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbSplash.setVisibility(View.GONE);
    }

    //endregion
}
