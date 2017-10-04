package com.amap.api.mapcore.util;

import uk.co.senab.photoview.IPhotoView;

public class de {
    public int[] a;
    public int b;
    public boolean c;

    public de() {
        this(true, 16);
    }

    public de(boolean z, int i) {
        this.c = z;
        this.a = new int[i];
    }

    public void a(int i) {
        int[] iArr = this.a;
        if (this.b == iArr.length) {
            iArr = d(Math.max(8, (int) (((float) this.b) * IPhotoView.DEFAULT_MID_SCALE)));
        }
        int i2 = this.b;
        this.b = i2 + 1;
        iArr[i2] = i;
    }

    public int b(int i) {
        if (i >= this.b) {
            throw new IndexOutOfBoundsException("index can't be >= size: " + i + " >= " + this.b);
        }
        Object obj = this.a;
        int i2 = obj[i];
        this.b--;
        if (this.c) {
            System.arraycopy(obj, i + 1, obj, i, this.b - i);
        } else {
            obj[i] = obj[this.b];
        }
        return i2;
    }

    public void a() {
        this.b = 0;
    }

    public int[] c(int i) {
        int i2 = this.b + i;
        if (i2 > this.a.length) {
            d(Math.max(8, i2));
        }
        return this.a;
    }

    protected int[] d(int i) {
        Object obj = new int[i];
        System.arraycopy(this.a, 0, obj, 0, Math.min(this.b, obj.length));
        this.a = obj;
        return obj;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof de)) {
            return false;
        }
        de deVar = (de) obj;
        int i = this.b;
        if (i != deVar.b) {
            return false;
        }
        for (int i2 = 0; i2 < i; i2++) {
            if (this.a[i2] != deVar.a[i2]) {
                return false;
            }
        }
        return true;
    }

    public String toString() {
        if (this.b == 0) {
            return "[]";
        }
        int[] iArr = this.a;
        StringBuilder stringBuilder = new StringBuilder(32);
        stringBuilder.append('[');
        stringBuilder.append(iArr[0]);
        for (int i = 1; i < this.b; i++) {
            stringBuilder.append(", ");
            stringBuilder.append(iArr[i]);
        }
        stringBuilder.append(']');
        return stringBuilder.toString();
    }
}
