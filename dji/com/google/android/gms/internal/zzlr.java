package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzlr<T> {
    private static zza zzadc = null;
    private static int zzadd = 0;
    private static String zzade = "com.google.android.providers.gsf.permission.READ_GSERVICES";
    private static final Object zzpy = new Object();
    private T zzOX = null;
    protected final String zzue;
    protected final T zzuf;

    private interface zza {
        Long getLong(String str, Long l);

        String getString(String str, String str2);

        Boolean zzb(String str, Boolean bool);

        Float zzb(String str, Float f);

        Integer zzb(String str, Integer num);
    }

    protected zzlr(String str, T t) {
        this.zzue = str;
        this.zzuf = t;
    }

    public static boolean isInitialized() {
        return zzadc != null;
    }

    public static zzlr<Float> zza(String str, Float f) {
        return new zzlr<Float>(str, f) {
            protected /* synthetic */ Object zzbY(String str) {
                return zzcc(str);
            }

            protected Float zzcc(String str) {
                return zzlr.zzadc.zzb(this.zzue, (Float) this.zzuf);
            }
        };
    }

    public static zzlr<Integer> zza(String str, Integer num) {
        return new zzlr<Integer>(str, num) {
            protected /* synthetic */ Object zzbY(String str) {
                return zzcb(str);
            }

            protected Integer zzcb(String str) {
                return zzlr.zzadc.zzb(this.zzue, (Integer) this.zzuf);
            }
        };
    }

    public static zzlr<Long> zza(String str, Long l) {
        return new zzlr<Long>(str, l) {
            protected /* synthetic */ Object zzbY(String str) {
                return zzca(str);
            }

            protected Long zzca(String str) {
                return zzlr.zzadc.getLong(this.zzue, (Long) this.zzuf);
            }
        };
    }

    public static zzlr<Boolean> zzg(String str, boolean z) {
        return new zzlr<Boolean>(str, Boolean.valueOf(z)) {
            protected /* synthetic */ Object zzbY(String str) {
                return zzbZ(str);
            }

            protected Boolean zzbZ(String str) {
                return zzlr.zzadc.zzb(this.zzue, (Boolean) this.zzuf);
            }
        };
    }

    public static int zzoo() {
        return zzadd;
    }

    public static zzlr<String> zzu(String str, String str2) {
        return new zzlr<String>(str, str2) {
            protected /* synthetic */ Object zzbY(String str) {
                return zzcd(str);
            }

            protected String zzcd(String str) {
                return zzlr.zzadc.getString(this.zzue, (String) this.zzuf);
            }
        };
    }

    public final T get() {
        return this.zzOX != null ? this.zzOX : zzbY(this.zzue);
    }

    protected abstract T zzbY(String str);

    public final T zzop() {
        long clearCallingIdentity = Binder.clearCallingIdentity();
        try {
            T t = get();
            return t;
        } finally {
            Binder.restoreCallingIdentity(clearCallingIdentity);
        }
    }
}
