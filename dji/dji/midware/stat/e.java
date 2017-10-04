package dji.midware.stat;

public class e extends StatBase {
    float a = 0.0f;
    long b = System.currentTimeMillis();

    public e(String str) {
        super(str);
    }

    public synchronized void addEvent(double d) {
        this.a = (float) (((double) this.a) + d);
    }

    public synchronized double getValue() {
        double d;
        long currentTimeMillis = System.currentTimeMillis() - this.b;
        if (currentTimeMillis == 0) {
            d = 0.0d;
        } else {
            d = (double) (this.a / ((float) (currentTimeMillis / 1000)));
        }
        return d;
    }

    public synchronized double getValueAndReset() {
        double value;
        value = getValue();
        this.a = 0.0f;
        this.b = System.currentTimeMillis();
        return value;
    }
}
