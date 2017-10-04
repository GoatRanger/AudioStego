package com.nostra13.universalimageloader.cache.disc;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class LimitedDiscCache extends BaseDiscCache {
    private static final int INVALID_SIZE = -1;
    private final AtomicInteger cacheSize;
    private final Map<File, Long> lastUsageDates;
    private final int sizeLimit;

    protected abstract int getSize(File file);

    public LimitedDiscCache(File file, int i) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator(), i);
    }

    public LimitedDiscCache(File file, FileNameGenerator fileNameGenerator, int i) {
        super(file, fileNameGenerator);
        this.lastUsageDates = Collections.synchronizedMap(new HashMap());
        this.sizeLimit = i;
        this.cacheSize = new AtomicInteger();
        calculateCacheSizeAndFillUsageMap();
    }

    private void calculateCacheSizeAndFillUsageMap() {
        new Thread(new Runnable() {
            public void run() {
                int i = 0;
                File[] listFiles = LimitedDiscCache.this.cacheDir.listFiles();
                if (listFiles != null) {
                    int length = listFiles.length;
                    int i2 = 0;
                    while (i < length) {
                        File file = listFiles[i];
                        i2 += LimitedDiscCache.this.getSize(file);
                        LimitedDiscCache.this.lastUsageDates.put(file, Long.valueOf(file.lastModified()));
                        i++;
                    }
                    LimitedDiscCache.this.cacheSize.set(i2);
                }
            }
        }).start();
    }

    public void put(String str, File file) {
        int size = getSize(file);
        int i = this.cacheSize.get();
        while (i + size > this.sizeLimit) {
            i = removeNext();
            if (i == -1) {
                break;
            }
            i = this.cacheSize.addAndGet(-i);
        }
        this.cacheSize.addAndGet(size);
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        file.setLastModified(valueOf.longValue());
        this.lastUsageDates.put(file, valueOf);
    }

    public File get(String str) {
        File file = super.get(str);
        Long valueOf = Long.valueOf(System.currentTimeMillis());
        file.setLastModified(valueOf.longValue());
        this.lastUsageDates.put(file, valueOf);
        return file;
    }

    public void clear() {
        this.lastUsageDates.clear();
        this.cacheSize.set(0);
        super.clear();
    }

    private int removeNext() {
        File file = null;
        if (this.lastUsageDates.isEmpty()) {
            return -1;
        }
        Set<Entry> entrySet = this.lastUsageDates.entrySet();
        synchronized (this.lastUsageDates) {
            Long l = null;
            for (Entry entry : entrySet) {
                File file2;
                Long l2;
                if (file == null) {
                    file2 = (File) entry.getKey();
                    l2 = (Long) entry.getValue();
                } else {
                    Long l3 = (Long) entry.getValue();
                    if (l3.longValue() < l.longValue()) {
                        File file3 = (File) entry.getKey();
                        l2 = l3;
                        file2 = file3;
                    } else {
                        file2 = file;
                        l2 = l;
                    }
                }
                file = file2;
                l = l2;
            }
        }
        if (file == null) {
            return 0;
        }
        if (file.exists()) {
            int size = getSize(file);
            if (!file.delete()) {
                return size;
            }
            this.lastUsageDates.remove(file);
            return size;
        }
        this.lastUsageDates.remove(file);
        return 0;
    }
}
