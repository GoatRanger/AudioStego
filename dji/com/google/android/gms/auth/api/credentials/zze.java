package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import java.util.List;

public class zze implements Creator<PasswordSpecification> {
    static void zza(PasswordSpecification passwordSpecification, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, passwordSpecification.zzSv, false);
        zzb.zzc(parcel, 1000, passwordSpecification.mVersionCode);
        zzb.zzb(parcel, 2, passwordSpecification.zzSw, false);
        zzb.zza(parcel, 3, passwordSpecification.zzSx, false);
        zzb.zzc(parcel, 4, passwordSpecification.zzSy);
        zzb.zzc(parcel, 5, passwordSpecification.zzSz);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzI(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaz(i);
    }

    public PasswordSpecification zzI(Parcel parcel) {
        List list = null;
        int i = 0;
        int zzap = zza.zzap(parcel);
        int i2 = 0;
        List list2 = null;
        String str = null;
        int i3 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 2:
                    list2 = zza.zzD(parcel, zzao);
                    break;
                case 3:
                    list = zza.zzC(parcel, zzao);
                    break;
                case 4:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 5:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 1000:
                    i3 = zza.zzg(parcel, zzao);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new PasswordSpecification(i3, str, list2, list, i2, i);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PasswordSpecification[] zzaz(int i) {
        return new PasswordSpecification[i];
    }
}
