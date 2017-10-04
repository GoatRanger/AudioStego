package dji.pilot.longan;

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

public class a {
    public static boolean a = false;
    private static final int c = 5000;
    private static final int d = 1;
    private static final String e = "LonganVideoDecoderController";
    public DJIVideoDecoder b = null;
    private Context f = null;
    private h g = null;
    private b h = null;
    private a i = null;

    private static final class a extends Handler {
        private final WeakReference<a> a;

        public a(a aVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(aVar);
        }

        public void handleMessage(Message message) {
            a aVar = (a) this.a.get();
            if (aVar != null) {
                switch (message.what) {
                    case 1:
                        if (aVar.b != null) {
                            e.a(a.e, "\n......Restarting by DJIVideoDecoderController......\n");
                            aVar.b.setSurface(null);
                            aVar.b.release();
                            aVar.b = null;
                            aVar.b = new DJIVideoDecoder(aVar.f, true, aVar.h);
                            aVar.a(aVar.g);
                            return;
                        }
                        return;
                    default:
                        return;
                }
            }
        }
    }

    public a(Context context, b bVar) {
        this.f = context;
        this.h = bVar;
        if (a) {
            this.i = new a(this);
            c.a().a(this);
        }
        this.b = new DJIVideoDecoder(context, true, bVar);
    }

    public void a(h hVar) {
        this.g = hVar;
        if (this.b != null) {
            this.b.setRecvDataCallBack(hVar);
        }
    }

    public void a() {
        if (this.b != null) {
            this.b.resetToManager();
        }
    }

    public void a(b bVar) {
        if (this.b != null) {
            this.b.setSurface(bVar);
        }
    }

    public void b() {
        if (this.b != null) {
            this.b.release();
            this.b = null;
        }
        if (a) {
            c.a().d(this);
        }
    }

    public void onEventMainThread(DJIVideoDecoder.e eVar) {
        e.a(e, "\nReceived decodeStatus = " + eVar + "\n");
        if (eVar == DJIVideoDecoder.e.b) {
            this.i.removeMessages(1);
        }
        if (eVar == DJIVideoDecoder.e.a && !this.i.hasMessages(1)) {
            this.i.sendEmptyMessageDelayed(1, OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL);
        }
    }
}
