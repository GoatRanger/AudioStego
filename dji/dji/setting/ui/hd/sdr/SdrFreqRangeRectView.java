package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import dji.common.airlink.DJILBAirLinkFrequencyPointRSSI;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBSDRBandwidth;
import dji.common.airlink.SDRHdOffsetParams;
import dji.common.error.DJIError;
import dji.common.util.SDRLinkHelper;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.c.h;

public class SdrFreqRangeRectView extends RelativeLayout implements OnTouchListener, d {
    private ImageView a;
    private ImageView b;
    private ProgressBar c;
    private boolean d = false;
    private float e = 0.0f;
    private int f = Integer.MAX_VALUE;
    private float g = SDRLinkHelper.ORIGINAL_NF_START_INDEX;
    private int h = 5;
    private float i = AutoScrollHelper.NO_MAX;
    private float j = AutoScrollHelper.NO_MAX;
    private float k = AutoScrollHelper.NO_MAX;
    private float l = AutoScrollHelper.NO_MAX;
    private int m = Integer.MAX_VALUE;
    private LBAirLinkChannelSelectionMode n = LBAirLinkChannelSelectionMode.Auto;
    private DJILBAirLinkFrequencyPointRSSI[] o;
    private a p;
    private Drawable q;
    private Drawable r;
    private c s = b.h(dji.sdksharedlib.b.a.c.K);
    private c t = b.h("ChannelSelectionMode");
    private c u = b.h(dji.sdksharedlib.b.a.c.H);
    private c v = b.h(dji.sdksharedlib.b.a.c.N);
    private c w = b.h("FrequencyPointIndexValidRange");
    private c x = b.h("SdrHdOffsetParamValues");

    public interface a {
        void a(float f, float f2, float f3, float f4, boolean z);

        void a(String str);
    }

    public SdrFreqRangeRectView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        if (!isInEditMode()) {
            this.a = (ImageView) findViewById(R.id.sdr_snr_freq_range_center_down);
            this.b = (ImageView) findViewById(R.id.sdr_snr_freq_range_center_up);
            this.c = (ProgressBar) findViewById(R.id.sdr_snr_freq_rect_pgb);
            this.q = getResources().getDrawable(R.drawable.freq_range_rect);
            this.r = getResources().getDrawable(R.drawable.freq_range_rect_hover);
            setOnTouchListener(this);
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode() && isShown()) {
            a();
        }
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!isInEditMode()) {
            if (i == 0) {
                a();
            } else {
                dji.sdksharedlib.a.a.a(this);
            }
        }
    }

    protected void onDetachedFromWindow() {
        dji.sdksharedlib.a.a.a(this);
        super.onDetachedFromWindow();
    }

    private void a() {
        DJISDKCache.getInstance().startListeningForUpdates(this.s, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.t, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.u, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.v, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.w, this, true);
        d();
    }

    public SdrFreqRangeRectView setParentWidth(int i) {
        this.m = i;
        return this;
    }

    public SdrFreqRangeRectView setNumValues(int i) {
        this.f = i;
        return this;
    }

    public SdrFreqRangeRectView setWidthInterval(float f) {
        this.i = f;
        return this;
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        float f = 0.0f;
        if (this.n != LBAirLinkChannelSelectionMode.Manual) {
            return false;
        }
        if (motionEvent.getAction() == 0) {
            this.e = motionEvent.getRawX();
            this.d = true;
            a(this.d);
        } else if (motionEvent.getAction() == 2) {
            r1 = motionEvent.getRawX();
            r0 = (view.getX() + r1) - this.e;
            if (r0 >= 0.0f) {
                if (((float) view.getWidth()) + r0 > ((float) this.m)) {
                    f = (float) (this.m - view.getWidth());
                } else {
                    f = r0;
                }
            }
            view.setX(f);
            this.e = r1;
            this.k = b(f);
            if (this.p != null) {
                this.p.a(this.k, this.k + ((float) (this.h * 2)), f, ((float) view.getWidth()) + f, this.d);
            }
        } else if (motionEvent.getAction() == 1 || motionEvent.getAction() == 3) {
            this.d = false;
            float x = view.getX();
            if (x % this.i != 0.0f) {
                f = x / this.i;
                r1 = f * this.i;
                r0 = (f + 1.0f) * this.i;
                if (x - r1 < r0 - x || f + 1.0f >= ((float) (this.f - 1))) {
                    r0 = r1;
                }
                view.setX(r0);
                f = r0;
            } else {
                f = x;
            }
            this.k = b(f);
            if (this.p != null) {
                this.p.a(this.k, this.k + ((float) (this.h * 2)), f, ((float) view.getWidth()) + f, this.d);
            }
            c();
            a(this.d);
            a(f);
        }
        return true;
    }

    private void a(float f) {
        float f2 = (((float) this.h) * this.i) + f;
        float b = b(f2);
        int convertFrequencyPointIndexFromFrequency = SDRLinkHelper.convertFrequencyPointIndexFromFrequency(b);
        Log.i(getClass().getSimpleName(), "centerX: " + f2 + " caculNf: " + b + " sendNf: " + convertFrequencyPointIndexFromFrequency);
        DJISDKCache.getInstance().setValue(this.s, Integer.valueOf(convertFrequencyPointIndexFromFrequency), new h(this) {
            final /* synthetic */ SdrFreqRangeRectView a;

            {
                this.a = r1;
            }

            public void a() {
                Log.i(getClass().getSimpleName(), "sendNf onSuccess!");
            }

            public void a(DJIError dJIError) {
                Log.i(getClass().getSimpleName(), "sendNf onFails: " + dJIError);
            }
        });
    }

    public void setOnRangeChangedListener(a aVar) {
        this.p = aVar;
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (cVar.equals(this.w)) {
            onValidRangeChanged();
        } else if (cVar.equals(this.s)) {
            e();
        } else if (cVar.equals(this.v)) {
            b();
        }
    }

    public void onValidRangeChanged() {
        LBSDRBandwidth lBSDRBandwidth = (LBSDRBandwidth) dji.sdksharedlib.a.a.a(this.u);
        LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode = (LBAirLinkChannelSelectionMode) dji.sdksharedlib.a.a.a(this.t);
        Float[] fArr = (Float[]) dji.sdksharedlib.a.a.a(this.w);
        if (lBSDRBandwidth != null && lBAirLinkChannelSelectionMode != null && this.m != Integer.MAX_VALUE && fArr != null) {
            float f;
            if (lBSDRBandwidth == LBSDRBandwidth.Bandwidth20MHz) {
                f = 10.0f;
            } else {
                f = 5.0f;
            }
            float floatValue = fArr[0].floatValue();
            float floatValue2 = fArr[1].floatValue();
            if (floatValue != SDRLinkHelper.ORIGINAL_NF_START_INDEX) {
                float f2 = floatValue - f;
                f += floatValue2;
                floatValue2 = f2;
            } else {
                f = floatValue2;
                floatValue2 = floatValue;
            }
            this.f = (int) (f - floatValue2);
            this.i = (((float) this.m) * 1.0f) / ((float) this.f);
            this.g = floatValue2;
            d();
        }
    }

    private void b() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.v);
        if (availableValue != null && availableValue.e() != null) {
            this.o = (DJILBAirLinkFrequencyPointRSSI[]) availableValue.e();
            c();
        }
    }

    private void c() {
        if (this.o != null && this.j != AutoScrollHelper.NO_MAX) {
            float f;
            int i = (int) ((this.k - this.g) / 2.0f);
            if (i < 0) {
                i = 0;
            }
            float f2 = 0.0f;
            int i2 = i;
            while (i2 < this.h + i && i2 < this.o.length) {
                f2 += (float) this.o[i2].rssi;
                i2++;
            }
            f2 /= (float) this.h;
            SDRHdOffsetParams sDRHdOffsetParams = (SDRHdOffsetParams) dji.sdksharedlib.a.a.a(this.x);
            if (sDRHdOffsetParams != null) {
                f = ((float) (sDRHdOffsetParams.mTxPowerOffset + ((sDRHdOffsetParams.mRcLinkOffset > 0 ? sDRHdOffsetParams.mRcLinkOffset : 0) + sDRHdOffsetParams.mPathLossOffset))) + f2;
            } else {
                f = f2;
            }
            this.c.setProgress((int) ((f - -110.0f) * 2.0f));
            if (this.p != null) {
                this.p.a(String.format("%.1f", new Object[]{Float.valueOf(f)}) + "dBm");
            }
        }
    }

    private void d() {
        e();
        f();
        h();
    }

    private void e() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.s);
        if (availableValue != null && availableValue.e() != null && this.i != AutoScrollHelper.NO_MAX && !this.d) {
            this.j = SDRLinkHelper.convertFrequencyFormFrequencyPointIndex(((Integer) availableValue.e()).intValue());
            Log.i(getClass().getSimpleName(), "convertIndex: " + this.j);
            this.l = (this.j - this.g) * this.i;
            g();
        }
    }

    private void f() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.u);
        if (availableValue != null && availableValue.e() != null) {
            if (availableValue.e() == LBSDRBandwidth.Bandwidth10MHz) {
                this.h = 5;
            } else {
                this.h = 10;
            }
            g();
        }
    }

    private void g() {
        if (this.l != AutoScrollHelper.NO_MAX) {
            this.k = this.j - ((float) this.h);
            float f = this.l - (((float) this.h) * this.i);
            float f2 = this.l + (((float) this.h) * this.i);
            Log.i(getClass().getSimpleName(), "centerX: " + this.l + " leftX: " + f + " rightX: " + f2);
            setX(f);
            LayoutParams layoutParams = getLayoutParams();
            layoutParams.width = (int) (f2 - f);
            setLayoutParams(layoutParams);
            if (this.p != null) {
                this.p.a(this.j - ((float) this.h), this.j + ((float) this.h), f, f2, this.d);
            }
            b();
        }
    }

    private float b(float f) {
        return (float) (Math.floor((double) ((f / this.i) + this.g)) + 0.5d);
    }

    private void h() {
        dji.sdksharedlib.d.a availableValue = DJISDKCache.getInstance().getAvailableValue(this.t);
        if (availableValue != null && availableValue.e() != null) {
            this.n = (LBAirLinkChannelSelectionMode) availableValue.e();
            if (this.n == LBAirLinkChannelSelectionMode.Auto) {
                this.a.setVisibility(4);
                this.b.setVisibility(4);
                this.d = false;
                setBackground(this.q);
                return;
            }
            this.a.setVisibility(0);
            this.b.setVisibility(0);
            setBackground(this.r);
            postDelayed(new Runnable(this) {
                final /* synthetic */ SdrFreqRangeRectView a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.setBackground(this.a.q);
                }
            }, 200);
        }
    }

    private void a(boolean z) {
        if (z) {
            this.c.setVisibility(4);
            setBackground(this.r);
            return;
        }
        this.c.setVisibility(0);
        setBackground(this.q);
    }
}
