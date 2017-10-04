package dji.midware.util;

import dji.log.DJILogHelper;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataCommonGetDeviceStatus;
import dji.midware.e.d;
import dji.midware.e.e;

public class m implements d, Runnable {
    public final String a = "RepeatDataBase";
    private e b;
    private d c;
    private int d;
    private int e;
    private int f;

    private void b() {
        m mVar = new m(new DataCommonGetDeviceStatus().setReceiveType(DeviceType.BATTERY), 3, 2000, new d(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(a aVar) {
            }
        });
    }

    private void c() {
        m mVar = new m(new DataCommonGetDeviceStatus().setReceiveType(DeviceType.BATTERY), new d(this) {
            final /* synthetic */ m a;

            {
                this.a = r1;
            }

            public void onSuccess(Object obj) {
            }

            public void onFailure(a aVar) {
            }
        });
    }

    public m(e eVar, d dVar) {
        this.b = eVar;
        this.d = 3;
        this.e = 1000;
        this.c = dVar;
        this.f = 0;
    }

    public m(e eVar, int i, d dVar) {
        this.b = eVar;
        this.d = i;
        this.e = 1000;
        this.c = dVar;
        this.f = 0;
    }

    public m(e eVar, int i, int i2, d dVar) {
        this.b = eVar;
        this.d = i;
        this.e = i2;
        this.c = dVar;
        this.f = 0;
    }

    public void a() {
        this.b.start(this);
    }

    public void onSuccess(Object obj) {
        if (this.c != null) {
            this.c.onSuccess(obj);
        }
    }

    public void onFailure(a aVar) {
        this.f++;
        if (this.f < this.d) {
            b.a(this, (long) this.e);
            DJILogHelper.getInstance().LOGI(dji.pilot.publics.control.p3cupgrade.e.a, "retry time: " + System.currentTimeMillis(), dji.pilot.publics.control.p3cupgrade.e.a);
        } else if (this.c != null) {
            this.c.onFailure(aVar);
        }
    }

    public void run() {
        a();
    }
}
