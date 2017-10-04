package dji.pilot2.explore.fragment;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.MeasureSpec;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.LinearInterpolator;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.log.DJILogHelper;
import dji.pilot.R;
import dji.pilot.visual.a.d;

public class DJIDragListView extends ListView implements OnClickListener, OnScrollListener {
    private static final int v = 2;
    private View a;
    private TextView b;
    private TextView c;
    private ImageView d;
    private ProgressBar e;
    private int f;
    private int g;
    private View h;
    private View i;
    private TextView j;
    private View k;
    private Animation l;
    private Animation m;
    private int n = -1;
    private boolean o = false;
    private int p;
    private int q;
    private int r;
    private int s;
    private b t = b.LV_NORMAL;
    private a u = a.LV_NORMAL;
    private boolean w = false;
    private c x;
    private boolean y = true;
    private int z = 300;

    private enum a {
        LV_NORMAL,
        LV_LOADING,
        LV_OVER
    }

    private enum b {
        LV_NORMAL,
        LV_PULL_REFRESH,
        LV_RELEASE_REFRESH,
        LV_LOADING
    }

    public interface c {
        void a();

        void a(boolean z);

        void b();
    }

    public DJIDragListView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        initDragListView(context);
    }

    public void setOnRefreshListener(c cVar) {
        this.x = cVar;
    }

    public void initDragListView(Context context) {
        initHeadView(context, "1994.12.05");
        setOnScrollListener(this);
    }

    public void initHeadView(Context context, String str) {
        this.a = LayoutInflater.from(context).inflate(R.layout.v2_layout_draglist_widget, null);
        this.d = (ImageView) this.a.findViewById(R.id.cph);
        this.d.setMinimumWidth(60);
        this.e = (ProgressBar) this.a.findViewById(R.id.cpi);
        this.b = (TextView) this.a.findViewById(R.id.cpj);
        this.c = (TextView) this.a.findViewById(R.id.cpk);
        this.c.setText("最近更新:" + str);
        int b = dji.publics.e.a.b(context, 20.0f);
        this.f = this.a.getMeasuredWidth();
        this.g = b;
        DJILogHelper.getInstance().LOGI("bob", "mHeadViewWidth=" + this.f + " mHeadViewHeight=" + this.g);
        addHeaderView(this.a, null, false);
        this.a.setPadding(0, this.g * -1, 0, 0);
        a();
    }

    private void a() {
        this.l = new RotateAnimation(0.0f, DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle, 1, d.c, 1, d.c);
        this.l.setInterpolator(new LinearInterpolator());
        this.l.setDuration(250);
        this.l.setFillAfter(true);
        this.m = new RotateAnimation(DJIFlightControllerDataType.DJIVirtualStickYawControlMinAngle, 0.0f, 1, d.c, 1, d.c);
        this.m.setInterpolator(new LinearInterpolator());
        this.m.setDuration(250);
        this.m.setFillAfter(true);
    }

    private void a(View view) {
        LayoutParams layoutParams = view.getLayoutParams();
        if (layoutParams == null) {
            layoutParams = new LayoutParams(-1, -2);
        }
        int childMeasureSpec = ViewGroup.getChildMeasureSpec(0, 0, layoutParams.width);
        int i = layoutParams.height;
        if (i > 0) {
            i = MeasureSpec.makeMeasureSpec(i, 1073741824);
        } else {
            i = MeasureSpec.makeMeasureSpec(0, 0);
        }
        view.measure(childMeasureSpec, i);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                a(motionEvent);
                break;
            case 1:
                doActionUp(motionEvent);
                break;
            case 2:
                b(motionEvent);
                break;
        }
        if (this.y) {
            return super.onTouchEvent(motionEvent);
        }
        return true;
    }

    void a(MotionEvent motionEvent) {
        if (!this.o && this.n == 0) {
            this.p = (int) motionEvent.getY();
            this.r = (int) motionEvent.getX();
            this.o = true;
        }
    }

    void b(MotionEvent motionEvent) {
        this.q = (int) motionEvent.getY();
        if (!this.o && this.n == 0) {
            this.p = (int) motionEvent.getY();
            this.o = true;
        }
        if (this.o && this.t != b.LV_LOADING) {
            int i = (this.q - this.p) / 2;
            switch (this.t) {
                case LV_NORMAL:
                    if (i > 0) {
                        this.a.setPadding(0, i - this.g, 0, 0);
                        a(b.LV_PULL_REFRESH);
                        return;
                    }
                    return;
                case LV_PULL_REFRESH:
                    setSelection(0);
                    this.a.setPadding(0, i - this.g, 0, 0);
                    if (i < 0) {
                        this.y = false;
                        a(b.LV_NORMAL);
                        Log.e("jj", "isScroller=" + this.y);
                        return;
                    } else if (i > this.g) {
                        a(b.LV_RELEASE_REFRESH);
                        return;
                    } else {
                        return;
                    }
                case LV_RELEASE_REFRESH:
                    setSelection(0);
                    this.a.setPadding(0, i - this.g, 0, 0);
                    if (i >= 0 && i <= this.g) {
                        this.w = true;
                        a(b.LV_PULL_REFRESH);
                        return;
                    } else if (i < 0) {
                        a(b.LV_NORMAL);
                        return;
                    } else {
                        return;
                    }
                default:
                    return;
            }
        }
    }

    public void doActionUp(MotionEvent motionEvent) {
        this.s = (int) motionEvent.getX();
        int i = this.s - this.r;
        if (i > this.z) {
            this.x.a(true);
        }
        if (i < (-this.z)) {
            this.x.a(false);
        }
        this.o = false;
        this.y = true;
        this.w = false;
        if (this.t != b.LV_LOADING) {
            b bVar;
            switch (this.t) {
                case LV_NORMAL:
                    return;
                case LV_PULL_REFRESH:
                    this.a.setPadding(0, this.g * -1, 0, 0);
                    bVar = this.t;
                    a(b.LV_NORMAL);
                    return;
                case LV_RELEASE_REFRESH:
                    this.a.setPadding(0, 0, 0, 0);
                    bVar = this.t;
                    a(b.LV_LOADING);
                    b();
                    return;
                default:
                    return;
            }
        }
    }

    private void a(b bVar) {
        switch (bVar) {
            case LV_NORMAL:
                this.d.clearAnimation();
                this.d.setImageResource(R.drawable.active_progressing);
                break;
            case LV_PULL_REFRESH:
                this.e.setVisibility(8);
                this.d.setVisibility(0);
                this.b.setText("下拉可以刷新");
                this.d.clearAnimation();
                if (this.w) {
                    this.w = false;
                    this.d.clearAnimation();
                    this.d.startAnimation(this.m);
                    break;
                }
                break;
            case LV_RELEASE_REFRESH:
                this.e.setVisibility(8);
                this.d.setVisibility(0);
                this.b.setText("松开获取更多");
                this.d.clearAnimation();
                this.d.startAnimation(this.l);
                break;
            case LV_LOADING:
                Log.e("!!!!!!!!!!!", "convert to IListViewState.LVS_LOADING");
                this.e.setVisibility(0);
                this.e.startAnimation(this.l);
                this.l.setRepeatCount(1000);
                this.d.clearAnimation();
                this.d.setVisibility(8);
                this.b.setText("载入中...");
                break;
            default:
                return;
        }
        this.t = bVar;
    }

    private void b() {
        if (this.x != null) {
            this.x.a();
        }
    }

    public void onRefreshComplete() {
        this.a.setPadding(0, this.g * -1, 0, 0);
        b bVar = this.t;
        a(b.LV_NORMAL);
    }

    public void onLoadMoreComplete(boolean z) {
        if (z) {
            a(a.LV_OVER);
        } else {
            a(a.LV_NORMAL);
        }
    }

    private void a(a aVar) {
        switch (aVar) {
        }
        this.u = aVar;
    }

    public void onScrollStateChanged(AbsListView absListView, int i) {
    }

    public void onScroll(AbsListView absListView, int i, int i2, int i3) {
        this.n = i;
        if (i2 + i >= i3 && this.x != null) {
            a aVar = this.u;
            a aVar2 = this.u;
            if (aVar != a.LV_LOADING) {
                this.x.b();
                aVar = this.u;
                a(a.LV_LOADING);
            }
        }
    }

    public void onClick(View view) {
        if (this.x != null && this.u == a.LV_NORMAL) {
            a(a.LV_LOADING);
            this.x.b();
        }
    }
}
