package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.amap.api.maps.AMapException;
import java.net.Proxy;

public class fv extends fq {
    private static fv a;
    private ga b;
    private Handler c;

    class AnonymousClass1 extends gc {
        final /* synthetic */ fw a;
        final /* synthetic */ fx b;
        final /* synthetic */ fv c;

        public void a() {
            try {
                this.c.a(this.c.b(this.a, false), this.b);
            } catch (dk e) {
                this.c.a(e, this.b);
            }
        }
    }

    static class a extends Handler {
        private a(Looper looper) {
            super(looper);
        }

        public void handleMessage(Message message) {
            try {
                switch (message.what) {
                    case 0:
                        ((fz) message.obj).b.a();
                        return;
                    case 1:
                        fz fzVar = (fz) message.obj;
                        fzVar.b.a(fzVar.a);
                        return;
                    default:
                        return;
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            th.printStackTrace();
        }
    }

    public static fv a(boolean z) {
        return a(z, 5);
    }

    private static synchronized fv a(boolean z, int i) {
        fv fvVar;
        synchronized (fv.class) {
            try {
                if (a == null) {
                    a = new fv(z, i);
                } else if (z) {
                    if (a.b == null) {
                        a.b = ga.a(i);
                    }
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            fvVar = a;
        }
        return fvVar;
    }

    private fv(boolean z, int i) {
        if (z) {
            try {
                this.b = ga.a(i);
            } catch (Throwable th) {
                ee.a(th, "NetManger", "NetManger1");
                th.printStackTrace();
                return;
            }
        }
        if (Looper.myLooper() == null) {
            this.c = new a(Looper.getMainLooper());
        } else {
            this.c = new a();
        }
    }

    public byte[] b(fw fwVar) throws dk {
        dk e;
        try {
            fy a = a(fwVar, false);
            if (a != null) {
                return a.a;
            }
            return null;
        } catch (dk e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            ee.a().b(th, "NetManager", "makeSyncPostRequest");
            e2 = new dk(AMapException.ERROR_UNKNOWN);
        }
    }

    public byte[] d(fw fwVar) throws dk {
        dk e;
        try {
            fy b = b(fwVar, false);
            if (b != null) {
                return b.a;
            }
            return null;
        } catch (dk e2) {
            throw e2;
        } catch (Throwable th) {
            e2 = new dk(AMapException.ERROR_UNKNOWN);
        }
    }

    public fy b(fw fwVar, boolean z) throws dk {
        dk e;
        try {
            Proxy proxy;
            c(fwVar);
            if (fwVar.i == null) {
                proxy = null;
            } else {
                proxy = fwVar.i;
            }
            return new fs(fwVar.g, fwVar.h, proxy, z).a(fwVar.a(), fwVar.c(), fwVar.b());
        } catch (dk e2) {
            throw e2;
        } catch (Throwable th) {
            th.printStackTrace();
            e2 = new dk(AMapException.ERROR_UNKNOWN);
        }
    }

    private void a(dk dkVar, fx fxVar) {
        fz fzVar = new fz();
        fzVar.a = dkVar;
        fzVar.b = fxVar;
        Message obtain = Message.obtain();
        obtain.obj = fzVar;
        obtain.what = 1;
        this.c.sendMessage(obtain);
    }

    private void a(fy fyVar, fx fxVar) {
        fxVar.a(fyVar.b, fyVar.a);
        fz fzVar = new fz();
        fzVar.b = fxVar;
        Message obtain = Message.obtain();
        obtain.obj = fzVar;
        obtain.what = 0;
        this.c.sendMessage(obtain);
    }
}
