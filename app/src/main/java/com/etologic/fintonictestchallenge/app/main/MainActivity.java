package com.etologic.fintonictestchallenge.app.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.etologic.fintonictestchallenge.R;
import com.etologic.fintonictestchallenge.app.base.Injection;
import com.etologic.fintonictestchallenge.app.hero_detail.HeroDetailActivity;
import com.etologic.fintonictestchallenge.app.splash.SplashActivity;
import com.etologic.fintonictestchallenge.domain.model.Hero;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class MainActivity extends AppCompatActivity implements IMainView {

    public static final String HERO_NAME_KEY = "hero_name";

    //region Fields

    @BindView (R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView (R.id.llBusyHeroes)
    LinearLayout llBusyHeroes;
    @BindView (R.id.pbMain)
    ProgressBar pbMain;

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
                Injection.provideGetHeroesUseCase(this));
        prepareRecyclerView();
        mPresenter.loadHeroes();
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
    public void showErrorSnackbar() {
        Snackbar.make(pbMain, R.string.busy_heroes, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.retry, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        startActivity(new Intent(MainActivity.this, SplashActivity.class));
                        MainActivity.this.finish();
                    }
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .show();
    }

    @Override
    public void showProgressBar() {
        pbMain.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        pbMain.setVisibility(View.GONE);
    }

    @Override
    public void showBusyHeroesLayout() {
        llBusyHeroes.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideBusyHeroesLayout() {
        llBusyHeroes.setVisibility(View.GONE);
    }

    //endregion
}
