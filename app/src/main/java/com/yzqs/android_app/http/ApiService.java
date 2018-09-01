package com.yzqs.android_app.http;

import com.yzqs.android_app.beans.ArticleBody;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by yangyan
 * on 2018/3/21.
 */

public interface ApiService {

	@GET("Make_ArticleJson")
	Observable<ArticleBody> getAllArticles(@QueryMap Map<String, String> map);
}
