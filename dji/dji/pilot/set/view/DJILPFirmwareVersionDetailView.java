package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.midware.e.e;
import dji.midware.util.m;
import dji.pilot.set.R;
import dji.pilot.set.view.a.a.b;
import java.lang.ref.WeakReference;

public class DJILPFirmwareVersionDetailView extends LinearLayout {
    public static final String a = ".";
    static final String j = "Failed";
    private static final int m = 1;
    private static final int n = 2;
    private static final int o = 3;
    private static final int p = 4;
    private static final int q = 5;
    private static final int r = 6;
    private static final int s = 7;
    private static final int t = 8;
    dji.pilot.set.view.a.a b;
    b c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;
    TextView i;
    a k;
    int l;
    private DataCommonGetVersion u;
    private DataCommonGetVersion v;
    private DataCommonGetVersion w;
    private DataCommonGetVersion x;
    private DataCommonGetVersion y;
    private DataCommonGetVersion z;

    private static final class a extends Handler {
        private final WeakReference<DJILPFirmwareVersionDetailView> a;

        public a(DJILPFirmwareVersionDetailView dJILPFirmwareVersionDetailView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(dJILPFirmwareVersionDetailView);
        }

        public void handleMessage(Message message) {
            DJILPFirmwareVersionDetailView dJILPFirmwareVersionDetailView = (DJILPFirmwareVersionDetailView) this.a.get();
            if (dJILPFirmwareVersionDetailView != null) {
                dJILPFirmwareVersionDetailView.a(message.what, message.arg1);
            }
        }
    }

    public DJILPFirmwareVersionDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = null;
        this.u = null;
        this.v = null;
        this.w = null;
        this.x = null;
        this.y = null;
        this.z = null;
        this.l = 500;
        this.u = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        this.v = new DataCommonGetVersion().setDeviceType(DeviceType.WIFI);
        this.x = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(1);
        this.y = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(2);
        this.z = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(3);
        this.k = new a(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = (TextView) findViewById(R.id.set_version_gimbal_value);
        this.e = (TextView) findViewById(R.id.set_version_blue_value);
        this.f = (TextView) findViewById(R.id.set_version_handset_value);
        this.g = (TextView) findViewById(R.id.set_version_pitch_value);
        this.h = (TextView) findViewById(R.id.set_version_roll_value);
        this.i = (TextView) findViewById(R.id.set_version_yaw_value);
        this.u.start(new d(this) {
            final /* synthetic */ DJILPFirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.k.obtainMessage(1, 7, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.k.obtainMessage(1, 8, 0).sendToTarget();
            }
        }, this.l, 3);
        this.v.start(new d(this) {
            final /* synthetic */ DJILPFirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.k.obtainMessage(2, 7, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.k.obtainMessage(2, 8, 0).sendToTarget();
            }
        }, this.l, 3);
        this.x.start(new d(this) {
            final /* synthetic */ DJILPFirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.k.obtainMessage(4, 7, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.k.obtainMessage(4, 8, 0).sendToTarget();
            }
        }, this.l, 3);
        this.y.start(new d(this) {
            final /* synthetic */ DJILPFirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.k.obtainMessage(5, 7, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.k.obtainMessage(5, 8, 0).sendToTarget();
            }
        }, this.l, 3);
        this.z.start(new d(this) {
            final /* synthetic */ DJILPFirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.k.obtainMessage(6, 7, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.k.obtainMessage(6, 8, 0).sendToTarget();
            }
        }, this.l, 3);
        a();
    }

    private void a(int i, int i2) {
        if (i == 2) {
            if (i2 == 7) {
                this.e.setText(a(this.v));
            } else {
                this.e.setText("Failed");
            }
        } else if (i == 1) {
            if (i2 == 7) {
                this.d.setText(a(this.u));
            } else {
                this.d.setText("Failed");
            }
        } else if (i == 3) {
            if (i2 == 7) {
                this.f.setText(a(this.w));
            } else {
                this.f.setText("Failed");
            }
        } else if (i == 4) {
            if (i2 == 7) {
                this.g.setText(a(this.x));
            } else {
                this.g.setText("Failed");
            }
        } else if (i == 5) {
            if (i2 == 7) {
                this.h.setText(a(this.y));
            } else {
                this.h.setText("Failed");
            }
        } else if (i != 6) {
        } else {
            if (i2 == 7) {
                this.i.setText(a(this.z));
            } else {
                this.i.setText("Failed");
            }
        }
    }

    private String a(DataCommonGetVersion dataCommonGetVersion) {
        return dataCommonGetVersion.getFirmVer(".");
    }

    private void a() {
        if (dji.logic.a.a.getInstance().b) {
            if (dji.logic.a.a.getInstance().a) {
                this.w = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM).setDeviceModel(0);
            } else {
                this.w = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM).setDeviceModel(0);
            }
            startGetHandset();
            return;
        }
        final e dataCommonGetVersion = new DataCommonGetVersion();
        dataCommonGetVersion.setDeviceType(DeviceType.find(9));
        new m(dataCommonGetVersion, new d(this) {
            final /* synthetic */ DJILPFirmwareVersionDetailView b;

            public void onSuccess(Object obj) {
                if (dataCommonGetVersion.getLoaderByte(2) == 1) {
                    this.b.w = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM).setDeviceModel(0);
                } else {
                    this.b.w = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM).setDeviceModel(0);
                }
                this.b.startGetHandset();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.b.w = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM).setDeviceModel(0);
                this.b.startGetHandset();
            }
        }).a();
    }

    public void startGetHandset() {
        this.w.start(new d(this) {
            final /* synthetic */ DJILPFirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.k.obtainMessage(3, 7, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.k.obtainMessage(3, 8, 0).sendToTarget();
            }
        }, this.l, 3);
    }
}
