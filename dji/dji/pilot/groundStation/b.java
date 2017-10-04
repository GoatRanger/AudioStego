package dji.pilot.groundStation;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.dji.frame.b.c;
import com.dji.frame.c.l;
import dji.gs.utils.a;
import dji.pilot.fpv.model.DJIGeocoderResult;
import dji.pilot.fpv.model.DJIGeocoderResult.FirstLevel;
import dji.pilot.fpv.model.DJIGeocoderResult.SecondLevel;
import dji.pilot.groundStation.db.DJIWPCollectionItem$WayPoint;
import dji.pilot.publics.e.e;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class b {
    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if ((networkInfo == null || !networkInfo.isConnected()) && (networkInfo2 == null || !networkInfo2.isConnected())) {
            return false;
        }
        return true;
    }

    public static void a(Context context, double d, double d2, final c cVar) {
        if (d != 0.0d && d2 != 0.0d && cVar != null) {
            DJIGeocoderResult.get(context, d, d2, new c() {
                public void a(Object obj) {
                    DJIGeocoderResult dJIGeocoderResult = (DJIGeocoderResult) obj;
                    if (dJIGeocoderResult != null && dJIGeocoderResult.status != null && dJIGeocoderResult.status.equals("OK")) {
                        FirstLevel streetAdress = DJIGeocoderResult.getStreetAdress(dJIGeocoderResult);
                        if (streetAdress != null) {
                            Iterator it = streetAdress.address_components.iterator();
                            String str = null;
                            String str2 = null;
                            String str3 = null;
                            String str4 = null;
                            while (it.hasNext()) {
                                String str5;
                                SecondLevel secondLevel = (SecondLevel) it.next();
                                if (secondLevel.types.contains("administrative_area_level_1")) {
                                    str5 = str;
                                    str = str2;
                                    str2 = str3;
                                    str3 = str4;
                                } else if (secondLevel.types.contains(dji.pilot.usercenter.protocol.c.W) || secondLevel.types.contains("administrative_area_level_2")) {
                                    str3 = str4;
                                    r9 = str2;
                                    str2 = secondLevel.long_name;
                                    str5 = str;
                                    str = r9;
                                } else if (secondLevel.types.contains("route")) {
                                    str2 = str3;
                                    str3 = str4;
                                    r9 = str;
                                    str = secondLevel.long_name;
                                    str5 = r9;
                                } else if (secondLevel.types.contains("sublocality")) {
                                    str5 = str;
                                    str = str2;
                                    str2 = str3;
                                    str3 = str4;
                                } else if (secondLevel.types.contains("country")) {
                                    str5 = secondLevel.long_name;
                                    str = str2;
                                    str2 = str3;
                                    str3 = str4;
                                } else if (secondLevel.types.contains("sublocality_level_1")) {
                                    r9 = str;
                                    str = str2;
                                    str2 = str3;
                                    str3 = secondLevel.long_name;
                                    str5 = r9;
                                } else {
                                    str5 = str;
                                    str = str2;
                                    str2 = str3;
                                    str3 = str4;
                                }
                                str4 = str3;
                                str3 = str2;
                                str2 = str;
                                str = str5;
                            }
                            if (Locale.getDefault().getLanguage().contains("zh")) {
                                if (!l.a(str3)) {
                                    if (!l.a(str4)) {
                                        str3 = str3 + str4;
                                    }
                                    if (!l.a(str2)) {
                                        str3 = str3 + str2;
                                    }
                                    cVar.a(str3);
                                } else if (!l.a(str)) {
                                    cVar.a(str);
                                }
                            } else if (!l.a(str3)) {
                                if (l.a(str2)) {
                                    str2 = null;
                                }
                                if (!(l.a(str4) || str2 == null)) {
                                    str2 = str2 + ", " + str4;
                                }
                                cVar.a(str2 + ", " + str3);
                            } else if (!l.a(str)) {
                                cVar.a(str);
                            }
                        }
                    }
                }
            });
        }
    }

    public static double a(List<DJIWPCollectionItem$WayPoint> list) {
        int i = 1;
        double d = 0.0d;
        while (i < list.size()) {
            DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint = (DJIWPCollectionItem$WayPoint) list.get(i - 1);
            DJIWPCollectionItem$WayPoint dJIWPCollectionItem$WayPoint2 = (DJIWPCollectionItem$WayPoint) list.get(i);
            double a = a.a(dJIWPCollectionItem$WayPoint.getLat(), dJIWPCollectionItem$WayPoint.getLng(), dJIWPCollectionItem$WayPoint2.getLat(), dJIWPCollectionItem$WayPoint2.getLng());
            double height = dJIWPCollectionItem$WayPoint.getHeight() - dJIWPCollectionItem$WayPoint2.getHeight();
            i++;
            d += Math.sqrt((a * a) + (height * height));
        }
        return d;
    }

    public static float a(float f) {
        return 3.28084f * f;
    }

    public static float b(float f) {
        return 2.2369363f * f;
    }

    public static float c(float f) {
        return e.e * f;
    }
}
