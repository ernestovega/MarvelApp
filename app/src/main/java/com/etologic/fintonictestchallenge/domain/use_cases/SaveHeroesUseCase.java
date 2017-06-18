package com.etologic.fintonictestchallenge.domain.use_cases;

import com.etologic.fintonictestchallenge.data.DataSource;
import com.etologic.fintonictestchallenge.data.requests.SaveHeroesRequest;
import com.etologic.fintonictestchallenge.data.ErrorBase;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class SaveHeroesUseCase
        extends UseCase<SaveHeroesUseCase.RequestValues, SaveHeroesUseCase.ResponseValue> {

    //region Field

    private final DataSource dataSource;

    //endregion

    //region Contructor

    public SaveHeroesUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //endregion

    //region ExecuteUseCase

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.saveHeroes(requestValues.getSaveHeroesRequest(),
                new DataSource.SaveHeroesCallback() {
                    @Override
                    public void onSaveHeroesSuccess(boolean isSuccess) {
                        getUseCaseCallback().onSuccess(new ResponseValue(isSuccess));
                    }

                    @Override
                    public void onSaveHeroesFailure(ErrorBase errorBase) {
                        getUseCaseCallback().onError(errorBase.ErrorDescription());
                    }
                });
    }

    //endregion

    //region RequestValues

    public static final class RequestValues implements UseCase.RequestValues {

        private final SaveHeroesRequest saveHeroesRequest;

        public RequestValues(SaveHeroesRequest saveHeroesRequest) {
            this.saveHeroesRequest = saveHeroesRequest;
        }

        public SaveHeroesRequest getSaveHeroesRequest() {
            return this.saveHeroesRequest;
        }
    }

    //endregion

    //region ResponseValue

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final boolean success;

        public ResponseValue(boolean success) {
            this.success = success;
        }

        public boolean isSuccess() {
            return this.success;
        }
    }

    //endregion
}
