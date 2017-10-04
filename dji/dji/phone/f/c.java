package dji.phone.f;

import android.os.Handler;
import android.support.v4.view.MotionEventCompat;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataOsdSetLED;
import dji.midware.util.b;
import dji.phone.d.d;
import dji.pilot.phonecamera.a.a;

public class c {
    private static final String c = "DJILPGimbleLEDController";
    final DataOsdSetLED a = new DataOsdSetLED();
    Handler b = new Handler(b.b());

    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] a = new int[a.values().length];

        static {
            try {
                a[a.b.ordinal()] = 1;
            } catch (NoSuchFieldError e) {
            }
            try {
                a[a.e.ordinal()] = 2;
            } catch (NoSuchFieldError e2) {
            }
            try {
                a[a.f.ordinal()] = 3;
            } catch (NoSuchFieldError e3) {
            }
        }
    }

    public c() {
        dji.f.a.a(this);
    }

    public void a() {
        dji.f.a.b(this);
    }

    public void onEventBackgroundThread(a aVar) {
        switch (AnonymousClass2.a[aVar.ordinal()]) {
            case 1:
                if (dji.phone.controview.b.getInstance().b() == dji.phone.controview.b.a.RECORD) {
                    this.a.a().c(1, 1, 2, 1).a(1, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16, 255).start(null);
                    return;
                }
                this.a.a().c(1, 1, 2, 1).b(1, -4, 32, 1).start(null);
                this.b.postDelayed(new Runnable(this) {
                    final /* synthetic */ c a;

                    {
                        this.a = r1;
                    }

                    public void run() {
                        this.a.b();
                    }
                }, 500);
                return;
            case 2:
                this.a.a().a(1, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16, 255).start(null);
                return;
            case 3:
                this.a.a().start(null);
                b();
                return;
            default:
                return;
        }
    }

    public void onEventMainThread(p pVar) {
        if (pVar == p.b && d.getInstance().c() == dji.phone.d.c.a.CAMERASTATE_RECORDING) {
            this.a.a().a(1, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16, 255).start(null);
        }
    }

    private void b() {
        this.a.a().b(1, -1, 32, 255).start(null);
    }

    public void onEventBackgroundThread(dji.phone.d.c.a aVar) {
        if (aVar == dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE) {
            if (d.getInstance().h() == dji.phone.d.a.b.LONGEXPOSURE) {
                this.a.a().c(1, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16, 255).start(null);
            }
        } else if (aVar == dji.phone.d.c.a.CAMERASTATE_TAKEPICTURE_PREVIEW || aVar == dji.phone.d.c.a.CAMERASTATE_RECORD_PREVIEW) {
            b();
        } else if (aVar == dji.phone.d.c.a.CAMERASTATE_RECORDING) {
            this.a.a().a(1, MotionEventCompat.ACTION_POINTER_INDEX_MASK, 16, 255).start(null);
        }
    }
}
