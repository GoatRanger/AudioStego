package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<PlacePhotoResult> {
    static void zza(PlacePhotoResult placePhotoResult, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, placePhotoResult.getStatus(), i, false);
        zzb.zzc(parcel, 1000, placePhotoResult.mVersionCode);
        zzb.zza(parcel, 2, placePhotoResult.zzaGs, i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeQ(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhm(i);
    }

    public PlacePhotoResult zzeQ(Parcel parcel) {
        BitmapTeleporter bitmapTeleporter = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzap) {
            int i2;
            BitmapTeleporter bitmapTeleporter2;
            Status status2;
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzao, Status.CREATOR);
                    bitmapTeleporter2 = bitmapTeleporter;
                    status2 = status3;
                    break;
                case 2:
                    bitmapTeleporter2 = (BitmapTeleporter) zza.zza(parcel, zzao, BitmapTeleporter.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    BitmapTeleporter bitmapTeleporter3 = bitmapTeleporter;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzao);
                    bitmapTeleporter2 = bitmapTeleporter3;
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    bitmapTeleporter2 = bitmapTeleporter;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            bitmapTeleporter = bitmapTeleporter2;
        }
        if (parcel.dataPosition() == zzap) {
            return new PlacePhotoResult(i, status, bitmapTeleporter);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlacePhotoResult[] zzhm(int i) {
        return new PlacePhotoResult[i];
    }
}
