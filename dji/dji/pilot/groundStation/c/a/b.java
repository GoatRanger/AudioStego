package dji.pilot.groundStation.c.a;

import dji.pilot.groundStation.c.a.a.a;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Serializable;
import java.io.StreamTokenizer;
import java.lang.reflect.Array;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.Vector;

public class b implements Serializable, Cloneable {
    private static final long d = 1;
    private double[][] a;
    private int b;
    private int c;

    public b(int i, int i2) {
        this.b = i;
        this.c = i2;
        this.a = (double[][]) Array.newInstance(Double.TYPE, new int[]{i, i2});
    }

    public b(int i, int i2, double d) {
        this.b = i;
        this.c = i2;
        this.a = (double[][]) Array.newInstance(Double.TYPE, new int[]{i, i2});
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                this.a[i3][i4] = d;
            }
        }
    }

    public b a(b bVar) {
        return this.b == this.c ? new a(this).a(bVar) : new c(this).a(bVar);
    }

    public b a() {
        return a(c(this.b, this.b));
    }

    public b(double[][] dArr) {
        int i = 0;
        this.b = dArr.length;
        this.c = dArr[0].length;
        while (i < this.b) {
            if (dArr[i].length != this.c) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
            i++;
        }
        this.a = dArr;
    }

    public b(double[][] dArr, int i, int i2) {
        this.a = dArr;
        this.b = i;
        this.c = i2;
    }

    public b(double[] dArr, int i) {
        int length;
        this.b = i;
        if (i != 0) {
            length = dArr.length / i;
        } else {
            length = 0;
        }
        this.c = length;
        if (this.c * i != dArr.length) {
            throw new IllegalArgumentException("Array length must be a multiple of m.");
        }
        this.a = (double[][]) Array.newInstance(Double.TYPE, new int[]{i, this.c});
        for (int i2 = 0; i2 < i; i2++) {
            for (length = 0; length < this.c; length++) {
                this.a[i2][length] = dArr[(length * i) + i2];
            }
        }
    }

    public static b a(double[][] dArr) {
        int length = dArr.length;
        int length2 = dArr[0].length;
        b bVar = new b(length, length2);
        double[][] c = bVar.c();
        for (int i = 0; i < length; i++) {
            if (dArr[i].length != length2) {
                throw new IllegalArgumentException("All rows must have the same length.");
            }
            for (int i2 = 0; i2 < length2; i2++) {
                c[i][i2] = dArr[i][i2];
            }
        }
        return bVar;
    }

    public b b() {
        b bVar = new b(this.b, this.c);
        double[][] c = bVar.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = this.a[i][i2];
            }
        }
        return bVar;
    }

    public Object clone() {
        return b();
    }

    public double[][] c() {
        return this.a;
    }

    public double[][] d() {
        double[][] dArr = (double[][]) Array.newInstance(Double.TYPE, new int[]{this.b, this.c});
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                dArr[i][i2] = this.a[i][i2];
            }
        }
        return dArr;
    }

    public double[] e() {
        double[] dArr = new double[(this.b * this.c)];
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                dArr[(this.b * i2) + i] = this.a[i][i2];
            }
        }
        return dArr;
    }

    public double[] f() {
        double[] dArr = new double[(this.b * this.c)];
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                dArr[(this.c * i) + i2] = this.a[i][i2];
            }
        }
        return dArr;
    }

    public int g() {
        return this.b;
    }

    public int h() {
        return this.c;
    }

    public double a(int i, int i2) {
        return this.a[i][i2];
    }

    public b a(int i, int i2, int i3, int i4) {
        b bVar = new b((i2 - i) + 1, (i4 - i3) + 1);
        double[][] c = bVar.c();
        for (int i5 = i; i5 <= i2; i5++) {
            int i6 = i3;
            while (i6 <= i4) {
                try {
                    c[i5 - i][i6 - i3] = this.a[i5][i6];
                    i6++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Submatrix indices");
                }
            }
        }
        return bVar;
    }

    public b a(int[] iArr, int[] iArr2) {
        b bVar = new b(iArr.length, iArr2.length);
        double[][] c = bVar.c();
        int i = 0;
        while (i < iArr.length) {
            try {
                for (int i2 = 0; i2 < iArr2.length; i2++) {
                    c[i][i2] = this.a[iArr[i]][iArr2[i2]];
                }
                i++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Submatrix indices");
            }
        }
        return bVar;
    }

    public b a(int i, int i2, int[] iArr) {
        b bVar = new b((i2 - i) + 1, iArr.length);
        double[][] c = bVar.c();
        for (int i3 = i; i3 <= i2; i3++) {
            int i4 = 0;
            while (i4 < iArr.length) {
                try {
                    c[i3 - i][i4] = this.a[i3][iArr[i4]];
                    i4++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Submatrix indices");
                }
            }
        }
        return bVar;
    }

    public b a(int[] iArr, int i, int i2) {
        b bVar = new b(iArr.length, (i2 - i) + 1);
        double[][] c = bVar.c();
        int i3 = 0;
        while (i3 < iArr.length) {
            try {
                for (int i4 = i; i4 <= i2; i4++) {
                    c[i3][i4 - i] = this.a[iArr[i3]][i4];
                }
                i3++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Submatrix indices");
            }
        }
        return bVar;
    }

    public void a(int i, int i2, double d) {
        this.a[i][i2] = d;
    }

    public void a(int i, int i2, int i3, int i4, b bVar) {
        for (int i5 = i; i5 <= i2; i5++) {
            int i6 = i3;
            while (i6 <= i4) {
                try {
                    this.a[i5][i6] = bVar.a(i5 - i, i6 - i3);
                    i6++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Submatrix indices");
                }
            }
        }
    }

    public void a(int[] iArr, int[] iArr2, b bVar) {
        int i = 0;
        while (i < iArr.length) {
            try {
                for (int i2 = 0; i2 < iArr2.length; i2++) {
                    this.a[iArr[i]][iArr2[i2]] = bVar.a(i, i2);
                }
                i++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Submatrix indices");
            }
        }
    }

    public void a(int[] iArr, int i, int i2, b bVar) {
        int i3 = 0;
        while (i3 < iArr.length) {
            try {
                for (int i4 = i; i4 <= i2; i4++) {
                    this.a[iArr[i3]][i4] = bVar.a(i3, i4 - i);
                }
                i3++;
            } catch (ArrayIndexOutOfBoundsException e) {
                throw new ArrayIndexOutOfBoundsException("Submatrix indices");
            }
        }
    }

    public void a(int i, int i2, int[] iArr, b bVar) {
        for (int i3 = i; i3 <= i2; i3++) {
            int i4 = 0;
            while (i4 < iArr.length) {
                try {
                    this.a[i3][iArr[i4]] = bVar.a(i3 - i, i4);
                    i4++;
                } catch (ArrayIndexOutOfBoundsException e) {
                    throw new ArrayIndexOutOfBoundsException("Submatrix indices");
                }
            }
        }
    }

    public b i() {
        b bVar = new b(this.c, this.b);
        double[][] c = bVar.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i2][i] = this.a[i][i2];
            }
        }
        return bVar;
    }

    public double j() {
        double d = 0.0d;
        for (int i = 0; i < this.c; i++) {
            double d2 = 0.0d;
            for (int i2 = 0; i2 < this.b; i2++) {
                d2 += Math.abs(this.a[i2][i]);
            }
            d = Math.max(d, d2);
        }
        return d;
    }

    public double k() {
        double d = 0.0d;
        for (int i = 0; i < this.b; i++) {
            double d2 = 0.0d;
            for (int i2 = 0; i2 < this.c; i2++) {
                d2 += Math.abs(this.a[i][i2]);
            }
            d = Math.max(d, d2);
        }
        return d;
    }

    public double l() {
        double d = 0.0d;
        for (int i = 0; i < this.b; i++) {
            int i2 = 0;
            while (i2 < this.c) {
                double a = a.a(d, this.a[i][i2]);
                i2++;
                d = a;
            }
        }
        return d;
    }

    public b m() {
        b bVar = new b(this.b, this.c);
        double[][] c = bVar.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = -this.a[i][i2];
            }
        }
        return bVar;
    }

    public b b(b bVar) {
        m(bVar);
        b bVar2 = new b(this.b, this.c);
        double[][] c = bVar2.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = this.a[i][i2] + bVar.a[i][i2];
            }
        }
        return bVar2;
    }

    public b c(b bVar) {
        m(bVar);
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                this.a[i][i2] = this.a[i][i2] + bVar.a[i][i2];
            }
        }
        return this;
    }

    public b d(b bVar) {
        m(bVar);
        b bVar2 = new b(this.b, this.c);
        double[][] c = bVar2.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = this.a[i][i2] - bVar.a[i][i2];
            }
        }
        return bVar2;
    }

    public b e(b bVar) {
        m(bVar);
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                this.a[i][i2] = this.a[i][i2] - bVar.a[i][i2];
            }
        }
        return this;
    }

    public b f(b bVar) {
        m(bVar);
        b bVar2 = new b(this.b, this.c);
        double[][] c = bVar2.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = this.a[i][i2] * bVar.a[i][i2];
            }
        }
        return bVar2;
    }

    public b g(b bVar) {
        m(bVar);
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                this.a[i][i2] = this.a[i][i2] * bVar.a[i][i2];
            }
        }
        return this;
    }

    public b h(b bVar) {
        m(bVar);
        b bVar2 = new b(this.b, this.c);
        double[][] c = bVar2.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = this.a[i][i2] / bVar.a[i][i2];
            }
        }
        return bVar2;
    }

    public b i(b bVar) {
        m(bVar);
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                this.a[i][i2] = this.a[i][i2] / bVar.a[i][i2];
            }
        }
        return this;
    }

    public b j(b bVar) {
        m(bVar);
        b bVar2 = new b(this.b, this.c);
        double[][] c = bVar2.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = bVar.a[i][i2] / this.a[i][i2];
            }
        }
        return bVar2;
    }

    public b k(b bVar) {
        m(bVar);
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                this.a[i][i2] = bVar.a[i][i2] / this.a[i][i2];
            }
        }
        return this;
    }

    public b a(double d) {
        b bVar = new b(this.b, this.c);
        double[][] c = bVar.c();
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                c[i][i2] = this.a[i][i2] * d;
            }
        }
        return bVar;
    }

    public b b(double d) {
        for (int i = 0; i < this.b; i++) {
            for (int i2 = 0; i2 < this.c; i2++) {
                this.a[i][i2] = this.a[i][i2] * d;
            }
        }
        return this;
    }

    public b l(b bVar) {
        if (bVar.b != this.c) {
            throw new IllegalArgumentException("Matrix inner dimensions must agree.");
        }
        b bVar2 = new b(this.b, bVar.c);
        double[][] c = bVar2.c();
        double[] dArr = new double[this.c];
        for (int i = 0; i < bVar.c; i++) {
            int i2;
            for (i2 = 0; i2 < this.c; i2++) {
                dArr[i2] = bVar.a[i2][i];
            }
            for (i2 = 0; i2 < this.b; i2++) {
                double[] dArr2 = this.a[i2];
                double d = 0.0d;
                for (int i3 = 0; i3 < this.c; i3++) {
                    d += dArr2[i3] * dArr[i3];
                }
                c[i2][i] = d;
            }
        }
        return bVar2;
    }

    public double n() {
        double d = 0.0d;
        for (int i = 0; i < Math.min(this.b, this.c); i++) {
            d += this.a[i][i];
        }
        return d;
    }

    public static b b(int i, int i2) {
        b bVar = new b(i, i2);
        double[][] c = bVar.c();
        for (int i3 = 0; i3 < i; i3++) {
            for (int i4 = 0; i4 < i2; i4++) {
                c[i3][i4] = Math.random();
            }
        }
        return bVar;
    }

    public static b c(int i, int i2) {
        b bVar = new b(i, i2);
        double[][] c = bVar.c();
        int i3 = 0;
        while (i3 < i) {
            int i4 = 0;
            while (i4 < i2) {
                c[i3][i4] = i3 == i4 ? 1.0d : 0.0d;
                i4++;
            }
            i3++;
        }
        return bVar;
    }

    public void d(int i, int i2) {
        a(new PrintWriter(System.out, true), i, i2);
    }

    public void a(PrintWriter printWriter, int i, int i2) {
        NumberFormat decimalFormat = new DecimalFormat();
        decimalFormat.setDecimalFormatSymbols(new DecimalFormatSymbols(Locale.US));
        decimalFormat.setMinimumIntegerDigits(1);
        decimalFormat.setMaximumFractionDigits(i2);
        decimalFormat.setMinimumFractionDigits(i2);
        decimalFormat.setGroupingUsed(false);
        a(printWriter, decimalFormat, i + 2);
    }

    public void a(NumberFormat numberFormat, int i) {
        a(new PrintWriter(System.out, true), numberFormat, i);
    }

    public void a(PrintWriter printWriter, NumberFormat numberFormat, int i) {
        printWriter.println();
        for (int i2 = 0; i2 < this.b; i2++) {
            for (int i3 = 0; i3 < this.c; i3++) {
                String format = numberFormat.format(this.a[i2][i3]);
                int max = Math.max(1, i - format.length());
                for (int i4 = 0; i4 < max; i4++) {
                    printWriter.print(' ');
                }
                printWriter.print(format);
            }
            printWriter.println();
        }
        printWriter.println();
    }

    public static b a(BufferedReader bufferedReader) throws IOException {
        StreamTokenizer streamTokenizer = new StreamTokenizer(bufferedReader);
        streamTokenizer.resetSyntax();
        streamTokenizer.wordChars(0, 255);
        streamTokenizer.whitespaceChars(0, 32);
        streamTokenizer.eolIsSignificant(true);
        Vector vector = new Vector();
        do {
        } while (streamTokenizer.nextToken() == 10);
        if (streamTokenizer.ttype == -1) {
            throw new IOException("Unexpected EOF on matrix read.");
        }
        int i;
        do {
            vector.addElement(Double.valueOf(streamTokenizer.sval));
        } while (streamTokenizer.nextToken() == -3);
        int size = vector.size();
        Object obj = new double[size];
        for (i = 0; i < size; i++) {
            obj[i] = ((Double) vector.elementAt(i)).doubleValue();
        }
        vector = new Vector();
        vector.addElement(obj);
        while (streamTokenizer.nextToken() == -3) {
            obj = new double[size];
            vector.addElement(obj);
            int i2 = 0;
            while (i2 < size) {
                i = i2 + 1;
                obj[i2] = Double.valueOf(streamTokenizer.sval).doubleValue();
                if (streamTokenizer.nextToken() == -3) {
                    i2 = i;
                } else if (i < size) {
                    throw new IOException("Row " + vector.size() + " is too short.");
                }
            }
            throw new IOException("Row " + vector.size() + " is too long.");
        }
        Object[] objArr = new double[vector.size()][];
        vector.copyInto(objArr);
        return new b(objArr);
    }

    private void m(b bVar) {
        if (bVar.b != this.b || bVar.c != this.c) {
            throw new IllegalArgumentException("Matrix dimensions must agree.");
        }
    }
}
