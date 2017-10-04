package dji.setting.ui.hd.sdr;

import android.content.Context;
import android.graphics.LinearGradient;
import android.graphics.Shader.TileMode;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import dji.common.airlink.DJILBAirLinkFrequencyPointRSSI;
import dji.common.airlink.LBAirLinkChannelSelectionMode;
import dji.common.airlink.LBSDRBandwidth;
import dji.common.util.SDRLinkHelper;
import dji.pilot.setting.ui.R;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.a.a;
import dji.sdksharedlib.a.b;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.c.d;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.ArrayList;
import java.util.List;
import lecho.lib.hellocharts.model.Viewport;
import lecho.lib.hellocharts.model.j;
import lecho.lib.hellocharts.model.k;
import lecho.lib.hellocharts.model.m;
import lecho.lib.hellocharts.view.LineChartView;

public class SdrFreqView extends RelativeLayout implements d {
    public static final int a = 50;
    public static final int b = -110;
    private LineChartView c;
    private k d;
    private SdrFreqRangeRectView e;
    private SdrFreqRangeTextView f;
    private SdrRectCenterTextView g;
    private TextView h;
    private SdrChartRightYAxisView i;
    private SdrDistanceLineView j;
    private final int k = 41;
    private int l = 81;
    private float m = 1.0f;
    private int n = -6954177;
    private int o = -507326;
    private c p = b.h(dji.sdksharedlib.b.a.c.N);
    private c q = b.h("ChannelSelectionMode");
    private c r = b.h(dji.sdksharedlib.b.a.c.H);
    private c s = b.h("FrequencyPointIndexValidRange");
    private c t = b.h("SdrHdDistOffset");
    private float u = 2400.0f;

    public SdrFreqView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        if (!isInEditMode() && z) {
            this.m = (((float) this.c.getWidth()) * 1.0f) / ((float) (this.l - 1));
            this.e.setWidthInterval(this.m).setNumValues(this.l).setParentWidth(this.c.getWidth()).onValidRangeChanged();
            d();
            e();
        }
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode() && isShown()) {
            a();
        }
    }

    protected void onDetachedFromWindow() {
        a.a(this);
        super.onDetachedFromWindow();
    }

    protected void onVisibilityChanged(View view, int i) {
        super.onVisibilityChanged(view, i);
        if (!isInEditMode()) {
            if (i == 0) {
                a();
            } else {
                a.a(this);
            }
        }
    }

    private void a() {
        DJISDKCache.getInstance().startListeningForUpdates(this.p, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.q, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.r, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.s, this, true);
        DJISDKCache.getInstance().startListeningForUpdates(this.t, this, true);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.c = (LineChartView) findViewById(R.id.sdr_snr_line);
        b();
        this.c.setLineChartData(this.d);
        this.c.setZoomEnabled(false);
        this.c.setLineShader(new LinearGradient(0.0f, getResources().getDimension(R.dimen.setting_ui_hd_sdr_chart_height), 0.0f, 0.0f, this.n, this.o, TileMode.MIRROR));
        this.c.setViewportCalculationEnabled(false);
        c();
        this.i = (SdrChartRightYAxisView) findViewById(R.id.sdr_quality_value);
        this.j = (SdrDistanceLineView) findViewById(R.id.sdr_quality_line);
        this.h = (TextView) findViewById(R.id.sdr_custom_alert_tip);
        this.f = (SdrFreqRangeTextView) findViewById(R.id.sdr_freq_range_tv);
        this.g = (SdrRectCenterTextView) findViewById(R.id.sdr_rect_average_value);
        this.e = (SdrFreqRangeRectView) findViewById(R.id.sdr_snr_freq_range_rect);
        this.e.setOnRangeChangedListener(new SdrFreqRangeRectView.a(this) {
            final /* synthetic */ SdrFreqView a;

            {
                this.a = r1;
            }

            public void a(float f, float f2, float f3, float f4, boolean z) {
                this.a.f.setMinMaxValue(f, f2, f3, f4);
                this.a.g.setCenterPos((f3 + f4) / 2.0f, z);
            }

            public void a(String str) {
                this.a.g.setCenterAverageText(str);
            }
        });
    }

    private void b() {
        List arrayList = new ArrayList();
        arrayList.add(new m(0.0f, 0.0f));
        for (int i = 0; i < 41; i++) {
            arrayList.add(new m(((float) i) + dji.pilot.visual.a.d.c, 0.0f));
        }
        arrayList.add(new m(41.0f, 0.0f));
        j jVar = new j(arrayList);
        jVar.d(2);
        jVar.a(false);
        List arrayList2 = new ArrayList();
        arrayList2.add(jVar);
        this.d = new k(arrayList2);
    }

    private void c() {
        Viewport viewport = new Viewport(this.c.getMaximumViewport());
        viewport.d = 0.0f;
        viewport.b = 50.0f;
        viewport.a = 0.0f;
        viewport.c = 41.0f;
        this.c.setMaximumViewport(viewport);
        this.c.setCurrentViewport(viewport);
    }

    private void setValues(DJILBAirLinkFrequencyPointRSSI[] dJILBAirLinkFrequencyPointRSSIArr) {
        int i = 0;
        this.c.clearAnimation();
        int i2 = 0;
        for (m mVar : ((j) this.c.getLineChartData().m().get(0)).b()) {
            if (i == 0) {
                mVar.b(mVar.b(), (float) ((dJILBAirLinkFrequencyPointRSSIArr[i2].rssi + 10) + FTPCodes.RESTART_MARKER));
            } else if (i == dJILBAirLinkFrequencyPointRSSIArr.length + 1) {
                mVar.b(mVar.b(), (float) ((dJILBAirLinkFrequencyPointRSSIArr[i - 2].rssi + 10) + FTPCodes.RESTART_MARKER));
            } else {
                int i3 = i2 + 1;
                mVar.b(mVar.b(), (float) (dJILBAirLinkFrequencyPointRSSIArr[i2].rssi + FTPCodes.RESTART_MARKER));
                i2 = i3;
            }
            i++;
        }
        this.c.startDataAnimation(300);
    }

    public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
        if (aVar2 != null) {
            if (cVar.equals(this.p)) {
                setValues((DJILBAirLinkFrequencyPointRSSI[]) aVar2.e());
            } else if (cVar.equals(this.s)) {
                d();
            } else if (cVar.equals(this.t)) {
                e();
            }
        }
    }

    private void d() {
        LBSDRBandwidth lBSDRBandwidth = (LBSDRBandwidth) a.a(this.r);
        LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode = (LBAirLinkChannelSelectionMode) a.a(this.q);
        Float[] fArr = (Float[]) a.a(this.s);
        if (lBSDRBandwidth != null && lBAirLinkChannelSelectionMode != null && fArr != null) {
            float f;
            if (lBSDRBandwidth == LBSDRBandwidth.Bandwidth20MHz) {
                f = 10.0f;
            } else {
                f = 5.0f;
            }
            float floatValue = fArr[0].floatValue();
            float floatValue2 = fArr[1].floatValue();
            if (floatValue != SDRLinkHelper.ORIGINAL_NF_START_INDEX) {
                floatValue -= f;
                f += floatValue2;
                floatValue2 = floatValue;
            } else {
                f = floatValue2;
                floatValue2 = floatValue;
            }
            Viewport viewport = new Viewport(this.c.getMaximumViewport());
            viewport.d = 0.0f;
            viewport.b = 50.0f;
            viewport.a = (floatValue2 - this.u) / 2.0f;
            viewport.c = (f - this.u) / 2.0f;
            if (viewport.a < 0.0f) {
                viewport.a = 0.0f;
            }
            if (viewport.c > 41.0f) {
                viewport.c = 41.0f;
            }
            this.c.setMaximumViewport(viewport);
            this.c.setCurrentViewport(viewport);
            a(lBAirLinkChannelSelectionMode);
            Log.i(getClass().getSimpleName(), "left: " + floatValue2 + "right: " + f);
        }
    }

    private void a(LBAirLinkChannelSelectionMode lBAirLinkChannelSelectionMode) {
        if (lBAirLinkChannelSelectionMode == LBAirLinkChannelSelectionMode.Auto) {
            this.h.setVisibility(8);
        } else {
            this.h.setVisibility(0);
        }
    }

    private void e() {
        Integer num = (Integer) a.a(this.t);
        if (num != null) {
            this.i.set1KmNfValue(num.intValue());
            this.j.set1KmNfValue(num.intValue());
        }
    }
}
