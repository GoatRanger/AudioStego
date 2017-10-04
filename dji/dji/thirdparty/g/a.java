package dji.thirdparty.g;

import com.alipay.sdk.j.i;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class a {
    private final boolean a;
    private final String b;
    private final ArrayList c = new ArrayList();

    public a(String str) {
        this.b = str;
        this.a = false;
    }

    public a(String str, boolean z) {
        this.b = str;
        this.a = z;
    }

    public static final a a() {
        return new a("ignore", false);
    }

    public void a(String str) throws e {
        this.c.add(str);
        if (this.a) {
            throw new e(str);
        }
    }

    public void a(String str, int i) throws e {
        a(str + ": " + a(i));
    }

    public String toString() {
        Writer stringWriter = new StringWriter();
        a(new PrintWriter(stringWriter));
        return stringWriter.getBuffer().toString();
    }

    public void b() {
        a(new PrintWriter(new OutputStreamWriter(System.out)));
    }

    public void a(PrintWriter printWriter) {
        printWriter.println("Format Compliance: " + this.b);
        if (this.c.size() == 0) {
            printWriter.println("\tNo comments.");
        } else {
            for (int i = 0; i < this.c.size(); i++) {
                printWriter.println("\t" + (i + 1) + ": " + this.c.get(i));
            }
        }
        printWriter.println("");
        printWriter.flush();
    }

    private String a(int i) {
        return i + " (" + Integer.toHexString(i) + ")";
    }

    public boolean a(String str, byte[] bArr, byte[] bArr2) throws e {
        if (bArr.length != bArr2.length) {
            a(str + ": Unexpected length: (expected: " + bArr.length + ", actual: " + bArr2.length + ")");
            return false;
        }
        for (int i = 0; i < bArr.length; i++) {
            if (bArr[i] != bArr2[i]) {
                a(str + ": Unexpected value: (expected: " + a(bArr[i]) + ", actual: " + a(bArr2[i]) + ")");
                return false;
            }
        }
        return true;
    }

    public boolean a(String str, int i, int i2, int i3) throws e {
        if (i3 >= i && i3 <= i2) {
            return true;
        }
        a(str + ": bounds check: " + i + " <= " + i3 + " <= " + i2 + ": false");
        return false;
    }

    public boolean a(String str, int i, int i2) throws e {
        return a(str, new int[]{i}, i2);
    }

    public boolean a(String str, int[] iArr, int i) throws e {
        int i2;
        for (int i3 : iArr) {
            if (i == i3) {
                return true;
            }
        }
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append(str + ": Unexpected value: (valid: ");
        if (iArr.length > 1) {
            stringBuffer.append("{");
        }
        for (i2 = 0; i2 < iArr.length; i2++) {
            if (i2 > 0) {
                stringBuffer.append(", ");
            }
            stringBuffer.append(a(iArr[i2]));
        }
        if (iArr.length > 1) {
            stringBuffer.append(i.d);
        }
        stringBuffer.append(", actual: " + a(i) + ")");
        a(stringBuffer.toString());
        return false;
    }
}
