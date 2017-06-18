package com.etologic.fintonictestchallenge.data.repositories.remote;

import com.etologic.fintonictestchallenge.data.responses.DownloadHeroesResponse;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by ernesto.vega on 16/06/2017.
 */

public interface APIServices {

    @GET ("/bins/bvyob")
    Call<DownloadHeroesResponse> getHeroes();
}
