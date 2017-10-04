package com.amap.api.mapcore.util;

import android.util.Log;
import com.autonavi.amap.mapcore.interfaces.GLOverlay;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import javax.microedition.khronos.opengles.GL10;

class s {
    a a = new a();
    private CopyOnWriteArrayList<GLOverlay> b = new CopyOnWriteArrayList();

    static class a implements Serializable, Comparator<Object> {
        a() {
        }

        public int compare(Object obj, Object obj2) {
            GLOverlay gLOverlay = (GLOverlay) obj;
            GLOverlay gLOverlay2 = (GLOverlay) obj2;
            if (!(gLOverlay == null || gLOverlay2 == null)) {
                try {
                    if (gLOverlay.getZIndex() > gLOverlay2.getZIndex()) {
                        return 1;
                    }
                    if (gLOverlay.getZIndex() < gLOverlay2.getZIndex()) {
                        return -1;
                    }
                } catch (Throwable th) {
                    ee.a(th, "CustomGLOverlayLayer", "compare");
                    th.printStackTrace();
                }
            }
            return 0;
        }
    }

    s() {
    }

    public void a() {
        try {
            this.b.clear();
        } catch (Throwable th) {
            ee.a(th, "CustomGLOverlayLayer", "clear");
            th.printStackTrace();
            Log.d("amapApi", "GLOverlayLayer clear erro" + th.getMessage());
        }
    }

    public void a(GLOverlay gLOverlay) {
        b(gLOverlay);
        this.b.add(gLOverlay);
        b();
    }

    public boolean b(GLOverlay gLOverlay) {
        if (this.b.contains(gLOverlay)) {
            return this.b.remove(gLOverlay);
        }
        return false;
    }

    private void b() {
        Object[] toArray = this.b.toArray();
        Arrays.sort(toArray, this.a);
        this.b.clear();
        for (Object obj : toArray) {
            this.b.add((GLOverlay) obj);
        }
    }

    public void a(GL10 gl10) {
        Iterator it = this.b.iterator();
        while (it.hasNext()) {
            ((GLOverlay) it.next()).onDrawFrame(gl10);
        }
    }
}
