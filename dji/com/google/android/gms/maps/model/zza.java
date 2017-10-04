package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<CameraPosition> {
    static void zza(CameraPosition cameraPosition, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, cameraPosition.getVersionCode());
        zzb.zza(parcel, 2, cameraPosition.target, i, false);
        zzb.zza(parcel, 3, cameraPosition.zoom);
        zzb.zza(parcel, 4, cameraPosition.tilt);
        zzb.zza(parcel, 5, cameraPosition.bearing);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfh(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhF(i);
    }

    public CameraPosition zzfh(Parcel parcel) {
        float f = 0.0f;
        int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        int i = 0;
        LatLng latLng = null;
        float f2 = 0.0f;
        float f3 = 0.0f;
        while (parcel.dataPosition() < zzap) {
            int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                case 1:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    break;
                case 2:
                    latLng = (LatLng) com.google.android.gms.common.internal.safeparcel.zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 3:
                    f3 = com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, zzao);
                    break;
                case 4:
                    f2 = com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, zzao);
                    break;
                case 5:
                    f = com.google.android.gms.common.internal.safeparcel.zza.zzl(parcel, zzao);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new CameraPosition(i, latLng, f3, f2, f);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public CameraPosition[] zzhF(int i) {
        return new CameraPosition[i];
    }
}
