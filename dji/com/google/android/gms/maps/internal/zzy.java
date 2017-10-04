package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.internal.zzc.zza;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzy {
    private static Context zzaJF;
    private static zzc zzaJG;

    private static Context getRemoteContext(Context context) {
        if (zzaJF == null) {
            if (zzxV()) {
                zzaJF = context.getApplicationContext();
            } else {
                zzaJF = GooglePlayServicesUtil.getRemoteContext(context);
            }
        }
        return zzaJF;
    }

    private static <T> T zza(ClassLoader classLoader, String str) {
        try {
            return zzc(((ClassLoader) zzx.zzw(classLoader)).loadClass(str));
        } catch (ClassNotFoundException e) {
            throw new IllegalStateException("Unable to find dynamic class " + str);
        }
    }

    public static zzc zzaG(Context context) throws GooglePlayServicesNotAvailableException {
        zzx.zzw(context);
        if (zzaJG != null) {
            return zzaJG;
        }
        zzaH(context);
        zzaJG = zzaI(context);
        try {
            zzaJG.zzd(zze.zzy(getRemoteContext(context).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
            return zzaJG;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    private static void zzaH(Context context) throws GooglePlayServicesNotAvailableException {
        int isGooglePlayServicesAvailable = GooglePlayServicesUtil.isGooglePlayServicesAvailable(context);
        switch (isGooglePlayServicesAvailable) {
            case 0:
                return;
            default:
                throw new GooglePlayServicesNotAvailableException(isGooglePlayServicesAvailable);
        }
    }

    private static zzc zzaI(Context context) {
        if (zzxV()) {
            Log.i(zzy.class.getSimpleName(), "Making Creator statically");
            return (zzc) zzc(zzxW());
        }
        Log.i(zzy.class.getSimpleName(), "Making Creator dynamically");
        return zza.zzcl((IBinder) zza(getRemoteContext(context).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
    }

    private static <T> T zzc(Class<?> cls) {
        try {
            return cls.newInstance();
        } catch (InstantiationException e) {
            throw new IllegalStateException("Unable to instantiate the dynamic class " + cls.getName());
        } catch (IllegalAccessException e2) {
            throw new IllegalStateException("Unable to call the default constructor of " + cls.getName());
        }
    }

    public static boolean zzxV() {
        return false;
    }

    private static Class<?> zzxW() {
        try {
            return Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
        } catch (Throwable e) {
            throw new RuntimeException(e);
        }
    }
}
