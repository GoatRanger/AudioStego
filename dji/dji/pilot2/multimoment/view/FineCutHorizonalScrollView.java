package dji.pilot2.multimoment.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.facebook.login.widget.ToolTipPopup;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot2.multimoment.activity.DJIMultiMomentFineActivity;
import dji.pilot2.multimoment.adapter.b;
import dji.pilot2.multimoment.adapter.b.c;
import dji.pilot2.utils.p;

public class FineCutHorizonalScrollView extends HorizontalScrollView implements OnClickListener {
    public static final int a = 12;
    public int b;
    protected a c;
    protected int d = 0;
    private int e;
    private int f;
    private Context g;
    private b h;
    private RelativeLayout i;
    private RelativeLayout j;
    private dji.pilot2.widget.b k;
    private int l;
    private int m;
    private int n;
    private int o;
    private int p;
    private int q;
    private int r = 0;

    public interface a {
        void a(int i);

        void a(int i, int i2, int i3, int i4);

        void b(int i);

        void c(int i);

        void d(int i);
    }

    public void onDestroy() {
        this.h.b();
        this.i.removeAllViews();
    }

    public long GetTotalLength() {
        if (this.r != 0) {
            return (long) this.r;
        }
        return (long) (this.e * this.h.c());
    }

    public int getIsSingleMoment() {
        this.d = ((DJIMultiMomentFineActivity) this.g).j();
        return this.d;
    }

    public FineCutHorizonalScrollView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        DisplayMetrics displayMetrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(displayMetrics);
        this.b = displayMetrics.widthPixels;
        this.g = context;
        this.r = 0;
    }

    public void fling(int i) {
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        this.i = (RelativeLayout) getChildAt(0);
    }

    public void updateChildView(int i, Bitmap bitmap) {
        if (this.i.getChildCount() > i + 1) {
            View childAt = this.i.getChildAt(i + 1);
            if (childAt != null) {
                c cVar = (c) childAt.getTag();
                if (!(cVar == null || cVar.a == null)) {
                    cVar.a.setImageBitmap(bitmap);
                }
                invalidate();
            }
        }
    }

    public void initDatas(b bVar, double d) {
        this.h = bVar;
        this.d = ((DJIMultiMomentFineActivity) this.g).j();
        this.i = (RelativeLayout) getChildAt(0);
        if (this.e == 0 && this.f == 0) {
            this.f = this.h.d;
            this.e = this.h.c;
        }
        speedChange(d);
    }

    public void speedChange(double d) {
        int c = this.h.c();
        if (this.d == 1) {
            initFirstScreenChildren(c, 0);
            this.r = this.e * this.h.c();
        } else if (d != 1.0d) {
            long l = ((DJIMultiMomentFineActivity) this.g).l();
            double d2 = this.h.d();
            c = (int) (((double) ((DJIMultiMomentFineActivity) this.g).h()) * d2);
            DJILogHelper.getInstance().LOGI("bob", "rectWidth = " + d2 + " segWidth =" + c + " segSelectD=" + l);
            initFirstScreenChildren((c / this.h.c) + 1, c);
            this.r = c;
        } else {
            initFirstScreenChildren(c, 0);
            this.r = this.e * this.h.c();
        }
        if (this.d == 1 || this.d == 2) {
            a();
        } else {
            b();
        }
        this.q = (int) ((GetTotalLength() * ((long) ((DJIMultiMomentFineActivity) this.g).k())) / ((DJIMultiMomentFineActivity) this.g).h());
        if (this.d == 2) {
            this.p = (int) ((GetTotalLength() * 1000) / ((DJIMultiMomentFineActivity) this.g).h());
        } else {
            this.p = (int) ((GetTotalLength() * ToolTipPopup.a) / ((DJIMultiMomentFineActivity) this.g).h());
        }
    }

    public void initFirstScreenChildren(int i, int i2) {
        int i3 = 0;
        this.i = (RelativeLayout) getChildAt(0);
        this.i.removeAllViews();
        View imageView = new ImageView(this.g);
        imageView.setBackgroundColor(-16777216);
        imageView.setLayoutParams(new LayoutParams(this.h.e, this.f));
        this.i.addView(imageView);
        double c = ((double) this.h.c()) / ((double) i);
        int i4 = this.h.e;
        for (int i5 = 0; i5 < i && (i2 == 0 || i3 < i2); i5++) {
            View a = this.h.a((int) (((double) i5) * c), null, this.i);
            int i6 = this.e;
            if (i2 != 0) {
                if (i2 - i3 <= i6) {
                    i6 = i2 - i3;
                }
                i3 += i6;
            }
            ViewGroup.LayoutParams layoutParams = new LayoutParams(i6, -1);
            layoutParams.leftMargin = i4;
            a.setLayoutParams(layoutParams);
            this.i.addView(a, layoutParams);
            i4 += layoutParams.width;
        }
        imageView = new ImageView(this.g);
        imageView.setBackgroundColor(-16777216);
        ViewGroup.LayoutParams layoutParams2 = new LayoutParams(this.h.f, this.f);
        layoutParams2.leftMargin = i4;
        imageView.setLayoutParams(layoutParams2);
        this.i.addView(imageView);
        this.i.invalidate();
    }

    public int getRectWidth() {
        if (this.d == 1 || this.d == 2) {
            return this.k.b();
        }
        return this.j.getWidth();
    }

    public int getRectX() {
        if (this.d == 1 || this.d == 2) {
            return this.k.c();
        }
        return (int) this.j.getX();
    }

    protected void a() {
        long l = ((DJIMultiMomentFineActivity) this.g).l();
        long GetTotalLength = ((long) this.h.e) + ((GetTotalLength() * ((long) ((DJIMultiMomentFineActivity) this.g).i())) / ((DJIMultiMomentFineActivity) this.g).h());
        long GetTotalLength2 = (long) ((int) ((l * GetTotalLength()) / ((DJIMultiMomentFineActivity) this.g).h()));
        this.k = new dji.pilot2.widget.b(this.g, this.i, 0);
        this.k.a((int) GetTotalLength, (int) GetTotalLength2);
        this.k.a(p.e((int) ((l + 500) / 1000)));
        this.i.addView(this.k.a());
        this.k.a(true);
    }

    protected void b() {
        long l = ((DJIMultiMomentFineActivity) this.g).l();
        int scrollX = this.h.e + getScrollX();
        int GetTotalLength = (int) ((GetTotalLength() * l) / ((DJIMultiMomentFineActivity) this.g).h());
        this.j = (RelativeLayout) LayoutInflater.from(this.g).inflate(R.layout.v2_multimoment_fine_horizonal_rect, this.i, false);
        ((TextView) this.j.findViewById(R.id.ctv)).setText(p.e((int) (l / 1000)));
        ViewGroup.LayoutParams layoutParams = new LayoutParams(GetTotalLength, this.f);
        layoutParams.leftMargin = scrollX;
        this.j.setLayoutParams(layoutParams);
        this.i.addView(this.j);
        this.j.setPressed(true);
    }

    protected void c() {
        long l = ((DJIMultiMomentFineActivity) this.g).l();
        DJILogHelper.getInstance().LOGI("bob", "ACTION_MOVE updateSingleRectTextView segSelectD= " + l);
        if (this.d == 1 || this.d == 2) {
            this.k.a(p.e((int) ((l + 500) / 1000)));
        } else {
            ((TextView) this.j.findViewById(R.id.crd)).setText(p.e((int) ((l + 500) / 1000)));
        }
    }

    public int isTouchInSeg(int i, int i2, View view) {
        Rect rect = new Rect(0, 0, 0, 0);
        view.getHitRect(rect);
        int i3 = rect.right - rect.left;
        int i4 = i3 / 5 > 100 ? 60 : i3 / 5;
        Rect rect2 = new Rect(rect.left, rect.top, rect.left + i4, rect.bottom);
        Rect rect3 = new Rect((rect.left + i3) - i4, rect.top, rect.right, rect.bottom);
        Rect rect4 = new Rect(rect.left + i4, rect.top, (i3 + rect.left) - i4, rect.bottom);
        i4 = getScrollX() + i;
        if (rect2.contains(i4, i2)) {
            return 1;
        }
        if (rect3.contains(i4, i2)) {
            return 3;
        }
        return rect4.contains(i4, i2) ? 2 : 0;
    }

    protected int a(int i, int i2) {
        long j;
        Object obj;
        long j2 = 1000;
        Object obj2 = null;
        ((DJIMultiMomentFineActivity) this.g).l();
        long h = (((DJIMultiMomentFineActivity) this.g).h() * ((long) i)) / GetTotalLength();
        int k = ((DJIMultiMomentFineActivity) this.g).k();
        if (h > ((long) k)) {
            j = (long) k;
            obj2 = 1;
        } else {
            j = h;
        }
        if (j < 1000) {
            obj = 1;
        } else {
            j2 = j;
            obj = obj2;
        }
        ((DJIMultiMomentFineActivity) this.g).a(j2, (long) ((int) ((((long) (i2 - this.h.e)) * ((DJIMultiMomentFineActivity) this.g).h()) / GetTotalLength())));
        if (obj == 1) {
            return (int) ((j2 * GetTotalLength()) / ((DJIMultiMomentFineActivity) this.g).h());
        }
        return i;
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        int i = 0;
        switch (motionEvent.getAction()) {
            case 0:
                if (this.d == 1 || this.d == 2) {
                    i = (int) motionEvent.getX();
                    int y = (int) motionEvent.getY();
                    this.l = isTouchInSeg(i, y, this.k.a());
                    if (this.l == 1 || this.l == 3) {
                        this.m = 1;
                        this.n = i;
                        this.o = y;
                    }
                }
                if (this.c != null) {
                    this.c.b(getScrollX());
                    break;
                }
                break;
            case 1:
                if (this.d == 1 || this.d == 2) {
                    this.m = 0;
                    if (this.l == 2) {
                        this.l = 0;
                    } else {
                        new Handler(Looper.getMainLooper()).postDelayed(new Runnable(this) {
                            final /* synthetic */ FineCutHorizonalScrollView a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.smoothScrollTo(this.a.getRectX() - this.a.h.e, 0);
                            }
                        }, 50);
                    }
                }
                if (this.c != null) {
                    this.c.d(getScrollX());
                    break;
                }
                break;
            case 2:
                int x = (int) motionEvent.getX();
                int y2 = (int) motionEvent.getY();
                if (this.m != 1 && (this.d == 1 || this.d == 2)) {
                    this.l = isTouchInSeg(x, y2, this.k.a());
                    if (this.l == 1 || this.l == 3) {
                        DJILogHelper.getInstance().LOGI("bob", "ACTION_MOVE not down");
                        this.m = 1;
                        this.n = x;
                        this.o = y2;
                    }
                }
                if (this.d != 1 && this.d != 2) {
                    this.j.getLeft();
                } else if (this.m != 0) {
                    y2 = this.k.b();
                    int c = this.k.c();
                    if (this.l == 3) {
                        y2 += x - this.n;
                        if (y2 > this.p && ((long) (c + y2)) <= GetTotalLength() + ((long) this.h.e) && y2 <= this.q) {
                            this.k.a(this.k.c(), y2);
                            a(y2, c);
                            c();
                        }
                        if (this.c != null) {
                            if (y2 >= this.p) {
                                i = 2;
                            }
                            if (y2 > this.q) {
                                i = 1;
                            }
                            if (i != 2) {
                                this.c.c(i);
                            }
                        }
                    } else if (this.l == 1) {
                        y2 += this.n - x;
                        DJILogHelper.getInstance().LOGI("bob", "ACTION_MOVE mDragLeftOrRight == 1 dX = " + y2 + " mMinLen = " + this.p + " mMaxLen=" + this.q);
                        if (y2 > this.p && y2 <= this.q) {
                            i = (x - this.n) + c;
                            DJILogHelper.getInstance().LOGI("bob", "ACTION_MOVE xP=" + i + " mAdapter.mAddWidth=" + this.h.e);
                            if (i >= this.h.e) {
                                this.k.a(i, y2);
                                a(y2, i);
                                c();
                            }
                        } else if (this.c != null) {
                            if (y2 >= this.p) {
                                i = 2;
                            }
                            if (y2 > this.q) {
                                i = 1;
                            }
                            if (i != 2) {
                                this.c.c(i);
                            }
                        }
                    }
                    this.n = x;
                }
                if (this.c != null) {
                    this.c.a(getScrollX());
                    break;
                }
                break;
        }
        if (this.m != 0) {
            return true;
        }
        return super.onTouchEvent(motionEvent);
    }

    public void onClick(View view) {
    }

    protected void onScrollChanged(int i, int i2, int i3, int i4) {
        if (this.d == 0) {
            ViewGroup.LayoutParams layoutParams = new LayoutParams(this.j.getWidth(), this.j.getHeight());
            layoutParams.leftMargin = this.h.e + i;
            this.j.setLayoutParams(layoutParams);
        }
        if (this.c != null) {
            this.c.a(i, i2, i3, i4);
        }
    }

    public int getmChildWidth() {
        return this.e;
    }

    public void setMoveCallBack(a aVar) {
        this.c = aVar;
    }
}
