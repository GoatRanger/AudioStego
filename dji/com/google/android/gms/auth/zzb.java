package com.google.android.gms.auth;

import android.accounts.Account;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzb implements Creator<AccountChangeEventsRequest> {
    static void zza(AccountChangeEventsRequest accountChangeEventsRequest, Parcel parcel, int i) {
        int zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1, accountChangeEventsRequest.mVersion);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, accountChangeEventsRequest.zzRu);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, accountChangeEventsRequest.zzRs, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, accountChangeEventsRequest.zzQd, i, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzA(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzar(i);
    }

    public AccountChangeEventsRequest zzA(Parcel parcel) {
        Account account = null;
        int i = 0;
        int zzap = zza.zzap(parcel);
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 3:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 4:
                    account = (Account) zza.zza(parcel, zzao, Account.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new AccountChangeEventsRequest(i2, i, str, account);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public AccountChangeEventsRequest[] zzar(int i) {
        return new AccountChangeEventsRequest[i];
    }
}
