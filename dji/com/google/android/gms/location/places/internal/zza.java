package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.places.internal.AutocompletePredictionEntity.SubstringEntity;
import java.util.List;

public class zza implements Creator<AutocompletePredictionEntity> {
    static void zza(AutocompletePredictionEntity autocompletePredictionEntity, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, autocompletePredictionEntity.zzaGO, false);
        zzb.zzc(parcel, 1000, autocompletePredictionEntity.mVersionCode);
        zzb.zza(parcel, 2, autocompletePredictionEntity.zzaGt, false);
        zzb.zza(parcel, 3, autocompletePredictionEntity.zzaFT, false);
        zzb.zzc(parcel, 4, autocompletePredictionEntity.zzaGP, false);
        zzb.zzc(parcel, 5, autocompletePredictionEntity.zzaGQ);
        zzb.zza(parcel, 6, autocompletePredictionEntity.zzaGR, false);
        zzb.zzc(parcel, 7, autocompletePredictionEntity.zzaGS, false);
        zzb.zza(parcel, 8, autocompletePredictionEntity.zzaGT, false);
        zzb.zzc(parcel, 9, autocompletePredictionEntity.zzaGU, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeU(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhr(i);
    }

    public AutocompletePredictionEntity zzeU(Parcel parcel) {
        int i = 0;
        List list = null;
        int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        String str = null;
        List list2 = null;
        String str2 = null;
        List list3 = null;
        String str3 = null;
        List list4 = null;
        String str4 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                case 1:
                    str3 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    break;
                case 2:
                    str4 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    break;
                case 3:
                    list4 = com.google.android.gms.common.internal.safeparcel.zza.zzC(parcel, zzao);
                    break;
                case 4:
                    list3 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao, SubstringEntity.CREATOR);
                    break;
                case 5:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    break;
                case 6:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    break;
                case 7:
                    list2 = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao, SubstringEntity.CREATOR);
                    break;
                case 8:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    break;
                case 9:
                    list = com.google.android.gms.common.internal.safeparcel.zza.zzc(parcel, zzao, SubstringEntity.CREATOR);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new AutocompletePredictionEntity(i2, str4, list4, i, str3, list3, str2, list2, str, list);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public AutocompletePredictionEntity[] zzhr(int i) {
        return new AutocompletePredictionEntity[i];
    }
}
