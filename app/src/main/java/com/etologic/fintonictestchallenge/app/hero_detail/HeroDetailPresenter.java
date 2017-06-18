package com.etologic.fintonictestchallenge.app.hero_detail;

import com.etologic.fintonictestchallenge.data.requests.GetHeroRequest;
import com.etologic.fintonictestchallenge.domain.model.Hero;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;
import com.etologic.fintonictestchallenge.domain.threading.UseCaseHandler;
import com.etologic.fintonictestchallenge.domain.use_cases.GetHeroUseCase;

final class HeroDetailPresenter implements IHeroDetailPresenter {

    private IHeroDetailView mView;
    private final UseCaseHandler useCaseHandler;
    private final GetHeroUseCase getHeroUseCase;
    private Hero hero;

    HeroDetailPresenter(IHeroDetailView mView,
                        UseCaseHandler useCaseHandler,
                        GetHeroUseCase getHeroUseCase) {
        this.mView = mView;
        this.useCaseHandler = useCaseHandler;
        this.getHeroUseCase = getHeroUseCase;
        hero = new Hero();
    }

    @Override
    public void loadHero(String heroName) {
        mView.showBusyHeroLayout();
        mView.showProgressBar();
        GetHeroRequest getHeroRequest = new GetHeroRequest(heroName);
        GetHeroUseCase.RequestValues requestValues =
                new GetHeroUseCase.RequestValues(getHeroRequest);
        useCaseHandler.execute(getHeroUseCase, requestValues,
                new UseCase.UseCaseCallback<GetHeroUseCase.ResponseValue>() {
                    @Override
                    public void onSuccess(GetHeroUseCase.ResponseValue response) {
                        if(response != null && response.getGetHeroResponse() != null) {
                            hero = response.getGetHeroResponse().getHero();
                            mView.hideProgressBar();
                            mView.hideBusyHeroLayout();
                            mView.fillHeroDetails(hero);
                        } else {
                            errorGettingHero();
                        }
                    }

                    @Override
                    public void onError(String error) {
                        errorGettingHero();
                    }
                });
    }

    private void errorGettingHero() {
        mView.hideProgressBar();
        mView.showErrorSnackbar();
    }
}