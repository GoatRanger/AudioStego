package com.amap.api.mapcore.util;

import android.os.Handler;
import android.os.RemoteException;
import com.autonavi.amap.mapcore.interfaces.IAMapDelegate;
import com.autonavi.amap.mapcore.interfaces.IUiSettingsDelegate;

class ax implements IUiSettingsDelegate {
    final Handler a = new ay(this);
    private IAMapDelegate b;
    private boolean c = true;
    private boolean d = true;
    private boolean e = true;
    private boolean f = false;
    private boolean g = true;
    private boolean h = true;
    private boolean i = true;
    private boolean j = false;
    private int k = 0;
    private int l = 1;
    private boolean m = true;

    ax(IAMapDelegate iAMapDelegate) {
        this.b = iAMapDelegate;
    }

    public boolean isIndoorSwitchEnabled() throws RemoteException {
        return this.m;
    }

    public void setIndoorSwitchEnabled(boolean z) throws RemoteException {
        this.m = z;
        this.a.obtainMessage(4).sendToTarget();
    }

    public void setScaleControlsEnabled(boolean z) throws RemoteException {
        this.j = z;
        this.a.obtainMessage(1).sendToTarget();
    }

    public void setZoomControlsEnabled(boolean z) throws RemoteException {
        this.h = z;
        this.a.obtainMessage(0).sendToTarget();
    }

    public void setCompassEnabled(boolean z) throws RemoteException {
        this.i = z;
        this.a.obtainMessage(2).sendToTarget();
    }

    public void setMyLocationButtonEnabled(boolean z) throws RemoteException {
        this.f = z;
        this.a.obtainMessage(3).sendToTarget();
    }

    public void setScrollGesturesEnabled(boolean z) throws RemoteException {
        this.d = z;
    }

    public void setZoomGesturesEnabled(boolean z) throws RemoteException {
        this.g = z;
    }

    public void setTiltGesturesEnabled(boolean z) throws RemoteException {
        this.e = z;
    }

    public void setRotateGesturesEnabled(boolean z) throws RemoteException {
        this.c = z;
    }

    public void setAllGesturesEnabled(boolean z) throws RemoteException {
        setRotateGesturesEnabled(z);
        setTiltGesturesEnabled(z);
        setZoomGesturesEnabled(z);
        setScrollGesturesEnabled(z);
    }

    public void setLogoPosition(int i) throws RemoteException {
        this.k = i;
        this.b.setLogoPosition(i);
    }

    public void setZoomPosition(int i) throws RemoteException {
        this.l = i;
        this.b.setZoomPosition(i);
    }

    public boolean isScaleControlsEnabled() throws RemoteException {
        return this.j;
    }

    public boolean isZoomControlsEnabled() throws RemoteException {
        return this.h;
    }

    public boolean isCompassEnabled() throws RemoteException {
        return this.i;
    }

    public boolean isMyLocationButtonEnabled() throws RemoteException {
        return this.f;
    }

    public boolean isScrollGesturesEnabled() throws RemoteException {
        return this.d;
    }

    public boolean isZoomGesturesEnabled() throws RemoteException {
        return this.g;
    }

    public boolean isTiltGesturesEnabled() throws RemoteException {
        return this.e;
    }

    public boolean isRotateGesturesEnabled() throws RemoteException {
        return this.c;
    }

    public int getLogoPosition() throws RemoteException {
        return this.k;
    }

    public int getZoomPosition() throws RemoteException {
        return this.l;
    }
}
