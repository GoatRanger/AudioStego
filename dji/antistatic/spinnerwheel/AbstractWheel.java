package antistatic.spinnerwheel;

import android.content.Context;
import android.content.res.TypedArray;
import android.database.DataSetObserver;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.BaseSavedState;
import android.view.animation.Interpolator;
import android.widget.LinearLayout;
import antistatic.spinnerwheel.a.e;
import antistatic.spinnerwheel.g.a;
import dji.frame.widget.R;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractWheel extends View {
    private static int m = -1;
    private static final int o = 4;
    private static final boolean p = false;
    protected int a = 0;
    protected int b;
    protected boolean c;
    protected boolean d;
    protected g e;
    protected boolean f;
    protected int g;
    protected LinearLayout h;
    protected int i;
    protected e j;
    protected int k;
    protected int l;
    private final String n;
    private f q = new f(this);
    private List<b> r = new LinkedList();
    private List<d> s = new LinkedList();
    private List<c> t = new LinkedList();
    private DataSetObserver u;

    static class SavedState extends BaseSavedState {
        public static final Creator<SavedState> CREATOR = new Creator<SavedState>() {
            public /* synthetic */ Object createFromParcel(Parcel parcel) {
                return a(parcel);
            }

            public /* synthetic */ Object[] newArray(int i) {
                return a(i);
            }

            public SavedState a(Parcel parcel) {
                return new SavedState(parcel);
            }

            public SavedState[] a(int i) {
                return new SavedState[i];
            }
        };
        int a;

        SavedState(Parcelable parcelable) {
            super(parcelable);
        }

        private SavedState(Parcel parcel) {
            super(parcel);
            this.a = parcel.readInt();
        }

        public void writeToParcel(Parcel parcel, int i) {
            super.writeToParcel(parcel, i);
            parcel.writeInt(this.a);
        }
    }

    protected abstract float a(MotionEvent motionEvent);

    protected abstract g a(a aVar);

    protected abstract void a(int i, int i2);

    protected abstract void e();

    protected abstract void f();

    protected abstract int getBaseDimension();

    protected abstract int getItemDimension();

    public AbstractWheel(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet);
        StringBuilder append = new StringBuilder().append(AbstractWheel.class.getName()).append(" #");
        int i2 = m + 1;
        m = i2;
        this.n = append.append(i2).toString();
        if (!isInEditMode()) {
            a(attributeSet, i);
            a(context);
        }
    }

    protected void a(AttributeSet attributeSet, int i) {
        if (!isInEditMode()) {
            TypedArray obtainStyledAttributes = getContext().obtainStyledAttributes(attributeSet, R.styleable.AbstractWheelView, i, 0);
            this.b = obtainStyledAttributes.getInt(R.styleable.AbstractWheelView_visibleItems, 4);
            this.c = obtainStyledAttributes.getBoolean(R.styleable.AbstractWheelView_isAllVisible, false);
            this.d = obtainStyledAttributes.getBoolean(R.styleable.AbstractWheelView_isCyclic, false);
            obtainStyledAttributes.recycle();
        }
    }

    protected void a(Context context) {
        this.u = new DataSetObserver(this) {
            final /* synthetic */ AbstractWheel a;

            {
                this.a = r1;
            }

            public void onChanged() {
                this.a.invalidateItemsLayout(false);
            }

            public void onInvalidated() {
                this.a.invalidateItemsLayout(true);
            }
        };
        this.e = a(new a(this) {
            final /* synthetic */ AbstractWheel a;

            {
                this.a = r1;
            }

            public void a() {
                this.a.f = true;
                this.a.g();
                this.a.a();
            }

            public void b() {
                this.a.b();
            }

            public void c() {
                if (!this.a.f) {
                    this.a.c();
                }
            }

            public void a(int i) {
                this.a.c(i);
                int baseDimension = this.a.getBaseDimension();
                if (this.a.g > baseDimension) {
                    this.a.g = baseDimension;
                    this.a.e.c();
                } else if (this.a.g < (-baseDimension)) {
                    this.a.g = -baseDimension;
                    this.a.e.c();
                }
            }

            public void d() {
                if (this.a.f) {
                    this.a.h();
                    this.a.f = false;
                    this.a.d();
                }
                this.a.g = 0;
                this.a.invalidate();
            }

            public void e() {
                if (Math.abs(this.a.g) > 1) {
                    this.a.e.b(this.a.g, 0);
                }
            }
        });
    }

    public Parcelable onSaveInstanceState() {
        Parcelable savedState = new SavedState(super.onSaveInstanceState());
        savedState.a = getCurrentItem();
        return savedState;
    }

    public void onRestoreInstanceState(Parcelable parcelable) {
        if (parcelable instanceof SavedState) {
            SavedState savedState = (SavedState) parcelable;
            super.onRestoreInstanceState(savedState.getSuperState());
            this.a = savedState.a;
            postDelayed(new Runnable(this) {
                final /* synthetic */ AbstractWheel a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.invalidateItemsLayout(false);
                }
            }, 100);
            return;
        }
        super.onRestoreInstanceState(parcelable);
    }

    protected void a() {
    }

    protected void b() {
    }

    protected void c() {
    }

    protected void d() {
    }

    public void stopScrolling() {
        this.e.c();
    }

    public void setInterpolator(Interpolator interpolator) {
        this.e.a(interpolator);
    }

    public void scroll(int i, int i2) {
        int itemDimension = (getItemDimension() * i) - this.g;
        b();
        this.e.b(itemDimension, i2);
    }

    private void c(int i) {
        this.g += i;
        int itemDimension = getItemDimension();
        int i2 = this.g / itemDimension;
        int i3 = this.a - i2;
        int h = this.j.h();
        int i4 = this.g % itemDimension;
        if (Math.abs(i4) <= itemDimension / 2) {
            i4 = 0;
        }
        if (this.d && h > 0) {
            if (i4 > 0) {
                i4 = i3 - 1;
                i3 = i2 + 1;
            } else if (i4 < 0) {
                i4 = i3 + 1;
                i3 = i2 - 1;
            } else {
                i4 = i3;
                i3 = i2;
            }
            while (i4 < 0) {
                i4 += h;
            }
            i4 %= h;
        } else if (i3 < 0) {
            i3 = this.a;
            i4 = 0;
        } else if (i3 >= h) {
            i3 = (this.a - h) + 1;
            i4 = h - 1;
        } else if (i3 > 0 && i4 > 0) {
            i4 = i3 - 1;
            i3 = i2 + 1;
        } else if (i3 >= h - 1 || i4 >= 0) {
            i4 = i3;
            i3 = i2;
        } else {
            i4 = i3 + 1;
            i3 = i2 - 1;
        }
        i2 = this.g;
        if (i4 != this.a) {
            setCurrentItem(i4, false);
        } else {
            invalidate();
        }
        i4 = getBaseDimension();
        this.g = i2 - (i3 * itemDimension);
        if (this.g > i4) {
            this.g = i4 + (this.g % i4);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        if (z) {
            int i5 = i3 - i;
            int i6 = i4 - i2;
            f();
            if (!(this.l == i5 && this.k == i6)) {
                a(getMeasuredWidth(), getMeasuredHeight());
            }
            this.l = i5;
            this.k = i6;
        }
    }

    public void invalidateItemsLayout(boolean z) {
        if (z) {
            this.q.c();
            if (this.h != null) {
                this.h.removeAllViews();
            }
            this.g = 0;
        } else if (this.h != null) {
            this.q.a(this.h, this.i, new a());
        }
        invalidate();
    }

    public int getVisibleItems() {
        return this.b;
    }

    public void setVisibleItems(int i) {
        this.b = i;
    }

    public void setAllItemsVisible(boolean z) {
        this.c = z;
        invalidateItemsLayout(false);
    }

    public e getViewAdapter() {
        return this.j;
    }

    public void setViewAdapter(e eVar) {
        if (this.j != null) {
            this.j.b(this.u);
        }
        this.j = eVar;
        if (this.j != null) {
            this.j.a(this.u);
        }
        invalidateItemsLayout(true);
    }

    public int getCurrentItem() {
        return this.a;
    }

    public void setCurrentItem(int i, boolean z) {
        if (this.j != null && this.j.h() != 0) {
            int h = this.j.h();
            if (i < 0 || i >= h) {
                if (this.d) {
                    while (i < 0) {
                        i += h;
                    }
                    i %= h;
                } else {
                    return;
                }
            }
            if (i == this.a) {
                return;
            }
            if (z) {
                int i2 = i - this.a;
                if (this.d) {
                    h = (h + Math.min(i, this.a)) - Math.max(i, this.a);
                    if (h < Math.abs(i2)) {
                        if (i2 >= 0) {
                            h = -h;
                        }
                        scroll(h, 0);
                        return;
                    }
                }
                h = i2;
                scroll(h, 0);
                return;
            }
            this.g = 0;
            h = this.a;
            this.a = i;
            b(h, this.a);
            invalidate();
        }
    }

    public void setCurrentItem(int i) {
        setCurrentItem(i, false);
    }

    public boolean isCyclic() {
        return this.d;
    }

    public void setCyclic(boolean z) {
        this.d = z;
        invalidateItemsLayout(false);
    }

    public void addChangingListener(b bVar) {
        this.r.add(bVar);
    }

    public void removeChangingListener(b bVar) {
        this.r.remove(bVar);
    }

    protected void b(int i, int i2) {
        for (b a : this.r) {
            a.a(this, i, i2);
        }
    }

    public void addScrollingListener(d dVar) {
        this.s.add(dVar);
    }

    public void removeScrollingListener(d dVar) {
        this.s.remove(dVar);
    }

    protected void g() {
        for (d a : this.s) {
            a.a(this);
        }
    }

    protected void h() {
        for (d b : this.s) {
            b.b(this);
        }
    }

    public void addClickingListener(c cVar) {
        this.t.add(cVar);
    }

    public void removeClickingListener(c cVar) {
        this.t.remove(cVar);
    }

    protected void a(int i) {
        for (c a : this.t) {
            a.a(this, i);
        }
    }

    protected boolean i() {
        int a;
        boolean z;
        a itemsRange = getItemsRange();
        if (this.h != null) {
            a = this.q.a(this.h, this.i, itemsRange);
            z = this.i != a;
            this.i = a;
        } else {
            e();
            z = true;
        }
        if (!z) {
            if (this.i == itemsRange.a() && this.h.getChildCount() == itemsRange.c()) {
                z = false;
            } else {
                z = true;
            }
        }
        if (this.i <= itemsRange.a() || this.i > itemsRange.b()) {
            this.i = itemsRange.a();
        } else {
            a = this.i - 1;
            while (a >= itemsRange.a() && a(a, true)) {
                this.i = a;
                a--;
            }
        }
        a = this.i;
        for (int childCount = this.h.getChildCount(); childCount < itemsRange.c(); childCount++) {
            if (!a(this.i + childCount, false) && this.h.getChildCount() == 0) {
                a++;
            }
        }
        this.i = a;
        return z;
    }

    private a getItemsRange() {
        int baseDimension;
        int itemDimension;
        if (this.c) {
            baseDimension = getBaseDimension();
            itemDimension = getItemDimension();
            if (itemDimension != 0) {
                this.b = (baseDimension / itemDimension) + 1;
            }
        }
        itemDimension = this.a - (this.b / 2);
        baseDimension = (itemDimension + this.b) - (this.b % 2 == 0 ? 0 : 1);
        if (this.g != 0) {
            if (this.g > 0) {
                itemDimension--;
            } else {
                baseDimension++;
            }
        }
        if (!isCyclic()) {
            if (itemDimension < 0) {
                itemDimension = 0;
            }
            if (this.j == null) {
                baseDimension = 0;
            } else if (baseDimension > this.j.h()) {
                baseDimension = this.j.h();
            }
        }
        return new a(itemDimension, (baseDimension - itemDimension) + 1);
    }

    protected boolean b(int i) {
        return this.j != null && this.j.h() > 0 && (this.d || (i >= 0 && i < this.j.h()));
    }

    private boolean a(int i, boolean z) {
        View d = d(i);
        if (d == null) {
            return false;
        }
        if (z) {
            this.h.addView(d, 0);
        } else {
            this.h.addView(d);
        }
        return true;
    }

    private View d(int i) {
        if (this.j == null || this.j.h() == 0) {
            return null;
        }
        int h = this.j.h();
        if (!b(i)) {
            return this.j.a(this.q.b(), this.h);
        }
        while (i < 0) {
            i += h;
        }
        return this.j.a(i % h, this.q.a(), this.h);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!isEnabled() || getViewAdapter() == null) {
            return true;
        }
        switch (motionEvent.getAction()) {
            case 0:
            case 2:
                if (getParent() != null) {
                    getParent().requestDisallowInterceptTouchEvent(true);
                    break;
                }
                break;
            case 1:
                if (!this.f) {
                    int a = ((int) a(motionEvent)) - (getBaseDimension() / 2);
                    if (a > 0) {
                        a += getItemDimension() / 2;
                    } else {
                        a -= getItemDimension() / 2;
                    }
                    a /= getItemDimension();
                    if (a != 0 && b(this.a + a)) {
                        a(a + this.a);
                        break;
                    }
                }
                break;
        }
        return this.e.b(motionEvent);
    }
}
