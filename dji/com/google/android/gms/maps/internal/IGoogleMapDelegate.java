package com.google.android.gms.maps.internal;

import android.location.Location;
import android.os.Bundle;
import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.GroundOverlayOptions;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolygonOptions;
import com.google.android.gms.maps.model.PolylineOptions;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.android.gms.maps.model.internal.IPolylineDelegate;
import com.google.android.gms.maps.model.internal.zzb;
import com.google.android.gms.maps.model.internal.zzc;
import com.google.android.gms.maps.model.internal.zzf;
import com.google.android.gms.maps.model.internal.zzg;
import com.google.android.gms.maps.model.internal.zzh;

public interface IGoogleMapDelegate extends IInterface {
    zzb addCircle(CircleOptions circleOptions) throws RemoteException;

    zzc addGroundOverlay(GroundOverlayOptions groundOverlayOptions) throws RemoteException;

    zzf addMarker(MarkerOptions markerOptions) throws RemoteException;

    zzg addPolygon(PolygonOptions polygonOptions) throws RemoteException;

    IPolylineDelegate addPolyline(PolylineOptions polylineOptions) throws RemoteException;

    zzh addTileOverlay(TileOverlayOptions tileOverlayOptions) throws RemoteException;

    void animateCamera(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    void animateCameraWithCallback(zzd com_google_android_gms_dynamic_zzd, zzb com_google_android_gms_maps_internal_zzb) throws RemoteException;

    void animateCameraWithDurationAndCallback(zzd com_google_android_gms_dynamic_zzd, int i, zzb com_google_android_gms_maps_internal_zzb) throws RemoteException;

    void clear() throws RemoteException;

    CameraPosition getCameraPosition() throws RemoteException;

    com.google.android.gms.maps.model.internal.zzd getFocusedBuilding() throws RemoteException;

    void getMapAsync(zzl com_google_android_gms_maps_internal_zzl) throws RemoteException;

    int getMapType() throws RemoteException;

    float getMaxZoomLevel() throws RemoteException;

    float getMinZoomLevel() throws RemoteException;

    Location getMyLocation() throws RemoteException;

    IProjectionDelegate getProjection() throws RemoteException;

    IUiSettingsDelegate getUiSettings() throws RemoteException;

    boolean isBuildingsEnabled() throws RemoteException;

    boolean isIndoorEnabled() throws RemoteException;

    boolean isMyLocationEnabled() throws RemoteException;

    boolean isTrafficEnabled() throws RemoteException;

    void moveCamera(zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    void onCreate(Bundle bundle) throws RemoteException;

    void onDestroy() throws RemoteException;

    void onEnterAmbient(Bundle bundle) throws RemoteException;

    void onExitAmbient() throws RemoteException;

    void onLowMemory() throws RemoteException;

    void onPause() throws RemoteException;

    void onResume() throws RemoteException;

    void onSaveInstanceState(Bundle bundle) throws RemoteException;

    void setBuildingsEnabled(boolean z) throws RemoteException;

    void setContentDescription(String str) throws RemoteException;

    boolean setIndoorEnabled(boolean z) throws RemoteException;

    void setInfoWindowAdapter(zzd com_google_android_gms_maps_internal_zzd) throws RemoteException;

    void setLocationSource(ILocationSourceDelegate iLocationSourceDelegate) throws RemoteException;

    void setMapType(int i) throws RemoteException;

    void setMyLocationEnabled(boolean z) throws RemoteException;

    void setOnCameraChangeListener(zze com_google_android_gms_maps_internal_zze) throws RemoteException;

    void setOnIndoorStateChangeListener(zzf com_google_android_gms_maps_internal_zzf) throws RemoteException;

    void setOnInfoWindowClickListener(zzg com_google_android_gms_maps_internal_zzg) throws RemoteException;

    void setOnMapClickListener(zzi com_google_android_gms_maps_internal_zzi) throws RemoteException;

    void setOnMapLoadedCallback(zzj com_google_android_gms_maps_internal_zzj) throws RemoteException;

    void setOnMapLongClickListener(zzk com_google_android_gms_maps_internal_zzk) throws RemoteException;

    void setOnMarkerClickListener(zzm com_google_android_gms_maps_internal_zzm) throws RemoteException;

    void setOnMarkerDragListener(zzn com_google_android_gms_maps_internal_zzn) throws RemoteException;

    void setOnMyLocationButtonClickListener(zzo com_google_android_gms_maps_internal_zzo) throws RemoteException;

    void setOnMyLocationChangeListener(zzp com_google_android_gms_maps_internal_zzp) throws RemoteException;

    void setOnPoiClickListener(zzq com_google_android_gms_maps_internal_zzq) throws RemoteException;

    void setPadding(int i, int i2, int i3, int i4) throws RemoteException;

    void setTrafficEnabled(boolean z) throws RemoteException;

    void snapshot(zzw com_google_android_gms_maps_internal_zzw, zzd com_google_android_gms_dynamic_zzd) throws RemoteException;

    void stopAnimation() throws RemoteException;

    boolean useViewLifecycleWhenInFragment() throws RemoteException;
}
