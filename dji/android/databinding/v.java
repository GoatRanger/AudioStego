package android.databinding;

import java.io.Serializable;

public class v<T> extends a implements Serializable {
    static final long a = 1;
    private T b;

    public v(T t) {
        this.b = t;
    }

    public T b() {
        return this.b;
    }

    public void a(T t) {
        if (t != this.b) {
            this.b = t;
            a();
        }
    }
}
