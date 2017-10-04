package com.flurry.android;

import android.content.Context;
import android.location.Criteria;
import android.location.Location;
import android.os.Build.VERSION;
import com.flurry.sdk.gg;
import com.flurry.sdk.hm;
import com.flurry.sdk.hz;
import com.flurry.sdk.ia;
import com.flurry.sdk.ib;
import com.flurry.sdk.ih;
import com.flurry.sdk.ii;
import com.flurry.sdk.ij;
import com.flurry.sdk.in;
import com.flurry.sdk.jm;
import com.flurry.sdk.jn;
import com.flurry.sdk.jq;
import com.flurry.sdk.jz;
import com.flurry.sdk.kg;
import com.here.odnp.config.OdnpConfigStatic;
import it.sauronsoftware.ftp4j.FTPCodes;
import java.util.Date;
import java.util.Map;

public final class FlurryAgent {
    private static final String a = FlurryAgent.class.getSimpleName();
    private static FlurryAgentListener b;
    private static final ii<jm> c = new ii<jm>() {
        public /* synthetic */ void a(ih ihVar) {
            a((jm) ihVar);
        }

        public void a(final jm jmVar) {
            hz.a().a(new Runnable(this) {
                final /* synthetic */ AnonymousClass1 b;

                public void run() {
                    switch (jmVar.c) {
                        case SESSION_ID_CREATED:
                            if (FlurryAgent.b != null) {
                                FlurryAgent.b.onSessionStarted();
                                return;
                            }
                            return;
                        default:
                            return;
                    }
                }
            });
        }
    };

    private FlurryAgent() {
    }

    public static int getAgentVersion() {
        return ia.a();
    }

    public static String getReleaseVersion() {
        return ia.f();
    }

    public static void setFlurryAgentListener(FlurryAgentListener flurryAgentListener) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (flurryAgentListener == null) {
            in.b(a, "Listener cannot be null");
            ij.a().b("com.flurry.android.sdk.FlurrySessionEvent", c);
        } else {
            b = flurryAgentListener;
            ij.a().a("com.flurry.android.sdk.FlurrySessionEvent", c);
        }
    }

    public static void setLogEnabled(boolean z) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (z) {
            in.b();
        } else {
            in.a();
        }
    }

    public static void setLogLevel(int i) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            in.a(i);
        }
    }

    public static void setVersionName(String str) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String versionName passed to setVersionName was null.");
        } else {
            jq.a().a("VersionName", (Object) str);
        }
    }

    public static void setReportLocation(boolean z) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            jq.a().a("ReportLocation", (Object) Boolean.valueOf(z));
        }
    }

    public static void setLocation(float f, float f2) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
            return;
        }
        Location location = new Location("Explicit");
        location.setLatitude((double) f);
        location.setLongitude((double) f2);
        jq.a().a("ExplicitLocation", (Object) location);
    }

    public static void clearLocation() {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            jq.a().a("ExplicitLocation", null);
        }
    }

    public static void setContinueSessionMillis(long j) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (j < OdnpConfigStatic.MIN_ALARM_TIMER_INTERVAL) {
            in.b(a, "Invalid time set for session resumption: " + j);
        } else {
            jq.a().a("ContinueSessionMillis", (Object) Long.valueOf(j));
        }
    }

    public static void setLogEvents(boolean z) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            jq.a().a("LogEvents", (Object) Boolean.valueOf(z));
        }
    }

    public static void setCaptureUncaughtExceptions(boolean z) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            jq.a().a("CaptureUncaughtExceptions", (Object) Boolean.valueOf(z));
        }
    }

    public static void addOrigin(String str, String str2) {
        addOrigin(str, str2, null);
    }

    public static void addOrigin(String str, String str2, Map<String, String> map) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("originName not specified");
        } else if (str2 == null || str2.length() == 0) {
            throw new IllegalArgumentException("originVersion not specified");
        } else {
            try {
                ib.a().a(str, str2, map);
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
    }

    public static void setPulseEnabled(boolean z) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            jq.a().a("ProtonEnabled", (Object) Boolean.valueOf(z));
        }
    }

    public static synchronized void init(Context context, String str) {
        synchronized (FlurryAgent.class) {
            if (VERSION.SDK_INT < 10) {
                in.b(a, "Device SDK Version older than 10");
            } else if (context == null) {
                throw new NullPointerException("Null context");
            } else {
                if (str != null) {
                    if (str.length() != 0) {
                        try {
                            kg.a();
                            hz.a(context, str);
                        } catch (Throwable th) {
                            in.a(a, "", th);
                        }
                    }
                }
                throw new IllegalArgumentException("apiKey not specified");
            }
        }
    }

    @Deprecated
    public static void onStartSession(Context context, String str) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (context == null) {
            throw new NullPointerException("Null context");
        } else if (str == null || str.length() == 0) {
            throw new IllegalArgumentException("Api key not specified");
        } else if (hz.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        } else {
            try {
                jn.a().b(context);
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
    }

    public static void onStartSession(Context context) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (context == null) {
            throw new NullPointerException("Null context");
        } else if (hz.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before starting a session");
        } else {
            try {
                jn.a().b(context);
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
    }

    public static void onEndSession(Context context) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (context == null) {
            throw new NullPointerException("Null context");
        } else if (hz.a() == null) {
            throw new IllegalStateException("Flurry SDK must be initialized before ending a session");
        } else {
            try {
                jn.a().c(context);
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
    }

    public static boolean isSessionActive() {
        boolean z = false;
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            try {
                z = jn.a().f();
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
        return z;
    }

    public static String getSessionId() {
        String str = null;
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else {
            try {
                str = hm.a().c();
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
        return str;
    }

    public static FlurryEventRecordStatus logEvent(String str) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to logEvent was null.");
        } else {
            try {
                flurryEventRecordStatus = gg.a().a(str);
            } catch (Throwable th) {
                in.a(a, "Failed to log event: " + str, th);
            }
        }
        return flurryEventRecordStatus;
    }

    public static FlurryEventRecordStatus logEvent(String str, Map<String, String> map) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to logEvent was null.");
        } else if (map == null) {
            in.b(a, "String parameters passed to logEvent was null.");
        } else {
            try {
                flurryEventRecordStatus = gg.a().a(str, (Map) map);
            } catch (Throwable th) {
                in.a(a, "Failed to log event: " + str, th);
            }
        }
        return flurryEventRecordStatus;
    }

    public static FlurryEventRecordStatus logEvent(String str, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to logEvent was null.");
        } else {
            try {
                flurryEventRecordStatus = gg.a().a(str, z);
            } catch (Throwable th) {
                in.a(a, "Failed to log event: " + str, th);
            }
        }
        return flurryEventRecordStatus;
    }

    public static FlurryEventRecordStatus logEvent(String str, Map<String, String> map, boolean z) {
        FlurryEventRecordStatus flurryEventRecordStatus = FlurryEventRecordStatus.kFlurryEventFailed;
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to logEvent was null.");
        } else if (map == null) {
            in.b(a, "String parameters passed to logEvent was null.");
        } else {
            try {
                flurryEventRecordStatus = gg.a().a(str, (Map) map, z);
            } catch (Throwable th) {
                in.a(a, "Failed to log event: " + str, th);
            }
        }
        return flurryEventRecordStatus;
    }

    public static void endTimedEvent(String str) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to endTimedEvent was null.");
        } else {
            try {
                gg.a().b(str);
            } catch (Throwable th) {
                in.a(a, "Failed to signify the end of event: " + str, th);
            }
        }
    }

    public static void endTimedEvent(String str, Map<String, String> map) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to endTimedEvent was null.");
        } else if (map == null) {
            in.b(a, "String eventId passed to endTimedEvent was null.");
        } else {
            try {
                gg.a().b(str, map);
            } catch (Throwable th) {
                in.a(a, "Failed to signify the end of event: " + str, th);
            }
        }
    }

    @Deprecated
    public static void onError(String str, String str2, String str3) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String errorId passed to onError was null.");
        } else if (str2 == null) {
            in.b(a, "String message passed to onError was null.");
        } else if (str3 == null) {
            in.b(a, "String errorClass passed to onError was null.");
        } else {
            try {
                gg.a().a(str, str2, str3);
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
    }

    public static void onError(String str, String str2, Throwable th) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String errorId passed to onError was null.");
        } else if (str2 == null) {
            in.b(a, "String message passed to onError was null.");
        } else if (th == null) {
            in.b(a, "Throwable passed to onError was null.");
        } else {
            try {
                gg.a().a(str, str2, th);
            } catch (Throwable th2) {
                in.a(a, "", th2);
            }
        }
    }

    @Deprecated
    public static void onEvent(String str) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to onEvent was null.");
        } else {
            try {
                gg.a().c(str);
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
    }

    @Deprecated
    public static void onEvent(String str, Map<String, String> map) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String eventId passed to onEvent was null.");
        } else if (map == null) {
            in.b(a, "Parameters Map passed to onEvent was null.");
        } else {
            try {
                gg.a().c(str, map);
            } catch (Throwable th) {
                in.a(a, "", th);
            }
        }
    }

    public static void onPageView() {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
            return;
        }
        try {
            gg.a().g();
        } catch (Throwable th) {
            in.a(a, "", th);
        }
    }

    @Deprecated
    public static void setLocationCriteria(Criteria criteria) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        }
    }

    public static void setAge(int i) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (i > 0 && i < FTPCodes.RESTART_MARKER) {
            jq.a().a("Age", (Object) Long.valueOf(new Date(new Date(System.currentTimeMillis() - (((long) i) * 31449600000L)).getYear(), 1, 1).getTime()));
        }
    }

    public static void setGender(byte b) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
            return;
        }
        switch (b) {
            case (byte) 0:
            case (byte) 1:
                jq.a().a("Gender", (Object) Byte.valueOf(b));
                return;
            default:
                jq.a().a("Gender", (Object) Byte.valueOf((byte) -1));
                return;
        }
    }

    public static void setUserId(String str) {
        if (VERSION.SDK_INT < 10) {
            in.b(a, "Device SDK Version older than 10");
        } else if (str == null) {
            in.b(a, "String userId passed to setUserId was null.");
        } else {
            jq.a().a("UserId", (Object) jz.b(str));
        }
    }
}
