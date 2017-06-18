package com.etologic.fintonictestchallenge.app.main;

import android.support.annotation.StringRes;
import android.support.annotation.UiThread;

import com.etologic.fintonictestchallenge.domain.model.Hero;

import java.util.List;

@UiThread
interface IMainView {

    void setRecyclerViewAdapter(List<Hero> heroes);
    void goToHeroDetail(String heroeName);
    void showSnackbar(@StringRes int load_heroes_error);
    void showBusyHeroes();
    void hideBusyHeroes();
    void showProgressBar();
    void hideProgressBar();
}