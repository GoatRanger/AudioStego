package com.google.a.g;

import com.google.android.gms.location.places.Place;
import java.util.Arrays;

public final class b extends r {
    private static final char[] a = new char[]{'A', 'B', 'C', 'D'};
    private static final char[] b = new char[]{'T', 'N', '*', 'E'};
    private static final char[] c = new char[]{'/', ':', '+', '.'};

    public boolean[] a(String str) {
        if (str.length() < 2) {
            throw new IllegalArgumentException("Codabar should start/end with start/stop symbols");
        }
        char toUpperCase = Character.toUpperCase(str.charAt(0));
        char toUpperCase2 = Character.toUpperCase(str.charAt(str.length() - 1));
        boolean z = a.a(a, toUpperCase) && a.a(a, toUpperCase2);
        boolean z2;
        if (a.a(b, toUpperCase) && a.a(b, toUpperCase2)) {
            z2 = true;
        } else {
            z2 = false;
        }
        if (z || r3) {
            int i = 20;
            int i2 = 1;
            while (i2 < str.length() - 1) {
                if (Character.isDigit(str.charAt(i2)) || str.charAt(i2) == '-' || str.charAt(i2) == '$') {
                    i += 9;
                } else if (a.a(c, str.charAt(i2))) {
                    i += 10;
                } else {
                    throw new IllegalArgumentException("Cannot encode : '" + str.charAt(i2) + '\'');
                }
                i2++;
            }
            boolean[] zArr = new boolean[((str.length() - 1) + i)];
            i2 = 0;
            i = 0;
            while (i2 < str.length()) {
                int i3;
                int i4;
                boolean z3;
                int i5;
                toUpperCase2 = Character.toUpperCase(str.charAt(i2));
                if (i2 == 0 || i2 == str.length() - 1) {
                    switch (toUpperCase2) {
                        case '*':
                            toUpperCase2 = 'C';
                            break;
                        case 'E':
                            toUpperCase2 = 'D';
                            break;
                        case Place.TYPE_REAL_ESTATE_AGENCY /*78*/:
                            toUpperCase2 = 'B';
                            break;
                        case Place.TYPE_SHOPPING_MALL /*84*/:
                            toUpperCase2 = 'A';
                            break;
                    }
                }
                int i6 = 0;
                while (i6 < a.a.length) {
                    if (toUpperCase2 == a.a[i6]) {
                        i3 = a.b[i6];
                        i6 = 0;
                        i4 = 0;
                        z3 = true;
                        while (i6 < 7) {
                            zArr[i] = z3;
                            i5 = i + 1;
                            if (((i3 >> (6 - i6)) & 1) != 0 || i4 == 1) {
                                i6++;
                                i4 = 0;
                                z3 = z3;
                                i = i5;
                            } else {
                                i4++;
                                i = i5;
                            }
                        }
                        if (i2 < str.length() - 1) {
                            zArr[i] = false;
                            i++;
                        }
                        i2++;
                    } else {
                        i6++;
                    }
                }
                i3 = 0;
                i6 = 0;
                i4 = 0;
                z3 = true;
                while (i6 < 7) {
                    zArr[i] = z3;
                    i5 = i + 1;
                    if (((i3 >> (6 - i6)) & 1) != 0) {
                    }
                    if (z3) {
                    }
                    i6++;
                    i4 = 0;
                    z3 = z3;
                    i = i5;
                }
                if (i2 < str.length() - 1) {
                    zArr[i] = false;
                    i++;
                }
                i2++;
            }
            return zArr;
        }
        throw new IllegalArgumentException("Codabar should start/end with " + Arrays.toString(a) + ", or start/end with " + Arrays.toString(b));
    }
}
