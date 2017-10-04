package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzn implements Creator<Tile> {
    static void zza(Tile tile, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, tile.getVersionCode());
        zzb.zzc(parcel, 2, tile.width);
        zzb.zzc(parcel, 3, tile.height);
        zzb.zza(parcel, 4, tile.data, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfu(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhS(i);
    }

    public Tile zzfu(Parcel parcel) {
        int i = 0;
        int zzap = zza.zzap(parcel);
        byte[] bArr = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i3 = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 4:
                    bArr = zza.zzs(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new Tile(i3, i2, i, bArr);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public Tile[] zzhS(int i) {
        return new Tile[i];
    }
}
