package com.nostra13.universalimageloader.cache.memory.impl;

import android.graphics.Bitmap;
import com.nostra13.universalimageloader.cache.memory.BaseMemoryCache;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public class WeakMemoryCache extends BaseMemoryCache<String, Bitmap> {
    protected Reference<Bitmap> createReference(Bitmap bitmap) {
        return new WeakReference(bitmap);
    }
}
