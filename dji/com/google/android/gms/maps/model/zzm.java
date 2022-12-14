package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm implements Creator<StreetViewPanoramaOrientation> {
    static void zza(StreetViewPanoramaOrientation streetViewPanoramaOrientation, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaOrientation.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaOrientation.tilt);
        zzb.zza(parcel, 3, streetViewPanoramaOrientation.bearing);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzft(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhR(i);
    }

    public StreetViewPanoramaOrientation zzft(Parcel parcel) {
        float f = 0.0f;
        int zzap = zza.zzap(parcel);
        int i = 0;
        float f2 = 0.0f;
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
                    f = zza.zzl(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new StreetViewPanoramaOrientation(i, f2, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public StreetViewPanoramaOrientation[] zzhR(int i) {
        return new StreetViewPanoramaOrientation[i];
    }
}
