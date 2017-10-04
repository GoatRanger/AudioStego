package com.nokia.maps;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.streetlevel.StreetLevelBuilding;
import com.here.android.mpa.streetlevel.StreetLevelModel;
import com.here.android.mpa.streetlevel.StreetLevelModel.OnEventListener;
import com.here.android.mpa.streetlevel.StreetLevelSelectedObject;
import java.nio.ByteBuffer;
import java.security.AccessControlException;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

public class ej extends x {
    private PanoramaModelImpl a;
    private List<Runnable> b = new CopyOnWriteArrayList();
    private final ApplicationContext$c c = new ApplicationContext$c(this) {
        final /* synthetic */ ej a;

        {
            this.a = r1;
        }

        public void a() {
            throw new AccessControlException("Access to this operation is denied. Contact your HERE representative for more information.");
        }

        public void b() {
        }
    };
    private com.nokia.maps.cy.a d = new com.nokia.maps.cy.a(this) {
        final /* synthetic */ ej a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.a.m();
        }

        public void a(boolean z) {
            if (z) {
                this.a.d();
            }
            synchronized (this.a.b) {
                for (Runnable run : this.a.b) {
                    run.run();
                }
                this.a.b.clear();
            }
            this.a.a.n();
        }
    };
    private boolean e = false;
    private OnEventListener f = new OnEventListener(this) {
        final /* synthetic */ ej a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.d();
        }

        public void onZoomStart(float f) {
        }

        public void onZoomEnd(float f) {
        }

        public void onPositionChanged(GeoCoordinate geoCoordinate) {
            this.a.d();
        }

        public void onMoveStart() {
            this.a.d();
            this.a.e = false;
            ez.a(this.a.h, 16);
        }

        public void onMoveWait() {
            this.a.d();
        }

        public void onMoveContinue() {
            this.a.d();
        }

        public void onMoveEnd(boolean z) {
            this.a.d();
        }

        public void onBuildingShow(StreetLevelBuilding streetLevelBuilding) {
        }

        public void onBuildingHide(StreetLevelBuilding streetLevelBuilding) {
        }

        public void onIconPlaced(StreetLevelSelectedObject streetLevelSelectedObject) {
        }

        public void onStreetLevelPreviewAvailable() {
        }

        public void onStreetLevelFullyLoaded() {
            a();
            this.a.e = true;
        }

        public void onStreetLevelInvalidated() {
            this.a.d();
        }

        public void onStreetLevelChanged() {
            a();
        }

        public void onOrientationStart(float f, float f2) {
        }

        public void onOrientationEnd(float f, float f2) {
        }

        public void onMoveEnd(GeoCoordinate geoCoordinate) {
        }
    };
    private c g = new c(this) {
        final /* synthetic */ ej a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.d();
        }
    };
    private a h = new a();

    private class a implements Runnable {
        final /* synthetic */ ej a;

        private a(ej ejVar) {
            this.a = ejVar;
        }

        public void run() {
            this.a.d();
            if (!this.a.e) {
                ez.a(this.a.h, 16);
            }
        }
    }

    private final class b implements Runnable {
        OnScreenCaptureListener a = null;
        final /* synthetic */ ej b;

        public b(ej ejVar, OnScreenCaptureListener onScreenCaptureListener) {
            this.b = ejVar;
            this.a = onScreenCaptureListener;
        }

        public void run() {
            final int c = this.b.a.c();
            final int d = this.b.a.d();
            byte[] bArr = new byte[((c * d) * 4)];
            Bitmap bitmap = null;
            if (this.b.a.captureScreen(bArr)) {
                try {
                    bitmap = Bitmap.createBitmap(c, d, Config.ARGB_8888);
                    bitmap.copyPixelsFromBuffer(ByteBuffer.wrap(bArr));
                } catch (Exception e) {
                    bj.c("StreetLevelPBufferSurface", e.getLocalizedMessage(), new Object[0]);
                }
            }
            ez.a(new Runnable(this) {
                final /* synthetic */ b d;

                public void run() {
                    bl.a(bitmap, "", c, d);
                    this.d.a.onScreenCaptured(bitmap);
                }
            });
        }
    }

    public ej(Context context) {
        super(context);
        ApplicationContext.b().check(22, this.c);
    }

    public void a(StreetLevelModel streetLevelModel) {
        if (streetLevelModel == null) {
            if (this.a != null) {
                this.a.b(this.f);
                this.a.a(null);
            }
            this.a = null;
            a(null);
            return;
        }
        this.a = PanoramaModelImpl.a(streetLevelModel);
        this.a.a(this.f);
        this.a.a(this.g);
        z cyVar = new cy();
        cyVar.a(this.a);
        cyVar.a(this.d);
        a(cyVar);
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.a != null) {
            synchronized (this.b) {
                this.b.add(new b(this, onScreenCaptureListener));
            }
            return;
        }
        throw new RuntimeException("StreetLevelOffScreenCapture not initialized with a model");
    }
}
