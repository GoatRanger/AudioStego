package com.nokia.maps;

import com.here.android.mpa.common.GeoCoordinate;
import com.here.android.mpa.common.Identifier;
import com.here.android.mpa.common.RoadElement.Attribute;
import com.here.android.mpa.common.RoadElement.FormOfWay;
import com.here.android.mpa.common.RoadElement.PluralType;
import com.nokia.maps.IdentifierImpl.a;
import com.nokia.maps.annotation.HybridPlus;
import com.nokia.maps.restrouting.DynamicSpeedInfo;
import com.nokia.maps.restrouting.Link;
import java.util.Date;
import java.util.EnumSet;
import java.util.List;

@HybridPlus
public class ea extends RoadElementImpl {
    private EnumSet<Attribute> a = null;
    private Identifier b = null;
    private String c;
    private String d;
    private float e;
    private float f;
    private float g;
    private Double h;
    private List<GeoCoordinate> i;
    private Date j;
    private Double k;
    private Double l;

    ea(Link link, long j, long j2) {
        super(0);
        this.a = a(link);
        this.b = IdentifierImpl.a(new IdentifierImpl(a.STRING, link.c()));
        this.c = link.j();
        this.d = link.k();
        this.e = link.i().floatValue();
        DynamicSpeedInfo a = link.a();
        if (a != null) {
            this.f = a.a().floatValue();
            this.g = a.b().floatValue();
        }
        this.h = link.e();
        this.i = ee.b(link.d());
        this.k = link.f();
        this.l = link.g();
        this.j = new Date(((j2 - this.l.longValue()) * 1000) + j);
    }

    public List<GeoCoordinate> d() {
        return this.i;
    }

    public boolean isValid() {
        return this.b != null;
    }

    public EnumSet<Attribute> a() {
        return this.a;
    }

    public FormOfWay b() {
        return FormOfWay.UNDEFINED;
    }

    public boolean isPlural() {
        return false;
    }

    public PluralType getPluralType() {
        return PluralType.NONE;
    }

    public String getRoadName() {
        return this.c;
    }

    public String getRouteName() {
        return this.d;
    }

    public float getSpeedLimit() {
        return this.e;
    }

    public float getDefaultSpeed() {
        return this.g;
    }

    public int getNumberOfLanes() {
        return 0;
    }

    public boolean isPedestrian() {
        return false;
    }

    public Date c() {
        return new Date(this.j.getTime());
    }

    public double getGeometryLength() {
        return this.h.doubleValue();
    }

    public Identifier e() {
        return this.b;
    }

    public float getStaticSpeed() {
        return this.f;
    }

    public int hashCode() {
        int i = 0;
        int hashCode = ((((this.d == null ? 0 : this.d.hashCode()) + (((this.c == null ? 0 : this.c.hashCode()) + (((this.l == null ? 0 : this.l.hashCode()) + (((this.k == null ? 0 : this.k.hashCode()) + (((this.h == null ? 0 : this.h.hashCode()) + (((this.b == null ? 0 : this.b.hashCode()) + (((this.i == null ? 0 : this.i.hashCode()) + (((((((this.a == null ? 0 : this.a.hashCode()) + 31) * 31) + ((int) this.f)) * 31) + ((int) this.g)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31)) * 31) + Float.floatToIntBits(this.e)) * 31;
        if (this.j != null) {
            i = this.j.hashCode();
        }
        return hashCode + i;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        ea eaVar = (ea) obj;
        if (this.a == null) {
            if (eaVar.a != null) {
                return false;
            }
        } else if (!this.a.equals(eaVar.a)) {
            return false;
        }
        if (this.f != eaVar.f) {
            return false;
        }
        if (this.i == null) {
            if (eaVar.i != null) {
                return false;
            }
        } else if (!this.i.equals(eaVar.i)) {
            return false;
        }
        if (this.b == null) {
            if (eaVar.b != null) {
                return false;
            }
        } else if (!this.b.equals(eaVar.b)) {
            return false;
        }
        if (this.h == null) {
            if (eaVar.h != null) {
                return false;
            }
        } else if (!this.h.equals(eaVar.h)) {
            return false;
        }
        if (this.k == null) {
            if (eaVar.k != null) {
                return false;
            }
        } else if (!this.k.equals(eaVar.k)) {
            return false;
        }
        if (this.l == null) {
            if (eaVar.l != null) {
                return false;
            }
        } else if (!this.l.equals(eaVar.l)) {
            return false;
        }
        if (this.c == null) {
            if (eaVar.c != null) {
                return false;
            }
        } else if (!this.c.equals(eaVar.c)) {
            return false;
        }
        if (this.d == null) {
            if (eaVar.d != null) {
                return false;
            }
        } else if (!this.d.equals(eaVar.d)) {
            return false;
        }
        if (Float.floatToIntBits(this.e) != Float.floatToIntBits(eaVar.e)) {
            return false;
        }
        if (this.j == null) {
            if (eaVar.j != null) {
                return false;
            }
            return true;
        } else if (this.j.equals(eaVar.j)) {
            return true;
        } else {
            return false;
        }
    }

    private EnumSet<Attribute> a(Link link) {
        EnumSet<Attribute> noneOf = EnumSet.noneOf(Attribute.class);
        for (String str : link.b()) {
            if (ee.d(str) != null) {
                noneOf.add(ee.d(str));
            }
        }
        return noneOf;
    }

    Double f() {
        return this.k;
    }

    Double g() {
        return this.l;
    }
}
