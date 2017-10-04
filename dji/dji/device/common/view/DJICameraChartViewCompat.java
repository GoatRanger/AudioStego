package dji.device.common.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Point;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.activity.DJIPreviewActivityLongan;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetPushChartInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetPushChart;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;

public class DJICameraChartViewCompat extends DJIRelativeLayout {
    private static final int l = 3;
    private static final int m = 3;
    private static final boolean p = true;
    int a;
    int b;
    private DJILineChartViewCompat c = null;
    private DJIImageView d = null;
    private boolean e = false;
    private int f;
    private int g;
    private boolean h = false;
    private DJIPreviewActivityLongan i = null;
    private Animation j = null;
    private Animation k = null;
    private final float[] n = new float[58];
    private OnClickListener o = null;
    private boolean q = false;
    private int r = 0;
    private int s = 0;
    private LayoutParams t = null;

    public DJICameraChartViewCompat(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        try {
            this.i = (DJIPreviewActivityLongan) context;
        } catch (ClassCastException e) {
            this.i = null;
        }
    }

    public void dispatchOnCreate() {
        a();
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        getScreenSize();
        getWidthAndHeight();
    }

    @SuppressLint({"NewApi"})
    private void getScreenSize() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService("window");
        Point point = new Point();
        if (VERSION.SDK_INT < 17) {
            DisplayMetrics displayMetrics = new DisplayMetrics();
            windowManager.getDefaultDisplay().getMetrics(displayMetrics);
            this.f = displayMetrics.widthPixels;
            this.g = displayMetrics.heightPixels;
            return;
        }
        windowManager.getDefaultDisplay().getRealSize(point);
        this.f = point.x;
        this.g = point.y;
    }

    @SuppressLint({"NewApi"})
    public void getWidthAndHeight() {
        this.f = DJIPreviewActivityLongan.mScreenWidth;
        this.g = DJIPreviewActivityLongan.mScreenHeight;
        if (this.t == null) {
            this.t = (LayoutParams) getLayoutParams();
        }
        this.t.addRule(12, 0);
        this.t.addRule(11, 0);
        this.t.rightMargin = 0;
        this.t.bottomMargin = 0;
        if (getResources().getConfiguration().orientation == 2) {
            this.t.addRule(11, 0);
            this.t.addRule(12);
            this.t.leftMargin = (this.f * 2) / 5;
        } else {
            this.t.addRule(12, 0);
            this.t.addRule(11);
            this.t.topMargin = (this.g * 3) / 5;
        }
        setLayoutParams(this.t);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        getScreenSize();
        getWidthAndHeight();
    }

    public void dispatchOnDestroy() {
        b();
    }

    public void showChart() {
        if (this.h) {
            show();
        }
    }

    public void hideChart() {
        go();
    }

    private void a(float[] fArr, short[] sArr) {
        for (int i = 3; i < 61; i++) {
            fArr[i - 3] = (float) sArr[i];
        }
    }

    public void onEventMainThread(DataCameraGetPushChartInfo dataCameraGetPushChartInfo) {
        if (getVisibility() == 0) {
            a(this.n, dataCameraGetPushChartInfo.getParams());
            this.c.setData(this.n);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        boolean isHistogramEnable = dataCameraGetPushStateInfo.isHistogramEnable();
        if (this.h != isHistogramEnable) {
            this.h = isHistogramEnable;
            if (isHistogramEnable) {
                showChart();
            } else {
                hideChart();
            }
        }
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.HIDE_ALL) {
            go();
        } else if (mVar == m.SHOW_ALL && !DJIPreviewActivityLongan.isPopViewShown() && DataCameraGetPushStateInfo.getInstance().isHistogramEnable()) {
            show();
        }
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            onEventMainThread(DataCameraGetPushChartInfo.getInstance());
            startAnimation(this.j);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
            startAnimation(this.k);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.o = new OnClickListener(this) {
                final /* synthetic */ DJICameraChartViewCompat a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (view.getId() == R.id.fpv_camera_close_img_compat) {
                        DataCameraSetPushChart.getInstance().a(false).start(new d(this) {
                            final /* synthetic */ AnonymousClass1 a;

                            {
                                this.a = r1;
                            }

                            public void onSuccess(Object obj) {
                            }

                            public void onFailure(a aVar) {
                            }
                        });
                    }
                }
            };
            this.c = (DJILineChartViewCompat) findViewById(R.id.fpv_camera_chart_line_compat);
            this.d = (DJIImageView) findViewById(R.id.fpv_camera_close_img_compat);
            this.d.setOnClickListener(this.o);
            Context context = getContext();
            this.j = AnimationUtils.loadAnimation(context, R.anim.show_middle_compat);
            this.k = AnimationUtils.loadAnimation(context, R.anim.hide_middel_compat);
        }
    }

    private void a() {
        if (!this.e) {
            this.e = true;
            c.a().a(this);
        }
    }

    private void b() {
        if (this.e) {
            this.e = false;
            c.a().d(this);
        }
    }

    private void a(MotionEvent motionEvent) {
        int i = 0;
        if (this.t == null) {
            this.t = (LayoutParams) getLayoutParams();
        }
        this.t.addRule(12, 0);
        this.t.addRule(11, 0);
        this.t.rightMargin = 0;
        this.t.bottomMargin = 0;
        int rawX = (int) (motionEvent.getRawX() - ((float) this.r));
        int rawY = (int) (motionEvent.getRawY() - ((float) this.s));
        int width = getWidth();
        int height = getHeight();
        if (rawX + width > this.f) {
            rawX = this.f - width;
        } else if (rawX <= 0) {
            rawX = 0;
        }
        this.a = rawX;
        if (rawY + height > this.g) {
            i = this.g - height;
        } else if (rawY > 0) {
            i = rawY;
        }
        this.b = i;
        Log.v("x", this.a + "");
        Log.v("y", this.b + "");
        this.t.leftMargin = this.a;
        this.t.topMargin = this.b;
        setLayoutParams(this.t);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.q = true;
                this.r = (int) motionEvent.getX();
                this.s = (int) motionEvent.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 1:
            case 3:
                if (this.q) {
                    this.q = false;
                    break;
                }
                break;
            case 2:
                if (this.q) {
                    a(motionEvent);
                    break;
                }
                break;
        }
        return true;
    }
}
