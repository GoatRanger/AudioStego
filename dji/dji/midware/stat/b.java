package dji.midware.stat;

public class b extends StatBase {
    private static final double b = -1.0d;
    double a = -1.0d;

    public b(String str) {
        super(str);
    }

    public synchronized void addEvent(double d) {
        this.a = d;
    }

    public synchronized double getValue() {
        return this.a;
    }

    public synchronized double getValueAndReset() {
        double d;
        d = this.a;
        this.a = -1.0d;
        return d;
    }
}
