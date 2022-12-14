package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.LocationRequest;
import java.util.List;

public class zzm implements Creator<LocationRequestInternal> {
    static void zza(LocationRequestInternal locationRequestInternal, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, locationRequestInternal.zzasN, i, false);
        zzb.zzc(parcel, 1000, locationRequestInternal.getVersionCode());
        zzb.zza(parcel, 2, locationRequestInternal.zzaFE);
        zzb.zza(parcel, 3, locationRequestInternal.zzaFF);
        zzb.zza(parcel, 4, locationRequestInternal.zzaFG);
        zzb.zzc(parcel, 5, locationRequestInternal.zzaFH, false);
        zzb.zza(parcel, 6, locationRequestInternal.mTag, false);
        zzb.zza(parcel, 7, locationRequestInternal.zzaFI);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeH(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzha(i);
    }

    public LocationRequestInternal zzeH(Parcel parcel) {
        String str = null;
        boolean z = true;
        boolean z2 = false;
        int zzap = zza.zzap(parcel);
        List list = LocationRequestInternal.zzaFD;
        boolean z3 = true;
        boolean z4 = false;
        LocationRequest locationRequest = null;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    locationRequest = (LocationRequest) zza.zza(parcel, zzao, LocationRequest.CREATOR);
                    break;
                case 2:
                    z4 = zza.zzc(parcel, zzao);
                    break;
                case 3:
                    z3 = zza.zzc(parcel, zzao);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 5:
                    list = zza.zzc(parcel, zzao, ClientIdentity.CREATOR);
                    break;
                case 6:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 7:
                    z2 = zza.zzc(parcel, zzao);
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
            return new LocationRequestInternal(i, locationRequest, z4, z3, z, list, str, z2);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LocationRequestInternal[] zzha(int i) {
        return new LocationRequestInternal[i];
    }
}
