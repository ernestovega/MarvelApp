package com.etologic.fintonictestchallenge.app.splash;

public final class SplashPresenter implements ISplashPresenter {

    private final ISplashInteractor mInteractor;
    private ISplashView mView;

    public SplashPresenter(ISplashView mView) {
        this.mView = mView;
        this.mInteractor = new SplashInteractor();
    }
}