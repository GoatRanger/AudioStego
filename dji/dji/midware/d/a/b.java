package dji.midware.d.a;

import android.util.Base64;

public class b {
    private static b a;

    public static synchronized b getInstance() {
        b bVar;
        synchronized (b.class) {
            if (a == null) {
                a = new b();
            }
            bVar = a;
        }
        return bVar;
    }

    public String a(String str, String str2) {
        String str3 = "";
        try {
            return new String(a.b(Base64.decode(str2, 2), "Android@" + str));
        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
    }

    public String b(String str, String str2) {
        try {
            str.getBytes();
            return new String(Base64.encode(a.a(str.getBytes(), str2), 2));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
