package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzm implements Creator<PlaceLikelihoodEntity> {
    static void zza(PlaceLikelihoodEntity placeLikelihoodEntity, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, placeLikelihoodEntity.zzaHA, i, false);
        zzb.zzc(parcel, 1000, placeLikelihoodEntity.mVersionCode);
        zzb.zza(parcel, 2, placeLikelihoodEntity.zzaHB);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeW(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhu(i);
    }

    public PlaceLikelihoodEntity zzeW(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        PlaceImpl placeImpl = null;
        float f = 0.0f;
        while (parcel.dataPosition() < zzap) {
            int i2;
            float f2;
            PlaceImpl placeImpl2;
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i2 = i;
                    PlaceImpl placeImpl3 = (PlaceImpl) zza.zza(parcel, zzao, PlaceImpl.CREATOR);
                    f2 = f;
                    placeImpl2 = placeImpl3;
                    break;
                case 2:
                    f2 = zza.zzl(parcel, zzao);
                    placeImpl2 = placeImpl;
                    i2 = i;
                    break;
                case 1000:
                    float f3 = f;
                    placeImpl2 = placeImpl;
                    i2 = zza.zzg(parcel, zzao);
                    f2 = f3;
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    f2 = f;
                    placeImpl2 = placeImpl;
                    i2 = i;
                    break;
            }
            i = i2;
            placeImpl = placeImpl2;
            f = f2;
        }
        if (parcel.dataPosition() == zzap) {
            return new PlaceLikelihoodEntity(i, placeImpl, f);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlaceLikelihoodEntity[] zzhu(int i) {
        return new PlaceLikelihoodEntity[i];
    }
}
