package com.yzqs.android_app.utils;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.design.widget.Snackbar;
import android.view.View;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.yzqs.android_app.R;

/**
 * Created by KomoriWu
 * on 2017-03-24.
 */

public class Utils {

    public static int getToolbarHeight(Context context) {
        final TypedArray styledAttributes = context.getTheme().obtainStyledAttributes(
                new int[]{R.attr.actionBarSize});
        int toolbarHeight = (int) styledAttributes.getDimension(0, 0);
        styledAttributes.recycle();

        return toolbarHeight;
    }

    public static DisplayImageOptions getImageOptions() {
        return getImageOptions(R.mipmap.ic_launcher, 0);
    }

    public static DisplayImageOptions getImageOptions(boolean isCircular) {
        return getImageOptions(R.mipmap.ic_launcher, 180);
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId) {
        return getImageOptions(defaultIconId, 0);
    }

    public static DisplayImageOptions getImageOptions(int defaultIconId, int cornerRadiusPixels) {
        return new DisplayImageOptions.Builder()
                .displayer(new RoundedBitmapDisplayer(cornerRadiusPixels))
                .showImageOnLoading(defaultIconId)
                .showImageOnFail(defaultIconId)
                .showImageForEmptyUri(defaultIconId)
                .cacheInMemory(true)
                .cacheOnDisc()
                .build();
    }

    public static void showSnackBar(View view, String str) {
        Snackbar.make(view, str, Snackbar.LENGTH_SHORT).setAction(R.string.kown,
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                }).show();
    }


}
