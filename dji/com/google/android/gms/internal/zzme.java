package com.google.android.gms.internal;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzme<K, V> extends zzmi<K, V> implements Map<K, V> {
    zzmh<K, V> zzagz;

    private zzmh<K, V> zzpx() {
        if (this.zzagz == null) {
            this.zzagz = new zzmh<K, V>(this) {
                final /* synthetic */ zzme zzagA;

                {
                    this.zzagA = r1;
                }

                protected void colClear() {
                    this.zzagA.clear();
                }

                protected Object colGetEntry(int i, int i2) {
                    return this.zzagA.mArray[(i << 1) + i2];
                }

                protected Map<K, V> colGetMap() {
                    return this.zzagA;
                }

                protected int colGetSize() {
                    return this.zzagA.mSize;
                }

                protected int colIndexOfKey(Object obj) {
                    return obj == null ? this.zzagA.indexOfNull() : this.zzagA.indexOf(obj, obj.hashCode());
                }

                protected int colIndexOfValue(Object obj) {
                    return this.zzagA.indexOfValue(obj);
                }

                protected void colPut(K k, V v) {
                    this.zzagA.put(k, v);
                }

                protected void colRemoveAt(int i) {
                    this.zzagA.removeAt(i);
                }

                protected V colSetValue(int i, V v) {
                    return this.zzagA.setValueAt(i, v);
                }
            };
        }
        return this.zzagz;
    }

    public Set<Entry<K, V>> entrySet() {
        return zzpx().getEntrySet();
    }

    public Set<K> keySet() {
        return zzpx().getKeySet();
    }

    public void putAll(Map<? extends K, ? extends V> map) {
        ensureCapacity(this.mSize + map.size());
        for (Entry entry : map.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    public Collection<V> values() {
        return zzpx().getValues();
    }
}
