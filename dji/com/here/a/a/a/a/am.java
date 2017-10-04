package com.here.a.a.a.a;

import com.here.a.a.a.i;

public class am extends ak {
    private ab k;

    public am(ak akVar, ab abVar) {
        super(akVar);
        if (abVar == null) {
            throw new IllegalArgumentException("Departures can't be null.");
        }
        this.k = abVar;
    }

    public static am a(o oVar, i iVar) {
        return new am(ak.fromJSON(oVar.c("Stn")), ab.a(oVar, iVar));
    }

    public ab b() {
        return this.k;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        am amVar = (am) obj;
        if (super.equals(amVar) && this.k.equals(amVar.k)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (super.hashCode() * 31) + this.k.hashCode();
    }
}
