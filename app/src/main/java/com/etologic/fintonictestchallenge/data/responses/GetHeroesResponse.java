package com.etologic.fintonictestchallenge.data.responses;

import com.etologic.fintonictestchallenge.domain.model.Hero;

import java.util.List;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class GetHeroesResponse {

    private List<Hero> heroes = null;

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heros) {
        this.heroes = heros;
    }

    public GetHeroesResponse(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
