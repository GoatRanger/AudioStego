package dji.phone.c;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.media.MediaRecorder;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import dji.phone.preview.DJILPPreviewActivity;
import dji.pilot.fpv.R;
import dji.pilot.phonecamera.a.b;
import dji.pilot.phonecamera.a.c;
import dji.pilot.phonecamera.e;
import dji.pilot.phonecamera.e.d;
import dji.pilot.phonecamera.g;
import dji.pilot.phonecamera.i;
import dji.pilot.phonecamera.j;

public class a {
    private static final String a = a.class.getSimpleName();
    private static b b = null;
    private static final Object c = new Object();
    private static boolean d = false;
    private static int e = -1;
    private static int f = -1;
    private static c g;
    private static Context h;
    private static d i = new d() {
        public void a(int i) {
        }

        public void b(int i) {
            new Handler().postDelayed(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 a;

                {
                    this.a = r1;
                }

                public void run() {
                    a.j();
                }
            }, 500);
        }

        public void a(e eVar) {
        }
    };

    private static void j() {
        if (((DJILPPreviewActivity) h).mTutoialPresenter != null && ((DJILPPreviewActivity) h).mTutoialPresenter.e()) {
            Log.d(a, "onDeviceOpenFailure: tutorialFinish");
            ((DJILPPreviewActivity) h).mTutoialPresenter.b();
        }
        Log.d(a, "onDeviceOpenFailure: ");
        k();
    }

    private static void k() {
        Log.d(a, "showCameraOpenFailDialog: ");
        final dji.phone.a.c cVar = new dji.phone.a.c(h, R.style.LpBaseDialog);
        cVar.a(h.getString(R.string.lp_camera_permission_denied));
        cVar.a(false);
        cVar.a(0, false);
        cVar.a(1, new OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent();
                intent.setComponent(new ComponentName("com.android.settings", "com.android.settings.Settings"));
                intent.setAction("android.intent.action.VIEW");
                a.h.startActivity(intent);
                cVar.dismiss();
                ((Activity) a.h).finish();
            }
        });
        cVar.show();
    }

    public static void a(Context context) {
        synchronized (c) {
            if (!d) {
                h = context;
                g = c.a();
                g.a(new b(h));
                f = g.s();
                e = g.o();
                b(e);
                g.a(b.m());
                d = true;
            }
        }
    }

    public static void a() {
        if (b != null) {
            b.c();
            b = null;
        }
        h = null;
        d = false;
    }

    public static boolean b() {
        return d;
    }

    private static void b(int i) {
        switch (i) {
            case 0:
                if (b != null) {
                    Log.d(a, "setModuleFromIndex: PHOTO_MODULE_INDEX mCameraModuleProxy != null");
                    b.e(0);
                    b.a(i, f, h, i);
                    return;
                }
                b = new b(new i());
                b.a(i, f, h, i);
                return;
            case 1:
                if (b != null) {
                    Log.d(a, "setModuleFromIndex: VIDEO_MODULE_INDEX mCameraModuleProxy != null");
                    b.e(1);
                    b.a(i, f, h, i);
                    return;
                }
                b = new b(new j());
                b.a(i, f, h, i);
                return;
            default:
                if (b != null) {
                    b.e(0);
                    b.a(i, f, h, i);
                    return;
                }
                b = new b(new i());
                b.a(i, f, h, i);
                return;
        }
    }

    public static void a(int i) {
        if (i != 0 && i != 1) {
            e = 0;
        } else if (i != e) {
            Log.d(a, "switchModule: currentModuleId = " + i + " mCurrentModuleId = " + e);
            e = i;
        } else {
            return;
        }
        b.c();
        g.j(e);
        f = g.s();
        b(e);
        b.a(dji.phone.j.d.getInstance().a());
        b.k();
    }

    public static dji.pilot.phonecamera.c c() throws IllegalStateException {
        dji.pilot.phonecamera.c cVar;
        synchronized (c) {
            if (d) {
                cVar = b;
            } else {
                throw new IllegalStateException("Default CameraModule haven't been made yet!");
            }
        }
        return cVar;
    }

    public static g d() {
        g iVar;
        synchronized (b.a) {
            iVar = new i();
        }
        return iVar;
    }

    public static g e() {
        g jVar;
        synchronized (b.a) {
            jVar = new j();
        }
        return jVar;
    }

    public static MediaRecorder f() {
        MediaRecorder g;
        synchronized (b.a) {
            g = ((b) c()).g();
        }
        return g;
    }

    public static int g() {
        return g.o();
    }
}
