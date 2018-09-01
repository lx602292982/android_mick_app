package com.yzqs.android_app;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;

import com.flyco.tablayout.CommonTabLayout;
import com.flyco.tablayout.listener.CustomTabEntity;
import com.flyco.tablayout.listener.OnTabSelectListener;
import com.youngmanster.collectionlibrary.utils.StatusBarUtils;
import com.yzqs.android_app.article.widget.ArticleFragment;
import com.yzqs.android_app.baseapp.BaseActivity;
import com.yzqs.android_app.beans.TabEntity;
import com.yzqs.android_app.home.view.HomeFragment;

import java.util.ArrayList;

import butterknife.BindView;

public class MainActivity extends BaseActivity {

    private String[] mTitles = new String[]{"每日精选", "发现", "热门", "我的"};

    // 未被选中的图标
    private int[] mIconUnSelectIds = {R.mipmap.ic_home_normal, R.mipmap.ic_discovery_normal, R.mipmap.ic_hot_normal, R.mipmap.ic_mine_normal};

    // 被选中的图标
    private int[] mIconSelectIds = {R.mipmap.ic_home_selected, R.mipmap.ic_discovery_selected, R.mipmap.ic_hot_selected, R.mipmap.ic_mine_selected};

    private ArrayList<CustomTabEntity> mTabEntities = new ArrayList<>();

    private ArticleFragment mArticleFragment = null;
    private HomeFragment mHomeFragment = null;



    //默认为0
    private int mIndex = 0;

    @BindView(R.id.tab_layout)
    CommonTabLayout tab_layout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        if (savedInstanceState !=null){
            mIndex = savedInstanceState.getInt("currTabIndex");
        }
        super.onCreate(savedInstanceState);
        initTab();
        tab_layout.setCurrentTab(mIndex);
        switchFragment(mIndex);
        setTitleContent(R.string.app_name);

    }

    /**
     * 布局文件加载
     */
    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private void switchFragment(int position) {
        setTitleContent(mTitles[0]);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        hideFragments(transaction);
        switch (position){
            case 0:
                if (mHomeFragment == null){
                    mHomeFragment = HomeFragment.getInstance();
                    transaction.add(R.id.fl_container, mHomeFragment, "home");
                } else {
                    transaction.show(mHomeFragment);
                    setTitleContent(mTitles[0]);
                }
                break;
            case 1:
                if (mArticleFragment == null){
                    mArticleFragment = ArticleFragment.getInstance();
                    transaction.add(R.id.fl_container, mArticleFragment, "home");
                } else {
                    transaction.show(mArticleFragment);
                    setTitleContent(mTitles[1]);
                }
                break;
            case 2:
                if (mArticleFragment == null){
                    mArticleFragment = ArticleFragment.getInstance();
                    transaction.add(R.id.fl_container, mArticleFragment, "home");
                } else {
                    transaction.show(mArticleFragment);
                    setTitleContent(mTitles[2]);
                }
                break;
            case 3:
                if (mArticleFragment == null){
                    mArticleFragment = ArticleFragment.getInstance();
                    transaction.add(R.id.fl_container, mArticleFragment, "home");
                } else {
                    transaction.show(mArticleFragment);
                    setTitleContent(mTitles[3]);
                }
                break;
        }
        mIndex = position;
        tab_layout.setCurrentTab(mIndex);
        transaction.commitAllowingStateLoss();

    }


    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private void hideFragments(FragmentTransaction transaction) {
        if (null != mHomeFragment) {
            transaction.hide(mHomeFragment);
        }
        if (null != mArticleFragment) {
            transaction.hide(mArticleFragment);
        }
        if (null != mArticleFragment) {
            transaction.hide(mArticleFragment);
        }
        if (null != mArticleFragment) {
            transaction.hide(mArticleFragment);
        }
    }

    private void initTab() {
        for (int i = 0; i < mTitles.length; i++) {
            mTabEntities.add(new TabEntity(mTitles[i], mIconSelectIds[i], mIconUnSelectIds[i]));
        }
        //为Tab赋值
        tab_layout.setTabData( mTabEntities);
        tab_layout.setOnTabSelectListener(new OnTabSelectListener() {
            @Override
            public void onTabSelect(int position) {
                switchFragment(position);
            }

            @Override
            public void onTabReselect(int position) {

            }
        });

    }

    /**
     * 初始化参数
     */
    @Override
    public void init() {
        StatusBarUtils.setTransparent(this);

    }

    /**
     * 请求数据
     */
    @Override
    public void requestData() {

    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        //记录fragment的位置,防止崩溃 activity被系统回收时，fragment错乱
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex);
        }
    }


    private Long mExitTime  = 0L;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {

            if ((System.currentTimeMillis()-mExitTime) <= 2000) {
                finish();
            } else {
                mExitTime = System.currentTimeMillis();
                showToast("再按一次退出程序");
            }
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }

}
