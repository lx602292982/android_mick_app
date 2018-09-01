package com.yzqs.android_app.article.presenter;

import com.youngmanster.collectionlibrary.network.NetWorkCodeException;
import com.youngmanster.collectionlibrary.network.RequestManager;
import com.youngmanster.collectionlibrary.network.rx.RxObservableListener;
import com.yzqs.android_app.article.contract.ArticleContract;
import com.yzqs.android_app.beans.ArticleBody;

/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public class ArticlePresenter extends ArticleContract.Presenter {

    @Override
    public void requestAndroidNews(int pre_page, int page) {
        rxManager.addObserver(RequestManager.loadOnlyNetWork(mModel.loadAndroidNews(pre_page,page),
                new RxObservableListener<ArticleBody>(mView) {
                    @Override
                    public void onNext(ArticleBody result) {
                                    mView.refreshUI(result);
                    }

                    @Override
                    public void onError(NetWorkCodeException.ResponseThrowable e) {
                        super.onError(e);
                        mView.onError(e.toString());
                    }

                    @Override
                    public void onComplete() {
                        super.onComplete();
                    }
                }));
    }
}
