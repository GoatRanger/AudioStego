package dji.thirdparty.g.c;

import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import com.here.odnp.debug.DebugFile;
import dji.pilot.phonecamera.h;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

public final class c {
    public static String a = DebugFile.EOL;
    private static long b = 0;
    private static final SimpleDateFormat c = new SimpleDateFormat("yyyy-MM-dd kk:mm:ss:SSS");

    public static void a(String str) {
        System.out.println(str);
    }

    public static void a(Object obj) {
        System.out.println(obj == null ? "null" : obj.toString());
    }

    public static String b(String str) {
        return str;
    }

    public static void a() {
        b();
    }

    public static void b() {
        System.out.print(a);
    }

    public static String a(String str, int i) {
        return b(str + ": " + i);
    }

    public static String a(String str, double d) {
        return b(str + ": " + d);
    }

    public static String a(String str, String str2) {
        return b(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2);
    }

    public static String a(String str, long j) {
        return b(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Long.toString(j));
    }

    public static String a(String str, int[] iArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (iArr == null) {
            stringBuffer.append(str + " (" + null + ")" + a);
        } else {
            stringBuffer.append(str + " (" + iArr.length + ")" + a);
            for (int i : iArr) {
                stringBuffer.append("\t" + i + a);
            }
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }

    public static String a(String str, byte[] bArr) {
        return a(str, bArr, 250);
    }

    public static String a(String str, byte[] bArr, int i) {
        StringBuffer stringBuffer = new StringBuffer();
        if (bArr == null) {
            stringBuffer.append(str + " (" + null + ")" + a);
        } else {
            stringBuffer.append(str + " (" + bArr.length + ")" + a);
            int i2 = 0;
            while (i2 < i && i2 < bArr.length) {
                char c;
                int i3 = bArr[i2] & 255;
                if (i3 == 0 || i3 == 10 || i3 == 11 || i3 == 13) {
                    c = ' ';
                } else {
                    c = (char) i3;
                }
                stringBuffer.append("\t" + i2 + ": " + i3 + " (" + c + ", 0x" + Integer.toHexString(i3) + ")" + a);
                i2++;
            }
            if (bArr.length > i) {
                stringBuffer.append("\t..." + a);
            }
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }

    public static String a(String str, char[] cArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (cArr == null) {
            stringBuffer.append(b(str + " (" + null + ")") + a);
        } else {
            stringBuffer.append(b(str + " (" + cArr.length + ")") + a);
            for (int i = 0; i < cArr.length; i++) {
                stringBuffer.append(b("\t" + cArr[i] + " (" + (cArr[i] & 255)) + ")" + a);
            }
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }

    public static String a(String str, List list) {
        StringBuffer stringBuffer = new StringBuffer();
        StringBuilder append = new StringBuilder().append(" [");
        long j = b;
        b = 1 + j;
        String stringBuilder = append.append(j).append(d.H).toString();
        stringBuffer.append(b(str + " (" + list.size() + ")" + stringBuilder) + a);
        for (int i = 0; i < list.size(); i++) {
            stringBuffer.append(b("\t" + list.get(i).toString() + stringBuilder) + a);
        }
        stringBuffer.append(a);
        return stringBuffer.toString();
    }

    public static void a(String str, Map map) {
        a(b(str, map));
    }

    public static String b(String str, Map map) {
        StringBuffer stringBuffer = new StringBuffer();
        if (map == null) {
            return b(str + " map: " + null);
        }
        ArrayList arrayList = new ArrayList(map.keySet());
        stringBuffer.append(b(str + " map: " + arrayList.size()) + a);
        for (int i = 0; i < arrayList.size(); i++) {
            Object obj = arrayList.get(i);
            stringBuffer.append(b("\t" + i + ": '" + obj + "' -> '" + map.get(obj) + "'") + a);
        }
        stringBuffer.append(a);
        return stringBuffer.toString();
    }

    public static boolean a(String str, Map map, Map map2) {
        return a(str, map, map2, null, null);
    }

    private static void a(StringBuffer stringBuffer, String str) {
        a(str);
        if (stringBuffer != null) {
            stringBuffer.append(str + a);
        }
    }

    public static boolean a(String str, Map map, Map map2, ArrayList arrayList, StringBuffer stringBuffer) {
        if (map == null && map2 == null) {
            a(stringBuffer, str + " both maps null");
            return true;
        } else if (map == null) {
            a(stringBuffer, str + " map a: null, map b: map");
            return false;
        } else if (map2 == null) {
            a(stringBuffer, str + " map a: map, map b: null");
            return false;
        } else {
            int i;
            ArrayList arrayList2 = new ArrayList(map.keySet());
            ArrayList arrayList3 = new ArrayList(map2.keySet());
            if (arrayList != null) {
                arrayList2.removeAll(arrayList);
                arrayList3.removeAll(arrayList);
            }
            boolean z = true;
            for (i = 0; i < arrayList2.size(); i++) {
                Object obj = arrayList2.get(i);
                if (arrayList3.contains(obj)) {
                    arrayList3.remove(obj);
                    Object obj2 = map.get(obj);
                    Object obj3 = map2.get(obj);
                    if (!obj2.equals(obj3)) {
                        a(stringBuffer, str + "key(" + obj + ") value a: " + obj2 + ") !=  b: " + obj3 + ")");
                        z = false;
                    }
                } else {
                    a(stringBuffer, str + "b is missing key '" + obj + "' from a");
                    z = false;
                }
            }
            i = 0;
            while (i < arrayList3.size()) {
                a(stringBuffer, str + "a is missing key '" + arrayList3.get(i) + "' from b");
                i++;
                z = false;
            }
            if (z) {
                a(stringBuffer, str + "a is the same as  b");
            }
            return z;
        }
    }

    private static final String b(int i) {
        byte b = (byte) ((i >> 24) & 255);
        byte b2 = (byte) ((i >> 16) & 255);
        byte b3 = (byte) ((i >> 8) & 255);
        byte b4 = (byte) ((i >> 0) & 255);
        char c = (char) b;
        char c2 = (char) b2;
        char c3 = (char) b3;
        char c4 = (char) b4;
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(new String(new char[]{c, c2, c3, c4}));
        stringBuffer.append(" bytequad: " + i);
        stringBuffer.append(" b1: " + b);
        stringBuffer.append(" b2: " + b2);
        stringBuffer.append(" b3: " + b3);
        stringBuffer.append(" b4: " + b4);
        return stringBuffer.toString();
    }

    public static String a(String str, boolean z) {
        return b(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (z ? "true" : h.e));
    }

    public static String a(String str, File file) {
        String str2;
        StringBuilder append = new StringBuilder().append(str).append(": ");
        if (file == null) {
            str2 = "null";
        } else {
            str2 = file.getPath();
        }
        return b(append.append(str2).toString());
    }

    public static String a(String str, Date date) {
        return a(str, date == null ? "null" : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date));
    }

    public static String a(String str, Calendar calendar) {
        return a(str, calendar == null ? "null" : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(calendar.getTime()));
    }

    public static void a(String str, Object obj) {
        if (obj == null) {
            b(str, "null");
        } else if (obj instanceof char[]) {
            b(str, (char[]) obj);
        } else if (obj instanceof byte[]) {
            b(str, (byte[]) obj);
        } else if (obj instanceof int[]) {
            b(str, (int[]) obj);
        } else if (obj instanceof String) {
            b(str, (String) obj);
        } else if (obj instanceof List) {
            b(str, (List) obj);
        } else if (obj instanceof Map) {
            a(str, (Map) obj);
        } else if (obj instanceof File) {
            b(str, (File) obj);
        } else if (obj instanceof Date) {
            b(str, (Date) obj);
        } else if (obj instanceof Calendar) {
            b(str, (Calendar) obj);
        } else {
            b(str, obj.toString());
        }
    }

    public static void a(String str, Object[] objArr) {
        if (objArr == null) {
            b(str, "null");
        }
        b(str, objArr.length);
        int i = 0;
        while (i < objArr.length && i < 10) {
            a("\t" + i, objArr[i]);
            i++;
        }
        if (objArr.length > 10) {
            a("\t...");
        }
        a();
    }

    public static String b(String str, Object obj) {
        if (obj == null) {
            return a(str, "null");
        }
        if (obj instanceof Calendar) {
            return a(str, (Calendar) obj);
        }
        if (obj instanceof Date) {
            return a(str, (Date) obj);
        }
        if (obj instanceof File) {
            return a(str, (File) obj);
        }
        if (obj instanceof Map) {
            return b(str, (Map) obj);
        }
        if (obj instanceof Map) {
            return b(str, (Map) obj);
        }
        if (obj instanceof String) {
            return a(str, (String) obj);
        }
        if (obj instanceof byte[]) {
            return a(str, (byte[]) obj);
        }
        if (obj instanceof char[]) {
            return a(str, (char[]) obj);
        }
        if (obj instanceof int[]) {
            return a(str, (int[]) obj);
        }
        if (obj instanceof List) {
            return a(str, (List) obj);
        }
        return a(str, obj.toString());
    }

    public static String b(Object obj) {
        if (obj == null) {
            return "null";
        }
        if (obj instanceof Object[]) {
            return "[Object[]: " + ((Object[]) obj).length + d.H;
        }
        if (obj instanceof char[]) {
            return "[char[]: " + ((char[]) obj).length + d.H;
        }
        if (obj instanceof byte[]) {
            return "[byte[]: " + ((byte[]) obj).length + d.H;
        }
        if (obj instanceof short[]) {
            return "[short[]: " + ((short[]) obj).length + d.H;
        }
        if (obj instanceof int[]) {
            return "[int[]: " + ((int[]) obj).length + d.H;
        }
        if (obj instanceof long[]) {
            return "[long[]: " + ((long[]) obj).length + d.H;
        }
        if (obj instanceof float[]) {
            return "[float[]: " + ((float[]) obj).length + d.H;
        }
        if (obj instanceof double[]) {
            return "[double[]: " + ((double[]) obj).length + d.H;
        }
        if (obj instanceof boolean[]) {
            return "[boolean[]: " + ((boolean[]) obj).length + d.H;
        }
        return obj.getClass().getName();
    }

    public static boolean c(Object obj) {
        if (obj == null) {
            return false;
        }
        if (obj instanceof Object[]) {
            return true;
        }
        if (obj instanceof char[]) {
            return true;
        }
        if (obj instanceof byte[]) {
            return true;
        }
        if (obj instanceof short[]) {
            return true;
        }
        if (obj instanceof int[]) {
            return true;
        }
        if (obj instanceof long[]) {
            return true;
        }
        if (obj instanceof float[]) {
            return true;
        }
        if (obj instanceof double[]) {
            return true;
        }
        if (obj instanceof boolean[]) {
            return true;
        }
        return false;
    }

    public static String b(String str, Object[] objArr) {
        StringBuffer stringBuffer = new StringBuffer();
        if (objArr == null) {
            stringBuffer.append(a(str, "null") + a);
        }
        stringBuffer.append(a(str, objArr.length));
        int i = 0;
        while (i < objArr.length && i < 10) {
            stringBuffer.append(b("\t" + i, objArr[i]) + a);
            i++;
        }
        if (objArr.length > 10) {
            stringBuffer.append(b("\t...") + a);
        }
        stringBuffer.append(a);
        return stringBuffer.toString();
    }

    public static String a(Class cls, Throwable th) {
        return b(cls == null ? "[Unknown]" : cls.getName(), th);
    }

    public static void b(Class cls, Throwable th) {
        a(cls.getName(), th);
    }

    public static void b(String str, boolean z) {
        a(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + (z ? "true" : h.e));
    }

    public static void b(String str, byte[] bArr) {
        a(a(str, bArr));
    }

    public static void b(String str, char[] cArr) {
        a(a(str, cArr));
    }

    public static void b(String str, Calendar calendar) {
        b(str, calendar == null ? "null" : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(calendar.getTime()));
    }

    public static void b(String str, Date date) {
        b(str, date == null ? "null" : new SimpleDateFormat("MM/dd/yyyy HH:mm:ss").format(date));
    }

    public static void b(String str, double d) {
        a(str + ": " + d);
    }

    public static void b(String str, File file) {
        a(str + ": " + (file == null ? "null" : file.getPath()));
    }

    public static void b(String str, int i) {
        a(str + ": " + i);
    }

    public static void b(String str, int[] iArr) {
        a(a(str, iArr));
    }

    public static void b(String str, byte[] bArr, int i) {
        a(a(str, bArr, i));
    }

    public static void b(String str, List list) {
        StringBuilder append = new StringBuilder().append(" [");
        long j = b;
        b = 1 + j;
        String stringBuilder = append.append(j).append(d.H).toString();
        a(str + " (" + list.size() + ")" + stringBuilder);
        for (int i = 0; i < list.size(); i++) {
            a("\t" + list.get(i).toString() + stringBuilder);
        }
        a();
    }

    public static void b(String str, long j) {
        a(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + Long.toString(j));
    }

    public static void b(String str, String str2) {
        a(str + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + str2);
    }

    public static void a(String str, Throwable th) {
        a(b(str, th));
    }

    public static void a(Throwable th) {
        a(b(th));
    }

    public static void a(Throwable th, int i) {
        a(b(th, i));
    }

    public static void c() {
        a(a(new Exception("Stack trace"), -1, 1));
    }

    public static void a(int i) {
        a(a(new Exception("Stack trace"), i, 1));
    }

    public static String b(String str, Throwable th) {
        return str + a + b(th);
    }

    public static String b(Throwable th) {
        return b(th, -1);
    }

    public static String b(Throwable th, int i) {
        String str;
        StringBuffer stringBuffer = new StringBuffer();
        String toLowerCase = c.format(new Date()).toLowerCase();
        stringBuffer.append(a);
        StringBuilder append = new StringBuilder().append("Throwable: ");
        if (th == null) {
            str = "";
        } else {
            str = "(" + th.getClass().getName() + ")";
        }
        stringBuffer.append(append.append(str).append(":").append(toLowerCase).append(a).toString());
        StringBuilder append2 = new StringBuilder().append("Throwable: ");
        if (th == null) {
            str = "null";
        } else {
            str = th.getLocalizedMessage();
        }
        stringBuffer.append(append2.append(str).append(a).toString());
        stringBuffer.append(a);
        stringBuffer.append(c(th, i));
        stringBuffer.append("Caught here:" + a);
        stringBuffer.append(a(new Exception(), i, 1));
        stringBuffer.append(a);
        return stringBuffer.toString();
    }

    public static String c(Throwable th) {
        return c(th, -1);
    }

    public static String c(Throwable th, int i) {
        return a(th, i, 0);
    }

    public static String a(Throwable th, int i, int i2) {
        StringBuffer stringBuffer = new StringBuffer();
        if (th != null) {
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                while (i2 < stackTrace.length && (i < 0 || i2 < i)) {
                    StackTraceElement stackTraceElement = stackTrace[i2];
                    stringBuffer.append("\tat " + stackTraceElement.getClassName() + "." + stackTraceElement.getMethodName() + "(" + stackTraceElement.getFileName() + ":" + stackTraceElement.getLineNumber() + ")" + a);
                    i2++;
                }
                if (i >= 0 && stackTrace.length > i) {
                    stringBuffer.append("\t..." + a);
                }
            }
            stringBuffer.append(a);
        }
        return stringBuffer.toString();
    }

    public static void c(String str, int i) {
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = (i >> 0) & 255;
        System.out.println(str + ": alpha: " + ((i >> 24) & 255) + ", red: " + i2 + ", green: " + i3 + ", blue: " + i4);
    }

    public static void d(String str, int i) {
        int i2 = (i >> 16) & 255;
        int i3 = (i >> 8) & 255;
        int i4 = (i >> 0) & 255;
        System.out.println(str + ": b1: " + ((i >> 24) & 255) + ", b2: " + i2 + ", b3: " + i3 + ", b4: " + i4);
    }

    public static void c(String str, byte[] bArr) {
        System.out.print(str + ": ");
        if (bArr == null) {
            System.out.print("null");
        } else {
            for (int i = 0; i < bArr.length; i++) {
                if (i > 0) {
                    System.out.print(".");
                }
                System.out.print(bArr[i] & 255);
            }
        }
        System.out.println();
    }

    public static void c(String str, Object obj) {
        int i = 0;
        if (obj == null) {
            b(str, "null");
        } else if (obj instanceof Object[]) {
            Object[] objArr = (Object[]) obj;
            a(str, objArr);
            while (i < objArr.length) {
                c(str + "\t" + i + ": ", objArr[i]);
                i++;
            }
        } else if (obj instanceof int[]) {
            int[] iArr = (int[]) obj;
            b(str, iArr);
            while (i < iArr.length) {
                b(str + "\t" + i + ": ", iArr[i]);
                i++;
            }
        } else if (obj instanceof char[]) {
            b(str, d.G + new String((char[]) obj) + d.H);
        } else if (obj instanceof long[]) {
            obj = (long[]) obj;
            a(str, obj);
            while (i < obj.length) {
                b(str + "\t" + i + ": ", obj[i]);
                i++;
            }
        } else if (obj instanceof boolean[]) {
            obj = (boolean[]) obj;
            a(str, obj);
            while (i < obj.length) {
                b(str + "\t" + i + ": ", obj[i]);
                i++;
            }
        } else if (obj instanceof byte[]) {
            byte[] bArr = (byte[]) obj;
            b(str, bArr);
            while (i < bArr.length) {
                b(str + "\t" + i + ": ", bArr[i]);
                i++;
            }
        } else if (obj instanceof float[]) {
            obj = (float[]) obj;
            a(str, obj);
            while (i < obj.length) {
                b(str + "\t" + i + ": ", (double) obj[i]);
                i++;
            }
        } else if (obj instanceof byte[]) {
            obj = (double[]) obj;
            a(str, obj);
            while (i < obj.length) {
                b(str + "\t" + i + ": ", obj[i]);
                i++;
            }
        } else if (obj instanceof List) {
            List list = (List) obj;
            b(str, "list");
            while (i < list.size()) {
                c(str + "\tlist: " + i + ": ", list.get(i));
                i++;
            }
        } else if (obj instanceof Map) {
            Map map = (Map) obj;
            b(str, "map");
            ArrayList arrayList = new ArrayList(map.keySet());
            Collections.sort(arrayList);
            while (i < arrayList.size()) {
                Object obj2 = arrayList.get(i);
                c(str + "\tmap: " + obj2 + " -> ", map.get(obj2));
                i++;
            }
        } else {
            b(str, obj.toString());
            b(str + "\t", obj.getClass().getName());
        }
    }

    public static final void d() {
        try {
            System.runFinalization();
            Thread.sleep(50);
            System.gc();
            Thread.sleep(50);
        } catch (Throwable th) {
            a(th);
        }
    }
}
