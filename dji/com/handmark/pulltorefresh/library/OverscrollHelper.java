package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.view.View;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;

@TargetApi(9)
public final class OverscrollHelper {
    static final float DEFAULT_OVERSCROLL_SCALE = 1.0f;
    static final String LOG_TAG = "OverscrollHelper";

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, boolean z) {
        overScrollBy(pullToRefreshBase, i, i2, i3, i4, 0, z);
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, boolean z) {
        overScrollBy(pullToRefreshBase, i, i2, i3, i4, i5, 0, 1.0f, z);
    }

    public static void overScrollBy(PullToRefreshBase<?> pullToRefreshBase, int i, int i2, int i3, int i4, int i5, int i6, float f, boolean z) {
        int scrollX;
        switch (pullToRefreshBase.getPullToRefreshScrollDirection()) {
            case HORIZONTAL:
                scrollX = pullToRefreshBase.getScrollX();
                break;
            default:
                scrollX = pullToRefreshBase.getScrollY();
                i2 = i4;
                i = i3;
                break;
        }
        if (pullToRefreshBase.isPullToRefreshOverScrollEnabled() && !pullToRefreshBase.isRefreshing()) {
            Mode mode = pullToRefreshBase.getMode();
            if (mode.permitsPullToRefresh() && !z && i != 0) {
                int i7 = i + i2;
                if (i7 < 0 - i6) {
                    if (mode.showHeaderLoadingLayout()) {
                        if (scrollX == 0) {
                            pullToRefreshBase.setState(State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setHeaderScroll((int) (((float) (scrollX + i7)) * f));
                    }
                } else if (i7 > i5 + i6) {
                    if (mode.showFooterLoadingLayout()) {
                        if (scrollX == 0) {
                            pullToRefreshBase.setState(State.OVERSCROLLING, new boolean[0]);
                        }
                        pullToRefreshBase.setHeaderScroll((int) (((float) ((scrollX + i7) - i5)) * f));
                    }
                } else if (Math.abs(i7) <= i6 || Math.abs(i7 - i5) <= i6) {
                    pullToRefreshBase.setState(State.RESET, new boolean[0]);
                }
            } else if (z && State.OVERSCROLLING == pullToRefreshBase.getState()) {
                pullToRefreshBase.setState(State.RESET, new boolean[0]);
            }
        }
    }

    static boolean isAndroidOverScrollEnabled(View view) {
        return view.getOverScrollMode() != 2;
    }
}
