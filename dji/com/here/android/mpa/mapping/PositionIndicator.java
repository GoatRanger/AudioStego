package com.here.android.mpa.mapping;

import com.here.android.mpa.common.Image;
import com.nokia.maps.am;
import com.nokia.maps.annotation.Online;
import com.nokia.maps.dw;
import com.nokia.maps.k;

@Online
public final class PositionIndicator {
    dw a;

    private PositionIndicator(dw dwVar) {
        this.a = dwVar;
    }

    public PositionIndicator setMarker(Image image) {
        this.a.a(image);
        return this;
    }

    public Image getMarker() {
        return this.a.a();
    }

    public PositionIndicator setVisible(boolean z) {
        this.a.b(z);
        return this;
    }

    public boolean isVisible() {
        return this.a.b();
    }

    public PositionIndicator setAccuracyIndicatorVisible(boolean z) {
        this.a.c(z);
        return this;
    }

    public boolean isAccuracyIndicatorVisible() {
        return this.a.c();
    }

    public int getZIndex() {
        return this.a.d();
    }

    public PositionIndicator setZIndex(int i) {
        this.a.a(i);
        return this;
    }

    static {
        dw.a(new k<PositionIndicator, dw>() {
            public dw a(PositionIndicator positionIndicator) {
                return positionIndicator.a;
            }
        }, new am<PositionIndicator, dw>() {
            public PositionIndicator a(dw dwVar) {
                if (dwVar != null) {
                    return new PositionIndicator(dwVar);
                }
                return null;
            }
        });
    }
}
