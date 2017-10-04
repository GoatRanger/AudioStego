package dji.midware.data.model.P3;

import dji.midware.data.a.a.c;
import dji.midware.data.config.P3.DeviceType;
import dji.midware.data.config.P3.g;
import dji.midware.data.config.P3.p;
import dji.midware.data.config.P3.q;
import dji.midware.data.config.P3.q.a;
import dji.midware.data.config.P3.q.b;
import dji.midware.data.manager.P3.n;
import dji.midware.e.d;
import dji.midware.e.e;

public class DataFlycGetFsAction extends n implements e {
    private static DataFlycGetFsAction instance = null;

    public enum FS_ACTION {
        Hover(0),
        Landing(1),
        GoHome(2),
        OTHER(100);
        
        private int data;

        private FS_ACTION(int i) {
            this.data = i;
        }

        public int value() {
            return this.data;
        }

        public boolean _equals(int i) {
            return this.data == i;
        }

        public static FS_ACTION find(int i) {
            FS_ACTION fs_action = OTHER;
            for (int i2 = 0; i2 < values().length; i2++) {
                if (values()[i2]._equals(i)) {
                    return values()[i2];
                }
            }
            return fs_action;
        }
    }

    public static synchronized DataFlycGetFsAction getInstance() {
        DataFlycGetFsAction dataFlycGetFsAction;
        synchronized (DataFlycGetFsAction.class) {
            if (instance == null) {
                instance = new DataFlycGetFsAction();
            }
            dataFlycGetFsAction = instance;
        }
        return dataFlycGetFsAction;
    }

    public FS_ACTION getFsAction() {
        if (this._recData == null || this._recData.length == 0) {
            return FS_ACTION.GoHome;
        }
        return FS_ACTION.find(((Integer) get(0, 1, Integer.class)).intValue());
    }

    protected void doPack() {
    }

    public void start(d dVar) {
        c cVar = new c();
        cVar.f = DeviceType.APP.value();
        cVar.h = DeviceType.FLYC.value();
        cVar.j = a.REQUEST.a();
        cVar.k = q.c.YES.a();
        cVar.l = b.NO.a();
        cVar.m = p.FLYC.a();
        cVar.n = g.a.GetFsAction.a();
        start(cVar, dVar);
    }
}
