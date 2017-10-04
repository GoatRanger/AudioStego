package dji.phone.pano;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.Uri;
import android.support.v4.app.NotificationManagerCompat;
import dji.gs.c.e;
import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.model.P3.DataCameraGetPushShutterCmd;
import dji.midware.data.model.P3.DataGimbalGetPushAbnormalStatus;
import dji.midware.data.model.P3.DataGimbalSetAngle;
import dji.phone.d.d;
import dji.phone.k.b;
import dji.pilot.fpv.R;
import dji.pilot.set.g;
import java.io.File;

public class a implements dji.phone.pano.DJILPPanoDisplayer.a, b, dji.phone.pano.g.a {
    private static final String a = "DJILPPanoPresenter";
    private static final String b = "OSMO_PHONE_TAKE_PHOTO";
    private static f c = f.NONE;
    private static final int i = 400;
    private static final int j = 225;
    private g d;
    private c e;
    private Activity f;
    private DJILPPanoDisplayer g;
    private a h = new a();

    private class a extends BroadcastReceiver {
        final /* synthetic */ a a;

        private a(a aVar) {
            this.a = aVar;
        }

        public void onReceive(Context context, Intent intent) {
            DJILogHelper.getInstance().LOGD(a.a, "DJIMethod : onReceive", false, true);
            if (this.a.d != null) {
                this.a.d.a(intent.getStringExtra("path"));
            }
        }
    }

    public a(Activity activity, DJILPPanoDisplayer dJILPPanoDisplayer) {
        this.f = activity;
        this.g = dJILPPanoDisplayer;
        dji.f.a.a(this);
    }

    public void b() {
        this.f = null;
        this.g = null;
        dji.f.a.b(this);
    }

    private void i() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("OSMO_PHONE_TAKE_PHOTO");
        this.f.registerReceiver(this.h, intentFilter);
    }

    private void j() {
        this.f.unregisterReceiver(this.h);
    }

    public void a(c cVar) {
        this.e = cVar;
    }

    public Activity c() {
        return this.f;
    }

    public static f d() {
        return c;
    }

    public void a(d dVar, boolean z) {
        int k = k();
        if (k == 0) {
            if (this.g.isShown()) {
                this.g.go();
            }
            d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE, true);
            this.d = new g(this, dVar, b(dVar, z), dji.pilot.set.a.b(this.f, g.o, 2));
            this.d.a((dji.phone.pano.g.a) this);
            this.d.a();
        } else if (k == 5) {
            e();
        } else {
            DJILogHelper.getInstance().LOGE(a, "DJIMethod : startPano error" + k, true, false);
        }
    }

    private e[] b(d dVar, boolean z) {
        switch (dVar) {
            case P_180:
                return new e[]{new e(-800, 0), new e(-400, 0), new e(0, 0), new e(400, 0), new e(e.g, 0)};
            case P_330:
                return new e[]{new e(-1400, 0), new e(NotificationManagerCompat.IMPORTANCE_UNSPECIFIED, 0), new e(-600, 0), new e(-200, 0), new e(200, 0), new e(600, 0), new e(1000, 0), new e(1400, 0)};
            case P_WIDE:
                return new e[]{new e(-400, 225), new e(0, 225), new e(400, 225), new e(-400, 0), new e(0, 0), new e(400, 0), new e(-400, -225), new e(0, -225), new e(400, -225)};
            default:
                return null;
        }
    }

    public int e() {
        if (c == f.PANO_TAKING) {
            this.d.b();
            this.d = null;
        }
        d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW, true);
        return 0;
    }

    private int k() {
        if (ServiceManager.getInstance().isConnected()) {
            dji.phone.f.d b = dji.phone.f.a.getInstance().b();
            if (b == dji.phone.f.d.WORK_FINE_NORMAL) {
                if (!DataGimbalGetPushAbnormalStatus.getInstance().isPanoReady()) {
                    b.showLong(this.f.getResources().getString(R.string.lp_pano_upright_tip));
                    return 2;
                }
            } else if (b == dji.phone.f.d.WORK_FINE_TRACK) {
                b.showLong(this.f.getResources().getString(R.string.lp_pano_tracking_tip));
                return 3;
            } else if (b == dji.phone.f.d.SLEEP) {
                b.showLong(this.f.getResources().getString(R.string.lp_pano_sleep_tip));
                return 4;
            } else if (b == dji.phone.f.d.WORK_FINE_PANORAMA) {
                return 5;
            }
            return 0;
        }
        b.showLong(this.f.getResources().getString(R.string.lp_ble_device_corrent));
        return 1;
    }

    public void f() {
        c = f.PANO_TAKING;
        if (this.e != null) {
            this.f.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.e.g();
                }
            });
        }
        i();
        dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_PANORAMA, true);
    }

    public void a(final int i, final int i2, final int i3) {
        DJILogHelper.getInstance().LOGD(a, "DJIMethod : onTaskProgressUpgrade" + i, false, true);
        if (i == 1) {
            c = f.PANO_TAKING;
        } else if (i == 2) {
            c = f.PANO_STITCHING;
            m();
            this.f.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.g.viewToStitching();
                }
            });
        }
        if (this.e != null) {
            this.f.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a d;

                public void run() {
                    this.d.e.a(i, i2, i3);
                }
            });
        }
    }

    public void a(final String str) {
        DJILogHelper.getInstance().LOGD(a, "DJIMethod : onTaskSucceed" + str, false, true);
        c = f.NONE;
        if (this.e != null) {
            this.f.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.e.a(0, str);
                }
            });
        }
        this.f.runOnUiThread(new Runnable(this) {
            final /* synthetic */ a b;

            public void run() {
                d.getInstance().a(dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PANO_PREVIEW, true);
                this.b.g.setVisibility(0);
                this.b.g.showPanoResult(str);
                a.c = f.PANO_DISPLAYING;
                this.b.b(str);
            }
        });
        l();
    }

    public void a(final int i) {
        DJILogHelper.getInstance().LOGD(a, "DJIMethod : onTaskFailed" + i, false, true);
        c = f.NONE;
        m();
        if (this.e != null) {
            this.f.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a b;

                public void run() {
                    this.b.e.a(1, null);
                    this.b.g.go();
                    b.showShort(R.string.lp_pano_failed_txt);
                    DJILogHelper.getInstance().LOGE(a.a, "DJIMethod : run (151)pano failed:" + i, true, false);
                }
            });
        }
        l();
    }

    public void g() {
        m();
        c = f.NONE;
        if (this.e != null) {
            this.f.runOnUiThread(new Runnable(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void run() {
                    this.a.e.a(2, null);
                }
            });
        }
        l();
    }

    private void l() {
        j();
        dji.phone.f.a.getInstance().a(dji.phone.f.d.WORK_FINE_PANORAMA, false);
    }

    private void m() {
        DataGimbalSetAngle.getInstance().a(0).b(0).d(50).e(2).a(2500, 3, null);
    }

    public void onEventMainThread(DataGimbalGetPushAbnormalStatus dataGimbalGetPushAbnormalStatus) {
        if (c == f.PANO_TAKING && !dataGimbalGetPushAbnormalStatus.isPanoReady()) {
            DJILogHelper.getInstance().LOGD(a, "DJIMethod : onEventMainThread (193)gimbal not vertical", false, true);
            b.showLong(this.f.getResources().getString(R.string.lp_pano_upright_tip));
            e();
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushShutterCmd dataCameraGetPushShutterCmd) {
        if (dataCameraGetPushShutterCmd.getShutterType() == 2) {
            e();
        }
    }

    public void b(String str) {
        Intent intent = new Intent("android.intent.action.MEDIA_SCANNER_SCAN_FILE");
        intent.setData(Uri.fromFile(new File(str)));
        this.f.sendBroadcast(intent);
        try {
            Class.forName("dji.pilot.support.longan.DJISupportLongan").getMethod("reflectNotifyNewMedia", new Class[]{String.class}).invoke(null, new Object[]{str});
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void a() {
        c = f.NONE;
    }

    public boolean h() {
        if (c == f.PANO_STITCHING) {
            return true;
        }
        if (c == f.PANO_DISPLAYING) {
            this.g.go();
            return true;
        } else if (c != f.PANO_TAKING) {
            return false;
        } else {
            return true;
        }
    }
}
