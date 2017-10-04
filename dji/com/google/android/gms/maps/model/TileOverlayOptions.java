package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.model.internal.zzi;
import com.google.android.gms.maps.model.internal.zzi.zza;

public final class TileOverlayOptions implements SafeParcelable {
    public static final zzo CREATOR = new zzo();
    private final int mVersionCode;
    private float zzaJS;
    private boolean zzaJT;
    private zzi zzaKA;
    private TileProvider zzaKB;
    private boolean zzaKC;

    public TileOverlayOptions() {
        this.zzaJT = true;
        this.zzaKC = true;
        this.mVersionCode = 1;
    }

    TileOverlayOptions(int i, IBinder iBinder, boolean z, float f, boolean z2) {
        this.zzaJT = true;
        this.zzaKC = true;
        this.mVersionCode = i;
        this.zzaKA = zza.zzcY(iBinder);
        this.zzaKB = this.zzaKA == null ? null : new TileProvider(this) {
            private final zzi zzaKD = this.zzaKE.zzaKA;
            final /* synthetic */ TileOverlayOptions zzaKE;

            {
                this.zzaKE = r2;
            }

            public Tile getTile(int i, int i2, int i3) {
                try {
                    return this.zzaKD.getTile(i, i2, i3);
                } catch (RemoteException e) {
                    return null;
                }
            }
        };
        this.zzaJT = z;
        this.zzaJS = f;
        this.zzaKC = z2;
    }

    public int describeContents() {
        return 0;
    }

    public TileOverlayOptions fadeIn(boolean z) {
        this.zzaKC = z;
        return this;
    }

    public boolean getFadeIn() {
        return this.zzaKC;
    }

    public TileProvider getTileProvider() {
        return this.zzaKB;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public float getZIndex() {
        return this.zzaJS;
    }

    public boolean isVisible() {
        return this.zzaJT;
    }

    public TileOverlayOptions tileProvider(final TileProvider tileProvider) {
        this.zzaKB = tileProvider;
        this.zzaKA = this.zzaKB == null ? null : new zza(this) {
            final /* synthetic */ TileOverlayOptions zzaKE;

            public Tile getTile(int i, int i2, int i3) {
                return tileProvider.getTile(i, i2, i3);
            }
        };
        return this;
    }

    public TileOverlayOptions visible(boolean z) {
        this.zzaJT = z;
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zzo.zza(this, parcel, i);
    }

    public TileOverlayOptions zIndex(float f) {
        this.zzaJS = f;
        return this;
    }

    IBinder zzyb() {
        return this.zzaKA.asBinder();
    }
}
