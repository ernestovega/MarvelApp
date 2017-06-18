package com.etologic.fintonictestchallenge.app.utils;

import android.content.Context;

import com.jakewharton.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;

public class PicassoCache {

    private static Picasso picassoInstance = null;

    private static final Interceptor REWRITE_CACHE_CONTROL_INTERCEPTOR = new Interceptor() {
        @Override
        public Response intercept(Chain chain) throws IOException {
            Response originalResponse = chain.proceed(chain.request());
            return originalResponse.newBuilder()
                    .header("Cache-Control", "max-age=" + 60 * 60 * 24 * 7) //1 week
                    .header("expires", "")
                    .build();
        }
    };

    private PicassoCache(Context context) {
        Cache cache = new Cache(new File(context.getCacheDir(), "PicassoCache"), Long.MAX_VALUE);

        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .cache(cache).addNetworkInterceptor(REWRITE_CACHE_CONTROL_INTERCEPTOR);

        OkHttpClient client = builder.build();

        picassoInstance = new Picasso.Builder(context)
//                .listener(new Picasso.Listener() {
//                    @Override
//                    public void onImageLoadFailed(Picasso picasso, Uri uri, Exception exception) {
//                        exception.printStackTrace();
//                    }
//                })
//                .indicatorsEnabled(true)
//                .loggingEnabled(true)
                .downloader(new OkHttp3Downloader(client))
                .build();
    }

    public static Picasso getPicassoInstance(Context context) {
        if (picassoInstance == null) {
            new PicassoCache(context);
            return picassoInstance;
        }
        return picassoInstance;
    }
}
