package com.amap.api.mapcore.util;

import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class dn {

    private static class a {
        String a;
        String b;
        String c;
        String d;
        String e;
        String f;
        String g;
        String h;
        String i;
        String j;
        String k;
        String l;
        String m;
        String n;
        String o;
        String p;
        String q;
        String r;
        String s;
        String t;

        private a() {
        }
    }

    public static String a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = ds.b(dl.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            eb.a(th, "CInfo", "Scode");
        }
        return str3;
    }

    public static String a() {
        String str;
        Throwable th;
        Throwable th2;
        String str2 = null;
        try {
            str2 = String.valueOf(System.currentTimeMillis());
            try {
                int length = str2.length();
                str = str2.substring(0, length - 2) + "1" + str2.substring(length - 1);
            } catch (Throwable th3) {
                th = th3;
                str = str2;
                th2 = th;
                eb.a(th2, "CInfo", "getTS");
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            eb.a(th2, "CInfo", "getTS");
            return str;
        }
        return str;
    }

    public static byte[] a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = dx.a(context);
        if (a == null) {
            return null;
        }
        Object a2 = dr.a(encoded, a);
        Object a3 = dr.a(encoded, bArr);
        byte[] bArr2 = new byte[(a2.length + a3.length)];
        System.arraycopy(a2, 0, bArr2, 0, a2.length);
        System.arraycopy(a3, 0, bArr2, a2.length, a3.length);
        return bArr2;
    }

    @Deprecated
    public static String a(Context context, dv dvVar, Map<String, String> map, boolean z) {
        try {
            return a(context, a(context, z));
        } catch (Throwable th) {
            eb.a(th, "CInfo", "rsaLocClineInfo");
            return null;
        }
    }

    public static String b(Context context, byte[] bArr) {
        try {
            return d(context, bArr);
        } catch (Throwable th) {
            eb.a(th, "CInfo", "AESData");
            return "";
        }
    }

    public static byte[] c(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key a = dx.a(context);
        if (bArr.length <= 117) {
            return dr.a(bArr, a);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        Object a2 = dr.a(bArr2, a);
        Object obj = new byte[((bArr.length + 128) - 117)];
        System.arraycopy(a2, 0, obj, 0, 128);
        System.arraycopy(bArr, 117, obj, 128, bArr.length - 117);
        return obj;
    }

    private static String a(Context context, a aVar) {
        return dr.a(b(context, aVar));
    }

    private static byte[] b(Context context, a aVar) {
        ByteArrayOutputStream byteArrayOutputStream;
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        try {
            byteArrayOutputStream = new ByteArrayOutputStream();
            try {
                a(byteArrayOutputStream, aVar.a);
                a(byteArrayOutputStream, aVar.b);
                a(byteArrayOutputStream, aVar.c);
                a(byteArrayOutputStream, aVar.d);
                a(byteArrayOutputStream, aVar.e);
                a(byteArrayOutputStream, aVar.f);
                a(byteArrayOutputStream, aVar.g);
                a(byteArrayOutputStream, aVar.h);
                a(byteArrayOutputStream, aVar.i);
                a(byteArrayOutputStream, aVar.j);
                a(byteArrayOutputStream, aVar.k);
                a(byteArrayOutputStream, aVar.l);
                a(byteArrayOutputStream, aVar.m);
                a(byteArrayOutputStream, aVar.n);
                a(byteArrayOutputStream, aVar.o);
                a(byteArrayOutputStream, aVar.p);
                a(byteArrayOutputStream, aVar.q);
                a(byteArrayOutputStream, aVar.r);
                a(byteArrayOutputStream, aVar.s);
                a(byteArrayOutputStream, aVar.t);
                bArr = a(context, byteArrayOutputStream);
                if (byteArrayOutputStream != null) {
                    try {
                        byteArrayOutputStream.close();
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                }
            } catch (Throwable th4) {
                th3 = th4;
                try {
                    eb.a(th3, "CInfo", "InitXInfo");
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    return bArr;
                } catch (Throwable th5) {
                    th2 = th5;
                    if (byteArrayOutputStream != null) {
                        try {
                            byteArrayOutputStream.close();
                        } catch (Throwable th322) {
                            th322.printStackTrace();
                        }
                    }
                    throw th2;
                }
            }
        } catch (Throwable th3222) {
            byteArrayOutputStream = bArr;
            th2 = th3222;
            if (byteArrayOutputStream != null) {
                byteArrayOutputStream.close();
            }
            throw th2;
        }
        return bArr;
    }

    private static byte[] a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return c(context, dx.b(byteArrayOutputStream.toByteArray()));
    }

    static String d(Context context, byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = dx.b(a(context, bArr));
        if (b != null) {
            return dr.a(b);
        }
        return "";
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            dx.a(byteArrayOutputStream, (byte) 0, new byte[0]);
            return;
        }
        byte b;
        if (str.getBytes().length > 255) {
            b = (byte) -1;
        } else {
            b = (byte) str.getBytes().length;
        }
        dx.a(byteArrayOutputStream, b, dx.a(str));
    }

    public static String e(Context context, byte[] bArr) {
        try {
            return d(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }

    public static String a(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(dl.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(dq.q(context)).append("\",\"pkg\":\"").append(dl.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(dl.b(context)).append("\",\"appversion\":\"").append(dl.d(context)).append("\",\"sysversion\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            eb.a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }

    public static String a(Context context, dv dvVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(dq.e(context)).append("\",\"sdkversion\":\"").append(dvVar.b()).append("\",\"product\":\"").append(dvVar.a()).append("\",\"ed\":\"").append(dvVar.d()).append("\",\"nt\":\"").append(dq.c(context)).append("\",\"np\":\"").append(dq.a(context)).append("\",\"mnc\":\"").append(dq.b(context)).append("\",\"ant\":\"").append(dq.d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    private static a a(Context context, boolean z) {
        a aVar = new a();
        aVar.a = dq.q(context);
        aVar.b = dq.i(context);
        String f = dq.f(context);
        if (f == null) {
            f = "";
        }
        aVar.c = f;
        aVar.d = dl.c(context);
        aVar.e = Build.MODEL;
        aVar.f = Build.MANUFACTURER;
        aVar.g = Build.DEVICE;
        aVar.h = dl.b(context);
        aVar.i = dl.d(context);
        aVar.j = String.valueOf(VERSION.SDK_INT);
        aVar.k = dq.r(context);
        aVar.l = dq.p(context);
        aVar.m = dq.m(context) + "";
        aVar.n = dq.l(context) + "";
        aVar.o = dq.s(context);
        aVar.p = dq.k(context);
        if (z) {
            aVar.q = "";
        } else {
            aVar.q = dq.h(context);
        }
        if (z) {
            aVar.r = "";
        } else {
            aVar.r = dq.g(context);
        }
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] j = dq.j(context);
            aVar.s = j[0];
            aVar.t = j[1];
        }
        return aVar;
    }
}
