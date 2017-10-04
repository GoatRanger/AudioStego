package com.meetme.android.horizontallistview;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EdgeEffect;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.Scroller;
import dji.frame.widget.R;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class HorizontalListView extends AdapterView<ListAdapter> {
    private static final int e = -1;
    private static final int f = 0;
    private static final float g = 30.0f;
    private static final float h = 0.009f;
    private static final String i = "BUNDLE_ID_CURRENT_X";
    private static final String j = "BUNDLE_ID_PARENT_STATE";
    private boolean A = false;
    private d B = null;
    private a C = a.SCROLL_STATE_IDLE;
    private EdgeEffect D;
    private EdgeEffect E;
    private int F;
    private boolean G = false;
    private boolean H = false;
    private OnClickListener I;
    private DataSetObserver J = new DataSetObserver(this) {
        final /* synthetic */ HorizontalListView a;

        {
            this.a = r1;
        }

        public void onChanged() {
            this.a.o = true;
            this.a.A = false;
            this.a.f();
            this.a.invalidate();
            this.a.requestLayout();
        }

        public void onInvalidated() {
            this.a.A = false;
            this.a.f();
            this.a.c();
            this.a.invalidate();
            this.a.requestLayout();
        }
    };
    private Runnable K = new Runnable(this) {
        final /* synthetic */ HorizontalListView a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.requestLayout();
        }
    };
    protected Scroller a = new Scroller(getContext());
    protected ListAdapter b;
    protected int c;
    protected int d;
    private final a k = new a();
    private GestureDetector l;
    private int m;
    private List<Queue<View>> n = new ArrayList();
    private boolean o = false;
    private Rect p = new Rect();
    private View q = null;
    private int r = 0;
    private Drawable s = null;
    private Integer t = null;
    private int u = Integer.MAX_VALUE;
    private int v;
    private int w;
    private int x;
    private e y = null;
    private int z = 0;

    private class a extends SimpleOnGestureListener {
        final /* synthetic */ HorizontalListView a;

        private a(HorizontalListView horizontalListView) {
            this.a = horizontalListView;
        }

        public boolean onDown(MotionEvent motionEvent) {
            return this.a.a(motionEvent);
        }

        public boolean onFling(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            return this.a.a(motionEvent, motionEvent2, f, f2);
        }

        public boolean onScroll(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
            this.a.a(Boolean.valueOf(true));
            this.a.setCurrentScrollState(a.SCROLL_STATE_TOUCH_SCROLL);
            this.a.f();
            HorizontalListView horizontalListView = this.a;
            horizontalListView.d += (int) f;
            this.a.i(Math.round(f));
            this.a.requestLayout();
            return true;
        }

        public boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            this.a.f();
            OnItemClickListener onItemClickListener = this.a.getOnItemClickListener();
            int a = this.a.c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (a >= 0 && !this.a.G) {
                View childAt = this.a.getChildAt(a);
                int e = this.a.v + a;
                if (onItemClickListener != null) {
                    onItemClickListener.onItemClick(this.a, childAt, e, this.a.b.getItemId(e));
                    return true;
                }
            }
            if (!(this.a.I == null || this.a.G)) {
                this.a.I.onClick(this.a);
            }
            return false;
        }

        public void onLongPress(MotionEvent motionEvent) {
            this.a.f();
            int a = this.a.c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (a >= 0 && !this.a.G) {
                View childAt = this.a.getChildAt(a);
                OnItemLongClickListener onItemLongClickListener = this.a.getOnItemLongClickListener();
                if (onItemLongClickListener != null) {
                    int e = this.a.v + a;
                    if (onItemLongClickListener.onItemLongClick(this.a, childAt, e, this.a.b.getItemId(e))) {
                        this.a.performHapticFeedback(0);
                    }
                }
            }
        }
    }

    @TargetApi(11)
    private static final class b {
        private b() {
        }

        static {
            if (VERSION.SDK_INT < 11) {
                throw new RuntimeException("Should not get to HoneycombPlus class unless sdk is >= 11!");
            }
        }

        public static void a(Scroller scroller, float f) {
            if (scroller != null) {
                scroller.setFriction(f);
            }
        }
    }

    @TargetApi(14)
    private static final class c {
        private c() {
        }

        static {
            if (VERSION.SDK_INT < 14) {
                throw new RuntimeException("Should not get to IceCreamSandwichPlus class unless sdk is >= 14!");
            }
        }

        public static float a(Scroller scroller) {
            return scroller.getCurrVelocity();
        }
    }

    public interface d {

        public enum a {
            SCROLL_STATE_IDLE,
            SCROLL_STATE_TOUCH_SCROLL,
            SCROLL_STATE_FLING
        }

        void a(a aVar);
    }

    public interface e {
        void a();
    }

    public HorizontalListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.D = new EdgeEffect(context);
        this.E = new EdgeEffect(context);
        this.l = new GestureDetector(context, this.k);
        a();
        b();
        a(context, attributeSet);
        setWillNotDraw(false);
        if (VERSION.SDK_INT >= 11) {
            b.a(this.a, h);
        }
    }

    private void a() {
        setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ HorizontalListView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                return this.a.l.onTouchEvent(motionEvent);
            }
        });
    }

    private void a(Boolean bool) {
        if (this.H != bool.booleanValue()) {
            View view = this;
            while (view.getParent() instanceof View) {
                if ((view.getParent() instanceof ListView) || (view.getParent() instanceof ScrollView)) {
                    view.getParent().requestDisallowInterceptTouchEvent(bool.booleanValue());
                    this.H = bool.booleanValue();
                    return;
                }
                view = (View) view.getParent();
            }
        }
    }

    private void a(Context context, AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray obtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R.styleable.HorizontalListView);
            Drawable drawable = obtainStyledAttributes.getDrawable(R.styleable.HorizontalListView_android_divider);
            if (drawable != null) {
                setDivider(drawable);
            }
            int dimensionPixelSize = obtainStyledAttributes.getDimensionPixelSize(R.styleable.HorizontalListView_dividerWidth, 0);
            if (dimensionPixelSize != 0) {
                setDividerWidth(dimensionPixelSize);
            }
            obtainStyledAttributes.recycle();
        }
    }

    public Parcelable onSaveInstanceState() {
        Parcelable bundle = new Bundle();
        bundle.putParcelable(j, super.onSaveInstanceState());
        bundle.putInt(i, this.c);
        return bundle;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof Bundle) {
            Bundle bundle = (Bundle) parcelable;
            this.t = Integer.valueOf(bundle.getInt(i));
            super.onRestoreInstanceState(bundle.getParcelable(j));
        }
    }

    public void setDivider(Drawable drawable) {
        this.s = drawable;
        if (drawable != null) {
            setDividerWidth(drawable.getIntrinsicWidth());
        } else {
            setDividerWidth(0);
        }
    }

    public void setDividerWidth(int i) {
        this.r = i;
        requestLayout();
        invalidate();
    }

    private void b() {
        this.v = -1;
        this.w = -1;
        this.m = 0;
        this.c = 0;
        this.d = 0;
        this.u = Integer.MAX_VALUE;
        setCurrentScrollState(a.SCROLL_STATE_IDLE);
    }

    private void c() {
        b();
        removeAllViewsInLayout();
        requestLayout();
    }

    public void setSelection(int i) {
        this.x = i;
    }

    public View getSelectedView() {
        return g(this.x);
    }

    public void setAdapter(ListAdapter listAdapter) {
        if (this.b != null) {
            this.b.unregisterDataSetObserver(this.J);
        }
        if (listAdapter != null) {
            this.A = false;
            this.b = listAdapter;
            this.b.registerDataSetObserver(this.J);
        }
        a(this.b.getViewTypeCount());
        c();
    }

    public ListAdapter getAdapter() {
        return this.b;
    }

    private void a(int i) {
        this.n.clear();
        for (int i2 = 0; i2 < i; i2++) {
            this.n.add(new LinkedList());
        }
    }

    private View b(int i) {
        int itemViewType = this.b.getItemViewType(i);
        if (c(itemViewType)) {
            return (View) ((Queue) this.n.get(itemViewType)).poll();
        }
        return null;
    }

    private void a(int i, View view) {
        int itemViewType = this.b.getItemViewType(i);
        if (c(itemViewType)) {
            ((Queue) this.n.get(itemViewType)).offer(view);
        }
    }

    private boolean c(int i) {
        return i < this.n.size();
    }

    private void a(View view, int i) {
        addViewInLayout(view, i, b(view), true);
        a(view);
    }

    private void a(View view) {
        int makeMeasureSpec;
        LayoutParams b = b(view);
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(this.F, getPaddingTop() + getPaddingBottom(), b.height);
        if (b.width > 0) {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(b.width, 1073741824);
        } else {
            makeMeasureSpec = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(makeMeasureSpec, childMeasureSpec);
    }

    private LayoutParams b(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            return new LayoutParams(-2, -1);
        }
        return layoutParams;
    }

    @SuppressLint({"WrongCall"})
    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (this.b != null) {
            int i5;
            invalidate();
            if (this.o) {
                i5 = this.c;
                b();
                removeAllViewsInLayout();
                this.d = i5;
                this.o = false;
            }
            if (this.t != null) {
                this.d = this.t.intValue();
                this.t = null;
            }
            if (this.a.computeScrollOffset()) {
                this.d = this.a.getCurrX();
            }
            if (this.d < 0) {
                this.d = 0;
                if (this.D.isFinished()) {
                    this.D.onAbsorb((int) d());
                }
                this.a.forceFinished(true);
                setCurrentScrollState(a.SCROLL_STATE_IDLE);
            } else if (this.d > this.u) {
                this.d = this.u;
                if (this.E.isFinished()) {
                    this.E.onAbsorb((int) d());
                }
                this.a.forceFinished(true);
                setCurrentScrollState(a.SCROLL_STATE_IDLE);
            }
            i5 = this.c - this.d;
            e(i5);
            d(i5);
            f(i5);
            this.c = this.d;
            if (e()) {
                onLayout(z, i, i2, i3, i4);
            } else if (!this.a.isFinished()) {
                postOnAnimation(this.K);
            } else if (this.C == a.SCROLL_STATE_FLING) {
                setCurrentScrollState(a.SCROLL_STATE_IDLE);
            }
        }
    }

    protected float getLeftFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == 0) {
            return 0.0f;
        }
        if (this.c < horizontalFadingEdgeLength) {
            return ((float) this.c) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    protected float getRightFadingEdgeStrength() {
        int horizontalFadingEdgeLength = getHorizontalFadingEdgeLength();
        if (this.c == this.u) {
            return 0.0f;
        }
        if (this.u - this.c < horizontalFadingEdgeLength) {
            return ((float) (this.u - this.c)) / ((float) horizontalFadingEdgeLength);
        }
        return 1.0f;
    }

    private float d() {
        if (VERSION.SDK_INT >= 14) {
            return c.a(this.a);
        }
        return 30.0f;
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.F = i2;
    }

    private boolean e() {
        if (!h(this.w)) {
            return false;
        }
        View rightmostChild = getRightmostChild();
        if (rightmostChild == null) {
            return false;
        }
        int i = this.u;
        this.u = ((rightmostChild.getRight() - getPaddingLeft()) + this.c) - getRenderWidth();
        if (this.u < 0) {
            this.u = 0;
        }
        if (this.u != i) {
            return true;
        }
        return false;
    }

    private void d(int i) {
        int right;
        int i2 = 0;
        View rightmostChild = getRightmostChild();
        if (rightmostChild != null) {
            right = rightmostChild.getRight();
        } else {
            right = 0;
        }
        a(right, i);
        rightmostChild = getLeftmostChild();
        if (rightmostChild != null) {
            i2 = rightmostChild.getLeft();
        }
        b(i2, i);
    }

    private void e(int i) {
        View leftmostChild = getLeftmostChild();
        while (leftmostChild != null && leftmostChild.getRight() + i <= 0) {
            int measuredWidth;
            int i2 = this.m;
            if (h(this.v)) {
                measuredWidth = leftmostChild.getMeasuredWidth();
            } else {
                measuredWidth = this.r + leftmostChild.getMeasuredWidth();
            }
            this.m = measuredWidth + i2;
            a(this.v, leftmostChild);
            removeViewInLayout(leftmostChild);
            this.v++;
            leftmostChild = getLeftmostChild();
        }
        View rightmostChild = getRightmostChild();
        while (rightmostChild != null && rightmostChild.getLeft() + i >= getWidth()) {
            a(this.w, rightmostChild);
            removeViewInLayout(rightmostChild);
            this.w--;
            rightmostChild = getRightmostChild();
        }
    }

    private void a(int i, int i2) {
        while ((i + i2) + this.r < getWidth() && this.w + 1 < this.b.getCount()) {
            this.w++;
            if (this.v < 0) {
                this.v = this.w;
            }
            View view = this.b.getView(this.w, b(this.w), this);
            a(view, -1);
            i += (this.w == 0 ? 0 : this.r) + view.getMeasuredWidth();
            h();
        }
    }

    private void b(int i, int i2) {
        while ((i + i2) - this.r > 0 && this.v >= 1) {
            this.v--;
            View view = this.b.getView(this.v, b(this.v), this);
            a(view, 0);
            i -= this.v == 0 ? view.getMeasuredWidth() : this.r + view.getMeasuredWidth();
            this.m -= i + i2 == 0 ? view.getMeasuredWidth() : this.r + view.getMeasuredWidth();
        }
    }

    private void f(int i) {
        int childCount = getChildCount();
        if (childCount > 0) {
            this.m += i;
            int i2 = this.m;
            for (int i3 = 0; i3 < childCount; i3++) {
                View childAt = getChildAt(i3);
                int paddingLeft = getPaddingLeft() + i2;
                int paddingTop = getPaddingTop();
                childAt.layout(paddingLeft, paddingTop, childAt.getMeasuredWidth() + paddingLeft, childAt.getMeasuredHeight() + paddingTop);
                i2 += childAt.getMeasuredWidth() + this.r;
            }
        }
    }

    private View getLeftmostChild() {
        return getChildAt(0);
    }

    private View getRightmostChild() {
        return getChildAt(getChildCount() - 1);
    }

    private View g(int i) {
        if (i >= this.v && i <= this.w) {
            getChildAt(i - this.v);
        }
        return null;
    }

    private int c(int i, int i2) {
        int childCount = getChildCount();
        for (int i3 = 0; i3 < childCount; i3++) {
            getChildAt(i3).getHitRect(this.p);
            if (this.p.contains(i, i2)) {
                return i3;
            }
        }
        return -1;
    }

    private boolean h(int i) {
        return i == this.b.getCount() + -1;
    }

    private int getRenderHeight() {
        return (getHeight() - getPaddingTop()) - getPaddingBottom();
    }

    private int getRenderWidth() {
        return (getWidth() - getPaddingLeft()) - getPaddingRight();
    }

    public void scrollTo(int i) {
        this.a.startScroll(this.d, 0, i - this.d, 0);
        setCurrentScrollState(a.SCROLL_STATE_FLING);
        requestLayout();
    }

    public int getFirstVisiblePosition() {
        return this.v;
    }

    public int getLastVisiblePosition() {
        return this.w;
    }

    private void a(Canvas canvas) {
        int save;
        int height;
        if (this.D != null && !this.D.isFinished() && i()) {
            save = canvas.save();
            height = getHeight();
            canvas.rotate(-90.0f, 0.0f, 0.0f);
            canvas.translate((float) ((-height) + getPaddingBottom()), 0.0f);
            this.D.setSize(getRenderHeight(), getRenderWidth());
            if (this.D.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        } else if (this.E != null && !this.E.isFinished() && i()) {
            save = canvas.save();
            height = getWidth();
            canvas.rotate(90.0f, 0.0f, 0.0f);
            canvas.translate((float) getPaddingTop(), (float) (-height));
            this.E.setSize(getRenderHeight(), getRenderWidth());
            if (this.E.draw(canvas)) {
                invalidate();
            }
            canvas.restoreToCount(save);
        }
    }

    private void b(Canvas canvas) {
        int childCount = getChildCount();
        Rect rect = this.p;
        this.p.top = getPaddingTop();
        this.p.bottom = this.p.top + getRenderHeight();
        for (int i = 0; i < childCount; i++) {
            if (i != childCount - 1 || !h(this.w)) {
                View childAt = getChildAt(i);
                rect.left = childAt.getRight();
                rect.right = childAt.getRight() + this.r;
                if (rect.left < getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                }
                if (rect.right > getWidth() - getPaddingRight()) {
                    rect.right = getWidth() - getPaddingRight();
                }
                a(canvas, rect);
                if (i == 0 && childAt.getLeft() > getPaddingLeft()) {
                    rect.left = getPaddingLeft();
                    rect.right = childAt.getLeft();
                    a(canvas, rect);
                }
            }
        }
    }

    private void a(Canvas canvas, Rect rect) {
        if (this.s != null) {
            this.s.setBounds(rect);
            this.s.draw(canvas);
        }
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        b(canvas);
    }

    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
        a(canvas);
    }

    protected void dispatchSetPressed(boolean z) {
    }

    protected boolean a(MotionEvent motionEvent, MotionEvent motionEvent2, float f, float f2) {
        this.a.fling(this.d, 0, (int) (-f), 0, 0, this.u, 0, 0);
        setCurrentScrollState(a.SCROLL_STATE_FLING);
        requestLayout();
        return true;
    }

    protected boolean a(MotionEvent motionEvent) {
        this.G = !this.a.isFinished();
        this.a.forceFinished(true);
        setCurrentScrollState(a.SCROLL_STATE_IDLE);
        f();
        if (!this.G) {
            int c = c((int) motionEvent.getX(), (int) motionEvent.getY());
            if (c >= 0) {
                this.q = getChildAt(c);
                if (this.q != null) {
                    this.q.setPressed(true);
                    refreshDrawableState();
                }
            }
        }
        return true;
    }

    private void f() {
        if (this.q != null) {
            this.q.setPressed(false);
            refreshDrawableState();
            this.q = null;
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 1) {
            if (this.a == null || this.a.isFinished()) {
                setCurrentScrollState(a.SCROLL_STATE_IDLE);
            }
            a(Boolean.valueOf(false));
            g();
        } else if (motionEvent.getAction() == 3) {
            f();
            g();
            a(Boolean.valueOf(false));
        }
        return super.onTouchEvent(motionEvent);
    }

    private void g() {
        if (this.D != null) {
            this.D.onRelease();
        }
        if (this.E != null) {
            this.E.onRelease();
        }
    }

    public void destroy() {
        this.D = null;
        this.E = null;
        this.l = null;
    }

    public void setRunningOutOfDataListener(e eVar, int i) {
        this.y = eVar;
        this.z = i;
    }

    private void h() {
        if (this.y != null && this.b != null && this.b.getCount() - (this.w + 1) < this.z && !this.A) {
            this.A = true;
            this.y.a();
        }
    }

    public void setOnClickListener(OnClickListener onClickListener) {
        this.I = onClickListener;
    }

    public void setOnScrollStateChangedListener(d dVar) {
        this.B = dVar;
    }

    private void setCurrentScrollState(a aVar) {
        if (!(this.C == aVar || this.B == null)) {
            this.B.a(aVar);
        }
        this.C = aVar;
    }

    private void i(int i) {
        if (this.D != null && this.E != null) {
            int i2 = this.c + i;
            if (this.a != null && !this.a.isFinished()) {
                return;
            }
            if (i2 < 0) {
                this.D.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.E.isFinished()) {
                    this.E.onRelease();
                }
            } else if (i2 > this.u) {
                this.E.onPull(((float) Math.abs(i)) / ((float) getRenderWidth()));
                if (!this.D.isFinished()) {
                    this.D.onRelease();
                }
            }
        }
    }

    private boolean i() {
        if (this.b == null || this.b.isEmpty() || this.u <= 0) {
            return false;
        }
        return true;
    }
}
