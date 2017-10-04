package dji.pilot2.usercenter.activate;

public class a {
    private static a b = new a();
    public boolean a;
    private String c = "1.1";
    private String d = "2.0";
    private boolean e = false;
    private int f;
    private String g;
    private b h = b.SDR;
    private String i = "";
    private boolean j = false;
    private boolean k;

    public enum a {
        BACK_PRESSED
    }

    public enum b {
        WIFI,
        SDR
    }

    public static a getInstance() {
        return b;
    }

    private a() {
    }

    public void a(int i) {
        this.f = i;
    }

    public int a() {
        return this.f;
    }

    public void a(String str) {
        this.g = str;
    }

    public String b() {
        return this.g;
    }

    public String c() {
        return this.d;
    }

    public String d() {
        return this.c;
    }

    public void b(String str) {
        this.c = str;
    }

    public void c(String str) {
        this.d = str;
    }

    public boolean e() {
        return this.e;
    }

    public void a(boolean z) {
        this.e = z;
    }

    public void a(b bVar) {
        this.h = bVar;
    }

    public b f() {
        return this.h;
    }

    public String g() {
        return this.i;
    }

    public void d(String str) {
        this.i = str;
    }

    public boolean h() {
        return this.j;
    }

    public void b(boolean z) {
        this.j = z;
    }

    public Boolean i() {
        boolean z = this.k || this.a;
        return Boolean.valueOf(z);
    }

    public void c(boolean z) {
        this.k = z;
    }

    public void d(boolean z) {
        this.a = z;
    }

    public boolean j() {
        return this.a;
    }
}
