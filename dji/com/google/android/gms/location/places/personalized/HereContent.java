package com.google.android.gms.location.places.personalized;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.List;

public class HereContent implements SafeParcelable {
    public static final zzb CREATOR = new zzb();
    final int mVersionCode;
    private final String zzaHW;
    private final List<Action> zzaHX;

    public static final class Action implements SafeParcelable {
        public static final zza CREATOR = new zza();
        final int mVersionCode;
        private final String zzQg;
        private final String zzajf;

        Action(int i, String str, String str2) {
            this.mVersionCode = i;
            this.zzajf = str;
            this.zzQg = str2;
        }

        public int describeContents() {
            zza com_google_android_gms_location_places_personalized_zza = CREATOR;
            return 0;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof Action)) {
                return false;
            }
            Action action = (Action) obj;
            return zzw.equal(this.zzajf, action.zzajf) && zzw.equal(this.zzQg, action.zzQg);
        }

        public String getTitle() {
            return this.zzajf;
        }

        public String getUri() {
            return this.zzQg;
        }

        public int hashCode() {
            return zzw.hashCode(this.zzajf, this.zzQg);
        }

        public String toString() {
            return zzw.zzv(this).zzg("title", this.zzajf).zzg("uri", this.zzQg).toString();
        }

        public void writeToParcel(Parcel parcel, int i) {
            zza com_google_android_gms_location_places_personalized_zza = CREATOR;
            zza.zza(this, parcel, i);
        }
    }

    HereContent(int i, String str, List<Action> list) {
        this.mVersionCode = i;
        this.zzaHW = str;
        this.zzaHX = list;
    }

    public int describeContents() {
        zzb com_google_android_gms_location_places_personalized_zzb = CREATOR;
        return 0;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof HereContent)) {
            return false;
        }
        HereContent hereContent = (HereContent) obj;
        return zzw.equal(this.zzaHW, hereContent.zzaHW) && zzw.equal(this.zzaHX, hereContent.zzaHX);
    }

    public List<Action> getActions() {
        return this.zzaHX;
    }

    public String getData() {
        return this.zzaHW;
    }

    public int hashCode() {
        return zzw.hashCode(this.zzaHW, this.zzaHX);
    }

    public String toString() {
        return zzw.zzv(this).zzg("data", this.zzaHW).zzg("actions", this.zzaHX).toString();
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzb com_google_android_gms_location_places_personalized_zzb = CREATOR;
        zzb.zza(this, parcel, i);
    }
}
