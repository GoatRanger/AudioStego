package lecho.lib.hellocharts.c;

import android.util.Log;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import lecho.lib.hellocharts.h.c;

public class k {
    public static final int a = 0;
    private static final String b = "ValueFormatterHelper";
    private int c = Integer.MIN_VALUE;
    private char[] d = new char[0];
    private char[] e = new char[0];
    private char f = '.';

    public void a() {
        NumberFormat instance = NumberFormat.getInstance();
        if (instance instanceof DecimalFormat) {
            this.f = ((DecimalFormat) instance).getDecimalFormatSymbols().getDecimalSeparator();
        }
    }

    public int b() {
        return this.c;
    }

    public k a(int i) {
        this.c = i;
        return this;
    }

    public char[] c() {
        return this.d;
    }

    public k a(char[] cArr) {
        if (cArr != null) {
            this.d = cArr;
        }
        return this;
    }

    public char[] d() {
        return this.e;
    }

    public k b(char[] cArr) {
        if (cArr != null) {
            this.e = cArr;
        }
        return this;
    }

    public char e() {
        return this.f;
    }

    public k a(char c) {
        if ('\u0000' != c) {
            this.f = c;
        }
        return this;
    }

    public int a(char[] cArr, float f, int i, char[] cArr2) {
        int length;
        if (cArr2 != null) {
            length = cArr2.length;
            if (length > cArr.length) {
                Log.w(b, "Label length is larger than buffer size(64chars), some chars will be skipped!");
                length = cArr.length;
            }
            System.arraycopy(cArr2, 0, cArr, cArr.length - length, length);
            return length;
        }
        length = b(cArr, f, b(i));
        c(cArr);
        a(cArr, length);
        return (length + d().length) + c().length;
    }

    public int a(char[] cArr, float f, char[] cArr2) {
        return a(cArr, f, 0, cArr2);
    }

    public int a(char[] cArr, float f, int i) {
        return a(cArr, f, i, null);
    }

    public int b(char[] cArr, float f, int i) {
        return c.a(cArr, f, cArr.length - this.d.length, i, this.f);
    }

    public void c(char[] cArr) {
        if (this.d.length > 0) {
            System.arraycopy(this.d, 0, cArr, cArr.length - this.d.length, this.d.length);
        }
    }

    public void a(char[] cArr, int i) {
        if (this.e.length > 0) {
            System.arraycopy(this.e, 0, cArr, ((cArr.length - i) - this.d.length) - this.e.length, this.e.length);
        }
    }

    public int b(int i) {
        if (this.c < 0) {
            return i;
        }
        return this.c;
    }
}
