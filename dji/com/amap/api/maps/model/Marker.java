package com.amap.api.maps.model;

import android.os.RemoteException;
import com.autonavi.amap.mapcore.IPoint;
import com.autonavi.amap.mapcore.interfaces.IMarkerDelegate;
import java.util.ArrayList;

public final class Marker {
    private IMarkerDelegate a;

    public Marker(IMarkerDelegate iMarkerDelegate) {
        this.a = iMarkerDelegate;
    }

    public void setPeriod(int i) {
        try {
            this.a.setPeriod(i);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int getPeriod() {
        try {
            return this.a.getPeriod();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIcons(ArrayList<BitmapDescriptor> arrayList) {
        try {
            this.a.setIcons(arrayList);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public ArrayList<BitmapDescriptor> getIcons() {
        try {
            return this.a.getIcons();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void remove() {
        try {
            this.a.remove();
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public void destroy() {
        try {
            if (this.a != null) {
                this.a.destroy();
            }
        } catch (Throwable th) {
            th.printStackTrace();
        }
    }

    public String getId() {
        try {
            return this.a.getId();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPerspective(boolean z) {
        try {
            this.a.setPerspective(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isPerspective() {
        try {
            return this.a.isPerspective();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setPosition(LatLng latLng) {
        try {
            this.a.setPosition(latLng);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public LatLng getPosition() {
        try {
            return this.a.getPosition();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setTitle(String str) {
        try {
            this.a.setTitle(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getTitle() {
        try {
            return this.a.getTitle();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setSnippet(String str) {
        try {
            this.a.setSnippet(str);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public String getSnippet() {
        try {
            return this.a.getSnippet();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setIcon(BitmapDescriptor bitmapDescriptor) {
        if (bitmapDescriptor != null) {
            try {
                this.a.setIcon(bitmapDescriptor);
            } catch (RemoteException e) {
                throw new RuntimeRemoteException(e);
            }
        }
    }

    public void setAnchor(float f, float f2) {
        try {
            this.a.setAnchor(f, f2);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setDraggable(boolean z) {
        try {
            this.a.setDraggable(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isDraggable() {
        return this.a.isDraggable();
    }

    public void showInfoWindow() {
        try {
            this.a.showInfoWindow();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void hideInfoWindow() {
        try {
            this.a.hideInfoWindow();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isInfoWindowShown() {
        return this.a.isInfoWindowShown();
    }

    public void setVisible(boolean z) {
        try {
            this.a.setVisible(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isVisible() {
        try {
            return this.a.isVisible();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean equals(Object obj) {
        try {
            if (obj instanceof Marker) {
                return this.a.equalsRemote(((Marker) obj).a);
            }
            return false;
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public int hashCode() {
        return this.a.hashCodeRemote();
    }

    public void setObject(Object obj) {
        this.a.setObject(obj);
    }

    public Object getObject() {
        return this.a.getObject();
    }

    public void setRotateAngle(float f) {
        try {
            this.a.setRotateAngle(f);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public float getRotateAngle() {
        return this.a.getRotateAngle();
    }

    public void setToTop() {
        try {
            this.a.set2Top();
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public void setGeoPoint(IPoint iPoint) {
        this.a.setGeoPoint(iPoint);
    }

    public IPoint getGeoPoint() {
        return this.a.getGeoPoint();
    }

    public void setFlat(boolean z) {
        try {
            this.a.setFlat(z);
        } catch (RemoteException e) {
            throw new RuntimeRemoteException(e);
        }
    }

    public boolean isFlat() {
        return this.a.isFlat();
    }

    public void setPositionByPixels(int i, int i2) {
        this.a.setPositionByPixels(i, i2);
    }

    public void setZIndex(float f) {
        this.a.setZIndex(f);
    }

    public float getZIndex() {
        return this.a.getZIndex();
    }
}
