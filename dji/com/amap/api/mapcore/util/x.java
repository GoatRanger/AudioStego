package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.LatLngBounds;
import com.autonavi.amap.mapcore.DPoint;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IGroundOverlayDelegate;
import com.autonavi.amap.mapcore.interfaces.IOverlayDelegate;
import dji.pilot.visual.a.d;
import java.nio.FloatBuffer;
import javax.microedition.khronos.opengles.GL10;

public class x implements IGroundOverlayDelegate {
    private IAMapDelegate a;
    private BitmapDescriptor b;
    private LatLng c;
    private float d;
    private float e;
    private LatLngBounds f;
    private float g;
    private float h;
    private boolean i = true;
    private float j = 0.0f;
    private float k = d.c;
    private float l = d.c;
    private String m;
    private FloatBuffer n = null;
    private FloatBuffer o;
    private int p;
    private boolean q = false;
    private boolean r = false;

    public x(IAMapDelegate iAMapDelegate) {
        this.a = iAMapDelegate;
        try {
            this.m = getId();
        } catch (Throwable e) {
            ee.a(e, "GroundOverlayDelegateImp", "create");
            e.printStackTrace();
        }
    }

    public void remove() throws RemoteException {
        this.a.deleteTexsureId(this.p);
        this.a.removeGLOverlay(getId());
        this.a.setRunLowFrame(false);
    }

    public String getId() throws RemoteException {
        if (this.m == null) {
            this.m = v.a("GroundOverlay");
        }
        return this.m;
    }

    public void setZIndex(float f) throws RemoteException {
        this.h = f;
        this.a.changeGLOverlayIndex();
        this.a.setRunLowFrame(false);
    }

    public float getZIndex() throws RemoteException {
        return this.h;
    }

    public void setVisible(boolean z) throws RemoteException {
        this.i = z;
        this.a.setRunLowFrame(false);
    }

    public boolean isVisible() throws RemoteException {
        return this.i;
    }

    public boolean equalsRemote(IOverlayDelegate iOverlayDelegate) throws RemoteException {
        if (equals(iOverlayDelegate) || iOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() throws RemoteException {
        return super.hashCode();
    }

    public void calMapFPoint() throws RemoteException {
        this.r = false;
        if (this.c == null) {
            b();
        } else if (this.f == null) {
            a();
        } else {
            c();
        }
    }

    private void a() {
        if (this.c != null) {
            double cos = ((double) this.d) / ((6371000.79d * Math.cos(this.c.latitude * 0.01745329251994329d)) * 0.01745329251994329d);
            double d = ((double) this.e) / 111194.94043265979d;
            this.f = new LatLngBounds(new LatLng(this.c.latitude - (((double) (1.0f - this.l)) * d), this.c.longitude - (((double) this.k) * cos)), new LatLng((d * ((double) this.l)) + this.c.latitude, (cos * ((double) (1.0f - this.k))) + this.c.longitude));
            c();
        }
    }

    private void b() {
        if (this.f != null) {
            LatLng latLng = this.f.southwest;
            LatLng latLng2 = this.f.northeast;
            this.c = new LatLng(latLng.latitude + (((double) (1.0f - this.l)) * (latLng2.latitude - latLng.latitude)), latLng.longitude + (((double) this.k) * (latLng2.longitude - latLng.longitude)));
            this.d = (float) (((6371000.79d * Math.cos(this.c.latitude * 0.01745329251994329d)) * (latLng2.longitude - latLng.longitude)) * 0.01745329251994329d);
            this.e = (float) (((latLng2.latitude - latLng.latitude) * 6371000.79d) * 0.01745329251994329d);
            c();
        }
    }

    private void c() {
        if (this.f != null) {
            float[] fArr = new float[12];
            FPoint fPoint = new FPoint();
            FPoint fPoint2 = new FPoint();
            FPoint fPoint3 = new FPoint();
            FPoint fPoint4 = new FPoint();
            this.a.getLatLng2Map(this.f.southwest.latitude, this.f.southwest.longitude, fPoint);
            this.a.getLatLng2Map(this.f.southwest.latitude, this.f.northeast.longitude, fPoint2);
            this.a.getLatLng2Map(this.f.northeast.latitude, this.f.northeast.longitude, fPoint3);
            this.a.getLatLng2Map(this.f.northeast.latitude, this.f.southwest.longitude, fPoint4);
            if (this.g != 0.0f) {
                double d = (double) (fPoint2.x - fPoint.x);
                double d2 = (double) (fPoint2.y - fPoint3.y);
                DPoint dPoint = new DPoint();
                dPoint.x = ((double) fPoint.x) + (((double) this.k) * d);
                dPoint.y = ((double) fPoint.y) - (((double) (1.0f - this.l)) * d2);
                a(dPoint, 0.0d, 0.0d, d, d2, fPoint);
                a(dPoint, d, 0.0d, d, d2, fPoint2);
                a(dPoint, d, d2, d, d2, fPoint3);
                a(dPoint, 0.0d, d2, d, d2, fPoint4);
            }
            fArr[0] = fPoint.x;
            fArr[1] = fPoint.y;
            fArr[2] = 0.0f;
            fArr[3] = fPoint2.x;
            fArr[4] = fPoint2.y;
            fArr[5] = 0.0f;
            fArr[6] = fPoint3.x;
            fArr[7] = fPoint3.y;
            fArr[8] = 0.0f;
            fArr[9] = fPoint4.x;
            fArr[10] = fPoint4.y;
            fArr[11] = 0.0f;
            if (this.n == null) {
                this.n = dj.a(fArr);
            } else {
                this.n = dj.a(fArr, this.n);
            }
        }
    }

    private void a(DPoint dPoint, double d, double d2, double d3, double d4, FPoint fPoint) {
        double d5 = d - (((double) this.k) * d3);
        double d6 = (((double) (1.0f - this.l)) * d4) - d2;
        double d7 = ((double) (-this.g)) * 0.01745329251994329d;
        fPoint.x = (float) (dPoint.x + ((Math.cos(d7) * d5) + (Math.sin(d7) * d6)));
        fPoint.y = (float) (((d6 * Math.cos(d7)) - (d5 * Math.sin(d7))) + dPoint.y);
    }

    private void d() {
        if (this.b != null) {
            int width = this.b.getWidth();
            float width2 = ((float) width) / ((float) this.b.getBitmap().getWidth());
            float height = ((float) this.b.getHeight()) / ((float) this.b.getBitmap().getHeight());
            this.o = dj.a(new float[]{0.0f, height, width2, height, width2, 0.0f, 0.0f, 0.0f});
        }
    }

    public void draw(GL10 gl10) throws RemoteException {
        if (!this.i) {
            return;
        }
        if ((this.c != null || this.f != null) && this.b != null) {
            if (!this.q) {
                Bitmap bitmap = this.b.getBitmap();
                if (!(bitmap == null || bitmap.isRecycled())) {
                    if (this.p == 0) {
                        this.p = this.a.getTexsureId();
                        if (this.p == 0) {
                            int[] iArr = new int[]{0};
                            gl10.glGenTextures(1, iArr, 0);
                            this.p = iArr[0];
                        }
                    } else {
                        gl10.glDeleteTextures(1, new int[]{this.p}, 0);
                    }
                    dj.b(gl10, this.p, bitmap, true);
                }
                this.q = true;
            }
            if (this.d != 0.0f || this.e != 0.0f) {
                a(gl10, this.p, this.n, this.o);
                this.r = true;
            }
        }
    }

    private void a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glTexEnvf(8960, 8704, 8448.0f);
            gl10.glBlendFunc(1, 771);
            gl10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f - this.j);
            gl10.glEnable(3553);
            gl10.glEnableClientState(32884);
            gl10.glEnableClientState(32888);
            gl10.glBindTexture(3553, i);
            gl10.glVertexPointer(3, 5126, 0, floatBuffer);
            gl10.glTexCoordPointer(2, 5126, 0, floatBuffer2);
            gl10.glDrawArrays(6, 0, 4);
            gl10.glDisableClientState(32884);
            gl10.glDisableClientState(32888);
            gl10.glDisable(3553);
            gl10.glDisable(3042);
        }
    }

    public void destroy() {
        try {
            remove();
            if (this.b != null) {
                Bitmap bitmap = this.b.getBitmap();
                if (bitmap != null) {
                    bitmap.recycle();
                    this.b = null;
                }
            }
            if (this.o != null) {
                this.o.clear();
                this.o = null;
            }
            if (this.n != null) {
                this.n.clear();
                this.n = null;
            }
            this.c = null;
            this.f = null;
        } catch (Throwable th) {
            ee.a(th, "GroundOverlayDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "GroundOverlayDelegateImp destroy");
        }
    }

    public boolean checkInBounds() {
        Rect rect = this.a.getRect();
        if (rect == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.c != null) {
            this.a.getLatLng2Pixel(this.c.latitude, this.c.longitude, iPoint);
        }
        return rect.contains(iPoint.x, iPoint.y);
    }

    public void setPosition(LatLng latLng) throws RemoteException {
        this.c = latLng;
        a();
        this.a.setRunLowFrame(false);
    }

    public LatLng getPosition() throws RemoteException {
        return this.c;
    }

    public void setDimensions(float f) throws RemoteException {
        cu.b(f >= 0.0f, "Width must be non-negative");
        if (!this.q || this.d == f) {
            this.d = f;
            this.e = f;
        } else {
            this.d = f;
            this.e = f;
            a();
        }
        this.a.setRunLowFrame(false);
    }

    public void setDimensions(float f, float f2) throws RemoteException {
        boolean z = true;
        cu.b(f >= 0.0f, "Width must be non-negative");
        if (f2 < 0.0f) {
            z = false;
        }
        cu.b(z, "Height must be non-negative");
        if (!this.q || this.d == f || this.e == f2) {
            this.d = f;
            this.e = f2;
        } else {
            this.d = f;
            this.e = f2;
            a();
        }
        this.a.setRunLowFrame(false);
    }

    public float getWidth() throws RemoteException {
        return this.d;
    }

    public float getHeight() throws RemoteException {
        return this.e;
    }

    public void setPositionFromBounds(LatLngBounds latLngBounds) throws RemoteException {
        this.f = latLngBounds;
        b();
        this.a.setRunLowFrame(false);
    }

    public LatLngBounds getBounds() throws RemoteException {
        return this.f;
    }

    public void setBearing(float f) throws RemoteException {
        float f2 = ((f % 360.0f) + 360.0f) % 360.0f;
        if (!this.q || ((double) Math.abs(this.g - f2)) <= 1.0E-7d) {
            this.g = f2;
        } else {
            this.g = f2;
            c();
        }
        this.a.setRunLowFrame(false);
    }

    public float getBearing() throws RemoteException {
        return this.g;
    }

    public void setTransparency(float f) throws RemoteException {
        boolean z = f >= 0.0f && f <= 1.0f;
        cu.b(z, "Transparency must be in the range [0..1]");
        this.j = f;
        this.a.setRunLowFrame(false);
    }

    public float getTransparency() throws RemoteException {
        return this.j;
    }

    public void setImage(BitmapDescriptor bitmapDescriptor) throws RemoteException {
        this.b = bitmapDescriptor;
        d();
        if (this.q) {
            this.q = false;
        }
        this.a.setRunLowFrame(false);
    }

    public void setAnchor(float f, float f2) throws RemoteException {
        this.k = f;
        this.l = f2;
        this.a.setRunLowFrame(false);
    }

    public void reLoadTexture() {
        this.q = false;
        this.p = 0;
    }

    public boolean isDrawFinish() {
        return this.r;
    }
}
