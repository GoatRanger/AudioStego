package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzo implements Creator<PlaceLocalization> {
    static void zza(PlaceLocalization placeLocalization, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, placeLocalization.name, false);
        zzb.zzc(parcel, 1000, placeLocalization.versionCode);
        zzb.zza(parcel, 2, placeLocalization.address, false);
        zzb.zza(parcel, 3, placeLocalization.zzaHC, false);
        zzb.zza(parcel, 4, placeLocalization.zzaHD, false);
        zzb.zzb(parcel, 5, placeLocalization.zzaHE, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeX(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhv(i);
    }

    public PlaceLocalization zzeX(Parcel parcel) {
        List list = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        String str = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    str4 = zza.zzp(parcel, zzao);
                    break;
                case 2:
                    str3 = zza.zzp(parcel, zzao);
                    break;
                case 3:
                    str2 = zza.zzp(parcel, zzao);
                    break;
                case 4:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 5:
                    list = zza.zzD(parcel, zzao);
                    break;
                case 1000:
                    i = zza.zzg(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new PlaceLocalization(i, str4, str3, str2, str, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlaceLocalization[] zzhv(int i) {
        return new PlaceLocalization[i];
    }
}
