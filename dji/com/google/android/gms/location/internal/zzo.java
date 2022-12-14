package com.google.android.gms.location.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzo implements Creator<ParcelableGeofence> {
    static void zza(ParcelableGeofence parcelableGeofence, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, parcelableGeofence.getRequestId(), false);
        zzb.zzc(parcel, 1000, parcelableGeofence.getVersionCode());
        zzb.zza(parcel, 2, parcelableGeofence.getExpirationTime());
        zzb.zza(parcel, 3, parcelableGeofence.zzwI());
        zzb.zza(parcel, 4, parcelableGeofence.getLatitude());
        zzb.zza(parcel, 5, parcelableGeofence.getLongitude());
        zzb.zza(parcel, 6, parcelableGeofence.zzwJ());
        zzb.zzc(parcel, 7, parcelableGeofence.zzwK());
        zzb.zzc(parcel, 8, parcelableGeofence.getNotificationResponsiveness());
        zzb.zzc(parcel, 9, parcelableGeofence.zzwL());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeJ(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzhe(i);
    }

    public ParcelableGeofence zzeJ(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        String str = null;
        int i2 = 0;
        short s = (short) 0;
        double d = 0.0d;
        double d2 = 0.0d;
        float f = 0.0f;
        long j = 0;
        int i3 = 0;
        int i4 = -1;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzao);
                    break;
                case 3:
                    s = zza.zzf(parcel, zzao);
                    break;
                case 4:
                    d = zza.zzn(parcel, zzao);
                    break;
                case 5:
                    d2 = zza.zzn(parcel, zzao);
                    break;
                case 6:
                    f = zza.zzl(parcel, zzao);
                    break;
                case 7:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 8:
                    i3 = zza.zzg(parcel, zzao);
                    break;
                case 9:
                    i4 = zza.zzg(parcel, zzao);
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
            return new ParcelableGeofence(i, str, i2, s, d, d2, f, j, i3, i4);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public ParcelableGeofence[] zzhe(int i) {
        return new ParcelableGeofence[i];
    }
}
