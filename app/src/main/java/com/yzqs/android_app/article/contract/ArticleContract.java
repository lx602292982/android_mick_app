package com.yzqs.android_app.article.contract;


import com.youngmanster.collectionlibrary.mvp.BaseModel;
import com.youngmanster.collectionlibrary.mvp.BasePresenter;
import com.youngmanster.collectionlibrary.mvp.BaseView;
import com.yzqs.android_app.beans.ArticleBody;

import io.reactivex.Observable;


/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public interface ArticleContract {

    interface Model extends BaseModel {
        Observable<ArticleBody> loadAndroidNews(int page, int size);
    }

    interface View extends BaseView {

        void refreshUI(ArticleBody been);

    }

    abstract class Presenter extends BasePresenter<Model,View> {
        public abstract void requestAndroidNews(int pre_page, int page);
    }
}
