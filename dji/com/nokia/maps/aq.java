package com.nokia.maps;

import com.here.android.mpa.common.OnEngineInitListener.Error;

public final class aq {
    private static a a = null;

    public interface a {
        Error a(Error error, String str, Throwable th);
    }

    public static void a(a aVar) {
        a = aVar;
    }

    public static Error a(Error error, String str) {
        return a(error, str, new Throwable());
    }

    public static Error a(Error error, String str, Throwable th) {
        return a.a(error, str, th);
    }
}
