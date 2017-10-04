package com.nokia.maps.a;

import com.here.a.a.a.a.ae;
import com.here.android.mpa.urbanmobility.Provider;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class aj {
    private static am<Provider, aj> b;
    private String a;

    public aj(ae aeVar) {
        this.a = aeVar.a;
    }

    public String a() {
        return this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return this.a.equals(((aj) obj).a);
    }

    public int hashCode() {
        return this.a.hashCode();
    }

    public static void a(am<Provider, aj> amVar) {
        b = amVar;
    }

    static Provider a(aj ajVar) {
        if (ajVar != null) {
            return (Provider) b.a(ajVar);
        }
        return null;
    }

    static {
        ce.a(Provider.class);
    }
}
