package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.zza;
import com.google.android.gms.common.internal.safeparcel.zzb;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.List;

public class zzl implements Creator<PlaceImpl> {
    static void zza(PlaceImpl placeImpl, Parcel parcel, int i) {
        int zzaq = zzb.zzaq(parcel);
        zzb.zza(parcel, 1, placeImpl.getId(), false);
        zzb.zza(parcel, 2, placeImpl.zzxj(), false);
        zzb.zza(parcel, 3, placeImpl.zzxl(), i, false);
        zzb.zza(parcel, 4, placeImpl.getLatLng(), i, false);
        zzb.zza(parcel, 5, placeImpl.zzxe());
        zzb.zza(parcel, 6, placeImpl.getViewport(), i, false);
        zzb.zza(parcel, 7, placeImpl.zzxk(), false);
        zzb.zza(parcel, 8, placeImpl.getWebsiteUri(), i, false);
        zzb.zza(parcel, 9, placeImpl.zzxh());
        zzb.zza(parcel, 10, placeImpl.getRating());
        zzb.zzc(parcel, 11, placeImpl.getPriceLevel());
        zzb.zza(parcel, 12, placeImpl.zzxi());
        zzb.zza(parcel, 13, placeImpl.zzxd(), false);
        zzb.zza(parcel, 14, placeImpl.getAddress(), false);
        zzb.zza(parcel, 15, placeImpl.getPhoneNumber(), false);
        zzb.zzb(parcel, 17, placeImpl.zzxg(), false);
        zzb.zza(parcel, 16, placeImpl.zzxf(), false);
        zzb.zzc(parcel, 1000, placeImpl.mVersionCode);
        zzb.zza(parcel, 19, placeImpl.getName(), false);
        zzb.zza(parcel, 18, placeImpl.zzaHu);
        zzb.zza(parcel, 20, placeImpl.getPlaceTypes(), false);
        zzb.zzI(parcel, zzaq);
    }

    public /* synthetic */ Object createFromParcel(Parcel parcel) {
        return zzeV(parcel);
    }

    public /* synthetic */ Object[] newArray(int i) {
        return zzht(i);
    }

    public PlaceImpl zzeV(Parcel parcel) {
        int zzap = zza.zzap(parcel);
        int i = 0;
        String str = null;
        List list = null;
        List list2 = null;
        Bundle bundle = null;
        String str2 = null;
        String str3 = null;
        String str4 = null;
        String str5 = null;
        List list3 = null;
        LatLng latLng = null;
        float f = 0.0f;
        LatLngBounds latLngBounds = null;
        String str6 = null;
        Uri uri = null;
        boolean z = false;
        float f2 = 0.0f;
        int i2 = 0;
        long j = 0;
        boolean z2 = false;
        PlaceLocalization placeLocalization = null;
        while (parcel.dataPosition() < zzap) {
            int zzao = zza.zzao(parcel);
            switch (zza.zzbM(zzao)) {
                case 1:
                    str = zza.zzp(parcel, zzao);
                    break;
                case 2:
                    bundle = zza.zzr(parcel, zzao);
                    break;
                case 3:
                    placeLocalization = (PlaceLocalization) zza.zza(parcel, zzao, (Creator) PlaceLocalization.CREATOR);
                    break;
                case 4:
                    latLng = (LatLng) zza.zza(parcel, zzao, LatLng.CREATOR);
                    break;
                case 5:
                    f = zza.zzl(parcel, zzao);
                    break;
                case 6:
                    latLngBounds = (LatLngBounds) zza.zza(parcel, zzao, (Creator) LatLngBounds.CREATOR);
                    break;
                case 7:
                    str6 = zza.zzp(parcel, zzao);
                    break;
                case 8:
                    uri = (Uri) zza.zza(parcel, zzao, Uri.CREATOR);
                    break;
                case 9:
                    z = zza.zzc(parcel, zzao);
                    break;
                case 10:
                    f2 = zza.zzl(parcel, zzao);
                    break;
                case 11:
                    i2 = zza.zzg(parcel, zzao);
                    break;
                case 12:
                    j = zza.zzi(parcel, zzao);
                    break;
                case 13:
                    list2 = zza.zzC(parcel, zzao);
                    break;
                case 14:
                    str3 = zza.zzp(parcel, zzao);
                    break;
                case 15:
                    str4 = zza.zzp(parcel, zzao);
                    break;
                case 16:
                    str5 = zza.zzp(parcel, zzao);
                    break;
                case 17:
                    list3 = zza.zzD(parcel, zzao);
                    break;
                case 18:
                    z2 = zza.zzc(parcel, zzao);
                    break;
                case 19:
                    str2 = zza.zzp(parcel, zzao);
                    break;
                case 20:
                    list = zza.zzC(parcel, zzao);
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
            return new PlaceImpl(i, str, list, list2, bundle, str2, str3, str4, str5, list3, latLng, f, latLngBounds, str6, uri, z, f2, i2, j, z2, placeLocalization);
        }
        throw new zza.zza("Overread allowed size end=" + zzap, parcel);
    }

    public PlaceImpl[] zzht(int i) {
        return new PlaceImpl[i];
    }
}
