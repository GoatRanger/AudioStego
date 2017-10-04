package cn.sharesdk.framework.utils;

import java.io.IOException;

public abstract class e implements Escaper {
    private static final ThreadLocal<char[]> a = new ThreadLocal<char[]>() {
        protected /* synthetic */ Object initialValue() {
            return a();
        }

        protected char[] a() {
            return new char[1024];
        }
    };

    protected abstract char[] a(int i);

    protected int a(CharSequence charSequence, int i, int i2) {
        while (i < i2) {
            int b = b(charSequence, i, i2);
            if (b < 0 || a(b) != null) {
                break;
            }
            i += Character.isSupplementaryCodePoint(b) ? 2 : 1;
        }
        return i;
    }

    public String escape(String str) {
        int length = str.length();
        int a = a((CharSequence) str, 0, length);
        return a == length ? str : a(str, a);
    }

    protected final String a(String str, int i) {
        int b;
        int length = str.length();
        int i2 = 0;
        char[] cArr = (char[]) a.get();
        int i3 = 0;
        while (i < length) {
            b = b(str, i, length);
            if (b < 0) {
                throw new IllegalArgumentException("Trailing high surrogate at end of input");
            }
            Object a = a(b);
            if (a != null) {
                int i4 = i - i2;
                int length2 = (i3 + i4) + a.length;
                if (cArr.length < length2) {
                    cArr = a(cArr, i3, (length2 + (length - i)) + 32);
                }
                if (i4 > 0) {
                    str.getChars(i2, i, cArr, i3);
                    i3 += i4;
                }
                if (a.length > 0) {
                    System.arraycopy(a, 0, cArr, i3, a.length);
                    i3 += a.length;
                }
            }
            b = (Character.isSupplementaryCodePoint(b) ? 2 : 1) + i;
            i = a((CharSequence) str, b, length);
            i2 = b;
        }
        b = length - i2;
        if (b > 0) {
            b += i3;
            if (cArr.length < b) {
                cArr = a(cArr, i3, b);
            }
            str.getChars(i2, length, cArr, i3);
            i3 = b;
        }
        return new String(cArr, 0, i3);
    }

    public Appendable escape(final Appendable appendable) {
        c.a(appendable);
        return new Appendable(this) {
            int a = -1;
            char[] b = new char[2];
            final /* synthetic */ e d;

            public Appendable append(CharSequence charSequence) throws IOException {
                return append(charSequence, 0, charSequence.length());
            }

            /* JADX WARNING: inconsistent code. */
            /* Code decompiled incorrectly, please refer to instructions dump. */
            public java.lang.Appendable append(java.lang.CharSequence r6, int r7, int r8) throws java.io.IOException {
                /*
                r5 = this;
                r4 = -1;
                if (r7 >= r8) goto L_0x0052;
            L_0x0003:
                r0 = r5.a;
                if (r0 == r4) goto L_0x008c;
            L_0x0007:
                r0 = r7 + 1;
                r1 = r6.charAt(r7);
                r2 = java.lang.Character.isLowSurrogate(r1);
                if (r2 != 0) goto L_0x002c;
            L_0x0013:
                r0 = new java.lang.IllegalArgumentException;
                r2 = new java.lang.StringBuilder;
                r2.<init>();
                r3 = "Expected low surrogate character but got ";
                r2 = r2.append(r3);
                r1 = r2.append(r1);
                r1 = r1.toString();
                r0.<init>(r1);
                throw r0;
            L_0x002c:
                r2 = r5.d;
                r3 = r5.a;
                r3 = (char) r3;
                r1 = java.lang.Character.toCodePoint(r3, r1);
                r1 = r2.a(r1);
                if (r1 == 0) goto L_0x0053;
            L_0x003b:
                r2 = r1.length;
                r5.a(r1, r2);
                r7 = r7 + 1;
            L_0x0041:
                r5.a = r4;
            L_0x0043:
                r1 = r5.d;
                r1 = r1.a(r6, r0, r8);
                if (r1 <= r7) goto L_0x0050;
            L_0x004b:
                r0 = r2;
                r0.append(r6, r7, r1);
            L_0x0050:
                if (r1 != r8) goto L_0x005c;
            L_0x0052:
                return r5;
            L_0x0053:
                r1 = r2;
                r2 = r5.a;
                r2 = (char) r2;
                r1.append(r2);
                goto L_0x0041;
            L_0x005c:
                r0 = cn.sharesdk.framework.utils.e.b(r6, r1, r8);
                if (r0 >= 0) goto L_0x0066;
            L_0x0062:
                r0 = -r0;
                r5.a = r0;
                goto L_0x0052;
            L_0x0066:
                r2 = r5.d;
                r2 = r2.a(r0);
                if (r2 == 0) goto L_0x007d;
            L_0x006e:
                r3 = r2.length;
                r5.a(r2, r3);
            L_0x0072:
                r0 = java.lang.Character.isSupplementaryCodePoint(r0);
                if (r0 == 0) goto L_0x008a;
            L_0x0078:
                r0 = 2;
            L_0x0079:
                r7 = r1 + r0;
                r0 = r7;
                goto L_0x0043;
            L_0x007d:
                r2 = r5.b;
                r3 = 0;
                r2 = java.lang.Character.toChars(r0, r2, r3);
                r3 = r5.b;
                r5.a(r3, r2);
                goto L_0x0072;
            L_0x008a:
                r0 = 1;
                goto L_0x0079;
            L_0x008c:
                r0 = r7;
                goto L_0x0043;
                */
                throw new UnsupportedOperationException("Method not decompiled: cn.sharesdk.framework.utils.e.1.append(java.lang.CharSequence, int, int):java.lang.Appendable");
            }

            public Appendable append(char c) throws IOException {
                char[] a;
                if (this.a != -1) {
                    if (Character.isLowSurrogate(c)) {
                        a = this.d.a(Character.toCodePoint((char) this.a, c));
                        if (a != null) {
                            a(a, a.length);
                        } else {
                            appendable.append((char) this.a);
                            appendable.append(c);
                        }
                        this.a = -1;
                    } else {
                        throw new IllegalArgumentException("Expected low surrogate character but got '" + c + "' with value " + c);
                    }
                } else if (Character.isHighSurrogate(c)) {
                    this.a = c;
                } else if (Character.isLowSurrogate(c)) {
                    throw new IllegalArgumentException("Unexpected low surrogate character '" + c + "' with value " + c);
                } else {
                    a = this.d.a(c);
                    if (a != null) {
                        a(a, a.length);
                    } else {
                        appendable.append(c);
                    }
                }
                return this;
            }

            private void a(char[] cArr, int i) throws IOException {
                for (int i2 = 0; i2 < i; i2++) {
                    appendable.append(cArr[i2]);
                }
            }
        };
    }

    protected static final int b(CharSequence charSequence, int i, int i2) {
        if (i < i2) {
            int i3 = i + 1;
            int charAt = charSequence.charAt(i);
            if (charAt < 55296 || charAt > 57343) {
                return charAt;
            }
            if (charAt > 56319) {
                throw new IllegalArgumentException("Unexpected low surrogate character '" + charAt + "' with value " + charAt + " at index " + (i3 - 1));
            } else if (i3 == i2) {
                return -charAt;
            } else {
                char charAt2 = charSequence.charAt(i3);
                if (Character.isLowSurrogate(charAt2)) {
                    return Character.toCodePoint(charAt, charAt2);
                }
                throw new IllegalArgumentException("Expected low surrogate but got char '" + charAt2 + "' with value " + charAt2 + " at index " + i3);
            }
        }
        throw new IndexOutOfBoundsException("Index exceeds specified range");
    }

    private static final char[] a(char[] cArr, int i, int i2) {
        Object obj = new char[i2];
        if (i > 0) {
            System.arraycopy(cArr, 0, obj, 0, i);
        }
        return obj;
    }
}
