package com.autonavi.amap.mapcore;

public class FPoint {
    public float x;
    public float y;

    public FPoint(float f, float f2) {
        this.x = f;
        this.y = f2;
    }

    public boolean equals(Object obj) {
        FPoint fPoint = (FPoint) obj;
        if (fPoint != null && this.x == fPoint.x && this.y == fPoint.y) {
            return true;
        }
        return false;
    }
}
