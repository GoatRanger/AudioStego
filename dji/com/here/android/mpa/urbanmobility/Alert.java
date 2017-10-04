package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.f;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;
import java.util.Date;

@HybridPlus
public final class Alert {
    private f a;

    private Alert(f fVar) {
        if (fVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = fVar;
    }

    public String getId() {
        return this.a.a();
    }

    public Operator getOperator() {
        return this.a.b();
    }

    public Collection<Line> getLines() {
        return this.a.c();
    }

    public String getInfo() {
        return this.a.d();
    }

    public Date getValidFrom() {
        return this.a.e();
    }

    public Date getValidTill() {
        return this.a.f();
    }

    public String getSourceUrl() {
        return this.a.g();
    }

    public String getImageUrl() {
        return this.a.h();
    }

    public String getImageCaption() {
        return this.a.i();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Alert) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        f.a(new am<Alert, f>() {
            public Alert a(f fVar) {
                return new Alert(fVar);
            }
        });
    }
}
