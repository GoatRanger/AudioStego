package dji.pilot.set.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.model.P3.DataCameraGetPushRawParams;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.e.d;
import dji.pilot.set.R;
import dji.pilot.set.view.a.a.b;
import java.lang.ref.WeakReference;

public class FirmwareVersionDetailView extends LinearLayout {
    private static final int A = 17;
    private static final int B = 18;
    private static final int C = 19;
    public static final String a = ".";
    static final String o = "Failed";
    private static final int q = 1;
    private static final int r = 2;
    private static final int s = 3;
    private static final int t = 4;
    private static final int u = 5;
    private static final int v = 6;
    private static final int w = 7;
    private static final int x = 8;
    private static final int y = 9;
    private static final int z = 16;
    private DataCommonGetVersion D;
    private DataCommonGetVersion E;
    private DataCommonGetVersion F;
    private DataCommonGetVersion G;
    private DataCommonGetVersion H;
    private DataCommonGetVersion I;
    private DataCommonGetVersion J;
    private DataCommonGetVersion K;
    private DataCommonGetVersion L;
    private DataCommonGetVersion M;
    private DataCommonGetVersion N;
    dji.pilot.set.view.a.a b;
    b c;
    TextView d;
    TextView e;
    TextView f;
    TextView g;
    TextView h;
    TextView i;
    TextView j;
    TextView k;
    TextView l;
    TextView m;
    TextView n;
    a p;

    private static final class a extends Handler {
        private final WeakReference<FirmwareVersionDetailView> a;

        public a(FirmwareVersionDetailView firmwareVersionDetailView) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(firmwareVersionDetailView);
        }

        public void handleMessage(Message message) {
            FirmwareVersionDetailView firmwareVersionDetailView = (FirmwareVersionDetailView) this.a.get();
            if (firmwareVersionDetailView != null) {
                firmwareVersionDetailView.a(message.what, message.arg1);
            }
        }
    }

    public FirmwareVersionDetailView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.c = null;
        this.D = null;
        this.E = null;
        this.F = null;
        this.G = null;
        this.H = null;
        this.I = null;
        this.J = null;
        this.K = null;
        this.L = null;
        this.M = null;
        this.N = null;
        this.D = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA);
        this.E = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL);
        this.F = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(1);
        this.G = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(2);
        this.H = new DataCommonGetVersion().setDeviceType(DeviceType.GIMBAL).setDeviceModel(3);
        this.I = new DataCommonGetVersion().setDeviceType(DeviceType.WIFI);
        this.J = new DataCommonGetVersion().setDeviceType(DeviceType.DM368);
        this.K = new DataCommonGetVersion().setDeviceType(DeviceType.OFDM);
        this.L = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA).setDeviceModel(6);
        this.M = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA).setDeviceModel(4);
        this.N = new DataCommonGetVersion().setDeviceType(DeviceType.CAMERA).setDeviceModel(1);
        this.p = new a(this);
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.d = (TextView) findViewById(R.id.set_version_camera_value);
        this.e = (TextView) findViewById(R.id.set_version_gimbal_value);
        this.f = (TextView) findViewById(R.id.set_version_368_value);
        this.g = (TextView) findViewById(R.id.set_version_1765_value);
        this.h = (TextView) findViewById(R.id.set_version_wifi_value);
        this.l = (TextView) findViewById(R.id.set_version_gimbal_model1_value);
        this.m = (TextView) findViewById(R.id.set_version_gimbal_model2_value);
        this.n = (TextView) findViewById(R.id.set_version_gimbal_model3_value);
        this.D.start(new d(this) {
            final /* synthetic */ FirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.p.obtainMessage(1, 6, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.p.obtainMessage(1, 7, 0).sendToTarget();
            }
        }, 200, 3);
        if (DataCameraGetPushRawParams.getInstance().isGetted()) {
            findViewById(R.id.set_version_fpga_ly).setVisibility(0);
            this.i = (TextView) findViewById(R.id.set_version_fpga_value);
            this.L.start(new d(this) {
                final /* synthetic */ FirmwareVersionDetailView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.obtainMessage(8, 6, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.obtainMessage(8, 7, 0).sendToTarget();
                }
            }, 200, 3);
        }
        if (dji.logic.f.b.o(null) || DataCameraGetPushRawParams.getInstance().isGetted()) {
            findViewById(R.id.set_version_camera04_ly).setVisibility(0);
            this.j = (TextView) findViewById(R.id.set_version_cameramodel4_value);
            this.M.start(new d(this) {
                final /* synthetic */ FirmwareVersionDetailView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.obtainMessage(9, 6, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.obtainMessage(9, 7, 0).sendToTarget();
                }
            }, 200, 3);
        }
        if (dji.logic.f.b.o(null)) {
            findViewById(R.id.set_version_camera01_ly).setVisibility(0);
            this.k = (TextView) findViewById(R.id.set_version_cameramodel1_value);
            this.N.start(new d(this) {
                final /* synthetic */ FirmwareVersionDetailView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.obtainMessage(16, 6, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.obtainMessage(16, 7, 0).sendToTarget();
                }
            }, 200, 3);
        }
        if (dji.logic.f.d.e(null)) {
            findViewById(R.id.set_version_368_ly).setVisibility(8);
        }
        this.E.start(new d(this) {
            final /* synthetic */ FirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.p.obtainMessage(2, 6, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.p.obtainMessage(2, 7, 0).sendToTarget();
            }
        }, 200, 3);
        if (dji.logic.f.d.j(null)) {
            findViewById(R.id.set_version_gimbal1_ly).setVisibility(0);
            findViewById(R.id.set_version_gimbal2_ly).setVisibility(0);
            findViewById(R.id.set_version_gimbal3_ly).setVisibility(0);
            this.F.start(new d(this) {
                final /* synthetic */ FirmwareVersionDetailView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.obtainMessage(17, 6, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.obtainMessage(17, 7, 0).sendToTarget();
                }
            }, 200, 3);
            this.G.start(new d(this) {
                final /* synthetic */ FirmwareVersionDetailView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.obtainMessage(18, 6, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.obtainMessage(18, 7, 0).sendToTarget();
                }
            }, 200, 3);
            this.H.start(new d(this) {
                final /* synthetic */ FirmwareVersionDetailView a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    this.a.p.obtainMessage(19, 6, 0).sendToTarget();
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    this.a.p.obtainMessage(19, 7, 0).sendToTarget();
                }
            }, 200, 3);
        }
        this.I.start(new d(this) {
            final /* synthetic */ FirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.p.obtainMessage(3, 6, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.p.obtainMessage(3, 7, 0).sendToTarget();
            }
        }, 200, 3);
        this.J.start(new d(this) {
            final /* synthetic */ FirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.p.obtainMessage(4, 6, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.p.obtainMessage(4, 7, 0).sendToTarget();
            }
        }, 200, 3);
        this.K.start(new d(this) {
            final /* synthetic */ FirmwareVersionDetailView a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.p.obtainMessage(5, 6, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.p.obtainMessage(5, 7, 0).sendToTarget();
            }
        }, 200, 3);
    }

    private void a(int i, int i2) {
        if (i == 1) {
            if (i2 == 6) {
                this.d.setText(a(this.D));
            } else {
                this.d.setText("Failed");
            }
        } else if (i == 2) {
            if (i2 == 6) {
                this.e.setText(a(this.E));
            } else {
                this.e.setText("Failed");
            }
        } else if (i == 17) {
            if (i2 == 6) {
                this.l.setText(a(this.F));
            } else {
                this.l.setText("Failed");
            }
        } else if (i == 18) {
            if (i2 == 6) {
                this.m.setText(a(this.G));
            } else {
                this.m.setText("Failed");
            }
        } else if (i == 19) {
            if (i2 == 6) {
                this.n.setText(a(this.H));
            } else {
                this.n.setText("Failed");
            }
        } else if (i == 5) {
            if (i2 == 6) {
                this.g.setText(a(this.K));
            } else {
                this.g.setText("Failed");
            }
        } else if (i == 4) {
            if (i2 == 6) {
                this.f.setText(a(this.J));
            } else {
                this.f.setText("Failed");
            }
        } else if (i == 3) {
            if (i2 == 6) {
                this.h.setText(a(this.I));
            } else {
                this.h.setText("Failed");
            }
        } else if (i == 8) {
            if (i2 == 6) {
                this.i.setText(a(this.L));
            } else {
                this.i.setText("Failed");
            }
        } else if (i == 9) {
            if (i2 == 6) {
                this.j.setText(a(this.M));
            } else {
                this.j.setText("Failed");
            }
        } else if (i != 16) {
        } else {
            if (i2 == 6) {
                this.k.setText(a(this.N));
            } else {
                this.k.setText("Failed");
            }
        }
    }

    private String a(DataCommonGetVersion dataCommonGetVersion) {
        return dataCommonGetVersion.getFirmVer(".");
    }

    public void onEventMainThread(DataCommonGetVersion dataCommonGetVersion) {
        CharSequence firmVerSimple = dataCommonGetVersion.getFirmVerSimple(".");
        if (dataCommonGetVersion.getDeviceType() == DeviceType.CAMERA) {
            this.d.setText(firmVerSimple);
        } else if (dataCommonGetVersion.getDeviceType() == DeviceType.GIMBAL) {
            this.e.setText(firmVerSimple);
        } else if (dataCommonGetVersion.getDeviceType() == DeviceType.OFDM) {
            this.g.setText(firmVerSimple);
        } else if (dataCommonGetVersion.getDeviceType() == DeviceType.WIFI) {
            this.h.setText(firmVerSimple);
        } else if (dataCommonGetVersion.getDeviceType() == DeviceType.DM368) {
            this.f.setText(firmVerSimple);
        }
    }
}
