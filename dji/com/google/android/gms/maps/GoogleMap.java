package com.google.android.gms.maps;

import android.graphics.Bitmap;
import android.location.Location;
import android.os.RemoteException;
import android.view.View;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.LocationSource.OnLocationChangedListener;
import com.google.android.gms.maps.internal.IGoogleMapDelegate;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlay;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.IndoorBuilding;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polygon;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.RuntimeRemoteException;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzd;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzh;

public final class GoogleMap {
    public static final int MAP_TYPE_HYBRID = 4;
    public static final int MAP_TYPE_NONE = 0;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    public static final int MAP_TYPE_TERRAIN = 3;
    private final IGoogleMapDelegate zzaIh;
    private UiSettings zzaIi;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);
    }

    public interface OnIndoorStateChangeListener {
        void onIndoorBuildingFocused();

        void onIndoorLevelActivated(IndoorBuilding indoorBuilding);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedCallback {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationButtonClickListener {
        boolean onMyLocationButtonClick();
    }

    @Deprecated
    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    public interface SnapshotReadyCallback {
        void onSnapshotReady(Bitmap bitmap);
    }

    private static final class zza extends com.google.android.gms.maps.internal.zzb.zza {
        private final CancelableCallback zzaIz;

        zza(CancelableCallback cancelableCallback) {
            this.zzaIz = cancelableCallback;
        }

        public void onCancel() {
            this.zzaIz.onCancel();
        }

        public void onFinish() {
            this.zzaIz.onFinish();
        }
    }

    protected GoogleMap(IGoogleMapDelegate iGoogleMapDelegate) {
        this.zzaIh = (IGoogleMapDelegate) zzx.zzw(iGoogleMapDelegate);
    }

    public final Circle addCircle(CircleOptions circleOptions) {
        try {
            return new Circle(this.zzaIh.addCircle(circleOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        try {
            zzc addGroundOverlay = this.zzaIh.addGroundOverlay(groundOverlayOptions);
            return addGroundOverlay != null ? new GroundOverlay(addGroundOverlay) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        try {
            zzf addMarker = this.zzaIh.addMarker(markerOptions);
            return addMarker != null ? new Marker(addMarker) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            return new Polygon(this.zzaIh.addPolygon(polygonOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polyline addPolyline(PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.zzaIh.addPolyline(polylineOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        try {
            zzh addTileOverlay = this.zzaIh.addTileOverlay(tileOverlayOptions);
            return addTileOverlay != null ? new TileOverlay(addTileOverlay) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        try {
            this.zzaIh.animateCamera(cameraUpdate.zzxw());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, int i, CancelableCallback cancelableCallback) {
        try {
            this.zzaIh.animateCameraWithDurationAndCallback(cameraUpdate.zzxw(), i, cancelableCallback == null ? null : new zza(cancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        try {
            this.zzaIh.animateCameraWithCallback(cameraUpdate.zzxw(), cancelableCallback == null ? null : new zza(cancelableCallback));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void clear() {
        try {
            this.zzaIh.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final CameraPosition getCameraPosition() {
        try {
            return this.zzaIh.getCameraPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public IndoorBuilding getFocusedBuilding() {
        try {
            zzd focusedBuilding = this.zzaIh.getFocusedBuilding();
            return focusedBuilding != null ? new IndoorBuilding(focusedBuilding) : null;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getMapType() {
        try {
            return this.zzaIh.getMapType();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMaxZoomLevel() {
        try {
            return this.zzaIh.getMaxZoomLevel();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMinZoomLevel() {
        try {
            return this.zzaIh.getMinZoomLevel();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @Deprecated
    public final Location getMyLocation() {
        try {
            return this.zzaIh.getMyLocation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Projection getProjection() {
        try {
            return new Projection(this.zzaIh.getProjection());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings getUiSettings() {
        try {
            if (this.zzaIi == null) {
                this.zzaIi = new UiSettings(this.zzaIh.getUiSettings());
            }
            return this.zzaIi;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isBuildingsEnabled() {
        try {
            return this.zzaIh.isBuildingsEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isIndoorEnabled() {
        try {
            return this.zzaIh.isIndoorEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isMyLocationEnabled() {
        try {
            return this.zzaIh.isMyLocationEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isTrafficEnabled() {
        try {
            return this.zzaIh.isTrafficEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        try {
            this.zzaIh.moveCamera(cameraUpdate.zzxw());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setBuildingsEnabled(boolean z) {
        try {
            this.zzaIh.setBuildingsEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setContentDescription(String str) {
        try {
            this.zzaIh.setContentDescription(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean setIndoorEnabled(boolean z) {
        try {
            return this.zzaIh.setIndoorEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setInfoWindowAdapter(final InfoWindowAdapter infoWindowAdapter) {
        if (infoWindowAdapter == null) {
            try {
                this.zzaIh.setInfoWindowAdapter(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setInfoWindowAdapter(new com.google.android.gms.maps.internal.zzd.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public com.google.android.gms.dynamic.zzd zzf(zzf com_google_android_gms_maps_model_internal_zzf) {
                return zze.zzy(infoWindowAdapter.getInfoWindow(new Marker(com_google_android_gms_maps_model_internal_zzf)));
            }

            public com.google.android.gms.dynamic.zzd zzg(zzf com_google_android_gms_maps_model_internal_zzf) {
                return zze.zzy(infoWindowAdapter.getInfoContents(new Marker(com_google_android_gms_maps_model_internal_zzf)));
            }
        });
    }

    public final void setLocationSource(final LocationSource locationSource) {
        if (locationSource == null) {
            try {
                this.zzaIh.setLocationSource(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setLocationSource(new com.google.android.gms.maps.internal.ILocationSourceDelegate.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void activate(final com.google.android.gms.maps.internal.zzh com_google_android_gms_maps_internal_zzh) {
                locationSource.activate(new OnLocationChangedListener(this) {
                    final /* synthetic */ AnonymousClass6 zzaIr;

                    public void onLocationChanged(Location location) {
                        try {
                            com_google_android_gms_maps_internal_zzh.zzd(location);
                        } catch (RemoteException e) {
                            throw new RuntimeRemoteException(e);
                        }
                    }
                });
            }

            public void deactivate() {
                locationSource.deactivate();
            }
        });
    }

    public final void setMapType(int i) {
        try {
            this.zzaIh.setMapType(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationEnabled(boolean z) {
        try {
            this.zzaIh.setMyLocationEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnCameraChangeListener(final OnCameraChangeListener onCameraChangeListener) {
        if (onCameraChangeListener == null) {
            try {
                this.zzaIh.setOnCameraChangeListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnCameraChangeListener(new com.google.android.gms.maps.internal.zze.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void onCameraChange(CameraPosition cameraPosition) {
                onCameraChangeListener.onCameraChange(cameraPosition);
            }
        });
    }

    public final void setOnIndoorStateChangeListener(final OnIndoorStateChangeListener onIndoorStateChangeListener) {
        if (onIndoorStateChangeListener == null) {
            try {
                this.zzaIh.setOnIndoorStateChangeListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnIndoorStateChangeListener(new com.google.android.gms.maps.internal.zzf.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void onIndoorBuildingFocused() {
                onIndoorStateChangeListener.onIndoorBuildingFocused();
            }

            public void zza(zzd com_google_android_gms_maps_model_internal_zzd) {
                onIndoorStateChangeListener.onIndoorLevelActivated(new IndoorBuilding(com_google_android_gms_maps_model_internal_zzd));
            }
        });
    }

    public final void setOnInfoWindowClickListener(final OnInfoWindowClickListener onInfoWindowClickListener) {
        if (onInfoWindowClickListener == null) {
            try {
                this.zzaIh.setOnInfoWindowClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnInfoWindowClickListener(new com.google.android.gms.maps.internal.zzg.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void zze(zzf com_google_android_gms_maps_model_internal_zzf) {
                onInfoWindowClickListener.onInfoWindowClick(new Marker(com_google_android_gms_maps_model_internal_zzf));
            }
        });
    }

    public final void setOnMapClickListener(final OnMapClickListener onMapClickListener) {
        if (onMapClickListener == null) {
            try {
                this.zzaIh.setOnMapClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnMapClickListener(new com.google.android.gms.maps.internal.zzi.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void onMapClick(LatLng latLng) {
                onMapClickListener.onMapClick(latLng);
            }
        });
    }

    public void setOnMapLoadedCallback(final OnMapLoadedCallback onMapLoadedCallback) {
        if (onMapLoadedCallback == null) {
            try {
                this.zzaIh.setOnMapLoadedCallback(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnMapLoadedCallback(new com.google.android.gms.maps.internal.zzj.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void onMapLoaded() throws RemoteException {
                onMapLoadedCallback.onMapLoaded();
            }
        });
    }

    public final void setOnMapLongClickListener(final OnMapLongClickListener onMapLongClickListener) {
        if (onMapLongClickListener == null) {
            try {
                this.zzaIh.setOnMapLongClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnMapLongClickListener(new com.google.android.gms.maps.internal.zzk.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void onMapLongClick(LatLng latLng) {
                onMapLongClickListener.onMapLongClick(latLng);
            }
        });
    }

    public final void setOnMarkerClickListener(final OnMarkerClickListener onMarkerClickListener) {
        if (onMarkerClickListener == null) {
            try {
                this.zzaIh.setOnMarkerClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnMarkerClickListener(new com.google.android.gms.maps.internal.zzm.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public boolean zza(zzf com_google_android_gms_maps_model_internal_zzf) {
                return onMarkerClickListener.onMarkerClick(new Marker(com_google_android_gms_maps_model_internal_zzf));
            }
        });
    }

    public final void setOnMarkerDragListener(final OnMarkerDragListener onMarkerDragListener) {
        if (onMarkerDragListener == null) {
            try {
                this.zzaIh.setOnMarkerDragListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnMarkerDragListener(new com.google.android.gms.maps.internal.zzn.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void zzb(zzf com_google_android_gms_maps_model_internal_zzf) {
                onMarkerDragListener.onMarkerDragStart(new Marker(com_google_android_gms_maps_model_internal_zzf));
            }

            public void zzc(zzf com_google_android_gms_maps_model_internal_zzf) {
                onMarkerDragListener.onMarkerDragEnd(new Marker(com_google_android_gms_maps_model_internal_zzf));
            }

            public void zzd(zzf com_google_android_gms_maps_model_internal_zzf) {
                onMarkerDragListener.onMarkerDrag(new Marker(com_google_android_gms_maps_model_internal_zzf));
            }
        });
    }

    public final void setOnMyLocationButtonClickListener(final OnMyLocationButtonClickListener onMyLocationButtonClickListener) {
        if (onMyLocationButtonClickListener == null) {
            try {
                this.zzaIh.setOnMyLocationButtonClickListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnMyLocationButtonClickListener(new com.google.android.gms.maps.internal.zzo.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public boolean onMyLocationButtonClick() throws RemoteException {
                return onMyLocationButtonClickListener.onMyLocationButtonClick();
            }
        });
    }

    @Deprecated
    public final void setOnMyLocationChangeListener(final OnMyLocationChangeListener onMyLocationChangeListener) {
        if (onMyLocationChangeListener == null) {
            try {
                this.zzaIh.setOnMyLocationChangeListener(null);
                return;
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
        this.zzaIh.setOnMyLocationChangeListener(new com.google.android.gms.maps.internal.zzp.zza(this) {
            final /* synthetic */ GoogleMap zzaIk;

            public void zzq(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) {
                onMyLocationChangeListener.onMyLocationChange((Location) zze.zzp(com_google_android_gms_dynamic_zzd));
            }
        });
    }

    public final void setPadding(int i, int i2, int i3, int i4) {
        try {
            this.zzaIh.setPadding(i, i2, i3, i4);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setTrafficEnabled(boolean z) {
        try {
            this.zzaIh.setTrafficEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void snapshot(SnapshotReadyCallback snapshotReadyCallback) {
        snapshot(snapshotReadyCallback, null);
    }

    public final void snapshot(final SnapshotReadyCallback snapshotReadyCallback, Bitmap bitmap) {
        try {
            this.zzaIh.snapshot(new com.google.android.gms.maps.internal.zzw.zza(this) {
                final /* synthetic */ GoogleMap zzaIk;

                public void onSnapshotReady(Bitmap bitmap) throws RemoteException {
                    snapshotReadyCallback.onSnapshotReady(bitmap);
                }

                public void zzr(com.google.android.gms.dynamic.zzd com_google_android_gms_dynamic_zzd) throws RemoteException {
                    snapshotReadyCallback.onSnapshotReady((Bitmap) zze.zzp(com_google_android_gms_dynamic_zzd));
                }
            }, (zze) (bitmap != null ? zze.zzy(bitmap) : null));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void stopAnimation() {
        try {
            this.zzaIh.stopAnimation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    IGoogleMapDelegate zzxy() {
        return this.zzaIh;
    }
}
