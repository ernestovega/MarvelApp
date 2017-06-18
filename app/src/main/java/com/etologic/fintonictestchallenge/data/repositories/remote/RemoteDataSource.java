package com.etologic.fintonictestchallenge.data.repositories.remote;

import com.etologic.fintonictestchallenge.BuildConfig;
import com.etologic.fintonictestchallenge.data.DataSource;
import com.etologic.fintonictestchallenge.data.errors.DownloadHeroesError;
import com.etologic.fintonictestchallenge.data.requests.GetHeroRequest;
import com.etologic.fintonictestchallenge.data.requests.SaveHeroesRequest;
import com.etologic.fintonictestchallenge.data.responses.DownloadHeroesResponse;
import com.etologic.fintonictestchallenge.domain.threading.UseCaseHandler;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by ernesto.vega on 16/06/2017.
 */

public class RemoteDataSource implements DataSource {

    //region Constants

    private static final String BASE_URL = "https://api.myjson.com";

    //endregion

    //region Fields

    private static RemoteDataSource instance;
    private APIServices apiServices = null;

    //endregion

    //region Constructors

    private RemoteDataSource() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        if (BuildConfig.DEBUG) {
            //Interceptor to LOG requests and response (only in Debug Mode)
            HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
            httpClient.addInterceptor(logging);
        }
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .callbackExecutor(UseCaseHandler.getThreadPoolExecutor())
                .client(httpClient.build())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        apiServices = retrofit.create(APIServices.class);
    }

    public static RemoteDataSource getInstance() {
        if (instance == null) {
            instance = new RemoteDataSource();
        }
        return instance;
    }

    //endregion

    @Override
    public void downloadHeroes(final DownloadHeroesCallback downloadHeroesCallback) {
        Call<DownloadHeroesResponse> downloadHeroesResponseCall = apiServices.getHeroes();
        downloadHeroesResponseCall.enqueue(new Callback<DownloadHeroesResponse>() {
            @Override
            public void onResponse(Call<DownloadHeroesResponse> call,
                                   Response<DownloadHeroesResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    downloadHeroesCallback.onDownloadHeroesSuccess(response.body());
                }
                else {
                    downloadHeroesCallback.onDownloadHeroesFailure(new DownloadHeroesError(""));
                }
            }

            @Override
            public void onFailure(Call<DownloadHeroesResponse> call, Throwable t) {
                downloadHeroesCallback.onDownloadHeroesFailure(
                        new DownloadHeroesError(t.getMessage()));
            }
        });
    }

    @Override
    public void saveHeroes(SaveHeroesRequest saveHeroesRequest,
                           SaveHeroesCallback saveHeroesCallback) {}

    @Override
    public void getHeroes(GetHeroesCallback getHeroesCallback) {}

    @Override
    public void getHero(GetHeroRequest getHeroRequest, GetHeroCallback getHeroCallback) {}
}
