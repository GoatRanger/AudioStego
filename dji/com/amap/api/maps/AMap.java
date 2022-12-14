package com.amap.api.maps;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Location;
import android.os.RemoteException;
import android.view.MotionEvent;
import android.view.View;
import com.amap.api.mapcore.indoor.IndoorBuilding;
import com.amap.api.mapcore.util.cu;
import com.amap.api.maps.model.Arc;
import com.amap.api.maps.model.ArcOptions;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.Circle;
import com.amap.api.maps.model.CircleOptions;
import com.amap.api.maps.model.GroundOverlay;
import com.amap.api.maps.model.GroundOverlayOptions;
import com.amap.api.maps.model.IndoorBuildingInfo;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.MyLocationStyle;
import com.amap.api.maps.model.MyTrafficStyle;
import com.amap.api.maps.model.NavigateArrow;
import com.amap.api.maps.model.NavigateArrowOptions;
import com.amap.api.maps.model.Poi;
import com.amap.api.maps.model.Polygon;
import com.amap.api.maps.model.PolygonOptions;
import com.amap.api.maps.model.Polyline;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.maps.model.RuntimeRemoteException;
import com.amap.api.maps.model.Text;
import com.amap.api.maps.model.TextOptions;
import com.amap.api.maps.model.TileOverlay;
import com.amap.api.maps.model.TileOverlayOptions;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import java.util.ArrayList;
import java.util.List;

public final class AMap {
    public static final int LOCATION_TYPE_LOCATE = 1;
    public static final int LOCATION_TYPE_MAP_FOLLOW = 2;
    public static final int LOCATION_TYPE_MAP_ROTATE = 3;
    public static final int MAP_TYPE_NAVI = 4;
    public static final int MAP_TYPE_NIGHT = 3;
    public static final int MAP_TYPE_NORMAL = 1;
    public static final int MAP_TYPE_SATELLITE = 2;
    private final IAMapDelegate a;
    private UiSettings b;
    private Projection c;
    private MyTrafficStyle d;

    public interface CancelableCallback {
        void onCancel();

        void onFinish();
    }

    public interface InfoWindowAdapter {
        View getInfoContents(Marker marker);

        View getInfoWindow(Marker marker);
    }

    public interface OnCacheRemoveListener {
        void onRemoveCacheFinish(boolean z);
    }

    public interface OnCameraChangeListener {
        void onCameraChange(CameraPosition cameraPosition);

        void onCameraChangeFinish(CameraPosition cameraPosition);
    }

    public interface OnIndoorBuildingActiveListener {
        void OnIndoorBuilding(IndoorBuildingInfo indoorBuildingInfo);
    }

    public interface OnInfoWindowClickListener {
        void onInfoWindowClick(Marker marker);
    }

    public interface OnMapClickListener {
        void onMapClick(LatLng latLng);
    }

    public interface OnMapLoadedListener {
        void onMapLoaded();
    }

    public interface OnMapLongClickListener {
        void onMapLongClick(LatLng latLng);
    }

    public interface OnMapScreenShotListener {
        void onMapScreenShot(Bitmap bitmap);

        void onMapScreenShot(Bitmap bitmap, int i);
    }

    public interface OnMapTouchListener {
        void onTouch(MotionEvent motionEvent);
    }

    public interface OnMarkerClickListener {
        boolean onMarkerClick(Marker marker);
    }

    public interface OnMarkerDragListener {
        void onMarkerDrag(Marker marker);

        void onMarkerDragEnd(Marker marker);

        void onMarkerDragStart(Marker marker);
    }

    public interface OnMyLocationChangeListener {
        void onMyLocationChange(Location location);
    }

    public interface OnPOIClickListener {
        void onPOIClick(Poi poi);
    }

    public interface OnPolylineClickListener {
        void onPolylineClick(Polyline polyline);
    }

    public interface onMapPrintScreenListener {
        void onMapPrint(Drawable drawable);
    }

    protected AMap(IAMapDelegate iAMapDelegate) {
        this.a = iAMapDelegate;
    }

    public final CameraPosition getCameraPosition() {
        try {
            return this.a.getCameraPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final float getMaxZoomLevel() {
        return this.a.getMaxZoomLevel();
    }

    public final float getMinZoomLevel() {
        return this.a.getMinZoomLevel();
    }

    public final void moveCamera(CameraUpdate cameraUpdate) {
        try {
            this.a.moveCamera(cameraUpdate.a());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate) {
        try {
            this.a.animateCamera(cameraUpdate.a());
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, CancelableCallback cancelableCallback) {
        try {
            this.a.animateCameraWithCallback(cameraUpdate.a(), cancelableCallback);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void animateCamera(CameraUpdate cameraUpdate, long j, CancelableCallback cancelableCallback) {
        try {
            cu.b(j > 0, "durationMs must be positive");
            this.a.animateCameraWithDurationAndCallback(cameraUpdate.a(), j, cancelableCallback);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void stopAnimation() {
        try {
            this.a.stopAnimation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final NavigateArrow addNavigateArrow(NavigateArrowOptions navigateArrowOptions) {
        try {
            return new NavigateArrow(this.a.addNavigateArrow(navigateArrowOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polyline addPolyline(PolylineOptions polylineOptions) {
        try {
            return new Polyline(this.a.addPolyline(polylineOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Circle addCircle(CircleOptions circleOptions) {
        try {
            return new Circle(this.a.addCircle(circleOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Arc addArc(ArcOptions arcOptions) {
        try {
            return new Arc(this.a.addArc(arcOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Polygon addPolygon(PolygonOptions polygonOptions) {
        try {
            return new Polygon(this.a.addPolygon(polygonOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final GroundOverlay addGroundOverlay(GroundOverlayOptions groundOverlayOptions) {
        try {
            return new GroundOverlay(this.a.addGroundOverlay(groundOverlayOptions));
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Marker addMarker(MarkerOptions markerOptions) {
        try {
            return this.a.addMarker(markerOptions);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Text addText(TextOptions textOptions) {
        try {
            return this.a.addText(textOptions);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final ArrayList<Marker> addMarkers(ArrayList<MarkerOptions> arrayList, boolean z) {
        try {
            return this.a.addMarkers(arrayList, z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final List<Marker> getMapScreenMarkers() {
        try {
            return this.a.getMapScreenMarkers();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final TileOverlay addTileOverlay(TileOverlayOptions tileOverlayOptions) {
        try {
            return this.a.addTileOverlay(tileOverlayOptions);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void clear() {
        try {
            this.a.clear();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void clear(boolean z) {
        try {
            this.a.clear(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getMapType() {
        try {
            return this.a.getMapType();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMapType(int i) {
        try {
            this.a.setMapType(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final boolean isTrafficEnabled() {
        try {
            return this.a.isTrafficEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTrafficEnabled(boolean z) {
        try {
            this.a.setTrafficEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void showMapText(boolean z) {
        try {
            this.a.setMapTextEnable(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void showIndoorMap(boolean z) {
        try {
            this.a.setIndoorEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void showBuildings(boolean z) {
        try {
            this.a.set3DBuildingEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setMyTrafficStyle(MyTrafficStyle myTrafficStyle) {
        try {
            this.d = myTrafficStyle;
            this.a.setMyTrafficStyle(myTrafficStyle);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public MyTrafficStyle getMyTrafficStyle() {
        return this.d;
    }

    public final boolean isMyLocationEnabled() {
        try {
            return this.a.isMyLocationEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationEnabled(boolean z) {
        try {
            this.a.setMyLocationEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Location getMyLocation() {
        try {
            return this.a.getMyLocation();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setLocationSource(LocationSource locationSource) {
        try {
            this.a.setLocationSource(locationSource);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationStyle(MyLocationStyle myLocationStyle) {
        try {
            this.a.setMyLocationStyle(myLocationStyle);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationType(int i) {
        try {
            this.a.setMyLocationType(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMyLocationRotateAngle(float f) {
        try {
            this.a.setMyLocationRotateAngle(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final UiSettings getUiSettings() {
        try {
            if (this.b == null) {
                this.b = new UiSettings(this.a.getUiSettings());
            }
            return this.b;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final Projection getProjection() {
        try {
            if (this.c == null) {
                this.c = new Projection(this.a.getProjection());
            }
            return this.c;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnCameraChangeListener(OnCameraChangeListener onCameraChangeListener) {
        try {
            this.a.setOnCameraChangeListener(onCameraChangeListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapClickListener(OnMapClickListener onMapClickListener) {
        try {
            this.a.setOnMapClickListener(onMapClickListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapTouchListener(OnMapTouchListener onMapTouchListener) {
        try {
            this.a.setOnMapTouchListener(onMapTouchListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnPOIClickListener(OnPOIClickListener onPOIClickListener) {
        try {
            this.a.setOnPOIClickListener(onPOIClickListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMyLocationChangeListener(OnMyLocationChangeListener onMyLocationChangeListener) {
        try {
            this.a.setOnMyLocationChangeListener(onMyLocationChangeListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapLongClickListener(OnMapLongClickListener onMapLongClickListener) {
        try {
            this.a.setOnMapLongClickListener(onMapLongClickListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMarkerClickListener(OnMarkerClickListener onMarkerClickListener) {
        try {
            this.a.setOnMarkerClickListener(onMarkerClickListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnPolylineClickListener(OnPolylineClickListener onPolylineClickListener) {
        try {
            this.a.setOnPolylineClickListener(onPolylineClickListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMarkerDragListener(OnMarkerDragListener onMarkerDragListener) {
        try {
            this.a.setOnMarkerDragListener(onMarkerDragListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnInfoWindowClickListener(OnInfoWindowClickListener onInfoWindowClickListener) {
        try {
            this.a.setOnInfoWindowClickListener(onInfoWindowClickListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setInfoWindowAdapter(InfoWindowAdapter infoWindowAdapter) {
        try {
            this.a.setInfoWindowAdapter(infoWindowAdapter);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnMapLoadedListener(OnMapLoadedListener onMapLoadedListener) {
        try {
            this.a.setOnMaploadedListener(onMapLoadedListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setOnIndoorBuildingActiveListener(OnIndoorBuildingActiveListener onIndoorBuildingActiveListener) {
        try {
            this.a.setOnIndoorBuildingActiveListener(onIndoorBuildingActiveListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void getMapPrintScreen(onMapPrintScreenListener com_amap_api_maps_AMap_onMapPrintScreenListener) {
        this.a.getMapPrintScreen(com_amap_api_maps_AMap_onMapPrintScreenListener);
    }

    public void getMapScreenShot(OnMapScreenShotListener onMapScreenShotListener) {
        this.a.getMapScreenShot(onMapScreenShotListener);
    }

    public float getScalePerPixel() {
        try {
            return this.a.getScalePerPixel();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void runOnDrawFrame() {
        this.a.setRunLowFrame(false);
    }

    public void removecache() {
        try {
            this.a.removecache();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void removecache(OnCacheRemoveListener onCacheRemoveListener) {
        try {
            this.a.removecache(onCacheRemoveListener);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setCustomRenderer(CustomRenderer customRenderer) {
        try {
            this.a.setCustomRenderer(customRenderer);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPointToCenter(int i, int i2) {
        try {
            this.a.setCenterToPixel(i, i2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setMapTextZIndex(int i) {
        try {
            this.a.setMapTextZIndex(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final void setLoadOfflineData(boolean z) {
        try {
            this.a.setLoadOfflineData(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public final int getMapTextZIndex() {
        try {
            return this.a.getMapTextZIndex();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    @Deprecated
    public static String getVersion() {
        return "3.3.2";
    }

    public void reloadMap() {
        this.a.reloadMap();
    }

    public void setRenderFps(int i) {
        this.a.setRenderFps(i);
    }

    public void setIndoorBuildingInfo(IndoorBuildingInfo indoorBuildingInfo) {
        try {
            this.a.setIndoorBuildingInfo((IndoorBuilding) indoorBuildingInfo);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
