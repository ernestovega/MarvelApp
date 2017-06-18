package com.etologic.fintonictestchallenge.domain.use_cases;

import com.etologic.fintonictestchallenge.data.DataSource;
import com.etologic.fintonictestchallenge.data.ErrorBase;
import com.etologic.fintonictestchallenge.data.responses.GetHeroesResponse;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class GetHeroesUseCase
        extends UseCase<GetHeroesUseCase.RequestValues, GetHeroesUseCase.ResponseValue> {

    //region Field

    private final DataSource dataSource;

    //endregion

    //region Contructor

    public GetHeroesUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //endregion

    //region ExecuteUseCase

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getHeroes(new DataSource.GetHeroesCallback() {
            @Override
            public void onGetHeroesSuccess(GetHeroesResponse getHeroesResponse) {
                getUseCaseCallback().onSuccess(new ResponseValue(getHeroesResponse));
            }

            @Override
            public void onGetHeroesFailure(ErrorBase errorBase) {
                getUseCaseCallback().onError(errorBase.ErrorDescription());
            }
        });
    }

    //endregion

    //region RequestValues

    public static final class RequestValues implements UseCase.RequestValues {}

    //endregion

    //region ResponseValue

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final GetHeroesResponse getHeroesResponse;

        public ResponseValue(GetHeroesResponse getHeroesResponse) {
            this.getHeroesResponse = getHeroesResponse;
        }

        public GetHeroesResponse getGetHeroesResponse() {
            return this.getHeroesResponse;
        }
    }

    //endregion
}
