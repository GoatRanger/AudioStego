package dji.thirdparty.b.a.b;

import com.google.api.client.http.HttpMethods;

public final class h {
    public static boolean a(String str) {
        return str.equals(HttpMethods.POST) || str.equals("PATCH") || str.equals(HttpMethods.PUT) || str.equals(HttpMethods.DELETE) || str.equals("MOVE");
    }

    public static boolean b(String str) {
        return str.equals(HttpMethods.POST) || str.equals(HttpMethods.PUT) || str.equals("PATCH") || str.equals("PROPPATCH") || str.equals("REPORT");
    }

    public static boolean c(String str) {
        return b(str) || str.equals(HttpMethods.OPTIONS) || str.equals(HttpMethods.DELETE) || str.equals("PROPFIND") || str.equals("MKCOL") || str.equals("LOCK");
    }

    public static boolean d(String str) {
        return !str.equals("PROPFIND");
    }

    private h() {
    }
}
