package com.etologic.fintonictestchallenge.data;

import com.etologic.fintonictestchallenge.data.requests.GetHeroRequest;
import com.etologic.fintonictestchallenge.data.requests.SaveHeroesRequest;
import com.etologic.fintonictestchallenge.data.responses.DownloadHeroesResponse;
import com.etologic.fintonictestchallenge.data.responses.GetHeroResponse;
import com.etologic.fintonictestchallenge.data.responses.GetHeroesResponse;

/**
 * Created by ernesto.vega on 16/06/2017.
 */

public interface DataSource {

    //region DownloadHeroes

    interface DownloadHeroesCallback {
        void onDownloadHeroesSuccess(DownloadHeroesResponse downloadHeroesResponse);
        void onDownloadHeroesFailure(ErrorBase errorBase);
    }
    void downloadHeroes(DownloadHeroesCallback downloadHeroesCallback);

    //endregion

    //region SaveHeroes

    interface SaveHeroesCallback {
        void onSaveHeroesSuccess(boolean isSuccess);
        void onSaveHeroesFailure(ErrorBase errorBase);
    }
    void saveHeroes(SaveHeroesRequest saveHeroesRequest, SaveHeroesCallback saveHeroesCallback);

    //endregion

    //region GetHeroes

    interface GetHeroesCallback {
        void onGetHeroesSuccess(GetHeroesResponse getHeroesResponse);
        void onGetHeroesFailure(ErrorBase errorBase);
    }
    void getHeroes(GetHeroesCallback getHeroesCallback);

    //endregion

    //region GetHeroe

    interface GetHeroCallback {
        void onGetHeroSuccess(GetHeroResponse getHeroResponse);
        void onGetHeroFailure(ErrorBase errorBase);
    }
    void getHero(GetHeroRequest getHeroRequest, GetHeroCallback getHeroCallback);

    //endregion
}
