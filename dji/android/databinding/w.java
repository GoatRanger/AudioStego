package android.databinding;

import java.util.List;

public interface w<T> extends List<T> {

    public static abstract class a<T extends w> {
        public abstract void a(T t);

        public abstract void a(T t, int i, int i2);

        public abstract void a(T t, int i, int i2, int i3);

        public abstract void b(T t, int i, int i2);

        public abstract void c(T t, int i, int i2);
    }

    void a(a<? extends w<T>> aVar);

    void b(a<? extends w<T>> aVar);
}
