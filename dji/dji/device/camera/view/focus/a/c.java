package dji.device.camera.view.focus.a;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.os.SystemClock;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Toast;
import dji.device.common.DJIUIEventManagerLongan.e;
import dji.device.common.b;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo.CameraType;
import dji.midware.data.model.P3.DataCommonGetVersion;
import dji.midware.data.model.P3.DataRcGetPushFollowFocus2;
import dji.midware.e.d;
import dji.pilot.fpv.R;
import dji.pilot.set.g;
import java.util.Timer;
import java.util.TimerTask;

public class c extends Handler implements b {
    private static c C = null;
    private TimerTask A;
    private Toast B;
    protected CameraType m = DataCameraGetPushStateInfo.getInstance().getCameraType();
    d n = new d(this) {
        final /* synthetic */ c a;

        {
            this.a = r1;
        }

        public void onSuccess(Object obj) {
            Object firmVer = ((DataCommonGetVersion) obj).getFirmVer(".");
            Log.d(this.a.o, "getCameraVersion onSuccess: version = " + firmVer);
            if (TextUtils.isEmpty(firmVer)) {
                this.a.sendEmptyMessageDelayed(102, 500);
                return;
            }
            this.a.e();
            this.a.x = Long.parseLong(firmVer.replace(".", ""));
            Log.d(this.a.o, "onSuccess: mGetVersion = " + this.a.x);
        }

        public void onFailure(a aVar) {
            Log.d(this.a.o, "getCameraVersion onFailure: ");
            this.a.sendEmptyMessageDelayed(102, 500);
        }
    };
    private final String o = c.class.getSimpleName();
    private Context p;
    private int q = 0;
    private boolean r = true;
    private boolean s = false;
    private boolean t = false;
    private boolean u = false;
    private int v = 0;
    private int w = -1;
    private long x = 0;
    private boolean y = false;
    private Timer z;

    static /* synthetic */ class AnonymousClass8 {
        static final /* synthetic */ int[] a = new int[CameraType.values().length];

        static {
            try {
                a[CameraType.DJICameraTypeFC550.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[CameraType.DJICameraTypeFC550Raw.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
        }
    }

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (C == null) {
                C = new c();
            }
            cVar = C;
        }
        return cVar;
    }

    public void a(Context context) {
        this.p = context;
        dji.f.a.a(this);
        this.z = new Timer(true);
        this.A = new TimerTask(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                if ((SystemClock.uptimeMillis() - ((long) this.a.q)) / 1000 >= 2 && this.a.s) {
                    this.a.b(-1);
                    this.a.sendEmptyMessage(100);
                }
            }
        };
        this.z.schedule(this.A, 0, 500);
        c();
    }

    private void c() {
        Log.d(this.o, "getCameraVersion: ");
        b.getInstance().a(0, this.n);
    }

    public void a() {
        Log.d(this.o, "dispose: ");
        dji.f.a.b(this);
        this.p = null;
        this.s = false;
        this.t = false;
        this.u = false;
        this.r = true;
        this.y = false;
        this.v = 0;
        if (this.z != null) {
            this.z.cancel();
            this.z = null;
        }
        if (this.A != null) {
            this.A.cancel();
            this.A = null;
            this.B = null;
        }
    }

    public void onEventBackgroundThread(DataRcGetPushFollowFocus2 dataRcGetPushFollowFocus2) {
        this.q = (int) SystemClock.uptimeMillis();
        b(dataRcGetPushFollowFocus2.getCtrlType().value());
        if (!this.s && this.x > 0) {
            this.s = true;
            sendEmptyMessage(101);
            Log.d(this.o, "onEventBackgroundThread: MSG_FOLLOW_FOCUS_DEVICE_PLUG_IN");
        }
    }

    private synchronized boolean d() {
        return this.y;
    }

    private synchronized void e() {
        boolean z = true;
        synchronized (this) {
            Log.d(this.o, "updateFocusHandwheelAvailability: supportCameraFocusHandwheel = " + dji.logic.f.b.g(this.m));
            if (dji.logic.f.b.g(this.m)) {
                boolean z2;
                Log.d(this.o, "updateFocusHandwheelAvailability:CameraType = " + this.m.name() + " version = " + this.x);
                switch (AnonymousClass8.a[this.m.ordinal()]) {
                    case 1:
                        if (this.x < b.h) {
                            z2 = false;
                            break;
                        } else {
                            z2 = true;
                            break;
                        }
                    case 2:
                        if (this.x < b.i) {
                            z2 = false;
                            break;
                        } else {
                            z2 = true;
                            break;
                        }
                    default:
                        z2 = false;
                        break;
                }
                Log.d(this.o, "updateFocusHandwheelAvailability: isVersionValid = " + z2);
                if (!(dji.logic.f.b.g(this.m) && z2)) {
                    z = false;
                }
                this.y = z;
            } else {
                this.y = false;
            }
        }
    }

    public void a(int i) {
        CharSequence charSequence;
        switch (i) {
            case -1:
                charSequence = "";
                break;
            case 0:
                if ((this.v & 1) == 0) {
                    charSequence = this.p.getString(R.string.longan_focus_handwheel_change_aperture_allowed);
                    this.v = 1;
                    break;
                }
                return;
            case 1:
                if ((this.v & 2) == 0) {
                    charSequence = this.p.getString(R.string.longan_focus_handwheel_change_focus_allowed);
                    this.v = 2;
                    break;
                }
                return;
            case 2:
                if ((this.v & 4) == 0) {
                    charSequence = this.p.getString(R.string.longan_focus_handwheel_change_zoom_allowed);
                    this.v = 4;
                    break;
                }
                return;
            default:
                charSequence = "";
                break;
        }
        if (!TextUtils.isEmpty(charSequence)) {
            sendMessage(obtainMessage(200, charSequence));
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private synchronized void b(int r4) {
        /*
        r3 = this;
        monitor-enter(r3);
        r0 = r3.w;	 Catch:{ all -> 0x0025 }
        if (r0 != r4) goto L_0x0007;
    L_0x0005:
        monitor-exit(r3);
        return;
    L_0x0007:
        switch(r4) {
            case -1: goto L_0x004f;
            case 0: goto L_0x0028;
            case 1: goto L_0x0035;
            case 2: goto L_0x0042;
            default: goto L_0x000a;
        };
    L_0x000a:
        r0 = r3.o;	 Catch:{ all -> 0x0025 }
        r1 = new java.lang.StringBuilder;	 Catch:{ all -> 0x0025 }
        r1.<init>();	 Catch:{ all -> 0x0025 }
        r2 = "setFocusHandwheelType: ";
        r1 = r1.append(r2);	 Catch:{ all -> 0x0025 }
        r2 = r3.w;	 Catch:{ all -> 0x0025 }
        r1 = r1.append(r2);	 Catch:{ all -> 0x0025 }
        r1 = r1.toString();	 Catch:{ all -> 0x0025 }
        android.util.Log.d(r0, r1);	 Catch:{ all -> 0x0025 }
        goto L_0x0005;
    L_0x0025:
        r0 = move-exception;
        monitor-exit(r3);
        throw r0;
    L_0x0028:
        r0 = 0;
        r3.w = r0;	 Catch:{ all -> 0x0025 }
        r0 = dji.thirdparty.a.c.a();	 Catch:{ all -> 0x0025 }
        r1 = dji.device.camera.view.focus.a.b.b.APERTURE;	 Catch:{ all -> 0x0025 }
        r0.e(r1);	 Catch:{ all -> 0x0025 }
        goto L_0x000a;
    L_0x0035:
        r0 = 1;
        r3.w = r0;	 Catch:{ all -> 0x0025 }
        r0 = dji.thirdparty.a.c.a();	 Catch:{ all -> 0x0025 }
        r1 = dji.device.camera.view.focus.a.b.b.FOCUS;	 Catch:{ all -> 0x0025 }
        r0.e(r1);	 Catch:{ all -> 0x0025 }
        goto L_0x000a;
    L_0x0042:
        r0 = 2;
        r3.w = r0;	 Catch:{ all -> 0x0025 }
        r0 = dji.thirdparty.a.c.a();	 Catch:{ all -> 0x0025 }
        r1 = dji.device.camera.view.focus.a.b.b.ZOOM;	 Catch:{ all -> 0x0025 }
        r0.e(r1);	 Catch:{ all -> 0x0025 }
        goto L_0x000a;
    L_0x004f:
        r0 = -1;
        r3.w = r0;	 Catch:{ all -> 0x0025 }
        r0 = dji.thirdparty.a.c.a();	 Catch:{ all -> 0x0025 }
        r1 = dji.device.camera.view.focus.a.b.a.hideUI;	 Catch:{ all -> 0x0025 }
        r0.e(r1);	 Catch:{ all -> 0x0025 }
        goto L_0x000a;
        */
        throw new UnsupportedOperationException("Method not decompiled: dji.device.camera.view.focus.a.c.b(int):void");
    }

    public synchronized int b() {
        return this.w;
    }

    public void onEventBackgroundThread(e eVar) {
        if (eVar == e.TUTORIAL_FINISHED) {
            this.u = true;
        }
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        CameraType cameraType = dataCameraGetPushStateInfo.getCameraType();
        Log.d(this.o, "onEventBackgroundThread: old type = " + this.m.name() + " new type = " + cameraType.name());
        if (cameraType != this.m) {
            this.m = cameraType;
            e();
            if (d()) {
                if (this.s) {
                    if (!(dji.pilot.set.a.c(this.p) || this.t || this.r)) {
                        this.t = true;
                        post(new Runnable(this) {
                            final /* synthetic */ c a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.f();
                            }
                        });
                    }
                    dji.thirdparty.a.c.a().e(b.a.showUI);
                }
            } else if (this.s) {
                b(-1);
            }
        }
    }

    private void f() {
        if (dji.pilot.set.a.d(this.p) || this.u) {
            final a aVar = new a(this.p, R.style.LpBaseDialog);
            switch (this.w) {
                case 0:
                    aVar.setTitle(R.string.longan_follow_focus_Aperture);
                    break;
                case 1:
                    aVar.setTitle(R.string.longan_follow_focus_Focus);
                    break;
                case 2:
                    aVar.setTitle(R.string.longan_follow_focus_Zoom);
                    break;
            }
            aVar.a(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    aVar.dismiss();
                    this.b.t = false;
                }
            });
            aVar.b(new OnClickListener(this) {
                final /* synthetic */ c b;

                public void onClick(View view) {
                    aVar.dismiss();
                    dji.pilot.set.a.a(this.b.p, g.u, true);
                    this.b.t = false;
                }
            });
            aVar.show();
            return;
        }
        postDelayed(new Runnable(this) {
            final /* synthetic */ c a;

            {
                this.a = r1;
            }

            public void run() {
                this.a.f();
            }
        }, 500);
    }

    public void handleMessage(Message message) {
        switch (message.what) {
            case 100:
                Log.d(this.o, "handleMessage: MSG_FOLLOW_FOCUS_DEVICE_PLUG_OUT");
                dji.thirdparty.a.c.a().e(b.a.hideUI);
                this.r = false;
                this.s = false;
                return;
            case 101:
                e();
                if (d()) {
                    Log.d(this.o, "handleMessage: MSG_FOLLOW_FOCUS_DEVICE_PLUG_IN mDeviceNotHotPlugIn = " + this.r);
                    if (!(dji.pilot.set.a.c(this.p) || this.t || this.r)) {
                        this.t = true;
                        post(new Runnable(this) {
                            final /* synthetic */ c a;

                            {
                                this.a = r1;
                            }

                            public void run() {
                                this.a.f();
                            }
                        });
                    }
                    dji.thirdparty.a.c.a().e(b.a.showUI);
                    return;
                }
                return;
            case 102:
                c();
                return;
            case 200:
                String str = (String) message.obj;
                if (this.B != null) {
                    this.B.cancel();
                }
                this.B = Toast.makeText(this.p, str, 0);
                this.B.setGravity(17, 0, 0);
                this.B.show();
                return;
            default:
                return;
        }
    }
}
