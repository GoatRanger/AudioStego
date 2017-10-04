package dji.midware.data.model.c;

import android.os.Handler;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataOsdGetPushConfig;
import dji.midware.e.d;
import dji.midware.util.b;
import dji.thirdparty.a.c;

public class a implements d, Runnable {
    private static a c;
    public final String a = "DataOsdConfigEx";
    private int b = -1;
    private DataOsdGetPushConfig d = DataOsdGetPushConfig.getInstance();
    private Handler e = new Handler(b.b());

    public static a get() {
        if (c == null) {
            c = new a();
        }
        return c;
    }

    private a() {
        c.a().a(this);
        a();
    }

    public void a() {
        if (ServiceManager.getInstance().isConnected()) {
            this.e.removeCallbacks(this);
            this.e.post(this);
            return;
        }
        this.e.removeCallbacks(this);
    }

    public DataOsdGetPushConfig b() {
        return this.d;
    }

    public void onEventBackgroundThread(p pVar) {
        a();
    }

    public void onEventBackgroundThread(DataOsdGetPushConfig dataOsdGetPushConfig) {
        if (this.b != this.d.getBandWidthPercent()) {
            this.b = this.d.getBandWidthPercent();
            c.a().e(this);
        }
    }

    public void run() {
        this.d.start(this);
    }

    public void onSuccess(Object obj) {
        this.e.postDelayed(this, 1000);
        if (this.b != this.d.getBandWidthPercent()) {
            this.b = this.d.getBandWidthPercent();
            c.a().e(this);
        }
    }

    public void onFailure(dji.midware.data.config.P3.a aVar) {
        this.e.postDelayed(this, 1000);
    }
}
