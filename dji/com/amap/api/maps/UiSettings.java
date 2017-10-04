package com.amap.api.maps;

import android.os.RemoteException;
import com.amap.api.maps.model.RuntimeRemoteException;
import com.autonavi.amap.mapcore.interfaces.IUiSettingsDelegate;

public final class UiSettings {
    private final IUiSettingsDelegate a;

    UiSettings(IUiSettingsDelegate iUiSettingsDelegate) {
        this.a = iUiSettingsDelegate;
    }

    public void setScaleControlsEnabled(boolean z) {
        try {
            this.a.setScaleControlsEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomControlsEnabled(boolean z) {
        try {
            this.a.setZoomControlsEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setCompassEnabled(boolean z) {
        try {
            this.a.setCompassEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setMyLocationButtonEnabled(boolean z) {
        try {
            this.a.setMyLocationButtonEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setScrollGesturesEnabled(boolean z) {
        try {
            this.a.setScrollGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomGesturesEnabled(boolean z) {
        try {
            this.a.setZoomGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTiltGesturesEnabled(boolean z) {
        try {
            this.a.setTiltGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setRotateGesturesEnabled(boolean z) {
        try {
            this.a.setRotateGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setAllGesturesEnabled(boolean z) {
        try {
            this.a.setAllGesturesEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setLogoPosition(int i) {
        try {
            this.a.setLogoPosition(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setZoomPosition(int i) {
        try {
            this.a.setZoomPosition(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getZoomPosition() {
        try {
            return this.a.getZoomPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isScaleControlsEnabled() {
        try {
            return this.a.isScaleControlsEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isZoomControlsEnabled() {
        try {
            return this.a.isZoomControlsEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isCompassEnabled() {
        try {
            return this.a.isCompassEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isMyLocationButtonEnabled() {
        try {
            return this.a.isMyLocationButtonEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isScrollGesturesEnabled() {
        try {
            return this.a.isScrollGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isZoomGesturesEnabled() {
        try {
            return this.a.isZoomGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isTiltGesturesEnabled() {
        try {
            return this.a.isTiltGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isRotateGesturesEnabled() {
        try {
            return this.a.isRotateGesturesEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getLogoPosition() {
        try {
            return this.a.getLogoPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isIndoorSwitchEnabled() {
        try {
            return this.a.isIndoorSwitchEnabled();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIndoorSwitchEnabled(boolean z) {
        try {
            this.a.setIndoorSwitchEnabled(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }
}
