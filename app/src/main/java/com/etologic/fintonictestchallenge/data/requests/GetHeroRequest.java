package com.etologic.fintonictestchallenge.data.requests;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class GetHeroRequest {

    private String heroName;

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    public GetHeroRequest(String heroName) {
        this.heroName = heroName;
    }
}
