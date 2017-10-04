package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zzd implements Creator<NearbyAlertFilter> {
    static void zza(NearbyAlertFilter nearbyAlertFilter, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzb(parcel, 1, nearbyAlertFilter.zzaFZ, false);
        zzb.zzc(parcel, 1000, nearbyAlertFilter.mVersionCode);
        zzb.zza(parcel, 2, nearbyAlertFilter.zzaFX, false);
        zzb.zzc(parcel, 3, nearbyAlertFilter.zzaGa, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeM(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhh(i);
    }

    public NearbyAlertFilter zzeM(Parcel parcel) {
        List list = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        List list2 = null;
        List list3 = null;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    list3 = zza.zzD(parcel, zzao);
                    break;
                case 2:
                    list2 = zza.zzC(parcel, zzao);
                    break;
                case 3:
                    list = zza.zzc(parcel, zzao, UserDataType.CREATOR);
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
            return new NearbyAlertFilter(i, list3, list2, list);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public NearbyAlertFilter[] zzhh(int i) {
        return new NearbyAlertFilter[i];
    }
}
