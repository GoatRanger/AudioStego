package dji.pilot.usercenter.protocol;

import android.content.Context;
import com.dji.frame.c.c;
import dji.pilot.c.a;
import dji.pilot.usercenter.protocol.a.d;
import dji.pilot2.publics.b.a$a;
import dji.thirdparty.afinal.f.b;
import java.io.File;
import java.security.Key;
import java.util.Formatter;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class g implements d {
    private static final String O = "HmacSHA1";
    private static final String P = "UTF-8";
    private static final String Q = "712137bad345e2b4e143fcf83561c3c6bf8350b2c66b612263b27e22118e3be98ffc6ed5a366bdfc5743dfab783460d6868693e802afb1265ed43f74485ceba4";

    private static String a(String... strArr) {
        StringBuilder stringBuilder = new StringBuilder();
        if (a.C) {
            stringBuilder.append(e.aG);
        } else {
            stringBuilder.append("https://www.skypixel.com/api");
        }
        if (strArr != null && strArr.length > 0) {
            for (String append : strArr) {
                stringBuilder.append(d.t).append(append);
            }
        }
        return stringBuilder.toString();
    }

    public static void a(Context context, String str, String str2, String str3, final e$a dji_pilot_usercenter_protocol_e_a) {
        String str4 = a$a.b;
        b bVar = new b();
        bVar.a("email", str);
        bVar.a(d.N, str2);
        bVar.a(d.M, str3);
        c.b(context).b(str4, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object b = d.b(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.r, 0, 0, e$b.a(0, 0, null, b), b);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.r, i, 0, e$b.a(0, 0, null, null));
            }
        });
    }

    public static void a(Context context, String str, final e$a dji_pilot_usercenter_protocol_e_a) {
        String str2 = a$a.c;
        b bVar = new b();
        bVar.a("email", str);
        c.b(context).b(str2, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object b = d.b(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.s, 0, 0, e$b.a(0, 0, null, b), b);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.s, i, 0, e$b.a(0, 0, null, null));
            }
        });
    }

    public static void a(Context context, String str, String str2, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.J);
        b bVar = new b();
        bVar.a("email", str);
        bVar.a("password", str2);
        bVar.a(d.L, a(str, Q));
        c.b(context).b(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object b = d.b(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.l, 0, 0, e$b.a(0, 0, obj, b), b);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.l, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void a(Context context, String str, String str2, String str3, String str4, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.J);
        b bVar = new b();
        bVar.a("email", str);
        bVar.a("password", str2);
        bVar.a(d.N, str3);
        bVar.a(d.M, str4);
        c.b(context).b(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object b = d.b(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.l, 0, 0, e$b.a(0, 0, obj, b), b);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.l, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void a(Context context, String str, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.w);
        b bVar = new b();
        bVar.a("token", str);
        c.b(context).a(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object a = d.a(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.n, 0, 0, e$b.a(0, 0, obj, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.n, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void b(Context context, String str, String str2, String str3, String str4, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.I);
        b bVar = new b();
        bVar.a("email", str);
        bVar.a("password", str2);
        bVar.a(d.N, str3);
        bVar.a(d.M, str4);
        c.b(context).b(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object a = d.a(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.m, 0, 0, e$b.a(0, 0, obj, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.m, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void b(Context context, String str, String str2, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.I);
        b bVar = new b();
        bVar.a("email", str);
        bVar.a("password", str2);
        bVar.a(d.L, a(str, Q));
        c.b(context).b(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object a = d.a(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.m, 0, 0, e$b.a(0, 0, obj, a), a);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.m, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void b(Context context, String str, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.x, d.y);
        b bVar = new b();
        bVar.a("token", str);
        c.b(context).a(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object d = d.d(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.o, 0, 0, e$b.a(0, 0, obj, d), d);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.o, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void a(Context context, String str, HashMap<String, String> hashMap, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.x, d.y);
        b bVar = new b();
        bVar.a("token", str);
        for (Entry entry : hashMap.entrySet()) {
            bVar.a("account[" + ((String) entry.getKey()) + d.H, (String) entry.getValue());
        }
        c.b(context).c(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object d = d.d(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.p, 0, 0, e$b.a(0, 0, obj, d), d);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.p, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static void c(Context context, String str, String str2, final Object obj, final e$a dji_pilot_usercenter_protocol_e_a) {
        String a = a(d.x, d.y, "avatar");
        b bVar = new b();
        bVar.a("token", str);
        try {
            bVar.a("avatar", new File(str2));
        } catch (Exception e) {
        }
        c.b(context).b(a, bVar, new dji.thirdparty.afinal.f.a<String>() {
            public void a(boolean z) {
            }

            public void a(long j, long j2) {
            }

            public void a(String str) {
                Object c = d.c(str);
                dji_pilot_usercenter_protocol_e_a.a((int) d.q, 0, 0, e$b.a(0, 0, obj, c), c);
            }

            public void a(Throwable th, int i, String str) {
                dji_pilot_usercenter_protocol_e_a.a((int) d.q, i, 0, e$b.a(0, 0, obj, null));
            }
        });
    }

    public static String a(String str, String str2) {
        try {
            Key secretKeySpec = new SecretKeySpec(str2.getBytes("UTF-8"), "HmacSHA1");
            Mac instance = Mac.getInstance("HmacSHA1");
            instance.init(secretKeySpec);
            return a(instance.doFinal(str.getBytes("UTF-8")));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    private static String a(byte[] bArr) {
        Formatter formatter = new Formatter();
        int length = bArr.length;
        for (int i = 0; i < length; i++) {
            formatter.format("%02x", new Object[]{Byte.valueOf(bArr[i])});
        }
        return formatter.toString();
    }
}
