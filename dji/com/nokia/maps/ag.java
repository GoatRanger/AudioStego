package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.here.android.mpa.ar.ARController;
import com.here.android.mpa.common.CopyrightLogoPosition;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker$OnDragListener;
import com.here.android.mpa.mapping.OnMapRenderListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ag {
    private static String a = ag.class.getSimpleName();
    private static String h = (ag.class.getName() + "State.MapView");
    private aj b;
    private AttributeSet c;
    private boolean d = false;
    private int e = 0;
    private final CopyOnWriteArrayList<OnEngineInitListener> f = new CopyOnWriteArrayList();
    private CopyrightLogoPosition g = CopyrightLogoPosition.BOTTOM_CENTER;
    private Map i;
    private MapMarker$OnDragListener j = null;

    public void a(final Context context, OnEngineInitListener onEngineInitListener) {
        String str = a;
        String str2 = "IN = listener=0x%08x";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(onEngineInitListener == null ? 0 : onEngineInitListener.hashCode());
        bj.a(str, str2, objArr);
        if (this.d) {
            o();
            b.a(l()).R().applicationIsReady();
            if (onEngineInitListener != null) {
                onEngineInitListener.onEngineInitializationCompleted(Error.NONE);
            }
        } else {
            int i = this.f.size() > 0 ? 1 : 0;
            if (onEngineInitListener != null) {
                this.f.add(onEngineInitListener);
            }
            if (i == 0) {
                MapEngine.getInstance().init(context, new OnEngineInitListener(this) {
                    final /* synthetic */ ag b;

                    public void onEngineInitializationCompleted(Error error) {
                        this.b.a(error, context);
                    }
                });
            }
        }
        str = a;
        str2 = "OUT = listener=0x%08x";
        Object[] objArr2 = new Object[1];
        objArr2[0] = Integer.valueOf(onEngineInitListener == null ? 0 : onEngineInitListener.hashCode());
        bj.a(str, str2, objArr2);
    }

    private void a(Error error, Context context) {
        if (error == Error.NONE) {
            this.d = true;
            for (int i = 0; i < this.e; i++) {
                try {
                    MapEngine instance = MapEngine.getInstance();
                    if (instance != null) {
                        instance.onResume();
                    }
                } catch (Exception e) {
                    bj.c(a, "Exception: %s", new Object[]{e.getLocalizedMessage()});
                }
            }
            this.e = 0;
            o();
            b.a(l()).R().applicationIsReady();
        }
        if (this.f.size() > 0) {
            Iterator it = this.f.iterator();
            while (it.hasNext()) {
                ((OnEngineInitListener) it.next()).onEngineInitializationCompleted(error);
            }
            this.f.clear();
        }
    }

    public View a(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.b = new aj(context);
        this.b.setLayoutParams(new LayoutParams(-1, -1));
        if (bundle != null) {
            Parcelable parcelable = bundle.getParcelable(h);
            if (parcelable != null) {
                this.b.onRestoreInstanceState(parcelable);
            }
        } else {
            this.b.setCopyrightLogoPosition(this.g);
        }
        this.b.setMapMarkerDragListener(this.j);
        return this.b;
    }

    public void a(AttributeSet attributeSet, Bundle bundle) {
        this.c = attributeSet;
    }

    public void a() {
        if (this.d) {
            try {
                MapsEngine.c().w();
            } catch (Exception e) {
            }
        } else {
            this.e++;
        }
        this.b.b();
    }

    public void b() {
        if (this.d) {
            try {
                MapsEngine.c().v();
            } catch (Exception e) {
            }
        } else {
            this.e--;
        }
        this.b.a();
    }

    public void c() {
        if (this.b != null) {
            this.b.setOnTouchListener(null);
            this.b.setMapMarkerDragListener(null);
            this.b.setMap(null);
            this.b.c();
            this.b = null;
        }
    }

    public void a(OnTouchListener onTouchListener) {
        if (this.b != null) {
            this.b.setOnTouchListener(onTouchListener);
        }
    }

    public void a(Bundle bundle) {
        Parcelable parcelable = null;
        if (this.b != null) {
            parcelable = this.b.onSaveInstanceState();
            this.g = this.b.getCopyrightLogoPosition();
        }
        if (parcelable != null) {
            bundle.putParcelable(h, parcelable);
        }
    }

    public Map d() {
        return this.i;
    }

    public ViewRect e() {
        if (this.b != null) {
            return this.b.getClipRect();
        }
        return null;
    }

    public void a(ViewRect viewRect, PointF pointF) {
        if (this.b != null) {
            this.b.a(viewRect, pointF);
        }
    }

    public void a(ViewRect viewRect) {
        if (this.b != null) {
            this.b.setClipRect(viewRect);
        }
    }

    public Rect f() {
        return this.b != null ? this.b.getCopyrightBoundaryRect() : null;
    }

    public void a(Rect rect) {
        if (this.b != null) {
            this.b.setCopyrightBoundaryRect(rect);
        }
    }

    public int g() {
        if (this.b != null) {
            return this.b.getCopyrightMargin();
        }
        return -1;
    }

    public void a(int i) {
        if (this.b != null) {
            this.b.setCopyrightMargin(i);
        }
    }

    public CopyrightLogoPosition h() {
        return this.b != null ? this.b.getCopyrightLogoPosition() : CopyrightLogoPosition.BOTTOM_CENTER;
    }

    public void a(CopyrightLogoPosition copyrightLogoPosition) {
        if (this.b != null) {
            this.b.setCopyrightLogoPosition(copyrightLogoPosition);
        }
    }

    public int i() {
        return this.b == null ? -1 : this.b.getCopyrightLogoWidth();
    }

    public int j() {
        return this.b == null ? -1 : this.b.getCopyrightLogoHeight();
    }

    public MapGesture k() {
        if (this.b != null) {
            return this.b.getMapGesture();
        }
        return null;
    }

    public void a(OnMapRenderListener onMapRenderListener) {
        if (this.b != null) {
            this.b.a(onMapRenderListener);
        }
    }

    public void b(OnMapRenderListener onMapRenderListener) {
        if (this.b != null) {
            this.b.b(onMapRenderListener);
        }
    }

    public void a(MapMarker$OnDragListener mapMarker$OnDragListener) {
        this.j = mapMarker$OnDragListener;
        if (this.b != null) {
            this.b.setMapMarkerDragListener(mapMarker$OnDragListener);
        }
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.i != null) {
            MapImpl.get(this.i).a(onScreenCaptureListener);
            return;
        }
        throw new RuntimeException("Fragment is not initialized");
    }

    private void o() {
        if (this.i == null) {
            this.i = new Map();
        }
        if (this.b != null && this.b.getMap() == null) {
            this.b.setMap(this.i);
        }
    }

    public ARController l() {
        return this.b != null ? this.b.getARController() : null;
    }

    public int m() {
        return this.b != null ? this.b.getWidth() : -1;
    }

    public int n() {
        return this.b != null ? this.b.getHeight() : -1;
    }
}
