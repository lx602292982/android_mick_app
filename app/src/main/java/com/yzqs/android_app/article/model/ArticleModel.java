package com.yzqs.android_app.article.model;

import com.youngmanster.collectionlibrary.network.RetrofitManager;
import com.yzqs.android_app.Model.BaseModel;
import com.yzqs.android_app.article.contract.ArticleContract;
import com.yzqs.android_app.beans.ArticleBody;
import com.yzqs.android_app.http.ApiService;

import java.util.HashMap;
import java.util.Map;

import io.reactivex.Observable;

/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public class ArticleModel implements ArticleContract.Model, BaseModel {

    @Override
    public Observable<ArticleBody> loadAndroidNews(int page, int size)
    {
        Map map = new HashMap();
        map.put("page", page);
        map.put("size", size);
        return RetrofitManager.getApiService(ApiService.class).getAllArticles(map);
    }
}