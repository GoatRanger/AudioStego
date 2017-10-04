package com.google.a.g;

import com.google.a.e;
import com.google.a.m;
import com.google.a.r;
import com.google.a.t;
import java.util.Arrays;
import java.util.Map;

public final class a extends q {
    static final char[] a = g.toCharArray();
    static final int[] b = new int[]{3, 6, 9, 96, 18, 66, 33, 36, 48, 72, 12, 24, 69, 81, 84, 21, 26, 41, 11, 14};
    private static final int e = 512;
    private static final int f = 384;
    private static final String g = "0123456789-$:/.+ABCD";
    private static final int h = 3;
    private static final char[] i = new char[]{'A', 'B', 'C', 'D'};
    private final StringBuilder j = new StringBuilder(20);
    private int[] k = new int[80];
    private int l = 0;

    public r a(int i, com.google.a.c.a aVar, Map<e, ?> map) throws m {
        Arrays.fill(this.k, 0);
        a(aVar);
        int b = b();
        this.j.setLength(0);
        int i2 = b;
        do {
            int c = c(i2);
            if (c != -1) {
                this.j.append((char) c);
                i2 += 8;
                if (this.j.length() > 1 && a(i, a[c])) {
                    break;
                }
            } else {
                throw m.a();
            }
        } while (i2 < this.l);
        int i3 = this.k[i2 - 1];
        int i4 = 0;
        for (c = -8; c < -1; c++) {
            i4 += this.k[i2 + c];
        }
        if (i2 >= this.l || i3 >= i4 / 2) {
            a(b);
            for (c = 0; c < this.j.length(); c++) {
                this.j.setCharAt(c, a[this.j.charAt(c)]);
            }
            if (a(i, this.j.charAt(0))) {
                if (!a(i, this.j.charAt(this.j.length() - 1))) {
                    throw m.a();
                } else if (this.j.length() <= 3) {
                    throw m.a();
                } else {
                    if (map == null || !map.containsKey(e.RETURN_CODABAR_START_END)) {
                        this.j.deleteCharAt(this.j.length() - 1);
                        this.j.deleteCharAt(0);
                    }
                    i4 = 0;
                    c = 0;
                    while (i4 < b) {
                        i3 = this.k[i4] + c;
                        i4++;
                        c = i3;
                    }
                    float f = (float) c;
                    while (b < i2 - 1) {
                        c += this.k[b];
                        b++;
                    }
                    float f2 = (float) c;
                    return new r(this.j.toString(), null, new t[]{new t(f, (float) i), new t(f2, (float) i)}, com.google.a.a.CODABAR);
                }
            }
            throw m.a();
        }
        throw m.a();
    }

    void a(int i) throws m {
        int i2 = 0;
        int[] iArr = new int[]{0, 0, 0, 0};
        int[] iArr2 = new int[]{0, 0, 0, 0};
        int length = this.j.length() - 1;
        int i3 = 0;
        int i4 = i;
        while (true) {
            int i5 = b[this.j.charAt(i3)];
            for (int i6 = 6; i6 >= 0; i6--) {
                int i7 = (i6 & 1) + ((i5 & 1) * 2);
                iArr[i7] = iArr[i7] + this.k[i4 + i6];
                iArr2[i7] = iArr2[i7] + 1;
                i5 >>= 1;
            }
            if (i3 >= length) {
                break;
            }
            i4 += 8;
            i3++;
        }
        int[] iArr3 = new int[4];
        int[] iArr4 = new int[4];
        for (i3 = 0; i3 < 2; i3++) {
            iArr4[i3] = 0;
            iArr4[i3 + 2] = (((iArr[i3] << 8) / iArr2[i3]) + ((iArr[i3 + 2] << 8) / iArr2[i3 + 2])) >> 1;
            iArr3[i3] = iArr4[i3 + 2];
            iArr3[i3 + 2] = ((iArr[i3 + 2] * 512) + f) / iArr2[i3 + 2];
        }
        loop3:
        while (true) {
            i4 = b[this.j.charAt(i2)];
            i3 = 6;
            while (i3 >= 0) {
                int i8 = (i3 & 1) + ((i4 & 1) * 2);
                int i9 = this.k[i + i3] << 8;
                if (i9 >= iArr4[i8] && i9 <= iArr3[i8]) {
                    i4 >>= 1;
                    i3--;
                }
            }
            if (i2 < length) {
                i += 8;
                i2++;
            } else {
                return;
            }
        }
        throw m.a();
    }

    private void a(com.google.a.c.a aVar) throws m {
        this.l = 0;
        int e = aVar.e(0);
        int a = aVar.a();
        if (e >= a) {
            throw m.a();
        }
        int i = 1;
        e = 0;
        for (int i2 = e; i2 < a; i2++) {
            if ((aVar.a(i2) ^ i) != 0) {
                e++;
            } else {
                b(e);
                i = i == 0 ? 1 : 0;
                e = 1;
            }
        }
        b(e);
    }

    private void b(int i) {
        this.k[this.l] = i;
        this.l++;
        if (this.l >= this.k.length) {
            Object obj = new int[(this.l * 2)];
            System.arraycopy(this.k, 0, obj, 0, this.l);
            this.k = obj;
        }
    }

    private int b() throws m {
        int i = 1;
        while (i < this.l) {
            int c = c(i);
            if (c != -1 && a(i, a[c])) {
                int i2 = 0;
                for (c = i; c < i + 7; c++) {
                    i2 += this.k[c];
                }
                if (i == 1 || this.k[i - 1] >= i2 / 2) {
                    return i;
                }
            }
            i += 2;
        }
        throw m.a();
    }

    static boolean a(char[] cArr, char c) {
        if (cArr == null) {
            return false;
        }
        for (char c2 : cArr) {
            if (c2 == c) {
                return true;
            }
        }
        return false;
    }

    private int c(int i) {
        int i2 = Integer.MAX_VALUE;
        int i3 = i + 7;
        if (i3 >= this.l) {
            return -1;
        }
        int[] iArr = this.k;
        int i4 = i;
        int i5 = Integer.MAX_VALUE;
        int i6 = 0;
        while (i4 < i3) {
            int i7 = iArr[i4];
            if (i7 < i5) {
                i5 = i7;
            }
            if (i7 <= i6) {
                i7 = i6;
            }
            i4 += 2;
            i6 = i7;
        }
        i5 = (i5 + i6) / 2;
        i4 = i + 1;
        i6 = 0;
        while (i4 < i3) {
            i7 = iArr[i4];
            if (i7 < i2) {
                i2 = i7;
            }
            if (i7 <= i6) {
                i7 = i6;
            }
            i4 += 2;
            i6 = i7;
        }
        i6 = (i2 + i6) / 2;
        i4 = 0;
        i2 = 0;
        i3 = 128;
        while (i4 < 7) {
            if ((i4 & 1) == 0) {
                i7 = i5;
            } else {
                i7 = i6;
            }
            i3 >>= 1;
            if (iArr[i + i4] > i7) {
                i7 = i2 | i3;
            } else {
                i7 = i2;
            }
            i4++;
            i2 = i7;
        }
        for (i7 = 0; i7 < b.length; i7++) {
            if (b[i7] == i2) {
                return i7;
            }
        }
        return -1;
    }
}
