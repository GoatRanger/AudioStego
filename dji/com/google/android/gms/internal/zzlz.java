package com.google.android.gms.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzlb.zzb;

public final class zzlz implements zzly {

    private static class zza extends zzlw {
        private final zzb<Status> zzagy;

        public zza(zzb<Status> com_google_android_gms_internal_zzlb_zzb_com_google_android_gms_common_api_Status) {
            this.zzagy = com_google_android_gms_internal_zzlb_zzb_com_google_android_gms_common_api_Status;
        }

        public void zzbN(int i) throws RemoteException {
            this.zzagy.zzp(new Status(i));
        }
    }

    public PendingResult<Status> zzb(GoogleApiClient googleApiClient) {
        return googleApiClient.zzb(new zza(this, googleApiClient) {
            final /* synthetic */ zzlz zzagx;

            protected void zza(zzmb com_google_android_gms_internal_zzmb) throws RemoteException {
                ((zzmd) com_google_android_gms_internal_zzmb.zzpc()).zza(new zza(this));
            }
        });
    }
}
