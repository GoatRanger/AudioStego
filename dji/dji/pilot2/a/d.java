package dji.pilot2.a;

public class d {
    private long a = 0;

    public synchronized long a() {
        long j;
        if (this.a == 0) {
            this.a = System.currentTimeMillis();
        }
        j = this.a;
        this.a = 1 + j;
        return j;
    }
}
