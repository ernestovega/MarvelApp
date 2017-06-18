package com.etologic.fintonictestchallenge.app.base;

import android.content.Context;
import android.support.annotation.NonNull;

import com.etologic.fintonictestchallenge.data.DataProvider;
import com.etologic.fintonictestchallenge.data.repositories.local.LocalDataSource;
import com.etologic.fintonictestchallenge.data.repositories.remote.RemoteDataSource;
import com.etologic.fintonictestchallenge.domain.threading.UseCaseHandler;
import com.etologic.fintonictestchallenge.domain.use_cases.DownloadHeroesUseCase;
import com.etologic.fintonictestchallenge.domain.use_cases.GetHeroUseCase;
import com.etologic.fintonictestchallenge.domain.use_cases.GetHeroesUseCase;
import com.etologic.fintonictestchallenge.domain.use_cases.SaveHeroesUseCase;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class Injection {

    public static UseCaseHandler provideUseCaseHandler() {
        return UseCaseHandler.getInstance();
    }

    private static DataProvider provideDataSource(@NonNull Context context) {
        return DataProvider.getInstance(RemoteDataSource.getInstance(),
                LocalDataSource.getInstance(context));
    }

    public static DownloadHeroesUseCase provideDownloadHeroesUseCase(Context context) {
        return new DownloadHeroesUseCase(Injection.provideDataSource(context));
    }

    public static SaveHeroesUseCase provideSaveHeroesUseCase(Context context) {
        return new SaveHeroesUseCase(Injection.provideDataSource(context));
    }

    public static GetHeroesUseCase provideGetHeroesUseCase(Context context) {
        return new GetHeroesUseCase(Injection.provideDataSource(context));
    }

    public static GetHeroUseCase provideGetHeroUseCase(Context context) {
        return new GetHeroUseCase(Injection.provideDataSource(context));
    }
}
