package dji.pilot.battery.a;

import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataCenterGetBatteryHistory;
import dji.midware.data.model.P3.DataCenterGetPushBatteryCommon.ConnStatus;
import dji.midware.e.d;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class b {
    public static final int a = 4096;
    public static final int b = 0;
    public static final int c = 1;
    private static final int d = 31;
    private final c e;
    private final DataCenterGetBatteryHistory f;
    private final ArrayList<a> g;
    private final d h;
    private final ArrayList<c> i;
    private final ArrayList<c> j;
    private boolean k;
    private int l;
    private ConnStatus m;
    private final c n;

    public interface a {
        void a(int i);

        void a(int i, dji.midware.data.config.P3.a aVar);

        void a(int i, Object obj);

        void b(int i, Object obj);
    }

    private static final class b {
        private static final b a = new b();

        private b() {
        }
    }

    private static final class c extends Handler {
        private final WeakReference<b> a;

        public c(b bVar) {
            super(Looper.getMainLooper());
            this.a = new WeakReference(bVar);
        }

        public void handleMessage(Message message) {
            b bVar = (b) this.a.get();
            if (bVar != null) {
                bVar.a(message.what, message.arg1, message.arg2, message.obj);
            }
        }
    }

    public static b getInstance() {
        return b.a;
    }

    public void a(a aVar) {
        if (aVar != null && !this.g.contains(aVar)) {
            this.g.add(aVar);
        }
    }

    public void b(a aVar) {
        if (aVar != null) {
            this.g.remove(aVar);
        }
    }

    public void a() {
        if (!this.k) {
            this.k = true;
            this.n.b(this.l);
            this.n.a(this.m);
            if (!this.f.isGetted()) {
                a(this.i, false);
                this.f.start(this.h);
                b(4096);
            }
            dji.thirdparty.a.c.a().a(this);
        }
    }

    public void b() {
        if (this.k) {
            dji.thirdparty.a.c.a().d(this);
            this.k = false;
        }
    }

    public void a(int i) {
        if (this.l != i) {
            this.l = i;
            if (this.k) {
                this.n.b(i);
                b(4096, this.i);
            }
        }
    }

    public void a(ConnStatus connStatus) {
        if (this.m != connStatus) {
            this.m = connStatus;
            if (this.k) {
                this.n.a(connStatus);
                b(4096, this.i);
            }
        }
    }

    public List<c> c() {
        return this.i;
    }

    public void onEventMainThread(o oVar) {
        if (o.b == oVar) {
            this.f.start(this.h);
            b(4096);
        } else if (o.a == oVar) {
            a(this.i, false);
            this.l = 0;
            this.m = ConnStatus.EXCEPTION;
            this.n.b(this.l);
            this.n.a(this.m);
            b(4096, this.i);
        }
    }

    private b() {
        int i = 0;
        this.f = DataCenterGetBatteryHistory.getInstance();
        this.g = new ArrayList(2);
        this.i = new ArrayList(31);
        this.j = new ArrayList(31);
        this.k = false;
        this.l = 0;
        this.m = ConnStatus.EXCEPTION;
        this.e = new c(this);
        this.h = new d(this) {
            final /* synthetic */ b a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
                this.a.e.obtainMessage(4096, 0, 0).sendToTarget();
            }

            public void onFailure(dji.midware.data.config.P3.a aVar) {
                this.a.e.obtainMessage(4096, 1, 0, aVar).sendToTarget();
            }
        };
        this.n = new c();
        this.i.add(this.n);
        while (i < 31) {
            this.j.add(new c());
            i++;
        }
    }

    private void a(int i, int i2, int i3, Object obj) {
        if (i2 == 0) {
            if (4096 == i) {
                int i4;
                c cVar;
                if (i.getInstance().c() == ProductType.KumquatX || i.getInstance().c() == ProductType.KumquatS) {
                    long[] historyLong = this.f.getHistoryLong();
                    a(this.i, false);
                    i4 = 0;
                    while (i4 < 31 && i4 < historyLong.length) {
                        cVar = (c) this.j.get(i4);
                        cVar.a(historyLong[i4]);
                        this.i.add(cVar);
                        i4++;
                    }
                } else {
                    int[] history = this.f.getHistory();
                    a(this.i, false);
                    i4 = 0;
                    while (i4 < 31 && i4 < history.length) {
                        cVar = (c) this.j.get(i4);
                        cVar.b(history[i4]);
                        this.i.add(cVar);
                        i4++;
                    }
                }
                a(4096, this.i);
            }
        } else if (1 == i2) {
            a(i, obj instanceof dji.midware.data.config.P3.a ? (dji.midware.data.config.P3.a) obj : dji.midware.data.config.P3.a.D);
        }
    }

    private void a(List<c> list, boolean z) {
        if (z) {
            list.clear();
            return;
        }
        while (list.size() > 1) {
            list.remove(1);
        }
    }

    private void b(int i) {
        if (!this.g.isEmpty()) {
            ((a) this.g.get(0)).a(i);
        }
    }

    private void a(int i, Object obj) {
        if (!this.g.isEmpty()) {
            ((a) this.g.get(0)).a(i, obj);
        }
    }

    private void a(int i, dji.midware.data.config.P3.a aVar) {
        if (!this.g.isEmpty()) {
            ((a) this.g.get(0)).a(i, aVar);
        }
    }

    private void b(int i, Object obj) {
        if (!this.g.isEmpty()) {
            ((a) this.g.get(0)).b(i, obj);
        }
    }
}
