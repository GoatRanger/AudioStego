package com.e;

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
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;

public class cw {

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
                dg.a(th2, "CInfo", "getTS");
                return str;
            }
        } catch (Throwable th32) {
            th = th32;
            str = str2;
            th2 = th;
            dg.a(th2, "CInfo", "getTS");
            return str;
        }
        return str;
    }

    public static String a(Context context) {
        String str = null;
        try {
            a aVar = new a();
            aVar.a = cx.q(context);
            String f = cx.f(context);
            if (f == null) {
                f = "";
            }
            aVar.c = f;
            aVar.d = cu.c(context);
            aVar.h = cu.b(context);
            aVar.i = cu.d(context);
            aVar.j = String.valueOf(VERSION.SDK_INT);
            aVar.m = cx.m(context) + "";
            aVar.n = cx.l(context) + "";
            aVar.p = cx.k(context);
            str = a(context, aVar);
        } catch (Throwable th) {
            dg.a(th, "CInfo", "InitXInfo");
        }
        return str;
    }

    private static String a(Context context, a aVar) {
        return cy.a(b(context, aVar));
    }

    public static String a(Context context, dc dcVar) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"sim\":\"").append(cx.e(context)).append("\",\"sdkversion\":\"").append(dcVar.b()).append("\",\"product\":\"").append(dcVar.a()).append("\",\"ed\":\"").append(dcVar.c()).append("\",\"nt\":\"").append(cx.c(context)).append("\",\"np\":\"").append(cx.a(context)).append("\",\"mnc\":\"").append(cx.b(context)).append("\",\"ant\":\"").append(cx.d(context)).append("\"");
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return stringBuilder.toString();
    }

    public static String a(Context context, String str, String str2) {
        String str3 = null;
        try {
            str3 = cz.b(cu.e(context) + ":" + str.substring(0, str.length() - 3) + ":" + str2);
        } catch (Throwable th) {
            dg.a(th, "CInfo", "Scode");
        }
        return str3;
    }

    public static void a(ByteArrayOutputStream byteArrayOutputStream, String str) {
        if (TextUtils.isEmpty(str)) {
            dd.a(byteArrayOutputStream, (byte) 0, new byte[0]);
        } else {
            dd.a(byteArrayOutputStream, str.getBytes().length > 255 ? (byte) -1 : (byte) str.getBytes().length, dd.a(str));
        }
    }

    private static byte[] a(Context context, ByteArrayOutputStream byteArrayOutputStream) throws CertificateException, NoSuchAlgorithmException, IOException, BadPaddingException, IllegalBlockSizeException, NoSuchPaddingException, InvalidKeyException, InvalidKeySpecException {
        return c(context, dd.b(byteArrayOutputStream.toByteArray()));
    }

    public static byte[] a(Context context, boolean z) {
        try {
            return b(context, b(context, z));
        } catch (Throwable th) {
            dg.a(th, "CInfo", "getGZipXInfo");
            return null;
        }
    }

    public static byte[] a(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        KeyGenerator instance = KeyGenerator.getInstance("AES");
        if (instance == null) {
            return null;
        }
        instance.init(256);
        byte[] encoded = instance.generateKey().getEncoded();
        Key a = dd.a(context);
        if (a == null) {
            return null;
        }
        Object a2 = cy.a(encoded, a);
        Object a3 = cy.a(encoded, bArr);
        Object obj = new byte[(a2.length + a3.length)];
        System.arraycopy(a2, 0, obj, 0, a2.length);
        System.arraycopy(a3, 0, obj, a2.length, a3.length);
        return obj;
    }

    private static a b(Context context, boolean z) {
        a aVar = new a();
        aVar.a = cx.q(context);
        aVar.b = cx.i(context);
        String f = cx.f(context);
        if (f == null) {
            f = "";
        }
        aVar.c = f;
        aVar.d = cu.c(context);
        aVar.e = Build.MODEL;
        aVar.f = Build.MANUFACTURER;
        aVar.g = Build.DEVICE;
        aVar.h = cu.b(context);
        aVar.i = cu.d(context);
        aVar.j = String.valueOf(VERSION.SDK_INT);
        aVar.k = cx.r(context);
        aVar.l = cx.p(context);
        aVar.m = cx.m(context) + "";
        aVar.n = cx.l(context) + "";
        aVar.o = cx.s(context);
        aVar.p = cx.k(context);
        if (z) {
            aVar.q = "";
        } else {
            aVar.q = cx.h(context);
        }
        if (z) {
            aVar.r = "";
        } else {
            aVar.r = cx.g(context);
        }
        if (z) {
            aVar.s = "";
            aVar.t = "";
        } else {
            String[] j = cx.j(context);
            aVar.s = j[0];
            aVar.t = j[1];
        }
        return aVar;
    }

    public static String b(Context context) {
        StringBuilder stringBuilder = new StringBuilder();
        try {
            stringBuilder.append("\"key\":\"").append(cu.f(context)).append("\",\"platform\":\"android\",\"diu\":\"").append(cx.q(context)).append("\",\"pkg\":\"").append(cu.c(context)).append("\",\"model\":\"").append(Build.MODEL).append("\",\"appname\":\"").append(cu.b(context)).append("\",\"appversion\":\"").append(cu.d(context)).append("\",\"sysversion\":\"").append(VERSION.RELEASE).append("\",");
        } catch (Throwable th) {
            dg.a(th, "CInfo", "getPublicJSONInfo");
        }
        return stringBuilder.toString();
    }

    public static String b(Context context, byte[] bArr) {
        try {
            return d(context, bArr);
        } catch (Throwable th) {
            dg.a(th, "CInfo", "AESData");
            return "";
        }
    }

    private static byte[] b(Context context, a aVar) {
        Throwable th;
        Throwable th2;
        byte[] bArr = null;
        ByteArrayOutputStream byteArrayOutputStream;
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
                    dg.a(th3, "CInfo", "InitXInfo");
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

    public static byte[] c(Context context, byte[] bArr) throws CertificateException, InvalidKeySpecException, NoSuchAlgorithmException, NullPointerException, IOException, InvalidKeyException, NoSuchPaddingException, IllegalBlockSizeException, BadPaddingException {
        Key a = dd.a(context);
        if (bArr.length <= 117) {
            return cy.a(bArr, a);
        }
        byte[] bArr2 = new byte[117];
        System.arraycopy(bArr, 0, bArr2, 0, 117);
        Object a2 = cy.a(bArr2, a);
        Object obj = new byte[((bArr.length + 128) - 117)];
        System.arraycopy(a2, 0, obj, 0, 128);
        System.arraycopy(bArr, 117, obj, 128, bArr.length - 117);
        return obj;
    }

    static String d(Context context, byte[] bArr) throws InvalidKeyException, IOException, InvalidKeySpecException, NoSuchPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, BadPaddingException, CertificateException {
        byte[] b = dd.b(a(context, bArr));
        return b != null ? cy.a(b) : "";
    }

    public static String e(Context context, byte[] bArr) {
        try {
            return d(context, bArr);
        } catch (Throwable th) {
            th.printStackTrace();
            return "";
        }
    }
}
