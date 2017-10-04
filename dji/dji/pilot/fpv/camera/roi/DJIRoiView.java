package dji.pilot.fpv.camera.roi;

import android.content.Context;
import android.graphics.Paint.FontMetrics;
import android.graphics.drawable.Drawable;
import android.os.Handler.Callback;
import android.os.Message;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.RelativeLayout.LayoutParams;
import dji.midware.data.model.P3.DataCameraGetPushTauParam;
import dji.midware.data.model.P3.DataCameraTauParamThermometric;
import dji.midware.data.model.P3.DataCameraTauParamThermometricEnable.ThermometricType;
import dji.midware.e.d;
import dji.pilot.R;
import dji.pilot.fpv.control.DJIGenSettingDataManager$b;
import dji.pilot.fpv.d.b;
import dji.pilot.publics.objects.DJIBaseActivity;
import dji.pilot.publics.objects.k;
import dji.pilot.publics.objects.k.a;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJITextView;
import dji.publics.layout.DJIDragLayout;
import dji.thirdparty.a.c;

public class DJIRoiView extends DJIDragLayout implements a {
    private static final float a = 0.3f;
    private static final float b = 0.7f;
    private static final int u = 4096;
    private static final long v = 50;
    private DJIImageView c = null;
    private DJITextView d = null;
    private float e = 0.0f;
    private float f = 0.0f;
    private float g = AutoScrollHelper.NO_MAX;
    private boolean h = true;
    private int[] i = null;
    private int[] j = null;
    private int q = 0;
    private int r = 0;
    private float s = 0.0f;
    private k t = null;
    private Callback w = new Callback(this) {
        final /* synthetic */ DJIRoiView a;

        {
            this.a = r1;
        }

        public boolean handleMessage(Message message) {
            switch (message.what) {
                case 4096:
                    DataCameraGetPushTauParam instance = DataCameraGetPushTauParam.getInstance();
                    if (!(this.a.c(instance.getThermometricXAxis(), this.a.e) && this.a.c(instance.getThermometricYAxis(), this.a.f))) {
                        this.a.b(this.a.e, this.a.f);
                        break;
                    }
            }
            return false;
        }
    };

    public DJIRoiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public void showView() {
        if (getVisibility() != 0) {
            DataCameraGetPushTauParam instance = DataCameraGetPushTauParam.getInstance();
            if (instance.supportSpotThermometric()) {
                this.c.setBackgroundResource(R.drawable.spot_roi_icon);
            } else {
                this.c.setBackgroundResource(R.drawable.center_roi_icon);
            }
            Drawable background = this.c.getBackground();
            this.q = background.getIntrinsicWidth();
            this.r = background.getIntrinsicHeight();
            this.h = true;
            if (instance.supportSpotThermometric()) {
                a(instance, false, this.e, this.f);
            } else {
                a(instance, false, 0.0f, 0.0f);
            }
            a(instance, true);
            setVisibility(0);
            if (!c.a().c(this)) {
                c.a().a(this);
            }
        }
    }

    public void hideView() {
        if (getVisibility() != 8) {
            if (c.a().c(this)) {
                c.a().d(this);
            }
            this.g = AutoScrollHelper.NO_MAX;
            setVisibility(8);
        }
    }

    public void handleThermmometricType(ThermometricType thermometricType) {
        if (ThermometricType.b == thermometricType) {
            DataCameraGetPushTauParam instance = DataCameraGetPushTauParam.getInstance();
            this.e = instance.getThermometricXAxis();
            this.f = instance.getThermometricYAxis();
            return;
        }
        this.e = 0.0f;
        this.f = 0.0f;
    }

    public void setHVLimits(int[] iArr, int[] iArr2) {
        this.i = iArr;
        this.j = iArr2;
    }

    public void handleMotion(MotionEvent motionEvent) {
        float x = motionEvent.getX();
        float y = motionEvent.getY();
        if (((float) this.i[0]) < x && x < ((float) this.i[1]) && ((float) this.j[0]) < y && y < ((float) this.j[1])) {
            this.g = AutoScrollHelper.NO_MAX;
            this.t.removeMessages(4096);
            x = a(x, this.i);
            y = a(y, this.j);
            a(DataCameraGetPushTauParam.getInstance(), true, x, y);
            b(x, y);
        }
    }

    private void b(float f, float f2) {
        new DataCameraTauParamThermometric().a(f, f2).b(false).start(new d(this) {
            final /* synthetic */ DJIRoiView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.t.sendEmptyMessageDelayed(4096, 50);
            }
        });
    }

    private void a(DataCameraGetPushTauParam dataCameraGetPushTauParam, boolean z, float f, float f2) {
        if (f <= 0.0f || f2 <= 0.0f) {
            f2 = 0.53f;
            f = 0.512f;
        } else if (z) {
            this.c.setBackgroundResource(R.drawable.spoting_roi_icon);
            Drawable background = this.c.getBackground();
            this.q = background.getIntrinsicWidth();
            this.r = background.getIntrinsicHeight();
            this.h = false;
            this.d.hide();
        }
        if (this.e != f || this.f != f2 || z) {
            LayoutParams layoutParams = (LayoutParams) this.d.getLayoutParams();
            int[] rules = layoutParams.getRules();
            boolean z2 = rules[2] != 0;
            boolean z3;
            if (rules[3] != 0) {
                z3 = true;
            } else {
                z3 = false;
            }
            if (f2 < a) {
                if (this.f >= a || z2) {
                    layoutParams.addRule(3, R.id.p8);
                    this.d.setLayoutParams(layoutParams);
                    layoutParams = (LayoutParams) this.c.getLayoutParams();
                    layoutParams.addRule(3, 0);
                    this.c.setLayoutParams(layoutParams);
                }
            } else if (f2 > b && (this.f <= b || r3)) {
                layoutParams.addRule(3, 0);
                this.d.setLayoutParams(layoutParams);
                layoutParams = (LayoutParams) this.c.getLayoutParams();
                layoutParams.addRule(3, R.id.p9);
                this.c.setLayoutParams(layoutParams);
            }
            this.e = f;
            this.f = f2;
            a((float) b(f, this.i), (float) b(f2, this.j));
        }
    }

    private boolean c(float f, float f2) {
        return Math.abs(f2 - f) <= 0.001f;
    }

    public void onEventMainThread(DataCameraGetPushTauParam dataCameraGetPushTauParam) {
        a(dataCameraGetPushTauParam, false);
    }

    private void a(DataCameraGetPushTauParam dataCameraGetPushTauParam, boolean z) {
        if (!dataCameraGetPushTauParam.supportSpotThermometric() || (c(dataCameraGetPushTauParam.getThermometricXAxis(), this.e) && c(dataCameraGetPushTauParam.getThermometricYAxis(), this.f))) {
            if (dataCameraGetPushTauParam.supportSpotThermometric()) {
                this.c.setBackgroundResource(R.drawable.spot_roi_icon);
            }
            boolean isThermometricValid = dataCameraGetPushTauParam.supportSpotThermometric() ? dataCameraGetPushTauParam.isThermometricValid() : true;
            if (z || isThermometricValid != this.h) {
                this.h = isThermometricValid;
                if (isThermometricValid) {
                    this.d.show();
                } else {
                    this.d.hide();
                }
            }
            float thermometricTemp = dataCameraGetPushTauParam.getThermometricTemp();
            if (Math.abs(thermometricTemp - this.g) >= 0.1f) {
                this.g = thermometricTemp;
                this.d.setText(b.a(getContext(), thermometricTemp, true));
            }
        }
    }

    protected void a(float f, float f2) {
        if (!isInEditMode()) {
            Object obj = ((LayoutParams) this.d.getLayoutParams()).getRules()[2] != 0 ? 1 : null;
            setX(f - ((float) (this.q / 2)));
            if (obj != null) {
                setY((f2 - ((float) (this.r / 2))) - this.s);
            } else {
                setY(f2 - ((float) (this.r / 2)));
            }
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        a((float) b(this.e, this.i), (float) b(this.f, this.j));
    }

    public void onEventMainThread(DJIGenSettingDataManager$b dJIGenSettingDataManager$b) {
        if (dJIGenSettingDataManager$b == DJIGenSettingDataManager$b.TEMPERATURE_UNIT_CHANGED && this.g != AutoScrollHelper.NO_MAX) {
            this.d.setText(b.a(getContext(), this.g, true));
        }
    }

    private float a(float f, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0.0f;
        }
        return (f - ((float) iArr[0])) / ((float) (iArr[1] - iArr[0]));
    }

    private int b(float f, int[] iArr) {
        if (iArr == null || iArr.length < 2) {
            return 0;
        }
        return (int) (((float) iArr[0]) + (((float) (iArr[1] - iArr[0])) * f));
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.t = new k(this, this.w);
            this.i = new int[]{0, DJIBaseActivity.screenWidth};
            this.j = new int[]{0, DJIBaseActivity.screenHeight};
            this.c = (DJIImageView) findViewById(R.id.p8);
            this.d = (DJITextView) findViewById(R.id.p9);
            FontMetrics fontMetrics = this.d.getPaint().getFontMetrics();
            this.s = (fontMetrics.bottom - fontMetrics.top) + ((float) dji.pilot.fpv.model.b.a(getContext(), R.dimen.n4));
        }
    }

    public boolean isFinished() {
        return getVisibility() != 0;
    }
}
