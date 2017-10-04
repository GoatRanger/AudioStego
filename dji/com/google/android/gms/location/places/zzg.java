package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzg implements Creator<PlaceFilter> {
    static void zza(PlaceFilter placeFilter, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, placeFilter.zzaFX, false);
        zzb.zzc(parcel, 1000, placeFilter.mVersionCode);
        zzb.zza(parcel, 3, placeFilter.zzaGl);
        zzb.zzc(parcel, 4, placeFilter.zzaGa, false);
        zzb.zzb(parcel, 6, placeFilter.zzaFZ, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeO(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhj(i);
    }

    public PlaceFilter zzeO(Parcel parcel) {
        boolean z = false;
        List list = null;
        int zzap = zza.zzap(parcel);
        List list2 = null;
        List list3 = null;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    list3 = zza.zzC(parcel, zzao);
                    break;
                case 3:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 4:
                    list = zza.zzc(parcel, zzao, UserDataType.CREATOR);
                    break;
                case 6:
                    list2 = zza.zzD(parcel, zzao);
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
            return new PlaceFilter(i, list3, z, list2, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlaceFilter[] zzhj(int i) {
        return new PlaceFilter[i];
    }
}
