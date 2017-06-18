package com.etologic.fintonictestchallenge.app.splash;

import android.support.annotation.UiThread;

@UiThread
interface ISplashView {

    void goToMain();

    void showErrorSnackbar();

    void showProgressBar();
    void hideProgressBar();
}