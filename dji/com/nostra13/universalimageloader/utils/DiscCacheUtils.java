package com.nostra13.universalimageloader.utils;

import com.nostra13.universalimageloader.cache.disc.DiscCacheAware;
import java.io.File;

public final class DiscCacheUtils {
    private DiscCacheUtils() {
    }

    public static File findInCache(String str, DiscCacheAware discCacheAware) {
        File file = discCacheAware.get(str);
        return file.exists() ? file : null;
    }

    public static boolean removeFromCache(String str, DiscCacheAware discCacheAware) {
        return discCacheAware.get(str).delete();
    }
}
