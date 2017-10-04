package dji.pilot.d;

public class a {
    private static final int a = 8;
    private int[] b = new int[8];
    private int c = 0;

    public void a(int i) {
        if (this.b.length == this.c) {
            Object obj = new int[(this.c + this.c)];
            System.arraycopy(this.b, 0, obj, 0, this.c);
            this.b = obj;
        }
        int[] iArr = this.b;
        int i2 = this.c;
        this.c = i2 + 1;
        iArr[i2] = i;
    }

    public int b(int i) {
        if (i < this.c) {
            return this.b[i];
        }
        throw new IndexOutOfBoundsException("Invalid index " + i + ", size is " + this.c);
    }

    public int a() {
        return this.c;
    }

    public int[] a(int[] iArr) {
        if (iArr == null || iArr.length < this.c) {
            iArr = new int[this.c];
        }
        System.arraycopy(this.b, 0, iArr, 0, this.c);
        return iArr;
    }
}
