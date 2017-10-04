package com.here.android.mpa.venues3d;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class Margin {
    private int a;
    private int b;
    private int c;
    private int d;

    public Margin(int i, int i2, int i3, int i4) {
        this.a = i;
        this.b = i2;
        this.c = i3;
        this.d = i4;
    }

    public int getLeft() {
        return this.a;
    }

    public int getTop() {
        return this.b;
    }

    public int getRight() {
        return this.c;
    }

    public int getBottom() {
        return this.d;
    }
}
