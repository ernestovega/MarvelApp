package com.etologic.fintonictestchallenge.data;

import com.etologic.fintonictestchallenge.data.requests.GetHeroRequest;
import com.etologic.fintonictestchallenge.data.requests.SaveHeroesRequest;

/**
 * Created by ernesto.vega on 16/06/2017.
 */

public class DataProvider implements DataSource {

    //region Fields

    private static DataProvider instance = null;
    private final DataSource remote;
    private final DataSource local;

    //endregion

    //region Constructors

    private DataProvider(DataSource remote, DataSource local) {
        this.remote = remote;
        this.local = local;
    }

    public static synchronized DataProvider getInstance(DataSource remoteDataSource,
                                                        DataSource localDataSource) {
        if (instance == null) {
            instance = new DataProvider(remoteDataSource, localDataSource);
        }
        return instance;
    }

    //endregion

    @Override
    public void downloadHeroes(final DownloadHeroesCallback downloadHeroesCallback) {
        remote.downloadHeroes(downloadHeroesCallback);
    }

    @Override
    public void saveHeroes(final SaveHeroesRequest saveHeroesRequest,
                           final SaveHeroesCallback saveHeroesCallback) {
        local.saveHeroes(saveHeroesRequest, saveHeroesCallback);
    }

    @Override
    public void getHeroes(final GetHeroesCallback getHeroesCallback) {
        local.getHeroes(getHeroesCallback);
    }

    @Override
    public void getHero(final GetHeroRequest getHeroRequest,
                        final GetHeroCallback getHeroeCallback) {
        local.getHero(getHeroRequest, getHeroeCallback);
    }
}
