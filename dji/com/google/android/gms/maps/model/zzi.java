package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzi implements Creator<PolylineOptions> {
    static void zza(PolylineOptions polylineOptions, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, polylineOptions.getVersionCode());
        zzb.zzc(parcel, 2, polylineOptions.getPoints(), false);
        zzb.zza(parcel, 3, polylineOptions.getWidth());
        zzb.zzc(parcel, 4, polylineOptions.getColor());
        zzb.zza(parcel, 5, polylineOptions.getZIndex());
        zzb.zza(parcel, 6, polylineOptions.isVisible());
        zzb.zza(parcel, 7, polylineOptions.isGeodesic());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfp(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhN(i);
    }

    public PolylineOptions zzfp(Parcel parcel) {
        float f = 0.0f;
        boolean z = false;
        int zzap = zza.zzap(parcel);
        List list = null;
        boolean z2 = false;
        int i = 0;
        float f2 = 0.0f;
        int i2 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    list = zza.zzc(parcel, zzao, LatLng.CREATOR);
                    break;
                case 3:
                    f2 = zza.zzl(parcel, zzao);
                    break;
                case 4:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 5:
                    f = zza.zzl(parcel, zzao);
                    break;
                case 6:
                    z2 = zza.zzc(parcel, zzao);
                    break;
                case 7:
                    z = zza.zzc(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new PolylineOptions(i2, list, f2, i, f, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PolylineOptions[] zzhN(int i) {
        return new PolylineOptions[i];
    }
}
