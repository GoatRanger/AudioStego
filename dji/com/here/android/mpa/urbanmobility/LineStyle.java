package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.y;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class LineStyle {
    private y a;

    @HybridPlus
    @Deprecated
    public enum LineNameIconShape {
        RECTANGLE,
        ROUNDED_RECT,
        UNKNOWN
    }

    private LineStyle(y yVar) {
        if (yVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = yVar;
    }

    public int getColor() {
        return this.a.a();
    }

    public int getTextColor() {
        return this.a.b();
    }

    public int getOutlineColor() {
        return this.a.c();
    }

    @Deprecated
    public LineNameIconShape getIconShape() {
        return this.a.d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((LineStyle) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        y.a(new 1());
    }
}
