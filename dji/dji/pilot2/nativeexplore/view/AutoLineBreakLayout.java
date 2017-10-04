package dji.pilot2.nativeexplore.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import java.util.ArrayList;
import java.util.List;

public class AutoLineBreakLayout extends ViewGroup {
    private List<Integer> a = null;
    private List<Integer> b = null;
    private int c;
    private int d;

    public AutoLineBreakLayout(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a();
    }

    public AutoLineBreakLayout(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    public AutoLineBreakLayout(Context context) {
        super(context);
        a();
    }

    private void a() {
        this.a = new ArrayList();
        this.b = new ArrayList();
        this.d = 0;
        this.c = 0;
    }

    public void setItemMargin(int i, int i2) {
        this.d = i2;
        this.c = i;
        requestLayout();
    }

    public int getHorizontalItemMargin() {
        return this.d;
    }

    public int getVerticalItemMargin() {
        return this.c;
    }

    protected void onMeasure(int i, int i2) {
        int i3;
        int makeMeasureSpec;
        int makeMeasureSpec2;
        int mode = MeasureSpec.getMode(i);
        int size = MeasureSpec.getSize(i);
        int mode2 = MeasureSpec.getMode(i2);
        int size2 = MeasureSpec.getSize(i2);
        this.a.clear();
        this.b.clear();
        this.a.add(Integer.valueOf(0));
        this.b.add(Integer.valueOf(0));
        for (i3 = 0; i3 < getChildCount(); i3++) {
            View childAt = getChildAt(i3);
            if (childAt.getVisibility() != 8) {
                LayoutParams layoutParams = childAt.getLayoutParams();
                if (layoutParams.width == -1) {
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, Integer.MIN_VALUE);
                } else if (layoutParams.width == -2) {
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(size, 0);
                } else {
                    makeMeasureSpec = MeasureSpec.makeMeasureSpec(layoutParams.width, 1073741824);
                }
                if (layoutParams.height == -1) {
                    makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(size2, Integer.MIN_VALUE);
                } else if (layoutParams.height == -2) {
                    makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(size2, 0);
                } else {
                    makeMeasureSpec2 = MeasureSpec.makeMeasureSpec(layoutParams.height, 1073741824);
                }
                childAt.measure(makeMeasureSpec, makeMeasureSpec2);
                makeMeasureSpec2 = ((Integer) this.a.get(this.a.size() - 1)).intValue();
                if ((((childAt.getMeasuredWidth() + makeMeasureSpec2) + (this.d * 2)) + getPaddingLeft()) + getPaddingRight() > size) {
                    this.a.set(this.a.size() - 1, Integer.valueOf((makeMeasureSpec2 + getPaddingLeft()) + getPaddingRight()));
                    this.a.add(Integer.valueOf(childAt.getMeasuredWidth() + (this.d * 2)));
                    this.b.add(Integer.valueOf(childAt.getMeasuredHeight() + (this.c * 2)));
                } else {
                    this.a.set(this.a.size() - 1, Integer.valueOf((childAt.getMeasuredWidth() + makeMeasureSpec2) + (this.d * 2)));
                    this.b.set(this.b.size() - 1, Integer.valueOf(Math.max(((Integer) this.b.get(this.b.size() - 1)).intValue(), childAt.getMeasuredHeight() + (this.c * 2))));
                    if (i3 == getChildCount() - 1) {
                        this.a.set(this.a.size() - 1, Integer.valueOf((makeMeasureSpec2 + getPaddingLeft()) + getPaddingRight()));
                    }
                }
            }
        }
        if (mode == 0) {
            i3 = size;
            for (Integer intValue : this.a) {
                i3 = Math.min(i3, intValue.intValue());
            }
        } else {
            i3 = size;
        }
        size = 0;
        for (makeMeasureSpec2 = 0; makeMeasureSpec2 < this.b.size(); makeMeasureSpec2++) {
            size += ((Integer) this.b.get(makeMeasureSpec2)).intValue();
        }
        makeMeasureSpec = (getPaddingTop() + getPaddingBottom()) + size;
        if (mode2 != 0) {
            makeMeasureSpec = size2;
        }
        setMeasuredDimension(i3, makeMeasureSpec);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int i5 = i3 - i;
        int i6 = i4 - i2;
        int i7 = 0;
        int i8 = 0;
        while (i8 < getChildCount()) {
            int measuredWidth;
            View childAt = getChildAt(i8);
            if (childAt.getVisibility() != 8) {
                int i9;
                measuredWidth = childAt.getMeasuredWidth();
                childAt.getMeasuredHeight();
                LayoutParams layoutParams = childAt.getLayoutParams();
                if (((measuredWidth + paddingLeft) + (this.d * 2)) + getPaddingRight() > i5) {
                    paddingTop += ((Integer) this.b.get(i7)).intValue();
                    i7++;
                    paddingLeft = getPaddingLeft();
                }
                if (layoutParams.width == -1) {
                    measuredWidth = i5;
                } else if (layoutParams.width == -2) {
                    measuredWidth = childAt.getMeasuredWidth();
                } else {
                    measuredWidth = layoutParams.width;
                }
                if (layoutParams.height == -1) {
                    i9 = i6;
                } else if (layoutParams.height == -2) {
                    i9 = childAt.getMeasuredHeight();
                } else {
                    i9 = layoutParams.height;
                }
                childAt.layout(this.d + paddingLeft, this.c + paddingTop, (this.d + paddingLeft) + measuredWidth, i9 + (this.c + paddingTop));
                measuredWidth = (measuredWidth + (this.d * 2)) + paddingLeft;
                paddingLeft = paddingTop;
                paddingTop = i7;
            } else {
                measuredWidth = paddingLeft;
                paddingLeft = paddingTop;
                paddingTop = i7;
            }
            i8++;
            i7 = paddingTop;
            paddingTop = paddingLeft;
            paddingLeft = measuredWidth;
        }
    }
}
