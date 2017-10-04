package dji.midware.util;

import android.content.Context;
import com.dji.frame.b.c;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class d {
    public String a;
    public ArrayList<a> b;

    public static class a {
        public ArrayList<b> a;
        public String b;
        public ArrayList<String> c;
    }

    public static class b {
        public String a;
        public String b;
        public ArrayList<String> c;
    }

    public static a a(d dVar) {
        Iterator it = dVar.b.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            if (aVar.c.contains("street_address")) {
                return aVar;
            }
            if (aVar.c.contains("route")) {
                return aVar;
            }
        }
        return null;
    }

    public static void a(Context context, double d, double d2, final c cVar) {
        dji.thirdparty.afinal.c b = com.dji.frame.c.c.b(context);
        String str = "";
        str = Locale.getDefault().getLanguage();
        if (str.contains("zh")) {
            b.a("http://ditu.google.cn/maps/api/geocode/json?latlng=" + d + "," + d2 + "&sensor=false&language=" + str, new dji.thirdparty.afinal.f.a<String>() {
                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    DJILogHelper.getInstance().LOGD("DJIGeocoderResult", "geted ");
                    d dVar = (d) h.b(str, d.class);
                    if (dVar != null) {
                        DJILogHelper.getInstance().LOGD("DJIGeocoderResult", "result " + dVar.a);
                    }
                    if (cVar != null) {
                        cVar.a(dVar);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    cVar.a(null);
                }
            });
        } else {
            b.a("http://ditu.google.cn/maps/api/geocode/json?latlng=" + d + "," + d2 + "&sensor=false&language=" + str, /* anonymous class already generated */);
        }
    }

    public static void b(Context context, double d, double d2, final c cVar) {
        com.dji.frame.c.c.b(context).a("http://ditu.google.cn/maps/api/geocode/json?latlng=" + d + "," + d2 + "&sensor=false&language=en", new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                DJILogHelper.getInstance().LOGD("nfz", "" + str, false, true);
                d dVar = (d) h.b(str, d.class);
                if (dVar != null) {
                }
                if (cVar != null) {
                    cVar.a(dVar);
                }
            }

            public void a(Throwable th, int i, String str) {
                cVar.a(null);
            }
        });
    }
}
