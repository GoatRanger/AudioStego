package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.here.odnp.posclient.pos.IPositioningSession;

public class LocationRequestCreator implements Creator<LocationRequest> {
    public static final int CONTENT_DESCRIPTION = 0;

    static void zza(LocationRequest locationRequest, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zzc(parcel, 1, locationRequest.mPriority);
        zzb.zzc(parcel, 1000, locationRequest.getVersionCode());
        zzb.zza(parcel, 2, locationRequest.zzaEE);
        zzb.zza(parcel, 3, locationRequest.zzaEF);
        zzb.zza(parcel, 4, locationRequest.zzasP);
        zzb.zza(parcel, 5, locationRequest.zzaEj);
        zzb.zzc(parcel, 6, locationRequest.zzaEG);
        zzb.zza(parcel, 7, locationRequest.zzaEH);
        zzb.zza(parcel, 8, locationRequest.zzaEI);
        zzb.zzI(parcel, zzaq);
    }

    public LocationRequest createFromParcel(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        int i2 = 102;
        long j = 3600000;
        long j2 = 600000;
        boolean z = false;
        long j3 = IPositioningSession.NotSet;
        int i3 = Integer.MAX_VALUE;
        float f = 0.0f;
        long j4 = 0;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 2:
                    j = zza.zzi(parcel, zzao);
                    break;
                case 3:
                    j2 = zza.zzi(parcel, zzao);
                    break;
                case 4:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 5:
                    j3 = zza.zzi(parcel, zzao);
                    break;
                case 6:
                    i3 = zza.zzg(parcel, zzao);
                    break;
                case 7:
                    f = zza.zzl(parcel, zzao);
                    break;
                case 8:
                    j4 = zza.zzi(parcel, zzao);
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
            return new LocationRequest(i, i2, j, j2, z, j3, i3, f, j4);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public LocationRequest[] newArray(int i) {
        return new LocationRequest[i];
    }
}
