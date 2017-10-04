package com.here.android.mpa.streetlevel;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Vector3f;
import com.here.android.mpa.common.ViewObject;
import com.nokia.maps.SelectedObject;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.annotation.HybridPlusNative;
import com.nokia.maps.k;

@HybridPlusNative
@HybridPlus
public final class StreetLevelSelectedObject {
    private SelectedObject a;

    public ViewObject getObject() {
        return this.a.a();
    }

    public GeoCoordinate getPosition() {
        return this.a.b();
    }

    public Vector3f getNormal() {
        return this.a.c();
    }

    private StreetLevelSelectedObject(SelectedObject selectedObject) {
        this.a = selectedObject;
    }

    static {
        SelectedObject.a(new k<StreetLevelSelectedObject, SelectedObject>() {
            public SelectedObject a(StreetLevelSelectedObject streetLevelSelectedObject) {
                return streetLevelSelectedObject.a;
            }
        }, new am<StreetLevelSelectedObject, SelectedObject>() {
            public StreetLevelSelectedObject a(SelectedObject selectedObject) {
                if (selectedObject != null) {
                    return new StreetLevelSelectedObject(selectedObject);
                }
                return null;
            }
        });
    }
}
