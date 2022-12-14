package com.amap.api.maps.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.amap.api.mapcore.util.dh;
import com.amap.api.mapcore.util.dj;
import com.amap.api.mapcore.util.r;
import com.amap.api.maps.AMap;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.amap.api.maps.model.LatLngBounds.Builder;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.amap.api.maps.model.PolylineOptions;
import com.amap.api.services.core.LatLonPoint;
import com.amap.api.services.route.DrivePath;
import com.amap.api.services.route.DriveStep;
import dji.pilot.visual.a.d;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DrivingRouteOverlay extends b {
    private DrivePath a;
    private List<LatLonPoint> b;
    private List<Marker> c;
    private boolean d;
    private Context e;
    private PolylineOptions f;

    public /* bridge */ /* synthetic */ void setNodeIconVisibility(boolean z) {
        super.setNodeIconVisibility(z);
    }

    public /* bridge */ /* synthetic */ void zoomToSpan() {
        super.zoomToSpan();
    }

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2) {
        this(context, aMap, drivePath, latLonPoint, latLonPoint2, null);
        this.e = context;
    }

    public DrivingRouteOverlay(Context context, AMap aMap, DrivePath drivePath, LatLonPoint latLonPoint, LatLonPoint latLonPoint2, List<LatLonPoint> list) {
        super(context);
        this.c = new ArrayList();
        this.d = true;
        this.mAMap = aMap;
        this.a = drivePath;
        this.startPoint = a.a(latLonPoint);
        this.endPoint = a.a(latLonPoint2);
        this.b = list;
        this.e = context;
    }

    public void addToMap() {
        a();
        try {
            List steps = this.a.getSteps();
            int i = 0;
            while (i < steps.size()) {
                DriveStep driveStep = (DriveStep) steps.get(i);
                LatLng a = a.a(a(driveStep));
                if (i < steps.size() - 1 && i == 0) {
                    a(this.startPoint, a);
                }
                a(driveStep, a);
                c(driveStep);
                if (i == steps.size() - 1) {
                    a(b(driveStep), this.endPoint);
                }
                i++;
            }
            addStartAndEndMarker();
            c();
            b();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private void a() {
        this.f = null;
        this.f = new PolylineOptions();
        this.f.color(getDriveColor()).width(getRouteWidth());
    }

    private void b() {
        addPolyLine(this.f);
    }

    private LatLonPoint a(DriveStep driveStep) {
        return (LatLonPoint) driveStep.getPolyline().get(0);
    }

    private LatLonPoint b(DriveStep driveStep) {
        return (LatLonPoint) driveStep.getPolyline().get(driveStep.getPolyline().size() - 1);
    }

    private void a(LatLonPoint latLonPoint, LatLng latLng) {
        a(a.a(latLonPoint), latLng);
    }

    private void a(LatLng latLng, LatLng latLng2) {
        this.f.add(latLng, latLng2);
    }

    private void c(DriveStep driveStep) {
        this.f.addAll(a.a(driveStep.getPolyline()));
    }

    private void a(DriveStep driveStep, LatLng latLng) {
        addStationMarker(new MarkerOptions().position(latLng).title("??????:" + driveStep.getAction() + "\n??????:" + driveStep.getRoad()).snippet(driveStep.getInstruction()).visible(this.nodeIconVisible).anchor(d.c, d.c).icon(getDriveBitmapDescriptor()));
    }

    private void c() {
        if (this.b != null && this.b.size() > 0) {
            for (int i = 0; i < this.b.size(); i++) {
                LatLonPoint latLonPoint = (LatLonPoint) this.b.get(i);
                if (latLonPoint != null) {
                    this.c.add(this.mAMap.addMarker(new MarkerOptions().position(new LatLng(latLonPoint.getLatitude(), latLonPoint.getLongitude())).visible(this.d).icon(d()).title("?????????")));
                }
            }
        }
    }

    protected LatLngBounds getLatLngBounds() {
        Builder builder = LatLngBounds.builder();
        builder.include(new LatLng(this.startPoint.latitude, this.startPoint.longitude));
        builder.include(new LatLng(this.endPoint.latitude, this.endPoint.longitude));
        if (this.b != null && this.b.size() > 0) {
            for (int i = 0; i < this.b.size(); i++) {
                builder.include(new LatLng(((LatLonPoint) this.b.get(i)).getLatitude(), ((LatLonPoint) this.b.get(i)).getLongitude()));
            }
        }
        return builder.build();
    }

    public void setThroughPointIconVisibility(boolean z) {
        try {
            this.d = z;
            if (this.c != null && this.c.size() > 0) {
                for (int i = 0; i < this.c.size(); i++) {
                    ((Marker) this.c.get(i)).setVisible(z);
                }
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    private BitmapDescriptor d() {
        Throwable th;
        Throwable th2;
        Throwable th3;
        BitmapDescriptor fromBitmap;
        Bitmap a;
        InputStream inputStream = null;
        InputStream open;
        try {
            Bitmap decodeStream;
            open = dh.a(this.e).open("amap_throughpoint.png");
            try {
                decodeStream = BitmapFactory.decodeStream(open);
            } catch (Throwable th22) {
                th = th22;
                Object obj = inputStream;
                th3 = th;
                try {
                    th3.printStackTrace();
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th32) {
                            th32.printStackTrace();
                        }
                    }
                    fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                    a.recycle();
                    return fromBitmap;
                } catch (Throwable th4) {
                    th22 = th4;
                    if (open != null) {
                        try {
                            open.close();
                        } catch (Throwable th322) {
                            th322.printStackTrace();
                        }
                    }
                    throw th22;
                }
            }
            try {
                a = dj.a(decodeStream, r.a);
                if (open != null) {
                    try {
                        open.close();
                    } catch (Throwable th3222) {
                        th3222.printStackTrace();
                    }
                }
            } catch (Throwable th222) {
                th = th222;
                a = decodeStream;
                th3222 = th;
                th3222.printStackTrace();
                if (open != null) {
                    open.close();
                }
                fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
                a.recycle();
                return fromBitmap;
            }
        } catch (Throwable th5) {
            th222 = th5;
            open = inputStream;
            if (open != null) {
                open.close();
            }
            throw th222;
        }
        fromBitmap = BitmapDescriptorFactory.fromBitmap(a);
        a.recycle();
        return fromBitmap;
    }

    public void removeFromMap() {
        try {
            super.removeFromMap();
            if (this.c != null && this.c.size() > 0) {
                for (int i = 0; i < this.c.size(); i++) {
                    ((Marker) this.c.get(i)).remove();
                }
                this.c.clear();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }
}
