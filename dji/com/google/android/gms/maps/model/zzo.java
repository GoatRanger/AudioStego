package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo implements Creator<TileOverlayOptions> {
    static void zza(TileOverlayOptions tileOverlayOptions, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, tileOverlayOptions.getVersionCode());
        zzb.zza(parcel, 2, tileOverlayOptions.zzyb(), false);
        zzb.zza(parcel, 3, tileOverlayOptions.isVisible());
        zzb.zza(parcel, 4, tileOverlayOptions.getZIndex());
        zzb.zza(parcel, 5, tileOverlayOptions.getFadeIn());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfv(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhT(i);
    }

    public TileOverlayOptions zzfv(Parcel parcel) {
        boolean z = false;
        int zzap = zza.zzap(parcel);
        IBinder iBinder = null;
        float f = 0.0f;
        boolean z2 = true;
        int i = 0;
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
                    z = zza.zzc(parcel, zzao);
                    break;
                case 4:
                    f = zza.zzl(parcel, zzao);
                    break;
                case 5:
                    z2 = zza.zzc(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new TileOverlayOptions(i, iBinder, z, f, z2);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public TileOverlayOptions[] zzhT(int i) {
        return new TileOverlayOptions[i];
    }
}
