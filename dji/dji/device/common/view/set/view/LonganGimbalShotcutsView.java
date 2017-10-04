package dji.device.common.view.set.view;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Handler;
import android.os.Looper;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.RelativeLayout.LayoutParams;
import dji.device.common.DJIUIEventManagerLongan.j;
import dji.device.common.DJIUIEventManagerLongan.m;
import dji.device.common.view.DJIRoundLinearLayoutCompat;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataGimbalControl.MODE;
import dji.midware.data.model.P3.DataGimbalGetPushParams;
import dji.midware.data.model.P3.DataSpecialControl;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.publics.DJIUI.DJIImageView;
import dji.thirdparty.a.c;

public class LonganGimbalShotcutsView extends DJIRoundLinearLayoutCompat implements OnClickListener {
    public static boolean a = false;
    public static final String k = ".";
    private static final String l = "LonganGimbalShotcutsView";
    private static final long n = 1290043;
    LayoutParams b;
    RelativeLayout c;
    RelativeLayout d;
    RelativeLayout e;
    RelativeLayout f;
    DJIImageView g;
    DJIImageView h;
    DJIImageView i;
    DJIImageView j;
    private DataCommonGetVersion m = null;

    public LonganGimbalShotcutsView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.b = (LayoutParams) getLayoutParams();
        b();
        a();
        c.a().a(this);
        this.m = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        this.m.start(new d(this) {
            final /* synthetic */ LonganGimbalShotcutsView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                long parseLong = Long.parseLong(this.a.m.getFirmVer(".").replace(".", ""));
                Log.d(LonganGimbalShotcutsView.l, "version:" + parseLong);
                if (parseLong != 0 && parseLong >= LonganGimbalShotcutsView.n) {
                    new Handler(Looper.getMainLooper()).post(new Runnable(this) {
                        final /* synthetic */ AnonymousClass1 a;

                        {
                            this.a = r1;
                        }

                        public void run() {
                            this.a.a.d.setVisibility(0);
                        }
                    });
                }
            }

            public void onFailure(a aVar) {
            }
        }, 1000, 3);
    }

    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        c.a().d(this);
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a();
    }

    private void a() {
        if (getResources().getConfiguration().orientation == 2) {
            this.b.addRule(3, 0);
            this.b.addRule(14, 0);
            this.b.addRule(0, R.id.longan_preview_setting_bar);
            this.b.addRule(15);
            return;
        }
        this.b.addRule(0, 0);
        this.b.addRule(15, 0);
        this.b.addRule(3, R.id.longan_preview_setting_bar);
        this.b.addRule(14);
    }

    private void b() {
        this.c = (RelativeLayout) findViewById(R.id.gimbal_shotcuts_recenter);
        this.d = (RelativeLayout) findViewById(R.id.gimbal_shotcuts_selfie);
        this.e = (RelativeLayout) findViewById(R.id.gimbal_shotcuts_lock);
        this.f = (RelativeLayout) findViewById(R.id.gimbal_shotcuts_sensor_control_ly);
        this.c.setOnClickListener(this);
        this.d.setOnClickListener(this);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g = (DJIImageView) findViewById(R.id.longan_shotcuts_recenter_iv);
        this.h = (DJIImageView) findViewById(R.id.longan_shotcuts_selfie_iv);
        this.i = (DJIImageView) findViewById(R.id.longan_shotcuts_lock_iv);
        this.j = (DJIImageView) findViewById(R.id.longan_shotcuts_sensor_control_iv);
    }

    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.gimbal_shotcuts_recenter) {
            DataSpecialControl.getInstance().resetGimbal().start(20);
            try {
                Class cls = Class.forName("dji.pilot.reflect.FpvReflect");
                cls.getMethod("flurryOsmoRecenter", new Class[0]).invoke(cls, new Object[0]);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (id == R.id.gimbal_shotcuts_selfie) {
            DataSpecialControl.getInstance().selfieGimbal().start(20);
        } else if (id == R.id.gimbal_shotcuts_lock) {
            if (this.i.isSelected()) {
                a(false);
            } else {
                a(true);
            }
        } else if (id != R.id.gimbal_shotcuts_sensor_control_ly) {
        } else {
            if (this.j.isSelected()) {
                b(false);
                this.j.setSelected(false);
                return;
            }
            b(true);
            this.j.setSelected(true);
        }
    }

    public void onEventMainThread(m mVar) {
        if (mVar == m.HIDE_SHOTCUTS_GIMBAL_LY) {
            hide();
            c.a().e(m.SHOW_INFO_BAR);
            a = false;
        } else if (mVar == m.SHOW_SHOTCUTS_GIMBAL_LY) {
            c.a().e(m.HIDE_INFO_BAR);
            a = true;
        } else if (mVar == m.HIDE_ALL) {
            hide();
            a = false;
        } else if (mVar == m.SHOW_TIMELAPSE_LY) {
            hide();
            a = false;
        } else if (mVar == m.SHOW_CONFLICT_RIGHT_VIEW) {
            hide();
            a = false;
        }
    }

    private void a(boolean z) {
        if (z) {
            DataSpecialControl.getInstance().setGimbalMode(MODE.YawNoFollow).start(20);
        } else {
            DataSpecialControl.getInstance().setGimbalMode(MODE.YawFollow).start(20);
        }
    }

    private void b(boolean z) {
        if (z) {
            c.a().e(j.START_SENSOR_CONTROL);
        } else {
            c.a().e(j.STOP_SENSOR_CONTROL);
        }
    }

    public void onEventMainThread(DataGimbalGetPushParams dataGimbalGetPushParams) {
        if (dataGimbalGetPushParams.getMode() == MODE.YawNoFollow) {
            this.i.setSelected(true);
        } else if (dataGimbalGetPushParams.getMode() == MODE.YawFollow) {
            this.i.setSelected(false);
        }
    }
}
