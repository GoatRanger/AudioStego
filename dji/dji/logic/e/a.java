package dji.logic.e;

import dji.log.DJILogHelper;
import dji.midware.data.model.P3.DataOsdGetPushMaxMcs;
import dji.midware.data.model.P3.DataOsdSetMaxMcs;
import dji.midware.e.d;

public class a {
    private static a a = null;
    private int b = 0;

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
    }

    public void onEventBackgroundThread(DataOsdGetPushMaxMcs dataOsdGetPushMaxMcs) {
        int maxMcs = dataOsdGetPushMaxMcs.getMaxMcs();
        if (this.b != maxMcs) {
            this.b = maxMcs;
            DJILogHelper.getInstance().LOGD("", "++++++++++++++++++pushMaxMcs=" + maxMcs, true, true);
            dataOsdGetPushMaxMcs.start();
            if (maxMcs > 4) {
                DataOsdSetMaxMcs.getInstance().a(4).start(new d(this) {
                    final /* synthetic */ a a;

                    {
                        this.a = r1;
                    }

                    public void onSuccess(Object obj) {
                        DJILogHelper.getInstance().LOGD("", "++++++++++++++++++setMaxMcs ok", true, true);
                    }

                    public void onFailure(dji.midware.data.config.P3.a aVar) {
                        DJILogHelper.getInstance().LOGD("", "++++++++++++++++++setMaxMcs " + aVar, true, true);
                    }
                });
            }
        }
    }
}
