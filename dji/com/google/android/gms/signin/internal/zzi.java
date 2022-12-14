package com.google.android.gms.signin.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.ServerAuthCodeCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.ServerAuthCodeCallbacks.CheckResult;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.AuthAccountRequest;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.ResolveAccountRequest;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzt;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzqw;
import com.google.android.gms.internal.zzqx;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;

public class zzi extends zzj<zzf> implements zzqw {
    private final boolean zzaVl;
    private final ExecutorService zzaVm;
    private final zzqx zzaaT;
    private final zzf zzabI;
    private Integer zzafj;

    private static class zza extends com.google.android.gms.signin.internal.zzd.zza {
        private final ExecutorService zzaVm;
        private final zzqx zzaaT;

        public zza(zzqx com_google_android_gms_internal_zzqx, ExecutorService executorService) {
            this.zzaaT = com_google_android_gms_internal_zzqx;
            this.zzaVm = executorService;
        }

        private ServerAuthCodeCallbacks zzCg() throws RemoteException {
            return this.zzaaT.zzCg();
        }

        public void zza(final String str, final String str2, final zzf com_google_android_gms_signin_internal_zzf) throws RemoteException {
            this.zzaVm.submit(new Runnable(this) {
                final /* synthetic */ zza zzaVq;

                public void run() {
                    try {
                        com_google_android_gms_signin_internal_zzf.zzaq(this.zzaVq.zzCg().onUploadServerAuthCode(str, str2));
                    } catch (Throwable e) {
                        Log.e("SignInClientImpl", "RemoteException thrown when processing uploadServerAuthCode callback", e);
                    }
                }
            });
        }

        public void zza(final String str, final List<Scope> list, final zzf com_google_android_gms_signin_internal_zzf) throws RemoteException {
            this.zzaVm.submit(new Runnable(this) {
                final /* synthetic */ zza zzaVq;

                public void run() {
                    try {
                        CheckResult onCheckServerAuthorization = this.zzaVq.zzCg().onCheckServerAuthorization(str, Collections.unmodifiableSet(new HashSet(list)));
                        com_google_android_gms_signin_internal_zzf.zza(new CheckServerAuthResult(onCheckServerAuthorization.zznD(), onCheckServerAuthorization.zznE()));
                    } catch (Throwable e) {
                        Log.e("SignInClientImpl", "RemoteException thrown when processing checkServerAuthorization callback", e);
                    }
                }
            });
        }
    }

    public zzi(Context context, Looper looper, boolean z, zzf com_google_android_gms_common_internal_zzf, zzqx com_google_android_gms_internal_zzqx, ConnectionCallbacks connectionCallbacks, OnConnectionFailedListener onConnectionFailedListener, ExecutorService executorService) {
        super(context, looper, 44, com_google_android_gms_common_internal_zzf, connectionCallbacks, onConnectionFailedListener);
        this.zzaVl = z;
        this.zzabI = com_google_android_gms_common_internal_zzf;
        this.zzaaT = com_google_android_gms_internal_zzqx;
        this.zzafj = com_google_android_gms_common_internal_zzf.zzoR();
        this.zzaVm = executorService;
    }

    public static Bundle zza(zzqx com_google_android_gms_internal_zzqx, Integer num, ExecutorService executorService) {
        Bundle bundle = new Bundle();
        bundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", com_google_android_gms_internal_zzqx.zzCf());
        bundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", com_google_android_gms_internal_zzqx.zzlY());
        bundle.putString("com.google.android.gms.signin.internal.serverClientId", com_google_android_gms_internal_zzqx.zzmb());
        if (com_google_android_gms_internal_zzqx.zzCg() != null) {
            bundle.putParcelable("com.google.android.gms.signin.internal.signInCallbacks", new BinderWrapper(new zza(com_google_android_gms_internal_zzqx, executorService).asBinder()));
        }
        if (num != null) {
            bundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", num.intValue());
        }
        bundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", com_google_android_gms_internal_zzqx.zzCh());
        bundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", com_google_android_gms_internal_zzqx.zzma());
        return bundle;
    }

    public void connect() {
        zza(new zzj.zzf(this));
    }

    public void zzCe() {
        try {
            ((zzf) zzpc()).zzjq(this.zzafj.intValue());
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
        }
    }

    protected /* synthetic */ IInterface zzW(IBinder iBinder) {
        return zzdO(iBinder);
    }

    public void zza(zzp com_google_android_gms_common_internal_zzp, Set<Scope> set, zze com_google_android_gms_signin_internal_zze) {
        zzx.zzb(com_google_android_gms_signin_internal_zze, "Expecting a valid ISignInCallbacks");
        try {
            ((zzf) zzpc()).zza(new AuthAccountRequest(com_google_android_gms_common_internal_zzp, set), com_google_android_gms_signin_internal_zze);
        } catch (Throwable e) {
            Log.w("SignInClientImpl", "Remote service probably died when authAccount is called");
            try {
                com_google_android_gms_signin_internal_zze.zza(new ConnectionResult(8, null), new AuthAccountResult());
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "ISignInCallbacks#onAuthAccount should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    public void zza(zzp com_google_android_gms_common_internal_zzp, boolean z) {
        try {
            ((zzf) zzpc()).zza(com_google_android_gms_common_internal_zzp, this.zzafj.intValue(), z);
        } catch (RemoteException e) {
            Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
        }
    }

    public void zza(zzt com_google_android_gms_common_internal_zzt) {
        zzx.zzb(com_google_android_gms_common_internal_zzt, "Expecting a valid IResolveAccountCallbacks");
        try {
            ((zzf) zzpc()).zza(new ResolveAccountRequest(this.zzabI.zzoI(), this.zzafj.intValue()), com_google_android_gms_common_internal_zzt);
        } catch (Throwable e) {
            Log.w("SignInClientImpl", "Remote service probably died when resolveAccount is called");
            try {
                com_google_android_gms_common_internal_zzt.zzb(new ResolveAccountResponse(8));
            } catch (RemoteException e2) {
                Log.wtf("SignInClientImpl", "IResolveAccountCallbacks#onAccountResolutionComplete should be executed from the same process, unexpected RemoteException.", e);
            }
        }
    }

    protected zzf zzdO(IBinder iBinder) {
        return com.google.android.gms.signin.internal.zzf.zza.zzdN(iBinder);
    }

    protected String zzfK() {
        return "com.google.android.gms.signin.service.START";
    }

    protected String zzfL() {
        return "com.google.android.gms.signin.internal.ISignInService";
    }

    public boolean zzlN() {
        return this.zzaVl;
    }

    protected Bundle zzly() {
        Bundle zza = zza(this.zzaaT, this.zzabI.zzoR(), this.zzaVm);
        if (!getContext().getPackageName().equals(this.zzabI.zzoN())) {
            zza.putString("com.google.android.gms.signin.internal.realClientPackageName", this.zzabI.zzoN());
        }
        return zza;
    }
}
