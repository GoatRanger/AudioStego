package com.here.android.mpa.mapping;

import android.graphics.PointF;
import android.view.View;
import com.here.android.mpa.cluster.ClusterLayer;
import com.here.android.mpa.common.GeoBoundingBox;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.IconCategory;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.customization.CustomizableScheme;
import com.nokia.maps.MapImpl;
import com.nokia.maps.MapsEngine;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.be;
import java.io.FileNotFoundException;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

public final class Map {
    @Online
    public static final float MOVE_PRESERVE_ORIENTATION = -1.0f;
    @Online
    public static final float MOVE_PRESERVE_TILT = -1.0f;
    @Online
    public static final double MOVE_PRESERVE_ZOOM_LEVEL = -1.0d;
    private MapImpl a = new MapImpl(MapsEngine.e());

    @Online
    @Deprecated
    public interface InfoBubbleAdapter {
        View getInfoBubble(MapMarker mapMarker);

        View getInfoBubbleContents(MapMarker mapMarker);
    }

    @Online
    public static void setCustomMapConfiguration(String str, String str2) throws FileNotFoundException {
        MapImpl.a(str, str2);
    }

    @Online
    public int getWidth() {
        return this.a.b();
    }

    @Online
    public int getHeight() {
        return this.a.c();
    }

    @Online
    public void setCenter(GeoCoordinate geoCoordinate, Animation animation, double d, float f, float f2) {
        this.a.a(geoCoordinate, animation, d, f, f2);
    }

    @Online
    public void setCenter(GeoCoordinate geoCoordinate, Animation animation) {
        this.a.a(geoCoordinate, animation);
    }

    @Online
    public void setCenter(PointF pointF, Animation animation, double d, float f, float f2) {
        this.a.a(pointF, animation, d, f, f2);
    }

    @Online
    public Map setTransformCenter(PointF pointF) {
        this.a.a(pointF);
        return this;
    }

    @Online
    public PointF getTransformCenter() {
        return this.a.d();
    }

    @Online
    public void zoomTo(GeoBoundingBox geoBoundingBox, Animation animation, float f) {
        this.a.a(geoBoundingBox, animation, f);
    }

    @Online
    public void zoomTo(GeoBoundingBox geoBoundingBox, Animation animation, float f, float f2) {
        this.a.a(geoBoundingBox, animation, f, f2);
    }

    @Online
    public void zoomTo(GeoBoundingBox geoBoundingBox, ViewRect viewRect, Animation animation, float f) {
        this.a.a(geoBoundingBox, viewRect, animation, f);
    }

    @Online
    public void zoomTo(GeoBoundingBox geoBoundingBox, int i, int i2, Animation animation, float f) {
        this.a.a(geoBoundingBox, i, i2, animation, f);
    }

    @Online
    public void pan(PointF pointF, PointF pointF2) {
        this.a.a(pointF, pointF2);
    }

    @Online
    public MapState getMapState() {
        return this.a.getMapState();
    }

    @Online
    public double getMaxZoomLevel() {
        return this.a.getMaxZoomLevel();
    }

    @Online
    public double getMinZoomLevel() {
        return this.a.getMinZoomLevel();
    }

    @Online
    public Map setZoomLevel(double d) {
        this.a.a(d);
        return this;
    }

    @Online
    public void setZoomLevel(double d, Animation animation) {
        this.a.a(d, animation);
    }

    @Online
    public void setZoomLevel(double d, PointF pointF, Animation animation) {
        this.a.a(d, pointF, animation);
    }

    @Online
    public double getZoomLevel() {
        return this.a.getZoomLevel();
    }

    @Online
    public double getScaleFromZoomLevel(double d) {
        return this.a.b(d);
    }

    @Online
    public float getOrientation() {
        return this.a.getOrientation();
    }

    @Online
    public Map setOrientation(float f) {
        this.a.a(f);
        return this;
    }

    @Online
    public void setOrientation(float f, Animation animation) {
        this.a.a(f, animation);
    }

    @Online
    public float getMaxTilt() {
        return this.a.getMaxTilt();
    }

    @Online
    public float getMinTilt() {
        return this.a.getMinTilt();
    }

    @Online
    public float getTilt() {
        return this.a.getTilt();
    }

    @Online
    public Map setTilt(float f) {
        this.a.b(f);
        return this;
    }

    @Online
    public void setTilt(float f, Animation animation) {
        this.a.b(f, animation);
    }

    @Online
    public List<String> getMapSchemes() {
        return this.a.g();
    }

    @Online
    public String getMapScheme() {
        return this.a.getMapScheme();
    }

    @Online
    public Map setMapScheme(String str) {
        this.a.a(str);
        return this;
    }

    @Online
    public Map setMapScheme(CustomizableScheme customizableScheme) {
        this.a.a(customizableScheme);
        return this;
    }

    @Online
    public GeoCoordinate getCenter() {
        return this.a.h();
    }

    @Online
    public String getCopyright() {
        return this.a.getCopyright();
    }

    @Online
    public GeoCoordinate pixelToGeo(PointF pointF) {
        return this.a.b(pointF);
    }

    @Online
    public GeoCoordinate pixelToGeo(PointF pointF, float f) {
        return this.a.a(pointF, f);
    }

    @Online
    public List<GeoCoordinate> pixelToGeo(List<PointF> list) {
        return this.a.a((List) list);
    }

    @Online
    public PixelResult projectToPixel(GeoCoordinate geoCoordinate) {
        return this.a.a(geoCoordinate);
    }

    @Online
    public List<PixelResult> projectToPixel(List<GeoCoordinate> list) {
        return this.a.b((List) list);
    }

    @Online
    public GeoBoundingBox getBoundingBox() {
        return this.a.i();
    }

    @Online
    public PositionIndicator getPositionIndicator() {
        return this.a.n();
    }

    @Online
    public void addClusterLayer(ClusterLayer clusterLayer) {
        this.a.a(clusterLayer);
    }

    @Online
    public void removeClusterLayer(ClusterLayer clusterLayer) {
        this.a.b(clusterLayer);
    }

    @HybridPlus
    public boolean addMapOverlay(MapOverlay mapOverlay) {
        return this.a.a(mapOverlay);
    }

    @HybridPlus
    public boolean removeMapOverlay(MapOverlay mapOverlay) {
        return this.a.b(mapOverlay);
    }

    @Online
    public boolean addMapObject(MapObject mapObject) {
        return this.a.a(mapObject);
    }

    @Online
    public boolean addMapObjects(List<MapObject> list) {
        return this.a.c((List) list);
    }

    @Online
    public boolean removeMapObject(MapObject mapObject) {
        return this.a.b(mapObject);
    }

    @Online
    public boolean removeMapObjects(List<MapObject> list) {
        return this.a.d((List) list);
    }

    @Online
    public List<ViewObject> getSelectedObjectsNearby(PointF pointF) {
        return this.a.c(pointF);
    }

    @Online
    public List<ViewObject> getSelectedObjects(PointF pointF) {
        return this.a.d(pointF);
    }

    @Online
    public List<ViewObject> getSelectedObjects(ViewRect viewRect) {
        return this.a.a(viewRect);
    }

    @Online
    public MapTransitLayer getMapTransitLayer() {
        return this.a.x();
    }

    @Online
    public Map setCartoMarkersVisible(boolean z) {
        this.a.b(z);
        return this;
    }

    @Online
    public boolean areCartoMarkersVisible() {
        return this.a.e();
    }

    @Online
    public void addTransformListener(OnTransformListener onTransformListener) {
        this.a.a(onTransformListener);
    }

    @Online
    public void removeTransformListener(OnTransformListener onTransformListener) {
        this.a.b(onTransformListener);
    }

    @Online
    public void addSchemeChangedListener(OnSchemeChangedListener onSchemeChangedListener) {
        this.a.a(onSchemeChangedListener);
    }

    @Online
    public void removeSchemeChangedListener(OnSchemeChangedListener onSchemeChangedListener) {
        this.a.b(onSchemeChangedListener);
    }

    @Online
    @Deprecated
    public void setInfoBubbleAdapter(InfoBubbleAdapter infoBubbleAdapter) {
        this.a.a(infoBubbleAdapter);
    }

    @Online
    public MapBuildingLayer getMapBuildingLayer() {
        return this.a.q();
    }

    @Online
    public boolean setExtrudedBuildingsVisible(boolean z) {
        return this.a.j(z);
    }

    @Online
    public boolean areExtrudedBuildingsVisible() {
        return this.a.isExtrudedBuildingsVisible();
    }

    @Online
    public boolean addRasterTileSource(MapRasterTileSource mapRasterTileSource) {
        return this.a.a(mapRasterTileSource);
    }

    @Online
    public boolean removeRasterTileSource(MapRasterTileSource mapRasterTileSource) {
        return this.a.b(mapRasterTileSource);
    }

    @HybridPlus
    public Map setTrafficInfoVisible(boolean z) {
        this.a.d(z);
        return this;
    }

    @HybridPlus
    public boolean isTrafficInfoVisible() {
        return this.a.l();
    }

    @Online
    public boolean areLandmarksVisible() {
        return this.a.f();
    }

    @Online
    public Map setLandmarksVisible(boolean z) {
        this.a.c(z);
        return this;
    }

    @HybridPlus
    public Map setStreetLevelCoverageVisible(boolean z) {
        this.a.g(z);
        return this;
    }

    @HybridPlus
    public boolean isStreetLevelCoverageVisible() {
        return this.a.p();
    }

    @Online
    public Map setFadingAnimations(boolean z) {
        this.a.i(z);
        return this;
    }

    @Online
    public boolean setUseSystemLanguage() {
        return this.a.j();
    }

    @Online
    public boolean setMapDisplayLanguage(Locale locale) {
        return this.a.a(locale);
    }

    @Online
    public boolean setMapSecondaryDisplayLanguage(Locale locale) {
        return this.a.b(locale);
    }

    @Online
    public String getMapDisplayLanguage() {
        return this.a.getMapDisplayLanguage();
    }

    @Online
    public String getMapSecondaryDisplayLanguage() {
        return this.a.getMapSecondaryDisplayLanguage();
    }

    @HybridPlus
    public MapTrafficLayer getMapTrafficLayer() {
        return this.a.y();
    }

    @Online
    public CustomizableScheme createCustomizableScheme(String str, String str2) {
        return this.a.b(str, str2);
    }

    @Online
    public boolean removeCustomizableScheme(String str) {
        return this.a.b(str);
    }

    @Online
    public CustomizableScheme getCustomizableScheme(String str) {
        return this.a.c(str);
    }

    @Online
    public List<String> getSupportedMapDisplayLanguages() {
        return this.a.k();
    }

    @Online
    public Map setProjectionMode(Projection projection) {
        this.a.a(projection);
        return this;
    }

    @Online
    public Projection getProjectionMode() {
        return this.a.o();
    }

    @Online
    public Map setCartoMarkersVisible(IconCategory iconCategory, boolean z) {
        this.a.a(iconCategory, z);
        return this;
    }

    @Online
    public boolean areCartoMarkersVisible(IconCategory iconCategory) {
        return this.a.a(iconCategory);
    }

    @HybridPlus
    public Map setFleetFeaturesVisible(EnumSet<FleetFeature> enumSet) {
        this.a.a(FleetFeature.a(enumSet));
        return this;
    }

    @HybridPlus
    public EnumSet<FleetFeature> getFleetFeaturesVisible() {
        return FleetFeature.a(this.a.getFleetFeaturesVisible());
    }

    @Online
    public Map setPedestrianFeaturesVisible(EnumSet<PedestrianFeature> enumSet) {
        this.a.b(PedestrianFeature.a(enumSet));
        return this;
    }

    @Online
    public EnumSet<PedestrianFeature> getPedestrianFeaturesVisible() {
        return PedestrianFeature.a(this.a.getPedestrianFeaturesVisible());
    }

    @Online
    public void executeSynchronized(Runnable runnable) {
        synchronized (this.a) {
            runnable.run();
        }
    }

    boolean a(MapObject mapObject, boolean z) {
        return this.a.a(mapObject, z);
    }

    @HybridPlus
    public void setSafetySpotsVisible(boolean z) {
        this.a.setSafetySpotsVisible(z);
    }

    @HybridPlus
    public boolean areSafetySpotsVisible() {
        return this.a.getSafetySpotsVisible();
    }

    public static int a(LayerCategory layerCategory) {
        return layerCategory.a;
    }

    @HybridPlus
    public Map setVisibleLayers(EnumSet<LayerCategory> enumSet, boolean z) {
        int[] iArr = new int[enumSet.size()];
        Iterator it = enumSet.iterator();
        int i = 0;
        while (it.hasNext()) {
            iArr[i] = ((LayerCategory) it.next()).a;
            i++;
        }
        this.a.a(iArr, z);
        return this;
    }

    @HybridPlus
    public EnumSet<LayerCategory> getVisibleLayers() {
        int[] layerCategory = this.a.getLayerCategory();
        EnumSet<LayerCategory> noneOf = EnumSet.noneOf(LayerCategory.class);
        for (int a : layerCategory) {
            noneOf.add(LayerCategory.a(a));
        }
        return noneOf;
    }

    @HybridPlus
    public static void enableMaximumFpsLimit(boolean z) {
        be.a(z);
    }

    @HybridPlus
    public static boolean isMaximumFpsLimited() {
        return be.a();
    }

    @HybridPlus
    public static void setMaximumFps(int i) {
        be.a(i);
    }

    @HybridPlus
    public static int getMaximumFps() {
        return be.b();
    }

    static {
        MapImpl.a(new 1());
    }
}
