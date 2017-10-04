package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.x;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class Line {
    private x a;

    private Line(x xVar) {
        if (xVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = xVar;
    }

    public String getName() {
        return this.a.a();
    }

    public String getDirection() {
        return this.a.b();
    }

    public LineCategory getLineCategory() {
        return this.a.c();
    }

    public LineStyle getStyle() {
        return this.a.d();
    }

    public Operator getOperator() {
        return this.a.e();
    }

    public boolean isBikeAllowed() {
        return this.a.g();
    }

    public boolean isBarrierFree() {
        return this.a.f();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Line) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        x.a(new am<Line, x>() {
            public Line a(x xVar) {
                return new Line(xVar);
            }
        });
    }
}
