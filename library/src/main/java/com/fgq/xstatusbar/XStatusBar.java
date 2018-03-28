package com.fgq.xstatusbar;

import android.annotation.TargetApi;
import android.app.Activity;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.ColorInt;
import android.support.annotation.IntRange;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;


/**
 * Created by fgq on 2017/6/27.
 */
public class XStatusBar {

    private static final int DEFAULT_ALPHA = 112;
    private static final String STATUS_VIEW_TAG = "status_view_tag";

    /**
     * 1.1、设置状态栏颜色(默认透明度)
     */
    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color) {
        setStatusBarColor(activity, color, DEFAULT_ALPHA);
    }

    /**
     * 1.2、设置状态栏颜色(自设透明度)
     */
    public static void setStatusBarColor(@NonNull Activity activity, @ColorInt int color, @IntRange(from = 0, to = 255) int alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        } else {
            setStatusBarColorInLOLLIPOP(activity, SystemBarUtil.calculateStatusColor(color, alpha));
        }
    }

    /**
     * 2、全透明状态栏(ContentView 内容会顶到状态栏)
     */
    public static void setStatusBarTransparent(@NonNull Activity activity) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        transparentStatusBar(activity);
    }

    /**
     * 3.1、半透明状态栏(ContentView 内容会顶到状态栏,状态栏被半透明小色块替代)(默认透明度)
     */
    public static void setStatusBarTranslucent(@NonNull Activity activity) {
        setStatusBarTranslucent(activity, DEFAULT_ALPHA);
    }

    /**
     * 3.2、半透明状态栏(ContentView 内容会顶到状态栏,状态栏被半透明小色块替代)(默认透明度)
     */
    public static void setStatusBarTranslucent(@NonNull Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
            return;
        }
        setStatusBarTransparent(activity);//先将状态栏全透明
        addTranslucentView(activity, alpha);
    }


    private static void setStatusBarColorInLOLLIPOP(Activity activity, int color) {
        Window window = activity.getWindow();
        //取消设置透明状态栏,使 ContentView 内容不再覆盖状态栏
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个flag才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        //设置状态栏颜色
        window.setStatusBarColor(color);
    }


    /**
     * 代码设置setFitsSystemWindows
     * 直接给ContentView设置无效，所以设置给ContentView 的第一个子 View，即布局文件中的layout填充形成的View
     */
    public static void setFitsSystemWindows(Activity activity, boolean isFitsSystemWindows) {
        ViewGroup mContentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View mChildView = mContentView.getChildAt(0);//这里就是我们的布局文件

        if (mChildView != null) {
            mChildView.setFitsSystemWindows(isFitsSystemWindows);
        }
    }


    private static void transparentStatusBar(Activity activity) {
        Window window = activity.getWindow();
        //设置透明状态栏,这样能让ContentView 内容覆盖状态栏
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        //需要设置这个 flag 才能调用 setStatusBarColor 来设置状态栏颜色
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
//            window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        window.setStatusBarColor(Color.TRANSPARENT);
    }

    /**
     * 添加半透明矩形条
     * ContentView实质为 FrameLayout，这里为其添加一个半透明小色块，代替已经全透明的状态栏
     */
    private static void addTranslucentView(Activity activity, @IntRange(from = 0, to = 255) int alpha) {
        ViewGroup contentView = (ViewGroup) activity.findViewById(Window.ID_ANDROID_CONTENT);
        View translucentView = contentView.findViewWithTag(STATUS_VIEW_TAG);
        if (translucentView != null) {
            if (translucentView.getVisibility() == View.GONE) {
                translucentView.setVisibility(View.VISIBLE);
            }
            translucentView.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
        } else {
            contentView.addView(createStatusBarView(activity, alpha));
        }
    }


    /**
     * 绘制一个和状态栏高度一致的半透明View
     */
    private static View createStatusBarView(Activity activity, int alpha) {
        View translucentView = new View(activity);
        FrameLayout.LayoutParams params =
                new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, SystemBarUtil.getStatusBarHeight(activity));
        translucentView.setLayoutParams(params);
        translucentView.setBackgroundColor(Color.argb(alpha, 0, 0, 0));
        translucentView.setTag(STATUS_VIEW_TAG);
        return translucentView;
    }


    /**
     * 6.0以上状态栏字体、图标颜色设置
     */
    @TargetApi(Build.VERSION_CODES.M)
    public static void setStatusFontColor(Activity activity, boolean isDarkMode) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decorView = activity.getWindow().getDecorView();
            int visibility = decorView.getSystemUiVisibility();
            if (isDarkMode) {
                visibility |= View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            } else {
                visibility &= ~View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR;
            }
            decorView.setSystemUiVisibility(visibility);
        }
    }


}
