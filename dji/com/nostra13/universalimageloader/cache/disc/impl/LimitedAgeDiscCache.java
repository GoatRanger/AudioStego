package com.nostra13.universalimageloader.cache.disc.impl;

import com.nostra13.universalimageloader.cache.disc.BaseDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class LimitedAgeDiscCache extends BaseDiscCache {
    private final Map<File, Long> loadingDates;
    private final long maxFileAge;

    public LimitedAgeDiscCache(File file, long j) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator(), j);
    }

    public LimitedAgeDiscCache(File file, FileNameGenerator fileNameGenerator, long j) {
        super(file, fileNameGenerator);
        this.loadingDates = Collections.synchronizedMap(new HashMap());
        this.maxFileAge = 1000 * j;
    }

    public void put(String str, File file) {
        long currentTimeMillis = System.currentTimeMillis();
        file.setLastModified(currentTimeMillis);
        this.loadingDates.put(file, Long.valueOf(currentTimeMillis));
    }

    public File get(String str) {
        File file = super.get(str);
        if (file.exists()) {
            Object obj;
            Long l = (Long) this.loadingDates.get(file);
            if (l == null) {
                obj = null;
                l = Long.valueOf(file.lastModified());
            } else {
                obj = 1;
            }
            if (System.currentTimeMillis() - l.longValue() > this.maxFileAge) {
                file.delete();
                this.loadingDates.remove(file);
            } else if (obj == null) {
                this.loadingDates.put(file, l);
            }
        }
        return file;
    }
}
