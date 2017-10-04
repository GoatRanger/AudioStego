package com.nokia.maps;

import android.util.SparseArray;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

public class fc<T> {
    private SparseArray<WeakReference<T>> a = new SparseArray();

    @Deprecated
    public void a(T t) {
        a(new WeakReference(t));
    }

    public void a(WeakReference<T> weakReference) {
        dy.a(weakReference, "Cannot add null WeakReference");
        Object obj = weakReference.get();
        dy.a(obj, "Cannot add WeakReference with null listener");
        synchronized (this.a) {
            this.a.put(obj.hashCode(), weakReference);
        }
    }

    public void b(T t) {
        if (t != null) {
            synchronized (this.a) {
                this.a.remove(t.hashCode());
            }
        }
    }

    public void a(a<T> aVar) {
        List<WeakReference> arrayList = new ArrayList();
        List<Integer> arrayList2 = new ArrayList();
        synchronized (this.a) {
            for (int i = 0; i < this.a.size(); i++) {
                WeakReference weakReference = (WeakReference) this.a.valueAt(i);
                Object obj = null;
                if (weakReference != null) {
                    obj = weakReference.get();
                }
                if (obj != null) {
                    arrayList.add(weakReference);
                } else {
                    arrayList2.add(Integer.valueOf(this.a.keyAt(i)));
                }
            }
            for (Integer intValue : arrayList2) {
                this.a.remove(intValue.intValue());
            }
        }
        for (WeakReference weakReference2 : arrayList) {
            Object obj2 = weakReference2.get();
            if (obj2 != null) {
                aVar.a(obj2);
            }
        }
    }

    public void b(a<T> aVar) {
        a(aVar, null);
    }

    public void a(a<T> aVar, Runnable runnable) {
        ce.a(new 1(this, aVar, runnable));
    }

    int a() {
        return this.a.size();
    }

    boolean b() {
        return this.a.size() == 0;
    }
}
