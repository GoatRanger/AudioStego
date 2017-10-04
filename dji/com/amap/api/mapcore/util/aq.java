package com.amap.api.mapcore.util;

import android.graphics.Point;
import android.graphics.PointF;
import android.os.RemoteException;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.TileProjection;
import com.amap.api.maps.model.VisibleRegion;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IProjectionDelegate;

class aq implements IProjectionDelegate {
    private IAMapDelegate a;

    public aq(IAMapDelegate iAMapDelegate) {
        this.a = iAMapDelegate;
    }

    public LatLng fromScreenLocation(Point point) throws RemoteException {
        if (point == null) {
            return null;
        }
        DPoint dPoint = new DPoint();
        this.a.getPixel2LatLng(point.x, point.y, dPoint);
        return new LatLng(dPoint.y, dPoint.x);
    }

    public Point toScreenLocation(LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return null;
        }
        IPoint iPoint = new IPoint();
        this.a.getLatLng2Pixel(latLng.latitude, latLng.longitude, iPoint);
        return new Point(iPoint.x, iPoint.y);
    }

    public VisibleRegion getVisibleRegion() throws RemoteException {
        int mapWidth = this.a.getMapWidth();
        int mapHeight = this.a.getMapHeight();
        LatLng fromScreenLocation = fromScreenLocation(new Point(0, 0));
        LatLng fromScreenLocation2 = fromScreenLocation(new Point(mapWidth, 0));
        LatLng fromScreenLocation3 = fromScreenLocation(new Point(0, mapHeight));
        LatLng fromScreenLocation4 = fromScreenLocation(new Point(mapWidth, mapHeight));
        return new VisibleRegion(fromScreenLocation3, fromScreenLocation4, fromScreenLocation, fromScreenLocation2, LatLngBounds.builder().include(fromScreenLocation3).include(fromScreenLocation4).include(fromScreenLocation).include(fromScreenLocation2).build());
    }

    public PointF toMapLocation(LatLng latLng) throws RemoteException {
        if (latLng == null) {
            return null;
        }
        FPoint fPoint = new FPoint();
        this.a.getLatLng2Map(latLng.latitude, latLng.longitude, fPoint);
        return new PointF(fPoint.x, fPoint.y);
    }

    public float toMapLenWithWin(int i) {
        if (i <= 0) {
            return 0.0f;
        }
        return this.a.toMapLenWithWin(i);
    }

    public TileProjection fromBoundsToTile(LatLngBounds latLngBounds, int i, int i2) throws RemoteException {
        if (latLngBounds == null || i < 0 || i > 20 || i2 <= 0) {
            return null;
        }
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        this.a.latlon2Geo(latLngBounds.southwest.latitude, latLngBounds.southwest.longitude, iPoint);
        this.a.latlon2Geo(latLngBounds.northeast.latitude, latLngBounds.northeast.longitude, iPoint2);
        int i3 = (iPoint.x >> (20 - i)) / i2;
        int i4 = (iPoint2.y >> (20 - i)) / i2;
        return new TileProjection((iPoint.x - ((i3 << (20 - i)) * i2)) >> (20 - i), (iPoint2.y - ((i4 << (20 - i)) * i2)) >> (20 - i), i3, (iPoint2.x >> (20 - i)) / i2, i4, (iPoint.y >> (20 - i)) / i2);
    }

    public LatLngBounds getMapBounds(LatLng latLng, float f) throws RemoteException {
        if (this.a == null || latLng == null) {
            return null;
        }
        return this.a.getMapBounds(latLng, dj.a(f));
    }
}
