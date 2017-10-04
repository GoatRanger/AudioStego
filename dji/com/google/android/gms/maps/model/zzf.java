package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import dji.pilot.visual.a.d;

public class zzf implements Creator<MarkerOptions> {
    static void zza(MarkerOptions markerOptions, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, markerOptions.getVersionCode());
        zzb.zza(parcel, 2, markerOptions.getPosition(), i, false);
        zzb.zza(parcel, 3, markerOptions.getTitle(), false);
        zzb.zza(parcel, 4, markerOptions.getSnippet(), false);
        zzb.zza(parcel, 5, markerOptions.zzxZ(), false);
        zzb.zza(parcel, 6, markerOptions.getAnchorU());
        zzb.zza(parcel, 7, markerOptions.getAnchorV());
        zzb.zza(parcel, 8, markerOptions.isDraggable());
        zzb.zza(parcel, 9, markerOptions.isVisible());
        zzb.zza(parcel, 10, markerOptions.isFlat());
        zzb.zza(parcel, 11, markerOptions.getRotation());
        zzb.zza(parcel, 12, markerOptions.getInfoWindowAnchorU());
        zzb.zza(parcel, 13, markerOptions.getInfoWindowAnchorV());
        zzb.zza(parcel, 14, markerOptions.getAlpha());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfm(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhK(i);
    }

    public MarkerOptions zzfm(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        LatLng latLng = null;
        String str = null;
        String str2 = null;
        IBinder iBinder = null;
        float f = 0.0f;
        float f2 = 0.0f;
        boolean z = false;
        boolean z2 = false;
        boolean z3 = false;
        float f3 = 0.0f;
        float f4 = d.c;
        float f5 = 0.0f;
        float f6 = 1.0f;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    latLng = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 3:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 4:
                    str2 = zza.zzp(parcel, zzao);
                    break;
                case 5:
                    iBinder = zza.zzq(parcel, zzao);
                    break;
                case 6:
                    f = zza.zzl(parcel, zzao);
                    break;
                case 7:
                    f2 = zza.zzl(parcel, zzao);
                    break;
                case 8:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 9:
                    z2 = zza.zzc(parcel, zzao);
                    break;
                case 10:
                    z3 = zza.zzc(parcel, zzao);
                    break;
                case 11:
                    f3 = zza.zzl(parcel, zzao);
                    break;
                case 12:
                    f4 = zza.zzl(parcel, zzao);
                    break;
                case 13:
                    f5 = zza.zzl(parcel, zzao);
                    break;
                case 14:
                    f6 = zza.zzl(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new MarkerOptions(i, latLng, str, str2, iBinder, f, f2, z, z2, z3, f3, f4, f5, f6);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public MarkerOptions[] zzhK(int i) {
        return new MarkerOptions[i];
    }
}
