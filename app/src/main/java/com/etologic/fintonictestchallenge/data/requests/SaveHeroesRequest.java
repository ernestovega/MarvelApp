package com.etologic.fintonictestchallenge.data.requests;

import com.etologic.fintonictestchallenge.domain.model.Hero;

import java.util.List;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class SaveHeroesRequest {

    private List<Hero> heroes = null;

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heros) {
        this.heroes = heros;
    }

    public SaveHeroesRequest(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
