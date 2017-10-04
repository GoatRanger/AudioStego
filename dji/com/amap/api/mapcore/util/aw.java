package com.amap.api.mapcore.util;

import android.content.Context;
import android.view.View;
import com.amap.api.maps.model.TileOverlayOptions;
import com.amap.api.maps.model.UrlTileProvider;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.ITileOverlayDelegate;
import java.io.Serializable;
import java.net.URL;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

public class aw extends View {
    CopyOnWriteArrayList<ITileOverlayDelegate> a = new CopyOnWriteArrayList();
    a b = new a();
    CopyOnWriteArrayList<Integer> c = new CopyOnWriteArrayList();
    av d = null;
    private IAMapDelegate e;

    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) obj;
            ITileOverlayDelegate iTileOverlayDelegate2 = (ITileOverlayDelegate) obj2;
            if (!(iTileOverlayDelegate == null || iTileOverlayDelegate2 == null)) {
                try {
                    if (iTileOverlayDelegate.getZIndex() > iTileOverlayDelegate2.getZIndex()) {
                        return 1;
                    }
                    if (iTileOverlayDelegate.getZIndex() < iTileOverlayDelegate2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ee.a(th, "TileOverlayView", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    public aw(Context context) {
        super(context);
    }

    public aw(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.e = iAMapDelegate;
        this.d = new av(new TileOverlayOptions().tileProvider(new UrlTileProvider(this, 256, 256) {
            final /* synthetic */ aw a;

            public URL getTileUrl(int i, int i2, int i3) {
                try {
                    return new URL(String.format("http://grid.amap.com/grid/%d/%d/%d?dpiType=webrd&lang=zh_cn&pack=%s&version=3.3.2", new Object[]{Integer.valueOf(i3), Integer.valueOf(i), Integer.valueOf(i2), r.c}));
                } catch (Throwable th) {
                    return null;
                }
            }
        }), this, true);
    }

    IAMapDelegate a() {
        return this.e;
    }

    public void a(GL10 gl10) {
        try {
            Iterator it = this.c.iterator();
            while (it.hasNext()) {
                dj.a(gl10, ((Integer) it.next()).intValue());
            }
            this.c.clear();
            this.d.drawTiles(gl10);
            it = this.a.iterator();
            while (it.hasNext()) {
                ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
                if (iTileOverlayDelegate.isVisible()) {
                    iTileOverlayDelegate.drawTiles(gl10);
                }
            }
        } catch (Throwable th) {
        }
    }

    public void b() {
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.remove();
            }
        }
        this.a.clear();
    }

    void c() {
        Object[] toArray = this.a.toArray();
        Arrays.sort(toArray, this.b);
        this.a.clear();
        for (Object obj : toArray) {
            this.a.add((ITileOverlayDelegate) obj);
        }
    }

    public void a(ITileOverlayDelegate iTileOverlayDelegate) {
        b(iTileOverlayDelegate);
        this.a.add(iTileOverlayDelegate);
        c();
    }

    public boolean b(ITileOverlayDelegate iTileOverlayDelegate) {
        return this.a.remove(iTileOverlayDelegate);
    }

    public void a(boolean z) {
        this.d.refresh(z);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null && iTileOverlayDelegate.isVisible()) {
                iTileOverlayDelegate.refresh(z);
            }
        }
    }

    public void d() {
        this.d.onResume();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.onResume();
            }
        }
    }

    public void b(boolean z) {
        this.d.onFling(z);
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.onFling(z);
            }
        }
    }

    public void e() {
        this.d.remove();
        this.d = null;
    }

    public void f() {
        this.d.reLoadTexture();
        Iterator it = this.a.iterator();
        while (it.hasNext()) {
            ITileOverlayDelegate iTileOverlayDelegate = (ITileOverlayDelegate) it.next();
            if (iTileOverlayDelegate != null) {
                iTileOverlayDelegate.reLoadTexture();
            }
        }
    }
}
