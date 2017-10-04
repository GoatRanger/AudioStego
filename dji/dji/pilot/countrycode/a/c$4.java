package dji.pilot.countrycode.a;

import dji.midware.data.config.P3.a;
import dji.midware.e.d;

class c$4 implements d {
    final /* synthetic */ c a;

    c$4(c cVar) {
        this.a = cVar;
    }

    public void onSuccess(Object obj) {
        a.a(c.d(this.a), "【国家码设置（set）】结果onSuccess，:model=" + obj);
        if (this.a.a != null) {
            this.a.a.a("set CountryCode success");
        }
    }

    public void onFailure(a aVar) {
        if (this.a.a != null) {
            this.a.a.b("set CountryCode fail");
        }
        a.a(c.d(this.a), "【国家码设置（set）】结果-onFailure()主要可能是飞机没有连接:ccode=" + aVar);
    }
}
