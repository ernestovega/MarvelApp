package com.etologic.fintonictestchallenge.data.responses;

import com.etologic.fintonictestchallenge.domain.model.Hero;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class GetHeroResponse {

    private Hero hero;

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public GetHeroResponse(Hero hero) {
        this.hero = hero;
    }
}
