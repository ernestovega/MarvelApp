package com.etologic.fintonictestchallenge.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.etologic.fintonictestchallenge.R;
import com.etologic.fintonictestchallenge.app.base.Injection;
import com.etologic.fintonictestchallenge.app.hero_detail.HeroDetailActivity;
import com.etologic.fintonictestchallenge.domain.model.Hero;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity implements IMainView {

    private static final String HERO_NAME_KEY = "hero_name";

    //region Fields

    @BindView (R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView (R.id.tvBusyHeroes)
    TextView tvBusyHeroes;
    @BindView (R.id.progressBar)
    ProgressBar progressBar;

    private IMainPresenter mPresenter;

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this,
                Injection.provideUseCaseHandler(),
                Injection.provideDownloadHeroesUseCase(this),
                Injection.provideSaveHeroesUseCase(this),
                Injection.provideGetHeroesUseCase(this));
        prepareRecyclerView();
        mPresenter.initData();
    }

    //endregion

    //region Private

    private void prepareRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
    }

    //endregion

    //region IMainView implementation

    @Override
    public void setRecyclerViewAdapter(List<Hero> heroes) {
        HeroesAdapter heroesAdapter = new HeroesAdapter(heroes, new HeroesAdapter.ItemHeroeListener() {
            @Override
            public void onItemHeroClick(String heroName) {
                goToHeroDetail(heroName);
            }
        });
        recyclerView.setAdapter(heroesAdapter);
    }

    @Override
    public void goToHeroDetail(String heroName) {
        Intent intent = new Intent(this, HeroDetailActivity.class);
        Bundle bundle = new Bundle();
        bundle.putString(HERO_NAME_KEY, heroName);
        intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public void showSnackbar(@StringRes int stringRes) {
        Snackbar.make(recyclerView, stringRes, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showBusyHeroes() {
        tvBusyHeroes.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBusyHeroes() {
        tvBusyHeroes.setVisibility(View.GONE);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    //endregion
}
