package dji.pilot.dji_groundstation.controller;

import android.os.Message;
import dji.midware.data.config.P3.a;
import dji.midware.data.model.P3.DataFlycUploadWayPointMsgByIndex;
import dji.midware.e.d;
import dji.pilot.dji_groundstation.a.c;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem;
import dji.pilot.dji_groundstation.controller.DataMgr.e;
import java.util.concurrent.Semaphore;

class d$2 implements d {
    final /* synthetic */ int a;
    final /* synthetic */ DJIWPCollectionItem b;
    final /* synthetic */ Semaphore c;
    final /* synthetic */ d d;

    d$2(d dVar, int i, DJIWPCollectionItem dJIWPCollectionItem, Semaphore semaphore) {
        this.d = dVar;
        this.a = i;
        this.b = dJIWPCollectionItem;
        this.c = semaphore;
    }

    public void onSuccess(Object obj) {
        int result = DataFlycUploadWayPointMsgByIndex.getInstance().getResult();
        if (result == 0) {
            Message obtain = Message.obtain();
            obtain.what = 257;
            obtain.arg1 = (this.a * 100) / this.b.getPoints().size();
            if (e.getInstance().p() != null) {
                e.getInstance().p().sendMessage(obtain);
            }
            this.c.release();
            return;
        }
        d.a(this.d, true);
        this.c.release();
        d.a(this.d, c.a(result), "");
    }

    public void onFailure(a aVar) {
        d.a(this.d, true);
        this.c.release();
        d.a(this.d, -1, aVar.toString());
    }
}
