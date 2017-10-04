package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.ai;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class Operator {
    private ai a;

    private Operator(ai aiVar) {
        if (aiVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = aiVar;
    }

    public String getId() {
        return this.a.a();
    }

    public String getName() {
        return this.a.b();
    }

    public CoverageType getType() {
        return this.a.c();
    }

    public Link getAgencyLink() {
        return this.a.d();
    }

    public Link getAgencyLogoLink() {
        return this.a.e();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Operator) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        ai.a(new am<Operator, ai>() {
            public Operator a(ai aiVar) {
                return new Operator(aiVar);
            }
        });
    }
}
