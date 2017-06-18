package com.etologic.fintonictestchallenge.app.hero_detail;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.etologic.fintonictestchallenge.R;
import com.etologic.fintonictestchallenge.app.base.Injection;
import com.etologic.fintonictestchallenge.app.main.MainActivity;
import com.etologic.fintonictestchallenge.app.utils.PicassoCache;
import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.squareup.picasso.Picasso;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class HeroDetailActivity extends AppCompatActivity implements IHeroDetailView {

    //region Fields

    @BindView (R.id.collapsingToolbarHeroDetail)
    CollapsingToolbarLayout collapsingToolbar;
    @BindView (R.id.ivToolbarHeroDetail)
    ImageView ivToolbar;
    @BindView (R.id.toolbarHeroDetail)
    Toolbar toolbar;
    @BindView(R.id.tvHeroDetailName)
    TextView tvName;
    @BindView(R.id.tvHeroDetailRealName)
    TextView tvRealName;
    @BindView(R.id.tvHeroDetailHeight)
    TextView tvHeight;
    @BindView(R.id.tvHeroDetailPowers)
    TextView tvPowers;
    @BindView(R.id.tvHeroDetailAbilities)
    TextView tvAbilities;
    @BindView(R.id.tvHeroDetailGroups)
    TextView tvGroups;

    private IHeroDetailPresenter mPresenter;
    private String heroName = "";

    //endregion

    //region Lifecycle

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        ButterKnife.bind(this);
        mPresenter = new HeroDetailPresenter(this,
                Injection.provideUseCaseHandler(),
                Injection.provideGetHeroUseCase(this));
        Intent intent = getIntent();
        heroName = intent.getStringExtra(MainActivity.HERO_NAME_KEY);
        if(heroName == null) {
            heroName = "";
        }
        setSupportActionBar(toolbar);
        collapsingToolbar.setTitle(heroName);
        mPresenter.loadHero(heroName);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        ActivityCompat.finishAfterTransition(this);
    }

    //endregion

    //region IHeroDetailView implementation

    @Override
    public void fillHeroDetails(Hero hero) {
        loadHeroPhoto(hero.getPhoto());
        tvName.setText(hero.getName());
        tvRealName.setText(hero.getRealName());
        tvHeight.setText(hero.getHeight());
        tvPowers.setText(hero.getPower());
        tvAbilities.setText(hero.getAbilities());
        tvGroups.setText(hero.getGroups());
    }

    @Override
    public void showErrorSnackbar() {
        String message;
        if(heroName.isEmpty()) {
            message = getString(R.string.busy_hero);
        } else {
            message = String.format("%s %s %s", R.string.busy_hero1, heroName, R.string.busy_hero2);
        }
        Snackbar.make(toolbar, message, Snackbar.LENGTH_INDEFINITE)
                .setAction(R.string.return_, new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                    Intent intent = new Intent(HeroDetailActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                    }
                })
                .setActionTextColor(ContextCompat.getColor(this, R.color.colorPrimary))
                .show();
    }

    @Override
    public void showBusyHeroLayout() {

    }

    @Override
    public void hideBusyHeroLayout() {

    }

    @Override
    public void showProgressBar() {

    }

    @Override
    public void hideProgressBar() {

    }

    //endregion

    //region Private

    public void loadHeroPhoto(String urlHeroPhoto) {
        Picasso piccaso = PicassoCache.getPicassoInstance(this);
        piccaso.load(urlHeroPhoto)
                .error(R.drawable.busy_heroes)
                .noPlaceholder()
                .into(ivToolbar);
    }

    //endregion
}
