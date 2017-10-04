package dji.pilot.dji_groundstation.a;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.TypedValue;
import android.view.WindowManager;
import com.dji.frame.c.l;
import com.here.android.mpa.mapping.Map;
import dji.gs.utils.a;
import dji.midware.data.model.P3.DataFlycGetPushRTKLocationData;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.util.d.b;
import dji.pilot.dji_groundstation.a.d.c;
import dji.pilot.dji_groundstation.controller.DataMgr.DJIWPCollectionItem$WayPoint;
import dji.pilot.dji_groundstation.controller.d;
import dji.pilot.publics.e.e;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public class f {
    private static final String a = "GSUtils";

    public static boolean a(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService("connectivity");
        NetworkInfo networkInfo = connectivityManager.getNetworkInfo(0);
        NetworkInfo networkInfo2 = connectivityManager.getNetworkInfo(1);
        if ((networkInfo == null || !networkInfo.isConnected()) && (networkInfo2 == null || !networkInfo2.isConnected())) {
            return false;
        }
        return true;
    }

    public static float a(float f) {
        return 3.28084f * f;
    }

    public static float b(float f) {
        return f / 1000.0f;
    }

    public static float c(float f) {
        return 2.2369363f * f;
    }

    public static float d(float f) {
        return e.e * f;
    }

    public static int a(int i, Context context) {
        if (context == null) {
            return 0;
        }
        return (int) TypedValue.applyDimension(1, (float) i, context.getResources().getDisplayMetrics());
    }

    public static double a(double d, Context context) {
        return (double) ((int) ((((double) context.getResources().getDisplayMetrics().density) * d) + 0.5d));
    }

    public static int b(Context context) {
        if (context == null) {
            return 0;
        }
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getWidth();
    }

    public static int c(Context context) {
        if (context == null) {
            return 0;
        }
        return ((WindowManager) context.getSystemService("window")).getDefaultDisplay().getHeight();
    }

    public static double a(double d, double d2) {
        double latitude;
        double longitude;
        if (d() && e()) {
            latitude = DataFlycGetPushRTKLocationData.getInstance().getLatitude();
            longitude = DataFlycGetPushRTKLocationData.getInstance().getLongitude();
        } else {
            latitude = DataOsdGetPushCommon.getInstance().getLatitude();
            longitude = DataOsdGetPushCommon.getInstance().getLongitude();
        }
        if (latitude == 0.0d && longitude == 0.0d) {
            return Map.MOVE_PRESERVE_ZOOM_LEVEL;
        }
        return a.a(d, d2, latitude, longitude);
    }

    public static double a() {
        if (e() && d()) {
            return (double) DataFlycGetPushRTKLocationData.getInstance().getHeight();
        }
        return (double) (0.1f * ((float) DataOsdGetPushCommon.getInstance().getHeight()));
    }

    public static double b() {
        if (e() && d()) {
            return DataFlycGetPushRTKLocationData.getInstance().getLatitude();
        }
        return DataOsdGetPushCommon.getInstance().getLatitude();
    }

    public static double c() {
        if (e() && d()) {
            return DataFlycGetPushRTKLocationData.getInstance().getLongitude();
        }
        return DataOsdGetPushCommon.getInstance().getLongitude();
    }

    private static boolean d() {
        if (d.getInstance().a().a() != d$a.Smart.a()) {
            return false;
        }
        c b = d.getInstance().b();
        if (b.a(c.b) || b.a(c.e)) {
            return true;
        }
        return false;
    }

    private static boolean e() {
        return DataFlycGetPushRTKLocationData.getInstance().isRTKConnected() && DataFlycGetPushRTKLocationData.getInstance().isRTKCanbeUsed();
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

    public static double a(double d) {
        return (180.0d * d) / 3.141592653589793d;
    }

    public static double b(double d) {
        return (3.141592653589793d * d) / 180.0d;
    }

    public static void a(Context context, double d, double d2, final i iVar) {
        dji.midware.util.d.a(context, d, d2, new com.dji.frame.b.c() {
            public void a(Object obj) {
                dji.midware.util.d dVar = (dji.midware.util.d) obj;
                if (dVar != null && dVar.a != null && dVar.a.equals("OK")) {
                    dji.midware.util.d.a a = dji.midware.util.d.a(dVar);
                    if (a != null) {
                        Iterator it = a.a.iterator();
                        String str = null;
                        String str2 = null;
                        String str3 = null;
                        String str4 = null;
                        while (it.hasNext()) {
                            String str5;
                            b bVar = (b) it.next();
                            if (bVar.c.contains("administrative_area_level_1")) {
                                str5 = str;
                                str = str2;
                                str2 = str3;
                                str3 = str4;
                            } else if (bVar.c.contains(dji.pilot.usercenter.protocol.c.W) || bVar.c.contains("administrative_area_level_2")) {
                                str3 = str4;
                                r9 = str2;
                                str2 = bVar.a;
                                str5 = str;
                                str = r9;
                            } else if (bVar.c.contains("route")) {
                                str2 = str3;
                                str3 = str4;
                                r9 = str;
                                str = bVar.a;
                                str5 = r9;
                            } else if (bVar.c.contains("sublocality")) {
                                str5 = str;
                                str = str2;
                                str2 = str3;
                                str3 = str4;
                            } else if (bVar.c.contains("country")) {
                                str5 = bVar.a;
                                str = str2;
                                str2 = str3;
                                str3 = str4;
                            } else if (bVar.c.contains("sublocality_level_1")) {
                                r9 = str;
                                str = str2;
                                str2 = str3;
                                str3 = bVar.a;
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
                                iVar.a(str3);
                            } else if (!l.a(str)) {
                                iVar.a(str);
                            }
                        } else if (!l.a(str3)) {
                            if (l.a(str2)) {
                                str2 = null;
                            }
                            if (!(l.a(str4) || str2 == null)) {
                                str2 = str2 + ", " + str4;
                            }
                            iVar.a(str2 + ", " + str3);
                        } else if (!l.a(str)) {
                            iVar.a(str);
                        }
                    }
                }
            }
        });
    }
}
