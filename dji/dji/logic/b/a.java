package dji.logic.b;

import dji.log.DJILogHelper;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataCameraGetPushStateInfo;
import dji.midware.data.model.P3.DataCameraSetDate;
import dji.midware.natives.FPVController;
import dji.thirdparty.a.c;

public class a {
    private static a a = null;
    private int b = 0;
    private long c = 0;

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
        b();
        if (DataCameraGetPushStateInfo.getInstance().isGetted()) {
            onEventBackgroundThread(DataCameraGetPushStateInfo.getInstance());
        }
    }

    public int a() {
        return this.b;
    }

    public void onEventBackgroundThread(DataCameraGetPushStateInfo dataCameraGetPushStateInfo) {
        int verstion = dataCameraGetPushStateInfo.getVerstion();
        if (this.b != verstion) {
            this.b = verstion;
            b();
        }
        if (!dataCameraGetPushStateInfo.getTimeSyncState() && System.currentTimeMillis() >= this.c + 500) {
            DJILogHelper.getInstance().LOGD("DJILogicCameraInfo", "camera sync time " + i.getInstance().c());
            this.c = System.currentTimeMillis();
            DataCameraSetDate.getInstance().start(null);
        }
    }

    private void b() {
        if (this.b != 0) {
            FPVController.native_setIsNewRate(true);
        } else {
            FPVController.native_setIsNewRate(false);
        }
    }
}
