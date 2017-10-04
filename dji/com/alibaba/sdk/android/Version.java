package com.alibaba.sdk.android;

public class Version {
    private int a;
    private int b;
    private int c;

    public Version(int i, int i2, int i3) {
        this.a = i;
        this.b = i2;
        this.c = i3;
    }

    public int getMajor() {
        return this.a;
    }

    public int getMinor() {
        return this.b;
    }

    public int getMicro() {
        return this.c;
    }

    public int hashCode() {
        return ((((this.a + 31) * 31) + this.c) * 31) + this.b;
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
        Version version = (Version) obj;
        if (this.a != version.a) {
            return false;
        }
        if (this.c != version.c) {
            return false;
        }
        if (this.b != version.b) {
            return false;
        }
        return true;
    }

    public String toString() {
        return this.a + "." + this.b + "." + this.c;
    }
}
