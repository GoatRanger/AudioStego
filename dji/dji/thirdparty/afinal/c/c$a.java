package dji.thirdparty.afinal.c;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.AbstractList;
import java.util.List;
import java.util.RandomAccess;

class c$a<E> extends AbstractList<E> implements Serializable, List<E>, RandomAccess {
    private static final long a = -2764017481108945198L;
    private final E[] b;

    c$a(E[] eArr) {
        if (eArr == null) {
            throw new NullPointerException();
        }
        this.b = eArr;
    }

    public boolean contains(Object obj) {
        if (obj != null) {
            for (Object equals : this.b) {
                if (obj.equals(equals)) {
                    return true;
                }
            }
        } else {
            for (Object equals2 : this.b) {
                if (equals2 == null) {
                    return true;
                }
            }
        }
        return false;
    }

    public E get(int i) {
        try {
            return this.b[i];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw e;
        }
    }

    public int indexOf(Object obj) {
        int i = 0;
        if (obj != null) {
            while (i < this.b.length) {
                if (obj.equals(this.b[i])) {
                    return i;
                }
                i++;
            }
        } else {
            while (i < this.b.length) {
                if (this.b[i] == null) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    public int lastIndexOf(Object obj) {
        int length;
        if (obj != null) {
            for (length = this.b.length - 1; length >= 0; length--) {
                if (obj.equals(this.b[length])) {
                    return length;
                }
            }
        } else {
            for (length = this.b.length - 1; length >= 0; length--) {
                if (this.b[length] == null) {
                    return length;
                }
            }
        }
        return -1;
    }

    public E set(int i, E e) {
        E e2 = this.b[i];
        this.b[i] = e;
        return e2;
    }

    public int size() {
        return this.b.length;
    }

    public Object[] toArray() {
        return (Object[]) this.b.clone();
    }

    public <T> T[] toArray(T[] tArr) {
        Object obj;
        int size = size();
        if (size > tArr.length) {
            obj = (Object[]) Array.newInstance(tArr.getClass().getComponentType(), size);
        } else {
            obj = tArr;
        }
        System.arraycopy(this.b, 0, obj, 0, size);
        if (size < obj.length) {
            obj[size] = null;
        }
        return obj;
    }
}
