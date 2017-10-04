package dji.logic.h;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import com.alipay.sdk.j.i;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataWifiRequestPushSnr;
import dji.midware.e.d;
import dji.thirdparty.a.c;

public class a {
    private static final String a = a.class.getSimpleName();
    private static final boolean b = true;
    private static final int c = 4096;
    private static final int d = 4097;
    private static final long e = 100;
    private ProductType f;
    private volatile boolean g;
    private final Handler h;

    private static final class a {
        private static final a a = new a();

        private a() {
        }
    }

    public static a getInstance() {
        return a.a;
    }

    public void a(boolean z) {
        this.g = z;
        if (z) {
            c();
            b();
        }
    }

    public void onEventBackgroundThread(ProductType productType) {
        if (productType != this.f) {
            this.f = productType;
            c();
            b();
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (p.b == pVar) {
            c();
        } else {
            this.h.removeMessages(4096);
        }
    }

    public void onEventBackgroundThread(o oVar) {
        if (o.b == oVar) {
            b();
        } else {
            this.h.removeMessages(4097);
        }
    }

    private void b() {
        DJILogHelper.getInstance().LOGD(a, "request wifi snr push-" + this.g + i.b + ServiceManager.getInstance().isConnected() + i.b + this.f + i.b, false, true);
        if (!this.g || !ServiceManager.getInstance().isRemoteOK()) {
            return;
        }
        if (ProductType.P34K == this.f || ProductType.litchiC == this.f) {
            DJILogHelper.getInstance().LOGD(a, "request wifi snr push start", false, true);
            new DataWifiRequestPushSnr().a(true).a(DeviceType.WIFI).start(new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD(a.a, "request wifi snr push success", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (dji.midware.data.config.P3.a.a == aVar) {
                        this.a.h.sendEmptyMessageDelayed(4097, a.e);
                    }
                    DJILogHelper.getInstance().LOGD(a.a, "request wifi snr push fail-" + aVar, false, true);
                }
            });
        }
    }

    private void c() {
        DJILogHelper.getInstance().LOGD(a, "request wifi-g snr push-" + this.g + i.b + ServiceManager.getInstance().isConnected() + i.b + this.f + i.b, false, true);
        if (!this.g || !ServiceManager.getInstance().isConnected()) {
            return;
        }
        if (ProductType.P34K == this.f || ProductType.litchiC == this.f) {
            DJILogHelper.getInstance().LOGD(a, "request wifi-g snr push start", false, true);
            new DataWifiRequestPushSnr().a(true).a(DeviceType.WIFI_G).start(new d(this) {
                final /* synthetic */ a a;

                {
                    this.a = r1;
                }

                public void onSuccess(Object obj) {
                    DJILogHelper.getInstance().LOGD(a.a, "request wifi-g snr push success", false, true);
                }

                public void onFailure(dji.midware.data.config.P3.a aVar) {
                    if (dji.midware.data.config.P3.a.a == aVar) {
                        this.a.h.sendEmptyMessageDelayed(4096, a.e);
                    }
                    DJILogHelper.getInstance().LOGD(a.a, "request wifi-g snr push fail-" + aVar, false, true);
                }
            });
        }
    }

    private a() {
        this.f = ProductType.OTHER;
        this.g = false;
        this.h = new Handler(Looper.getMainLooper(), new Callback(this) {
            final /* synthetic */ a a;

            {
                this.a = r1;
            }

            public boolean handleMessage(Message message) {
                switch (message.what) {
                    case 4096:
                        this.a.c();
                        break;
                    case 4097:
                        this.a.b();
                        break;
                }
                return false;
            }
        });
        c.a().a(this);
        onEventBackgroundThread(dji.midware.data.manager.P3.i.getInstance().c());
    }
}
