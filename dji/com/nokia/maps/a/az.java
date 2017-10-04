package com.nokia.maps.a;

import com.here.a.a.a.a.an;
import com.here.android.mpa.urbanmobility.Ticket;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class az {
    private static am<Ticket, az> e;
    private String a;
    private String b;
    private double c;
    private String d;

    protected az(an anVar) {
        this.a = anVar.a;
        this.b = anVar.b;
        this.c = anVar.c;
        this.d = (String) anVar.e.c("");
    }

    public String a() {
        return this.a;
    }

    public String b() {
        return this.b;
    }

    public double c() {
        return this.c;
    }

    public String d() {
        return this.d;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        az azVar = (az) obj;
        if (Double.compare(azVar.c, this.c) == 0 && this.a.equals(azVar.a) && this.b.equals(azVar.b) && this.d.equals(azVar.d)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.a.hashCode() * 31) + this.b.hashCode();
        long doubleToLongBits = Double.doubleToLongBits(this.c);
        return (((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.d.hashCode();
    }

    public static void a(am<Ticket, az> amVar) {
        e = amVar;
    }

    static Ticket a(az azVar) {
        if (azVar != null) {
            return (Ticket) e.a(azVar);
        }
        return null;
    }

    static {
        ce.a(Ticket.class);
    }
}
