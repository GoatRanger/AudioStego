package com.here.a.a.a.a;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class an {
    public final String a;
    public final String b;
    public final double c;
    public final int d;
    public final ad<String> e;
    public final ad<String> f;
    private final Collection<String> g;

    public an(String str, String str2, double d, Integer num, String str3, String str4, Collection<String> collection) {
        if (str == null || str2 == null) {
            throw new NullPointerException("Ticket name and currency can't be null.");
        } else if (d <= 0.0d) {
            throw new IllegalArgumentException("Ticket price should be greater than zero.");
        } else {
            if (num == null) {
                num = Integer.valueOf(1);
            }
            if (num.intValue() < 1) {
                throw new IllegalArgumentException("Ticket amount should not be smaller than one.");
            }
            if (collection == null) {
                collection = Collections.emptyList();
            }
            this.a = str;
            this.b = str2;
            this.c = d;
            this.d = num.intValue();
            this.e = ad.b(str3);
            this.f = ad.b(str4);
            this.g = collection;
        }
    }

    public Collection<String> a() {
        return Collections.unmodifiableCollection(this.g);
    }

    public static an fromJSON(o oVar) {
        Collection asList;
        String[] split = oVar.a("@sec_ids", "").split("\\s");
        String i = oVar.i("@name");
        String i2 = oVar.i("@currency");
        double doubleValue = oVar.h("@price").doubleValue();
        Integer j = oVar.b("@amount") ? null : oVar.j("@amount");
        String a = oVar.a("@area", null);
        String a2 = oVar.a("@vendor", null);
        if (split.length > 0) {
            asList = Arrays.asList(split);
        } else {
            asList = null;
        }
        return new an(i, i2, doubleValue, j, a, a2, asList);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        an anVar = (an) obj;
        if (Double.compare(anVar.c, this.c) == 0 && this.a.equals(anVar.a) && this.b.equals(anVar.b) && this.e.equals(anVar.e) && this.f.equals(anVar.f) && this.d == anVar.d && this.g.equals(anVar.g)) {
            return true;
        }
        return false;
    }

    public int hashCode() {
        int hashCode = (this.a.hashCode() * 31) + this.b.hashCode();
        long doubleToLongBits = Double.doubleToLongBits(this.c);
        return (((((((((hashCode * 31) + ((int) (doubleToLongBits ^ (doubleToLongBits >>> 32)))) * 31) + this.e.hashCode()) * 31) + this.f.hashCode()) * 31) + this.d) * 31) + this.g.hashCode();
    }
}
