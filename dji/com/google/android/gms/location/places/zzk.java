package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.here.odnp.posclient.pos.IPositioningSession;

public class zzk implements Creator<PlaceRequest> {
    static void zza(PlaceRequest placeRequest, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1000, placeRequest.mVersionCode);
        zzb.zza(parcel, 2, placeRequest.zzwO(), i, false);
        zzb.zza(parcel, 3, placeRequest.getInterval());
        zzb.zzc(parcel, 4, placeRequest.getPriority());
        zzb.zza(parcel, 5, placeRequest.getExpirationTime());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeS(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzho(i);
    }

    public PlaceRequest zzeS(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        PlaceFilter placeFilter = null;
        long j = PlaceRequest.zzaGv;
        int i2 = 102;
        long j2 = IPositioningSession.NotSet;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 2:
                    placeFilter = (PlaceFilter) zza.zza(parcel, zzao, PlaceFilter.CREATOR);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzao);
                    break;
                case 4:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 5:
                    j2 = zza.zzi(parcel, zzao);
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
            return new PlaceRequest(i, placeFilter, j, i2, j2);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlaceRequest[] zzho(int i) {
        return new PlaceRequest[i];
    }
}
