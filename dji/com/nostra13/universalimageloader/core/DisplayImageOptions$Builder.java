package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory.Options;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.process.BitmapProcessor;

public class DisplayImageOptions$Builder {
    private boolean cacheInMemory;
    private boolean cacheOnDisc;
    private boolean considerExifParams;
    private Options decodingOptions;
    private int delayBeforeLoading;
    private BitmapDisplayer displayer;
    private Object extraForDownloader;
    private Handler handler;
    private Drawable imageForEmptyUri;
    private Drawable imageOnFail;
    private Drawable imageOnLoading;
    private int imageResForEmptyUri;
    private int imageResOnFail;
    private int imageResOnLoading;
    private ImageScaleType imageScaleType;
    private boolean isSyncLoading;
    private BitmapProcessor postProcessor;
    private BitmapProcessor preProcessor;
    private boolean resetViewBeforeLoading;

    public DisplayImageOptions$Builder() {
        this.imageResOnLoading = 0;
        this.imageResForEmptyUri = 0;
        this.imageResOnFail = 0;
        this.imageOnLoading = null;
        this.imageForEmptyUri = null;
        this.imageOnFail = null;
        this.resetViewBeforeLoading = false;
        this.cacheInMemory = false;
        this.cacheOnDisc = false;
        this.imageScaleType = ImageScaleType.IN_SAMPLE_POWER_OF_2;
        this.decodingOptions = new Options();
        this.delayBeforeLoading = 0;
        this.considerExifParams = false;
        this.extraForDownloader = null;
        this.preProcessor = null;
        this.postProcessor = null;
        this.displayer = DefaultConfigurationFactory.createBitmapDisplayer();
        this.handler = null;
        this.isSyncLoading = false;
        this.decodingOptions.inPurgeable = true;
        this.decodingOptions.inInputShareable = true;
    }

    @Deprecated
    public DisplayImageOptions$Builder showStubImage(int i) {
        this.imageResOnLoading = i;
        return this;
    }

    public DisplayImageOptions$Builder showImageOnLoading(int i) {
        this.imageResOnLoading = i;
        return this;
    }

    public DisplayImageOptions$Builder showImageOnLoading(Drawable drawable) {
        this.imageOnLoading = drawable;
        return this;
    }

    public DisplayImageOptions$Builder showImageForEmptyUri(int i) {
        this.imageResForEmptyUri = i;
        return this;
    }

    public DisplayImageOptions$Builder showImageForEmptyUri(Drawable drawable) {
        this.imageForEmptyUri = drawable;
        return this;
    }

    public DisplayImageOptions$Builder showImageOnFail(int i) {
        this.imageResOnFail = i;
        return this;
    }

    public DisplayImageOptions$Builder showImageOnFail(Drawable drawable) {
        this.imageOnFail = drawable;
        return this;
    }

    public DisplayImageOptions$Builder resetViewBeforeLoading() {
        this.resetViewBeforeLoading = true;
        return this;
    }

    public DisplayImageOptions$Builder resetViewBeforeLoading(boolean z) {
        this.resetViewBeforeLoading = z;
        return this;
    }

    public DisplayImageOptions$Builder cacheInMemory() {
        this.cacheInMemory = true;
        return this;
    }

    public DisplayImageOptions$Builder cacheInMemory(boolean z) {
        this.cacheInMemory = z;
        return this;
    }

    public DisplayImageOptions$Builder cacheOnDisc() {
        this.cacheOnDisc = true;
        return this;
    }

    public DisplayImageOptions$Builder cacheOnDisc(boolean z) {
        this.cacheOnDisc = z;
        return this;
    }

    public DisplayImageOptions$Builder imageScaleType(ImageScaleType imageScaleType) {
        this.imageScaleType = imageScaleType;
        return this;
    }

    public DisplayImageOptions$Builder bitmapConfig(Config config) {
        if (config == null) {
            throw new IllegalArgumentException("bitmapConfig can't be null");
        }
        this.decodingOptions.inPreferredConfig = config;
        return this;
    }

    public DisplayImageOptions$Builder decodingOptions(Options options) {
        if (options == null) {
            throw new IllegalArgumentException("decodingOptions can't be null");
        }
        this.decodingOptions = options;
        return this;
    }

    public DisplayImageOptions$Builder delayBeforeLoading(int i) {
        this.delayBeforeLoading = i;
        return this;
    }

    public DisplayImageOptions$Builder extraForDownloader(Object obj) {
        this.extraForDownloader = obj;
        return this;
    }

    public DisplayImageOptions$Builder considerExifParams(boolean z) {
        this.considerExifParams = z;
        return this;
    }

    public DisplayImageOptions$Builder preProcessor(BitmapProcessor bitmapProcessor) {
        this.preProcessor = bitmapProcessor;
        return this;
    }

    public DisplayImageOptions$Builder postProcessor(BitmapProcessor bitmapProcessor) {
        this.postProcessor = bitmapProcessor;
        return this;
    }

    public DisplayImageOptions$Builder displayer(BitmapDisplayer bitmapDisplayer) {
        if (bitmapDisplayer == null) {
            throw new IllegalArgumentException("displayer can't be null");
        }
        this.displayer = bitmapDisplayer;
        return this;
    }

    DisplayImageOptions$Builder syncLoading(boolean z) {
        this.isSyncLoading = z;
        return this;
    }

    public DisplayImageOptions$Builder handler(Handler handler) {
        this.handler = handler;
        return this;
    }

    public DisplayImageOptions$Builder cloneFrom(DisplayImageOptions displayImageOptions) {
        this.imageResOnLoading = DisplayImageOptions.access$1900(displayImageOptions);
        this.imageResForEmptyUri = DisplayImageOptions.access$2000(displayImageOptions);
        this.imageResOnFail = DisplayImageOptions.access$2100(displayImageOptions);
        this.imageOnLoading = DisplayImageOptions.access$2200(displayImageOptions);
        this.imageForEmptyUri = DisplayImageOptions.access$2300(displayImageOptions);
        this.imageOnFail = DisplayImageOptions.access$2400(displayImageOptions);
        this.resetViewBeforeLoading = DisplayImageOptions.access$2500(displayImageOptions);
        this.cacheInMemory = DisplayImageOptions.access$2600(displayImageOptions);
        this.cacheOnDisc = DisplayImageOptions.access$2700(displayImageOptions);
        this.imageScaleType = DisplayImageOptions.access$2800(displayImageOptions);
        this.decodingOptions = DisplayImageOptions.access$2900(displayImageOptions);
        this.delayBeforeLoading = DisplayImageOptions.access$3000(displayImageOptions);
        this.considerExifParams = DisplayImageOptions.access$3100(displayImageOptions);
        this.extraForDownloader = DisplayImageOptions.access$3200(displayImageOptions);
        this.preProcessor = DisplayImageOptions.access$3300(displayImageOptions);
        this.postProcessor = DisplayImageOptions.access$3400(displayImageOptions);
        this.displayer = DisplayImageOptions.access$3500(displayImageOptions);
        this.handler = DisplayImageOptions.access$3600(displayImageOptions);
        this.isSyncLoading = DisplayImageOptions.access$3700(displayImageOptions);
        return this;
    }

    public DisplayImageOptions build() {
        return new DisplayImageOptions(this, null);
    }
}
