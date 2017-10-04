package dji.publics.b.b;

import android.util.Log;
import com.dji.frame.c.l;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.model.P3.DataGimbalActiveStatus;
import dji.midware.data.model.P3.DataOsdActiveStatus;
import dji.midware.data.model.b.a.b;
import dji.midware.e.d;
import java.util.HashMap;

public class a {
    public static final String a = "OSMO MOBILE";
    private static final String h = "LonganReportHelper";
    private static a l = new a();
    public HashMap<String, String> b = new HashMap();
    public HashMap<String, String> c = new HashMap();
    public HashMap<String, String> d = new HashMap();
    public HashMap<String, String> e = new HashMap();
    public HashMap<String, String> f = new HashMap();
    public HashMap<String, String> g = new HashMap();
    private boolean i = false;
    private String j;
    private boolean k = true;

    public static a getInstance() {
        return l;
    }

    public void a(String str) {
        com.dji.a.a.a(str);
    }

    public void a(String str, HashMap<String, String> hashMap) {
        com.dji.a.a.a(str, hashMap);
    }

    private void a(final String str, final HashMap<String, String> hashMap, String str2, String str3, boolean z) {
        hashMap.put(str2, str3);
        if (z) {
            if (!this.k) {
                com.dji.a.a.a(str, hashMap);
            } else if (l.a(this.j)) {
                dji.midware.data.model.b.a a = a();
                if (a.isGetted()) {
                    String sn = a.getSN();
                    this.j = sn;
                    a(sn, str, (HashMap) hashMap);
                } else {
                    a.setType(b.b).start(new d(this) {
                        final /* synthetic */ a c;

                        public void onSuccess(Object obj) {
                            String sn = DataGimbalActiveStatus.getInstance().getSN();
                            this.c.j = sn;
                            this.c.a(sn, str, hashMap);
                        }

                        public void onFailure(dji.midware.data.config.P3.a aVar) {
                            this.c.a("", str, hashMap);
                        }
                    });
                }
            } else {
                a(this.j, str, (HashMap) hashMap);
            }
            if (this.i) {
                Log.d(h, "DJIA reportData: " + hashMap.toString());
            }
        }
    }

    private dji.midware.data.model.b.a a() {
        DataGimbalActiveStatus.getInstance();
        if (i.getInstance().c() == ProductType.LonganMobile) {
            return DataGimbalActiveStatus.getInstance();
        }
        return DataOsdActiveStatus.getInstance();
    }

    private void a(String str, String str2, HashMap<String, String> hashMap) {
        hashMap.put("device_sn", str);
        com.dji.a.a.a(str2, hashMap);
    }

    public a a(String str, String str2, boolean z) {
        a(dji.publics.b.a.a.a, this.b, str, str2, z);
        return this;
    }

    public a b(String str, String str2, boolean z) {
        a(dji.publics.b.a.a.b, this.c, str, str2, z);
        return this;
    }

    public a c(String str, String str2, boolean z) {
        a(dji.publics.b.a.a.c, this.d, str, str2, z);
        return this;
    }

    public a d(String str, String str2, boolean z) {
        a(dji.publics.b.a.a.d, this.e, str, str2, z);
        return this;
    }

    public a e(String str, String str2, boolean z) {
        a(dji.publics.b.a.a.e, this.f, str, str2, z);
        return this;
    }

    public a f(String str, String str2, boolean z) {
        a(dji.publics.b.a.a.f, this.g, str, str2, z);
        return this;
    }
}
