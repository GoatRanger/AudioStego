package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzi implements Creator<GetServiceRequest> {
    static void zza(GetServiceRequest getServiceRequest, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, getServiceRequest.version);
        zzb.zzc(parcel, 2, getServiceRequest.zzafq);
        zzb.zzc(parcel, 3, getServiceRequest.zzafr);
        zzb.zza(parcel, 4, getServiceRequest.zzafs, false);
        zzb.zza(parcel, 5, getServiceRequest.zzaft, false);
        zzb.zza(parcel, 6, getServiceRequest.zzafu, i, false);
        zzb.zza(parcel, 7, getServiceRequest.zzafv, false);
        zzb.zza(parcel, 8, getServiceRequest.zzafw, i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzak(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzbD(i);
    }

    public GetServiceRequest zzak(Parcel parcel) {
        int i = 0;
        Account account = null;
        int zzap = zza.zzap(parcel);
        Bundle bundle = null;
        Scope[] scopeArr = null;
        IBinder iBinder = null;
        String str = null;
        int i2 = 0;
        int i3 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i3 = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 3:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 4:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 5:
                    iBinder = zza.zzq(parcel, zzao);
                    break;
                case 6:
                    scopeArr = (Scope[]) zza.zzb(parcel, zzao, Scope.CREATOR);
                    break;
                case 7:
                    bundle = zza.zzr(parcel, zzao);
                    break;
                case 8:
                    account = (Account) zza.zza(parcel, zzao, Account.CREATOR);
                    break;
                default:
                    zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new GetServiceRequest(i3, i2, i, str, iBinder, scopeArr, bundle, account);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public GetServiceRequest[] zzbD(int i) {
        return new GetServiceRequest[i];
    }
}
