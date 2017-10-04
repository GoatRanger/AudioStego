package dji.logic.d;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.manager.P3.p;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.midware.data.model.P3.DataRcSetPowerMode;
import dji.midware.data.model.P3.DataRcSetPowerMode.DJIRcPowerMode;
import dji.midware.e.d;
import dji.thirdparty.a.c;

public class a {
    private static a a = null;
    private volatile int b = 0;
    private volatile int c = 0;

    public static synchronized a getInstance() {
        a aVar;
        synchronized (a.class) {
            if (a == null) {
                a = new a();
            }
            aVar = a;
        }
        return aVar;
    }

    private a() {
        c.a().a(this);
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            onEventBackgroundThread(DataOsdGetPushHome.getInstance());
        }
    }

    public void onEventBackgroundThread(p pVar) {
        this.b = 0;
        this.c = 0;
    }

    public void onEventBackgroundThread(o oVar) {
        this.b = 0;
        this.c = 0;
    }

    public void onEventBackgroundThread(DataOsdGetPushHome dataOsdGetPushHome) {
        if ((i.getInstance().c() == ProductType.litchiC || i.getInstance().c() == ProductType.P34K) && dataOsdGetPushHome.isHomeRecord() && dji.logic.f.c.a(dataOsdGetPushHome.getLatitude(), dataOsdGetPushHome.getLongitude())) {
            final boolean a = dji.logic.f.a.a(dataOsdGetPushHome.getLatitude(), dataOsdGetPushHome.getLongitude());
            if (this.b == 0) {
                this.b = 1;
                dji.logic.f.a.a(a, new d(this) {
                    final /* synthetic */ a b;

                    public void onSuccess(Object obj) {
                        this.b.b = 2;
                        DJILogHelper.getInstance().LOGD("Hehe", "setArea isInCEArea success ", false, true);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.b.b = 0;
                        DJILogHelper.getInstance().LOGD("Hehe", "setArea isInCEArea=" + a + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + aVar, false, true);
                    }
                });
            }
            if (this.c == 0) {
                this.c = 1;
                DataRcSetPowerMode.getInstance().a(a ? DJIRcPowerMode.CE : DJIRcPowerMode.FCC).start(new d(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        this.a.c = 2;
                        DJILogHelper.getInstance().LOGD("Hehe", "setArea osd isInCEArea success ", false, true);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        this.a.c = 0;
                    }
                });
            }
        }
    }
}
