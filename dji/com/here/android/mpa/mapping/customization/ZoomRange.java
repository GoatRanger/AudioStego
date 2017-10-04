package com.here.android.mpa.mapping.customization;

import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class ZoomRange {
    private double a;
    private double b;

    public ZoomRange(double d, double d2) {
        this.a = d;
        this.b = d2;
    }

    public double getMin() {
        return this.a;
    }

    public void setMin(double d) {
        this.a = d;
    }

    public double getMax() {
        return this.b;
    }

    public void setMax(double d) {
        this.b = d;
    }
}
