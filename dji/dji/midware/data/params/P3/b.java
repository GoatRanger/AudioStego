package dji.midware.data.params.P3;

public class b {
    public Number a;
    public Number b;
    public Number c;

    public b(Number number, Number number2, Number number3) {
        this.a = number;
        this.b = number2;
        this.c = number3;
    }

    public String toString() {
        return "minValue=" + this.a + " maxValue=" + this.b + " defaultValue=" + this.c;
    }
}
