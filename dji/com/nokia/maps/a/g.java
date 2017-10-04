package com.nokia.maps.a;

import com.here.a.a.a.a.ac;
import com.here.a.a.a.a.af;
import com.here.a.a.a.a.d;
import com.here.a.a.a.a.s;
import com.here.android.mpa.urbanmobility.AlternativeDeparture;
import com.nokia.maps.am;
import com.nokia.maps.ce;
import java.util.Date;

public class g extends a {
    private static am<AlternativeDeparture, g> i;

    protected g(d dVar) {
        if (dVar.c.c()) {
            s sVar = (s) dVar.c.b();
            this.a = x.a(new x(sVar));
            this.b = (String) sVar.c.c("");
            if (sVar.d.c()) {
                this.c = ai.a(new ai((ac) sVar.d.b()));
            }
            this.g = ((Boolean) sVar.f.c(Boolean.valueOf(false))).booleanValue();
            this.h = ((Boolean) sVar.g.c(Boolean.valueOf(false))).booleanValue();
        } else {
            this.b = "";
            this.g = false;
            this.h = false;
        }
        if (dVar.a.c()) {
            this.d = (Date) dVar.a.b();
        }
        if (dVar.b.c()) {
            this.f = true;
            this.e = ak.a(new ak((af) dVar.b.b()));
            return;
        }
        this.f = false;
    }

    public static void a(am<AlternativeDeparture, g> amVar) {
        i = amVar;
    }

    static AlternativeDeparture a(g gVar) {
        if (gVar != null) {
            return (AlternativeDeparture) i.a(gVar);
        }
        return null;
    }

    static {
        ce.a(AlternativeDeparture.class);
    }
}
