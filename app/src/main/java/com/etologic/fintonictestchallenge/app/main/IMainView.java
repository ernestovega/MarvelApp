package com.etologic.fintonictestchallenge.app.main;

import android.support.annotation.UiThread;

import com.etologic.fintonictestchallenge.domain.model.Hero;

import java.util.List;

@UiThread
interface IMainView {

    void setRecyclerViewAdapter(List<Hero> heroes);

    void goToHeroDetail(String heroeName);

    void showErrorSnackbar();

    void showProgressBar();
    void hideProgressBar();

    void showBusyHeroesLayout();
    void hideBusyHeroesLayout();

}