package com.nostra13.universalimageloader.cache.memory.impl;

import com.nostra13.universalimageloader.cache.memory.MemoryCacheAware;
import java.util.Collection;
import java.util.Comparator;

public class FuzzyKeyMemoryCache<K, V> implements MemoryCacheAware<K, V> {
    private final MemoryCacheAware<K, V> cache;
    private final Comparator<K> keyComparator;

    public FuzzyKeyMemoryCache(MemoryCacheAware<K, V> memoryCacheAware, Comparator<K> comparator) {
        this.cache = memoryCacheAware;
        this.keyComparator = comparator;
    }

    public boolean put(K k, V v) {
        synchronized (this.cache) {
            for (Object next : this.cache.keys()) {
                if (this.keyComparator.compare(k, next) == 0) {
                    break;
                }
            }
            Object next2 = null;
            if (next2 != null) {
                this.cache.remove(next2);
            }
        }
        return this.cache.put(k, v);
    }

    public V get(K k) {
        return this.cache.get(k);
    }

    public void remove(K k) {
        this.cache.remove(k);
    }

    public void clear() {
        this.cache.clear();
    }

    public Collection<K> keys() {
        return this.cache.keys();
    }
}
