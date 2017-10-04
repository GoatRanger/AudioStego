package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import com.amap.api.maps.model.CameraPosition;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import dji.pilot.usercenter.protocol.d;

class e extends Handler {
    final /* synthetic */ c a;

    e(c cVar) {
        this.a = cVar;
    }

    public void handleMessage(Message message) {
        if (message != null && !this.a.aQ.booleanValue()) {
            this.a.setRunLowFrame(false);
            CameraPosition cameraPosition;
            CameraUpdateFactoryDelegate cameraUpdateFactoryDelegate;
            int currX;
            int currY;
            switch (message.what) {
                case 2:
                    Log.w("amapsdk", "Key验证失败：[" + dm.b + d.H);
                    break;
                case 10:
                    cameraPosition = (CameraPosition) message.obj;
                    if (!(cameraPosition == null || this.a.aa == null)) {
                        this.a.aa.onCameraChange(cameraPosition);
                        break;
                    }
                case 11:
                    if (this.a.aE != null) {
                        try {
                            this.a.moveCamera(this.a.aE);
                        } catch (Throwable th) {
                            ee.a(th, "AMapDelegateImp", "onMapLoaded");
                            th.printStackTrace();
                        }
                    }
                    if (this.a.Z != null) {
                        this.a.Z.onMapLoaded();
                        break;
                    }
                    break;
                case 12:
                    cameraUpdateFactoryDelegate = (CameraUpdateFactoryDelegate) message.obj;
                    if (cameraUpdateFactoryDelegate != null) {
                        this.a.e.a(cameraUpdateFactoryDelegate);
                        break;
                    }
                    break;
                case 13:
                    if (this.a.at != null && this.a.at.computeScrollOffset()) {
                        switch (this.a.at.getMode()) {
                            case 2:
                                cameraUpdateFactoryDelegate = CameraUpdateFactoryDelegate.newCamera(new IPoint(this.a.at.getCurrX(), this.a.at.getCurrY()), this.a.at.getCurrZ(), this.a.at.getCurrBearing(), this.a.at.getCurrTilt());
                                if (this.a.at.isFinished()) {
                                    cameraUpdateFactoryDelegate.isChangeFinished = true;
                                }
                                cameraUpdateFactoryDelegate.isUseAnchor = this.a.at.isUseAnchor();
                                this.a.e.a(cameraUpdateFactoryDelegate);
                                break;
                            default:
                                currX = this.a.at.getCurrX() - this.a.au;
                                currY = this.a.at.getCurrY() - this.a.av;
                                this.a.au = this.a.at.getCurrX();
                                this.a.av = this.a.at.getCurrY();
                                FPoint fPoint = new FPoint((float) (currX + (this.a.c() / 2)), (float) (currY + (this.a.d() / 2)));
                                FPoint fPoint2 = new FPoint();
                                this.a.J.win2Map((int) fPoint.x, (int) fPoint.y, fPoint2);
                                IPoint iPoint = new IPoint();
                                this.a.J.map2Geo(fPoint2.x, fPoint2.y, iPoint);
                                cameraUpdateFactoryDelegate = CameraUpdateFactoryDelegate.changeGeoCenter(iPoint);
                                if (this.a.at.isFinished()) {
                                    cameraUpdateFactoryDelegate.isChangeFinished = true;
                                }
                                this.a.e.a(cameraUpdateFactoryDelegate);
                                break;
                        }
                    }
                case 14:
                    if (this.a.R != null) {
                        this.a.R.b();
                        break;
                    }
                    return;
                case 16:
                    Bitmap bitmap = (Bitmap) message.obj;
                    currY = message.arg1;
                    if (bitmap != null) {
                        Canvas canvas = new Canvas(bitmap);
                        if (this.a.P != null) {
                            this.a.P.onDraw(canvas);
                        }
                        if (!(this.a.aj == null || this.a.ak == null)) {
                            Bitmap drawingCache = this.a.aj.getDrawingCache(true);
                            if (drawingCache != null) {
                                canvas.drawBitmap(drawingCache, (float) this.a.aj.getLeft(), (float) this.a.aj.getTop(), new Paint());
                            }
                        }
                        if (this.a.aA != null) {
                            this.a.aA.onMapPrint(new BitmapDrawable(this.a.H.getResources(), bitmap));
                        }
                        if (this.a.aB != null) {
                            this.a.aB.onMapScreenShot(bitmap);
                            this.a.aB.onMapScreenShot(bitmap, currY);
                        }
                    } else {
                        if (this.a.aA != null) {
                            this.a.aA.onMapPrint(null);
                        }
                        if (this.a.aB != null) {
                            this.a.aB.onMapScreenShot(null);
                            this.a.aB.onMapScreenShot(null, currY);
                        }
                    }
                    this.a.aA = null;
                    this.a.aB = null;
                    break;
                case 17:
                    if (!(this.a.aj == null || this.a.al == null)) {
                        this.a.aj.setVisibility(0);
                    }
                    try {
                        cameraPosition = this.a.getCameraPosition();
                        if (cameraPosition != null) {
                            if (cameraPosition.zoom < 10.0f || dg.a(cameraPosition.target.latitude, cameraPosition.target.longitude)) {
                                this.a.P.setVisibility(0);
                            } else {
                                this.a.P.setVisibility(8);
                            }
                        }
                        if (this.a.aw == null || !this.a.aN) {
                            this.a.a(true, cameraPosition);
                        }
                        if (this.a.aw != null) {
                            this.a.aO = true;
                            this.a.aw.onFinish();
                            this.a.aO = false;
                        }
                        if (!this.a.aP) {
                            this.a.aw = null;
                            break;
                        } else {
                            this.a.aP = false;
                            break;
                        }
                    } catch (Throwable th2) {
                        ee.a(th2, "AMapDelegateImpGLSurfaceView", "CameraUpdateFinish");
                        break;
                    }
                    break;
                case 18:
                    currX = this.a.c();
                    int d = this.a.d();
                    if (currX > 0 && d > 0) {
                        try {
                            CameraPosition cameraPosition2 = this.a.getCameraPosition();
                            MapProjection.lonlat2Geo(cameraPosition2.target.longitude, cameraPosition2.target.latitude, new IPoint());
                            MapProjection mapProjection = new MapProjection(this.a.G);
                            mapProjection.setCameraHeaderAngle(cameraPosition2.tilt);
                            mapProjection.setMapAngle(cameraPosition2.bearing);
                            mapProjection.setMapZoomer(cameraPosition2.zoom);
                            mapProjection.recalculate();
                            DPoint dPoint = new DPoint();
                            this.a.a(mapProjection, 0, 0, dPoint);
                            LatLng latLng = new LatLng(dPoint.y, dPoint.x, false);
                            this.a.a(mapProjection, currX, 0, dPoint);
                            LatLng latLng2 = new LatLng(dPoint.y, dPoint.x, false);
                            this.a.a(mapProjection, 0, d, dPoint);
                            LatLng latLng3 = new LatLng(dPoint.y, dPoint.x, false);
                            this.a.a(mapProjection, currX, d, dPoint);
                            this.a.bp = LatLngBounds.builder().include(latLng3).include(new LatLng(dPoint.y, dPoint.x, false)).include(latLng).include(latLng2).build();
                            mapProjection.recycle();
                            break;
                        } catch (Throwable th3) {
                            break;
                        }
                    }
                    this.a.bp = null;
                    break;
                    break;
                case 20:
                    if (this.a.at.isFinished() || !(this.a.at.getMode() == 1 || this.a.g == null)) {
                        this.a.g.b(false);
                    }
                    if (this.a.g != null) {
                        this.a.g.a(message.arg1 != 0);
                        break;
                    }
                    break;
                case 21:
                    if (this.a.f != null) {
                        this.a.f.a(this.a.getZoomLevel());
                        break;
                    }
                    break;
                case 22:
                    this.a.p();
                    break;
            }
            this.a.setRunLowFrame(false);
        }
    }
}
