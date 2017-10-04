package com.here.android.mpa.common;

import com.nokia.maps.annotation.Online;
import com.nokia.maps.annotation.OnlineNative;

@Online
public final class ViewRect {
    private int a;
    private int b;
    private int c;
    private int d;

    @OnlineNative
    public ViewRect(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int getX() {
        return this.a;
    }

    public int getY() {
        return this.b;
    }

    public int getWidth() {
        return this.c;
    }

    public int getHeight() {
        return this.d;
    }

    public void setX(int i) {
        this.a = i;
    }

    public void setY(int i) {
        this.b = i;
    }

    public void setWidth(int i) {
        this.c = i;
    }

    public void setHeight(int i) {
        this.d = i;
    }

    public boolean isValid() {
        if (this.a < 0 || this.b < 0 || this.c <= 0 || this.d <= 0) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        return ((((((this.b + 31) * 31) + this.a) * 31) + this.c) * 31) + this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof ViewRect)) {
            return false;
        }
        ViewRect viewRect = (ViewRect) obj;
        if (this.a == viewRect.a && this.b == viewRect.b && this.c == viewRect.c && this.d == viewRect.d) {
            return true;
        }
        return false;
    }
}
