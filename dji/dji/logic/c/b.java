package dji.logic.c;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.os.Handler;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.sockets.P3.f;
import dji.midware.usb.P3.UsbAccessoryService;
import dji.thirdparty.a.c;

public class b {
    private static b f = null;
    private static final int g = 10000;
    private static final int h = 1;
    private c a = c.b;
    private boolean b = false;
    private AlertDialog c;
    private AlertDialog d;
    private boolean e = false;
    private Handler i = new Handler(new 1(this));
    private f j;
    private UsbAccessoryService k;

    @Deprecated
    public enum a {
        SHOW_SWITCH_DLG,
        SHOW_NOT_CONNECT_DLG
    }

    public boolean a() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    private b() {
        c.a().a((Object) this);
    }

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (f == null) {
                f = new b();
            }
            bVar = f;
        }
        return bVar;
    }

    public boolean b() {
        if (this.j == null) {
            this.j = f.getInstance();
        }
        return this.j.isConnected();
    }

    public boolean c() {
        if (this.k == null) {
            this.k = UsbAccessoryService.getInstance();
        }
        return this.k.isConnected();
    }

    public boolean a(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return b() && (productType == ProductType.KumquatX || productType == ProductType.KumquatS);
    }

    public boolean b(ProductType productType) {
        if (productType == null) {
            productType = i.getInstance().c();
        }
        return c() && (productType == ProductType.KumquatX || productType == ProductType.KumquatS);
    }

    public void a(Context context, String str, String str2, String str3) {
        if (DataOsdGetPushCommon.getInstance().groundOrSky() == 2) {
            a(context, str, str3);
        } else {
            b(context, str, str2);
        }
        this.e = false;
    }

    public void a(Context context, String str, String str2) {
        if (this.d == null || !this.d.isShowing()) {
            if (!(this.d == null || this.d.getContext().equals(context))) {
                this.d = null;
            }
            Builder builder = new Builder(context);
            builder.setTitle(str);
            builder.setMessage(str2);
            builder.setPositiveButton(17039379, new 2(this));
            builder.setCancelable(false);
            this.d = builder.create();
            if (this.c != null && this.c.isShowing()) {
                this.c.dismiss();
            }
            this.d.show();
        }
    }

    public void b(Context context, String str, String str2) {
        if (this.c == null || !this.c.isShowing()) {
            if (!(this.c == null || this.c.getContext().equals(context))) {
                this.c = null;
            }
            Builder builder = new Builder(context);
            builder.setTitle(str);
            builder.setMessage(str2);
            builder.setPositiveButton(17039379, new 3(this, context));
            builder.setNegativeButton(17039369, new 4(this));
            builder.setCancelable(false);
            this.c = builder.create();
            if (this.d != null && this.d.isShowing()) {
                this.d.dismiss();
            }
            this.c.show();
        }
    }

    public void a(Context context, String str) {
        if (this.b) {
            this.b = false;
            Builder builder = new Builder(context);
            builder.setMessage(str);
            builder.setPositiveButton(17039379, new 5(this));
            builder.show();
        }
    }

    public void d() {
        DJILogHelper.getInstance().LOGE("wm220", "****resetSwitchFromWifiFlag", false, true);
        this.b = false;
        this.i.removeMessages(1);
        if (this.c != null && this.c.isShowing()) {
            this.c.dismiss();
        }
        if (this.d != null && this.d.isShowing()) {
            this.d.dismiss();
        }
    }

    public c e() {
        return this.a;
    }

    public void onEventBackgroundThread(o oVar) {
        if (oVar == o.ConnectOK) {
            d();
        }
    }

    public void onEventBackgroundThread(p pVar) {
        if (pVar != p.ConnectOK) {
            this.a = c.b;
            c.a().e(c.b);
        } else if (a(i.getInstance().c())) {
            this.a = c.a;
            c.a().e(c.a);
        } else {
            this.a = c.b;
            c.a().e(c.b);
        }
    }
}
