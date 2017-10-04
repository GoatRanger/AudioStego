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
import com.here.android.mpa.common.CopyrightLogoPosition;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.common.ViewRect;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.MapGesture;
import com.here.android.mpa.mapping.MapMarker$OnDragListener;
import com.here.android.mpa.mapping.MapView;
import com.here.android.mpa.mapping.OnMapRenderListener;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class bn {
    private static String a = (bn.class.getName() + ".State.MapView");
    private static String b = bn.class.getSimpleName();
    private Map c;
    private MapView d;
    private AttributeSet e;
    private boolean f = false;
    private int g = 0;
    private final CopyOnWriteArrayList<OnEngineInitListener> h = new CopyOnWriteArrayList();
    private MapMarker$OnDragListener i = null;
    private CopyrightLogoPosition j = CopyrightLogoPosition.BOTTOM_CENTER;

    public void a(Context context, OnEngineInitListener onEngineInitListener) {
        String str = b;
        String str2 = "IN = listener=0x%08x";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(onEngineInitListener == null ? 0 : onEngineInitListener.hashCode());
        bj.a(str, str2, objArr);
        if (this.f) {
            l();
            if (onEngineInitListener != null) {
                onEngineInitListener.onEngineInitializationCompleted(Error.NONE);
            }
        } else {
            int i = this.h.size() > 0 ? 1 : 0;
            if (onEngineInitListener != null) {
                this.h.add(onEngineInitListener);
            }
            if (i == 0) {
                MapEngine.getInstance().init(context, new OnEngineInitListener(this) {
                    final /* synthetic */ bn a;

                    {
                        this.a = r1;
                    }

                    public void onEngineInitializationCompleted(Error error) {
                        this.a.a(error);
                    }
                });
            }
        }
        str = b;
        str2 = "OUT = listener=0x%08x";
        Object[] objArr2 = new Object[1];
        objArr2[0] = Integer.valueOf(onEngineInitListener == null ? 0 : onEngineInitListener.hashCode());
        bj.a(str, str2, objArr2);
    }

    public View a(Context context, LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) {
        this.d = new MapView(context, this.e);
        if (this.d != null) {
            this.d.setLayoutParams(new LayoutParams(-1, -1));
            if (bundle != null) {
                Parcelable parcelable = bundle.getParcelable(a);
                if (parcelable != null) {
                    this.d.onRestoreInstanceState(parcelable);
                }
            } else {
                this.d.setCopyrightLogoPosition(this.j);
            }
            this.d.setMapMarkerDragListener(this.i);
        }
        return this.d;
    }

    public void a(AttributeSet attributeSet, Bundle bundle) {
        this.e = attributeSet;
    }

    public void a() {
        if (this.f) {
            try {
                MapsEngine.c().w();
            } catch (Exception e) {
            }
        } else {
            this.g++;
        }
        if (this.d != null) {
            this.d.onResume();
        }
    }

    public void b() {
        if (this.d != null) {
            this.d.onPause();
        }
        if (this.f) {
            try {
                MapsEngine.c().v();
                return;
            } catch (Exception e) {
                return;
            }
        }
        this.g--;
    }

    public void c() {
        if (this.d != null) {
            this.d.setOnTouchListener(null);
            this.d.setMapMarkerDragListener(null);
            if (this.d.getMap() != null) {
                this.d.setMap(null);
            }
            this.d = null;
        }
    }

    public void a(Bundle bundle) {
        if (this.d != null) {
            Parcelable onSaveInstanceState = this.d.onSaveInstanceState();
            this.j = this.d.getCopyrightLogoPosition();
            if (onSaveInstanceState != null) {
                bundle.putParcelable(a, onSaveInstanceState);
            }
        }
    }

    public Map d() {
        return this.c;
    }

    public ViewRect e() {
        if (this.d != null) {
            return this.d.getClipRect();
        }
        return null;
    }

    public void a(ViewRect viewRect, PointF pointF) {
        if (this.d != null) {
            this.d.setClipRect(viewRect, pointF);
        }
    }

    public void a(ViewRect viewRect) {
        if (this.d != null) {
            this.d.setClipRect(viewRect);
        }
    }

    public Rect f() {
        return this.d != null ? this.d.getCopyrightBoundaryRect() : null;
    }

    public void a(Rect rect) {
        if (this.d != null) {
            this.d.setCopyrightBoundaryRect(rect);
        }
    }

    public int g() {
        if (this.d != null) {
            return this.d.getCopyrightMargin();
        }
        return -1;
    }

    public void a(int i) {
        if (this.d != null) {
            this.d.setCopyrightMargin(i);
        }
    }

    public CopyrightLogoPosition h() {
        return this.d != null ? this.d.getCopyrightLogoPosition() : CopyrightLogoPosition.BOTTOM_CENTER;
    }

    public void a(CopyrightLogoPosition copyrightLogoPosition) {
        if (this.d != null) {
            this.d.setCopyrightLogoPosition(copyrightLogoPosition);
        }
    }

    public int i() {
        return this.d == null ? -1 : this.d.getCopyrightLogoWidth();
    }

    public int j() {
        return this.d == null ? -1 : this.d.getCopyrightLogoHeight();
    }

    public MapGesture k() {
        if (this.d != null) {
            return this.d.getMapGesture();
        }
        return null;
    }

    public void a(OnMapRenderListener onMapRenderListener) {
        if (this.d != null) {
            this.d.addOnMapRenderListener(onMapRenderListener);
        }
    }

    public void b(OnMapRenderListener onMapRenderListener) {
        if (this.d != null) {
            this.d.removeOnMapRenderListener(onMapRenderListener);
        }
    }

    public void a(OnTouchListener onTouchListener) {
        if (this.d != null) {
            this.d.setOnTouchListener(onTouchListener);
        }
    }

    public void a(MapMarker$OnDragListener mapMarker$OnDragListener) {
        this.i = mapMarker$OnDragListener;
        if (this.d != null) {
            this.d.setMapMarkerDragListener(mapMarker$OnDragListener);
        }
    }

    private void l() {
        if (this.c == null) {
            this.c = new Map();
        }
        if (this.d != null && this.d.getMap() == null) {
            this.d.setMap(this.c);
        }
    }

    private void a(Error error) {
        if (error == Error.NONE) {
            this.f = true;
            for (int i = 0; i < this.g; i++) {
                try {
                    MapEngine.getInstance().onResume();
                } catch (Throwable e) {
                    bj.c(b, "Exception: %s", new Object[]{e.getLocalizedMessage()});
                    error = aq.a(Error.OPERATION_NOT_ALLOWED, "Cannot initialize due to invalid credentials. Please check if provided credentials in the AndroidManifest.xml are correct.", e);
                } catch (Throwable e2) {
                    bj.c(b, "Exception: %s", new Object[]{e2.getLocalizedMessage()});
                    error = aq.a(Error.UNKNOWN, "Unknown error occurred.", e2);
                }
            }
            this.g = 0;
            l();
        }
        if (this.h.size() > 0) {
            Iterator it = this.h.iterator();
            while (it.hasNext()) {
                ((OnEngineInitListener) it.next()).onEngineInitializationCompleted(error);
            }
            this.h.clear();
        }
    }

    public void a(OnScreenCaptureListener onScreenCaptureListener) {
        if (this.c != null) {
            MapImpl.get(this.c).a(onScreenCaptureListener);
            return;
        }
        throw new RuntimeException("Fragment is not initialized");
    }
}
