package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzk implements Creator<StreetViewPanoramaLink> {
    static void zza(StreetViewPanoramaLink streetViewPanoramaLink, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, streetViewPanoramaLink.getVersionCode());
        zzb.zza(parcel, 2, streetViewPanoramaLink.panoId, false);
        zzb.zza(parcel, 3, streetViewPanoramaLink.bearing);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfr(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhP(i);
    }

    public StreetViewPanoramaLink zzfr(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        String str = null;
        float f = 0.0f;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    str = zza.zzp(parcel, zzao);
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
            return new StreetViewPanoramaLink(i, str, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public StreetViewPanoramaLink[] zzhP(int i) {
        return new StreetViewPanoramaLink[i];
    }
}
