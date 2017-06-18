package com.etologic.fintonictestchallenge.data.responses;

import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DownloadHeroesResponse {

    @SerializedName("superheroes")
    @Expose
    private List<Hero> heroes = null;

    public List<Hero> getHeroes() {
        return heroes;
    }

    public void setHeroes(List<Hero> heroes) {
        this.heroes = heroes;
    }
}
