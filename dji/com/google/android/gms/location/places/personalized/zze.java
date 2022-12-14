package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.location.places.personalized.internal.TestDataImpl;
import java.util.List;

public class zze implements Creator<PlaceUserData> {
    static void zza(PlaceUserData placeUserData, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, placeUserData.zzxr(), false);
        zzb.zzc(parcel, 1000, placeUserData.mVersionCode);
        zzb.zza(parcel, 2, placeUserData.getPlaceId(), false);
        zzb.zzc(parcel, 5, placeUserData.zzxu(), false);
        zzb.zzc(parcel, 6, placeUserData.zzxs(), false);
        zzb.zzc(parcel, 7, placeUserData.zzxt(), false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzfd(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhB(i);
    }

    public PlaceUserData zzfd(Parcel parcel) {
        List list = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        List list2 = null;
        List list3 = null;
        String str = null;
        String str2 = null;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    str2 = zza.zzp(parcel, zzao);
                    break;
                case 2:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 5:
                    list3 = zza.zzc(parcel, zzao, TestDataImpl.CREATOR);
                    break;
                case 6:
                    list2 = zza.zzc(parcel, zzao, PlaceAlias.CREATOR);
                    break;
                case 7:
                    list = zza.zzc(parcel, zzao, HereContent.CREATOR);
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
            return new PlaceUserData(i, str2, str, list3, list2, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlaceUserData[] zzhB(int i) {
        return new PlaceUserData[i];
    }
}
