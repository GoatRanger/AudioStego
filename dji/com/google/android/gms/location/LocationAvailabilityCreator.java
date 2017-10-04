package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class LocationAvailabilityCreator implements Creator<LocationAvailability> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(LocationAvailability locationAvailability, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationAvailability.zzaEA);
        zzb.zzc(parcel, 1000, locationAvailability.getVersionCode());
        zzb.zzc(parcel, 2, locationAvailability.zzaEB);
        zzb.zza(parcel, 3, locationAvailability.zzaEC);
        zzb.zzc(parcel, 4, locationAvailability.zzaED);
        zzb.zzI(parcel, zzaq);
    }

    public LocationAvailability createFromParcel(Parcel parcel) {
        int i = 1;
        int zzap = zza.zzap(parcel);
        int i2 = 0;
        int i3 = 1000;
        long j = 0;
        int i4 = 1;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i4 = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzao);
                    break;
                case 4:
                    i3 = zza.zzg(parcel, zzao);
                    break;
                case 1000:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new LocationAvailability(i2, i3, i4, i, j);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LocationAvailability[] newArray(int i) {
        return new LocationAvailability[i];
    }
}
