package com.nokia.maps;

public class dj {
    private int a = 0;
    private int b = 0;

    public dj(int i, int i2) {
        this.a = i;
        this.b = i2;
    }

    public int a() {
        return this.a;
    }

    public int b() {
        return this.b;
    }

    public int hashCode() {
        return ((this.b + 31) * 31) + this.a;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        dj djVar = (dj) obj;
        if (this.b != djVar.b) {
            return false;
        }
        if (this.a != djVar.a) {
            return false;
        }
        return true;
    }
}
