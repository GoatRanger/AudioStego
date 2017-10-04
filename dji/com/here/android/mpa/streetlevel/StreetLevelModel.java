package com.here.android.mpa.streetlevel;

import android.graphics.PointF;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Image;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.PanoramaModelImpl;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.k;
import java.util.List;

@HybridPlus
public final class StreetLevelModel {
    public static final float MOVE_PRESERVE_HEADING = -1.0f;
    public static final float MOVE_PRESERVE_PITCH = -1.0f;
    public static final float MOVE_PRESERVE_ZOOM = -1.0f;
    private PanoramaModelImpl a;

    @HybridPlus
    public interface OnEventListener {

        @HybridPlus
        public static abstract class OnEventListenerAdapter implements OnEventListener {
            public void onOrientationStart(float f, float f2) {
            }

            public void onOrientationEnd(float f, float f2) {
            }

            public void onZoomStart(float f) {
            }

            public void onZoomEnd(float f) {
            }

            public void onPositionChanged(GeoCoordinate geoCoordinate) {
            }

            public void onMoveStart() {
            }

            public void onMoveWait() {
            }

            public void onMoveContinue() {
            }

            public void onMoveEnd(boolean z) {
            }

            public void onMoveEnd(GeoCoordinate geoCoordinate) {
            }

            public void onBuildingShow(StreetLevelBuilding streetLevelBuilding) {
            }

            public void onBuildingHide(StreetLevelBuilding streetLevelBuilding) {
            }

            public void onIconPlaced(StreetLevelSelectedObject streetLevelSelectedObject) {
            }

            public void onStreetLevelFullyLoaded() {
            }

            public void onStreetLevelInvalidated() {
            }

            public void onStreetLevelChanged() {
            }

            public void onStreetLevelPreviewAvailable() {
            }
        }

        void onBuildingHide(StreetLevelBuilding streetLevelBuilding);

        void onBuildingShow(StreetLevelBuilding streetLevelBuilding);

        void onIconPlaced(StreetLevelSelectedObject streetLevelSelectedObject);

        void onMoveContinue();

        void onMoveEnd(GeoCoordinate geoCoordinate);

        void onMoveEnd(boolean z);

        void onMoveStart();

        void onMoveWait();

        void onOrientationEnd(float f, float f2);

        void onOrientationStart(float f, float f2);

        void onPositionChanged(GeoCoordinate geoCoordinate);

        void onStreetLevelChanged();

        void onStreetLevelFullyLoaded();

        void onStreetLevelInvalidated();

        void onStreetLevelPreviewAvailable();

        void onZoomEnd(float f);

        void onZoomStart(float f);
    }

    @HybridPlus
    public interface OnRetrievalListener {
        void onGetStreetLevelCompleted(StreetLevel streetLevel);
    }

    public StreetLevelModel() {
        this.a = new PanoramaModelImpl(MapsEngine.e());
    }

    private StreetLevelModel(PanoramaModelImpl panoramaModelImpl) {
        this.a = panoramaModelImpl;
    }

    public void addStreetLevelModelListener(OnEventListener onEventListener) {
        this.a.a(onEventListener);
    }

    public void removeStreetLevelModelListener(OnEventListener onEventListener) {
        this.a.b(onEventListener);
    }

    public boolean isMoving() {
        return this.a.b();
    }

    public boolean isStreetLevelDataNeeded() {
        return this.a.k();
    }

    public boolean getStreetLevel(GeoCoordinate geoCoordinate, OnRetrievalListener onRetrievalListener) {
        return this.a.a(geoCoordinate, onRetrievalListener);
    }

    public StreetLevel getStreetLevel(GeoCoordinate geoCoordinate, int i) {
        return this.a.b(geoCoordinate, i);
    }

    public StreetLevel getStreetLevel(PointF pointF) {
        return this.a.b(pointF);
    }

    public StreetLevel getStreetLevel() {
        return this.a.j();
    }

    public void moveTo(StreetLevel streetLevel, boolean z, float f, float f2, float f3) {
        this.a.a(streetLevel, z, f, f2, f3);
    }

    public void moveTo(StreetLevel streetLevel, boolean z, GeoCoordinate geoCoordinate, float f) {
        this.a.a(streetLevel, z, geoCoordinate, f);
    }

    public void cancelMoveTo(boolean z) {
        this.a.cancelMoveTo(z);
    }

    public void moveCamera(GeoCoordinate geoCoordinate, float f, float f2, float f3) {
        this.a.a(geoCoordinate, f, f2, f3);
    }

    public List<Float> toCameraOrientation(PointF pointF) {
        return this.a.d(pointF);
    }

    public float getOverlayTransparency() {
        return this.a.getOverlayTransparency();
    }

    public void setOverlayTransparency(float f) {
        this.a.setOverlayTransparency(f);
    }

    public boolean isStreetGeometryVisible() {
        return this.a.isStreetGeometryVisible();
    }

    public void setStreetGeometryVisible(boolean z) {
        this.a.a(z);
    }

    public boolean isNavigationArrowVisible() {
        return this.a.e();
    }

    public void setNavigationArrowVisible(boolean z) {
        this.a.b(z);
    }

    public void setNavigationArrow(Image image) {
        this.a.a(image);
    }

    public void pan(PointF pointF, PointF pointF2) {
        this.a.a(pointF, pointF2);
    }

    public void rotate(PointF pointF, PointF pointF2) {
        this.a.b(pointF, pointF2);
    }

    public float getZoom() {
        return this.a.getZoom();
    }

    public void setZoom(float f) {
        this.a.setZoom(f);
    }

    public float getMinZoom() {
        return this.a.getMinZoom();
    }

    public float getMaxZoom() {
        return this.a.getMaxZoom();
    }

    public float getHeading() {
        return this.a.getHeading();
    }

    public void setHeading(float f) {
        this.a.setHeading(f);
    }

    public float getPitch() {
        return this.a.getPitch();
    }

    public void setPitch(float f) {
        this.a.setPitch(f);
    }

    public float getMinPitch() {
        return this.a.getMinPitch();
    }

    public float getMaxPitch() {
        return this.a.getMaxPitch();
    }

    public float getMinHeading() {
        return this.a.getMinHeading();
    }

    public float getMaxHeading() {
        return this.a.getMaxHeading();
    }

    public List<StreetLevelSelectedObject> getSelectedObjects(PointF pointF) {
        return this.a.a(pointF);
    }

    public boolean addStreetLevelObject(StreetLevelObject streetLevelObject) {
        return this.a.a(streetLevelObject);
    }

    public boolean removeStreetLevelObject(StreetLevelObject streetLevelObject) {
        return this.a.b(streetLevelObject);
    }

    public StreetLevelModelState getState() {
        return this.a.getState();
    }

    public GeoCoordinate getPosition() {
        return this.a.l();
    }

    public PointF geoToPixel(GeoCoordinate geoCoordinate) {
        return this.a.a(geoCoordinate);
    }

    public GeoCoordinate pixelToGeo(PointF pointF) {
        return this.a.c(pointF);
    }

    public void setCompassMapVisible(boolean z) {
        this.a.c(z);
    }

    public boolean isCompassMapVisible() {
        return this.a.f();
    }

    public int getWidth() {
        return this.a.c();
    }

    public int getHeight() {
        return this.a.d();
    }

    public void executeSynchronized(Runnable runnable) {
        synchronized (this.a) {
            runnable.run();
        }
    }

    static {
        PanoramaModelImpl.a(new k<StreetLevelModel, PanoramaModelImpl>() {
            public PanoramaModelImpl a(StreetLevelModel streetLevelModel) {
                return streetLevelModel.a;
            }
        }, new am<StreetLevelModel, PanoramaModelImpl>() {
            public StreetLevelModel a(PanoramaModelImpl panoramaModelImpl) {
                if (panoramaModelImpl != null) {
                    return new StreetLevelModel(panoramaModelImpl);
                }
                return null;
            }
        });
    }
}
