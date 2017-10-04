package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzf implements Creator<LocationSettingsRequest> {
    static void zza(LocationSettingsRequest locationSettingsRequest, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationSettingsRequest.zztd(), false);
        zzb.zzc(parcel, 1000, locationSettingsRequest.getVersionCode());
        zzb.zza(parcel, 2, locationSettingsRequest.zzwx());
        zzb.zza(parcel, 3, locationSettingsRequest.zzwy());
        zzb.zza(parcel, 4, locationSettingsRequest.zzwz());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeC(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgS(i);
    }

    public LocationSettingsRequest zzeC(Parcel parcel) {
        boolean z = false;
        int zzap = zza.zzap(parcel);
        List list = null;
        boolean z2 = false;
        boolean z3 = false;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    list = zza.zzc(parcel, zzao, LocationRequest.CREATOR);
                    break;
                case 2:
                    z3 = zza.zzc(parcel, zzao);
                    break;
                case 3:
                    z2 = zza.zzc(parcel, zzao);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new LocationSettingsRequest(i, list, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LocationSettingsRequest[] zzgS(int i) {
        return new LocationSettingsRequest[i];
    }
}
