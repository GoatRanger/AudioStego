package com.nokia.maps;

import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.streetlevel.StreetLevelObject;
import com.nokia.maps.annotation.HybridPlus;
import java.lang.ref.WeakReference;

@HybridPlus
public class cx extends ViewObjectImpl {
    private static k<StreetLevelObject, cx> a = null;
    private cq b = new cq(cx.class.getName());
    private Object c = new Object();
    private WeakReference<PanoramaModelImpl> d = new WeakReference(null);

    static cx a(StreetLevelObject streetLevelObject) {
        if (a != null) {
            return (cx) a.a(streetLevelObject);
        }
        return null;
    }

    public static void a(k<StreetLevelObject, cx> kVar) {
        a = kVar;
    }

    protected cx() {
    }

    protected cx(int i) {
        super(i);
    }

    public Type k() {
        return Type.USER_OBJECT;
    }

    public StreetLevelObject.Type a() {
        return StreetLevelObject.Type.UNKNOWN;
    }

    public void a(PanoramaModelImpl panoramaModelImpl) {
        synchronized (this.c) {
            this.d = new WeakReference(panoramaModelImpl);
        }
    }

    protected void h() {
        PanoramaModelImpl i = i();
        if (i != null) {
            i.onRedraw();
        }
    }

    protected PanoramaModelImpl i() {
        PanoramaModelImpl panoramaModelImpl;
        synchronized (this.c) {
            panoramaModelImpl = (PanoramaModelImpl) this.d.get();
        }
        return panoramaModelImpl;
    }
}
