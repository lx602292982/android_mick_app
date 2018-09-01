package com.yzqs.android_app.article.widget;


import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.youngmanster.collectionlibrary.base.StateView;
import com.youngmanster.collectionlibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;
import com.yzqs.android_app.R;
import com.yzqs.android_app.article.ArticleAdapter;
import com.yzqs.android_app.article.contract.ArticleContract;
import com.yzqs.android_app.article.model.ArticleModel;
import com.yzqs.android_app.article.presenter.ArticlePresenter;
import com.yzqs.android_app.baseapp.BaseFragment;
import com.yzqs.android_app.beans.Article;
import com.yzqs.android_app.beans.ArticleBody;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public class ArticleFragment extends BaseFragment<ArticleModel,ArticlePresenter> implements ArticleContract.View,
        PullToRefreshRecyclerView.OnRefreshAndLoadMoreListener {
    @BindView(R.id.layout_refresh)
    PullToRefreshRecyclerView refreshLayout;
    @BindView(R.id.state_view)
    StateView stateView;
    private int mPage = 1;
    private int mSize = 8;
    private ArticleAdapter mArticleAdapter;

    private ArrayList<Article> mDatas = new ArrayList<>();



    /**
     * 布局文件加载
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_article;
    }

    /**
     * 初始化参数
     */
    @Override
    public void init() {
        stateView.showViewByState(StateView.STATE_LOADING);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        refreshLayout.setLayoutManager(linearLayoutManager);
        refreshLayout.setPullRefreshEnabled(true);
        refreshLayout.setLoadMoreEnabled(true);
        refreshLayout.setRefreshAndLoadMoreListener(this);

        stateView.setOnDisConnectViewListener(new StateView.OnDisConnectListener() {
            @Override
            public void onDisConnectViewClick() {
                stateView.showViewByState(StateView.STATE_LOADING);
                mPage = 1;
                requestData();
            }
        });

    }


    /**
     * 请求数据
     */
    @Override
    public void requestData() {
        ((ArticlePresenter) mPresenter).requestAndroidNews(mPage,mSize);
    }

    @Override
    public void onRecyclerViewRefresh() {
            mPage = 1;
        requestData();
    }

    @Override
    public void onRecyclerViewLoadMore() {
        mPage++;
        requestData();
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        if(refreshLayout != null){
            refreshLayout.destroy();
            refreshLayout = null;
        }
    }

    @Override
    public void refreshUI(ArticleBody been) {
        if(been!=null){
            if (mPage == 1) {
                mDatas.clear();
                mDatas.addAll(been.getArticleArrayList());
            } else {
                mDatas.addAll(been.getArticleArrayList());
            }
            if (mArticleAdapter == null) {
                if(mDatas.size()==0){
                    stateView.showViewByState(StateView.STATE_EMPTY);
                }else{
                    stateView.showViewByState(StateView.STATE_NO_DATA);
                }
                mArticleAdapter = new ArticleAdapter(getActivity(), mDatas, refreshLayout);
                refreshLayout.setAdapter(mArticleAdapter);
            } else {
                if (refreshLayout.isLoading()) {
                    refreshLayout.loadMoreComplete();
                    if (been.getArticleArrayList().size() == 0) {
                        refreshLayout.setNoMoreDate(true);
                    }
                } else if (refreshLayout.isRefreshing()) {
                    refreshLayout.refreshComplete();
                }
            }
        }

    }

    public static ArticleFragment getInstance() {
        Bundle args = new Bundle();
        ArticleFragment fragment = new ArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onError(String errorMsg) {
        showToast(errorMsg);
        if(mDatas.size()==0){
            stateView.showViewByState(StateView.STATE_DISCONNECT);
        }
    }
}
