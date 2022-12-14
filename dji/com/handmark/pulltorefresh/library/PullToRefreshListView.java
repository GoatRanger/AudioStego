package com.handmark.pulltorefresh.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;
import android.widget.ListAdapter;
import android.widget.ListView;
import com.handmark.pulltorefresh.library.PullToRefreshBase.AnimationStyle;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Orientation;
import com.handmark.pulltorefresh.library.PullToRefreshBase.State;
import com.handmark.pulltorefresh.library.internal.EmptyViewMethodAccessor;
import com.handmark.pulltorefresh.library.internal.LoadingLayout;

public class PullToRefreshListView extends PullToRefreshAdapterViewBase<ListView> {
    private LoadingLayout mFooterLoadingView;
    private LoadingLayout mHeaderLoadingView;
    private boolean mListViewExtrasEnabled;
    private FrameLayout mLvFooterLoadingFrame;

    protected class InternalListView extends ListView implements EmptyViewMethodAccessor {
        private boolean mAddedLvFooter = false;

        public InternalListView(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected void dispatchDraw(Canvas canvas) {
            try {
                super.dispatchDraw(canvas);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
            }
        }

        public boolean dispatchTouchEvent(MotionEvent motionEvent) {
            try {
                return super.dispatchTouchEvent(motionEvent);
            } catch (IndexOutOfBoundsException e) {
                e.printStackTrace();
                return false;
            }
        }

        public void setAdapter(ListAdapter listAdapter) {
            if (!(PullToRefreshListView.this.mLvFooterLoadingFrame == null || this.mAddedLvFooter)) {
                addFooterView(PullToRefreshListView.this.mLvFooterLoadingFrame, null, false);
                this.mAddedLvFooter = true;
            }
            super.setAdapter(listAdapter);
        }

        public void setEmptyView(View view) {
            PullToRefreshListView.this.setEmptyView(view);
        }

        public void setEmptyViewInternal(View view) {
            super.setEmptyView(view);
        }
    }

    @TargetApi(9)
    final class InternalListViewSDK9 extends InternalListView {
        public InternalListViewSDK9(Context context, AttributeSet attributeSet) {
            super(context, attributeSet);
        }

        protected boolean overScrollBy(int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8, boolean z) {
            boolean overScrollBy = super.overScrollBy(i, i2, i3, i4, i5, i6, i7, i8, z);
            OverscrollHelper.overScrollBy(PullToRefreshListView.this, i, i3, i2, i4, z);
            return overScrollBy;
        }
    }

    public PullToRefreshListView(Context context) {
        super(context);
    }

    public PullToRefreshListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public PullToRefreshListView(Context context, Mode mode) {
        super(context, mode);
    }

    public PullToRefreshListView(Context context, Mode mode, AnimationStyle animationStyle) {
        super(context, mode, animationStyle);
    }

    public final Orientation getPullToRefreshScrollDirection() {
        return Orientation.VERTICAL;
    }

    protected void onRefreshing(boolean z) {
        ListAdapter adapter = ((ListView) this.mRefreshableView).getAdapter();
        if (!this.mListViewExtrasEnabled || !getShowViewWhileRefreshing() || adapter == null || adapter.isEmpty()) {
            super.onRefreshing(z);
            return;
        }
        LoadingLayout footerLayout;
        LoadingLayout loadingLayout;
        LoadingLayout loadingLayout2;
        int count;
        int scrollY;
        super.onRefreshing(false);
        switch (getCurrentMode()) {
            case MANUAL_REFRESH_ONLY:
            case PULL_FROM_END:
                footerLayout = getFooterLayout();
                loadingLayout = this.mFooterLoadingView;
                loadingLayout2 = this.mHeaderLoadingView;
                count = ((ListView) this.mRefreshableView).getCount() - 1;
                scrollY = getScrollY() - getFooterSize();
                break;
            default:
                loadingLayout = getHeaderLayout();
                loadingLayout2 = this.mHeaderLoadingView;
                scrollY = getScrollY() + getHeaderSize();
                footerLayout = loadingLayout;
                loadingLayout = loadingLayout2;
                loadingLayout2 = this.mFooterLoadingView;
                count = 0;
                break;
        }
        footerLayout.reset();
        footerLayout.hideAllViews();
        loadingLayout2.setVisibility(8);
        loadingLayout.setVisibility(0);
        loadingLayout.refreshing();
        if (z) {
            disableLoadingLayoutVisibilityChanges();
            setHeaderScroll(scrollY);
            ((ListView) this.mRefreshableView).setSelection(count);
            smoothScrollTo(0);
        }
    }

    protected void onReset() {
        int i = 0;
        int i2 = 1;
        if (this.mListViewExtrasEnabled) {
            int i3;
            LoadingLayout loadingLayout;
            LoadingLayout loadingLayout2;
            LoadingLayout loadingLayout3;
            int count;
            switch (getCurrentMode()) {
                case MANUAL_REFRESH_ONLY:
                case PULL_FROM_END:
                    LoadingLayout footerLayout = getFooterLayout();
                    loadingLayout3 = this.mFooterLoadingView;
                    count = ((ListView) this.mRefreshableView).getCount() - 1;
                    int footerSize = getFooterSize();
                    if (Math.abs(((ListView) this.mRefreshableView).getLastVisiblePosition() - count) <= 1) {
                        i3 = 1;
                    } else {
                        i3 = 0;
                    }
                    i = count;
                    i2 = footerSize;
                    loadingLayout = loadingLayout3;
                    loadingLayout2 = footerLayout;
                    break;
                default:
                    loadingLayout3 = getHeaderLayout();
                    loadingLayout2 = this.mHeaderLoadingView;
                    count = -getHeaderSize();
                    if (Math.abs(((ListView) this.mRefreshableView).getFirstVisiblePosition() - 0) > 1) {
                        i2 = 0;
                    }
                    i3 = i2;
                    i2 = count;
                    loadingLayout = loadingLayout2;
                    loadingLayout2 = loadingLayout3;
                    break;
            }
            if (loadingLayout.getVisibility() == 0) {
                loadingLayout2.showInvisibleViews();
                loadingLayout.setVisibility(8);
                if (!(i3 == 0 || getState() == State.MANUAL_REFRESHING)) {
                    ((ListView) this.mRefreshableView).setSelection(i);
                    setHeaderScroll(i2);
                }
            }
            super.onReset();
            return;
        }
        super.onReset();
    }

    protected LoadingLayoutProxy createLoadingLayoutProxy(boolean z, boolean z2) {
        LoadingLayoutProxy createLoadingLayoutProxy = super.createLoadingLayoutProxy(z, z2);
        if (this.mListViewExtrasEnabled) {
            Mode mode = getMode();
            if (z && mode.showHeaderLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mHeaderLoadingView);
            }
            if (z2 && mode.showFooterLoadingLayout()) {
                createLoadingLayoutProxy.addLayout(this.mFooterLoadingView);
            }
        }
        return createLoadingLayoutProxy;
    }

    protected ListView createListView(Context context, AttributeSet attributeSet) {
        if (VERSION.SDK_INT >= 9) {
            return new InternalListViewSDK9(context, attributeSet);
        }
        return new InternalListView(context, attributeSet);
    }

    protected ListView createRefreshableView(Context context, AttributeSet attributeSet) {
        ListView createListView = createListView(context, attributeSet);
        createListView.setId(16908298);
        return createListView;
    }

    protected void handleStyledAttributes(TypedArray typedArray) {
        super.handleStyledAttributes(typedArray);
        this.mListViewExtrasEnabled = typedArray.getBoolean(14, true);
        if (this.mListViewExtrasEnabled) {
            LayoutParams layoutParams = new FrameLayout.LayoutParams(-1, -2, 1);
            View frameLayout = new FrameLayout(getContext());
            this.mHeaderLoadingView = createLoadingLayout(getContext(), Mode.PULL_FROM_START, typedArray);
            this.mHeaderLoadingView.setVisibility(8);
            frameLayout.addView(this.mHeaderLoadingView, layoutParams);
            ((ListView) this.mRefreshableView).addHeaderView(frameLayout, null, false);
            this.mLvFooterLoadingFrame = new FrameLayout(getContext());
            this.mFooterLoadingView = createLoadingLayout(getContext(), Mode.PULL_FROM_END, typedArray);
            this.mFooterLoadingView.setVisibility(8);
            this.mLvFooterLoadingFrame.addView(this.mFooterLoadingView, layoutParams);
            if (!typedArray.hasValue(13)) {
                setScrollingWhileRefreshingEnabled(true);
            }
        }
    }
}
