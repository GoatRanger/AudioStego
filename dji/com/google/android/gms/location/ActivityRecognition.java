package com.google.android.gms.location;

import android.content.Context;
import android.os.Looper;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.location.internal.zzl;

public class ActivityRecognition {
    public static final Api<NoOptions> API = new Api("ActivityRecognition.API", zzRl, zzRk);
    public static final ActivityRecognitionApi ActivityRecognitionApi = new com.google.android.gms.location.internal.zza();
    public static final String CLIENT_NAME = "activity_recognition";
    private static final zzc<zzl> zzRk = new zzc();
    private static final com.google.android.gms.common.api.Api.zza<zzl, NoOptions> zzRl = new com.google.android.gms.common.api.Api.zza<zzl, NoOptions>() {
        public /* synthetic */ zzb zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzm(context, looper, com_google_android_gms_common_internal_zzf, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzl zzm(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzl(context, looper, connectionCallbacks, onConnectionFailedListener, ActivityRecognition.CLIENT_NAME);
        }
    };

    public static abstract class zza<R extends Result> extends com.google.android.gms.internal.zzlb.zza<R, zzl> {
        public zza(GoogleApiClient googleApiClient) {
            super(ActivityRecognition.zzRk, googleApiClient);
        }
    }

    private ActivityRecognition() {
    }
}
