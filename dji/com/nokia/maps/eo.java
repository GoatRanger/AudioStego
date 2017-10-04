package com.nokia.maps;

import com.here.android.mpa.guidance.TrafficUpdater.Error;
import com.here.android.mpa.guidance.TrafficUpdater.RequestInfo;

public class eo {
    private static a a;

    public interface a {
        RequestInfo a(Error error, long j);
    }

    public static void a(a aVar) {
        a = aVar;
    }

    public static RequestInfo a(Error error, long j) {
        return a.a(error, j);
    }

    static {
        ce.a(RequestInfo.class);
    }
}
