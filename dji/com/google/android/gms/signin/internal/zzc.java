package com.google.android.gms.signin.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzc implements Creator<CheckServerAuthResult> {
    static void zza(CheckServerAuthResult checkServerAuthResult, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, checkServerAuthResult.mVersionCode);
        zzb.zza(parcel, 2, checkServerAuthResult.zzaVi);
        zzb.zzc(parcel, 3, checkServerAuthResult.zzaVj, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzgC(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzjp(i);
    }

    public CheckServerAuthResult zzgC(Parcel parcel) {
        boolean z = false;
        int zzap = zza.zzap(parcel);
        List list = null;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 3:
                    list = zza.zzc(parcel, zzao, Scope.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new CheckServerAuthResult(i, z, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public CheckServerAuthResult[] zzjp(int i) {
        return new CheckServerAuthResult[i];
    }
}
