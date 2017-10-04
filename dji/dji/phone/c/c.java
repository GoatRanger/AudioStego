package dji.phone.c;

import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.Log;
import dji.pilot.d.a;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class c {
    public static HashMap<Integer, String> a = new HashMap();
    private static final String b = c.class.getSimpleName();
    private static a c = new a();

    private static List<String> b() {
        return a.c().q();
    }

    public static String[] a(String[] strArr) {
        a a = a();
        String[] strArr2 = new String[a.a()];
        for (int i = 0; i < a.a(); i++) {
            strArr2[i] = strArr[a.b(i)];
        }
        return strArr2;
    }

    public static a b(String[] strArr) {
        if (strArr == null && strArr.length == 0) {
            a(true);
        }
        a aVar = new a();
        for (int i = 0; i < strArr.length; i++) {
            if (strArr[i].equals("4K")) {
                aVar.a(8);
            } else if (strArr[i].equals("1080P")) {
                aVar.a(6);
            } else if (strArr[i].equals(dji.pilot.phonecamera.a.c.A)) {
                aVar.a(5);
            } else if (strArr[i].equals("480P")) {
                aVar.a(4);
            }
        }
        return aVar;
    }

    public static int[] a(Resources resources, TypedArray typedArray, int[] iArr) {
        if (typedArray == null || iArr == null || typedArray.length() == 0 || iArr.length == 0) {
            return null;
        }
        int length = iArr.length;
        int[] iArr2 = new int[length];
        for (int i = 0; i < length; i++) {
            iArr2[i] = typedArray.getResourceId(iArr[i], 0);
        }
        typedArray.recycle();
        return iArr2;
    }

    public static List<String> a(String[] strArr, List<String> list) {
        List<String> arrayList = new ArrayList();
        if (strArr == null && (list == null || list.size() == 0)) {
            a(true);
        } else if (strArr == null) {
            return list;
        } else {
            for (int i = 0; i < strArr.length; i++) {
                if (list.contains(strArr[i])) {
                    arrayList.add(strArr[i]);
                }
            }
        }
        return arrayList;
    }

    public static int[] a(int[] iArr) {
        a a = a();
        int[] iArr2 = new int[a.a()];
        for (int i = 0; i < a.a(); i++) {
            iArr2[i] = iArr[a.b(i)];
        }
        return iArr2;
    }

    public static String a(int i) {
        if (i == 0) {
            return "off";
        }
        if (i == 1) {
            return "on";
        }
        if (i == 2) {
            return "auto";
        }
        if (i == 3) {
            return "torch";
        }
        return "off";
    }

    public static a a() {
        int i = 0;
        if (!a.isEmpty() && c.a() > 0) {
            return c;
        }
        List b = b();
        if (b == null) {
            while (i < 7) {
                c.a(i);
                i++;
            }
            return c;
        }
        if (b.contains("auto")) {
            c.a(0);
            a.put(Integer.valueOf(0), "auto");
        }
        if (b.contains("incandescent")) {
            c.a(1);
            a.put(Integer.valueOf(1), "incandescent");
        }
        if (b.contains("fluorescent")) {
            c.a(2);
            a.put(Integer.valueOf(2), "fluorescent");
        }
        if (b.contains("daylight")) {
            c.a(3);
            a.put(Integer.valueOf(3), "daylight");
        }
        if (b.contains("cloudy-daylight")) {
            c.a(4);
            a.put(Integer.valueOf(4), "cloudy-daylight");
        }
        if (b.contains("shade")) {
            c.a(5);
            a.put(Integer.valueOf(5), "shade");
        }
        if (b.contains("twilight")) {
            c.a(6);
            a.put(Integer.valueOf(6), "twilight");
        }
        return c;
    }

    private static void a(boolean z) {
        if (!z) {
            Log.e(b, "Camera don't open!!");
            throw new AssertionError();
        }
    }
}
