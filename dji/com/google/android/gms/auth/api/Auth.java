package com.google.android.gms.auth.api;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import com.google.android.gms.auth.api.credentials.CredentialsApi;
import com.google.android.gms.auth.api.credentials.PasswordSpecification;
import com.google.android.gms.auth.api.credentials.internal.zzc;
import com.google.android.gms.auth.api.credentials.internal.zze;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.signin.GoogleSignInConfig;
import com.google.android.gms.auth.api.signin.internal.zzb;
import com.google.android.gms.auth.api.signin.internal.zzg;
import com.google.android.gms.auth.api.signin.zzd;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.internal.zzjz;
import com.google.android.gms.internal.zzka;
import com.google.android.gms.internal.zzkb;
import com.google.android.gms.internal.zzke;
import com.google.android.gms.internal.zzkf;
import com.google.android.gms.internal.zzki;
import com.google.android.gms.internal.zzkm;

public final class Auth {
    public static final Api<AuthCredentialsOptions> CREDENTIALS_API = new Api("Auth.CREDENTIALS_API", zzRL, zzRF);
    public static final CredentialsApi CredentialsApi = new zzc();
    public static final Api<zza> PROXY_API = new Api("Auth.PROXY_API", zzRK, zzRE);
    public static final ProxyApi ProxyApi = new zzkm();
    public static final Api.zzc<zzki> zzRE = new Api.zzc();
    public static final Api.zzc<zze> zzRF = new Api.zzc();
    public static final Api.zzc<zzkb> zzRG = new Api.zzc();
    public static final Api.zzc<zzg> zzRH = new Api.zzc();
    public static final Api.zzc<zzb> zzRI = new Api.zzc();
    public static final Api.zzc<zzkf> zzRJ = new Api.zzc();
    private static final com.google.android.gms.common.api.Api.zza<zzki, zza> zzRK = new com.google.android.gms.common.api.Api.zza<zzki, zza>() {
        public zzki zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, zza com_google_android_gms_auth_api_Auth_zza, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzki(context, looper, com_google_android_gms_common_internal_zzf, com_google_android_gms_auth_api_Auth_zza, connectionCallbacks, onConnectionFailedListener);
        }
    };
    private static final com.google.android.gms.common.api.Api.zza<zze, AuthCredentialsOptions> zzRL = new com.google.android.gms.common.api.Api.zza<zze, AuthCredentialsOptions>() {
        public zze zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, AuthCredentialsOptions authCredentialsOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zze(context, looper, com_google_android_gms_common_internal_zzf, authCredentialsOptions, connectionCallbacks, onConnectionFailedListener);
        }
    };
    private static final com.google.android.gms.common.api.Api.zza<zzkb, NoOptions> zzRM = new com.google.android.gms.common.api.Api.zza<zzkb, NoOptions>() {
        public /* synthetic */ Api.zzb zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzc(context, looper, com_google_android_gms_common_internal_zzf, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzkb zzc(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzkb(context, looper, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        }
    };
    private static final com.google.android.gms.common.api.Api.zza<zzkf, NoOptions> zzRN = new com.google.android.gms.common.api.Api.zza<zzkf, NoOptions>() {
        public /* synthetic */ Api.zzb zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, Object obj, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return zzd(context, looper, com_google_android_gms_common_internal_zzf, (NoOptions) obj, connectionCallbacks, onConnectionFailedListener);
        }

        public zzkf zzd(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, NoOptions noOptions, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzkf(context, looper, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        }
    };
    private static final com.google.android.gms.common.api.Api.zza<zzg, com.google.android.gms.auth.api.signin.zzg> zzRO = new com.google.android.gms.common.api.Api.zza<zzg, com.google.android.gms.auth.api.signin.zzg>() {
        public zzg zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, com.google.android.gms.auth.api.signin.zzg com_google_android_gms_auth_api_signin_zzg, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzg(context, looper, com_google_android_gms_common_internal_zzf, com_google_android_gms_auth_api_signin_zzg, connectionCallbacks, onConnectionFailedListener);
        }
    };
    private static final com.google.android.gms.common.api.Api.zza<zzb, GoogleSignInConfig> zzRP = new com.google.android.gms.common.api.Api.zza<zzb, GoogleSignInConfig>() {
        public zzb zza(Context context, Looper looper, zzf com_google_android_gms_common_internal_zzf, GoogleSignInConfig googleSignInConfig, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener) {
            return new zzb(context, looper, com_google_android_gms_common_internal_zzf, googleSignInConfig, connectionCallbacks, onConnectionFailedListener);
        }
    };
    public static final Api<com.google.android.gms.auth.api.signin.zzg> zzRQ = new Api("Auth.SIGN_IN_API", zzRO, zzRH);
    public static final Api<GoogleSignInConfig> zzRR = new Api("Auth.GOOGLE_SIGN_IN_API", zzRP, zzRI);
    public static final Api<NoOptions> zzRS = new Api("Auth.ACCOUNT_STATUS_API", zzRM, zzRG);
    public static final Api<NoOptions> zzRT = new Api("Auth.CONSENT_API", zzRN, zzRJ);
    public static final zzjz zzRU = new zzka();
    public static final com.google.android.gms.auth.api.signin.zzf zzRV = new com.google.android.gms.auth.api.signin.internal.zzf();
    public static final zzd zzRW = new com.google.android.gms.auth.api.signin.internal.zza();
    public static final com.google.android.gms.auth.api.consent.zza zzRX = new zzke();

    public static final class AuthCredentialsOptions implements Optional {
        private final String zzRY;
        private final PasswordSpecification zzRZ;

        public static class Builder {
            private PasswordSpecification zzRZ = PasswordSpecification.zzSt;
        }

        public Bundle zzly() {
            Bundle bundle = new Bundle();
            bundle.putString("consumer_package", this.zzRY);
            bundle.putParcelable("password_specification", this.zzRZ);
            return bundle;
        }
    }

    public static final class zza implements Optional {
        private final Bundle zzSa;

        public Bundle zzlE() {
            return new Bundle(this.zzSa);
        }
    }

    private Auth() {
    }
}
