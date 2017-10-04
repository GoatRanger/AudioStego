package dji.pilot.flyunlimit.c;

import android.content.Context;
import com.dji.frame.b.c;
import com.dji.frame.c.l;
import dji.pilot.R;
import dji.pilot.flyunlimit.b.d;
import dji.pilot.fpv.model.DJIGeocoderResult;
import dji.pilot.fpv.model.DJIGeocoderResult.FirstLevel;
import dji.pilot.fpv.model.DJIGeocoderResult.SecondLevel;
import java.util.Iterator;

public class a {
    private static String[] a = null;
    private static int[] b = null;

    public static void a(Context context, double d, double d2, final d dVar) {
        if (d != 0.0d && d2 != 0.0d && dVar != null) {
            DJIGeocoderResult.get_en(context, d, d2, new c() {
                public void a(Object obj) {
                    String str = null;
                    DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) obj;
                    if (!(dJIGeocoderResult == null || dJIGeocoderResult.status == null || !dJIGeocoderResult.status.equals("OK"))) {
                        FirstLevel streetAdress = DJIGeocoderResult.getStreetAdress(dJIGeocoderResult);
                        if (streetAdress != null) {
                            Iterator it = streetAdress.address_components.iterator();
                            String str2 = null;
                            while (it.hasNext()) {
                                String str3;
                                SecondLevel secondLevel = (SecondLevel) it.next();
                                String str4;
                                if (secondLevel.types.contains(dji.pilot.usercenter.protocol.c.W) || secondLevel.types.contains("administrative_area_level_2")) {
                                    str4 = str;
                                    str = secondLevel.long_name;
                                    str3 = str4;
                                } else if (secondLevel.types.contains("country")) {
                                    str3 = secondLevel.long_name;
                                    str = str2;
                                } else if (secondLevel.types.contains("sublocality_level_1") && l.a(str2)) {
                                    str4 = str;
                                    str = secondLevel.long_name;
                                    str3 = str4;
                                } else {
                                    str3 = str;
                                    str = str2;
                                }
                                str2 = str;
                                str = str3;
                            }
                            dVar.a(str, str2);
                            return;
                        }
                    }
                    dVar.a();
                }
            });
        } else if (dVar != null) {
            dVar.a();
        }
    }

    public static String a(Context context, int i) {
        if (a == null) {
            b = context.getResources().getIntArray(R.array.dg);
            a = context.getResources().getStringArray(R.array.df);
        }
        for (int i2 = 0; i2 != b.length; i2++) {
            if (b[i2] == i) {
                return a[i2];
            }
        }
        return a[0];
    }

    public static long a() {
        return System.currentTimeMillis() / 1000;
    }
}
