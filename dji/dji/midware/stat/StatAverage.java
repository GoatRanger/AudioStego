package dji.midware.stat;

public class StatAverage extends StatBase {
    float sum = 0.0f;
    int times = 0;

    public StatAverage(String str) {
        super(str);
    }

    public synchronized void addEvent(double d) {
        this.sum = (float) (((double) this.sum) + d);
        this.times++;
    }

    public synchronized double getValue() {
        double d;
        if (this.times == 0) {
            d = 0.0d;
        } else {
            d = (double) (this.sum / ((float) this.times));
        }
        return d;
    }

    public synchronized double getValueAndReset() {
        float f;
        if (this.times == 0) {
            f = 0.0f;
        } else {
            f = this.sum / ((float) this.times);
        }
        this.times = 0;
        this.sum = (float) null;
        return (double) f;
    }
}
