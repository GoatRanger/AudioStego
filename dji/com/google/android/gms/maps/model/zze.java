package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<LatLng> {
    static void zza(LatLng latLng, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, latLng.getVersionCode());
        zzb.zza(parcel, 2, latLng.latitude);
        zzb.zza(parcel, 3, latLng.longitude);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfl(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhJ(i);
    }

    public LatLng zzfl(Parcel parcel) {
        double d = 0.0d;
        int zzap = zza.zzap(parcel);
        int i = 0;
        double d2 = 0.0d;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    d2 = zza.zzn(parcel, zzao);
                    break;
                case 3:
                    d = zza.zzn(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new LatLng(i, d2, d);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LatLng[] zzhJ(int i) {
        return new LatLng[i];
    }
}
