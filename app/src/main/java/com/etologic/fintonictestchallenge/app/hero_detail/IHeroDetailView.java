package com.etologic.fintonictestchallenge.app.hero_detail;

import android.support.annotation.UiThread;

import com.etologic.fintonictestchallenge.domain.model.Hero;

@UiThread
interface IHeroDetailView {

    void fillHeroDetails(Hero hero);

    void showErrorSnackbar();

    void showBusyHeroLayout();
    void hideBusyHeroLayout();

    void showProgressBar();
    void hideProgressBar();
}