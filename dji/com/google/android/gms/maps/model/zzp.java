package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzp implements Creator<VisibleRegion> {
    static void zza(VisibleRegion visibleRegion, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, visibleRegion.getVersionCode());
        zzb.zza(parcel, 2, visibleRegion.nearLeft, i, false);
        zzb.zza(parcel, 3, visibleRegion.nearRight, i, false);
        zzb.zza(parcel, 4, visibleRegion.farLeft, i, false);
        zzb.zza(parcel, 5, visibleRegion.farRight, i, false);
        zzb.zza(parcel, 6, visibleRegion.latLngBounds, i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfw(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhU(i);
    }

    public VisibleRegion zzfw(Parcel parcel) {
        LatLngBounds latLngBounds = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        LatLng latLng = null;
        LatLng latLng2 = null;
        LatLng latLng3 = null;
        LatLng latLng4 = null;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    latLng4 = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 3:
                    latLng3 = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 4:
                    latLng2 = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 5:
                    latLng = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zza.zza(parcel, zzao, LatLngBounds.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new VisibleRegion(i, latLng4, latLng3, latLng2, latLng, latLngBounds);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public VisibleRegion[] zzhU(int i) {
        return new VisibleRegion[i];
    }
}
