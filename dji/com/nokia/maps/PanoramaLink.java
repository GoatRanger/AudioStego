package com.nokia.maps;

import android.util.Log;
import com.here.android.mpa.common.ViewObject.Type;
import com.here.android.mpa.streetlevel.StreetLevel;
import com.here.android.mpa.streetlevel.StreetLevelProxyObject;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import java.lang.ref.WeakReference;

@HybridPlus
public class PanoramaLink extends ViewObjectImpl {
    PanoramaImpl a;
    WeakReference<PanoramaModelImpl> b;
    private cq c;

    static native void destroyLinkNative(int i);

    private native PanoramaImpl getPanorama();

    @HybridPlusNative
    private PanoramaLink(int i) {
        super(i);
        this.c = new cq(PanoramaLink.class.getSimpleName());
        this.a = null;
        this.a = getPanorama();
    }

    private PanoramaLink() {
        this.c = new cq(PanoramaLink.class.getSimpleName());
        this.a = null;
        this.a = getPanorama();
    }

    protected void finalize() {
        final int i = this.nativeptr;
        if (this.b != null) {
            PanoramaModelImpl panoramaModelImpl = (PanoramaModelImpl) this.b.get();
            if (panoramaModelImpl != null) {
                panoramaModelImpl.a(new Runnable(this) {
                    final /* synthetic */ PanoramaLink b;

                    public void run() {
                        PanoramaLink.destroyLinkNative(i);
                    }
                });
            }
        } else {
            Log.wtf("PanormaLink", "BAD PROGRAMMING ERROR. Make sure to setModel");
        }
        this.nativeptr = 0;
        super.finalize();
    }

    public Type k() {
        return Type.PROXY_OBJECT;
    }

    public StreetLevelProxyObject.Type a() {
        return StreetLevelProxyObject.Type.LINK_OBJECT;
    }

    public StreetLevel b() {
        return PanoramaImpl.a(this.a, (PanoramaModelImpl) this.b.get());
    }

    public void a(PanoramaModelImpl panoramaModelImpl) {
        this.b = new WeakReference(panoramaModelImpl);
        this.a.a(panoramaModelImpl);
    }
}
