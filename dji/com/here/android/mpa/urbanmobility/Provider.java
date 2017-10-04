package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.aj;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public class Provider {
    private aj a;

    private Provider(aj ajVar) {
        if (ajVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = ajVar;
    }

    public String getName() {
        return this.a.a();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Provider) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        aj.a(new am<Provider, aj>() {
            public Provider a(aj ajVar) {
                return new Provider(ajVar);
            }
        });
    }
}
