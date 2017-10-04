package dji.pilot.countrycode.model;

public class a {
    private static final String c = "CountryCodeLocationBean";
    private static final double d = -500.0d;
    public double a = -501.0d;
    public double b = -501.0d;

    public boolean a() {
        return this.b < d || this.a < d || Double.isNaN(this.b) || Double.isNaN(this.a);
    }

    public boolean b() {
        return this.a < d || Double.isNaN(this.a);
    }

    public boolean c() {
        return this.b < d || Double.isNaN(this.b);
    }

    public static boolean a(double d) {
        return d < d || Double.isNaN(d);
    }
}
