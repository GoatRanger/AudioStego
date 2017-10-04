package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Paint.Cap;
import android.graphics.Paint.FontMetrics;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.opengl.GLES10;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import com.amap.api.maps.model.BitmapDescriptor;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.TextOptions;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import com.autonavi.amap.mapcore.interfaces.ITextDelegate;
import dji.pilot.visual.a.d;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import javax.microedition.khronos.opengles.GL10;

class at implements ITextDelegate {
    private static int a = 0;
    private Paint A = new Paint();
    private Handler B = new Handler();
    private Runnable C = new au(this);
    private boolean D = false;
    private boolean E = false;
    private float b = 0.0f;
    private float c = 0.0f;
    private int d = 4;
    private int e = 32;
    private FPoint f = new FPoint();
    private int g;
    private Bitmap h;
    private int i;
    private int j;
    private FloatBuffer k = null;
    private String l;
    private LatLng m;
    private float n = d.c;
    private float o = 1.0f;
    private boolean p = true;
    private ae q;
    private FloatBuffer r;
    private Object s;
    private String t;
    private int u;
    private int v;
    private int w;
    private Typeface x;
    private float y;
    private Rect z = new Rect();

    private static String a(String str) {
        a++;
        return str + a;
    }

    public void setRotateAngle(float f) {
        this.c = f;
        this.b = (((-f) % 360.0f) + 360.0f) % 360.0f;
        d();
    }

    public boolean isDestory() {
        return this.D;
    }

    public synchronized void realDestroy() {
        if (this.D) {
            try {
                remove();
                if (this.h != null) {
                    this.h.recycle();
                    this.h = null;
                }
                if (this.r != null) {
                    this.r.clear();
                    this.r = null;
                }
                if (this.k != null) {
                    this.k.clear();
                    this.k = null;
                }
                this.m = null;
                this.s = null;
            } catch (Throwable th) {
                ee.a(th, "TextDelegateImp", "realdestroy");
                th.printStackTrace();
                Log.d("destroy erro", "TextDelegateImp destroy");
            }
        }
    }

    public void destroy() {
        try {
            this.D = true;
            if (!(this.q == null || this.q.a == null)) {
                this.q.a.callRunDestroy();
            }
            this.g = 0;
        } catch (Throwable th) {
            ee.a(th, "TextDelegateImp", "destroy");
            th.printStackTrace();
            Log.d("destroy erro", "TextDelegateImp destroy");
        }
    }

    public at(TextOptions textOptions, ae aeVar) throws RemoteException {
        this.q = aeVar;
        if (textOptions.getPosition() != null) {
            this.m = textOptions.getPosition();
        }
        setAlign(textOptions.getAlignX(), textOptions.getAlignY());
        this.p = textOptions.isVisible();
        this.t = textOptions.getText();
        this.u = textOptions.getBackgroundColor();
        this.v = textOptions.getFontColor();
        this.w = textOptions.getFontSize();
        this.s = textOptions.getObject();
        this.y = textOptions.getZIndex();
        this.x = textOptions.getTypeface();
        this.l = getId();
        setRotateAngle(textOptions.getRotate());
        a();
        calFPoint();
    }

    private void a() {
        if (this.t != null && this.t.trim().length() > 0) {
            try {
                this.A.setTypeface(this.x);
                this.A.setSubpixelText(true);
                this.A.setAntiAlias(true);
                this.A.setStrokeWidth(5.0f);
                this.A.setStrokeCap(Cap.ROUND);
                this.A.setTextSize((float) this.w);
                this.A.setTextAlign(Align.CENTER);
                this.A.setColor(this.v);
                FontMetrics fontMetrics = this.A.getFontMetrics();
                int i = (int) (fontMetrics.descent - fontMetrics.ascent);
                int i2 = (int) (((((float) i) - fontMetrics.bottom) - fontMetrics.top) / 2.0f);
                this.A.getTextBounds(this.t, 0, this.t.length(), this.z);
                Bitmap createBitmap = Bitmap.createBitmap(this.z.width() + 6, i, Config.ARGB_8888);
                Canvas canvas = new Canvas(createBitmap);
                canvas.drawColor(this.u);
                canvas.drawText(this.t, (float) (this.z.centerX() + 3), (float) i2, this.A);
                this.h = createBitmap;
                this.i = this.h.getWidth();
                this.j = this.h.getHeight();
                this.r = dj.a(new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 0.0f, 0.0f, 0.0f});
            } catch (Throwable th) {
                ee.a(th, "TextDelegateImp", "initBitmap");
            }
        }
    }

    private int b() {
        return this.i;
    }

    private int c() {
        return this.j;
    }

    public FPoint anchorUVoff() {
        return null;
    }

    public boolean isContains() {
        return this.q.a((IMarkerDelegate) this);
    }

    public IPoint getAnchor() {
        return null;
    }

    public synchronized boolean remove() {
        d();
        this.p = false;
        return this.q.c(this);
    }

    private void d() {
        if (this.q.a != null) {
            this.q.a.setRunLowFrame(false);
        }
    }

    public LatLng getPosition() {
        return this.m;
    }

    public String getId() {
        if (this.l == null) {
            this.l = a("Text");
        }
        return this.l;
    }

    public void setPosition(LatLng latLng) {
        this.m = latLng;
        calFPoint();
        d();
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

    public synchronized void setIcons(ArrayList<BitmapDescriptor> arrayList) {
    }

    public synchronized ArrayList<BitmapDescriptor> getIcons() {
        return null;
    }

    public synchronized void setIcon(BitmapDescriptor bitmapDescriptor) {
    }

    public synchronized BitmapDescriptor getBitmapDescriptor() {
        return null;
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
        if (this.p != z) {
            this.p = z;
            d();
        }
    }

    public boolean isVisible() {
        return this.p;
    }

    public void setZIndex(float f) {
        this.y = f;
        this.q.i();
    }

    public float getZIndex() {
        return this.y;
    }

    public void setAnchor(float f, float f2) {
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
        if (this.m == null) {
            return false;
        }
        this.q.a.getLatLng2Map(this.m.latitude, this.m.longitude, this.f);
        return true;
    }

    private void a(IAMapDelegate iAMapDelegate) throws RemoteException {
        float[] a = dj.a(iAMapDelegate, 0, this.f, this.b, b(), c(), this.n, this.o);
        if (this.k == null) {
            this.k = dj.a(a);
        } else {
            this.k = dj.a(a, this.k);
        }
        if (this.g != 0) {
            a(this.g, this.k, this.r);
        }
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
        if (this.p && !this.D && this.m != null && this.h != null) {
            if (!this.E) {
                try {
                    if (!(this.h == null || this.h.isRecycled())) {
                        if (this.g == 0) {
                            this.g = a(gl10);
                        }
                        dj.b(gl10, this.g, this.h, false);
                        this.E = true;
                        this.h.recycle();
                    }
                } catch (Throwable th) {
                    ee.a(th, "TextDelegateImp", "loadtexture");
                    th.printStackTrace();
                    return;
                }
            }
            try {
                a(iAMapDelegate);
            } catch (Throwable th2) {
                ee.a(th2, "TextDelegateImp", "drawMarker");
            }
        }
    }

    private int a(GL10 gl10) {
        int texsureId = this.q.a.getTexsureId();
        if (texsureId != 0) {
            return texsureId;
        }
        int[] iArr = new int[]{0};
        gl10.glGenTextures(1, iArr, 0);
        return iArr[0];
    }

    public boolean isAllowLow() {
        return true;
    }

    public void setPeriod(int i) {
    }

    public void setObject(Object obj) {
        this.s = obj;
    }

    public Object getObject() {
        return this.s;
    }

    public void setPerspective(boolean z) {
    }

    public boolean isPerspective() {
        return false;
    }

    public int getTextureId() {
        try {
            return this.g;
        } catch (Throwable th) {
            return 0;
        }
    }

    public int getPeriod() {
        return 0;
    }

    public LatLng getRealPosition() {
        return this.m;
    }

    public void set2Top() {
        this.q.d(this);
    }

    public void setFlat(boolean z) throws RemoteException {
    }

    public boolean isFlat() {
        return false;
    }

    public float getRotateAngle() {
        return this.c;
    }

    public void setInfoWindowOffset(int i, int i2) throws RemoteException {
    }

    public int getInfoWindowOffsetX() {
        return 0;
    }

    public int getInfoWindowOffsetY() {
        return 0;
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

    public Rect getRect() {
        return null;
    }

    public void setText(String str) throws RemoteException {
        this.t = str;
        e();
    }

    public String getText() throws RemoteException {
        return this.t;
    }

    public void setBackgroundColor(int i) throws RemoteException {
        this.u = i;
        e();
    }

    public int getBackgroundColor() throws RemoteException {
        return this.u;
    }

    public void setFontColor(int i) throws RemoteException {
        this.v = i;
        e();
    }

    public int getFontColor() throws RemoteException {
        return this.v;
    }

    public void setFontSize(int i) throws RemoteException {
        this.w = i;
        e();
    }

    public int getFontSize() throws RemoteException {
        return this.w;
    }

    public void setTypeface(Typeface typeface) throws RemoteException {
        this.x = typeface;
        e();
    }

    public Typeface getTypeface() throws RemoteException {
        return this.x;
    }

    public void setAlign(int i, int i2) throws RemoteException {
        this.d = i;
        switch (i) {
            case 1:
                this.n = 0.0f;
                break;
            case 2:
                this.n = 1.0f;
                break;
            case 4:
                this.n = d.c;
                break;
            default:
                this.n = d.c;
                break;
        }
        this.e = i2;
        switch (i2) {
            case 8:
                this.o = 0.0f;
                break;
            case 16:
                this.o = 1.0f;
                break;
            case 32:
                this.o = d.c;
                break;
            default:
                this.o = d.c;
                break;
        }
        d();
    }

    public int getAlignX() throws RemoteException {
        return this.d;
    }

    public int getAlignY() {
        return this.e;
    }

    public int getWidth() {
        return 0;
    }

    public int getHeight() {
        return 0;
    }

    private void e() {
        this.B.removeCallbacks(this.C);
        this.B.post(this.C);
    }

    public boolean checkInBounds() {
        Rect rect = this.q.a.getRect();
        if (rect == null) {
            return true;
        }
        IPoint iPoint = new IPoint();
        if (this.m != null) {
            this.q.a.getLatLng2Pixel(this.m.latitude, this.m.longitude, iPoint);
        }
        return rect.contains(iPoint.x, iPoint.y);
    }

    public void setInfoWindowShown(boolean z) {
    }

    public void setGeoPoint(IPoint iPoint) {
    }

    public IPoint getGeoPoint() {
        return null;
    }

    public void reLoadTexture() {
        this.E = false;
        this.g = 0;
        a();
    }
}
