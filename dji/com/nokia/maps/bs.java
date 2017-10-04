package com.nokia.maps;

import android.content.Context;
import android.graphics.PointF;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.mapping.Map;
import com.here.android.mpa.mapping.Map$OnTransformListener;
import com.here.android.mpa.mapping.MapObject;
import com.here.android.mpa.mapping.MapOverlay;
import com.here.android.mpa.mapping.MapState;
import com.here.android.mpa.mapping.OnMapRenderListener;
import java.util.HashSet;
import java.util.Set;

public class bs extends ViewGroup implements Map$OnTransformListener, MapImpl$h {
    private int a = -1;
    private int b = -1;
    private final Set<MapOverlay> c = new HashSet();
    private Map d;
    private final Object e = new Object();
    private final Handler f = new Handler(this) {
        final /* synthetic */ bs a;

        {
            this.a = r1;
        }

        public void handleMessage(Message message) {
            if (message.what == 126754) {
                this.a.b();
            }
        }
    };
    private final OnMapRenderListener g = new OnMapRenderListener(this) {
        final /* synthetic */ bs a;

        {
            this.a = r1;
        }

        public void onSizeChanged(int i, int i2) {
        }

        public void onRenderBufferCreated() {
        }

        public void onPreDraw() {
        }

        public void onPostDraw(boolean z, long j) {
            if (this.a.a != this.a.b) {
                this.a.f.sendEmptyMessage(126754);
            }
        }

        public void onGraphicsDetached() {
        }
    };

    public bs(Context context) {
        super(context);
    }

    protected void onMeasure(int i, int i2) {
        super.onMeasure(i, i2);
        for (MapOverlay view : this.c) {
            measureChildWithMargins(view.getView(), i, 0, i2, 0);
        }
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        synchronized (this.e) {
            if (this.d != null) {
                for (MapOverlay mapOverlay : this.c) {
                    int i5;
                    int i6;
                    View view = mapOverlay.getView();
                    PointF result = this.d.projectToPixel(mapOverlay.getCoordinate()).getResult();
                    int measuredWidth = view.getMeasuredWidth();
                    int measuredHeight = view.getMeasuredHeight();
                    int i7 = (int) result.x;
                    int i8 = (int) result.y;
                    PointF anchorPoint = mapOverlay.getAnchorPoint();
                    if (anchorPoint != null) {
                        i5 = (int) (((float) i7) - anchorPoint.x);
                        i6 = (int) (((float) i8) - anchorPoint.y);
                    } else {
                        i5 = i7 - (measuredWidth / 2);
                        i6 = i8 - (measuredHeight / 2);
                    }
                    view.layout(i5, i6, measuredWidth + i5, measuredHeight + i6);
                }
                if (this.a == -1) {
                    a(true);
                    a(false, true);
                    this.a = 1;
                    this.b = 1;
                }
            }
        }
    }

    public void b(MapOverlay mapOverlay) {
        synchronized (this.e) {
            if (this.c.remove(mapOverlay)) {
                removeView(mapOverlay.getView());
                this.d.removeMapObject(bt.a(mapOverlay).d());
            }
        }
    }

    public void a(MapOverlay mapOverlay) {
        boolean z = false;
        synchronized (this.e) {
            if (!this.c.contains(mapOverlay)) {
                View view = mapOverlay.getView();
                LayoutParams marginLayoutParams = new MarginLayoutParams(-2, -2);
                view.setLayoutParams(marginLayoutParams);
                addView(view);
                view.measure(marginLayoutParams.width, marginLayoutParams.height);
                view.layout((-view.getMeasuredWidth()) / 2, (-view.getMeasuredHeight()) / 2, view.getMeasuredWidth() / 2, view.getMeasuredHeight() / 2);
                MapObject d = bt.a(mapOverlay).d();
                this.d.addMapObject(d);
                d.setZIndex(65535);
                d.setVisible(false);
                bt.a(mapOverlay).e();
                this.c.add(mapOverlay);
                view.setVisibility((this.a & 1) != 0 ? 0 : 4);
                if ((this.a & 2) != 0) {
                    z = true;
                }
                d.setVisible(z);
            }
        }
    }

    public void a() {
        synchronized (this.e) {
            if (this.d != null) {
                MapImpl.get(this.d).b(this);
                this.d.removeTransformListener(this);
                this.d = null;
            }
        }
    }

    public void a(Map map) {
        synchronized (this.e) {
            if (this.d != null) {
                a();
            }
            this.d = map;
            MapImpl.get(map).a(this);
            map.addTransformListener(this);
            invalidate();
        }
    }

    public void onMapTransformStart() {
        b(true, true);
        b(false);
    }

    public void onMapTransformEnd(MapState mapState) {
        b(true);
        b(false, false);
    }

    private void a(boolean z) {
        if (this.d != null) {
            GeoCoordinate center = this.d.getCenter();
            float toRadians = (float) Math.toRadians(center.getLatitude());
            float toRadians2 = (float) Math.toRadians(center.getLongitude());
            double cos = Math.cos((double) toRadians);
            double sin = Math.sin((double) toRadians);
            for (MapOverlay mapOverlay : this.c) {
                Object obj;
                GeoCoordinate coordinate = mapOverlay.getCoordinate();
                float toRadians3 = (float) Math.toRadians(coordinate.getLatitude());
                toRadians = (float) Math.acos(((Math.cos((double) toRadians3) * cos) * Math.cos((double) (((float) Math.toRadians(coordinate.getLongitude())) - toRadians2))) + (Math.sin((double) toRadians3) * sin));
                if (toRadians <= 1.15f || ((double) toRadians) >= 5.133185331021444d) {
                    obj = null;
                } else {
                    obj = 1;
                }
                View view = mapOverlay.getView();
                int i = (z && obj == null) ? 0 : 8;
                view.setVisibility(i);
            }
        }
    }

    private void a(boolean z, boolean z2) {
        MapImpl mapImpl = MapImpl.get(this.d);
        if (mapImpl != null) {
            synchronized (mapImpl) {
                for (MapOverlay a : this.c) {
                    bt a2 = bt.a(a);
                    if (z2) {
                        a2.e();
                    }
                    a2.d().setVisible(z);
                }
            }
        }
    }

    private void b(boolean z) {
        synchronized (this.e) {
            if (z) {
                this.b |= 1;
            } else {
                this.b &= -2;
            }
        }
    }

    private void b(boolean z, boolean z2) {
        synchronized (this.e) {
            if (z) {
                this.b |= 2;
            } else {
                this.b &= -3;
            }
            if (z2) {
                this.b |= 4;
            } else {
                this.b &= -5;
            }
        }
    }

    private void b() {
        boolean z = true;
        synchronized (this.e) {
            int i = this.b ^ this.a;
            if ((i & 1) != 0) {
                a((this.b & 1) != 0);
            }
            if ((i & 6) != 0) {
                boolean z2;
                if ((this.b & 2) != 0) {
                    z2 = true;
                } else {
                    z2 = false;
                }
                if ((this.b & 4) == 0) {
                    z = false;
                }
                a(z2, z);
            }
            this.a = this.b & 3;
            this.b = this.a;
        }
    }

    public OnMapRenderListener getMapRenderListener() {
        return this.g;
    }
}
