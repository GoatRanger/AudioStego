package android.databinding;

import android.databinding.x.a;
import android.support.v4.util.ArrayMap;
import java.util.Collection;

public class u<K, V> extends ArrayMap<K, V> implements x<K, V> {
    private transient r a;

    public void a(a<? extends x<K, V>, K, V> aVar) {
        if (this.a == null) {
            this.a = new r();
        }
        this.a.a((Object) aVar);
    }

    public void b(a<? extends x<K, V>, K, V> aVar) {
        if (this.a != null) {
            this.a.b((Object) aVar);
        }
    }

    public void clear() {
        if (!isEmpty()) {
            super.clear();
            a(null);
        }
    }

    public V put(K k, V v) {
        super.put(k, v);
        a((Object) k);
        return v;
    }

    public boolean removeAll(Collection<?> collection) {
        boolean z = false;
        for (Object indexOfKey : collection) {
            int indexOfKey2 = indexOfKey(indexOfKey);
            if (indexOfKey2 >= 0) {
                z = true;
                removeAt(indexOfKey2);
            }
        }
        return z;
    }

    public boolean retainAll(Collection<?> collection) {
        boolean z = false;
        for (int size = size() - 1; size >= 0; size--) {
            if (!collection.contains(keyAt(size))) {
                removeAt(size);
                z = true;
            }
        }
        return z;
    }

    public V removeAt(int i) {
        Object keyAt = keyAt(i);
        V removeAt = super.removeAt(i);
        if (removeAt != null) {
            a(keyAt);
        }
        return removeAt;
    }

    public V setValueAt(int i, V v) {
        Object keyAt = keyAt(i);
        V valueAt = super.setValueAt(i, v);
        a(keyAt);
        return valueAt;
    }

    private void a(Object obj) {
        if (this.a != null) {
            this.a.a(this, 0, obj);
        }
    }
}
