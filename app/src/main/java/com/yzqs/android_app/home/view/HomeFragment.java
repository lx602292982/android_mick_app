package com.yzqs.android_app.home.view;

import android.os.Bundle;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.yzqs.android_app.R;
import com.yzqs.android_app.baseapp.BaseFragment;
import com.yzqs.android_app.utils.GlideImageLoader;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

public class HomeFragment extends BaseFragment {
    @BindView(R.id.banner)
    Banner mBanner;
    private List<Integer> images= new ArrayList<Integer>();

    /**
     * 布局文件加载
     */
    @Override
    public int getLayoutId() {
        return R.layout.fragment_home;
    }


    public static HomeFragment getInstance() {
        Bundle args = new Bundle();
        HomeFragment homeFragment = new HomeFragment();
        homeFragment.setArguments(args);
        return homeFragment;
    }


    /**
     * 初始化参数
     */
    @Override
    public void init() {

        images.add(R.mipmap.timg);
        images.add(R.mipmap.timg1);
        images.add(R.mipmap.timg2);
        images.add(R.mipmap.timg3);
        images.add(R.mipmap.timg4);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置banner动画效果
        mBanner.setBannerAnimation(Transformer.DepthPage);
        //设置标题集合（当banner样式有显示title时）
        //设置自动轮播，默认为true
        mBanner.isAutoPlay(true);
        //设置轮播时间
        mBanner.setDelayTime(2500);
        //设置指示器位置（当banner模式中有指示器时）
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置图片集合
        mBanner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        mBanner.start();
    }

    /**
     * 请求数据
     */
    @Override
    public void requestData() {

    }
}
