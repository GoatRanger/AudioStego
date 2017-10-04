package com.nokia.maps;

import android.content.Context;
import com.here.android.mpa.common.Image;
import com.nokia.maps.annotation.HybridPlus;
import java.lang.ref.WeakReference;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicBoolean;

@HybridPlus
public class PanoramaMapCompass extends BaseNativeObject {
    private static Image b;
    private static Image c;
    private WeakReference<PanoramaModelImpl> a;
    private PanoramaIconBase d = null;
    private AtomicBoolean e = new AtomicBoolean(false);
    private Semaphore f = new Semaphore(1);
    private a g = null;
    private AtomicBoolean h = new AtomicBoolean(false);
    private cq i = new cq(PanoramaMapCompass.class.getName());

    private class a extends Thread {
        final /* synthetic */ PanoramaMapCompass a;

        private a(PanoramaMapCompass panoramaMapCompass) {
            this.a = panoramaMapCompass;
        }

        public void run() {
            while (this.a.h.get()) {
                try {
                    this.a.f.acquire();
                } catch (Throwable e) {
                    bj.c("PANORAMA_MAP_COMPASS", "InterruptedException \n%s", new Object[]{bj.a(e)});
                }
                this.a.f.drainPermits();
                PanoramaModelImpl panoramaModelImpl = (PanoramaModelImpl) this.a.a.get();
                if (panoramaModelImpl != null && this.a.doDraw(panoramaModelImpl, ImageImpl.a(PanoramaMapCompass.b), ImageImpl.a(PanoramaMapCompass.c))) {
                    panoramaModelImpl.onRedraw();
                }
            }
        }
    }

    private native void createNative(PanoramaModelImpl panoramaModelImpl);

    private native void destroyNative();

    private native boolean doDraw(PanoramaModelImpl panoramaModelImpl, ImageImpl imageImpl, ImageImpl imageImpl2);

    private native PanoramaIconBase getCompassObjectNative();

    public native void setAlpha(float f);

    public native void setVisible(boolean z);

    public PanoramaMapCompass(PanoramaModelImpl panoramaModelImpl) {
        createNative(panoramaModelImpl);
        this.a = new WeakReference(panoramaModelImpl);
        Context e = MapsEngine.e();
        if (e != null || b == null || c == null) {
            byte[] a = ResourceManager.a(e, "./res/images/compass_border.png");
            b = new Image();
            b.setImageData(a);
            byte[] a2 = ResourceManager.a(e, "./res/images/panorama_position.png");
            c = new Image();
            c.setImageData(a2);
        }
    }

    protected void finalize() {
        destroyNative();
    }

    public void a(boolean z) {
        if (this.e.get() != z) {
            this.e.set(z);
        }
    }

    public PanoramaIconBase a() {
        if (this.d == null) {
            this.d = getCompassObjectNative();
        }
        return this.d;
    }

    public synchronized void b() {
        this.h.set(false);
        this.f.release();
        if (this.g != null) {
            try {
                this.g.join();
            } catch (Throwable e) {
                bj.c("PANORAMA_MAP_COMPASS", "InterruptedException \n%s", new Object[]{bj.a(e)});
            }
            this.g = null;
        }
    }

    public synchronized void c() {
        this.h.set(true);
        if (this.g == null) {
            this.g = new a();
            this.g.start();
        }
    }

    public void d() {
        if (!this.e.get()) {
            this.f.release();
        }
    }
}
