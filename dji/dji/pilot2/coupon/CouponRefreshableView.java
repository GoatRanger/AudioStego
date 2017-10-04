package dji.pilot2.coupon;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewConfiguration;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import dji.pilot.R;

public class CouponRefreshableView extends LinearLayout implements OnTouchListener {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = -20;
    public static final long f = 60000;
    public static final long g = 3600000;
    public static final long h = 86400000;
    public static final long i = 2592000000L;
    public static final long j = 31104000000L;
    private static final String k = "updated_at";
    private b l;
    private View m;
    private ListView n;
    private MarginLayoutParams o;
    private long p;
    private int q = -1;
    private int r;
    private int s = 3;
    private int t = this.s;
    private float u;
    private final int v;
    private boolean w;
    private boolean x;

    class a extends AsyncTask<Void, Integer, Integer> {
        final /* synthetic */ CouponRefreshableView a;

        a(CouponRefreshableView couponRefreshableView) {
            this.a = couponRefreshableView;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onPostExecute(Object obj) {
            a((Integer) obj);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((Integer[]) objArr);
        }

        protected Integer a(Void... voidArr) {
            if (this.a.o == null) {
                return Integer.valueOf(0);
            }
            int i = this.a.o.topMargin;
            while (true) {
                i -= 20;
                if (i <= this.a.r) {
                    return Integer.valueOf(this.a.r);
                }
                publishProgress(new Integer[]{Integer.valueOf(i)});
                this.a.a(10);
            }
        }

        protected void a(Integer... numArr) {
            if (numArr[0] == null) {
                numArr[0] = Integer.valueOf(0);
            }
            if (this.a.o != null) {
                this.a.o.topMargin = numArr[0].intValue();
                this.a.m.setLayoutParams(this.a.o);
            }
        }

        protected void a(Integer num) {
            if (this.a.o != null) {
                this.a.o.topMargin = num.intValue();
                this.a.m.setLayoutParams(this.a.o);
            }
            this.a.s = 3;
        }
    }

    public interface b {
        void a();
    }

    class c extends AsyncTask<Void, Integer, Void> {
        final /* synthetic */ CouponRefreshableView a;

        c(CouponRefreshableView couponRefreshableView) {
            this.a = couponRefreshableView;
        }

        protected /* synthetic */ Object doInBackground(Object[] objArr) {
            return a((Void[]) objArr);
        }

        protected /* synthetic */ void onProgressUpdate(Object[] objArr) {
            a((Integer[]) objArr);
        }

        protected Void a(Void... voidArr) {
            if (this.a.o != null) {
                int i = this.a.o.topMargin;
                while (true) {
                    i -= 20;
                    if (i <= 0) {
                        break;
                    }
                    publishProgress(new Integer[]{Integer.valueOf(i)});
                    this.a.a(10);
                }
                this.a.s = 2;
                publishProgress(new Integer[]{Integer.valueOf(0)});
                if (this.a.l != null) {
                    this.a.l.a();
                }
            }
            return null;
        }

        protected void a(Integer... numArr) {
            this.a.a();
            this.a.o.topMargin = numArr[0].intValue();
            this.a.m.setLayoutParams(this.a.o);
        }
    }

    public CouponRefreshableView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.v = ViewConfiguration.get(context).getScaledTouchSlop();
        setOrientation(1);
        if (!isInEditMode()) {
            this.m = LayoutInflater.from(context).inflate(R.layout.v2_coupon_refreshheader, null);
            ((AnimationDrawable) ((ImageView) this.m.findViewById(R.id.cj8)).getDrawable()).start();
            addView(this.m, 0);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (z && !this.w) {
            this.r = -this.m.getHeight();
            this.o = (MarginLayoutParams) this.m.getLayoutParams();
            this.o.topMargin = this.r;
            this.n = (ListView) getChildAt(1);
            this.n.setOnTouchListener(this);
            this.w = true;
        }
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        setIsAbleToPull(motionEvent);
        if (!this.x) {
            return false;
        }
        switch (motionEvent.getAction()) {
            case 0:
                this.u = motionEvent.getRawY();
                break;
            case 2:
                int rawY = (int) (motionEvent.getRawY() - this.u);
                if ((rawY > 0 || this.o.topMargin > this.r) && rawY >= this.v) {
                    if (this.s != 2) {
                        if (this.o.topMargin > 0) {
                            this.s = 1;
                        } else {
                            this.s = 0;
                        }
                        this.o.topMargin = (rawY / 2) + this.r;
                        this.m.setLayoutParams(this.o);
                        break;
                    }
                }
                return false;
                break;
            default:
                if (this.s != 1) {
                    if (this.s == 0) {
                        new a(this).execute(new Void[0]);
                        break;
                    }
                }
                new c(this).execute(new Void[0]);
                break;
                break;
        }
        if (this.s != 0 && this.s != 1) {
            return false;
        }
        a();
        this.n.setPressed(false);
        this.n.setFocusable(false);
        this.n.setFocusableInTouchMode(false);
        this.t = this.s;
        return true;
    }

    public void setOnRefreshListener(b bVar, int i) {
        this.l = bVar;
        this.q = i;
    }

    public void finishRefreshing() {
        this.s = 3;
        new a(this).execute(new Void[0]);
    }

    public void setStartRefreshing() {
        if (this.s != 2) {
            new c(this).execute(new Void[0]);
        }
    }

    private void setIsAbleToPull(MotionEvent motionEvent) {
        View childAt = this.n.getChildAt(0);
        if (childAt == null) {
            this.x = true;
        } else if (this.n.getFirstVisiblePosition() == 0 && childAt.getTop() == 0) {
            if (!this.x) {
                this.u = motionEvent.getRawY();
            }
            this.x = true;
        } else {
            if (this.o.topMargin != this.r) {
                this.o.topMargin = this.r;
                this.m.setLayoutParams(this.o);
            }
            this.x = false;
        }
    }

    public void showHeaderView() {
        if (this.o != null) {
            this.o.topMargin = 0;
            this.m.setLayoutParams(this.o);
        }
    }

    private void a() {
    }

    private void a(int i) {
        try {
            Thread.sleep((long) i);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
