package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzj implements Creator<StreetViewPanoramaCamera> {
    static void zza(StreetViewPanoramaCamera streetViewPanoramaCamera, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaCamera.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaCamera.zoom);
        zzb.zza(parcel, 3, streetViewPanoramaCamera.tilt);
        zzb.zza(parcel, 4, streetViewPanoramaCamera.bearing);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfq(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhO(i);
    }

    public StreetViewPanoramaCamera zzfq(Parcel parcel) {
        float f = 0.0f;
        int zzap = zza.zzap(parcel);
        float f2 = 0.0f;
        int i = 0;
        float f3 = 0.0f;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    f2 = zza.zzl(parcel, zzao);
                    break;
                case 3:
                    f3 = zza.zzl(parcel, zzao);
                    break;
                case 4:
                    f = zza.zzl(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new StreetViewPanoramaCamera(i, f2, f3, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public StreetViewPanoramaCamera[] zzhO(int i) {
        return new StreetViewPanoramaCamera[i];
    }
}
