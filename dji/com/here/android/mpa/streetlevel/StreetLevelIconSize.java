package com.here.android.mpa.streetlevel;

import com.nokia.maps.am;
import com.nokia.maps.cw;
import com.nokia.maps.k;

public final class StreetLevelIconSize {
    private cw a;

    public enum ScalePolicy {
        FIXED(0),
        LINEAR(1),
        PERSPECTIVE(2);
        
        private int a;

        private ScalePolicy(int i) {
            this.a = i;
        }

        public int value() {
            return this.a;
        }
    }

    public StreetLevelIconSize(int i, int i2) {
        this.a = new cw(i, i2);
    }

    private StreetLevelIconSize(cw cwVar) {
        this.a = cwVar;
    }

    public StreetLevelIconSize setWidth(int i) {
        this.a.a(i);
        return this;
    }

    public int getWidth() {
        return this.a.a();
    }

    public StreetLevelIconSize setHeight(int i) {
        this.a.b(i);
        return this;
    }

    public int getHeight() {
        return this.a.b();
    }

    public StreetLevelIconSize setScalePolicy(ScalePolicy scalePolicy) {
        this.a.a(scalePolicy);
        return this;
    }

    public ScalePolicy getScalePolicy() {
        return this.a.c();
    }

    public StreetLevelIconSize setNearScale(float f) {
        this.a.a(f);
        return this;
    }

    public float getNearScale() {
        return this.a.d();
    }

    public StreetLevelIconSize setNearDistance(float f) {
        this.a.b(f);
        return this;
    }

    public float getNearDistance() {
        return this.a.e();
    }

    public StreetLevelIconSize setFarScale(float f) {
        this.a.c(f);
        return this;
    }

    public float getFarScale() {
        return this.a.f();
    }

    public StreetLevelIconSize setFarDistance(float f) {
        this.a.d(f);
        return this;
    }

    public float getFarDistance() {
        return this.a.g();
    }

    static {
        cw.a(new k<StreetLevelIconSize, cw>() {
            public cw a(StreetLevelIconSize streetLevelIconSize) {
                return streetLevelIconSize.a;
            }
        }, new am<StreetLevelIconSize, cw>() {
            public StreetLevelIconSize a(cw cwVar) {
                if (cwVar != null) {
                    return new StreetLevelIconSize(cwVar);
                }
                return null;
            }
        });
    }
}
