package com.etologic.fintonictestchallenge.app.hero_detail;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.etologic.fintonictestchallenge.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public final class HeroDetailActivity extends AppCompatActivity implements IHeroDetailView {

    @BindView (R.id.tvHeroDetailActivityHello)
    TextView tvHeroDetailActivityHello;

    private IHeroDetailPresenter mPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hero_detail);
        ButterKnife.bind(this);
        mPresenter = new HeroDetailPresenter(this);

        tvHeroDetailActivityHello.setText("Hello HeroDetailActivity!");
    }
}
