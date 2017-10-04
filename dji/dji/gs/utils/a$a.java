package dji.gs.utils;

class a$a {
    public double a;
    public double b;
    public double c;
    public double d;

    public a$a(double d, double d2, double d3, double d4) {
        this.a = Math.min(d2, d4);
        this.b = Math.max(d, d3);
        this.c = Math.max(d2, d4);
        this.d = Math.min(d, d3);
    }
}
