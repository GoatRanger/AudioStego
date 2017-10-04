package com.alipay.sdk.app.a;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.os.Build.VERSION;
import android.text.TextUtils;
import com.alipay.sdk.b.a;
import com.alipay.sdk.i.b;
import dji.pilot.usercenter.protocol.d;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class c {
    public static final String a = "net";
    public static final String b = "biz";
    public static final String c = "cp";
    public static final String d = "H5PayNetworkError";
    public static final String e = "H5AuthNetworkError";
    public static final String f = "SSLError";
    public static final String g = "H5PayDataAnalysisError";
    public static final String h = "H5AuthDataAnalysisError";
    public static final String i = "ClientSignError";
    public static final String j = "ClientBindFailed";
    public static final String k = "TriDesEncryptError";
    public static final String l = "TriDesDecryptError";
    public static final String m = "ClientBindException";
    public static final String n = "SaveTradeTokenError";
    public static final String o = "ClientBindServiceFailed";
    public static final String p = "partner";
    public static final String q = "out_trade_no";
    public static final String r = "trade_no";
    String A = "";
    String B;
    String s;
    String t;
    String u;
    String v;
    String w;
    String x;
    String y;
    String z;

    public c(Context context) {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        this.s = String.format("123456789,%s", new Object[]{format});
        this.u = a(context);
        format = a(a.e);
        String a = a(a.f);
        this.v = String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{format, a});
        format = a(b.a().a);
        a = a(com.alipay.sdk.h.b.a().c());
        this.w = String.format("%s,%s,-,-,-", new Object[]{format, a});
        format = a(com.alipay.sdk.j.b.d(context));
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String a4 = a(com.alipay.sdk.j.b.a(context).a());
        String a5 = a(com.alipay.sdk.j.b.b(context).p);
        String a6 = a(com.alipay.sdk.j.b.a(context).b());
        this.x = String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{format, "android", a2, a3, "-", a4, a5, "gw", a6});
        this.y = "-";
        this.z = "-";
        this.B = "-";
    }

    private boolean a() {
        return TextUtils.isEmpty(this.A);
    }

    public final void a(String str, String str2, Throwable th) {
        a(str, str2, a(th));
    }

    private void a(String str, String str2, Throwable th, String str3) {
        a(str, str2, a(th), str3);
    }

    public final void a(String str, String str2, String str3, String str4) {
        String str5 = "";
        if (!TextUtils.isEmpty(this.A)) {
            str5 = str5 + "^";
        }
        this.A += (str5 + String.format("%s,%s,%s,%s", new Object[]{str, str2, a(str3), str4}));
    }

    public final void a(String str, String str2, String str3) {
        a(str, str2, str3, "-");
    }

    static String a(String str) {
        if (TextUtils.isEmpty(str)) {
            return "";
        }
        return str.replace(d.G, "【").replace(d.H, "】").replace("(", "（").replace(")", "）").replace(",", "，").replace("-", "=").replace("^", "~");
    }

    static String a(Throwable th) {
        if (th == null) {
            return "";
        }
        StringBuffer stringBuffer = new StringBuffer();
        try {
            stringBuffer.append(th.getClass().getName()).append(":");
            stringBuffer.append(th.getMessage());
            stringBuffer.append(" 》 ");
            StackTraceElement[] stackTrace = th.getStackTrace();
            if (stackTrace != null) {
                for (StackTraceElement stackTraceElement : stackTrace) {
                    stringBuffer.append(stackTraceElement.toString() + " 》 ");
                }
            }
        } catch (Throwable th2) {
        }
        return stringBuffer.toString();
    }

    private String b(String str) {
        String str2 = null;
        if (TextUtils.isEmpty(this.A)) {
            return "";
        }
        String str3;
        String[] split = str.split(com.alipay.sdk.h.a.b);
        if (split != null) {
            str3 = null;
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(p)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(q)) {
                        str3 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(r)) {
                        str2 = split3[1].replace("\"", "");
                    }
                }
            }
        } else {
            str3 = null;
        }
        str2 = a(str2);
        String a = a(a(str3));
        this.t = String.format("%s,%s,-,%s,-,-,-", new Object[]{str2, str3, a});
        return String.format("[(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s),(%s)]", new Object[]{this.s, this.t, this.u, this.v, this.w, this.x, this.y, this.z, this.A, this.B});
    }

    @SuppressLint({"SimpleDateFormat"})
    private static String b() {
        String format = new SimpleDateFormat("yyyy-MM-dd-HH:mm:ss").format(new Date());
        return String.format("123456789,%s", new Object[]{format});
    }

    private static String c(String str) {
        String str2;
        String str3 = null;
        String[] split = str.split(com.alipay.sdk.h.a.b);
        if (split != null) {
            str2 = null;
            for (String split2 : split) {
                String[] split3 = split2.split("=");
                if (split3 != null && split3.length == 2) {
                    if (split3[0].equalsIgnoreCase(p)) {
                        split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(q)) {
                        str2 = split3[1].replace("\"", "");
                    } else if (split3[0].equalsIgnoreCase(r)) {
                        str3 = split3[1].replace("\"", "");
                    }
                }
            }
        } else {
            str2 = null;
        }
        str3 = a(str3);
        String a = a(a(str2));
        return String.format("%s,%s,-,%s,-,-,-", new Object[]{str3, a(str2), a});
    }

    private static String a(Context context) {
        String str = "-";
        String str2 = "-";
        if (context != null) {
            try {
                Context applicationContext = context.getApplicationContext();
                str = applicationContext.getPackageName();
                str2 = applicationContext.getPackageManager().getPackageInfo(str, 0).versionName;
            } catch (Throwable th) {
            }
        }
        return String.format("%s,%s,-,-,-", new Object[]{str, str2});
    }

    private static String c() {
        String a = a(a.e);
        String a2 = a(a.f);
        return String.format("android,3,%s,%s,com.alipay.mcpay,5.0,-,-,-", new Object[]{a, a2});
    }

    private static String d() {
        String a = a(b.a().a);
        String a2 = a(com.alipay.sdk.h.b.a().c());
        return String.format("%s,%s,-,-,-", new Object[]{a, a2});
    }

    private static String b(Context context) {
        String a = a(com.alipay.sdk.j.b.d(context));
        String a2 = a(VERSION.RELEASE);
        String a3 = a(Build.MODEL);
        String a4 = a(com.alipay.sdk.j.b.a(context).a());
        String a5 = a(com.alipay.sdk.j.b.b(context).p);
        String a6 = a(com.alipay.sdk.j.b.a(context).b());
        return String.format("%s,%s,%s,%s,%s,%s,%s,%s,%s,-", new Object[]{a, "android", a2, a3, "-", a4, a5, "gw", a6});
    }
}
