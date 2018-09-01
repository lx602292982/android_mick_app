package com.yzqs.android_app.article;

import android.content.Context;
import android.widget.ImageView;

import com.youngmanster.collectionlibrary.refreshrecyclerview.base.adapter.BaseRecyclerViewAdapter;
import com.youngmanster.collectionlibrary.refreshrecyclerview.base.adapter.BaseViewHolder;
import com.youngmanster.collectionlibrary.refreshrecyclerview.pulltorefresh.PullToRefreshRecyclerView;
import com.yzqs.android_app.R;
import com.yzqs.android_app.application.MyApplication;
import com.yzqs.android_app.beans.Article;
import com.yzqs.android_app.utils.Utils;

import java.util.ArrayList;

/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public class ArticleAdapter extends BaseRecyclerViewAdapter<Article> {


    public ArticleAdapter(Context mContext, ArrayList<Article> mDatas, PullToRefreshRecyclerView pullToRefreshRecyclerView) {
        super(mContext, R.layout.item_article, mDatas, pullToRefreshRecyclerView);
    }


    /**
     * 简化ViewHolder创建
     *
     * @param baseViewHolder
     * @param article
     */
    @Override
    protected void convert(BaseViewHolder baseViewHolder, Article article) {
        baseViewHolder.setText(R.id.tv_article_views, article.getViews());
        MyApplication.getImageLoader(mContext).displayImage(article.getCoverUrl(), (ImageView) baseViewHolder.getView(R.id.iv_article_cover),
                Utils.getImageOptions(R.mipmap.ic_launcher));
        MyApplication.getImageLoader(mContext).displayImage(article.getHeadImgUrl(), (ImageView) baseViewHolder.getView(R.id.iv_article_head_img));
        Utils.getImageOptions(true);
        baseViewHolder.setText(R.id.tv_username, article.getUserName());

        baseViewHolder.setText(R.id.tv_username, article.getUserName());
        baseViewHolder.setText(R.id.tv_article_likes, article.getLikes());
        baseViewHolder.setText(R.id.tv_article_title, article.getTitle());

        baseViewHolder.setText(R.id.tv_article_time, article.getTime());
        baseViewHolder.setText(R.id.tv_article_content, article.getBriefContent());
    }

}