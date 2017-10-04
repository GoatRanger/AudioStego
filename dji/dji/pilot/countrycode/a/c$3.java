package dji.pilot.countrycode.a;

import dji.pilot.countrycode.a.b.a;

class c$3 implements a {
    final /* synthetic */ c a;

    c$3(c cVar) {
        this.a = cVar;
    }

    public void a(String str, String str2) {
        a.a(c.d(this.a), "【国家码获取（get）】返回结果onSuccess:国家码=" + str + ",采取策略为=" + str2);
        c.e(this.a);
    }

    public void a(String str) {
        a.a(c.d(this.a), "【国家码获取（get）】返回结果onFailure:" + str);
        c.e(this.a);
        if (this.a.a != null) {
            this.a.a.b("get CountryCode fail");
        }
    }
}
