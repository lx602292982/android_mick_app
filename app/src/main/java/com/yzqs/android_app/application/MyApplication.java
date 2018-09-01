package com.yzqs.android_app.application;

import android.app.Application;
import android.content.Context;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.youngmanster.collectionlibrary.BuildConfig;
import com.youngmanster.collectionlibrary.config.Config;

/**
 * Created by Mick
 * on 2017-03-25.
 */

public class MyApplication extends Application {
    private static ImageLoader mImageLoader;

    @Override
    public void onCreate() {
        super.onCreate();
        config();

    }

    private void config() {
        Config.DEBUG= BuildConfig.DEBUG;
        Config.CONTEXT=this;
        Config.URL_DOMAIN="http://120.24.218.251:8080/geek_wz/";
    }

    public static ImageLoader getImageLoader(Context context) {
        if (mImageLoader == null) {
            synchronized (ImageLoader.class) {
                if (mImageLoader == null) {
                    mImageLoader = ImageLoader.getInstance();
                    mImageLoader.init(ImageLoaderConfiguration.createDefault(context.
                            getApplicationContext()));
                }
            }
        }
        return mImageLoader;
    }
}
