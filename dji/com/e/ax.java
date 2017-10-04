package com.e;

import android.content.Context;
import android.net.NetworkInfo;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import com.autonavi.aps.amapapi.model.AmapLoc;
import com.e.as.c;
import com.e.bd.a;
import dji.pilot.usercenter.protocol.d;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map.Entry;
import org.json.JSONObject;

public class ax {
    public static final int[] a = new int[]{0, 0};
    static int b = 213891;
    private static volatile String c = null;
    private static Hashtable<String, Long> d = new Hashtable();
    private static bi e = new bi();
    private static Hashtable<String, String> f = new Hashtable();
    private static TelephonyManager g = null;

    static int a(int i) {
        int i2 = 0;
        int[] iArr = new int[32];
        int i3 = 0;
        while (i2 < 4) {
            iArr[i2] = (i >> (i2 * 8)) & 255;
            iArr[i2] = ((iArr[i2] << 4) & 240) + ((iArr[i2] >> 4) & 15);
            i3 += (iArr[i2] & 255) << ((3 - i2) * 8);
            i2++;
        }
        return b + i3;
    }

    private static int a(int i, bd bdVar, String str, int[] iArr, int i2, int i3, String str2, int i4) {
        int i5 = i4 + 1;
        if (i5 > 25) {
            return -1;
        }
        int i6 = (((((i2 + i3) / 2) - i) / 16) * 16) + i;
        int a = a(bdVar, str, iArr, i6, str2);
        if (i2 == i6 && i6 == i3) {
            if (a != 0) {
                i2 = -1;
            }
            return i2;
        } else if (a == Integer.MAX_VALUE) {
            return -1;
        } else {
            if (a == 0) {
                return i6;
            }
            if (a < 0) {
                return a(i, bdVar, str, iArr, i2, i6, str2, i5);
            }
            return a(i, bdVar, str, iArr, i6 + 16, i3, str2, i5);
        }
    }

    private static int a(bd bdVar, String str, int[] iArr, int i, String str2) {
        try {
            bdVar.a((long) i);
            int i2;
            int i3;
            if (str2.equals("gsm")) {
                i2 = iArr[0];
                i3 = iArr[1];
                int d = bdVar.d();
                int e = bdVar.e();
                return i2 < d ? -1 : i2 > d ? 1 : i3 >= e ? i3 > e ? 1 : 0 : -1;
            } else if (str2.equals("cdma")) {
                r4 = new int[]{iArr[0], iArr[1], iArr[2]};
                int[] iArr2 = new int[3];
                for (i2 = 0; i2 < 3; i2++) {
                    iArr2[i2] = bdVar.d();
                    if (r4[i2] < iArr2[i2]) {
                        return -1;
                    }
                    if (r4[i2] > iArr2[i2]) {
                        return 1;
                    }
                }
                return 0;
            } else {
                if (str2.equals("wifi")) {
                    byte[] b = br.b(str);
                    int[] iArr3 = new int[6];
                    i3 = 0;
                    while (i3 < 6) {
                        iArr3[i3] = b[i3] < (byte) 0 ? b[i3] + 256 : b[i3];
                        i3++;
                    }
                    r4 = new int[6];
                    for (i2 = 0; i2 < 6; i2++) {
                        r4[i2] = bdVar.f();
                        if (iArr3[i2] < r4[i2]) {
                            return -1;
                        }
                        if (iArr3[i2] > r4[i2]) {
                            return 1;
                        }
                    }
                    return 0;
                }
                return Integer.MAX_VALUE;
            }
        } catch (Throwable th) {
            bc.a(th, "Off", "cmpItem");
        }
    }

    private static int a(String str) {
        if (TextUtils.isEmpty(str) || !str.contains("cgi")) {
            return 9;
        }
        String[] split = str.split("#");
        return split.length == 7 ? 1 : split.length == 8 ? 2 : 9;
    }

    private static AmapLoc a(Hashtable<String, String> hashtable, Hashtable<String, String> hashtable2, int i, int i2) {
        String str;
        ArrayList a;
        AmapLoc amapLoc;
        as asVar = new as();
        if (!hashtable.isEmpty()) {
            for (Entry value : hashtable.entrySet()) {
                str = (String) value.getValue();
                int i3 = str.contains("access") ? 1 : 0;
                if (str.contains("|")) {
                    try {
                        asVar.a(i3 != 0 ? 1 : 2, str.substring(0, str.lastIndexOf("|")));
                    } catch (Throwable th) {
                        bc.a(th, "Off", "calLoc part3");
                    }
                }
            }
        }
        if (!hashtable2.isEmpty()) {
            for (Entry value2 : hashtable2.entrySet()) {
                str = (String) value2.getValue();
                if (str.contains("|")) {
                    try {
                        asVar.a(0, str.substring(0, str.lastIndexOf("|")));
                    } catch (Throwable th2) {
                        bc.a(th2, "Off", "calLoc part2");
                    }
                }
            }
        }
        try {
            a = asVar.a((double) i2, (double) i);
        } catch (Throwable th22) {
            bc.a(th22, "Off", "calLoc part4");
            a = null;
        }
        if (a == null || a.isEmpty()) {
            amapLoc = null;
        } else {
            c cVar = (c) a.get(0);
            amapLoc = null == null ? new AmapLoc() : null;
            amapLoc.setProvider("network");
            amapLoc.setLat(cVar.a);
            amapLoc.setLon(cVar.b);
            amapLoc.setAccuracy((float) cVar.c);
            amapLoc.setAdcode(cVar.d);
            amapLoc.setCoord("0");
            amapLoc.setTime(br.a());
            a.clear();
        }
        if (!br.a(amapLoc)) {
            return null;
        }
        amapLoc.setType(d.A);
        return amapLoc;
    }

    public static AmapLoc a(double[] dArr, String str, String str2, String str3, int i, Context context, int[] iArr) {
        bd bdVar;
        Throwable e;
        if (TextUtils.isEmpty(str2)) {
            return null;
        }
        if (str2.contains("gps")) {
            return null;
        }
        int a = a(str2);
        String a2 = a(a, str2);
        Hashtable hashtable = new Hashtable();
        a(a, str2, str3, hashtable);
        Hashtable hashtable2 = new Hashtable();
        a(str3, hashtable2);
        StringBuilder c = c();
        String[] a3 = dArr == null ? a(0.0d, 0.0d, str) : a(dArr[0], dArr[1], str);
        int length = a3.length / 2;
        if (1 > i || i > 3) {
            i = 1;
        }
        bc.n = hashtable.size();
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (i4 < a3.length && bc.m) {
            int i5;
            if ((i4 >= length || (i3 <= 0 && !hashtable.isEmpty())) && ((i4 < length || (i2 <= 0 && !hashtable2.isEmpty())) && i4 >= length && i3 > 0)) {
            }
            if (i == 1) {
                if (i4 != 0) {
                }
            } else if (i == 2) {
                if (i4 > 8 && i4 < 25) {
                    i5 = i2;
                    i2 = i3;
                    i3 = i5;
                    i4++;
                    i5 = i3;
                    i3 = i2;
                    i2 = i5;
                } else if (i4 > 33) {
                }
            }
            String stringBuilder = c.toString();
            if (i4 < length) {
                switch (a) {
                    case 1:
                        iArr[0] = 0;
                        iArr[1] = 0;
                        break;
                    case 2:
                        break;
                    default:
                        iArr[0] = 0;
                        iArr[1] = 0;
                        break;
                }
                stringBuilder = stringBuilder + a2 + File.separator;
                stringBuilder = a3[i4].startsWith("-") ? stringBuilder + a3[i4].substring(0, 4) + "," : stringBuilder + a3[i4].substring(0, 3) + ",";
                int indexOf = a3[i4].indexOf(",") + 1;
                stringBuilder = a3[i4].substring(indexOf, indexOf + 1).startsWith("-") ? stringBuilder + a3[i4].substring(indexOf, indexOf + 4) : stringBuilder + a3[i4].substring(indexOf, indexOf + 3);
                String str4 = (stringBuilder + File.separator) + a3[i4];
                if (str4.equals(c)) {
                    i5 = i2;
                    i2 = i3;
                    i3 = i5;
                } else {
                    bi biVar = e;
                    bd bdVar2 = (bd) biVar.b(str4);
                    Object obj = null;
                    File file = new File(str4);
                    if (bdVar2 != null) {
                        obj = 1;
                        bdVar = bdVar2;
                    } else if (!file.getParentFile().exists()) {
                        i5 = i2;
                        i2 = i3;
                        i3 = i5;
                    } else if (file.isDirectory()) {
                        i5 = i2;
                        i2 = i3;
                        i3 = i5;
                    } else if (file.exists()) {
                        a aVar = new a();
                        try {
                            bdVar2 = new bd(file, aVar);
                        } catch (Throwable e2) {
                            bc.a(e2, "Off", "search part1");
                            i5 = i2;
                            i2 = i3;
                            i3 = i5;
                        } catch (Throwable e22) {
                            Throwable th = e22;
                            bdVar2 = null;
                            bc.a(th, "Off", "search part3");
                        }
                        bdVar = bdVar2;
                    } else {
                        i5 = i2;
                        i2 = i3;
                        i3 = i5;
                    }
                    int i6 = 0;
                    if (bdVar == null) {
                        i5 = i2;
                        i2 = i3;
                        i3 = i5;
                    } else {
                        try {
                            bdVar.a(0);
                            long c2 = bdVar.c();
                            if (i4 < length) {
                                i6 = bdVar.d();
                            }
                            try {
                                long g = bdVar.g();
                                int i7 = 8;
                                if (i4 < length) {
                                    i7 = ((i6 * 4) + 2) + 8;
                                }
                                if (c2 + 7776000000L < br.a()) {
                                    if (bdVar != null) {
                                        if (obj != null) {
                                            try {
                                                biVar.c(str4);
                                            } catch (Throwable e222) {
                                                bc.a(e222, "Off", "search part6");
                                            }
                                        } else {
                                            bdVar.b();
                                        }
                                    }
                                    file.delete();
                                    d.remove(a3[i4]);
                                    i5 = i2;
                                    i2 = i3;
                                    i3 = i5;
                                } else if (g <= 8 || (g - ((long) i7)) % 16 != 0) {
                                    if (bdVar != null) {
                                        try {
                                            bdVar.b();
                                        } catch (Throwable e2222) {
                                            bc.a(e2222, "Off", "search part7");
                                        }
                                    }
                                    file.delete();
                                    d.remove(a3[i4]);
                                    i5 = i2;
                                    i2 = i3;
                                    i3 = i5;
                                } else {
                                    int i8;
                                    double[] a4;
                                    Entry entry;
                                    Object obj2 = (i4 >= length || hashtable.isEmpty() || i3 >= bc.n) ? null : 1;
                                    Object obj3 = (i4 < length || hashtable2.isEmpty() || i2 >= 15) ? null : 1;
                                    if (obj2 != null) {
                                        try {
                                            i8 = i3;
                                            for (Entry entry2 : hashtable.entrySet()) {
                                                try {
                                                    a4 = a(i7, bdVar, ((String) entry2.getKey()).toString(), 0);
                                                    if (a4 != null) {
                                                        i8++;
                                                        hashtable.put(((String) entry2.getKey()).toString(), ((a4[0] + "|" + a4[1]) + "|" + a4[2] + "|") + ((String) hashtable.get(((String) entry2.getKey()).toString())));
                                                        if (i8 >= bc.n) {
                                                        }
                                                    }
                                                    i8 = i8;
                                                } catch (Throwable th2) {
                                                    e2222 = th2;
                                                }
                                            }
                                        } catch (Throwable th3) {
                                            e2222 = th3;
                                            i8 = i3;
                                            bc.a(e2222, "Off", "search part8");
                                            i3 = i8;
                                            if (bdVar != null) {
                                                if (!bdVar.a()) {
                                                    try {
                                                        bdVar.b();
                                                        i5 = i2;
                                                        i2 = i3;
                                                        i3 = i5;
                                                    } catch (Throwable e22222) {
                                                        bc.a(e22222, "Off", "search part9");
                                                        i5 = i2;
                                                        i2 = i3;
                                                        i3 = i5;
                                                    }
                                                    i4++;
                                                    i5 = i3;
                                                    i3 = i2;
                                                    i2 = i5;
                                                } else if (obj == null) {
                                                    biVar.b(str4, bdVar);
                                                }
                                            }
                                            i5 = i2;
                                            i2 = i3;
                                            i3 = i5;
                                            i4++;
                                            i5 = i3;
                                            i3 = i2;
                                            i2 = i5;
                                        }
                                    } else {
                                        i8 = i3;
                                    }
                                    if (obj3 != null) {
                                        Iterator it = hashtable2.entrySet().iterator();
                                        while (it != null && it.hasNext()) {
                                            entry2 = (Entry) it.next();
                                            a4 = a(i7, bdVar, ((String) entry2.getKey()).toString(), 1);
                                            if (a4 != null) {
                                                i2++;
                                                hashtable2.put(((String) entry2.getKey()).toString(), ((a4[0] + "|" + a4[1]) + "|" + a4[2] + "|") + ((String) hashtable2.get(((String) entry2.getKey()).toString())));
                                                if (i2 >= 15) {
                                                }
                                            }
                                            i2 = i2;
                                        }
                                    }
                                    i3 = i8;
                                    if (bdVar != null) {
                                        if (!bdVar.a()) {
                                            bdVar.b();
                                            i5 = i2;
                                            i2 = i3;
                                            i3 = i5;
                                        } else if (obj == null) {
                                            biVar.b(str4, bdVar);
                                        }
                                    }
                                }
                            } catch (Throwable e222222) {
                                bc.a(e222222, "Off", "search part5");
                                if (obj != null) {
                                    biVar.c(str4);
                                }
                                i5 = i2;
                                i2 = i3;
                                i3 = i5;
                            }
                        } catch (Throwable e2222222) {
                            bc.a(e2222222, "Off", "search part4");
                            if (obj != null) {
                                biVar.c(str4);
                            }
                            i5 = i2;
                            i2 = i3;
                            i3 = i5;
                        }
                    }
                }
                i4++;
                i5 = i3;
                i3 = i2;
                i2 = i5;
            }
            i5 = i2;
            i2 = i3;
            i3 = i5;
            i4++;
            i5 = i3;
            i3 = i2;
            i2 = i5;
        }
        c.delete(0, c.length());
        AmapLoc a5 = a(hashtable, hashtable2, iArr[0], iArr[1]);
        return !br.a(a5) ? null : a5;
    }

    private static String a(int i, String str) {
        String[] split = str.split("#");
        switch (i) {
            case 1:
                return split[1] + "_" + split[2];
            case 2:
                return split[3];
            default:
                return null;
        }
    }

    private static String a(String str, String str2, int i) {
        if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
            return null;
        }
        StringBuilder c = c();
        int indexOf;
        switch (i) {
            case 1:
                c.append(a(a(str), str)).append(File.separator);
                if (str2.startsWith("-")) {
                    c.append(str2.substring(0, 4));
                } else {
                    c.append(str2.substring(0, 3));
                }
                c.append(",");
                indexOf = str2.indexOf(",") + 1;
                if (str2.substring(indexOf, indexOf + 1).startsWith("-")) {
                    c.append(str2.substring(indexOf, indexOf + 4));
                } else {
                    c.append(str2.substring(indexOf, indexOf + 3));
                }
                c.append(File.separator).append(str2);
                break;
            case 2:
                c.append("wifi").append(File.separator);
                c.append(str2.substring(0, 3)).append(",");
                indexOf = str2.indexOf(",") + 1;
                c.append(str2.substring(indexOf, indexOf + 3));
                c.append(File.separator).append(str2);
                break;
            default:
                return null;
        }
        return c.toString();
    }

    public static ArrayList<String> a(String str, boolean z) {
        ArrayList<String> arrayList = null;
        if (f.isEmpty()) {
            return null;
        }
        int a = a(str);
        String[] split = str.split("#");
        switch (a) {
            case 1:
            case 2:
                for (String str2 : f.keySet()) {
                    ArrayList<String> arrayList2;
                    if (((String) f.get(str2)).contains("," + split[3] + ",")) {
                        arrayList2 = arrayList == null ? new ArrayList() : arrayList;
                        arrayList2.add(str2);
                        if (z) {
                            return arrayList2;
                        }
                    } else {
                        arrayList2 = arrayList;
                    }
                    arrayList = arrayList2;
                }
                return arrayList;
            default:
                return null;
        }
    }

    public static void a() {
        e.a();
        d.clear();
        f.clear();
        a[0] = 0;
        a[1] = 0;
    }

    private static void a(int i, String str, String str2, Hashtable<String, String> hashtable) {
        String[] split = str.split("#");
        switch (i) {
            case 1:
                hashtable.put(split[3] + "|" + split[4], "access");
                if (!TextUtils.isEmpty(str2) && 0 < str2.split("#").length) {
                    return;
                }
                return;
            case 2:
                hashtable.put(split[3] + "|" + split[4] + "|" + split[5], "access");
                return;
            default:
                return;
        }
    }

    private static void a(String str, Hashtable<String, String> hashtable) {
        if (!TextUtils.isEmpty(str)) {
            String[] strArr = new String[2];
            for (String str2 : str.split("#")) {
                if (str2.contains(",")) {
                    String[] split = str2.split(",");
                    hashtable.put(split[0], split[1]);
                }
            }
        }
    }

    private static boolean a(Context context, String str, int i, boolean z, boolean z2) {
        int i2;
        boolean z3;
        if (z) {
            if (i < 25) {
                i2 = 1;
            } else {
                z3 = false;
            }
        } else if (i == 1) {
            i2 = 1;
        } else {
            z3 = false;
        }
        if (!str.contains("cgi") && r0 != 0) {
            return false;
        }
        if ((!str.contains("wifi") && r0 == 0) || a[1] > 2000) {
            return false;
        }
        NetworkInfo c = br.c(context);
        if (bk.a(c) == -1) {
            return false;
        }
        if (c.getType() != 1 && z2) {
            return false;
        }
        if (!(c.getType() == 1 || z2 || g != null)) {
            g = (TelephonyManager) br.a(context, "phone");
        }
        return true;
    }

    public static boolean a(Context context, String str, String str2, int i, int i2, boolean z, boolean z2) {
        if (!a(context, str, i, false, z)) {
            return false;
        }
        if (i2 == 0) {
            return a(context, str, str2, i, z2);
        }
        int i3 = i2 == 1 ? 8 : 24;
        String[] a = a(0.0d, 0.0d, str2);
        for (int i4 = 1; i4 < i3; i4++) {
            a(context, str, a[i4], i, z2);
        }
        return true;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private static boolean a(android.content.Context r11, java.lang.String r12, java.lang.String r13, int r14, boolean r15) {
        /*
        r0 = 2;
        r6 = new java.lang.String[r0];
        r0 = a(r12, r13, r14, r6);
        if (r0 != 0) goto L_0x000b;
    L_0x0009:
        r1 = 0;
    L_0x000a:
        return r1;
    L_0x000b:
        r0 = d;
        r1 = 1;
        r1 = r6[r1];
        r0 = r0.containsKey(r1);
        if (r0 == 0) goto L_0x003c;
    L_0x0016:
        r0 = d;
        r1 = 1;
        r1 = r6[r1];
        r0 = r0.get(r1);
        r0 = (java.lang.Long) r0;
        r0 = r0.longValue();
        r2 = com.e.br.a();
        r0 = r2 - r0;
        r2 = 86400000; // 0x5265c00 float:7.82218E-36 double:4.2687272E-316;
        r0 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1));
        if (r0 >= 0) goto L_0x0034;
    L_0x0032:
        r1 = 0;
        goto L_0x000a;
    L_0x0034:
        r0 = d;
        r1 = 1;
        r1 = r6[r1];
        r0.remove(r1);
    L_0x003c:
        r1 = 0;
        r4 = 0;
        r3 = 0;
        r2 = 0;
        com.e.br.b();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0 = new java.util.HashMap;	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0.<init>();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r5 = "v";
        r7 = 1065353216; // 0x3f800000 float:1.0 double:5.263544247E-315;
        r7 = java.lang.String.valueOf(r7);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0.put(r5, r7);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r5 = 1;
        r5 = com.e.bk.a(r11, r5);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r7 = "https://offline.aps.amap.com/LoadOfflineData/getData";
        r8 = 0;
        r8 = r6[r8];	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r9 = "UTF-8";
        r8 = r8.getBytes(r9);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r2 = r5.a(r11, r7, r0, r8);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        if (r2 != 0) goto L_0x0082;
    L_0x0069:
        if (r3 == 0) goto L_0x006e;
    L_0x006b:
        r3.close();	 Catch:{ Throwable -> 0x0314 }
    L_0x006e:
        if (r4 == 0) goto L_0x0073;
    L_0x0070:
        r4.close();	 Catch:{ Throwable -> 0x031e }
    L_0x0073:
        if (r2 == 0) goto L_0x000a;
    L_0x0075:
        r2.disconnect();	 Catch:{ Throwable -> 0x0079 }
        goto L_0x000a;
    L_0x0079:
        r0 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        com.e.bc.a(r0, r2, r3);
        goto L_0x000a;
    L_0x0082:
        r0 = r2.getResponseCode();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r5 = 200; // 0xc8 float:2.8E-43 double:9.9E-322;
        if (r0 != r5) goto L_0x0203;
    L_0x008a:
        r5 = 0;
        r0 = r2.getHeaderFields();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0 = r0.entrySet();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r7 = r0.iterator();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
    L_0x0097:
        r0 = r7.hasNext();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        if (r0 == 0) goto L_0x03ca;
    L_0x009d:
        r0 = r7.next();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0 = (java.util.Map.Entry) r0;	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r8 = "code";
        r9 = r0.getKey();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r8 = r8.equals(r9);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        if (r8 == 0) goto L_0x0097;
    L_0x00af:
        r0 = r0.getValue();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0 = (java.util.List) r0;	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r5 = 0;
        r0 = r0.get(r5);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0 = (java.lang.String) r0;	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0 = java.lang.Integer.parseInt(r0);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
    L_0x00c0:
        r5 = 260; // 0x104 float:3.64E-43 double:1.285E-321;
        if (r0 != r5) goto L_0x01e9;
    L_0x00c4:
        r0 = 1;
        r0 = r6[r0];	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        c = r0;	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0 = 1;
        r5 = r2.getInputStream();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r4 = new java.util.zip.GZIPInputStream;	 Catch:{ UnknownHostException -> 0x03b8, SocketException -> 0x039d, SocketTimeoutException -> 0x037d, EOFException -> 0x0365, Throwable -> 0x034d, all -> 0x033c }
        r4.<init>(r5);	 Catch:{ UnknownHostException -> 0x03b8, SocketException -> 0x039d, SocketTimeoutException -> 0x037d, EOFException -> 0x0365, Throwable -> 0x034d, all -> 0x033c }
        r3 = new java.io.File;	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r7 = 1;
        r7 = r6[r7];	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r3.<init>(r7);	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r7 = r3.exists();	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        if (r7 == 0) goto L_0x00e7;
    L_0x00e1:
        r7 = r3.delete();	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        if (r7 == 0) goto L_0x013f;
    L_0x00e7:
        if (r0 == 0) goto L_0x03c7;
    L_0x00e9:
        r0 = com.e.bc.m;	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        if (r0 == 0) goto L_0x03c7;
    L_0x00ed:
        r0 = r3.getParentFile();	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r7 = r0.exists();	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        if (r7 != 0) goto L_0x00fa;
    L_0x00f7:
        r0.mkdirs();	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
    L_0x00fa:
        r0 = 2048; // 0x800 float:2.87E-42 double:1.0118E-320;
        r7 = new java.io.FileOutputStream;	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r7.<init>(r3);	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r3 = new java.io.BufferedOutputStream;	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r3.<init>(r7, r0);	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r7 = new byte[r0];	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
    L_0x0108:
        r8 = 0;
        r8 = r4.read(r7, r8, r0);	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r9 = -1;
        if (r8 == r9) goto L_0x0141;
    L_0x0110:
        r9 = 0;
        r3.write(r7, r9, r8);	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        goto L_0x0108;
    L_0x0115:
        r0 = move-exception;
        r3 = r4;
        r4 = r5;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x011b:
        r5 = "Off";
        r6 = "c 2 part2";
        com.e.bc.a(r1, r5, r6);	 Catch:{ all -> 0x0348 }
        if (r3 == 0) goto L_0x0127;
    L_0x0124:
        r3.close();	 Catch:{ Throwable -> 0x02a9 }
    L_0x0127:
        if (r4 == 0) goto L_0x012c;
    L_0x0129:
        r4.close();	 Catch:{ Throwable -> 0x02b3 }
    L_0x012c:
        if (r2 == 0) goto L_0x0131;
    L_0x012e:
        r2.disconnect();	 Catch:{ Throwable -> 0x02bd }
    L_0x0131:
        r1 = c;
        r1 = android.text.TextUtils.isEmpty(r1);
        if (r1 != 0) goto L_0x013c;
    L_0x0139:
        r1 = 0;
        c = r1;
    L_0x013c:
        r1 = r0;
        goto L_0x000a;
    L_0x013f:
        r0 = 0;
        goto L_0x00e7;
    L_0x0141:
        r3.flush();	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r3.close();	 Catch:{ UnknownHostException -> 0x0115, SocketException -> 0x03a4, SocketTimeoutException -> 0x0384, EOFException -> 0x036c, Throwable -> 0x0354 }
        r0 = 1;
        r1 = d;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = 1;
        r3 = r6[r3];	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r8 = com.e.br.a();	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r1.put(r3, r7);	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r8 = 0;
        r1 = "yyyyMMdd";
        r1 = com.e.br.a(r8, r1);	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = a;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r7 = 0;
        r3 = r3[r7];	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = java.lang.String.valueOf(r3);	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = r1.equals(r3);	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        if (r3 == 0) goto L_0x01a0;
    L_0x016f:
        r1 = a;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = 1;
        r7 = a;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r8 = 1;
        r7 = r7[r8];	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r7 = r7 + 1;
        r1[r3] = r7;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
    L_0x017b:
        r1 = r4;
        r3 = r5;
        r4 = r0;
    L_0x017e:
        if (r15 == 0) goto L_0x0186;
    L_0x0180:
        r0 = 1;
        r0 = r6[r0];	 Catch:{ UnknownHostException -> 0x03bf, SocketException -> 0x03aa, SocketTimeoutException -> 0x038d, EOFException -> 0x0375, Throwable -> 0x035d, all -> 0x0343 }
        b(r0);	 Catch:{ UnknownHostException -> 0x03bf, SocketException -> 0x03aa, SocketTimeoutException -> 0x038d, EOFException -> 0x0375, Throwable -> 0x035d, all -> 0x0343 }
    L_0x0186:
        r0 = r4;
    L_0x0187:
        if (r1 == 0) goto L_0x018c;
    L_0x0189:
        r1.close();	 Catch:{ Throwable -> 0x0328 }
    L_0x018c:
        if (r3 == 0) goto L_0x0191;
    L_0x018e:
        r3.close();	 Catch:{ Throwable -> 0x0332 }
    L_0x0191:
        if (r2 == 0) goto L_0x0131;
    L_0x0193:
        r2.disconnect();	 Catch:{ Throwable -> 0x0197 }
        goto L_0x0131;
    L_0x0197:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
    L_0x019c:
        com.e.bc.a(r1, r2, r3);
        goto L_0x0131;
    L_0x01a0:
        r3 = a;	 Catch:{ Throwable -> 0x01b5, UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372 }
        r7 = 0;
        r1 = java.lang.Integer.parseInt(r1);	 Catch:{ Throwable -> 0x01b5, UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372 }
        r3[r7] = r1;	 Catch:{ Throwable -> 0x01b5, UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372 }
    L_0x01a9:
        r1 = a;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = 1;
        r7 = 1;
        r1[r3] = r7;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        goto L_0x017b;
    L_0x01b0:
        r1 = move-exception;
        r3 = r4;
        r4 = r5;
        goto L_0x011b;
    L_0x01b5:
        r1 = move-exception;
        r3 = a;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r7 = 0;
        r8 = 0;
        r3[r7] = r8;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = a;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r7 = 1;
        r8 = 0;
        r3[r7] = r8;	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        r3 = "Off";
        r7 = "c 2 part1";
        com.e.bc.a(r1, r3, r7);	 Catch:{ UnknownHostException -> 0x01b0, SocketException -> 0x01ca, SocketTimeoutException -> 0x038a, EOFException -> 0x0372, Throwable -> 0x035a }
        goto L_0x01a9;
    L_0x01ca:
        r1 = move-exception;
    L_0x01cb:
        r3 = "Off";
        r6 = "c 2 part3";
        com.e.bc.a(r1, r3, r6);	 Catch:{ all -> 0x0340 }
        if (r4 == 0) goto L_0x01d7;
    L_0x01d4:
        r4.close();	 Catch:{ Throwable -> 0x02c4 }
    L_0x01d7:
        if (r5 == 0) goto L_0x01dc;
    L_0x01d9:
        r5.close();	 Catch:{ Throwable -> 0x02ce }
    L_0x01dc:
        if (r2 == 0) goto L_0x0131;
    L_0x01de:
        r2.disconnect();	 Catch:{ Throwable -> 0x01e3 }
        goto L_0x0131;
    L_0x01e3:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019c;
    L_0x01e9:
        r0 = com.e.bc.m;	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        if (r0 == 0) goto L_0x01fd;
    L_0x01ed:
        r0 = d;	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r5 = 1;
        r5 = r6[r5];	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r8 = com.e.br.a();	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r7 = java.lang.Long.valueOf(r8);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
        r0.put(r5, r7);	 Catch:{ UnknownHostException -> 0x03b2, SocketException -> 0x0395, SocketTimeoutException -> 0x020c, EOFException -> 0x0231, Throwable -> 0x0256, all -> 0x027b }
    L_0x01fd:
        r10 = r3;
        r3 = r4;
        r4 = r1;
        r1 = r10;
        goto L_0x017e;
    L_0x0203:
        r5 = 404; // 0x194 float:5.66E-43 double:1.996E-321;
        if (r0 != r5) goto L_0x0207;
    L_0x0207:
        r0 = r1;
        r1 = r3;
        r3 = r4;
        goto L_0x0187;
    L_0x020c:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x0212:
        r3 = "Off";
        r6 = "c 2 part4";
        com.e.bc.a(r1, r3, r6);	 Catch:{ all -> 0x0340 }
        if (r4 == 0) goto L_0x021e;
    L_0x021b:
        r4.close();	 Catch:{ Throwable -> 0x02d8 }
    L_0x021e:
        if (r5 == 0) goto L_0x0223;
    L_0x0220:
        r5.close();	 Catch:{ Throwable -> 0x02e2 }
    L_0x0223:
        if (r2 == 0) goto L_0x0131;
    L_0x0225:
        r2.disconnect();	 Catch:{ Throwable -> 0x022a }
        goto L_0x0131;
    L_0x022a:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019c;
    L_0x0231:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x0237:
        r3 = "Off";
        r6 = "c 2 part5";
        com.e.bc.a(r1, r3, r6);	 Catch:{ all -> 0x0340 }
        if (r4 == 0) goto L_0x0243;
    L_0x0240:
        r4.close();	 Catch:{ Throwable -> 0x02ec }
    L_0x0243:
        if (r5 == 0) goto L_0x0248;
    L_0x0245:
        r5.close();	 Catch:{ Throwable -> 0x02f6 }
    L_0x0248:
        if (r2 == 0) goto L_0x0131;
    L_0x024a:
        r2.disconnect();	 Catch:{ Throwable -> 0x024f }
        goto L_0x0131;
    L_0x024f:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019c;
    L_0x0256:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
    L_0x025c:
        r3 = "Off";
        r6 = "c 2 part6";
        com.e.bc.a(r1, r3, r6);	 Catch:{ all -> 0x0340 }
        if (r4 == 0) goto L_0x0268;
    L_0x0265:
        r4.close();	 Catch:{ Throwable -> 0x0300 }
    L_0x0268:
        if (r5 == 0) goto L_0x026d;
    L_0x026a:
        r5.close();	 Catch:{ Throwable -> 0x030a }
    L_0x026d:
        if (r2 == 0) goto L_0x0131;
    L_0x026f:
        r2.disconnect();	 Catch:{ Throwable -> 0x0274 }
        goto L_0x0131;
    L_0x0274:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019c;
    L_0x027b:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
    L_0x027e:
        if (r4 == 0) goto L_0x0283;
    L_0x0280:
        r4.close();	 Catch:{ Throwable -> 0x028e }
    L_0x0283:
        if (r5 == 0) goto L_0x0288;
    L_0x0285:
        r5.close();	 Catch:{ Throwable -> 0x0297 }
    L_0x0288:
        if (r2 == 0) goto L_0x028d;
    L_0x028a:
        r2.disconnect();	 Catch:{ Throwable -> 0x02a0 }
    L_0x028d:
        throw r0;
    L_0x028e:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.e.bc.a(r1, r3, r4);
        goto L_0x0283;
    L_0x0297:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r1, r3, r4);
        goto L_0x0288;
    L_0x02a0:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        com.e.bc.a(r1, r2, r3);
        goto L_0x028d;
    L_0x02a9:
        r1 = move-exception;
        r3 = "Off";
        r5 = "c 2 part7";
        com.e.bc.a(r1, r3, r5);
        goto L_0x0127;
    L_0x02b3:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r1, r3, r4);
        goto L_0x012c;
    L_0x02bd:
        r1 = move-exception;
        r2 = "Off";
        r3 = "c 2 part9";
        goto L_0x019c;
    L_0x02c4:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.e.bc.a(r1, r3, r4);
        goto L_0x01d7;
    L_0x02ce:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r1, r3, r4);
        goto L_0x01dc;
    L_0x02d8:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.e.bc.a(r1, r3, r4);
        goto L_0x021e;
    L_0x02e2:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r1, r3, r4);
        goto L_0x0223;
    L_0x02ec:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.e.bc.a(r1, r3, r4);
        goto L_0x0243;
    L_0x02f6:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r1, r3, r4);
        goto L_0x0248;
    L_0x0300:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part7";
        com.e.bc.a(r1, r3, r4);
        goto L_0x0268;
    L_0x030a:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r1, r3, r4);
        goto L_0x026d;
    L_0x0314:
        r0 = move-exception;
        r3 = "Off";
        r5 = "c 2 part7";
        com.e.bc.a(r0, r3, r5);
        goto L_0x006e;
    L_0x031e:
        r0 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r0, r3, r4);
        goto L_0x0073;
    L_0x0328:
        r1 = move-exception;
        r4 = "Off";
        r5 = "c 2 part7";
        com.e.bc.a(r1, r4, r5);
        goto L_0x018c;
    L_0x0332:
        r1 = move-exception;
        r3 = "Off";
        r4 = "c 2 part8";
        com.e.bc.a(r1, r3, r4);
        goto L_0x0191;
    L_0x033c:
        r0 = move-exception;
        r4 = r3;
        goto L_0x027e;
    L_0x0340:
        r0 = move-exception;
        goto L_0x027e;
    L_0x0343:
        r0 = move-exception;
        r4 = r1;
        r5 = r3;
        goto L_0x027e;
    L_0x0348:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        goto L_0x027e;
    L_0x034d:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x025c;
    L_0x0354:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x025c;
    L_0x035a:
        r1 = move-exception;
        goto L_0x025c;
    L_0x035d:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x025c;
    L_0x0365:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0237;
    L_0x036c:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0237;
    L_0x0372:
        r1 = move-exception;
        goto L_0x0237;
    L_0x0375:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x0237;
    L_0x037d:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0212;
    L_0x0384:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x0212;
    L_0x038a:
        r1 = move-exception;
        goto L_0x0212;
    L_0x038d:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x0212;
    L_0x0395:
        r0 = move-exception;
        r5 = r4;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x01cb;
    L_0x039d:
        r0 = move-exception;
        r4 = r3;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x01cb;
    L_0x03a4:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x01cb;
    L_0x03aa:
        r0 = move-exception;
        r5 = r3;
        r10 = r1;
        r1 = r0;
        r0 = r4;
        r4 = r10;
        goto L_0x01cb;
    L_0x03b2:
        r0 = move-exception;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x011b;
    L_0x03b8:
        r0 = move-exception;
        r4 = r5;
        r10 = r0;
        r0 = r1;
        r1 = r10;
        goto L_0x011b;
    L_0x03bf:
        r0 = move-exception;
        r10 = r0;
        r0 = r4;
        r4 = r3;
        r3 = r1;
        r1 = r10;
        goto L_0x011b;
    L_0x03c7:
        r0 = r1;
        goto L_0x017b;
    L_0x03ca:
        r0 = r5;
        goto L_0x00c0;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.e.ax.a(android.content.Context, java.lang.String, java.lang.String, int, boolean):boolean");
    }

    public static boolean a(String str, String str2, int i, int i2) {
        int i3 = 0;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        if (i2 == 0) {
            String a = a(str, str2, i);
            if (a != null) {
                File file = new File(a);
                if (file.exists() && file.isFile()) {
                    file.delete();
                }
                if (f.containsKey(a)) {
                    f.remove(a);
                }
                if (d.containsKey(a)) {
                    d.remove(a);
                }
            }
            return true;
        } else if (i2 != 1 && i2 != 2) {
            return false;
        } else {
            String[] a2 = a(0.0d, 0.0d, str2);
            int i4 = i2 == 1 ? 9 : i2 == 2 ? 25 : 0;
            if (i != 1) {
                if (i != 2) {
                    return false;
                }
                i3 = 25;
            }
            Hashtable hashtable = f;
            Hashtable hashtable2 = d;
            for (i3 = 
/*
Method generation error in method: com.e.ax.a(java.lang.String, java.lang.String, int, int):boolean
jadx.core.utils.exceptions.CodegenException: Error generate insn: PHI: (r0_3 'i3' int) = (r0_0 'i3' int), (r0_7 'i3' int) binds: {(r0_0 'i3' int)=B:22:0x0052, (r0_7 'i3' int)=B:42:0x0091} in method: com.e.ax.a(java.lang.String, java.lang.String, int, int):boolean
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.RegionGen.makeLoop(RegionGen.java:184)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:61)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:128)
	at jadx.core.codegen.RegionGen.connectElseIf(RegionGen.java:146)
	at jadx.core.codegen.RegionGen.makeIf(RegionGen.java:124)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:57)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.CodegenException: PHI can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:530)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:514)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 26 more

*/

            private static boolean a(String str, String str2, int i, String[] strArr) {
                Object a;
                RandomAccessFile randomAccessFile;
                Throwable th;
                String str3;
                String str4;
                JSONObject jSONObject;
                Throwable th2;
                long j = 0;
                if (TextUtils.isEmpty(str) || TextUtils.isEmpty(str2)) {
                    return false;
                }
                if (strArr == null || strArr.length != 2) {
                    return false;
                }
                StringBuilder c = c();
                int indexOf;
                switch (i) {
                    case 1:
                        a = a(a(str), str);
                        c.append(a).append(File.separator);
                        if (str2.startsWith("-")) {
                            c.append(str2.substring(0, 4));
                        } else {
                            c.append(str2.substring(0, 3));
                        }
                        c.append(",");
                        indexOf = str2.indexOf(",") + 1;
                        if (str2.substring(indexOf, indexOf + 1).startsWith("-")) {
                            c.append(str2.substring(indexOf, indexOf + 4));
                        } else {
                            c.append(str2.substring(indexOf, indexOf + 3));
                        }
                        c.append(File.separator).append(str2);
                        break;
                    case 2:
                        a = "wifi";
                        c.append(a).append(File.separator);
                        c.append(str2.substring(0, 3)).append(",");
                        indexOf = str2.indexOf(",") + 1;
                        c.append(str2.substring(indexOf, indexOf + 3));
                        c.append(File.separator).append(str2);
                        break;
                    default:
                        return false;
                }
                strArr[1] = c.toString();
                c.delete(0, c.length());
                File file = new File(strArr[1]);
                if (file.exists() && file.isFile()) {
                    RandomAccessFile randomAccessFile2 = null;
                    try {
                        randomAccessFile = new RandomAccessFile(file, "r");
                        try {
                            randomAccessFile.seek(0);
                            j = randomAccessFile.readLong();
                            if (randomAccessFile != null) {
                                try {
                                    randomAccessFile.close();
                                } catch (Throwable th3) {
                                    th = th3;
                                    str3 = "Off";
                                    str4 = "getRequestParams part3";
                                    bc.a(th, str3, str4);
                                    jSONObject = new JSONObject();
                                    jSONObject.put("v", String.valueOf(1.0f));
                                    jSONObject.put("geohash", str2);
                                    jSONObject.put("t", String.valueOf(j));
                                    jSONObject.put("type", a);
                                    jSONObject.put("imei", bc.a);
                                    jSONObject.put("imsi", bc.b);
                                    jSONObject.put("src", bc.d);
                                    jSONObject.put("license", bc.e);
                                    strArr[0] = jSONObject.toString();
                                    return true;
                                }
                            }
                        } catch (FileNotFoundException e) {
                            th = e;
                            try {
                                bc.a(th, "Off", "getRequestParams part1");
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th4) {
                                        th = th4;
                                        str3 = "Off";
                                        str4 = "getRequestParams part3";
                                        bc.a(th, str3, str4);
                                        jSONObject = new JSONObject();
                                        jSONObject.put("v", String.valueOf(1.0f));
                                        jSONObject.put("geohash", str2);
                                        jSONObject.put("t", String.valueOf(j));
                                        jSONObject.put("type", a);
                                        jSONObject.put("imei", bc.a);
                                        jSONObject.put("imsi", bc.b);
                                        jSONObject.put("src", bc.d);
                                        jSONObject.put("license", bc.e);
                                        strArr[0] = jSONObject.toString();
                                        return true;
                                    }
                                }
                                jSONObject = new JSONObject();
                                jSONObject.put("v", String.valueOf(1.0f));
                                jSONObject.put("geohash", str2);
                                jSONObject.put("t", String.valueOf(j));
                                jSONObject.put("type", a);
                                jSONObject.put("imei", bc.a);
                                jSONObject.put("imsi", bc.b);
                                jSONObject.put("src", bc.d);
                                jSONObject.put("license", bc.e);
                                strArr[0] = jSONObject.toString();
                                return true;
                            } catch (Throwable th5) {
                                th2 = th5;
                                randomAccessFile2 = randomAccessFile;
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (Throwable th6) {
                                        bc.a(th6, "Off", "getRequestParams part3");
                                    }
                                }
                                throw th2;
                            }
                        } catch (Throwable th7) {
                            th = th7;
                            randomAccessFile2 = randomAccessFile;
                            try {
                                bc.a(th, "Off", "getRequestParams part2");
                                if (randomAccessFile2 != null) {
                                    try {
                                        randomAccessFile2.close();
                                    } catch (Throwable th8) {
                                        th = th8;
                                        str3 = "Off";
                                        str4 = "getRequestParams part3";
                                        bc.a(th, str3, str4);
                                        jSONObject = new JSONObject();
                                        jSONObject.put("v", String.valueOf(1.0f));
                                        jSONObject.put("geohash", str2);
                                        jSONObject.put("t", String.valueOf(j));
                                        jSONObject.put("type", a);
                                        jSONObject.put("imei", bc.a);
                                        jSONObject.put("imsi", bc.b);
                                        jSONObject.put("src", bc.d);
                                        jSONObject.put("license", bc.e);
                                        strArr[0] = jSONObject.toString();
                                        return true;
                                    }
                                }
                                jSONObject = new JSONObject();
                                jSONObject.put("v", String.valueOf(1.0f));
                                jSONObject.put("geohash", str2);
                                jSONObject.put("t", String.valueOf(j));
                                jSONObject.put("type", a);
                                jSONObject.put("imei", bc.a);
                                jSONObject.put("imsi", bc.b);
                                jSONObject.put("src", bc.d);
                                jSONObject.put("license", bc.e);
                                strArr[0] = jSONObject.toString();
                                return true;
                            } catch (Throwable th9) {
                                th2 = th9;
                                if (randomAccessFile2 != null) {
                                    randomAccessFile2.close();
                                }
                                throw th2;
                            }
                        }
                    } catch (FileNotFoundException e2) {
                        th = e2;
                        randomAccessFile = null;
                        bc.a(th, "Off", "getRequestParams part1");
                        if (randomAccessFile != null) {
                            randomAccessFile.close();
                        }
                        jSONObject = new JSONObject();
                        jSONObject.put("v", String.valueOf(1.0f));
                        jSONObject.put("geohash", str2);
                        jSONObject.put("t", String.valueOf(j));
                        jSONObject.put("type", a);
                        jSONObject.put("imei", bc.a);
                        jSONObject.put("imsi", bc.b);
                        jSONObject.put("src", bc.d);
                        jSONObject.put("license", bc.e);
                        strArr[0] = jSONObject.toString();
                        return true;
                    } catch (Throwable th10) {
                        th = th10;
                        bc.a(th, "Off", "getRequestParams part2");
                        if (randomAccessFile2 != null) {
                            randomAccessFile2.close();
                        }
                        jSONObject = new JSONObject();
                        jSONObject.put("v", String.valueOf(1.0f));
                        jSONObject.put("geohash", str2);
                        jSONObject.put("t", String.valueOf(j));
                        jSONObject.put("type", a);
                        jSONObject.put("imei", bc.a);
                        jSONObject.put("imsi", bc.b);
                        jSONObject.put("src", bc.d);
                        jSONObject.put("license", bc.e);
                        strArr[0] = jSONObject.toString();
                        return true;
                    }
                }
                jSONObject = new JSONObject();
                try {
                    jSONObject.put("v", String.valueOf(1.0f));
                    jSONObject.put("geohash", str2);
                    jSONObject.put("t", String.valueOf(j));
                    jSONObject.put("type", a);
                    jSONObject.put("imei", bc.a);
                    jSONObject.put("imsi", bc.b);
                    jSONObject.put("src", bc.d);
                    jSONObject.put("license", bc.e);
                } catch (Throwable th22) {
                    bc.a(th22, "Off", "getRequestParams part4");
                }
                strArr[0] = jSONObject.toString();
                return true;
            }

            private static double[] a(int i, bd bdVar, String str, int i2) {
                int[] iArr;
                int i3;
                String str2;
                Throwable th;
                if (i2 == 0) {
                    String[] split = str.split("\\|");
                    iArr = new int[split.length];
                    for (i3 = 0; i3 < split.length; i3++) {
                        iArr[i3] = Integer.parseInt(split[i3]);
                    }
                    str2 = split.length == 2 ? "gsm" : "cdma";
                } else {
                    str2 = "wifi";
                    iArr = null;
                }
                double[] dArr;
                try {
                    if (bdVar.g() > ((long) i)) {
                        bdVar.a((long) i);
                        i3 = a(i, bdVar, str, iArr, i, ((int) bdVar.g()) - 16, str2, 0);
                        if (i3 != -1) {
                            bdVar.a((long) (i3 + 6));
                            dArr = new double[3];
                            try {
                                dArr[0] = ((double) a(bdVar.e())) / 1000000.0d;
                                dArr[1] = ((double) a(bdVar.e())) / 1000000.0d;
                                dArr[2] = (double) bdVar.d();
                                return !br.a(dArr[1]) ? null : !br.b(dArr[0]) ? null : dArr;
                            } catch (Throwable th2) {
                                th = th2;
                                bc.a(th, "Off", "binS");
                                return dArr;
                            }
                        }
                    }
                    return null;
                } catch (Throwable th3) {
                    th = th3;
                    dArr = null;
                    bc.a(th, "Off", "binS");
                    return dArr;
                }
            }

            public static String[] a(double d, double d2, String str) {
                String a;
                int i;
                String[] strArr = new String[50];
                if (TextUtils.isEmpty(str)) {
                    a = aw.a(d, d2);
                    str = aw.a(d, d2);
                } else {
                    a = str;
                }
                strArr[0] = a;
                strArr[25] = str;
                String[] a2 = aw.a(a);
                for (i = 1; i < 25; i++) {
                    strArr[i] = a2[i - 1];
                }
                a2 = aw.a(str);
                for (i = 26; i < 50; i++) {
                    strArr[i] = a2[i - 26];
                }
                return strArr;
            }

            private static void b(String str) {
                Throwable th;
                String str2;
                String str3;
                if (!f.containsKey(str) || TextUtils.isEmpty((CharSequence) f.get(str))) {
                    File file = new File(str);
                    if (file.exists() && file.isFile()) {
                        RandomAccessFile randomAccessFile;
                        try {
                            randomAccessFile = new RandomAccessFile(file, "r");
                            try {
                                randomAccessFile.seek(8);
                                int readUnsignedShort = randomAccessFile.readUnsignedShort();
                                StringBuilder stringBuilder = new StringBuilder();
                                for (int i = 0; i < readUnsignedShort; i++) {
                                    int readInt = randomAccessFile.readInt();
                                    if (stringBuilder.indexOf("," + readInt) == -1) {
                                        stringBuilder.append(",").append(readInt);
                                    }
                                    if (i == readUnsignedShort - 1) {
                                        stringBuilder.append(",");
                                    }
                                }
                                f.put(str, stringBuilder.toString());
                                stringBuilder.delete(0, stringBuilder.length());
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th2) {
                                        th = th2;
                                        str2 = "Off";
                                        str3 = "loadFcFea part3";
                                        bc.a(th, str2, str3);
                                    }
                                }
                            } catch (FileNotFoundException e) {
                                th = e;
                                try {
                                    bc.a(th, "Off", "loadFcFea part1");
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (Throwable th3) {
                                            th = th3;
                                            str2 = "Off";
                                            str3 = "loadFcFea part3";
                                            bc.a(th, str2, str3);
                                        }
                                    }
                                } catch (Throwable th4) {
                                    th = th4;
                                    if (randomAccessFile != null) {
                                        try {
                                            randomAccessFile.close();
                                        } catch (Throwable th5) {
                                            bc.a(th5, "Off", "loadFcFea part3");
                                        }
                                    }
                                    throw th;
                                }
                            } catch (Throwable th6) {
                                th = th6;
                                bc.a(th, "Off", "loadFcFea part2");
                                if (randomAccessFile != null) {
                                    try {
                                        randomAccessFile.close();
                                    } catch (Throwable th7) {
                                        th = th7;
                                        str2 = "Off";
                                        str3 = "loadFcFea part3";
                                        bc.a(th, str2, str3);
                                    }
                                }
                            }
                        } catch (FileNotFoundException e2) {
                            th = e2;
                            randomAccessFile = null;
                            bc.a(th, "Off", "loadFcFea part1");
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                        } catch (Throwable th8) {
                            th = th8;
                            randomAccessFile = null;
                            if (randomAccessFile != null) {
                                randomAccessFile.close();
                            }
                            throw th;
                        }
                    }
                }
            }

            public static boolean b() {
                return !f.isEmpty();
            }

            private static StringBuilder c() {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append(br.e());
                stringBuilder.append("offline").append(File.separator);
                stringBuilder.append(br.j()).append(File.separator).append("s").append(File.separator);
                return stringBuilder;
            }
        }
