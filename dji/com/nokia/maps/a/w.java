package com.nokia.maps.a;

import com.here.a.a.a.a.s;
import com.here.a.a.a.a.s.a;
import com.here.a.a.a.i.d;
import com.here.android.mpa.common.TransitType;
import com.here.android.mpa.urbanmobility.LineCategory;
import com.here.android.mpa.urbanmobility.LineCategory$LineCategoryIconSize;
import com.here.android.mpa.urbanmobility.TransportType;
import com.nokia.maps.am;
import com.nokia.maps.ce;

public class w {
    private static am<LineCategory, w> d;
    private s a;
    private String b;
    private TransportType c;

    protected w(s sVar) {
        this.a = sVar;
        this.b = (String) sVar.e.c("");
        this.c = sVar.b.c() ? ba.a((d) sVar.b.b()) : TransportType.UNKNOWN;
    }

    public String a() {
        return this.b;
    }

    public String a(LineCategory$LineCategoryIconSize lineCategory$LineCategoryIconSize) {
        return (String) this.a.a(b(lineCategory$LineCategoryIconSize)).c(null);
    }

    public TransportType b() {
        return this.c;
    }

    public TransitType c() {
        return ba.b(this.c);
    }

    private static a b(LineCategory$LineCategoryIconSize lineCategory$LineCategoryIconSize) {
        if (LineCategory$LineCategoryIconSize._12x12.equals(lineCategory$LineCategoryIconSize)) {
            return a._12x12;
        }
        if (LineCategory$LineCategoryIconSize._34x34.equals(lineCategory$LineCategoryIconSize)) {
            return a._34x34;
        }
        throw new RuntimeException("Unknown size: " + lineCategory$LineCategoryIconSize);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        w wVar = (w) obj;
        if (this.b.equals(wVar.b) && this.c == wVar.c) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        return (this.b.hashCode() * 31) + this.c.hashCode();
    }

    public static void a(am<LineCategory, w> amVar) {
        d = amVar;
    }

    static LineCategory a(w wVar) {
        if (wVar != null) {
            return (LineCategory) d.a(wVar);
        }
        return null;
    }

    static {
        ce.a(LineCategory.class);
    }
}
