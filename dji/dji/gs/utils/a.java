package dji.gs.utils;

import android.content.Context;
import com.dji.frame.c.h;
import com.here.android.mpa.mapping.Map;
import dji.gs.R;
import dji.gs.e.b;
import dji.gs.utils.CountryBorder.CountryItem;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class a {
    public static boolean a = true;
    private static final double b = 3.141592653589793d;
    private static a[] c = new a[]{new a(49.2204d, 79.4462d, 42.8899d, 96.33d), new a(54.1415d, 109.6872d, 39.3742d, 135.0002d), new a(42.8899d, 73.1246d, 29.5297d, 124.143255d), new a(29.5297d, 82.9684d, 26.7186d, 97.0352d), new a(29.5297d, 97.0253d, 20.414096d, 124.367395d), new a(20.414096d, 107.975793d, 17.871542d, 111.744104d)};
    private static a[] d = new a[]{new a(25.398623d, 119.921265d, 21.785006d, 122.497559d), new a(22.284d, 101.8652d, 20.0988d, 106.665d), new a(21.5422d, 106.4525d, 20.4878d, 108.051d), new a(55.8175d, 109.0323d, 50.3257d, 119.127d), new a(55.8175d, 127.4568d, 49.5574d, 137.0227d), new a(44.8922d, 131.2662d, 42.5692d, 137.0227d)};
    private static List<CountryItem> e = null;

    private static double e(double d, double d2) {
        return (((((((-100.0d + (2.0d * d)) + (3.0d * d2)) + ((0.2d * d2) * d2)) + ((0.1d * d) * d2)) + (0.2d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * b)) + (20.0d * Math.sin((2.0d * d) * b))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(b * d2)) + (40.0d * Math.sin((d2 / 3.0d) * b))) * 2.0d) / 3.0d)) + ((((160.0d * Math.sin((d2 / 12.0d) * b)) + (320.0d * Math.sin((b * d2) / 30.0d))) * 2.0d) / 3.0d);
    }

    private static double f(double d, double d2) {
        return (((((((300.0d + d) + (2.0d * d2)) + ((0.1d * d) * d)) + ((0.1d * d) * d2)) + (0.1d * Math.sqrt(Math.abs(d)))) + ((((20.0d * Math.sin((6.0d * d) * b)) + (20.0d * Math.sin((2.0d * d) * b))) * 2.0d) / 3.0d)) + ((((20.0d * Math.sin(b * d)) + (40.0d * Math.sin((d / 3.0d) * b))) * 2.0d) / 3.0d)) + ((((150.0d * Math.sin((d / 12.0d) * b)) + (300.0d * Math.sin((d / 30.0d) * b))) * 2.0d) / 3.0d);
    }

    private static b g(b bVar) {
        double e = e(bVar.c - 105.0d, bVar.b - 35.0d);
        double f = f(bVar.c - 105.0d, bVar.b - 35.0d);
        double d = (bVar.b / 180.0d) * b;
        double sin = Math.sin(d);
        sin = 1.0d - (sin * (0.006693421622965943d * sin));
        double sqrt = Math.sqrt(sin);
        return new b((e * 180.0d) / ((((1.0d - 0.006693421622965943d) * 6378245.0d) / (sin * sqrt)) * b), (180.0d * f) / (((6378245.0d / sqrt) * Math.cos(d)) * b));
    }

    public static b a(b bVar) {
        if (!f(bVar)) {
            return bVar;
        }
        b g = g(bVar);
        return new b(bVar.b + g.b, g.c + bVar.c);
    }

    public static b b(b bVar) {
        if (!f(bVar)) {
            return bVar;
        }
        b g = g(bVar);
        return new b(bVar.b - g.b, bVar.c - g.c);
    }

    public static b c(b bVar) {
        b g = g(bVar);
        return new b(bVar.b + g.b, g.c + bVar.c);
    }

    public static b d(b bVar) {
        b g = g(bVar);
        return new b(bVar.b - g.b, bVar.c - g.c, bVar.d, bVar.e);
    }

    public static b e(b bVar) {
        double d = 0.0d;
        double d2 = bVar.b - 0.01d;
        double d3 = bVar.c - 0.01d;
        double d4 = bVar.b + 0.01d;
        int i = 0;
        double d5 = bVar.c + 0.01d;
        double d6 = 0.0d;
        while (i < 30) {
            d = (d2 + d4) / 2.0d;
            d6 = (d3 + d5) / 2.0d;
            b bVar2 = new b(d, d6);
            b a = a(new b(d, d6));
            double d7 = a.b - bVar.b;
            double d8 = a.c - bVar.c;
            if (Math.abs(d7) < 1.0E-6d && Math.abs(d8) < 1.0E-6d) {
                return bVar2;
            }
            double d9;
            if (d7 > 0.0d) {
                d9 = d;
                d4 = d2;
            } else {
                d9 = d4;
                d4 = d;
            }
            if (d8 > 0.0d) {
                d5 = d6;
            } else {
                d3 = d6;
            }
            i++;
            d2 = d4;
            d4 = d9;
        }
        return new b(d, d6);
    }

    public static double a(double d, double d2, double d3, double d4) {
        double cos = ((Math.cos((b * d) / 180.0d) * Math.cos((b * d3) / 180.0d)) * Math.cos(((d2 - d4) * b) / 180.0d)) + (Math.sin((b * d) / 180.0d) * Math.sin((b * d3) / 180.0d));
        if (cos > 1.0d) {
            cos = 1.0d;
        }
        if (cos < Map.MOVE_PRESERVE_ZOOM_LEVEL) {
            cos = Map.MOVE_PRESERVE_ZOOM_LEVEL;
        }
        return Math.acos(cos) * 6371000.0d;
    }

    public static boolean f(b bVar) {
        if (!a) {
            return false;
        }
        for (a a : c) {
            if (a(a, bVar)) {
                for (a a2 : d) {
                    if (a(a2, bVar)) {
                        return false;
                    }
                }
                return true;
            }
        }
        return false;
    }

    private static boolean a(a aVar, b bVar) {
        return aVar.a <= bVar.c && aVar.c >= bVar.c && aVar.b >= bVar.b && aVar.d <= bVar.b;
    }

    public static boolean a(double d, double d2) {
        if (d > 25.0d && d < 49.0d && d2 > -130.0d && d2 < -70.0d) {
            return true;
        }
        if (d > 60.0d && d < 70.0d && d2 > -170.0d && d2 < -140.0d) {
            return true;
        }
        if (d <= 19.0d || d >= 23.0d || d2 <= -180.0d || d2 >= -150.0d) {
            return false;
        }
        return true;
    }

    public static boolean b(double d, double d2) {
        return a("JPN", d, d2);
    }

    public static boolean c(double d, double d2) {
        return a("CHN", d, d2);
    }

    public static boolean d(double d, double d2) {
        return a("USA", d, d2);
    }

    public static boolean a(String str, double d, double d2) {
        ArrayList arrayList = null;
        for (CountryItem countryItem : e) {
            ArrayList arrayList2;
            if (countryItem.id.equals(str)) {
                arrayList2 = countryItem.geometry.coordinates;
            } else {
                arrayList2 = arrayList;
            }
            arrayList = arrayList2;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            if (a(d, d2, (ArrayList) ((ArrayList) it.next()).get(0))) {
                return true;
            }
        }
        return false;
    }

    public static void a(Context context) {
        InputStream openRawResource = context.getResources().openRawResource(R.raw.min_country_border);
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(openRawResource, "utf-8");
            char[] cArr = new char[openRawResource.available()];
            inputStreamReader.read(cArr);
            e = h.a(String.valueOf(cArr), new 1());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    private static boolean a(double d, double d2, ArrayList<ArrayList<Double>> arrayList) {
        if (arrayList.size() <= 3) {
            return false;
        }
        boolean z = false;
        int size = arrayList.size() - 1;
        for (int i = 0; i < arrayList.size(); i++) {
            boolean z2;
            if (Math.abs(((Double) ((ArrayList) arrayList.get(size)).get(1)).doubleValue() - ((Double) ((ArrayList) arrayList.get(i)).get(1)).doubleValue()) < 1.0E-6d) {
                z2 = z;
            } else {
                double doubleValue = (((((Double) ((ArrayList) arrayList.get(size)).get(0)).doubleValue() - ((Double) ((ArrayList) arrayList.get(i)).get(0)).doubleValue()) * (d - ((Double) ((ArrayList) arrayList.get(size)).get(1)).doubleValue())) / (((Double) ((ArrayList) arrayList.get(size)).get(1)).doubleValue() - ((Double) ((ArrayList) arrayList.get(i)).get(1)).doubleValue())) + ((Double) ((ArrayList) arrayList.get(i)).get(0)).doubleValue();
                if ((((((Double) ((ArrayList) arrayList.get(i)).get(1)).doubleValue() > d ? 1 : (((Double) ((ArrayList) arrayList.get(i)).get(1)).doubleValue() == d ? 0 : -1)) > 0 ? 1 : null) != ((((Double) ((ArrayList) arrayList.get(size)).get(1)).doubleValue() > d ? 1 : (((Double) ((ArrayList) arrayList.get(size)).get(1)).doubleValue() == d ? 0 : -1)) > 0 ? 1 : null) ? 1 : null) == null || d2 >= doubleValue) {
                    z2 = z;
                } else {
                    boolean z3;
                    if (z) {
                        z3 = false;
                    } else {
                        z3 = true;
                    }
                    z2 = z3;
                }
            }
            z = z2;
            size = i;
        }
        return z;
    }
}
