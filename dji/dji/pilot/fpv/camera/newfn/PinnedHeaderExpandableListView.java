package dji.pilot.fpv.camera.newfn;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnGroupClickListener;
import dji.pilot.fpv.camera.newfn.b.b;

public class PinnedHeaderExpandableListView extends ExpandableListView implements OnScrollListener {
    private static final String b = "PinnedHeaderExpandableListView";
    private static final boolean c = true;
    protected boolean a = true;
    private View d;
    private int e;
    private int f;
    private View g;
    private OnScrollListener h;
    private a i;
    private boolean j = false;

    public interface a {
        View getPinnedHeader();

        void updatePinnedHeader(View view, int i);
    }

    public PinnedHeaderExpandableListView(Context context) {
        super(context);
        b();
    }

    public PinnedHeaderExpandableListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        b();
    }

    public PinnedHeaderExpandableListView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        b();
    }

    private void b() {
        setFadingEdgeLength(0);
        setOnScrollListener(this);
    }

    public void setOnScrollListener(OnScrollListener onScrollListener) {
        if (onScrollListener != this) {
            this.h = onScrollListener;
        } else {
            this.h = null;
        }
        super.setOnScrollListener(this);
    }

    public void setOnGroupClickListener(OnGroupClickListener onGroupClickListener, boolean z) {
        this.a = z;
        super.setOnGroupClickListener(onGroupClickListener);
    }

    public void setOnHeaderUpdateListener(a aVar) {
        this.i = aVar;
        if (aVar == null) {
            this.d = null;
            this.f = 0;
            this.e = 0;
            return;
        }
        this.d = aVar.getPinnedHeader();
        int firstVisiblePosition = getFirstVisiblePosition();
        getPackedPositionGroup(getExpandableListPosition(firstVisiblePosition));
        aVar.updatePinnedHeader(this.d, firstVisiblePosition);
        requestLayout();
        postInvalidate();
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        if (this.d != null) {
            measureChild(this.d, i, i2);
            this.e = this.d.getMeasuredWidth();
            this.f = this.d.getMeasuredHeight();
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.d != null) {
            int top = this.d.getTop();
            this.d.layout(0, top, this.e, this.f + top);
        }
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        if (this.d != null) {
            drawChild(canvas, this.d, getDrawingTime());
        }
    }

    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int x = (int) motionEvent.getX();
        int y = (int) motionEvent.getY();
        int pointToPosition = pointToPosition(x, y);
        if (this.d == null || y < this.d.getTop() || y > this.d.getBottom()) {
            return super.dispatchTouchEvent(motionEvent);
        }
        if (motionEvent.getAction() == 0) {
            this.g = a(this.d, x, y);
            this.j = true;
            return true;
        } else if (motionEvent.getAction() != 1) {
            return true;
        } else {
            if (a(this.d, x, y) == this.g && this.g.isClickable()) {
                this.g.performClick();
                invalidate(new Rect(0, 0, this.e, this.f));
            } else if (this.a) {
                x = getPackedPositionGroup(getExpandableListPosition(pointToPosition));
                if (x != -1 && this.j) {
                    if (isGroupExpanded(x)) {
                        collapseGroup(x);
                    } else {
                        expandGroup(x);
                    }
                }
            }
            this.j = false;
            return true;
        }
    }

    private View a(View view, int i, int i2) {
        if (!(view instanceof ViewGroup)) {
            return view;
        }
        View childAt;
        ViewGroup viewGroup = (ViewGroup) view;
        int childCount = viewGroup.getChildCount();
        boolean isChildrenDrawingOrderEnabled = isChildrenDrawingOrderEnabled();
        int i3 = childCount - 1;
        while (i3 >= 0) {
            childAt = viewGroup.getChildAt(isChildrenDrawingOrderEnabled ? getChildDrawingOrder(childCount, i3) : i3);
            if (b(childAt, i, i2)) {
                break;
            }
            i3--;
        }
        childAt = null;
        if (childAt != null) {
            return childAt;
        }
        return viewGroup;
    }

    private boolean b(View view, int i, int i2) {
        if (!view.isClickable() || i2 < view.getTop() || i2 > view.getBottom() || i < view.getLeft() || i > view.getRight()) {
            return false;
        }
        return true;
    }

    public void requestRefreshHeader() {
        a();
        invalidate(new Rect(0, 0, this.e, this.f));
    }

    protected void a() {
        if (this.d != null) {
            int firstVisiblePosition = getFirstVisiblePosition();
            int i = firstVisiblePosition + 1;
            int packedPositionGroup = getPackedPositionGroup(getExpandableListPosition(firstVisiblePosition));
            i = getPackedPositionGroup(getExpandableListPosition(i));
            b bVar = (b) getExpandableListAdapter().getGroup(i);
            Log.d(b, "refreshHeader firstVisibleGroupPos=" + packedPositionGroup);
            if (i == packedPositionGroup + 1 && bVar.d.contains("@T")) {
                View childAt = getChildAt(1);
                if (childAt == null) {
                    Log.w(b, "Warning : refreshHeader getChildAt(1)=null");
                    return;
                } else if (childAt.getTop() <= this.f) {
                    firstVisiblePosition = this.f - childAt.getTop();
                    this.d.layout(0, -firstVisiblePosition, this.e, this.f - firstVisiblePosition);
                } else {
                    this.d.layout(0, 0, this.e, this.f);
                }
            } else {
                this.d.layout(0, 0, this.e, this.f);
            }
            if (this.i != null) {
                this.i.updatePinnedHeader(this.d, packedPositionGroup);
            }
        }
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
        if (this.h != null) {
            this.h.onScrollStateChanged(absListView, i);
        }
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        if (i3 > 0) {
            a();
        }
        if (this.h != null) {
            this.h.onScroll(absListView, i, i2, i3);
        }
    }
}
