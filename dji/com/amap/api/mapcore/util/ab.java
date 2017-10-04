package com.amap.api.mapcore.util;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.amap.api.maps.AMapOptions;
import com.amap.api.maps.model.CameraPosition;
import com.autonavi.amap.mapcore.interfaces.CameraUpdateFactoryDelegate;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IMapFragmentDelegate;
import com.autonavi.amap.mapcore.interfaces.IUiSettingsDelegate;
import dji.midware.util.a.b;
import dji.pilot.visual.a.d;
import lecho.lib.hellocharts.model.l;

public class ab implements IMapFragmentDelegate {
    public static volatile Context a;
    public static int c = 0;
    public static int d = 1;
    public int b = 0;
    private IAMapDelegate e;
    private int f = 0;
    private AMapOptions g;

    public ab(int i) {
        int i2 = 0;
        if (i > 0) {
            i2 = 1;
        }
        this.f = i2;
    }

    public void setContext(Context context) {
        if (context != null) {
            a = context.getApplicationContext();
        }
    }

    public void setOptions(AMapOptions aMapOptions) {
        this.g = aMapOptions;
    }

    public IAMapDelegate getMap() throws RemoteException {
        if (this.e == null) {
            if (a == null) {
                throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
            }
            int i = a.getResources().getDisplayMetrics().densityDpi;
            if (i <= 120) {
                r.a = d.c;
            } else if (i <= 160) {
                r.a = 0.8f;
            } else if (i <= 240) {
                r.a = 0.87f;
            } else if (i <= 320) {
                r.a = 1.0f;
            } else if (i <= 480) {
                r.a = b.c;
            } else if (i <= 640) {
                r.a = 1.8f;
            } else {
                r.a = 0.9f;
            }
            if (this.f == c) {
                this.e = new k(a).a();
            } else {
                this.e = new l(a).a();
            }
        }
        return this.e;
    }

    public void onInflate(Activity activity, AMapOptions aMapOptions, Bundle bundle) throws RemoteException {
        a = activity.getApplicationContext();
        this.g = aMapOptions;
    }

    public void onCreate(Bundle bundle) throws RemoteException {
    }

    public View onCreateView(LayoutInflater layoutInflater, ViewGroup viewGroup, Bundle bundle) throws RemoteException {
        if (this.e == null) {
            if (a == null && layoutInflater != null) {
                a = layoutInflater.getContext().getApplicationContext();
            }
            if (a == null) {
                throw new NullPointerException("Context 为 null 请在地图调用之前 使用 MapsInitializer.initialize(Context paramContext) 来设置Context");
            }
            int i = a.getResources().getDisplayMetrics().densityDpi;
            if (i <= 120) {
                r.a = d.c;
            } else if (i <= 160) {
                r.a = l.n;
            } else if (i <= 240) {
                r.a = 0.87f;
            } else if (i <= 320) {
                r.a = 1.0f;
            } else if (i <= 480) {
                r.a = b.c;
            } else if (i <= 640) {
                r.a = 1.8f;
            } else {
                r.a = 0.9f;
            }
            if (this.f == c) {
                this.e = new k(a).a();
            } else {
                this.e = new l(a).a();
            }
            this.e.setVisibilityEx(this.b);
        }
        try {
            if (this.g == null && bundle != null) {
                byte[] byteArray = bundle.getByteArray("MapOptions");
                if (byteArray != null) {
                    Parcel obtain = Parcel.obtain();
                    obtain.unmarshall(byteArray, 0, byteArray.length);
                    obtain.setDataPosition(0);
                    this.g = AMapOptions.CREATOR.createFromParcel(obtain);
                }
            }
            a(this.g);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        return this.e.getView();
    }

    void a(AMapOptions aMapOptions) throws RemoteException {
        if (aMapOptions != null && this.e != null) {
            CameraPosition camera = aMapOptions.getCamera();
            if (camera != null) {
                this.e.moveCamera(CameraUpdateFactoryDelegate.newCamera(camera.target, camera.zoom, camera.bearing, camera.tilt));
            }
            IUiSettingsDelegate uiSettings = this.e.getUiSettings();
            uiSettings.setRotateGesturesEnabled(aMapOptions.getRotateGesturesEnabled().booleanValue());
            uiSettings.setScrollGesturesEnabled(aMapOptions.getScrollGesturesEnabled().booleanValue());
            uiSettings.setTiltGesturesEnabled(aMapOptions.getTiltGesturesEnabled().booleanValue());
            uiSettings.setZoomControlsEnabled(aMapOptions.getZoomControlsEnabled().booleanValue());
            uiSettings.setZoomGesturesEnabled(aMapOptions.getZoomGesturesEnabled().booleanValue());
            uiSettings.setCompassEnabled(aMapOptions.getCompassEnabled().booleanValue());
            uiSettings.setScaleControlsEnabled(aMapOptions.getScaleControlsEnabled().booleanValue());
            uiSettings.setLogoPosition(aMapOptions.getLogoPosition());
            this.e.setMapType(aMapOptions.getMapType());
            this.e.setZOrderOnTop(aMapOptions.getZOrderOnTop().booleanValue());
        }
    }

    public void onResume() throws RemoteException {
        if (this.e != null) {
            this.e.onActivityResume();
        }
    }

    public void onPause() throws RemoteException {
        if (this.e != null) {
            this.e.onActivityPause();
        }
    }

    public void onDestroyView() throws RemoteException {
    }

    public void onDestroy() throws RemoteException {
        if (this.e != null) {
            this.e.clear();
            this.e.destroy();
            this.e = null;
        }
    }

    public void onLowMemory() throws RemoteException {
        Log.d("onLowMemory", "onLowMemory run");
    }

    public void onSaveInstanceState(Bundle bundle) throws RemoteException {
        if (this.e != null) {
            if (this.g == null) {
                this.g = new AMapOptions();
            }
            try {
                Parcel obtain = Parcel.obtain();
                this.g = this.g.camera(getMap().getCameraPositionPrj(false));
                this.g.writeToParcel(obtain, 0);
                bundle.putByteArray("MapOptions", obtain.marshall());
            } catch (Throwable th) {
            }
        }
    }

    public boolean isReady() throws RemoteException {
        return false;
    }

    public void setVisibility(int i) {
        this.b = i;
        if (this.e != null) {
            this.e.setVisibilityEx(i);
        }
    }
}
