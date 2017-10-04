package com.nokia.maps;

import android.content.Context;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.OnMapRenderListener;
import com.nokia.maps.annotation.Internal;
import com.nokia.maps.cc.a;

@Internal
public class br extends x {
    private bx a;
    private MapImpl b;
    private MapImpl$e c = new MapImpl$e(this) {
        final /* synthetic */ br a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.d();
        }

        public void b() {
        }
    };
    private a d = new a(this) {
        final /* synthetic */ br a;

        {
            this.a = r1;
        }

        public void a() {
            this.a.d();
        }

        public void b() {
            this.a.d();
        }
    };

    public br(Context context) {
        super(context);
    }

    public void a(Map map) {
        if (map == null) {
            a(null);
            this.a = null;
            this.b.b(this.c);
            this.b = null;
            MapsEngine.b(null).A().b(this.d);
            return;
        }
        this.b = MapImpl.get(map);
        this.b.a(this.c);
        this.a = new au();
        this.a.a(this.b);
        a(this.a);
        MapsEngine.b(null).A().a(this.d);
    }

    public void a(OnMapRenderListener onMapRenderListener) {
        if (this.a != null) {
            this.a.a(onMapRenderListener);
        }
    }

    public void b(OnMapRenderListener onMapRenderListener) {
        if (this.a != null) {
            this.a.b(onMapRenderListener);
        }
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (onScreenCaptureListener == null) {
            throw new IllegalArgumentException("OnScreenCaptureListener is null");
        } else if (this.b != null) {
            this.b.a(onScreenCaptureListener);
        } else {
            throw new RuntimeException("MapOffSrceenRenderer not initialized with a Map");
        }
    }

    public void a(final ViewRect viewRect) {
        if (this.b != null) {
            this.b.b(new Runnable(this) {
                final /* synthetic */ br b;

                public void run() {
                    this.b.b.b(viewRect);
                }
            });
            return;
        }
        throw new RuntimeException("MapOffSrceenRenderer not initialized with a Map");
    }
}
