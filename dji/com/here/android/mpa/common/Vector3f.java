package com.here.android.mpa.common;

import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class Vector3f {
    @OnlineNative
    private float x;
    @OnlineNative
    private float y;
    @OnlineNative
    private float z;

    public Vector3f() {
        this.x = 0.0f;
        this.y = 0.0f;
        this.z = 0.0f;
    }

    public Vector3f(float f, float f2, float f3) {
        this.x = f;
        this.y = f2;
        this.z = f3;
    }

    public float getX() {
        return this.x;
    }

    public float getY() {
        return this.y;
    }

    public float getZ() {
        return this.z;
    }

    public void setX(float f) {
        this.x = f;
    }

    public void setY(float f) {
        this.y = f;
    }

    public void setZ(float f) {
        this.z = f;
    }

    public String toString() {
        return String.format("X: %f, Y: %f, Z: %f", new Object[]{Float.valueOf(this.x), Float.valueOf(this.y), Float.valueOf(this.z)});
    }

    public int hashCode() {
        return ((((Float.floatToIntBits(this.x) + 31) * 31) + Float.floatToIntBits(this.y)) * 31) + Float.floatToIntBits(this.z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof Vector3f)) {
            return false;
        }
        Vector3f vector3f = (Vector3f) obj;
        if (Float.floatToIntBits(this.x) != Float.floatToIntBits(vector3f.x)) {
            return false;
        }
        if (Float.floatToIntBits(this.y) != Float.floatToIntBits(vector3f.y)) {
            return false;
        }
        if (Float.floatToIntBits(this.z) != Float.floatToIntBits(vector3f.z)) {
            return false;
        }
        return true;
    }
}
