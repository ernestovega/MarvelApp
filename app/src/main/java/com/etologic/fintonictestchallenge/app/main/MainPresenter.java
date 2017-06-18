package com.etologic.fintonictestchallenge.app.main;

import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;
import com.etologic.fintonictestchallenge.domain.threading.UseCaseHandler;
import com.etologic.fintonictestchallenge.domain.use_cases.GetHeroesUseCase;

import java.util.ArrayList;
import java.util.List;

final class MainPresenter implements IMainPresenter {

    //region Fields

    private IMainView mView;
    private UseCaseHandler useCaseHandler;
    private GetHeroesUseCase getHeroesUseCase;
    private List<Hero> heroes;

    //endregion

    //region Constructor

    MainPresenter(IMainView mView,
                  UseCaseHandler useCaseHandler,
                  GetHeroesUseCase getHeroesUseCase) {
        this.mView = mView;
        this.useCaseHandler = useCaseHandler;
        this.getHeroesUseCase = getHeroesUseCase;
        heroes = new ArrayList<>();
    }

    //endregion

    //region IMainPresenter implementation

    @Override
    public void loadHeroes() {
        mView.showBusyHeroesLayout();
        mView.showProgressBar();
        useCaseHandler.execute(getHeroesUseCase, null,
                new UseCase.UseCaseCallback<GetHeroesUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetHeroesUseCase.ResponseValue response) {
                        if(response != null && response.getGetHeroesResponse() != null) {
                            mView.hideProgressBar();
                            mView.hideBusyHeroesLayout();
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

    //endregion

    //region Private

    private void errorGettingHeroes() {
        mView.hideProgressBar();
        mView.showErrorSnackbar();
    }

    //endregion
}