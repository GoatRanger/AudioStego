package dji.thirdparty.b;

import dji.thirdparty.c.f;
import java.io.UnsupportedEncodingException;

public final class o {
    private o() {
    }

    public static String a(String str, String str2) {
        try {
            return "Basic " + f.a((str + ":" + str2).getBytes("ISO-8859-1")).b();
        } catch (UnsupportedEncodingException e) {
            throw new AssertionError();
        }
    }
}
