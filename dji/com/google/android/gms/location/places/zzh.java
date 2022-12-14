package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<PlacePhotoMetadataResult> {
    static void zza(PlacePhotoMetadataResult placePhotoMetadataResult, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, placePhotoMetadataResult.getStatus(), i, false);
        zzb.zzc(parcel, 1000, placePhotoMetadataResult.mVersionCode);
        zzb.zza(parcel, 2, placePhotoMetadataResult.zzaGq, i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeP(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhl(i);
    }

    public PlacePhotoMetadataResult zzeP(Parcel parcel) {
        DataHolder dataHolder = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzap) {
            int i2;
            DataHolder dataHolder2;
            Status status2;
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzao, Status.CREATOR);
                    dataHolder2 = dataHolder;
                    status2 = status3;
                    break;
                case 2:
                    dataHolder2 = (DataHolder) zza.zza(parcel, zzao, DataHolder.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    DataHolder dataHolder3 = dataHolder;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzao);
                    dataHolder2 = dataHolder3;
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    dataHolder2 = dataHolder;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            dataHolder = dataHolder2;
        }
        if (parcel.dataPosition() == zzap) {
            return new PlacePhotoMetadataResult(i, status, dataHolder);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlacePhotoMetadataResult[] zzhl(int i) {
        return new PlacePhotoMetadataResult[i];
    }
}
