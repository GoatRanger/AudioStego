package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.view.MotionEvent;
import com.here.android.mpa.common.ViewObject;
import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.mapping.MapMarker;
import com.here.android.mpa.mapping.MapObject;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class cf {
    private static final String a = cf.class.getSimpleName();
    private MapMarker b;
    private final MapImpl c;
    private List<cg> d = new ArrayList();

    cf(MapImpl mapImpl, Context context) {
        this.c = mapImpl;
    }

    public boolean a(PointF pointF) {
        for (ViewObject viewObject : this.c.a(this.c.e(pointF))) {
            if (viewObject.getBaseType() == Type.USER_OBJECT) {
                MapObject mapObject = (MapObject) viewObject;
                if (mapObject instanceof MapMarker) {
                    Iterator it = MapMarkerImpl.a.iterator();
                    while (it.hasNext()) {
                        MapMarker mapMarker = (MapMarker) it.next();
                        if (mapMarker.equals(mapObject)) {
                            this.b = mapMarker;
                            b();
                            return true;
                        }
                    }
                    continue;
                } else {
                    continue;
                }
            }
        }
        return false;
    }

    public boolean a(MotionEvent motionEvent) {
        if (motionEvent.getPointerCount() > 2) {
            return false;
        }
        if (motionEvent.getAction() == 2) {
            return b(motionEvent);
        }
        if (motionEvent.getAction() == 1) {
            return c(motionEvent);
        }
        if (motionEvent.getAction() == 3) {
            return d(motionEvent);
        }
        bj.e(a, "Unhandled event", new Object[0]);
        return false;
    }

    private boolean b(MotionEvent motionEvent) {
        e(motionEvent);
        return true;
    }

    private boolean c(MotionEvent motionEvent) {
        f(motionEvent);
        return true;
    }

    private boolean d(MotionEvent motionEvent) {
        return c(motionEvent);
    }

    public void a(cg cgVar) {
        if (cgVar != null) {
            synchronized (this.d) {
                if (!this.d.contains(cgVar)) {
                    this.d.add(cgVar);
                }
            }
        }
    }

    public void b(cg cgVar) {
        if (cgVar != null) {
            synchronized (this.d) {
                this.d.remove(cgVar);
            }
        }
    }

    private void e(MotionEvent motionEvent) {
        c(new PointF(motionEvent.getX(), motionEvent.getY()));
    }

    private void f(MotionEvent motionEvent) {
        b(new PointF(motionEvent.getX(), motionEvent.getY()));
        this.b = null;
    }

    public void a() {
        this.b = null;
    }

    private void b(PointF pointF) {
        for (cg a : this.d) {
            a.a(this.b, pointF);
        }
    }

    private void b() {
        for (cg a : this.d) {
            a.a(this.b);
        }
    }

    private void c(PointF pointF) {
        for (cg b : this.d) {
            b.b(this.b, pointF);
        }
    }
}
