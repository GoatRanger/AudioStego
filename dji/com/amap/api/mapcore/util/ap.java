package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.MarkerOptions;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import dji.pilot.visual.a.d;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

class ap implements IMarkerDelegate {
    private boolean a = false;
    private int b = 0;
    private int c = 0;
    private FloatBuffer d = null;
    private String e;
    private FPoint f;
    private BitmapDescriptor g;
    private boolean h = true;
    private FloatBuffer i;
    private Object j;
    private int k;
    private IAMapDelegate l = null;
    private MapProjection m = null;
    private float n = d.c;
    private float o = 1.0f;
    private boolean p;
    private boolean q = false;
    private boolean r = true;
    private int s = 20;

    public boolean isDestory() {
        return this.a;
    }

    public void realDestroy() {
        if (this.a) {
            try {
                remove();
                if (this.g != null) {
                    Bitmap bitmap = this.g.getBitmap();
                    if (bitmap != null) {
                        bitmap.recycle();
                        this.g = null;
                    }
                }
                if (this.i != null) {
                    this.i.clear();
                    this.i = null;
                }
                if (this.d != null) {
                    this.d.clear();
                    this.d = null;
                }
                this.f = null;
                this.j = null;
                this.k = 0;
            } catch (Throwable th) {
                ee.a(th, "PopupOverlay", "realDestroy");
                th.printStackTrace();
                Log.d("destroy erro", "MarkerDelegateImp destroy");
            }
        }
    }

    private void a(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.k = 0;
            this.g = bitmapDescriptor;
        }
    }

    public ap(MarkerOptions markerOptions, IAMapDelegate iAMapDelegate) {
        this.l = iAMapDelegate;
        this.m = iAMapDelegate.getMapProjection();
        a(markerOptions.getIcon());
        this.b = markerOptions.getInfoWindowOffsetX();
        this.c = markerOptions.getInfoWindowOffsetY();
        this.h = markerOptions.isVisible();
        this.e = getId();
        calFPoint();
    }

    public IPoint b() {
        if (this.f == null) {
            return null;
        }
        IPoint iPoint = new IPoint();
        this.l.getMapProjection().map2Win(this.f.x, this.f.y, iPoint);
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
        if (this.g != null) {
            fPoint.x = ((float) getWidth()) * this.n;
            fPoint.y = ((float) getHeight()) * this.o;
        }
        return fPoint;
    }

    public boolean isContains() {
        return false;
    }

    public IPoint getAnchor() {
        IPoint b = b();
        if (b == null) {
            return null;
        }
        return b;
    }

    public Rect getRect() {
        return null;
    }

    public boolean remove() {
        c();
        if (this.k != 0) {
            this.l.deleteTexsureId(this.k);
        }
        return true;
    }

    private void c() {
        if (this.l != null) {
            this.l.setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        return null;
    }

    public String getId() {
        if (this.e == null) {
            this.e = "PopupOverlay";
        }
        return this.e;
    }

    public void a(FPoint fPoint) {
        if (fPoint == null || !fPoint.equals(this.f)) {
            this.f = fPoint;
        }
    }

    public void setPosition(LatLng latLng) {
    }

    public void setTitle(String str) {
    }

    public String getTitle() {
        return null;
    }

    public void setSnippet(String str) {
    }

    public String getSnippet() {
        return null;
    }

    public void setDraggable(boolean z) {
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        return null;
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            this.g = bitmapDescriptor;
            this.q = false;
            if (this.i != null) {
                this.i.clear();
                this.i = null;
            }
            c();
        }
    }

    public BitmapDescriptor getBitmapDescriptor() {
        return this.g;
    }

    public boolean isDraggable() {
        return false;
    }

    public void showInfoWindow() {
    }

    public void hideInfoWindow() {
    }

    public boolean isInfoWindowShown() {
        return false;
    }

    public void setVisible(boolean z) {
        if (!this.h && z) {
            this.p = true;
        }
        this.h = z;
    }

    public boolean isVisible() {
        return this.h;
    }

    public void setAnchor(float f, float f2) {
        if (this.n != f || this.o != f2) {
            this.n = f;
            this.o = f2;
        }
    }

    public float getAnchorU() {
        return this.n;
    }

    public float getAnchorV() {
        return this.o;
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
        if (this.f == null) {
            return false;
        }
        IPoint iPoint = new IPoint();
        this.l.getMapProjection().map2Win(this.f.x, this.f.y, iPoint);
        int width = getWidth();
        int height = getHeight();
        int i = (int) (((float) (iPoint.x + this.b)) - (((float) width) * this.n));
        int i2 = (int) (((float) (iPoint.y + this.c)) + (((float) height) * (1.0f - this.o)));
        if (i - width > this.l.getMapWidth() || i < (-width) * 2 || i2 < (-height) * 2 || i2 - height > this.l.getMapHeight() || this.g == null) {
            return false;
        }
        width = this.g.getWidth();
        float width2 = ((float) width) / ((float) this.g.getBitmap().getWidth());
        float height2 = ((float) this.g.getHeight()) / ((float) this.g.getBitmap().getHeight());
        if (this.i == null) {
            this.i = dj.a(new float[]{0.0f, height2, width2, height2, width2, 0.0f, 0.0f, 0.0f});
        }
        float[] fArr = new float[]{(float) i, (float) (this.l.getMapHeight() - i2), 0.0f, (float) (i + width), (float) (this.l.getMapHeight() - i2), 0.0f, (float) (width + i), (float) ((this.l.getMapHeight() - i2) + height), 0.0f, (float) i, (float) ((this.l.getMapHeight() - i2) + height), 0.0f};
        if (this.d == null) {
            this.d = dj.a(fArr);
        } else {
            this.d = dj.a(fArr, this.d);
        }
        return true;
    }

    private void a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glBlendFunc(1, 771);
            gl10.glColor4f(1.0f, 1.0f, 1.0f, 1.0f);
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

    public void a(GL10 gl10) {
        if (this.h && this.f != null && getBitmapDescriptor() != null) {
            if (!this.q) {
                try {
                    if (this.k != 0) {
                        gl10.glDeleteTextures(1, new int[]{this.k}, 0);
                        this.l.deleteTexsureId(this.k);
                    }
                    this.k = b(gl10);
                    if (this.g != null) {
                        Bitmap bitmap = this.g.getBitmap();
                        if (!(bitmap == null || bitmap.isRecycled())) {
                            dj.b(gl10, this.k, bitmap, false);
                        }
                        this.q = true;
                    }
                } catch (Throwable th) {
                    ee.a(th, "PopupOverlay", "drawMarker");
                    th.printStackTrace();
                    return;
                }
            }
            if (calFPoint()) {
                gl10.glLoadIdentity();
                gl10.glViewport(0, 0, this.l.getMapWidth(), this.l.getMapHeight());
                gl10.glMatrixMode(5889);
                gl10.glLoadIdentity();
                gl10.glOrthof(0.0f, (float) this.l.getMapWidth(), 0.0f, (float) this.l.getMapHeight(), 1.0f, -1.0f);
                a(gl10, this.k, this.d, this.i);
                if (this.p) {
                    a();
                    this.p = false;
                }
            }
        }
    }

    public void a() {
    }

    private int b(GL10 gl10) {
        int texsureId = this.l.getTexsureId();
        if (texsureId != 0) {
            return texsureId;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean isAllowLow() {
        return this.r;
    }

    public void setPeriod(int i) {
        if (i <= 1) {
            this.s = 1;
        } else {
            this.s = i;
        }
    }

    public void setObject(Object obj) {
        this.j = obj;
    }

    public Object getObject() {
        return this.j;
    }

    public void setPerspective(boolean z) {
    }

    public boolean isPerspective() {
        return false;
    }

    public int getTextureId() {
        return this.k;
    }

    public int getPeriod() {
        return this.s;
    }

    public LatLng getRealPosition() {
        return null;
    }

    public void set2Top() {
    }

    public void setFlat(boolean z) throws RemoteException {
        c();
    }

    public boolean isFlat() {
        return false;
    }

    public void setRotateAngle(float f) throws RemoteException {
    }

    public void destroy() {
    }

    public void drawMarker(GL10 gl10, IAMapDelegate iAMapDelegate) {
    }

    public float getRotateAngle() {
        return 0.0f;
    }

    public void setInfoWindowOffset(int i, int i2) throws RemoteException {
        this.b = i;
        this.c = i2;
    }

    public int getInfoWindowOffsetX() {
        return this.b;
    }

    public int getInfoWindowOffsetY() {
        return this.c;
    }

    public void setPositionByPixels(int i, int i2) {
    }

    public int getRealInfoWindowOffsetX() {
        return 0;
    }

    public int getRealInfoWindowOffsetY() {
        return 0;
    }

    public FPoint getMapPosition() {
        return this.f;
    }

    public boolean isViewMode() {
        return false;
    }

    public void setZIndex(float f) {
    }

    public float getZIndex() {
        return 0.0f;
    }

    public boolean checkInBounds() {
        return false;
    }

    public void setInfoWindowShown(boolean z) {
    }

    public void setGeoPoint(IPoint iPoint) {
    }

    public IPoint getGeoPoint() {
        return null;
    }

    public void reLoadTexture() {
        this.q = false;
        this.k = 0;
    }
}
