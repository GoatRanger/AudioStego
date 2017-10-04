package dji.thirdparty.g;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.util.ArrayList;

public class c {
    public static final int a = 0;
    public static final int b = 1;
    public static final int c = 2;
    public static final int d = 3;
    public static final int e = -1;
    public static final int f = -2;
    public static final String g = "Unknown";
    public static final String h = "None";
    public static final String i = "LZW";
    public static final String j = "PackBits";
    public static final String k = "JPEG";
    public static final String l = "RLE: Run-Length Encoding";
    public static final String m = "Photoshop";
    public static final String n = "PNG Filter";
    public static final String o = "CCITT Group 3 1-Dimensional Modified Huffman run-length encoding.";
    public static final String p = "CCITT Group 4";
    public static final String q = "CCITT 1D";
    private final float A;
    private final int B;
    private final float C;
    private final int D;
    private final boolean E;
    private final boolean F;
    private final boolean G;
    private final int H;
    private final String I;
    private final String r;
    private final int s;
    private final ArrayList t;
    private final b u;
    private final String v;
    private final int w;
    private final String x;
    private final int y;
    private final int z;

    public c(String str, int i, ArrayList arrayList, b bVar, String str2, int i2, String str3, int i3, int i4, float f, int i5, float f2, int i6, boolean z, boolean z2, boolean z3, int i7, String str4) {
        this.r = str;
        this.s = i;
        this.t = arrayList;
        this.u = bVar;
        this.v = str2;
        this.w = i2;
        this.x = str3;
        this.y = i3;
        this.z = i4;
        this.A = f;
        this.B = i5;
        this.C = f2;
        this.D = i6;
        this.E = z;
        this.F = z2;
        this.G = z3;
        this.H = i7;
        this.I = str4;
    }

    public int a() {
        return this.s;
    }

    public ArrayList b() {
        return new ArrayList(this.t);
    }

    public b c() {
        return this.u;
    }

    public String d() {
        return this.v;
    }

    public int e() {
        return this.w;
    }

    public String f() {
        return this.x;
    }

    public int g() {
        return this.y;
    }

    public int h() {
        return this.z;
    }

    public float i() {
        return this.A;
    }

    public int j() {
        return this.B;
    }

    public float k() {
        return this.C;
    }

    public int l() {
        return this.D;
    }

    public boolean m() {
        return this.E;
    }

    public int n() {
        return this.H;
    }

    public String o() {
        switch (this.H) {
            case -2:
                return "Unknown";
            case -1:
                return "Other";
            case 0:
                return "Black and White";
            case 1:
                return "Grayscale";
            case 2:
                return "RGB";
            case 3:
                return "CMYK";
            default:
                return "Unknown";
        }
    }

    public void p() {
        System.out.print(toString());
    }

    public String toString() {
        try {
            Writer stringWriter = new StringWriter();
            PrintWriter printWriter = new PrintWriter(stringWriter);
            a(printWriter, "");
            printWriter.flush();
            return stringWriter.toString();
        } catch (Exception e) {
            return "Image Data: Error";
        }
    }

    public void a(PrintWriter printWriter, String str) throws e, IOException {
        printWriter.println("Format Details: " + this.r);
        printWriter.println("Bits Per Pixel: " + this.s);
        printWriter.println("Comments: " + this.t.size());
        for (int i = 0; i < this.t.size(); i++) {
            printWriter.println("\t" + i + ": '" + ((String) this.t.get(i)) + "'");
        }
        printWriter.println("Format: " + this.u.a);
        printWriter.println("Format Name: " + this.v);
        printWriter.println("Compression Algorithm: " + this.I);
        printWriter.println("Height: " + this.w);
        printWriter.println("MimeType: " + this.x);
        printWriter.println("Number Of Images: " + this.y);
        printWriter.println("Physical Height Dpi: " + this.z);
        printWriter.println("Physical Height Inch: " + this.A);
        printWriter.println("Physical Width Dpi: " + this.B);
        printWriter.println("Physical Width Inch: " + this.C);
        printWriter.println("Width: " + this.D);
        printWriter.println("Is Progressive: " + this.E);
        printWriter.println("Is Transparent: " + this.F);
        printWriter.println("Color Type: " + o());
        printWriter.println("Uses Palette: " + this.G);
        printWriter.flush();
    }
}
