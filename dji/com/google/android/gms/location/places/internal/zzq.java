package com.google.android.gms.location.places.internal;

import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.location.places.zzf;
import com.google.android.gms.location.places.zzf.zza;

public class zzq implements PlacePhotoMetadata {
    private int mIndex;
    private final int zzAG;
    private final int zzAH;
    private final String zzaHL;
    private final CharSequence zzaHM;

    public zzq(String str, int i, int i2, CharSequence charSequence, int i3) {
        this.zzaHL = str;
        this.zzAG = i;
        this.zzAH = i2;
        this.zzaHM = charSequence;
        this.mIndex = i3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof zzq)) {
            return false;
        }
        zzq com_google_android_gms_location_places_internal_zzq = (zzq) obj;
        return com_google_android_gms_location_places_internal_zzq.zzAG == this.zzAG && com_google_android_gms_location_places_internal_zzq.zzAH == this.zzAH && zzw.equal(com_google_android_gms_location_places_internal_zzq.zzaHL, this.zzaHL) && zzw.equal(com_google_android_gms_location_places_internal_zzq.zzaHM, this.zzaHM);
    }

    public /* synthetic */ Object freeze() {
        return zzxp();
    }

    public CharSequence getAttributions() {
        return this.zzaHM;
    }

    public int getMaxHeight() {
        return this.zzAH;
    }

    public int getMaxWidth() {
        return this.zzAG;
    }

    public PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient googleApiClient) {
        return getScaledPhoto(googleApiClient, getMaxWidth(), getMaxHeight());
    }

    public PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient googleApiClient, int i, int i2) {
        final int i3 = i;
        final int i4 = i2;
        return googleApiClient.zza(new zza<zze>(this, Places.zzaGz, googleApiClient) {
            final /* synthetic */ zzq zzaHP;

            protected void zza(zze com_google_android_gms_location_places_internal_zze) throws RemoteException {
                com_google_android_gms_location_places_internal_zze.zza(new zzf((zza) this), this.zzaHP.zzaHL, i3, i4, this.zzaHP.mIndex);
            }
        });
    }

    public int hashCode() {
        return zzw.hashCode(Integer.valueOf(this.zzAG), Integer.valueOf(this.zzAH), this.zzaHL, this.zzaHM);
    }

    public boolean isDataValid() {
        return true;
    }

    public PlacePhotoMetadata zzxp() {
        return this;
    }
}
