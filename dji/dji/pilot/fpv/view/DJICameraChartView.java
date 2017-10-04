package dji.pilot.fpv.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.RelativeLayout.LayoutParams;
import dji.midware.data.model.P3.DataCameraGetPushChartInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.pilot.R;
import dji.pilot.fpv.activity.DJIBaseNewPreviewActivity;
import dji.pilot.fpv.activity.DJIPreviewActivity;
import dji.pilot.fpv.activity.DJIPreviewActivityLitchi;
import dji.pilot.fpv.control.DJIGenSettingDataManager;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.widget.DJILineChartView;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;

public class DJICameraChartView extends DJIRelativeLayout {
    private static final int h = 3;
    private static final int i = 3;
    private static final boolean l = true;
    private DJILineChartView a = null;
    private DJIImageView b = null;
    private boolean c = false;
    private boolean d = false;
    private Context e = null;
    private Animation f = null;
    private Animation g = null;
    private final float[] j = new float[58];
    private OnClickListener k = null;
    private boolean m = false;
    private int n = 0;
    private int o = 0;
    private LayoutParams p = null;

    public DJICameraChartView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = context;
    }

    public void dispatchOnCreate() {
        b();
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventMainThread(DataCameraGetPushStateInfo.getInstance());
        }
    }

    public void dispatchOnDestroy() {
        c();
    }

    private boolean a() {
        if (this.e instanceof DJIPreviewActivity) {
            if (((DJIPreviewActivity) this.e).a()) {
                return false;
            }
        } else if (this.e instanceof DJIPreviewActivityLitchi) {
            if (((DJIPreviewActivityLitchi) this.e).a()) {
                return false;
            }
        } else if ((this.e instanceof DJIBaseNewPreviewActivity) && ((DJIBaseNewPreviewActivity) this.e).g()) {
            return false;
        }
        return true;
    }

    public void showChart() {
        if (this.d && a()) {
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
            a(this.j, dataCameraGetPushChartInfo.getParams());
            this.a.setData(this.j);
        }
    }

    public void onEventMainThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        boolean isHistogramEnable = dataCameraGetPushStateInfo.isHistogramEnable();
        DJIGenSettingDataManager.getInstance().e();
        if (this.d != isHistogramEnable) {
            this.d = isHistogramEnable;
            if (isHistogramEnable) {
                showChart();
            } else {
                hideChart();
            }
        }
    }

    public void show() {
        if (getVisibility() != 0) {
            setVisibility(0);
            onEventMainThread(DataCameraGetPushChartInfo.getInstance());
            startAnimation(this.f);
        }
    }

    public void go() {
        if (getVisibility() != 8) {
            setVisibility(8);
            startAnimation(this.g);
        }
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.k = new OnClickListener(this) {
                final /* synthetic */ DJICameraChartView a;

                {
                    this.a = r1;
                }

                public void onClick(View view) {
                    if (view.getId() == R.id.ul) {
                        DJIGenSettingDataManager.getInstance().b(false);
                    }
                }
            };
            this.a = (DJILineChartView) findViewById(R.id.uk);
            this.b = (DJIImageView) findViewById(R.id.ul);
            this.b.setOnClickListener(this.k);
            Context context = getContext();
            this.f = AnimationUtils.loadAnimation(context, R.anim.ba);
            this.g = AnimationUtils.loadAnimation(context, R.anim.ao);
        }
    }

    private void b() {
        if (!this.c) {
            this.c = true;
            c.a().a(this);
        }
    }

    private void c() {
        if (this.c) {
            this.c = false;
            c.a().d(this);
        }
    }

    private void a(MotionEvent motionEvent) {
        int i = 0;
        if (this.p == null) {
            this.p = (LayoutParams) getLayoutParams();
            this.p.addRule(12, 0);
            this.p.addRule(11, 0);
            this.p.rightMargin = 0;
            this.p.bottomMargin = 0;
        }
        int rawX = (int) (motionEvent.getRawX() - ((float) this.n));
        int rawY = (int) (motionEvent.getRawY() - ((float) this.o));
        int width = getWidth();
        int height = getHeight();
        if (rawX + width > DJIBaseActivity.screenWidth) {
            rawX = DJIBaseActivity.screenWidth - width;
        } else if (rawX <= 0) {
            rawX = 0;
        }
        if (rawY + height > DJIBaseActivity.screenHeight) {
            i = DJIBaseActivity.screenHeight - height;
        } else if (rawY > 0) {
            i = rawY;
        }
        this.p.leftMargin = rawX;
        this.p.topMargin = i;
        setLayoutParams(this.p);
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        switch (motionEvent.getAction()) {
            case 0:
                this.m = true;
                this.n = (int) motionEvent.getX();
                this.o = (int) motionEvent.getY();
                getParent().requestDisallowInterceptTouchEvent(true);
                break;
            case 1:
            case 3:
                if (this.m) {
                    this.m = false;
                    break;
                }
                break;
            case 2:
                if (this.m) {
                    a(motionEvent);
                    break;
                }
                break;
        }
        return true;
    }
}
