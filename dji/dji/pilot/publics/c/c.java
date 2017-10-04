package dji.pilot.publics.c;

import android.os.Handler;
import android.os.Looper;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.ServiceManager;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.m;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import java.util.ArrayList;
import java.util.Iterator;

public class c {
    private static c a;
    private ArrayList<a> b = new ArrayList();
    private a c = a.c;
    private a d = a.c;
    private int e = 1000;
    private boolean f = false;
    private Handler g = new Handler(Looper.getMainLooper(), new 1(this));

    public static synchronized c getInstance() {
        c cVar;
        synchronized (c.class) {
            if (a == null) {
                a = new c();
            }
            cVar = a;
        }
        return cVar;
    }

    private c() {
    }

    public a a() {
        return this.d;
    }

    private boolean c() {
        ProductType c = i.getInstance().c();
        return (c == ProductType.litchiS || c.isFromWifi()) ? false : true;
    }

    private void d() {
        if (!ServiceManager.getInstance().isConnected()) {
        }
    }

    private void e() {
        if (!ServiceManager.getInstance().isRemoteOK()) {
        }
    }

    protected synchronized void b() {
        int i = 100;
        this.c = a.c;
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.a() < i) {
                i = aVar.a();
                this.c = aVar;
            }
            i = i;
        }
    }

    private synchronized void a(a aVar) {
        if (!this.b.contains(aVar)) {
            this.b.add(aVar);
            DJILogHelper.getInstance().LOGD("", "DJIHDStatus " + aVar, false, true);
        }
    }

    private synchronized void b(a aVar) {
        if (this.b.contains(aVar)) {
            this.b.remove(aVar);
        }
    }

    public void onEventBackgroundThread(m mVar) {
        switch (2.a[mVar.ordinal()]) {
            case 1:
                a(a.b);
                return;
            case 2:
                b(a.b);
                return;
            default:
                return;
        }
    }

    public void onEventBackgroundThread(p pVar) {
        switch (2.b[pVar.ordinal()]) {
        }
    }

    public void onEventBackgroundThread(o oVar) {
        switch (2.c[oVar.ordinal()]) {
        }
    }
}
