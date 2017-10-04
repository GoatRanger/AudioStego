package com.amap.api.mapcore.util;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.amap.api.mapcore.util.ah.a;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;

class ba extends LinearLayout {
    private Bitmap a;
    private Bitmap b;
    private Bitmap c;
    private Bitmap d;
    private Bitmap e;
    private Bitmap f;
    private Bitmap g;
    private Bitmap h;
    private Bitmap i;
    private Bitmap j;
    private Bitmap k;
    private Bitmap l;
    private ImageView m;
    private ImageView n;
    private IAMapDelegate o;

    public void a() {
        try {
            removeAllViews();
            this.a.recycle();
            this.b.recycle();
            this.c.recycle();
            this.d.recycle();
            this.e.recycle();
            this.f.recycle();
            this.a = null;
            this.b = null;
            this.c = null;
            this.d = null;
            this.e = null;
            this.f = null;
            if (this.g != null) {
                this.g.recycle();
                this.g = null;
            }
            if (this.h != null) {
                this.h.recycle();
                this.h = null;
            }
            if (this.i != null) {
                this.i.recycle();
                this.i = null;
            }
            if (this.j != null) {
                this.j.recycle();
                this.g = null;
            }
            if (this.k != null) {
                this.k.recycle();
                this.k = null;
            }
            if (this.l != null) {
                this.l.recycle();
                this.l = null;
            }
            this.m = null;
            this.n = null;
        } catch (Throwable th) {
            ee.a(th, "ZoomControllerView", "destory");
            th.printStackTrace();
        }
    }

    public ba(Context context) {
        super(context);
    }

    public ba(Context context, IAMapDelegate iAMapDelegate) {
        super(context);
        this.o = iAMapDelegate;
        try {
            this.g = dj.a(context, "zoomin_selected.png");
            this.a = dj.a(this.g, r.a);
            this.h = dj.a(context, "zoomin_unselected.png");
            this.b = dj.a(this.h, r.a);
            this.i = dj.a(context, "zoomout_selected.png");
            this.c = dj.a(this.i, r.a);
            this.j = dj.a(context, "zoomout_unselected.png");
            this.d = dj.a(this.j, r.a);
            this.k = dj.a(context, "zoomin_pressed.png");
            this.e = dj.a(this.k, r.a);
            this.l = dj.a(context, "zoomout_pressed.png");
            this.f = dj.a(this.l, r.a);
            this.m = new ImageView(context);
            this.m.setImageBitmap(this.a);
            this.m.setClickable(true);
            this.n = new ImageView(context);
            this.n.setImageBitmap(this.c);
            this.n.setClickable(true);
            this.m.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ ba a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (this.a.o.getZoomLevel() < this.a.o.getMaxZoomLevel() && this.a.o.isMaploaded()) {
                        if (motionEvent.getAction() == 0) {
                            this.a.m.setImageBitmap(this.a.e);
                        } else if (motionEvent.getAction() == 1) {
                            this.a.m.setImageBitmap(this.a.a);
                            try {
                                this.a.o.animateCamera(CameraUpdateFactoryDelegate.zoomIn());
                            } catch (Throwable e) {
                                ee.a(e, "ZoomControllerView", "zoomin ontouch");
                                e.printStackTrace();
                            }
                        }
                    }
                    return false;
                }
            });
            this.n.setOnTouchListener(new OnTouchListener(this) {
                final /* synthetic */ ba a;

                {
                    this.a = r1;
                }

                public boolean onTouch(View view, MotionEvent motionEvent) {
                    if (this.a.o.getZoomLevel() > this.a.o.getMinZoomLevel() && this.a.o.isMaploaded()) {
                        if (motionEvent.getAction() == 0) {
                            this.a.n.setImageBitmap(this.a.f);
                        } else if (motionEvent.getAction() == 1) {
                            this.a.n.setImageBitmap(this.a.c);
                            try {
                                this.a.o.animateCamera(CameraUpdateFactoryDelegate.zoomOut());
                            } catch (Throwable e) {
                                ee.a(e, "ZoomControllerView", "zoomout ontouch");
                                e.printStackTrace();
                            }
                        }
                    }
                    return false;
                }
            });
            this.m.setPadding(0, 0, 20, -2);
            this.n.setPadding(0, 0, 20, 20);
            setOrientation(1);
            addView(this.m);
            addView(this.n);
        } catch (Throwable th) {
            ee.a(th, "ZoomControllerView", "create");
            th.printStackTrace();
        }
    }

    public void a(float f) {
        try {
            if (f < this.o.getMaxZoomLevel() && f > this.o.getMinZoomLevel()) {
                this.m.setImageBitmap(this.a);
                this.n.setImageBitmap(this.c);
            } else if (f == this.o.getMinZoomLevel()) {
                this.n.setImageBitmap(this.d);
                this.m.setImageBitmap(this.a);
            } else if (f == this.o.getMaxZoomLevel()) {
                this.m.setImageBitmap(this.b);
                this.n.setImageBitmap(this.c);
            }
        } catch (Throwable th) {
            ee.a(th, "ZoomControllerView", "setZoomBitmap");
            th.printStackTrace();
        }
    }

    public void a(int i) {
        try {
            a aVar = (a) getLayoutParams();
            if (i == 1) {
                aVar.d = 16;
            } else if (i == 2) {
                aVar.d = 80;
            }
            setLayoutParams(aVar);
        } catch (Throwable th) {
            ee.a(th, "ZoomControllerView", "setZoomPosition");
            th.printStackTrace();
        }
    }

    public void a(boolean z) {
        if (z) {
            setVisibility(0);
        } else {
            setVisibility(8);
        }
    }
}
