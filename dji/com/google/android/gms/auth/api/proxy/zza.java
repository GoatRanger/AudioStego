package com.google.android.gms.auth.api.proxy;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zza implements Creator<ProxyGrpcRequest> {
    static void zza(ProxyGrpcRequest proxyGrpcRequest, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, proxyGrpcRequest.hostname, false);
        zzb.zzc(parcel, 1000, proxyGrpcRequest.versionCode);
        zzb.zzc(parcel, 2, proxyGrpcRequest.port);
        zzb.zza(parcel, 3, proxyGrpcRequest.timeoutMillis);
        zzb.zza(parcel, 4, proxyGrpcRequest.body, false);
        zzb.zza(parcel, 5, proxyGrpcRequest.method, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzL(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzaC(i);
    }

    public ProxyGrpcRequest zzL(Parcel parcel) {
        int i = 0;
        String str = null;
        int zzap = com.google.android.gms.common.internal.safeparcel.zza.zzap(parcel);
        long j = 0;
        byte[] bArr = null;
        String str2 = null;
        int i2 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = com.google.android.gms.common.internal.safeparcel.zza.zzao(parcel);
            switch (com.google.android.gms.common.internal.safeparcel.zza.zzbM(zzao)) {
                case 1:
                    str2 = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    break;
                case 2:
                    i = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    break;
                case 3:
                    j = com.google.android.gms.common.internal.safeparcel.zza.zzi(parcel, zzao);
                    break;
                case 4:
                    bArr = com.google.android.gms.common.internal.safeparcel.zza.zzs(parcel, zzao);
                    break;
                case 5:
                    str = com.google.android.gms.common.internal.safeparcel.zza.zzp(parcel, zzao);
                    break;
                case 1000:
                    i2 = com.google.android.gms.common.internal.safeparcel.zza.zzg(parcel, zzao);
                    break;
                default:
                    com.google.android.gms.common.internal.safeparcel.zza.zzb(parcel, zzao);
                    break;
            }
        }
        if (parcel.dataPosition() == zzap) {
            return new ProxyGrpcRequest(i2, str2, i, j, bArr, str);
        }
        throw new com.google.android.gms.common.internal.safeparcel.zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public ProxyGrpcRequest[] zzaC(int i) {
        return new ProxyGrpcRequest[i];
    }
}
