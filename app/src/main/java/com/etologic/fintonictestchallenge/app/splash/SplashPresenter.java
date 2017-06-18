package com.etologic.fintonictestchallenge.app.splash;

import com.etologic.fintonictestchallenge.data.requests.SaveHeroesRequest;
import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;
import com.etologic.fintonictestchallenge.domain.threading.UseCaseHandler;
import com.etologic.fintonictestchallenge.domain.use_cases.DownloadHeroesUseCase;
import com.etologic.fintonictestchallenge.domain.use_cases.SaveHeroesUseCase;

import java.util.List;

final class SplashPresenter implements ISplashPresenter {

    private ISplashView mView;
    private final UseCaseHandler useCaseHandler;
    private final DownloadHeroesUseCase downloadHeroesUseCase;
    private final SaveHeroesUseCase saveHeroesUseCase;

    SplashPresenter(ISplashView mView,
                    UseCaseHandler useCaseHandler,
                    DownloadHeroesUseCase downloadHeroesUseCase,
                    SaveHeroesUseCase saveHeroesUseCase) {
        this.mView = mView;
        this.useCaseHandler = useCaseHandler;
        this.downloadHeroesUseCase = downloadHeroesUseCase;
        this.saveHeroesUseCase = saveHeroesUseCase;
    }

    @Override
    public void initData() {
        useCaseHandler.execute(downloadHeroesUseCase, null,
                new UseCase.UseCaseCallback<DownloadHeroesUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(DownloadHeroesUseCase.ResponseValue response) {
                        if(response != null && response.getDownloadHeroesResponse() != null) {
                            saveHeroes(response.getDownloadHeroesResponse().getHeroes());
                        } else {
                            mView.showErrorSnackbar();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mView.showErrorSnackbar();
                    }
                });
    }

    private void saveHeroes(List<Hero> heroes) {
        mView.showProgressBar();
        SaveHeroesRequest saveHeroesRequest = new SaveHeroesRequest(heroes);
        SaveHeroesUseCase.RequestValues requestValues =
                new SaveHeroesUseCase.RequestValues(saveHeroesRequest);
        useCaseHandler.execute(saveHeroesUseCase, requestValues,
                new UseCase.UseCaseCallback<SaveHeroesUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveHeroesUseCase.ResponseValue response) {
                        if(response != null && response.isSuccess()) {
                            mView.hideProgressBar();
                            mView.goToMain();
                        } else {
                            mView.hideProgressBar();
                            mView.showErrorSnackbar();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        mView.showErrorSnackbar();
                    }
                });
    }
}