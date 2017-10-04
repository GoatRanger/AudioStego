package com.e;

import java.util.Locale;

public class ay {
    public String a = "";
    public String b = "";
    public int c = 0;
    public int d = 0;
    public int e = 0;
    public int f = 0;
    public int g = 0;
    public int h = 0;
    public int i = 0;
    public int j = -113;
    public int k = 9;

    protected ay(int i) {
        this.k = i;
    }

    public boolean a(ay ayVar) {
        if (ayVar == null) {
            return false;
        }
        switch (ayVar.k) {
            case 1:
                return this.k == 1 && ayVar.c == this.c && ayVar.d == this.d && ayVar.b != null && ayVar.b.equals(this.b);
            case 2:
                return this.k == 2 && ayVar.i == this.i && ayVar.h == this.h && ayVar.g == this.g;
            default:
                return false;
        }
    }

    public String toString() {
        String str = "unknown";
        switch (this.k) {
            case 1:
                return String.format(Locale.US, "GSM lac=%d, cid=%d, mnc=%s", new Object[]{Integer.valueOf(this.c), Integer.valueOf(this.d), this.b});
            case 2:
                return String.format(Locale.US, "CDMA bid=%d, nid=%d, sid=%d", new Object[]{Integer.valueOf(this.i), Integer.valueOf(this.h), Integer.valueOf(this.g)});
            default:
                return str;
        }
    }
}
