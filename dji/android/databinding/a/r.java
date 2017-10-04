package android.databinding.a;

import android.os.Build.VERSION;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import java.util.WeakHashMap;

public class r {
    private static SparseArray<WeakHashMap<View, WeakReference<?>>> a = new SparseArray();

    public static <T> T a(View view, T t, int i) {
        if (VERSION.SDK_INT >= 14) {
            T tag = view.getTag(i);
            view.setTag(i, t);
            return tag;
        }
        synchronized (a) {
            WeakReference weakReference;
            WeakHashMap weakHashMap = (WeakHashMap) a.get(i);
            if (weakHashMap == null) {
                weakHashMap = new WeakHashMap();
                a.put(i, weakHashMap);
            }
            if (t == null) {
                weakReference = (WeakReference) weakHashMap.remove(view);
            } else {
                weakReference = (WeakReference) weakHashMap.put(view, new WeakReference(t));
            }
            if (weakReference == null) {
                return null;
            }
            tag = weakReference.get();
            return tag;
        }
    }

    public static <T> T a(View view, int i) {
        if (VERSION.SDK_INT >= 14) {
            return view.getTag(i);
        }
        synchronized (a) {
            WeakHashMap weakHashMap = (WeakHashMap) a.get(i);
            if (weakHashMap == null) {
                return null;
            }
            WeakReference weakReference = (WeakReference) weakHashMap.get(view);
            if (weakReference == null) {
                return null;
            }
            T t = weakReference.get();
            return t;
        }
    }
}
