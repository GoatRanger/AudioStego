package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<GroundOverlayOptions> {
    static void zza(GroundOverlayOptions groundOverlayOptions, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, groundOverlayOptions.getVersionCode());
        zzb.zza(parcel, 2, groundOverlayOptions.zzxY(), false);
        zzb.zza(parcel, 3, groundOverlayOptions.getLocation(), i, false);
        zzb.zza(parcel, 4, groundOverlayOptions.getWidth());
        zzb.zza(parcel, 5, groundOverlayOptions.getHeight());
        zzb.zza(parcel, 6, groundOverlayOptions.getBounds(), i, false);
        zzb.zza(parcel, 7, groundOverlayOptions.getBearing());
        zzb.zza(parcel, 8, groundOverlayOptions.getZIndex());
        zzb.zza(parcel, 9, groundOverlayOptions.isVisible());
        zzb.zza(parcel, 10, groundOverlayOptions.getTransparency());
        zzb.zza(parcel, 11, groundOverlayOptions.getAnchorU());
        zzb.zza(parcel, 12, groundOverlayOptions.getAnchorV());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfj(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhH(i);
    }

    public GroundOverlayOptions zzfj(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        IBinder iBinder = null;
        LatLng latLng = null;
        float f = 0.0f;
        float f2 = 0.0f;
        LatLngBounds latLngBounds = null;
        float f3 = 0.0f;
        float f4 = 0.0f;
        boolean z = false;
        float f5 = 0.0f;
        float f6 = 0.0f;
        float f7 = 0.0f;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    iBinder = zza.zzq(parcel, zzao);
                    break;
                case 3:
                    latLng = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 4:
                    f = zza.zzl(parcel, zzao);
                    break;
                case 5:
                    f2 = zza.zzl(parcel, zzao);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zza.zza(parcel, zzao, LatLngBounds.CREATOR);
                    break;
                case 7:
                    f3 = zza.zzl(parcel, zzao);
                    break;
                case 8:
                    f4 = zza.zzl(parcel, zzao);
                    break;
                case 9:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 10:
                    f5 = zza.zzl(parcel, zzao);
                    break;
                case 11:
                    f6 = zza.zzl(parcel, zzao);
                    break;
                case 12:
                    f7 = zza.zzl(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new GroundOverlayOptions(i, iBinder, latLng, f, f2, latLngBounds, f3, f4, z, f5, f6, f7);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public GroundOverlayOptions[] zzhH(int i) {
        return new GroundOverlayOptions[i];
    }
}
