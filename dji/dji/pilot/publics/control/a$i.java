package dji.pilot.publics.control;

import dji.midware.data.model.P3.DataCommonGetVersion;

abstract class a$i implements Runnable {
    protected DataCommonGetVersion a;
    final /* synthetic */ a b;

    private a$i(a aVar) {
        this.b = aVar;
    }

    public a$i a(DataCommonGetVersion dataCommonGetVersion) {
        this.a = dataCommonGetVersion;
        return this;
    }
}
