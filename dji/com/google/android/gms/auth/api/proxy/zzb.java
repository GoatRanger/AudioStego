package com.google.android.gms.auth.api.proxy;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;

public class zzb implements Creator<ProxyRequest> {
    static void zza(ProxyRequest proxyRequest, Parcel parcel, int i) {
        int zzaq = com.google.android.gms.common.internal.safeparcel.zzb.zzaq(parcel);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 1, proxyRequest.url, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 1000, proxyRequest.versionCode);
        com.google.android.gms.common.internal.safeparcel.zzb.zzc(parcel, 2, proxyRequest.httpMethod);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 3, proxyRequest.timeoutMillis);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 4, proxyRequest.body, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zza(parcel, 5, proxyRequest.zzSK, false);
        com.google.android.gms.common.internal.safeparcel.zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzM(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaD(i);
    }

    public ProxyRequest zzM(Parcel parcel) {
        int i = 0;
        Bundle bundle = null;
        int zzap = zza.zzap(parcel);
        long j = 0;
        byte[] bArr = null;
        String str = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 2:
                    i = zza.zzg(parcel, zzao);
                    break;
                case 3:
                    j = zza.zzi(parcel, zzao);
                    break;
                case 4:
                    bArr = zza.zzs(parcel, zzao);
                    break;
                case 5:
                    bundle = zza.zzr(parcel, zzao);
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
            return new ProxyRequest(i2, str, i, j, bArr, bundle);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public ProxyRequest[] zzaD(int i) {
        return new ProxyRequest[i];
    }
}
