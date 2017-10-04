package com.here.android.mpa.urbanmobility;

import com.nokia.maps.a.ay;
import com.nokia.maps.am;
import com.nokia.maps.annotation.HybridPlus;
import java.util.Collection;

@HybridPlus
public final class TicketCollection {
    private ay a;

    private TicketCollection(ay ayVar) {
        if (ayVar == null) {
            throw new IllegalArgumentException("Impl can't be null.");
        }
        this.a = ayVar;
    }

    public Collection<Ticket> getTickets() {
        return this.a.a();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((TicketCollection) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode() + 31;
    }

    static {
        ay.a(new am<TicketCollection, ay>() {
            public TicketCollection a(ay ayVar) {
                return new TicketCollection(ayVar);
            }
        });
    }
}
