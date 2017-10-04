package dji.pilot.fpv.model;

import android.content.Context;
import com.dji.frame.b.c;
import com.dji.frame.c.h;
import dji.log.DJILogHelper;
import dji.thirdparty.afinal.f.a;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Locale;

public class DJIGeocoderResult {
    public ArrayList<FirstLevel> results;
    public String status;

    public static class FirstLevel {
        public ArrayList<SecondLevel> address_components;
        public String formatted_address;
        public ArrayList<String> types;
    }

    public static class SecondLevel {
        public String long_name;
        public String short_name;
        public ArrayList<String> types;
    }

    public static FirstLevel getStreetAdress(DJIGeocoderResult dJIGeocoderResult) {
        Iterator it = dJIGeocoderResult.results.iterator();
        while (it.hasNext()) {
            FirstLevel firstLevel = (FirstLevel) it.next();
            if (firstLevel.types.contains("street_address")) {
                return firstLevel;
            }
            if (firstLevel.types.contains("route")) {
                return firstLevel;
            }
        }
        return null;
    }

    public static void get(Context context, double d, double d2, final c cVar) {
        dji.thirdparty.afinal.c b = com.dji.frame.c.c.b(context);
        String str = "";
        str = Locale.getDefault().getLanguage();
        if (str.contains("zh")) {
            str = "http://ditu.google.cn/maps/api/geocode/json?latlng=" + d + "," + d2 + "&sensor=false&language=" + str;
            DJILogHelper.getInstance().LOGI("bob", "gps " + str);
            b.a(str, new a<String>() {
                public void a(boolean z) {
                }

                public void a(long j, long j2) {
                }

                public void a(String str) {
                    DJILogHelper.getInstance().LOGD("DJIGeocoderResult", "geted ");
                    DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) h.b(str, DJIGeocoderResult.class);
                    if (dJIGeocoderResult != null) {
                        DJILogHelper.getInstance().LOGD("DJIGeocoderResult", "result " + dJIGeocoderResult.status);
                    }
                    if (cVar != null) {
                        cVar.a(dJIGeocoderResult);
                    }
                }

                public void a(Throwable th, int i, String str) {
                    cVar.a(null);
                }
            });
        } else {
            str = "http://ditu.google.cn/maps/api/geocode/json?latlng=" + d + "," + d2 + "&sensor=false&language=" + str;
            DJILogHelper.getInstance().LOGI("bob", "gps " + str);
            b.a(str, /* anonymous class already generated */);
        }
    }

    public static void get_en(Context context, double d, double d2, final c cVar) {
        com.dji.frame.c.c.b(context).a("http://ditu.google.cn/maps/api/geocode/json?latlng=" + d + "," + d2 + "&sensor=false&language=en", new a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) h.b(str, DJIGeocoderResult.class);
                if (dJIGeocoderResult != null) {
                }
                if (cVar != null) {
                    cVar.a(dJIGeocoderResult);
                }
            }

            public void a(Throwable th, int i, String str) {
                cVar.a(null);
            }
        });
    }
}
