package dji.midware.stat;

public class f extends StatBase {
    double a = 0.0d;

    public f(String str) {
        super(str);
    }

    public synchronized void addEvent(double d) {
        this.a += d;
    }

    public synchronized double getValue() {
        return this.a;
    }

    public synchronized double getValueAndReset() {
        return getValue();
    }
}
