package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.opengl.GLES10;
import android.os.Build.VERSION;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.AMapException;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import dji.pilot.visual.a.d;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class ai implements IMarkerDelegate {
    private static int a = 0;
    private boolean A = false;
    private boolean B = true;
    private ae C;
    private FloatBuffer D;
    private Object E;
    private boolean F = false;
    private CopyOnWriteArrayList<BitmapDescriptor> G = null;
    private boolean H = false;
    private boolean I = false;
    private boolean J = true;
    private int K = 0;
    private int L = 20;
    private boolean M = false;
    private int N;
    private int O;
    private long P = 0;
    private boolean b = false;
    private boolean c = false;
    private boolean d = false;
    private float e = 0.0f;
    private float f = 0.0f;
    private boolean g = false;
    private int h = 0;
    private int i = 0;
    private int j = 0;
    private int k = 0;
    private int l;
    private int m;
    private FPoint n = new FPoint();
    private float[] o;
    private int[] p = null;
    private float q = 0.0f;
    private boolean r = false;
    private FloatBuffer s = null;
    private String t;
    private LatLng u;
    private LatLng v;
    private String w;
    private String x;
    private float y = d.c;
    private float z = 1.0f;

    private static String a(String str) {
        a++;
        return str + a;
    }

    public void setRotateAngle(float f) {
        this.f = f;
        this.e = (((-f) % 360.0f) + 360.0f) % 360.0f;
        if (isInfoWindowShown()) {
            this.C.f(this);
            this.C.e(this);
        }
        c();
    }

    public boolean isDestory() {
        return this.r;
    }

    public void realDestroy() {
        if (this.r) {
            try {
                if (this.G != null) {
                    Iterator it = this.G.iterator();
                    while (it.hasNext()) {
                        ((BitmapDescriptor) it.next()).recycle();
                    }
                    this.G = null;
                }
                if (this.D != null) {
                    this.D.clear();
                    this.D = null;
                }
                if (this.s != null) {
                    this.s.clear();
                    this.s = null;
                }
                this.u = null;
                this.E = null;
                this.p = null;
            } catch (Throwable th) {
                ee.a(th, "MarkerDelegateImp", "realdestroy");
                th.printStackTrace();
                Log.d("destroy erro", "MarkerDelegateImp destroy");
            }
        }
    }

    public void destroy() {
        try {
            int i;
            this.r = true;
            remove();
            if (this.C != null) {
                this.C.a.callRunDestroy();
                i = 0;
                while (this.p != null && i < this.p.length) {
                    this.C.a(Integer.valueOf(this.p[i]));
                    this.C.a(this.p[i]);
                    i++;
                }
            }
            i = 0;
            while (this.G != null && i < this.G.size()) {
                ((BitmapDescriptor) this.G.get(i)).recycle();
                i++;
            }
        } catch (Throwable th) {
            ee.a(th, "MarkerDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "MarkerDelegateImp destroy");
        }
    }

    synchronized void a() {
        if (this.G == null) {
            this.G = new CopyOnWriteArrayList();
        } else {
            this.G.clear();
        }
    }

    public synchronized void a(ArrayList<BitmapDescriptor> arrayList) {
        a();
        if (arrayList != null) {
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                BitmapDescriptor bitmapDescriptor = (BitmapDescriptor) it.next();
                if (bitmapDescriptor != null) {
                    this.G.add(bitmapDescriptor);
                }
            }
        }
    }

    public ai(MarkerOptions markerOptions, ae aeVar) {
        this.C = aeVar;
        this.u = markerOptions.getPosition();
        IPoint iPoint = new IPoint();
        this.H = markerOptions.isGps();
        if (markerOptions.getPosition() != null) {
            if (this.H) {
                try {
                    double[] a = gd.a(markerOptions.getPosition().longitude, markerOptions.getPosition().latitude);
                    this.v = new LatLng(a[1], a[0]);
                    MapProjection.lonlat2Geo(a[0], a[1], iPoint);
                } catch (Throwable th) {
                    ee.a(th, "MarkerDelegateImp", "create");
                    this.v = markerOptions.getPosition();
                }
            } else {
                MapProjection.lonlat2Geo(this.u.longitude, this.u.latitude, iPoint);
            }
        }
        this.l = iPoint.x;
        this.m = iPoint.y;
        this.y = markerOptions.getAnchorU();
        this.z = markerOptions.getAnchorV();
        this.h = markerOptions.getInfoWindowOffsetX();
        this.i = markerOptions.getInfoWindowOffsetY();
        this.L = markerOptions.getPeriod();
        this.q = markerOptions.getZIndex();
        calFPoint();
        a(markerOptions.getIcons());
        this.B = markerOptions.isVisible();
        this.x = markerOptions.getSnippet();
        this.w = markerOptions.getTitle();
        this.A = markerOptions.isDraggable();
        this.t = getId();
        this.F = markerOptions.isPerspective();
        this.g = markerOptions.isFlat();
    }

    public IPoint b() {
        if (this.u == null && !this.M) {
            return null;
        }
        IPoint iPoint = new IPoint();
        this.C.a().getMapProjection().map2Win(this.n.x, this.n.y, iPoint);
        return iPoint;
    }

    public int getWidth() {
        try {
            return getBitmapDescriptor().getWidth();
        } catch (Throwable th) {
            return 0;
        }
    }

    public int getHeight() {
        try {
            return getBitmapDescriptor().getHeight();
        } catch (Throwable th) {
            return 0;
        }
    }

    public FPoint anchorUVoff() {
        FPoint fPoint = new FPoint();
        if (!(this.G == null || this.G.size() == 0)) {
            fPoint.x = ((float) getWidth()) * this.y;
            fPoint.y = ((float) getHeight()) * this.z;
        }
        return fPoint;
    }

    public boolean isContains() {
        return this.C.a((IMarkerDelegate) this);
    }

    public IPoint getAnchor() {
        IPoint b = b();
        if (b == null) {
            return null;
        }
        return b;
    }

    public Rect getRect() {
        if (this.o == null) {
            return new Rect(0, 0, 0, 0);
        }
        try {
            Rect rect;
            MapProjection mapProjection = this.C.a.getMapProjection();
            int width = getWidth();
            int height = getHeight();
            IPoint iPoint = new IPoint();
            IPoint iPoint2 = new IPoint();
            mapProjection.map2Win(this.n.x, this.n.y, iPoint);
            if (this.g) {
                mapProjection.map2Win(this.o[0], this.o[1], iPoint2);
                rect = new Rect(iPoint2.x, iPoint2.y, iPoint2.x, iPoint2.y);
                mapProjection.map2Win(this.o[3], this.o[4], iPoint2);
                rect.union(iPoint2.x, iPoint2.y);
                mapProjection.map2Win(this.o[6], this.o[7], iPoint2);
                rect.union(iPoint2.x, iPoint2.y);
                mapProjection.map2Win(this.o[9], this.o[10], iPoint2);
                rect.union(iPoint2.x, iPoint2.y);
            } else {
                a((-this.y) * ((float) width), (this.z - 1.0f) * ((float) height), iPoint2);
                rect = new Rect(iPoint.x + iPoint2.x, iPoint.y - iPoint2.y, iPoint.x + iPoint2.x, iPoint.y - iPoint2.y);
                a((-this.y) * ((float) width), this.z * ((float) height), iPoint2);
                rect.union(iPoint.x + iPoint2.x, iPoint.y - iPoint2.y);
                a((1.0f - this.y) * ((float) width), this.z * ((float) height), iPoint2);
                rect.union(iPoint.x + iPoint2.x, iPoint.y - iPoint2.y);
                a((1.0f - this.y) * ((float) width), (this.z - 1.0f) * ((float) height), iPoint2);
                rect.union(iPoint.x + iPoint2.x, iPoint.y - iPoint2.y);
            }
            this.j = rect.centerX() - iPoint.x;
            this.k = rect.top - iPoint.y;
            return rect;
        } catch (Throwable th) {
            ee.a(th, "MarkerDelegateImp", "getRect");
            th.printStackTrace();
            return new Rect(0, 0, 0, 0);
        }
    }

    public boolean remove() {
        c();
        this.B = false;
        if (this.C != null) {
            return this.C.c(this);
        }
        return false;
    }

    private void c() {
        if (this.C.a != null) {
            this.C.a.setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        if (!this.M || this.n == null) {
            return this.u;
        }
        DPoint dPoint = new DPoint();
        IPoint iPoint = new IPoint();
        calFPoint();
        this.C.a.map2Geo(this.n.x, this.n.y, iPoint);
        MapProjection.geo2LonLat(iPoint.x, iPoint.y, dPoint);
        return new LatLng(dPoint.y, dPoint.x);
    }

    public String getId() {
        if (this.t == null) {
            this.t = a("Marker");
        }
        return this.t;
    }

    public void setPosition(LatLng latLng) {
        if (latLng == null) {
            ee.a(new AMapException("非法坐标值 latlng is null"), "setPosition", "Marker");
            return;
        }
        this.u = latLng;
        IPoint iPoint = new IPoint();
        if (this.H) {
            try {
                double[] a = gd.a(latLng.longitude, latLng.latitude);
                this.v = new LatLng(a[1], a[0]);
                MapProjection.lonlat2Geo(a[0], a[1], iPoint);
            } catch (Throwable th) {
                this.v = latLng;
            }
        } else {
            MapProjection.lonlat2Geo(latLng.longitude, latLng.latitude, iPoint);
        }
        this.l = iPoint.x;
        this.m = iPoint.y;
        this.M = false;
        calFPoint();
        c();
    }

    public void setTitle(String str) {
        this.w = str;
        c();
    }

    public String getTitle() {
        return this.w;
    }

    public void setSnippet(String str) {
        this.x = str;
        c();
    }

    public String getSnippet() {
        return this.x;
    }

    public void setDraggable(boolean z) {
        this.A = z;
        c();
    }

    public synchronized void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        if (arrayList != null) {
            try {
                if (this.G != null) {
                    a((ArrayList) arrayList);
                    this.I = false;
                    this.b = false;
                    if (this.D != null) {
                        this.D.clear();
                        this.D = null;
                    }
                    this.p = null;
                    if (isInfoWindowShown()) {
                        this.C.f(this);
                        this.C.e(this);
                    }
                    c();
                }
            } catch (Throwable th) {
                ee.a(th, "MarkerDelegateImp", "setIcons");
                th.printStackTrace();
            }
        }
    }

    public synchronized ArrayList<BitmapDescriptor> getIcons() {
        ArrayList<BitmapDescriptor> arrayList;
        if (this.G == null || this.G.size() <= 0) {
            arrayList = null;
        } else {
            ArrayList<BitmapDescriptor> arrayList2 = new ArrayList();
            Iterator it = this.G.iterator();
            while (it.hasNext()) {
                arrayList2.add((BitmapDescriptor) it.next());
            }
            arrayList = arrayList2;
        }
        return arrayList;
    }

    public synchronized void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                if (this.G != null) {
                    this.G.clear();
                    this.G.add(bitmapDescriptor);
                    this.I = false;
                    this.b = false;
                    this.p = null;
                    if (this.D != null) {
                        this.D.clear();
                        this.D = null;
                    }
                    if (isInfoWindowShown()) {
                        this.C.f(this);
                        this.C.e(this);
                    }
                    c();
                }
            } catch (Throwable th) {
                ee.a(th, "MarkerDelegateImp", "setIcon");
                th.printStackTrace();
            }
        }
    }

    public synchronized BitmapDescriptor getBitmapDescriptor() {
        BitmapDescriptor bitmapDescriptor;
        try {
            if (this.G == null || this.G.size() == 0) {
                a();
                this.G.add(BitmapDescriptorFactory.defaultMarker());
            } else if (this.G.get(0) == null) {
                this.G.clear();
                bitmapDescriptor = getBitmapDescriptor();
            }
            bitmapDescriptor = (BitmapDescriptor) this.G.get(0);
        } catch (Throwable th) {
            ee.a(th, "MarkerDelegateImp", "getBitmapDescriptor");
            th.printStackTrace();
            bitmapDescriptor = null;
        }
        return bitmapDescriptor;
    }

    public boolean isDraggable() {
        return this.A;
    }

    public void showInfoWindow() {
        if (this.B) {
            this.C.e(this);
            c();
        }
    }

    public void hideInfoWindow() {
        if (isInfoWindowShown()) {
            this.C.f(this);
            c();
            this.c = false;
        }
        this.d = false;
    }

    public void setInfoWindowShown(boolean z) {
        this.c = z;
        if (this.c && this.M) {
            this.d = true;
        }
    }

    public boolean isInfoWindowShown() {
        return this.c;
    }

    public void setVisible(boolean z) {
        if (this.B != z) {
            this.B = z;
            if (!z && isInfoWindowShown()) {
                this.C.f(this);
            }
            c();
        }
    }

    public boolean isVisible() {
        return this.B;
    }

    public void setAnchor(float f, float f2) {
        if (this.y != f || this.z != f2) {
            this.y = f;
            this.z = f2;
            if (isInfoWindowShown()) {
                this.C.f(this);
                this.C.e(this);
            }
            c();
        }
    }

    public float getAnchorU() {
        return this.y;
    }

    public float getAnchorV() {
        return this.z;
    }

    public boolean equalsRemote(IMarkerDelegate iMarkerDelegate) throws RemoteException {
        if (equals(iMarkerDelegate) || iMarkerDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    public boolean calFPoint() {
        if (this.M) {
            this.C.a.getMapProjection().win2Map(this.N, this.O, this.n);
        } else {
            this.C.a.getMapProjection().geo2Map(this.l, this.m, this.n);
        }
        return true;
    }

    private void a(IAMapDelegate iAMapDelegate) throws RemoteException {
        float[] a = dj.a(iAMapDelegate, this.g ? 1 : 0, this.n, this.e, getWidth(), getHeight(), this.y, this.z);
        this.o = (float[]) a.clone();
        if (this.s == null) {
            this.s = dj.a(a);
        } else {
            this.s = dj.a(a, this.s);
        }
        if (this.G != null && this.G.size() > 0) {
            this.K++;
            if (this.K >= this.L * this.G.size()) {
                this.K = 0;
            }
            int i = this.K / this.L;
            if (!this.J) {
                c();
            }
            if (this.p != null && this.p.length > 0) {
                a(this.p[i % this.G.size()], this.s, this.D);
            }
        }
    }

    private void a(float f, float f2, IPoint iPoint) {
        float f3 = (float) ((3.141592653589793d * ((double) this.e)) / 180.0d);
        iPoint.x = (int) ((((double) f) * Math.cos((double) f3)) + (((double) f2) * Math.sin((double) f3)));
        iPoint.y = (int) ((((double) f2) * Math.cos((double) f3)) - (Math.sin((double) f3) * ((double) f)));
    }

    private void a(int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (i != 0 && floatBuffer != null && floatBuffer2 != null) {
            GLES10.glEnable(3042);
            GLES10.glBlendFunc(1, 771);
            GLES10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
            GLES10.glEnable(3553);
            GLES10.glEnableClientState(32884);
            GLES10.glEnableClientState(32888);
            GLES10.glBindTexture(3553, i);
            GLES10.glVertexPointer(3, 5126, 0, floatBuffer);
            GLES10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            GLES10.glDrawArrays(6, 0, 4);
            GLES10.glDisableClientState(32884);
            GLES10.glDisableClientState(32888);
            GLES10.glDisable(3553);
            GLES10.glDisable(3042);
        }
    }

    public void drawMarker(GL10 gl10, IAMapDelegate iAMapDelegate) {
        int i = 0;
        if (this.B && !this.r) {
            if (this.u == null && !this.M) {
                return;
            }
            if (getBitmapDescriptor() != null || this.G != null) {
                int i2;
                int i3;
                BitmapDescriptor bitmapDescriptor;
                if (!this.I) {
                    try {
                        if (this.G != null) {
                            this.p = new int[this.G.size()];
                            i2 = VERSION.SDK_INT >= 12 ? 1 : 0;
                            Iterator it = this.G.iterator();
                            i3 = 0;
                            while (it.hasNext()) {
                                bitmapDescriptor = (BitmapDescriptor) it.next();
                                if (i2 != 0) {
                                    i = this.C.a(bitmapDescriptor);
                                }
                                if (i == 0) {
                                    Bitmap bitmap = bitmapDescriptor.getBitmap();
                                    if (!(bitmap == null || bitmap.isRecycled())) {
                                        i = a(gl10);
                                        if (i2 != 0) {
                                            this.C.a(new am(bitmapDescriptor, i));
                                        }
                                        dj.b(gl10, i, bitmap, false);
                                    }
                                }
                                int i4 = i;
                                this.p[i3] = i4;
                                i3++;
                                i = i4;
                            }
                            if (this.G.size() == 1) {
                                this.J = true;
                            } else {
                                this.J = false;
                            }
                            this.I = true;
                        }
                    } catch (Throwable th) {
                        ee.a(th, "MarkerDelegateImp", "loadtexture");
                        return;
                    }
                }
                try {
                    if (!this.b) {
                        if (this.D == null) {
                            bitmapDescriptor = getBitmapDescriptor();
                            if (bitmapDescriptor != null) {
                                i = bitmapDescriptor.getWidth();
                                i3 = bitmapDescriptor.getHeight();
                                i2 = bitmapDescriptor.getBitmap().getHeight();
                                float width = ((float) i) / ((float) bitmapDescriptor.getBitmap().getWidth());
                                float f = ((float) i3) / ((float) i2);
                                this.D = dj.a(new float[]{0.0f, f, width, f, width, 0.0f, 0.0f, 0.0f});
                            } else {
                                return;
                            }
                        }
                        calFPoint();
                        this.P = System.currentTimeMillis();
                        this.b = true;
                    }
                    if (this.M) {
                        iAMapDelegate.pixel2Map(this.N, this.O, this.n);
                    }
                    a(iAMapDelegate);
                    if (this.d && isInfoWindowShown()) {
                        this.C.k();
                        if (System.currentTimeMillis() - this.P > 1000) {
                            this.d = false;
                        }
                    }
                } catch (Throwable th2) {
                    ee.a(th2, "MarkerDelegateImp", "drawMarker");
                }
            }
        }
    }

    private int a(GL10 gl10) {
        int texsureId = this.C.a.getTexsureId();
        if (texsureId != 0) {
            return texsureId;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean isAllowLow() {
        return this.J;
    }

    public void setPeriod(int i) {
        if (i <= 1) {
            this.L = 1;
        } else {
            this.L = i;
        }
    }

    public void setObject(Object obj) {
        this.E = obj;
    }

    public Object getObject() {
        return this.E;
    }

    public void setPerspective(boolean z) {
        this.F = z;
    }

    public boolean isPerspective() {
        return this.F;
    }

    public int getTextureId() {
        int i = 0;
        try {
            if (this.G != null && this.G.size() > 0) {
                i = this.p[0];
            }
        } catch (Throwable th) {
        }
        return i;
    }

    public int getPeriod() {
        return this.L;
    }

    public LatLng getRealPosition() {
        if (!this.M) {
            return this.H ? this.v : this.u;
        } else {
            this.C.a.getMapProjection().win2Map(this.N, this.O, this.n);
            DPoint dPoint = new DPoint();
            this.C.a.getPixel2LatLng(this.N, this.O, dPoint);
            return new LatLng(dPoint.y, dPoint.y);
        }
    }

    public void set2Top() {
        this.C.d(this);
    }

    public void setFlat(boolean z) throws RemoteException {
        this.g = z;
        c();
    }

    public boolean isFlat() {
        return this.g;
    }

    public float getRotateAngle() {
        return this.f;
    }

    public void setInfoWindowOffset(int i, int i2) throws RemoteException {
        this.h = i;
        this.i = i2;
    }

    public int getInfoWindowOffsetX() {
        return this.h;
    }

    public int getInfoWindowOffsetY() {
        return this.i;
    }

    public void setPositionByPixels(int i, int i2) {
        int i3 = 1;
        this.N = i;
        this.O = i2;
        this.M = true;
        calFPoint();
        try {
            IAMapDelegate iAMapDelegate = this.C.a;
            if (!this.g) {
                i3 = 0;
            }
            this.o = dj.a(iAMapDelegate, i3, this.n, this.e, getWidth(), getHeight(), this.y, this.z);
        } catch (Throwable th) {
            ee.a(th, "MarkerDelegateImp", "setPositionByPixels");
        }
        c();
        if (isInfoWindowShown()) {
            showInfoWindow();
        }
    }

    public int getRealInfoWindowOffsetX() {
        return this.j;
    }

    public int getRealInfoWindowOffsetY() {
        return this.k;
    }

    public FPoint getMapPosition() {
        return this.n;
    }

    public boolean isViewMode() {
        return this.M;
    }

    public void setZIndex(float f) {
        this.q = f;
        this.C.i();
    }

    public float getZIndex() {
        return this.q;
    }

    public boolean checkInBounds() {
        Rect rect = this.C.a.getRect();
        if (this.M || rect == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.n != null) {
            this.C.a.getMapProjection().map2Win(this.n.x, this.n.y, iPoint);
        }
        return rect.contains(iPoint.x, iPoint.y);
    }

    public void setGeoPoint(IPoint iPoint) {
        this.M = false;
        this.l = iPoint.x;
        this.m = iPoint.y;
        DPoint dPoint = new DPoint();
        MapProjection.geo2LonLat(this.l, this.m, dPoint);
        this.u = new LatLng(dPoint.y, dPoint.x, false);
        this.C.a.getMapProjection().geo2Map(this.l, this.m, this.n);
    }

    public IPoint getGeoPoint() {
        IPoint iPoint = new IPoint();
        if (!this.M) {
            return new IPoint(this.l, this.m);
        }
        this.C.a.getPixel2Geo(this.N, this.O, iPoint);
        return iPoint;
    }

    public void reLoadTexture() {
        this.I = false;
        if (this.p != null) {
            Arrays.fill(this.p, 0);
        }
    }
}
