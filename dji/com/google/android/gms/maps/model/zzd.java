package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzd implements Creator<LatLngBounds> {
    static void zza(LatLngBounds latLngBounds, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, latLngBounds.getVersionCode());
        zzb.zza(parcel, 2, latLngBounds.southwest, i, false);
        zzb.zza(parcel, 3, latLngBounds.northeast, i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfk(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhI(i);
    }

    public LatLngBounds zzfk(Parcel parcel) {
        LatLng latLng = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        LatLng latLng2 = null;
        while (parcel.dataPosition() < zzap) {
            int zzg;
            LatLng latLng3;
            int zzao = zza.zzao(parcel);
            LatLng latLng4;
            switch (zza.zzbM(zzao)) {
                case 1:
                    latLng4 = latLng;
                    latLng = latLng2;
                    zzg = zza.zzg(parcel, zzao);
                    latLng3 = latLng4;
                    break;
                case 2:
                    zzg = i;
                    latLng4 = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    latLng3 = latLng;
                    latLng = latLng4;
                    break;
                case 3:
                    latLng3 = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    latLng = latLng2;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    latLng3 = latLng;
                    latLng = latLng2;
                    zzg = i;
                    break;
            }
            i = zzg;
            latLng2 = latLng;
            latLng = latLng3;
        }
        if (parcel.dataPosition() == zzap) {
            return new LatLngBounds(i, latLng2, latLng);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LatLngBounds[] zzhI(int i) {
        return new LatLngBounds[i];
    }
}
