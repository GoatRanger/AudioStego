package com.google.android.gms.auth.api.credentials;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;

public class zzc implements Creator<CredentialRequest> {
    static void zza(CredentialRequest credentialRequest, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, credentialRequest.getSupportsPasswordLogin());
        zzb.zzc(parcel, 1000, credentialRequest.mVersionCode);
        zzb.zza(parcel, 2, credentialRequest.getAccountTypes(), false);
        zzb.zza(parcel, 3, credentialRequest.getCredentialPickerConfig(), i, false);
        zzb.zza(parcel, 4, credentialRequest.getCredentialHintPickerConfig(), i, false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzG(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzax(i);
    }

    public CredentialRequest zzG(Parcel parcel) {
        boolean z = false;
        CredentialPickerConfig credentialPickerConfig = null;
        int zzap = zza.zzap(parcel);
        CredentialPickerConfig credentialPickerConfig2 = null;
        String[] strArr = null;
        int i = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 2:
                    strArr = zza.zzB(parcel, zzao);
                    break;
                case 3:
                    credentialPickerConfig2 = (CredentialPickerConfig) zza.zza(parcel, zzao, CredentialPickerConfig.CREATOR);
                    break;
                case 4:
                    credentialPickerConfig = (CredentialPickerConfig) zza.zza(parcel, zzao, CredentialPickerConfig.CREATOR);
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
            return new CredentialRequest(i, z, strArr, credentialPickerConfig2, credentialPickerConfig);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public CredentialRequest[] zzax(int i) {
        return new CredentialRequest[i];
    }
}
