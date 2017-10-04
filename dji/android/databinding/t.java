package android.databinding;

import android.databinding.w.a;
import java.util.ArrayList;
import java.util.Collection;

public class t<T> extends ArrayList<T> implements w<T> {
    private transient q a = new q();

    public void a(a aVar) {
        if (this.a == null) {
            this.a = new q();
        }
        this.a.a((Object) aVar);
    }

    public void b(a aVar) {
        if (this.a != null) {
            this.a.b((Object) aVar);
        }
    }

    public boolean add(T t) {
        super.add(t);
        a(size() - 1, 1);
        return true;
    }

    public void add(int i, T t) {
        super.add(i, t);
        a(i, 1);
    }

    public boolean addAll(Collection<? extends T> collection) {
        int size = size();
        boolean addAll = super.addAll(collection);
        if (addAll) {
            a(size, size() - size);
        }
        return addAll;
    }

    public boolean addAll(int i, Collection<? extends T> collection) {
        boolean addAll = super.addAll(i, collection);
        if (addAll) {
            a(i, collection.size());
        }
        return addAll;
    }

    public void clear() {
        int size = size();
        super.clear();
        if (size != 0) {
            b(0, size);
        }
    }

    public T remove(int i) {
        T remove = super.remove(i);
        b(i, 1);
        return remove;
    }

    public boolean remove(Object obj) {
        int indexOf = indexOf(obj);
        if (indexOf < 0) {
            return false;
        }
        remove(indexOf);
        return true;
    }

    public T set(int i, T t) {
        T t2 = super.set(i, t);
        if (this.a != null) {
            this.a.a((w) this, i, 1);
        }
        return t2;
    }

    protected void removeRange(int i, int i2) {
        super.removeRange(i, i2);
        b(i, i2 - i);
    }

    private void a(int i, int i2) {
        if (this.a != null) {
            this.a.b(this, i, i2);
        }
    }

    private void b(int i, int i2) {
        if (this.a != null) {
            this.a.c(this, i, i2);
        }
    }
}
