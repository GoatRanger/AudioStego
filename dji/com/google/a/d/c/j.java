package com.google.a.d.c;

import com.google.a.f;
import dji.common.flightcontroller.DJIFlightControllerDataType;
import dji.midware.util.a.b;
import java.util.Arrays;
import lecho.lib.hellocharts.model.h;

public final class j {
    static final char a = 'æ';
    static final char b = 'ç';
    static final char c = 'ë';
    static final char d = 'î';
    static final char e = 'ï';
    static final char f = 'ð';
    static final char g = 'þ';
    static final char h = 'þ';
    static final int i = 0;
    static final int j = 1;
    static final int k = 2;
    static final int l = 3;
    static final int m = 4;
    static final int n = 5;
    private static final char o = '';
    private static final char p = 'ì';
    private static final char q = 'í';
    private static final String r = "[)>\u001e05\u001d";
    private static final String s = "[)>\u001e06\u001d";
    private static final String t = "\u001e\u0004";

    private j() {
    }

    private static char a(char c, int i) {
        int i2 = (((i * 149) % 253) + 1) + c;
        return i2 <= 254 ? (char) i2 : (char) (i2 - 254);
    }

    public static String a(String str) {
        return a(str, l.FORCE_NONE, null, null);
    }

    public static String a(String str, l lVar, f fVar, f fVar2) {
        int i = 0;
        g[] gVarArr = new g[]{new a(), new c(), new m(), new n(), new f(), new b()};
        h hVar = new h(str);
        hVar.a(lVar);
        hVar.a(fVar, fVar2);
        if (str.startsWith(r) && str.endsWith(t)) {
            hVar.a((char) p);
            hVar.a(2);
            hVar.a += r.length();
        } else if (str.startsWith(s) && str.endsWith(t)) {
            hVar.a((char) q);
            hVar.a(2);
            hVar.a += s.length();
        }
        while (hVar.h()) {
            gVarArr[i].a(hVar);
            if (hVar.f() >= 0) {
                i = hVar.f();
                hVar.g();
            }
        }
        int e = hVar.e();
        hVar.k();
        int i2 = hVar.j().i();
        if (!(e >= i2 || i == 0 || i == 5)) {
            hVar.a('þ');
        }
        StringBuilder d = hVar.d();
        if (d.length() < i2) {
            d.append(o);
        }
        while (d.length() < i2) {
            d.append(a((char) o, d.length() + 1));
        }
        return hVar.d().toString();
    }

    static int a(CharSequence charSequence, int i, int i2) {
        if (i >= charSequence.length()) {
            return i2;
        }
        float[] fArr;
        int i3;
        if (i2 == 0) {
            fArr = new float[]{0.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.25f};
        } else {
            fArr = new float[]{1.0f, 2.0f, 2.0f, 2.0f, 2.0f, 2.25f};
            fArr[i2] = 0.0f;
        }
        int i4 = 0;
        while (i + i4 != charSequence.length()) {
            char charAt = charSequence.charAt(i + i4);
            i4++;
            if (a(charAt)) {
                fArr[0] = (float) (((double) fArr[0]) + 0.5d);
            } else if (b(charAt)) {
                fArr[0] = (float) ((int) Math.ceil((double) fArr[0]));
                fArr[0] = fArr[0] + 2.0f;
            } else {
                fArr[0] = (float) ((int) Math.ceil((double) fArr[0]));
                fArr[0] = fArr[0] + 1.0f;
            }
            if (d(charAt)) {
                fArr[1] = fArr[1] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[1] = fArr[1] + 2.6666667f;
            } else {
                fArr[1] = fArr[1] + b.b;
            }
            if (e(charAt)) {
                fArr[2] = fArr[2] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[2] = fArr[2] + 2.6666667f;
            } else {
                fArr[2] = fArr[2] + b.b;
            }
            if (f(charAt)) {
                fArr[3] = fArr[3] + 0.6666667f;
            } else if (b(charAt)) {
                fArr[3] = fArr[3] + 4.3333335f;
            } else {
                fArr[3] = fArr[3] + 3.3333333f;
            }
            if (h(charAt)) {
                fArr[4] = fArr[4] + h.l;
            } else if (b(charAt)) {
                fArr[4] = fArr[4] + 4.25f;
            } else {
                fArr[4] = fArr[4] + 3.25f;
            }
            if (i(charAt)) {
                fArr[5] = fArr[5] + DJIFlightControllerDataType.DJIVirtualStickVerticalControlMaxVelocity;
            } else {
                fArr[5] = fArr[5] + 1.0f;
            }
            if (i4 >= 4) {
                int[] iArr = new int[6];
                byte[] bArr = new byte[6];
                a(fArr, iArr, Integer.MAX_VALUE, bArr);
                int a = a(bArr);
                if (iArr[0] < iArr[5] && iArr[0] < iArr[1] && iArr[0] < iArr[2] && iArr[0] < iArr[3] && iArr[0] < iArr[4]) {
                    return 0;
                }
                if (iArr[5] < iArr[0] || ((bArr[1] + bArr[2]) + bArr[3]) + bArr[4] == 0) {
                    return 5;
                }
                if (a == 1 && bArr[4] > (byte) 0) {
                    return 4;
                }
                if (a == 1 && bArr[2] > (byte) 0) {
                    return 2;
                }
                if (a == 1 && bArr[3] > (byte) 0) {
                    return 3;
                }
                if (iArr[1] + 1 < iArr[0] && iArr[1] + 1 < iArr[5] && iArr[1] + 1 < iArr[4] && iArr[1] + 1 < iArr[2]) {
                    if (iArr[1] < iArr[3]) {
                        return 1;
                    }
                    if (iArr[1] == iArr[3]) {
                        for (i3 = (i + i4) + 1; i3 < charSequence.length(); i3++) {
                            char charAt2 = charSequence.charAt(i3);
                            if (g(charAt2)) {
                                return 3;
                            }
                            if (!f(charAt2)) {
                                break;
                            }
                        }
                        return 1;
                    }
                }
            }
        }
        byte[] bArr2 = new byte[6];
        int[] iArr2 = new int[6];
        i3 = a(fArr, iArr2, Integer.MAX_VALUE, bArr2);
        i4 = a(bArr2);
        if (iArr2[0] == i3) {
            return 0;
        }
        if (i4 == 1 && bArr2[5] > (byte) 0) {
            return 5;
        }
        if (i4 == 1 && bArr2[4] > (byte) 0) {
            return 4;
        }
        if (i4 == 1 && bArr2[2] > (byte) 0) {
            return 2;
        }
        if (i4 != 1 || bArr2[3] <= (byte) 0) {
            return 1;
        }
        return 3;
    }

    private static int a(float[] fArr, int[] iArr, int i, byte[] bArr) {
        Arrays.fill(bArr, (byte) 0);
        int i2 = i;
        for (int i3 = 0; i3 < 6; i3++) {
            iArr[i3] = (int) Math.ceil((double) fArr[i3]);
            int i4 = iArr[i3];
            if (i2 > i4) {
                Arrays.fill(bArr, (byte) 0);
                i2 = i4;
            }
            if (i2 == i4) {
                bArr[i3] = (byte) (bArr[i3] + 1);
            }
        }
        return i2;
    }

    private static int a(byte[] bArr) {
        int i = 0;
        int i2 = 0;
        while (i < 6) {
            i2 += bArr[i];
            i++;
        }
        return i2;
    }

    static boolean a(char c) {
        return c >= '0' && c <= '9';
    }

    static boolean b(char c) {
        return c >= '' && c <= 'ÿ';
    }

    private static boolean d(char c) {
        return c == ' ' || ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z'));
    }

    private static boolean e(char c) {
        return c == ' ' || ((c >= '0' && c <= '9') || (c >= 'a' && c <= 'z'));
    }

    private static boolean f(char c) {
        return g(c) || c == ' ' || ((c >= '0' && c <= '9') || (c >= 'A' && c <= 'Z'));
    }

    private static boolean g(char c) {
        return c == '\r' || c == '*' || c == '>';
    }

    private static boolean h(char c) {
        return c >= ' ' && c <= '^';
    }

    private static boolean i(char c) {
        return false;
    }

    public static int a(CharSequence charSequence, int i) {
        int i2 = 0;
        int length = charSequence.length();
        if (i < length) {
            char charAt = charSequence.charAt(i);
            while (a(charAt) && i < length) {
                i2++;
                i++;
                if (i < length) {
                    charAt = charSequence.charAt(i);
                }
            }
        }
        return i2;
    }

    static void c(char c) {
        String toHexString = Integer.toHexString(c);
        throw new IllegalArgumentException("Illegal character: " + c + " (0x" + ("0000".substring(0, 4 - toHexString.length()) + toHexString) + ')');
    }
}
