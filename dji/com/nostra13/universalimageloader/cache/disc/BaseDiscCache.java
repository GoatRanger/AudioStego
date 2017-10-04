package com.nostra13.universalimageloader.cache.disc;

import com.nostra13.universalimageloader.cache.disc.naming.FileNameGenerator;
import com.nostra13.universalimageloader.core.DefaultConfigurationFactory;
import java.io.File;

public abstract class BaseDiscCache implements DiscCacheAware {
    private static final String ERROR_ARG_NULL = "\"%s\" argument must be not null";
    protected File cacheDir;
    private FileNameGenerator fileNameGenerator;

    public BaseDiscCache(File file) {
        this(file, DefaultConfigurationFactory.createFileNameGenerator());
    }

    public BaseDiscCache(File file, FileNameGenerator fileNameGenerator) {
        if (file == null) {
            throw new IllegalArgumentException(String.format(ERROR_ARG_NULL, new Object[]{"cacheDir"}));
        } else if (fileNameGenerator == null) {
            throw new IllegalArgumentException(String.format(ERROR_ARG_NULL, new Object[]{"fileNameGenerator"}));
        } else {
            this.cacheDir = file;
            this.fileNameGenerator = fileNameGenerator;
        }
    }

    public File get(String str) {
        return new File(this.cacheDir, this.fileNameGenerator.generate(str));
    }

    public void clear() {
        File[] listFiles = this.cacheDir.listFiles();
        if (listFiles != null) {
            for (File delete : listFiles) {
                delete.delete();
            }
        }
    }
}
