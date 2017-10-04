package dji.publics.widget.djiviewpager;

import android.annotation.SuppressLint;
import android.content.Context;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;

@SuppressLint({"NewApi"})
public class IndicatorBar extends LinearLayout {
    private static final int a = 30;
    private static final int b = 0;
    private Context c;
    private int d;
    private int e;
    private int f;
    private int g;
    private int h;
    private boolean i;
    private ViewPager j;

    public IndicatorBar(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        a(context);
    }

    public IndicatorBar(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(context);
    }

    public IndicatorBar(Context context) {
        super(context);
        a(context);
    }

    private void a(Context context) {
        this.c = context;
        this.d = 30;
        this.e = 0;
        this.i = true;
        this.h = getResources().getColor(17170435);
        this.g = getResources().getColor(17170444);
        a();
    }

    public void setCount(int i) {
        while (getChildCount() > i) {
            removeViewAt(getChildCount() - 1);
        }
        while (getChildCount() < i) {
            addView(new View(this.c), new LayoutParams(this.d, this.d));
        }
        a();
    }

    public void setViewPager(ViewPager viewPager) {
        this.j = viewPager;
        this.j.addOnPageChangeListener(new OnPageChangeListener(this) {
            final /* synthetic */ IndicatorBar a;

            {
                this.a = r1;
            }

            public void onPageSelected(int i) {
                this.a.setSelectedIndex(i);
            }

            public void onPageScrolled(int i, float f, int i2) {
            }

            public void onPageScrollStateChanged(int i) {
            }
        });
    }

    public void setSelectedIndex(int i) {
        if (this.f != i) {
            View childAt = getChildAt(this.f);
            View childAt2 = getChildAt(i);
            this.f = i;
            if (childAt != null) {
                if (this.i) {
                    childAt.setBackgroundColor(this.h);
                } else {
                    childAt.setBackgroundResource(this.h);
                }
            }
            if (childAt2 == null) {
                return;
            }
            if (this.i) {
                childAt2.setBackgroundColor(this.g);
            } else {
                childAt2.setBackgroundResource(this.g);
            }
        }
    }

    public void setIndicatorResource(int i, int i2) {
        this.i = false;
        this.g = i;
        this.h = i2;
        b();
    }

    public void setIndicatorColor(int i, int i2) {
        this.i = true;
        this.g = i;
        this.h = i2;
        b();
    }

    public void setItemSize(int i) {
        this.d = i;
        a();
    }

    public void setItemDisatance(int i) {
        this.e = i;
        a();
    }

    private void a() {
        for (int i = 0; i < getChildCount(); i++) {
            LayoutParams layoutParams = (LayoutParams) getChildAt(i).getLayoutParams();
            if (i == 0) {
                layoutParams.setMargins(0, 0, 0, 0);
            } else if (getOrientation() == 0) {
                layoutParams.setMargins(this.e, 0, 0, 0);
            } else {
                layoutParams.setMargins(0, this.e, 0, 0);
            }
            layoutParams.width = this.d;
            layoutParams.height = this.d;
            getChildAt(i).setLayoutParams(layoutParams);
        }
        b();
    }

    private void b() {
        for (int i = 0; i < getChildCount(); i++) {
            if (i == this.f) {
                if (this.g != 0) {
                    if (this.i) {
                        getChildAt(i).setBackgroundColor(this.g);
                    } else {
                        getChildAt(i).setBackgroundResource(this.g);
                    }
                }
            } else if (this.h != 0) {
                if (this.i) {
                    getChildAt(i).setBackgroundColor(this.h);
                } else {
                    getChildAt(i).setBackgroundResource(this.h);
                }
            }
        }
    }
}
