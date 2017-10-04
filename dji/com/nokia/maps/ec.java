package com.nokia.maps;

import com.here.android.mpa.routing.a;
import com.nokia.maps.annotation.Internal;
import java.util.ArrayList;
import java.util.List;

@Internal
public class ec extends BaseNativeObject {
    private static k<a, ec> a = null;
    private static am<a, ec> b = null;

    private native void a();

    static {
        ce.a(a.class);
    }

    public static void a(k<a, ec> kVar, am<a, ec> amVar) {
        a = kVar;
        b = amVar;
    }

    static a a(ec ecVar) {
        if (ecVar != null) {
            return (a) b.a(ecVar);
        }
        return null;
    }

    static List<a> a(List<ec> list) {
        List arrayList = new ArrayList();
        for (ec a : list) {
            arrayList.add(a(a));
        }
        return arrayList;
    }

    protected void finalize() {
        a();
    }
}
