package com.google.android.gms.internal;

import android.content.Context;
import android.os.RemoteException;
import com.google.android.gms.auth.api.proxy.ProxyApi;
import com.google.android.gms.auth.api.proxy.ProxyApi.ProxyResult;
import com.google.android.gms.auth.api.proxy.ProxyRequest;
import com.google.android.gms.auth.api.proxy.ProxyResponse;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzx;

public class zzkm implements ProxyApi {
    public PendingResult<ProxyResult> performProxyRequest(GoogleApiClient googleApiClient, final ProxyRequest proxyRequest) {
        zzx.zzw(googleApiClient);
        zzx.zzw(proxyRequest);
        return googleApiClient.zzb(new zzkl(this, googleApiClient) {
            final /* synthetic */ zzkm zzSR;

            protected void zza(Context context, zzkk com_google_android_gms_internal_zzkk) throws RemoteException {
                com_google_android_gms_internal_zzkk.zza(new zzkh(this) {
                    final /* synthetic */ AnonymousClass1 zzSS;

                    {
                        this.zzSS = r1;
                    }

                    public void zza(ProxyResponse proxyResponse) {
                        this.zzSS.zzb(new zzkn(proxyResponse));
                    }
                }, proxyRequest);
            }
        });
    }
}
