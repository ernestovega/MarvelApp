package com.etologic.fintonictestchallenge.domain.use_cases;

import com.etologic.fintonictestchallenge.data.DataSource;
import com.etologic.fintonictestchallenge.data.ErrorBase;
import com.etologic.fintonictestchallenge.data.requests.GetHeroRequest;
import com.etologic.fintonictestchallenge.data.responses.GetHeroResponse;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class GetHeroUseCase
        extends UseCase<GetHeroUseCase.RequestValues, GetHeroUseCase.ResponseValue> {

    //region Field

    private final DataSource dataSource;

    //endregion

    //region Contructor

    public GetHeroUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //endregion

    //region ExecuteUseCase

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.getHero(requestValues.getGetHeroRequest(), new DataSource.GetHeroCallback() {
            @Override
            public void onGetHeroSuccess(GetHeroResponse getHeroResponse) {
                getUseCaseCallback().onSuccess(new ResponseValue(getHeroResponse));
            }

            @Override
            public void onGetHeroFailure(ErrorBase errorBase) {
                getUseCaseCallback().onError(errorBase.ErrorDescription());
            }
        });
    }

    //endregion

    //region RequestValues

    public static final class RequestValues implements UseCase.RequestValues {

        private final GetHeroRequest getHeroRequest;

        public RequestValues(GetHeroRequest getHeroRequest) {
            this.getHeroRequest = getHeroRequest;
        }

        public GetHeroRequest getGetHeroRequest() {
            return this.getHeroRequest;
        }
    }

    //endregion

    //region ResponseValue

    public static final class ResponseValue implements UseCase.ResponseValue {

        private final GetHeroResponse getHeroResponse;

        public ResponseValue(GetHeroResponse getHeroResponse) {
            this.getHeroResponse = getHeroResponse;
        }

        public GetHeroResponse getGetHeroResponse() {
            return getHeroResponse;
        }
    }

    //endregion
}
