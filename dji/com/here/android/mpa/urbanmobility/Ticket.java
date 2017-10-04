package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.az;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;

@HybridPlus
public final class Ticket {
    private az a;

    private Ticket(az azVar) {
        if (azVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = azVar;
    }

    public String getName() {
        return this.a.a();
    }

    public String getCurrency() {
        return this.a.b();
    }

    public double getPrice() {
        return this.a.c();
    }

    public String getArea() {
        return this.a.d();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((Ticket) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        az.a(new am<Ticket, az>() {
            public Ticket a(az azVar) {
                return new Ticket(azVar);
            }
        });
    }
}
