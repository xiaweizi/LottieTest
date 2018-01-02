package com.xiaweizi.lottietest;

import android.os.Handler;
import android.support.annotation.FloatRange;
import android.text.TextUtils;
import android.util.Log;

import com.airbnb.lottie.LottieAnimationView;

/**
 * <pre>
 *     author : xiaweizi
 *     class  : com.xiaweizi.lottietest.PullToRefreshUtil
 *     e-mail : 1012126908@qq.com
 *     time   : 2018/01/02
 *     desc   :
 * </pre>
 */

public class PullToRefreshUtil {
    /**
     * 动画日志 TAG
     */
    private static final String TAG = "PullToRefreshUtil:";
    /**
     * 下拉刷新动画文件名
     */
    private static final String PULL_FILE_NAME = "data_pull.json";
    /**
     * 正在加载动画文件名
     */
    private static final String LOADING_FILE_NAME = "data_loading.json";

    private LottieAnimationView mAnimationView;

    public PullToRefreshUtil(LottieAnimationView view) {
        this(view, PULL_FILE_NAME);
    }

    public PullToRefreshUtil(LottieAnimationView view, String viewFileName) {
        this.mAnimationView = view;
        if (!TextUtils.isEmpty(viewFileName)) {
            mAnimationView.setAnimation(viewFileName, LottieAnimationView.CacheStrategy.Weak);
        }
        mAnimationView.useHardwareAcceleration(true);
    }

    /**
     * 用于设置下拉刷新的进度
     *
     * @param progress 下拉刷新的进度
     */
    public void setRefreshProgress(@FloatRange(from = 0f, to = 1f) float progress) {
        Log.i(TAG, "下拉刷新的进度 progress:\t" + progress);
        if (mAnimationView != null) {
            float tempProgress = progress;
            if (progress < 0) {
                tempProgress = 0;
            }
            if (progress > 1f) {
                tempProgress = 1f;
            }
            mAnimationView.setProgress(tempProgress);
        }
    }

    /**
     * 显示 loading 的效果
     */
    public void refreshLoading() {
        if (mAnimationView == null) {
            return;
        }
        Log.i(TAG, "正在加载中...");
        mAnimationView.cancelAnimation();
        mAnimationView.setAnimation(LOADING_FILE_NAME, LottieAnimationView.CacheStrategy.Weak);
        mAnimationView.loop(true);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                mAnimationView.playAnimation();
            }
        }, 10);
    }

    /**
     * 重置下拉刷新动画状态
     */
    public void refreshReset() {
        if (mAnimationView == null) {
            return;
        }
        Log.i(TAG, "下拉刷新重置！");
        mAnimationView.loop(false);
        mAnimationView.setAnimation(PULL_FILE_NAME, LottieAnimationView.CacheStrategy.Weak);
        mAnimationView.clearAnimation();
        mAnimationView.setProgress(1f);
    }
}

