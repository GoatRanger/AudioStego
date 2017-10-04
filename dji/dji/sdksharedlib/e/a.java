package dji.sdksharedlib.e;

import android.os.Handler;
import android.os.Message;
import dji.common.error.DJIError;
import dji.common.util.CallbackUtils;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushCommon.FLYC_STATE;
import dji.sdksharedlib.DJISDKCache;
import dji.sdksharedlib.b.c;
import dji.sdksharedlib.b.e;
import dji.sdksharedlib.c.d;
import dji.sdksharedlib.hardware.abstractions.b;

public class a {
    private static final String c = "DJICompletionHelper";
    private static a d;
    private static final int g = 0;
    private static final c h = new dji.sdksharedlib.b.c.a().b(e.a).d(e.W).a();
    final d a = new d(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void onValueChange(c cVar, dji.sdksharedlib.d.a aVar, dji.sdksharedlib.d.a aVar2) {
            if (aVar2.f()) {
                this.a.f = DataOsdGetPushCommon.getInstance().getFlycState();
                if (this.a.e.equals(FLYC_STATE.AutoTakeoff) && this.a.f.equals(FLYC_STATE.GPS_Atti)) {
                    this.a.j.a(null);
                }
                this.a.e = this.a.f;
            }
        }
    };
    final Runnable b = new Runnable(this) {
        final /* synthetic */ a a;

        {
            this.a = r1;
        }

        public void run() {
            this.a.j.a(DJIError.COMMON_TIMEOUT);
        }
    };
    private FLYC_STATE e = FLYC_STATE.GoHome;
    private FLYC_STATE f;
    private Handler i = new Handler(this, b.a()) {
        final /* synthetic */ a a;

        public void handleMessage(Message message) {
            switch (message.what) {
                case 0:
                    DJISDKCache.getInstance().stopListening((d) message.obj);
                    return;
                default:
                    return;
            }
        }
    };
    private a j;
    private a k;

    private class a implements b.e {
        final /* synthetic */ a a;
        private b.e b;
        private Runnable c;
        private d d;

        public a(a aVar, b.e eVar, Runnable runnable, d dVar) {
            this.a = aVar;
            this.b = eVar;
            this.c = runnable;
            this.d = dVar;
        }

        public synchronized void a(Object obj) {
            CallbackUtils.onSuccess(this.b, null);
            DJISDKCache.getInstance().stopListening(this.d);
            this.a.i.removeCallbacks(this.c);
        }

        public synchronized void a(DJIError dJIError) {
            CallbackUtils.onFailure(this.b, DJIError.COMMON_TIMEOUT);
            DJISDKCache.getInstance().stopListening(this.d);
        }
    }

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (d == null) {
                d = new a();
            }
            aVar = d;
        }
        return aVar;
    }

    public synchronized void a(b.e eVar) {
        this.j = new a(this, eVar, this.b, this.a);
        this.i.postDelayed(this.b, 8000);
        DJISDKCache.getInstance().startListeningForUpdates(h, this.a, false);
    }

    public synchronized void b(b.e eVar) {
        CallbackUtils.onSuccess(eVar, null);
        DJISDKCache.getInstance().stopListening(this.a);
        this.i.removeCallbacks(this.b);
    }
}
