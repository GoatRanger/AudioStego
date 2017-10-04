package com.google.android.gms.common.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzz implements Creator<ResolveAccountResponse> {
    static void zza(ResolveAccountResponse resolveAccountResponse, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, resolveAccountResponse.mVersionCode);
        zzb.zza(parcel, 2, resolveAccountResponse.zzaeH, false);
        zzb.zza(parcel, 3, resolveAccountResponse.zzpr(), i, false);
        zzb.zza(parcel, 4, resolveAccountResponse.zzps());
        zzb.zza(parcel, 5, resolveAccountResponse.zzpt());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzam(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzbK(i);
    }

    public ResolveAccountResponse zzam(Parcel parcel) {
        ConnectionResult connectionResult = null;
        boolean z = false;
        int zzap = zza.zzap(parcel);
        boolean z2 = false;
        IBinder iBinder = null;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    iBinder = zza.zzq(parcel, zzao);
                    break;
                case 3:
                    connectionResult = (ConnectionResult) zza.zza(parcel, zzao, ConnectionResult.CREATOR);
                    break;
                case 4:
                    z2 = zza.zzc(parcel, zzao);
                    break;
                case 5:
                    z = zza.zzc(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new ResolveAccountResponse(i, iBinder, connectionResult, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public ResolveAccountResponse[] zzbK(int i) {
        return new ResolveAccountResponse[i];
    }
}
