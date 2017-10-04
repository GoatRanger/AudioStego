package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzg implements Creator<PointOfInterest> {
    static void zza(PointOfInterest pointOfInterest, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, pointOfInterest.getVersionCode());
        zzb.zza(parcel, 2, pointOfInterest.zzaKr, i, false);
        zzb.zza(parcel, 3, pointOfInterest.zzaKs, false);
        zzb.zza(parcel, 4, pointOfInterest.name, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfn(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhL(i);
    }

    public PointOfInterest zzfn(Parcel parcel) {
        String str = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        String str2 = null;
        LatLng latLng = null;
        while (parcel.dataPosition() < zzap) {
            LatLng latLng2;
            int zzg;
            String str3;
            int zzao = zza.zzao(parcel);
            String str4;
            switch (zza.zzbM(zzao)) {
                case 1:
                    str4 = str;
                    str = str2;
                    latLng2 = latLng;
                    zzg = zza.zzg(parcel, zzao);
                    str3 = str4;
                    break;
                case 2:
                    zzg = i;
                    str4 = str2;
                    latLng2 = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    str3 = str;
                    str = str4;
                    break;
                case 3:
                    latLng2 = latLng;
                    zzg = i;
                    str4 = str;
                    str = zza.zzp(parcel, zzao);
                    str3 = str4;
                    break;
                case 4:
                    str3 = zza.zzp(parcel, zzao);
                    str = str2;
                    latLng2 = latLng;
                    zzg = i;
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    str3 = str;
                    str = str2;
                    latLng2 = latLng;
                    zzg = i;
                    break;
            }
            i = zzg;
            latLng = latLng2;
            str2 = str;
            str = str3;
        }
        if (parcel.dataPosition() == zzap) {
            return new PointOfInterest(i, latLng, str2, str);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PointOfInterest[] zzhL(int i) {
        return new PointOfInterest[i];
    }
}
