package com.nostra13.universalimageloader.cache.disc.impl;

import com.nostra13.universalimageloader.cache.disc.BaseDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;

public class UnlimitedDiscCache extends BaseDiscCache {
    public UnlimitedDiscCache(File file) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator());
    }

    public UnlimitedDiscCache(File file, FileNameGenerator fileNameGenerator) {
        super(file, fileNameGenerator);
    }

    public void put(String str, File file) {
    }
}
