package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.util.AttributeSet;
import com.google.android.gms.R;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;

public final class GoogleMapOptions implements SafeParcelable {
    public static final zza CREATOR = new zza();
    private final int mVersionCode;
    private Boolean zzaIA;
    private Boolean zzaIB;
    private int zzaIC;
    private CameraPosition zzaID;
    private Boolean zzaIE;
    private Boolean zzaIF;
    private Boolean zzaIG;
    private Boolean zzaIH;
    private Boolean zzaII;
    private Boolean zzaIJ;
    private Boolean zzaIK;
    private Boolean zzaIL;
    private Boolean zzaIM;

    public GoogleMapOptions() {
        this.zzaIC = -1;
        this.mVersionCode = 1;
    }

    GoogleMapOptions(int i, byte b, byte b2, int i2, CameraPosition cameraPosition, byte b3, byte b4, byte b5, byte b6, byte b7, byte b8, byte b9, byte b10, byte b11) {
        this.zzaIC = -1;
        this.mVersionCode = i;
        this.zzaIA = zza.zza(b);
        this.zzaIB = zza.zza(b2);
        this.zzaIC = i2;
        this.zzaID = cameraPosition;
        this.zzaIE = zza.zza(b3);
        this.zzaIF = zza.zza(b4);
        this.zzaIG = zza.zza(b5);
        this.zzaIH = zza.zza(b6);
        this.zzaII = zza.zza(b7);
        this.zzaIJ = zza.zza(b8);
        this.zzaIK = zza.zza(b9);
        this.zzaIL = zza.zza(b10);
        this.zzaIM = zza.zza(b11);
    }

    public static GoogleMapOptions createFromAttributes(Context context, AttributeSet attributeSet) {
        if (attributeSet == null) {
            return null;
        }
        TypedArray obtainAttributes = context.getResources().obtainAttributes(attributeSet, R.styleable.MapAttrs);
        GoogleMapOptions googleMapOptions = new GoogleMapOptions();
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_mapType)) {
            googleMapOptions.mapType(obtainAttributes.getInt(R.styleable.MapAttrs_mapType, -1));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
            googleMapOptions.zOrderOnTop(obtainAttributes.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
            googleMapOptions.useViewLifecycleInFragment(obtainAttributes.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiCompass)) {
            googleMapOptions.compassEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiCompass, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
            googleMapOptions.rotateGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
            googleMapOptions.scrollGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
            googleMapOptions.tiltGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
            googleMapOptions.zoomGesturesEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
            googleMapOptions.zoomControlsEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_liteMode)) {
            googleMapOptions.liteMode(obtainAttributes.getBoolean(R.styleable.MapAttrs_liteMode, false));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
            googleMapOptions.mapToolbarEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
        }
        if (obtainAttributes.hasValue(R.styleable.MapAttrs_ambientEnabled)) {
            googleMapOptions.ambientEnabled(obtainAttributes.getBoolean(R.styleable.MapAttrs_ambientEnabled, false));
        }
        googleMapOptions.camera(CameraPosition.createFromAttributes(context, attributeSet));
        obtainAttributes.recycle();
        return googleMapOptions;
    }

    public GoogleMapOptions ambientEnabled(boolean z) {
        this.zzaIM = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions camera(CameraPosition cameraPosition) {
        this.zzaID = cameraPosition;
        return this;
    }

    public GoogleMapOptions compassEnabled(boolean z) {
        this.zzaIF = Boolean.valueOf(z);
        return this;
    }

    public int describeContents() {
        return 0;
    }

    public Boolean getAmbientEnabled() {
        return this.zzaIM;
    }

    public CameraPosition getCamera() {
        return this.zzaID;
    }

    public Boolean getCompassEnabled() {
        return this.zzaIF;
    }

    public Boolean getLiteMode() {
        return this.zzaIK;
    }

    public Boolean getMapToolbarEnabled() {
        return this.zzaIL;
    }

    public int getMapType() {
        return this.zzaIC;
    }

    public Boolean getRotateGesturesEnabled() {
        return this.zzaIJ;
    }

    public Boolean getScrollGesturesEnabled() {
        return this.zzaIG;
    }

    public Boolean getTiltGesturesEnabled() {
        return this.zzaII;
    }

    public Boolean getUseViewLifecycleInFragment() {
        return this.zzaIB;
    }

    int getVersionCode() {
        return this.mVersionCode;
    }

    public Boolean getZOrderOnTop() {
        return this.zzaIA;
    }

    public Boolean getZoomControlsEnabled() {
        return this.zzaIE;
    }

    public Boolean getZoomGesturesEnabled() {
        return this.zzaIH;
    }

    public GoogleMapOptions liteMode(boolean z) {
        this.zzaIK = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions mapToolbarEnabled(boolean z) {
        this.zzaIL = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions mapType(int i) {
        this.zzaIC = i;
        return this;
    }

    public GoogleMapOptions rotateGesturesEnabled(boolean z) {
        this.zzaIJ = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions scrollGesturesEnabled(boolean z) {
        this.zzaIG = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions tiltGesturesEnabled(boolean z) {
        this.zzaII = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions useViewLifecycleInFragment(boolean z) {
        this.zzaIB = Boolean.valueOf(z);
        return this;
    }

    public void writeToParcel(Parcel parcel, int i) {
        zza.zza(this, parcel, i);
    }

    public GoogleMapOptions zOrderOnTop(boolean z) {
        this.zzaIA = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomControlsEnabled(boolean z) {
        this.zzaIE = Boolean.valueOf(z);
        return this;
    }

    public GoogleMapOptions zoomGesturesEnabled(boolean z) {
        this.zzaIH = Boolean.valueOf(z);
        return this;
    }

    byte zzxA() {
        return zza.zze(this.zzaIB);
    }

    byte zzxB() {
        return zza.zze(this.zzaIE);
    }

    byte zzxC() {
        return zza.zze(this.zzaIF);
    }

    byte zzxD() {
        return zza.zze(this.zzaIG);
    }

    byte zzxE() {
        return zza.zze(this.zzaIH);
    }

    byte zzxF() {
        return zza.zze(this.zzaII);
    }

    byte zzxG() {
        return zza.zze(this.zzaIJ);
    }

    byte zzxH() {
        return zza.zze(this.zzaIK);
    }

    byte zzxI() {
        return zza.zze(this.zzaIL);
    }

    byte zzxJ() {
        return zza.zze(this.zzaIM);
    }

    byte zzxz() {
        return zza.zze(this.zzaIA);
    }
}
