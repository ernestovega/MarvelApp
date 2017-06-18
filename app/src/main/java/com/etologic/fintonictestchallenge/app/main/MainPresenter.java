package com.etologic.fintonictestchallenge.app.main;

import com.etologic.fintonictestchallenge.R;
import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.etologic.fintonictestchallenge.data.requests.SaveHeroesRequest;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;
import com.etologic.fintonictestchallenge.domain.threading.UseCaseHandler;
import com.etologic.fintonictestchallenge.domain.use_cases.DownloadHeroesUseCase;
import com.etologic.fintonictestchallenge.domain.use_cases.GetHeroesUseCase;
import com.etologic.fintonictestchallenge.domain.use_cases.SaveHeroesUseCase;

import java.util.ArrayList;
import java.util.List;

final class MainPresenter implements IMainPresenter {

    //region Fields

    private IMainView mView;
    private UseCaseHandler useCaseHandler;
    private DownloadHeroesUseCase downloadHeroesUseCase;
    private SaveHeroesUseCase saveHeroesUseCase;
    private GetHeroesUseCase getHeroesUseCase;
    private List<Hero> heroes;

    //endregion

    //region Constructor

    MainPresenter(IMainView mView,
                  UseCaseHandler useCaseHandler,
                  DownloadHeroesUseCase downloadHeroesUseCase,
                  SaveHeroesUseCase saveHeroesUseCase,
                  GetHeroesUseCase getHeroesUseCase) {
        this.mView = mView;
        this.useCaseHandler = useCaseHandler;
        this.downloadHeroesUseCase = downloadHeroesUseCase;
        this.saveHeroesUseCase = saveHeroesUseCase;
        this.getHeroesUseCase = getHeroesUseCase;
        heroes = new ArrayList<>();
    }

    //endregion

    //region IMainPresenter implementation

    @Override
    public void initData() {
        useCaseHandler.execute(downloadHeroesUseCase, null,
                new UseCase.UseCaseCallback<DownloadHeroesUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(DownloadHeroesUseCase.ResponseValue response) {
                        if(response != null && response.getDownloadHeroesResponse() != null) {
                            saveHeroes(response.getDownloadHeroesResponse().getHeroes());
                        } else {
                            errorDownloadingHeroes();
                        }
                    }

                    @Override
            public void onError(String error) {
                errorDownloadingHeroes();
            }
        });
    }

    //endregion

    //region Private

    private void saveHeroes(List<Hero> heroes) {
        SaveHeroesRequest saveHeroesRequest = new SaveHeroesRequest(heroes);
        SaveHeroesUseCase.RequestValues requestValues =
                new SaveHeroesUseCase.RequestValues(saveHeroesRequest);
        useCaseHandler.execute(saveHeroesUseCase, requestValues,
                new UseCase.UseCaseCallback<SaveHeroesUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(SaveHeroesUseCase.ResponseValue response) {
                        if(response != null && response.isSuccess()) {
                            loadHeroes();
                        } else {
                            errorSavingHeroes();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        errorSavingHeroes();
                    }
        });
    }

    private void loadHeroes() {
        mView.hideBusyHeroes();
        mView.showProgressBar();
        useCaseHandler.execute(getHeroesUseCase, null,
                new UseCase.UseCaseCallback<GetHeroesUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetHeroesUseCase.ResponseValue response) {
                        if(response != null && response.getGetHeroesResponse() != null) {
                            mView.hideProgressBar();
                            mView.setRecyclerViewAdapter(
                                    response.getGetHeroesResponse().getHeroes());
                        } else {
                            errorGettingHeroes();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        errorGettingHeroes();
                    }
                });
    }

    private void errorDownloadingHeroes() {
        mView.hideProgressBar();
        mView.showBusyHeroes();
        mView.showSnackbar(R.string.download_heroes_error);
    }

    private void errorSavingHeroes() {
        mView.hideProgressBar();
        mView.showBusyHeroes();
        mView.showSnackbar(R.string.load_heroes_error);
    }

    private void errorGettingHeroes() {
        mView.hideProgressBar();
        mView.showBusyHeroes();
        mView.showSnackbar(R.string.get_heroes_error);
    }

    //endregion
}