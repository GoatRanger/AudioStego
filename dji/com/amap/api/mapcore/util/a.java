package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Point;
import android.os.Message;
import android.os.RemoteException;
import com.amap.api.mapcore.indoor.IndoorBuilding;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.BaseMapCallImplement;
import com.autonavi.amap.mapcore.Convert;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapCore;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.MapSourceGridData;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate.Type;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

class a extends BaseMapCallImplement {
    IPoint a = new IPoint();
    float b;
    float c;
    float d;
    IPoint e = new IPoint();
    private c f;
    private float g = -1.0f;
    private int h;
    private int i;

    public String getMapSvrAddress() {
        return "http://mps.amap.com";
    }

    public a(c cVar) {
        this.f = cVar;
    }

    public void OnMapSurfaceCreate(GL10 gl10, MapCore mapCore) {
        super.OnMapSurfaceCreate(mapCore);
    }

    public void OnMapSurfaceRenderer(GL10 gl10, MapCore mapCore, int i) {
        super.OnMapSurfaceRenderer(gl10, mapCore, i);
        if (i == 3) {
            this.f.h.a(gl10, true, this.f.getMapTextZIndex());
            if (this.f.m != null) {
                this.f.m.onDrawFrame(gl10);
            }
        }
    }

    public void OnMapSufaceChanged(GL10 gl10, MapCore mapCore, int i, int i2) {
    }

    public void OnMapProcessEvent(MapCore mapCore) {
        float f = 0.0f;
        if (this.f != null && this.f.isNeedRunDestroy()) {
            this.f.runDestroy();
        }
        if (this.f != null) {
            float zoomLevel = this.f.getZoomLevel();
            a(mapCore);
            while (true) {
                ac a = this.f.e.a();
                if (a == null) {
                    break;
                } else if (a.a == 2) {
                    if (a.a()) {
                        mapCore.setParameter(2010, 4, 0, 0, 0);
                    } else {
                        mapCore.setParameter(2010, 0, 0, 0, 0);
                    }
                }
            }
            mapCore.setMapstate(this.f.getMapProjection());
            if (!(this.b < this.f.getMinZoomLevel() || this.g == zoomLevel || this.f.l == null)) {
                this.f.l.obtainMessage(21).sendToTarget();
            }
            f = zoomLevel;
        }
        this.g = f;
    }

    void a(MapCore mapCore) {
        Object obj = null;
        MapProjection mapProjection = this.f.getMapProjection();
        float mapZoomer = mapProjection.getMapZoomer();
        float cameraHeaderAngle = mapProjection.getCameraHeaderAngle();
        float mapAngle = mapProjection.getMapAngle();
        mapProjection.getGeoCenter(this.e);
        int i = 0;
        while (this.f.isDrawOnce()) {
            CameraUpdateFactoryDelegate c = this.f.e.c();
            if (c == null) {
                break;
            }
            try {
                a(c);
                i |= c.isChangeFinished;
            } catch (Throwable e) {
                ee.a(e, "AMapCallback", "runMessage");
                e.printStackTrace();
            }
        }
        this.b = mapProjection.getMapZoomer();
        this.c = mapProjection.getCameraHeaderAngle();
        this.d = mapProjection.getMapAngle();
        mapProjection.getGeoCenter(this.a);
        if (!(mapZoomer == this.b && this.c == cameraHeaderAngle && this.d == mapAngle && this.a.x == this.e.x && this.a.y == this.e.y)) {
            obj = 1;
        }
        if (obj != null) {
            try {
                this.f.setRunLowFrame(false);
                if (this.f.getOnCameraChangeListener() != null) {
                    DPoint dPoint = new DPoint();
                    MapProjection.geo2LonLat(this.a.x, this.a.y, dPoint);
                    this.f.a(new CameraPosition(new LatLng(dPoint.y, dPoint.x, false), this.b, this.c, this.d));
                }
                this.f.e();
            } catch (Throwable e2) {
                ee.a(e2, "AMapCallback", "runMessage cameraChange");
                e2.printStackTrace();
                return;
            }
        }
        this.f.setRunLowFrame(true);
        if (i != 0) {
            if (i != 0) {
                this.f.a(true);
            } else {
                this.f.a(false);
            }
            Message message = new Message();
            message.what = 17;
            this.f.l.sendMessage(message);
        }
        if (!(this.c == cameraHeaderAngle && this.d == mapAngle) && this.f.getUiSettings().isCompassEnabled()) {
            this.f.a();
        }
        if (this.f.getUiSettings().isScaleControlsEnabled()) {
            this.f.b();
        }
    }

    private void b(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        MapCore mapCore = this.f.getMapCore();
        MapProjection mapProjection = this.f.getMapProjection();
        LatLngBounds latLngBounds = cameraUpdateFactoryDelegate.bounds;
        int i = cameraUpdateFactoryDelegate.width;
        int i2 = cameraUpdateFactoryDelegate.height;
        int i3 = cameraUpdateFactoryDelegate.padding;
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        MapProjection.lonlat2Geo(latLngBounds.northeast.longitude, latLngBounds.northeast.latitude, iPoint);
        MapProjection.lonlat2Geo(latLngBounds.southwest.longitude, latLngBounds.southwest.latitude, iPoint2);
        int i4 = iPoint2.y - iPoint.y;
        int i5 = i - (i3 * 2);
        i = i2 - (i3 * 2);
        if (iPoint.x - iPoint2.x >= 0 || i4 >= 0) {
            if (i5 <= 0) {
                i5 = 1;
            }
            if (i <= 0) {
                i = 1;
            }
            float a = a(latLngBounds.northeast, latLngBounds.southwest, i5, i);
            i5 = (iPoint.x + iPoint2.x) / 2;
            int i6 = (iPoint.y + iPoint2.y) / 2;
            mapProjection.setMapZoomer(a);
            mapProjection.setGeoCenter(i5, i6);
            mapProjection.setCameraHeaderAngle(0.0f);
            mapProjection.setMapAngle(0.0f);
            mapCore.setMapstate(mapProjection);
        }
    }

    private float a(LatLng latLng, LatLng latLng2, int i, int i2) {
        float a;
        MapProjection mapProjection = this.f.getMapProjection();
        mapProjection.setMapAngle(0.0f);
        mapProjection.setCameraHeaderAngle(0.0f);
        mapProjection.recalculate();
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        this.f.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint);
        this.f.getLatLng2Pixel(latLng2.latitude, latLng2.longitude, iPoint2);
        double d = (double) (iPoint.x - iPoint2.x);
        double d2 = (double) (iPoint2.y - iPoint.y);
        if (d <= 0.0d) {
            d = 1.0d;
        }
        if (d2 <= 0.0d) {
            d2 = 1.0d;
        }
        d = Math.log(((double) i) / d) / Math.log(2.0d);
        double min = Math.min(d, Math.log(((double) i2) / d2) / Math.log(2.0d));
        Object obj = Math.abs(min - d) < 1.0E-7d ? 1 : null;
        float a2 = dj.a((float) (((double) mapProjection.getMapZoomer()) + Math.floor(min)));
        while (true) {
            a = dj.a((float) (((double) a2) + 0.1d));
            mapProjection.setMapZoomer(a);
            mapProjection.recalculate();
            this.f.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint);
            this.f.getLatLng2Pixel(latLng2.latitude, latLng2.longitude, iPoint2);
            d = (double) (iPoint.x - iPoint2.x);
            min = (double) (iPoint2.y - iPoint.y);
            if (obj != null) {
                if (d >= ((double) i)) {
                    break;
                }
                if (a < r.f) {
                    return a;
                }
                a2 = a;
            } else {
                if (min >= ((double) i2)) {
                    break;
                }
                if (a < r.f) {
                    return a;
                }
                a2 = a;
            }
        }
        return (float) (((double) a) - 0.1d);
    }

    void a(CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) throws RemoteException {
        MapCore mapCore = this.f.getMapCore();
        MapProjection mapProjection = this.f.getMapProjection();
        mapProjection.recalculate();
        cameraUpdateFactoryDelegate.zoom = this.f.checkZoomLevel(cameraUpdateFactoryDelegate.zoom);
        float checkZoomLevel;
        switch (b.a[cameraUpdateFactoryDelegate.nowType.ordinal()]) {
            case 1:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, cameraUpdateFactoryDelegate.geoPoint);
                } else {
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.x, cameraUpdateFactoryDelegate.geoPoint.y);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 2:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    d(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 3:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.x, cameraUpdateFactoryDelegate.geoPoint.y);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 4:
                cameraUpdateFactoryDelegate.tilt = dj.a(cameraUpdateFactoryDelegate.tilt, mapProjection.getMapZoomer());
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    c(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setCameraHeaderAngle(cameraUpdateFactoryDelegate.tilt);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 5:
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    b(mapProjection, cameraUpdateFactoryDelegate);
                } else {
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.x, cameraUpdateFactoryDelegate.geoPoint.y);
                    mapProjection.setMapZoomer(cameraUpdateFactoryDelegate.zoom);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 6:
                LatLng latLng = cameraUpdateFactoryDelegate.cameraPosition.target;
                IPoint iPoint = new IPoint();
                MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
                float a = dj.a(cameraUpdateFactoryDelegate.cameraPosition.zoom);
                float f = cameraUpdateFactoryDelegate.cameraPosition.bearing;
                float a2 = dj.a(cameraUpdateFactoryDelegate.cameraPosition.tilt, a);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, iPoint, a, f, a2);
                } else {
                    mapProjection.setGeoCenter(iPoint.x, iPoint.y);
                    mapProjection.setMapZoomer(a);
                    mapProjection.setMapAngle(f);
                    mapProjection.setCameraHeaderAngle(a2);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 7:
                checkZoomLevel = this.f.checkZoomLevel(mapProjection.getMapZoomer() + 1.0f);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 8:
                checkZoomLevel = this.f.checkZoomLevel(mapProjection.getMapZoomer() - 1.0f);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapProjection.setMapZoomer(checkZoomLevel);
                mapCore.setMapstate(mapProjection);
                return;
            case 9:
                checkZoomLevel = cameraUpdateFactoryDelegate.zoom;
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 10:
                checkZoomLevel = this.f.checkZoomLevel(mapProjection.getMapZoomer() + cameraUpdateFactoryDelegate.amount);
                Point point = cameraUpdateFactoryDelegate.focus;
                if (point != null) {
                    a(mapProjection, checkZoomLevel, point.x, point.y);
                } else if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, checkZoomLevel);
                } else {
                    mapProjection.setMapZoomer(checkZoomLevel);
                }
                mapCore.setMapstate(mapProjection);
                return;
            case 11:
                checkZoomLevel = cameraUpdateFactoryDelegate.xPixel;
                checkZoomLevel += ((float) this.f.c()) / 2.0f;
                float d = cameraUpdateFactoryDelegate.yPixel + (((float) this.f.d()) / 2.0f);
                IPoint iPoint2 = new IPoint();
                this.f.getPixel2Geo((int) checkZoomLevel, (int) d, iPoint2);
                mapProjection.setGeoCenter(iPoint2.x, iPoint2.y);
                mapCore.setMapstate(mapProjection);
                return;
            case 12:
                cameraUpdateFactoryDelegate.nowType = Type.newLatLngBoundsWithSize;
                cameraUpdateFactoryDelegate.width = this.f.c();
                cameraUpdateFactoryDelegate.height = this.f.d();
                b(cameraUpdateFactoryDelegate);
                return;
            case 13:
                b(cameraUpdateFactoryDelegate);
                return;
            case 14:
                cameraUpdateFactoryDelegate.tilt = dj.a(cameraUpdateFactoryDelegate.tilt, cameraUpdateFactoryDelegate.zoom);
                if (cameraUpdateFactoryDelegate.isUseAnchor) {
                    a(mapProjection, cameraUpdateFactoryDelegate.geoPoint, cameraUpdateFactoryDelegate.zoom, cameraUpdateFactoryDelegate.bearing, cameraUpdateFactoryDelegate.tilt);
                } else {
                    mapProjection.setGeoCenter(cameraUpdateFactoryDelegate.geoPoint.x, cameraUpdateFactoryDelegate.geoPoint.y);
                    mapProjection.setMapZoomer(cameraUpdateFactoryDelegate.zoom);
                    mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
                    mapProjection.setCameraHeaderAngle(cameraUpdateFactoryDelegate.tilt);
                }
                mapCore.setMapstate(mapProjection);
                return;
            default:
                mapCore.setMapstate(mapProjection);
                return;
        }
    }

    private void a(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
        a(mapProjection, cameraUpdateFactoryDelegate.geoPoint);
    }

    private void a(MapProjection mapProjection, float f) {
        a(mapProjection, f, this.h, this.i);
    }

    private void a(MapProjection mapProjection, float f, int i, int i2) {
        IPoint a = a(mapProjection, i, i2);
        mapProjection.setMapZoomer(f);
        a(mapProjection, a, i, i2);
    }

    private void a(MapProjection mapProjection, IPoint iPoint, float f, float f2, float f3) {
        mapProjection.setMapZoomer(f);
        mapProjection.setMapAngle(f2);
        mapProjection.setCameraHeaderAngle(f3);
        a(mapProjection, iPoint);
    }

    private void b(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        mapProjection.setMapZoomer(cameraUpdateFactoryDelegate.zoom);
        a(mapProjection, cameraUpdateFactoryDelegate.geoPoint);
    }

    private void c(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        IPoint a = a(mapProjection);
        mapProjection.setCameraHeaderAngle(cameraUpdateFactoryDelegate.tilt);
        a(mapProjection, a);
    }

    private void d(MapProjection mapProjection, CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate) {
        IPoint a = a(mapProjection);
        mapProjection.setMapAngle(cameraUpdateFactoryDelegate.bearing);
        a(mapProjection, a);
    }

    private void a(MapProjection mapProjection, IPoint iPoint) {
        a(mapProjection, iPoint, this.h, this.i);
    }

    private void a(MapProjection mapProjection, IPoint iPoint, int i, int i2) {
        mapProjection.recalculate();
        IPoint a = a(mapProjection, i, i2);
        IPoint iPoint2 = new IPoint();
        mapProjection.getGeoCenter(iPoint2);
        mapProjection.setGeoCenter((iPoint2.x + iPoint.x) - a.x, (iPoint2.y + iPoint.y) - a.y);
    }

    private IPoint a(MapProjection mapProjection) {
        return a(mapProjection, this.h, this.i);
    }

    private IPoint a(MapProjection mapProjection, int i, int i2) {
        FPoint fPoint = new FPoint();
        mapProjection.win2Map(i, i2, fPoint);
        IPoint iPoint = new IPoint();
        mapProjection.map2Geo(fPoint.x, fPoint.y, iPoint);
        return iPoint;
    }

    public void OnMapDestory(GL10 gl10, MapCore mapCore) {
        super.OnMapDestory(mapCore);
    }

    public void OnMapReferencechanged(MapCore mapCore, String str, String str2) {
        try {
            if (this.f.getUiSettings().isCompassEnabled()) {
                this.f.a();
            }
            if (this.f.getUiSettings().isScaleControlsEnabled()) {
                this.f.b();
            }
            this.f.a(true);
        } catch (Throwable e) {
            ee.a(e, "AMapCallback", "OnMapReferencechanged");
            e.printStackTrace();
        }
        this.f.f();
    }

    public Context getContext() {
        return this.f.g();
    }

    public boolean isMapEngineValid() {
        if (this.f.getMapCore() != null) {
            return this.f.getMapCore().isMapEngineValid();
        }
        return false;
    }

    public void OnMapLoaderError(int i) {
    }

    public void a(int i, int i2) {
        this.h = i;
        this.i = i2;
    }

    public void requestRender() {
        this.f.setRunLowFrame(false);
    }

    public void onIndoorBuildingActivity(MapCore mapCore, byte[] bArr) {
        IndoorBuilding indoorBuilding = null;
        if (bArr != null) {
            try {
                IndoorBuilding indoorBuilding2 = new IndoorBuilding();
                byte b = bArr[0];
                indoorBuilding2.name_cn = new String(bArr, 1, b);
                int i = b + 1;
                int i2 = i + 1;
                b = bArr[i];
                indoorBuilding2.name_en = new String(bArr, i2, b);
                i = b + i2;
                i2 = i + 1;
                b = bArr[i];
                indoorBuilding2.activeFloorName = new String(bArr, i2, b);
                i = b + i2;
                indoorBuilding2.activeFloorIndex = Convert.getInt(bArr, i);
                i += 4;
                i2 = i + 1;
                b = bArr[i];
                indoorBuilding2.poiid = new String(bArr, i2, b);
                i = b + i2;
                indoorBuilding2.numberofFloor = Convert.getInt(bArr, i);
                i += 4;
                indoorBuilding2.floor_indexs = new int[indoorBuilding2.numberofFloor];
                indoorBuilding2.floor_names = new String[indoorBuilding2.numberofFloor];
                indoorBuilding2.floor_nonas = new String[indoorBuilding2.numberofFloor];
                for (int i3 = 0; i3 < indoorBuilding2.numberofFloor; i3++) {
                    indoorBuilding2.floor_indexs[i3] = Convert.getInt(bArr, i);
                    i2 = i + 4;
                    i = i2 + 1;
                    byte b2 = bArr[i2];
                    if (b2 > (byte) 0) {
                        indoorBuilding2.floor_names[i3] = new String(bArr, i, b2);
                        i2 = i + b2;
                    } else {
                        i2 = i;
                    }
                    i = i2 + 1;
                    b2 = bArr[i2];
                    if (b2 > (byte) 0) {
                        indoorBuilding2.floor_nonas[i3] = new String(bArr, i, b2);
                        i += b2;
                    }
                }
                indoorBuilding2.numberofParkFloor = Convert.getInt(bArr, i);
                i += 4;
                if (indoorBuilding2.numberofParkFloor > 0) {
                    indoorBuilding2.park_floor_indexs = new int[indoorBuilding2.numberofParkFloor];
                    int i4 = i;
                    for (i = 0; i < indoorBuilding2.numberofParkFloor; i++) {
                        indoorBuilding2.park_floor_indexs[i] = Convert.getInt(bArr, i4);
                        i4 += 4;
                    }
                }
                indoorBuilding = indoorBuilding2;
            } catch (Throwable th) {
                th.printStackTrace();
                ee.a(th, "AMapCallback", "onIndoorBuildingActivity");
                return;
            }
        }
        this.f.a(indoorBuilding);
    }

    public void onIndoorDataRequired(MapCore mapCore, int i, String[] strArr, int[] iArr, int[] iArr2) {
        if (strArr != null && strArr.length != 0) {
            ArrayList reqGridList = getReqGridList(i);
            if (reqGridList != null) {
                reqGridList.clear();
                for (int i2 = 0; i2 < strArr.length; i2++) {
                    reqGridList.add(new MapSourceGridData(strArr[i2], i, iArr[i2], iArr2[i2]));
                }
                if (i != 5) {
                    proccessRequiredData(mapCore, reqGridList, i);
                }
            }
        }
    }
}
