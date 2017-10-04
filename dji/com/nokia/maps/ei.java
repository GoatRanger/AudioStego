package com.nokia.maps;

import android.content.Context;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import com.here.android.mpa.common.CopyrightLogoPosition;
import com.here.android.mpa.common.MapEngine;
import com.here.android.mpa.common.OnEngineInitListener;
import com.here.android.mpa.common.OnEngineInitListener.Error;
import com.here.android.mpa.common.OnScreenCaptureListener;
import com.here.android.mpa.streetlevel.StreetLevelGesture;
import com.here.android.mpa.streetlevel.StreetLevelModel;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class ei {
    private static String a = ei.class.getSimpleName();
    private static String b = (bn.class.getName() + ".State.SLView");
    private el c = null;
    private AttributeSet d = null;
    private StreetLevelModel e = null;
    private int f = 0;
    private boolean g = false;
    private CopyOnWriteArrayList<OnEngineInitListener> h = new CopyOnWriteArrayList();
    private CopyrightLogoPosition i = CopyrightLogoPosition.BOTTOM_CENTER;

    public void a(AttributeSet attributeSet) {
        this.d = attributeSet;
    }

    public View a(Context context, ViewGroup viewGroup, Bundle bundle) {
        this.c = new el(context, this.d);
        if (this.c != null) {
            this.c.setLayoutParams(new LayoutParams(-1, -1));
            if (bundle != null) {
                Parcelable parcelable = bundle.getParcelable(b);
                if (parcelable != null) {
                    this.c.onRestoreInstanceState(parcelable);
                }
            }
        }
        return this.c;
    }

    public void a(Context context, final OnEngineInitListener onEngineInitListener) {
        String str = a;
        String str2 = "IN - listener=0x%08x";
        Object[] objArr = new Object[1];
        objArr[0] = Integer.valueOf(onEngineInitListener == null ? 0 : onEngineInitListener.hashCode());
        bj.a(str, str2, objArr);
        if (this.g) {
            if (onEngineInitListener != null) {
                onEngineInitListener.onEngineInitializationCompleted(Error.NONE);
            }
            a(this.e);
        } else {
            int i = this.h.size() > 0 ? 1 : 0;
            if (onEngineInitListener != null) {
                this.h.add(onEngineInitListener);
            }
            if (i == 0) {
                final MapEngine instance = MapEngine.getInstance();
                instance.init(context, new OnEngineInitListener(this) {
                    final /* synthetic */ ei c;

                    public void onEngineInitializationCompleted(Error error) {
                        int i = 0;
                        String j = ei.a;
                        String str = "IN - error=%s listener=0x%08x";
                        Object[] objArr = new Object[2];
                        objArr[0] = error.toString();
                        if (onEngineInitListener != null) {
                            i = onEngineInitListener.hashCode();
                        }
                        objArr[1] = Integer.valueOf(i);
                        bj.a(j, str, objArr);
                        if (error != Error.NONE) {
                            this.c.a(error);
                        } else {
                            ApplicationContext.b().check(22, new ApplicationContext$c(this) {
                                final /* synthetic */ AnonymousClass1 a;

                                {
                                    this.a = r1;
                                }

                                public void b() {
                                    this.a.c.g = true;
                                    for (int i = 0; i < this.a.c.f; i++) {
                                        instance.onResume();
                                    }
                                    this.a.c.f = 0;
                                    this.a.c.g = true;
                                    this.a.c.e = new StreetLevelModel();
                                    if (this.a.c.e == null || this.a.c.c == null) {
                                        this.a.c.g = false;
                                        this.a.c.a(aq.a(Error.UNKNOWN, "Unknown error occurred."));
                                        return;
                                    }
                                    this.a.c.a(this.a.c.e);
                                    this.a.c.a(Error.NONE);
                                }

                                public void a() {
                                    ez.a(new Runnable(this) {
                                        final /* synthetic */ AnonymousClass1 a;

                                        {
                                            this.a = r1;
                                        }

                                        public void run() {
                                            onEngineInitListener.onEngineInitializationCompleted(aq.a(Error.OPERATION_NOT_ALLOWED, "Street Level permission missing."));
                                        }
                                    });
                                }
                            });
                        }
                    }
                });
            }
        }
        bj.a(a, "OUT", new Object[0]);
    }

    public void a() {
        if (this.c != null) {
            this.c.a();
        }
        if (this.g) {
            try {
                MapsEngine.c().v();
                return;
            } catch (Exception e) {
                return;
            }
        }
        this.f--;
    }

    public void b() {
        if (this.g) {
            try {
                MapsEngine.c().w();
            } catch (Exception e) {
            }
        } else {
            this.f++;
        }
        if (this.c != null) {
            this.c.b();
        }
    }

    public void c() {
        if (this.c != null) {
            this.c.c();
            this.c = null;
        }
    }

    public void a(Bundle bundle) {
        if (this.c != null) {
            Parcelable onSaveInstanceState = this.c.onSaveInstanceState();
            this.i = this.c.getCopyrightLogoPosition();
            if (onSaveInstanceState != null) {
                bundle.putParcelable(b, onSaveInstanceState);
            }
        }
    }

    public Rect d() {
        return this.c != null ? this.c.getCopyrightBoundaryRect() : null;
    }

    public void a(Rect rect) {
        if (this.c != null) {
            this.c.setCopyrightBoundaryRect(rect);
        }
    }

    public int e() {
        if (this.c != null) {
            return this.c.getCopyrightMargin();
        }
        return -1;
    }

    public void a(int i) {
        if (this.c != null) {
            this.c.setCopyrightMargin(i);
        }
    }

    public int f() {
        return this.c == null ? -1 : this.c.getCopyrightLogoWidth();
    }

    public int g() {
        return this.c == null ? -1 : this.c.getCopyrightLogoHeight();
    }

    public void a(StreetLevelModel streetLevelModel) {
        this.e = streetLevelModel;
        if (this.c != null) {
            this.c.setStreetLevelModel(streetLevelModel);
        }
    }

    public StreetLevelModel h() {
        return this.e;
    }

    public void a(OnTouchListener onTouchListener) {
        if (this.c != null) {
            this.c.setOnTouchListener(onTouchListener);
        }
    }

    public StreetLevelGesture i() {
        if (this.c != null) {
            return this.c.getStreetLevelGesture();
        }
        return null;
    }

    public void a(boolean z) {
        if (this.c != null) {
            this.c.setBlankStreetLevelImageVisible(z);
        }
    }

    private void a(Error error) {
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
            this.c.a(onScreenCaptureListener);
            return;
        }
        throw new RuntimeException("Fragment is not initialized");
    }
}
