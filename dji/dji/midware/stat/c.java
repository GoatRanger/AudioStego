package dji.midware.stat;

public class c extends StatBase {
    private static final double b = -1000000.0d;
    double a = b;

    public c(String str) {
        super(str);
    }

    public synchronized void addEvent(double d) {
        if (d > this.a) {
            this.a = d;
        }
    }

    public synchronized double getValue() {
        return this.a;
    }

    public synchronized double getValueAndReset() {
        double value;
        value = getValue();
        this.a = b;
        return value;
    }
}
