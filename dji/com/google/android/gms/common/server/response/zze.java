package com.google.android.gms.common.server.response;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zze implements Creator<SafeParcelResponse> {
    static void zza(SafeParcelResponse safeParcelResponse, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, safeParcelResponse.getVersionCode());
        zzb.zza(parcel, 2, safeParcelResponse.zzpV(), false);
        zzb.zza(parcel, 3, safeParcelResponse.zzpW(), i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzaz(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzbX(i);
    }

    public SafeParcelResponse zzaz(Parcel parcel) {
        FieldMappingDictionary fieldMappingDictionary = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        Parcel parcel2 = null;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    parcel2 = zza.zzE(parcel, zzao);
                    break;
                case 3:
                    fieldMappingDictionary = (FieldMappingDictionary) zza.zza(parcel, zzao, FieldMappingDictionary.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new SafeParcelResponse(i, parcel2, fieldMappingDictionary);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public SafeParcelResponse[] zzbX(int i) {
        return new SafeParcelResponse[i];
    }
}
