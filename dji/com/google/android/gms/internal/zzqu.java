package com.google.android.gms.internal;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.Scopes;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.signin.internal.zzh;
import com.google.android.gms.signin.internal.zzi;
import java.util.concurrent.Executors;

public final class zzqu {
    public static final Api<zzqx> API = new Api("SignIn.API", zzRl, zzRk);
    public static final zzc<zzi> zzRk = new zzc();
    public static final zza<zzi, zzqx> zzRl = new zza<zzi, zzqx>() {
        public zzi zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, zzqx com_google_android_gms_internal_zzqx, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzi(context, looper, true, com_google_android_gms_common_internal_zzf, com_google_android_gms_internal_zzqx == null ? zzqx.zzaUZ : com_google_android_gms_internal_zzqx, connectionCallbacks, onConnectionFailedListener, Executors.newSingleThreadExecutor());
        }
    };
    public static final Scope zzTe = new Scope(Scopes.PROFILE);
    public static final Scope zzTf = new Scope("email");
    static final zza<zzi, NoOptions> zzaUX = new zza<zzi, NoOptions>() {
        public /* synthetic */ zzb zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzt(context, looper, com_google_android_gms_common_internal_zzf, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzi zzt(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzi(context, looper, false, com_google_android_gms_common_internal_zzf, zzqx.zzaUZ, connectionCallbacks, onConnectionFailedListener, Executors.newSingleThreadExecutor());
        }
    };
    public static final zzqv zzaUY = new zzh();
    public static final Api<NoOptions> zzaiH = new Api("SignIn.INTERNAL_API", zzaUX, zzapF);
    public static final zzc<zzi> zzapF = new zzc();
}
