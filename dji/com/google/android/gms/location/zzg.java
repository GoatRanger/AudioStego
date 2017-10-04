package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzg implements Creator<LocationSettingsResult> {
    static void zza(LocationSettingsResult locationSettingsResult, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, locationSettingsResult.getStatus(), i, false);
        zzb.zzc(parcel, 1000, locationSettingsResult.getVersionCode());
        zzb.zza(parcel, 2, locationSettingsResult.getLocationSettingsStates(), i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeD(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgT(i);
    }

    public LocationSettingsResult zzeD(Parcel parcel) {
        LocationSettingsStates locationSettingsStates = null;
        int zzap = zza.zzap(parcel);
        int i = 0;
        Status status = null;
        while (parcel.dataPosition() < zzap) {
            int i2;
            LocationSettingsStates locationSettingsStates2;
            Status status2;
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i2 = i;
                    Status status3 = (Status) zza.zza(parcel, zzao, Status.CREATOR);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status3;
                    break;
                case 2:
                    locationSettingsStates2 = (LocationSettingsStates) zza.zza(parcel, zzao, LocationSettingsStates.CREATOR);
                    status2 = status;
                    i2 = i;
                    break;
                case 1000:
                    LocationSettingsStates locationSettingsStates3 = locationSettingsStates;
                    status2 = status;
                    i2 = zza.zzg(parcel, zzao);
                    locationSettingsStates2 = locationSettingsStates3;
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    locationSettingsStates2 = locationSettingsStates;
                    status2 = status;
                    i2 = i;
                    break;
            }
            i = i2;
            status = status2;
            locationSettingsStates = locationSettingsStates2;
        }
        if (parcel.dataPosition() == zzap) {
            return new LocationSettingsResult(i, status, locationSettingsStates);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LocationSettingsResult[] zzgT(int i) {
        return new LocationSettingsResult[i];
    }
}
