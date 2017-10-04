package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;

public class zzb implements Creator<StreetViewPanoramaOptions> {
    static void zza(StreetViewPanoramaOptions streetViewPanoramaOptions, Parcel parcel, int i) {
        int zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, streetViewPanoramaOptions.getVersionCode());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 2, streetViewPanoramaOptions.getStreetViewPanoramaCamera(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, streetViewPanoramaOptions.getPanoramaId(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, streetViewPanoramaOptions.getPosition(), i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, streetViewPanoramaOptions.getRadius(), false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 6, streetViewPanoramaOptions.zzxP());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 7, streetViewPanoramaOptions.zzxE());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 8, streetViewPanoramaOptions.zzxQ());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 9, streetViewPanoramaOptions.zzxR());
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 10, streetViewPanoramaOptions.zzxA());
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfg(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhE(i);
    }

    public StreetViewPanoramaOptions zzfg(Parcel parcel) {
        Integer num = null;
        byte b = (byte) 0;
        int zzap = zza.zzap(parcel);
        byte b2 = (byte) 0;
        byte b3 = (byte) 0;
        byte b4 = (byte) 0;
        byte b5 = (byte) 0;
        LatLng latLng = null;
        String str = null;
        StreetViewPanoramaCamera streetViewPanoramaCamera = null;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    streetViewPanoramaCamera = (StreetViewPanoramaCamera) zza.zza(parcel, zzao, StreetViewPanoramaCamera.CREATOR);
                    break;
                case 3:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 4:
                    latLng = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 5:
                    num = zza.zzh(parcel, zzao);
                    break;
                case 6:
                    b5 = zza.zze(parcel, zzao);
                    break;
                case 7:
                    b4 = zza.zze(parcel, zzao);
                    break;
                case 8:
                    b3 = zza.zze(parcel, zzao);
                    break;
                case 9:
                    b2 = zza.zze(parcel, zzao);
                    break;
                case 10:
                    b = zza.zze(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new StreetViewPanoramaOptions(i, streetViewPanoramaCamera, str, latLng, num, b5, b4, b3, b2, b);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public StreetViewPanoramaOptions[] zzhE(int i) {
        return new StreetViewPanoramaOptions[i];
    }
}
