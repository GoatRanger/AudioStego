package com.flurry.sdk;

public class gx extends is {
    private static final String a = gx.class.getSimpleName();

    public String a(String str) {
        String b = b(str);
        while (b != null) {
            str = b(str, b);
            b = b(str);
        }
        return str;
    }

    private String b(String str, String str2) {
        String valueOf;
        if (a("timestamp_epoch_millis", str2)) {
            valueOf = String.valueOf(System.currentTimeMillis());
            in.a(3, a, "Replacing param timestamp_epoch_millis with: " + valueOf);
            return str.replace(str2, jz.c(valueOf));
        } else if (a("session_duration_millis", str2)) {
            valueOf = Long.toString(hm.a().f());
            in.a(3, a, "Replacing param session_duration_millis with: " + valueOf);
            return str.replace(str2, jz.c(valueOf));
        } else if (a("fg_timespent_millis", str2)) {
            valueOf = Long.toString(hm.a().f());
            in.a(3, a, "Replacing param fg_timespent_millis with: " + valueOf);
            return str.replace(str2, jz.c(valueOf));
        } else {
            in.a(3, a, "Unknown param: " + str2);
            return str.replace(str2, "");
        }
    }
}
