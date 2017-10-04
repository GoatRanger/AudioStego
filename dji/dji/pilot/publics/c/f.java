package dji.pilot.publics.c;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.here.odnp.config.OdnpConfigStatic;
import dji.midware.e.h;
import dji.midware.media.DJIVideoDecoder;
import dji.midware.media.e;
import dji.midware.media.h.b.b;
import dji.thirdparty.a.c;
import java.lang.ref.WeakReference;

public class f {
    public static boolean a = false;
    private static final int b = 5000;
    private static final int c = 1;
    private static final String d = "DJIVideoDecoderController";
    private DJIVideoDecoder e = null;
    private Context f = null;
    private h g = null;
    private b h = null;
    private a i = null;

    private static final class a extends Handler {
        private final WeakReference<f> a;

        public a(f fVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(fVar);
        }

        public void handleMessage(Message message) {
            f fVar = (f) this.a.get();
            if (fVar != null) {
                switch (message.what) {
                    case 1:
                        if (fVar.e != null) {
                            e.a(f.d, "\n......Restarting by DJIVideoDecoderController......\n");
                            fVar.e.setSurface(null);
                            fVar.e.release();
                            fVar.e = null;
                            fVar.e = new DJIVideoDecoder(fVar.f, fVar.h);
                            fVar.a(fVar.g);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public f(Context context, b bVar) {
        this.f = context;
        this.h = bVar;
        if (a) {
            this.i = new a(this);
            c.a().a(this);
        }
        this.e = new DJIVideoDecoder(context, bVar);
    }

    public void a(h hVar) {
        this.g = hVar;
        if (this.e != null) {
            this.e.setRecvDataCallBack(hVar);
        }
    }

    public void a() {
        if (this.e != null) {
            this.e.resetToManager();
        }
    }

    public void a(b bVar) {
        if (this.e != null) {
            this.e.setSurface(bVar);
        }
    }

    public void b() {
        if (this.e != null) {
            this.e.release();
            this.e = null;
        }
        if (a) {
            c.a().d(this);
        }
    }

    public void onEventMainThread(DJIVideoDecoder.e eVar) {
        e.a(d, "\nReceived decodeStatus = " + eVar + "\n");
        if (eVar == DJIVideoDecoder.e.b) {
            this.i.removeMessages(1);
        }
        if (eVar == DJIVideoDecoder.e.a && !this.i.hasMessages(1)) {
            this.i.sendEmptyMessageDelayed(1, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
        }
    }
}
