package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzh implements Creator<LocationSettingsStates> {
    static void zza(LocationSettingsStates locationSettingsStates, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, locationSettingsStates.isGpsUsable());
        zzb.zzc(parcel, 1000, locationSettingsStates.getVersionCode());
        zzb.zza(parcel, 2, locationSettingsStates.isNetworkLocationUsable());
        zzb.zza(parcel, 3, locationSettingsStates.isBleUsable());
        zzb.zza(parcel, 4, locationSettingsStates.isGpsPresent());
        zzb.zza(parcel, 5, locationSettingsStates.isNetworkLocationPresent());
        zzb.zza(parcel, 6, locationSettingsStates.isBlePresent());
        zzb.zza(parcel, 7, locationSettingsStates.zzwA());
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeE(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzgU(i);
    }

    public LocationSettingsStates zzeE(Parcel parcel) {
        boolean z = false;
        int zzap = zza.zzap(parcel);
        boolean z2 = false;
        boolean z3 = false;
        boolean z4 = false;
        boolean z5 = false;
        boolean z6 = false;
        boolean z7 = false;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    z7 = zza.zzc(parcel, zzao);
                    break;
                case 2:
                    z6 = zza.zzc(parcel, zzao);
                    break;
                case 3:
                    z5 = zza.zzc(parcel, zzao);
                    break;
                case 4:
                    z4 = zza.zzc(parcel, zzao);
                    break;
                case 5:
                    z3 = zza.zzc(parcel, zzao);
                    break;
                case 6:
                    z2 = zza.zzc(parcel, zzao);
                    break;
                case 7:
                    z = zza.zzc(parcel, zzao);
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
            return new LocationSettingsStates(i, z7, z6, z5, z4, z3, z2, z);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LocationSettingsStates[] zzgU(int i) {
        return new LocationSettingsStates[i];
    }
}
