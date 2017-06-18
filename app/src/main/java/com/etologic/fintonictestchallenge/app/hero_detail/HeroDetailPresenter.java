package com.etologic.fintonictestchallenge.app.hero_detail;

final class HeroDetailPresenter implements IHeroDetailPresenter {

    private IHeroDetailView mView;

    HeroDetailPresenter(IHeroDetailView mView) {
        this.mView = mView;
    }
}