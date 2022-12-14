package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.LimitedMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

public class LRULimitedMemoryCache extends LimitedMemoryCache<String, Bitmap> {
    private static final int INITIAL_CAPACITY = 10;
    private static final float LOAD_FACTOR = 1.1f;
    private final Map<String, Bitmap> lruCache = Collections.synchronizedMap(new LinkedHashMap(10, LOAD_FACTOR, true));

    public LRULimitedMemoryCache(int i) {
        super(i);
    }

    public boolean put(String str, Bitmap bitmap) {
        if (!super.put(str, bitmap)) {
            return false;
        }
        this.lruCache.put(str, bitmap);
        return true;
    }

    public Bitmap get(String str) {
        this.lruCache.get(str);
        return (Bitmap) super.get(str);
    }

    public void remove(String str) {
        this.lruCache.remove(str);
        super.remove(str);
    }

    public void clear() {
        this.lruCache.clear();
        super.clear();
    }

    protected int getSize(Bitmap bitmap) {
        return bitmap.getRowBytes() * bitmap.getHeight();
    }

    protected Bitmap removeNext() {
        Bitmap bitmap = null;
        synchronized (this.lruCache) {
            Iterator it = this.lruCache.entrySet().iterator();
            if (it.hasNext()) {
                bitmap = (Bitmap) ((Entry) it.next()).getValue();
                it.remove();
            }
        }
        return bitmap;
    }

    protected Reference<Bitmap> createReference(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
