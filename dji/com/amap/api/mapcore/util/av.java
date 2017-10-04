package com.amap.api.mapcore.util;

import android.graphics.Bitmap;
import com.amap.api.mapcore.util.cv.d;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.TileProvider;
import com.autonavi.amap.mapcore.FPoint;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.MapProjection;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

public class av implements ITileOverlayDelegate {
    private static int g = 0;
    private aw a;
    private TileProvider b;
    private Float c;
    private boolean d;
    private boolean e;
    private IAMapDelegate f;
    private int h;
    private int i;
    private int j;
    private db k;
    private CopyOnWriteArrayList<a> l;
    private boolean m;
    private b n;
    private String o;
    private FloatBuffer p;

    public class a implements Cloneable {
        public int a;
        public int b;
        public int c;
        public int d;
        public IPoint e;
        public int f = 0;
        public boolean g = false;
        public FloatBuffer h = null;
        public Bitmap i = null;
        public com.amap.api.mapcore.util.dd.a j = null;
        public int k = 0;
        final /* synthetic */ av l;

        public /* synthetic */ Object clone() throws CloneNotSupportedException {
            return a();
        }

        public a(av avVar, int i, int i2, int i3, int i4) {
            this.l = avVar;
            this.a = i;
            this.b = i2;
            this.c = i3;
            this.d = i4;
        }

        public a(av avVar, a aVar) {
            this.l = avVar;
            this.a = aVar.a;
            this.b = aVar.b;
            this.c = aVar.c;
            this.d = aVar.d;
            this.e = aVar.e;
            this.h = aVar.h;
        }

        public a a() {
            try {
                a aVar = (a) super.clone();
                aVar.a = this.a;
                aVar.b = this.b;
                aVar.c = this.c;
                aVar.d = this.d;
                aVar.e = (IPoint) this.e.clone();
                aVar.h = this.h.asReadOnlyBuffer();
            } catch (CloneNotSupportedException e) {
                e.printStackTrace();
            }
            return new a(this.l, this);
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            if (this.a == aVar.a && this.b == aVar.b && this.c == aVar.c && this.d == aVar.d) {
                return true;
            }
            return false;
        }

        public int hashCode() {
            return (((this.a * 7) + (this.b * 11)) + (this.c * 13)) + this.d;
        }

        public String toString() {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(this.a);
            stringBuilder.append("-");
            stringBuilder.append(this.b);
            stringBuilder.append("-");
            stringBuilder.append(this.c);
            stringBuilder.append("-");
            stringBuilder.append(this.d);
            return stringBuilder.toString();
        }

        public void a(Bitmap bitmap) {
            if (bitmap != null && !bitmap.isRecycled()) {
                try {
                    this.j = null;
                    this.i = dj.a(bitmap, dj.a(bitmap.getWidth()), dj.a(bitmap.getHeight()));
                    this.l.f.setRunLowFrame(false);
                } catch (Throwable th) {
                    ee.a(th, "TileOverlayDelegateImp", "setBitmap");
                    th.printStackTrace();
                    if (this.k < 3) {
                        this.l.k.a(true, this);
                        this.k++;
                    }
                }
            } else if (this.k < 3) {
                this.l.k.a(true, this);
                this.k++;
            }
            if (bitmap != null && !bitmap.isRecycled()) {
                bitmap.recycle();
            }
        }

        public void b() {
            try {
                dd.a(this);
                if (this.g) {
                    this.l.a.c.add(Integer.valueOf(this.f));
                }
                this.g = false;
                this.f = 0;
                if (!(this.i == null || this.i.isRecycled())) {
                    this.i.recycle();
                }
                this.i = null;
                if (this.h != null) {
                    this.h.clear();
                }
                this.h = null;
                this.j = null;
            } catch (Throwable th) {
                th.printStackTrace();
            }
        }
    }

    private class b extends cv<IAMapDelegate, Void, List<a>> {
        final /* synthetic */ av a;
        private int e;
        private boolean f;

        public b(av avVar, boolean z) {
            this.a = avVar;
            this.f = z;
        }

        protected List<a> a(IAMapDelegate... iAMapDelegateArr) {
            List<a> list = null;
            try {
                int mapWidth = iAMapDelegateArr[0].getMapWidth();
                int mapHeight = iAMapDelegateArr[0].getMapHeight();
                this.e = (int) iAMapDelegateArr[0].getZoomLevel();
                if (mapWidth > 0 && mapHeight > 0) {
                    list = this.a.a(this.e, mapWidth, mapHeight);
                }
            } catch (Throwable th) {
                th.printStackTrace();
            }
            return list;
        }

        protected void a(List<a> list) {
            if (list != null) {
                try {
                    if (list.size() > 0) {
                        this.a.a((List) list, this.e, this.f);
                        list.clear();
                    }
                } catch (Throwable th) {
                    th.printStackTrace();
                }
            }
        }
    }

    private static String a(String str) {
        g++;
        return str + g;
    }

    public av(TileOverlayOptions tileOverlayOptions, aw awVar) {
        this.e = false;
        this.h = 256;
        this.i = 256;
        this.j = -1;
        this.l = new CopyOnWriteArrayList();
        this.m = false;
        this.n = null;
        this.o = null;
        this.p = null;
        this.a = awVar;
        this.b = tileOverlayOptions.getTileProvider();
        this.h = this.b.getTileWidth();
        this.i = this.b.getTileHeight();
        int a = dj.a(this.h);
        float f = ((float) this.h) / ((float) a);
        float a2 = ((float) this.i) / ((float) dj.a(this.i));
        this.p = dj.a(new float[]{0.0f, a2, f, a2, f, 0.0f, 0.0f, 0.0f});
        this.c = Float.valueOf(tileOverlayOptions.getZIndex());
        this.d = tileOverlayOptions.isVisible();
        this.o = getId();
        this.f = this.a.a();
        this.j = Integer.valueOf(this.o.substring("TileOverlay".length())).intValue();
        com.amap.api.mapcore.util.da.a aVar = new com.amap.api.mapcore.util.da.a(this.a.getContext(), this.o);
        aVar.a(tileOverlayOptions.getMemoryCacheEnabled());
        aVar.b(tileOverlayOptions.getDiskCacheEnabled());
        aVar.a(tileOverlayOptions.getMemCacheSize());
        aVar.b(tileOverlayOptions.getDiskCacheSize());
        String diskCacheDir = tileOverlayOptions.getDiskCacheDir();
        if (!(diskCacheDir == null || diskCacheDir.equals(""))) {
            aVar.a(diskCacheDir);
        }
        this.k = new db(this.a.getContext(), this.h, this.i);
        this.k.a(this.b);
        this.k.a(aVar);
        refresh(true);
    }

    public av(TileOverlayOptions tileOverlayOptions, aw awVar, boolean z) {
        this(tileOverlayOptions, awVar);
        this.e = z;
    }

    public void remove() {
        if (this.n != null && this.n.a() == d.RUNNING) {
            this.n.a(true);
        }
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            ((a) it.next()).b();
        }
        this.l.clear();
        this.k.h();
        this.a.b((ITileOverlayDelegate) this);
        this.f.setRunLowFrame(false);
    }

    public void clearTileCache() {
        this.k.f();
    }

    public String getId() {
        if (this.o == null) {
            this.o = a("TileOverlay");
        }
        return this.o;
    }

    public void setZIndex(float f) {
        this.c = Float.valueOf(f);
        this.a.c();
    }

    public float getZIndex() {
        return this.c.floatValue();
    }

    public void setVisible(boolean z) {
        this.d = z;
        this.f.setRunLowFrame(false);
        if (z) {
            refresh(true);
        }
    }

    public boolean isVisible() {
        return this.d;
    }

    public boolean equalsRemote(ITileOverlayDelegate iTileOverlayDelegate) {
        if (equals(iTileOverlayDelegate) || iTileOverlayDelegate.getId().equals(getId())) {
            return true;
        }
        return false;
    }

    public int hashCodeRemote() {
        return super.hashCode();
    }

    private boolean a(a aVar) {
        MapProjection mapProjection = this.f.getMapProjection();
        float f = (float) aVar.c;
        int i = this.h;
        int i2 = this.i;
        int i3 = aVar.e.x;
        int i4 = aVar.e.y + ((1 << (20 - ((int) f))) * i2);
        r6 = new float[12];
        FPoint fPoint = new FPoint();
        mapProjection.geo2Map(i3, i4, fPoint);
        FPoint fPoint2 = new FPoint();
        mapProjection.geo2Map(((1 << (20 - ((int) f))) * i) + i3, i4, fPoint2);
        FPoint fPoint3 = new FPoint();
        mapProjection.geo2Map((i * (1 << (20 - ((int) f)))) + i3, i4 - ((1 << (20 - ((int) f))) * i2), fPoint3);
        FPoint fPoint4 = new FPoint();
        mapProjection.geo2Map(i3, i4 - ((1 << (20 - ((int) f))) * i2), fPoint4);
        r6[0] = fPoint.x;
        r6[1] = fPoint.y;
        r6[2] = 0.0f;
        r6[3] = fPoint2.x;
        r6[4] = fPoint2.y;
        r6[5] = 0.0f;
        r6[6] = fPoint3.x;
        r6[7] = fPoint3.y;
        r6[8] = 0.0f;
        r6[9] = fPoint4.x;
        r6[10] = fPoint4.y;
        r6[11] = 0.0f;
        if (aVar.h == null) {
            aVar.h = dj.a(r6);
        } else {
            aVar.h = dj.a(r6, aVar.h);
        }
        return true;
    }

    private void a(GL10 gl10, int i, FloatBuffer floatBuffer, FloatBuffer floatBuffer2) {
        if (floatBuffer != null && floatBuffer2 != null) {
            gl10.glEnable(3042);
            gl10.glTexEnvf(8960, 8704, 8448.0f);
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

    public void drawTiles(GL10 gl10) {
        if (this.l != null && this.l.size() != 0) {
            Iterator it = this.l.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                if (!aVar.g) {
                    try {
                        IPoint iPoint = aVar.e;
                        if (!(aVar.i == null || aVar.i.isRecycled() || iPoint == null)) {
                            aVar.f = dj.a(gl10, aVar.i);
                            if (aVar.f != 0) {
                                aVar.g = true;
                            }
                            aVar.i = null;
                        }
                    } catch (Throwable th) {
                        ee.a(th, "TileOverlayDelegateImp", "drawTiles");
                    }
                }
                if (aVar.g) {
                    a(aVar);
                    a(gl10, aVar.f, aVar.h, this.p);
                }
            }
        }
    }

    private ArrayList<a> a(int i, int i2, int i3) {
        MapProjection mapProjection = this.f.getMapProjection();
        FPoint fPoint = new FPoint();
        IPoint iPoint = new IPoint();
        IPoint iPoint2 = new IPoint();
        mapProjection.win2Map(0, 0, fPoint);
        mapProjection.map2Geo(fPoint.x, fPoint.y, iPoint);
        int min = Math.min(Integer.MAX_VALUE, iPoint.x);
        int max = Math.max(0, iPoint.x);
        int min2 = Math.min(Integer.MAX_VALUE, iPoint.y);
        int max2 = Math.max(0, iPoint.y);
        mapProjection.win2Map(i2, 0, fPoint);
        mapProjection.map2Geo(fPoint.x, fPoint.y, iPoint);
        min = Math.min(min, iPoint.x);
        max = Math.max(max, iPoint.x);
        min2 = Math.min(min2, iPoint.y);
        max2 = Math.max(max2, iPoint.y);
        mapProjection.win2Map(0, i3, fPoint);
        mapProjection.map2Geo(fPoint.x, fPoint.y, iPoint);
        min = Math.min(min, iPoint.x);
        max = Math.max(max, iPoint.x);
        min2 = Math.min(min2, iPoint.y);
        max2 = Math.max(max2, iPoint.y);
        mapProjection.win2Map(i2, i3, fPoint);
        mapProjection.map2Geo(fPoint.x, fPoint.y, iPoint);
        min = Math.min(min, iPoint.x);
        int max3 = Math.max(max, iPoint.x);
        max = Math.min(min2, iPoint.y);
        int max4 = Math.max(max2, iPoint.y);
        int i4 = min - ((1 << (20 - i)) * this.h);
        int i5 = max - ((1 << (20 - i)) * this.i);
        mapProjection.getGeoCenter(iPoint2);
        max = (iPoint2.x >> (20 - i)) / this.h;
        min2 = (iPoint2.y >> (20 - i)) / this.i;
        int i6 = (max << (20 - i)) * this.h;
        int i7 = (min2 << (20 - i)) * this.i;
        a aVar = new a(this, max, min2, i, this.j);
        aVar.e = new IPoint(i6, i7);
        ArrayList<a> arrayList = new ArrayList();
        arrayList.add(aVar);
        min = 1;
        while (true) {
            Object obj = null;
            for (i6 = max - min; i6 <= max + min; i6++) {
                i7 = min2 + min;
                IPoint iPoint3 = new IPoint((i6 << (20 - i)) * this.h, (i7 << (20 - i)) * this.i);
                if (iPoint3.x < max3 && iPoint3.x > i4 && iPoint3.y < max4 && iPoint3.y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    a aVar2 = new a(this, i6, i7, i, this.j);
                    aVar2.e = iPoint3;
                    arrayList.add(aVar2);
                }
                i7 = min2 - min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.h, (i7 << (20 - i)) * this.i);
                if (iPoint3.x < max3 && iPoint3.x > i4 && iPoint3.y < max4 && iPoint3.y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    aVar2 = new a(this, i6, i7, i, this.j);
                    aVar2.e = iPoint3;
                    arrayList.add(aVar2);
                }
            }
            for (i7 = (min2 + min) - 1; i7 > min2 - min; i7--) {
                i6 = max + min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.h, (i7 << (20 - i)) * this.i);
                if (iPoint3.x < max3 && iPoint3.x > i4 && iPoint3.y < max4 && iPoint3.y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    aVar2 = new a(this, i6, i7, i, this.j);
                    aVar2.e = iPoint3;
                    arrayList.add(aVar2);
                }
                i6 = max - min;
                iPoint3 = new IPoint((i6 << (20 - i)) * this.h, (i7 << (20 - i)) * this.i);
                if (iPoint3.x < max3 && iPoint3.x > i4 && iPoint3.y < max4 && iPoint3.y > i5) {
                    if (obj == null) {
                        obj = 1;
                    }
                    aVar2 = new a(this, i6, i7, i, this.j);
                    aVar2.e = iPoint3;
                    arrayList.add(aVar2);
                }
            }
            if (obj == null) {
                return arrayList;
            }
            min++;
        }
    }

    private boolean a(List<a> list, int i, boolean z) {
        int i2 = 0;
        if (list == null) {
            return false;
        }
        if (this.l == null) {
            return false;
        }
        int i3;
        Iterator it = this.l.iterator();
        while (it.hasNext()) {
            a aVar = (a) it.next();
            for (a aVar2 : list) {
                if (aVar.equals(aVar2) && aVar.g) {
                    aVar2.g = aVar.g;
                    aVar2.f = aVar.f;
                    i3 = 1;
                    break;
                }
            }
            i3 = 0;
            if (i3 == 0) {
                aVar.b();
            }
        }
        this.l.clear();
        if (i > ((int) this.f.getMaxZoomLevel()) || i < ((int) this.f.getMinZoomLevel())) {
            return false;
        }
        i3 = list.size();
        if (i3 <= 0) {
            return false;
        }
        while (i2 < i3) {
            aVar = (a) list.get(i2);
            if (aVar != null && (!this.e || (aVar.c >= 10 && !dg.a(aVar.a, aVar.b, aVar.c)))) {
                this.l.add(aVar);
                if (!aVar.g) {
                    this.k.a(z, aVar);
                }
            }
            i2++;
        }
        return true;
    }

    public void refresh(boolean z) {
        if (!this.m) {
            if (this.n != null && this.n.a() == d.RUNNING) {
                this.n.a(true);
            }
            this.n = new b(this, z);
            this.n.c((Object[]) new IAMapDelegate[]{this.f});
        }
    }

    public void onPause() {
        this.k.b(false);
        this.k.a(true);
        this.k.g();
    }

    public void onResume() {
        this.k.a(false);
    }

    public void onFling(boolean z) {
        if (this.m != z) {
            this.m = z;
            this.k.b(z);
        }
    }

    public void reLoadTexture() {
        if (this.l != null && this.l.size() != 0) {
            Iterator it = this.l.iterator();
            while (it.hasNext()) {
                a aVar = (a) it.next();
                aVar.g = false;
                aVar.f = 0;
            }
        }
    }
}
