package dji.pilot.visual.a;

public class g$a {
    public boolean a = false;
    public boolean b = false;
    public boolean c = false;
    public boolean d = false;

    public void a() {
        this.a = false;
        this.b = false;
        this.c = false;
        this.d = false;
    }

    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public int hashCode() {
        int i;
        int i2 = 1;
        if (this.a) {
            i = 1;
        } else {
            i = 0;
        }
        int i3 = i * 31;
        if (this.b) {
            i = 1;
        } else {
            i = 0;
        }
        i3 = (i + i3) * 31;
        if (this.c) {
            i = 1;
        } else {
            i = 0;
        }
        i = (i + i3) * 31;
        if (!this.d) {
            i2 = 0;
        }
        return i + i2;
    }

    public String toString() {
        return "AvoidDisableTipFlag{mByAutoLanding=" + this.a + ", mByTripod=" + this.b + ", mBySwitchSensor=" + this.c + ", mByAttiLarge=" + this.d + '}';
    }
}
