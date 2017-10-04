package android.databinding;

import java.util.Map;

public interface x<K, V> extends Map<K, V> {

    public static abstract class a<T extends x<K, V>, K, V> {
        public abstract void a(T t, K k);
    }

    void a(a<? extends x<K, V>, K, V> aVar);

    void b(a<? extends x<K, V>, K, V> aVar);
}
