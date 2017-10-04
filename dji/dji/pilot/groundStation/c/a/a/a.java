package dji.pilot.groundStation.c.a.a;

public class a {
    public static double a(double d, double d2) {
        double d3;
        if (Math.abs(d) > Math.abs(d2)) {
            d3 = d2 / d;
            return Math.sqrt((d3 * d3) + 1.0d) * Math.abs(d);
        } else if (d2 == 0.0d) {
            return 0.0d;
        } else {
            d3 = d / d2;
            return Math.sqrt((d3 * d3) + 1.0d) * Math.abs(d2);
        }
    }
}
