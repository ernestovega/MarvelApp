package com.etologic.fintonictestchallenge.domain.use_cases;

import com.etologic.fintonictestchallenge.data.DataSource;
import com.etologic.fintonictestchallenge.data.responses.DownloadHeroesResponse;
import com.etologic.fintonictestchallenge.data.ErrorBase;
import com.etologic.fintonictestchallenge.domain.threading.UseCase;

/**
 * Created by ernesto.vega on 18/06/2017.
 */

public class DownloadHeroesUseCase
        extends UseCase<DownloadHeroesUseCase.RequestValues, DownloadHeroesUseCase.ResponseValue> {

    //region Field

    private final DataSource dataSource;

    //endregion

    //region Contructor

    public DownloadHeroesUseCase(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    //endregion

    //region ExecuteUseCase

    @Override
    protected void executeUseCase(RequestValues requestValues) {
        dataSource.downloadHeroes(new DataSource.DownloadHeroesCallback() {
            @Override
            public void onDownloadHeroesSuccess(DownloadHeroesResponse downloadHeroesResponse) {
                getUseCaseCallback().onSuccess(new ResponseValue(downloadHeroesResponse));
            }

            @Override
            public void onDownloadHeroesFailure(ErrorBase errorBase) {
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

        private final DownloadHeroesResponse downloadHeroesResponse;

        public ResponseValue(DownloadHeroesResponse downloadHeroesResponse) {
            this.downloadHeroesResponse = downloadHeroesResponse;
        }

        public DownloadHeroesResponse getDownloadHeroesResponse() {
            return downloadHeroesResponse;
        }
    }

    //endregion
}
