package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.os.Handler;
import com.nostra13.universalimageloader.core.DisplayImageOptions.Builder;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.FailReason.FailType;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.LoadedFrom;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.decode.ImageDecoder;
import com.nostra13.universalimageloader.core.decode.ImageDecodingInfo;
import com.nostra13.universalimageloader.core.download.ImageDownloader;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.nostra13.universalimageloader.core.listener.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.IoUtils.CopyListener;
import com.nostra13.universalimageloader.utils.L;
import java.io.BufferedOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

final class LoadAndDisplayImageTask implements CopyListener, Runnable {
    private static final int BUFFER_SIZE = 32768;
    private static final String ERROR_POST_PROCESSOR_NULL = "Post-processor returned null [%s]";
    private static final String ERROR_PRE_PROCESSOR_NULL = "Pre-processor returned null [%s]";
    private static final String ERROR_PROCESSOR_FOR_DISC_CACHE_NULL = "Bitmap processor for disc cache returned null [%s]";
    private static final String LOG_CACHE_IMAGE_IN_MEMORY = "Cache image in memory [%s]";
    private static final String LOG_CACHE_IMAGE_ON_DISC = "Cache image on disc [%s]";
    private static final String LOG_DELAY_BEFORE_LOADING = "Delay %d ms before loading...  [%s]";
    private static final String LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING = "...Get cached bitmap from memory after waiting. [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_DISC_CACHE = "Load image from disc cache [%s]";
    private static final String LOG_LOAD_IMAGE_FROM_NETWORK = "Load image from network [%s]";
    private static final String LOG_POSTPROCESS_IMAGE = "PostProcess image before displaying [%s]";
    private static final String LOG_PREPROCESS_IMAGE = "PreProcess image before caching in memory [%s]";
    private static final String LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISC = "Process image before cache on disc [%s]";
    private static final String LOG_RESIZE_CACHED_IMAGE_FILE = "Resize image in disc cache [%s]";
    private static final String LOG_RESUME_AFTER_PAUSE = ".. Resume loading [%s]";
    private static final String LOG_START_DISPLAY_IMAGE_TASK = "Start display image task [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED = "ImageAware was collected by GC. Task is cancelled. [%s]";
    private static final String LOG_TASK_CANCELLED_IMAGEAWARE_REUSED = "ImageAware is reused for another image. Task is cancelled. [%s]";
    private static final String LOG_TASK_INTERRUPTED = "Task was interrupted [%s]";
    private static final String LOG_WAITING_FOR_IMAGE_LOADED = "Image already is loading. Waiting... [%s]";
    private static final String LOG_WAITING_FOR_RESUME = "ImageLoader is paused. Waiting...  [%s]";
    private final ImageLoaderConfiguration configuration;
    private final ImageDecoder decoder;
    private final ImageDownloader downloader;
    private final ImageLoaderEngine engine;
    private final Handler handler;
    final ImageAware imageAware;
    private final ImageLoadingInfo imageLoadingInfo;
    final ImageLoadingListener listener;
    private LoadedFrom loadedFrom = LoadedFrom.NETWORK;
    private final String memoryCacheKey;
    private final ImageDownloader networkDeniedDownloader;
    final DisplayImageOptions options;
    final ImageLoadingProgressListener progressListener;
    private final ImageDownloader slowNetworkDownloader;
    private final ImageSize targetSize;
    final String uri;
    private final boolean writeLogs;

    class TaskCancelledException extends Exception {
        TaskCancelledException() {
        }
    }

    public LoadAndDisplayImageTask(ImageLoaderEngine imageLoaderEngine, ImageLoadingInfo imageLoadingInfo, Handler handler) {
        this.engine = imageLoaderEngine;
        this.imageLoadingInfo = imageLoadingInfo;
        this.handler = handler;
        this.configuration = imageLoaderEngine.configuration;
        this.downloader = this.configuration.downloader;
        this.networkDeniedDownloader = this.configuration.networkDeniedDownloader;
        this.slowNetworkDownloader = this.configuration.slowNetworkDownloader;
        this.decoder = this.configuration.decoder;
        this.writeLogs = this.configuration.writeLogs;
        this.uri = imageLoadingInfo.uri;
        this.memoryCacheKey = imageLoadingInfo.memoryCacheKey;
        this.imageAware = imageLoadingInfo.imageAware;
        this.targetSize = imageLoadingInfo.targetSize;
        this.options = imageLoadingInfo.options;
        this.listener = imageLoadingInfo.listener;
        this.progressListener = imageLoadingInfo.progressListener;
    }

    public void run() {
        if (!waitIfPaused() && !delayIfNeed()) {
            ReentrantLock reentrantLock = this.imageLoadingInfo.loadFromUriLock;
            log(LOG_START_DISPLAY_IMAGE_TASK);
            if (reentrantLock.isLocked()) {
                log(LOG_WAITING_FOR_IMAGE_LOADED);
            }
            reentrantLock.lock();
            try {
                checkTaskNotActual();
                Bitmap bitmap = (Bitmap) this.configuration.memoryCache.get(this.memoryCacheKey);
                if (bitmap == null) {
                    bitmap = tryLoadBitmap();
                    if (bitmap != null) {
                        checkTaskNotActual();
                        checkTaskInterrupted();
                        if (this.options.shouldPreProcess()) {
                            log(LOG_PREPROCESS_IMAGE);
                            bitmap = this.options.getPreProcessor().process(bitmap);
                            if (bitmap == null) {
                                L.e(ERROR_PRE_PROCESSOR_NULL, this.memoryCacheKey);
                            }
                        }
                        if (bitmap != null && this.options.isCacheInMemory()) {
                            log(LOG_CACHE_IMAGE_IN_MEMORY);
                            this.configuration.memoryCache.put(this.memoryCacheKey, bitmap);
                        }
                    } else {
                        return;
                    }
                }
                this.loadedFrom = LoadedFrom.MEMORY_CACHE;
                log(LOG_GET_IMAGE_FROM_MEMORY_CACHE_AFTER_WAITING);
                if (bitmap != null && this.options.shouldPostProcess()) {
                    log(LOG_POSTPROCESS_IMAGE);
                    bitmap = this.options.getPostProcessor().process(bitmap);
                    if (bitmap == null) {
                        L.e(ERROR_POST_PROCESSOR_NULL, this.memoryCacheKey);
                    }
                }
                checkTaskNotActual();
                checkTaskInterrupted();
                reentrantLock.unlock();
                Runnable displayBitmapTask = new DisplayBitmapTask(bitmap, this.imageLoadingInfo, this.engine, this.loadedFrom);
                displayBitmapTask.setLoggingEnabled(this.writeLogs);
                runTask(displayBitmapTask, this.options.isSyncLoading(), this.handler, this.engine);
            } catch (TaskCancelledException e) {
                fireCancelEvent();
            } finally {
                reentrantLock.unlock();
            }
        }
    }

    private boolean waitIfPaused() {
        AtomicBoolean pause = this.engine.getPause();
        if (pause.get()) {
            synchronized (this.engine.getPauseLock()) {
                if (pause.get()) {
                    log(LOG_WAITING_FOR_RESUME);
                    try {
                        this.engine.getPauseLock().wait();
                        log(LOG_RESUME_AFTER_PAUSE);
                    } catch (InterruptedException e) {
                        L.e(LOG_TASK_INTERRUPTED, this.memoryCacheKey);
                        return true;
                    }
                }
            }
        }
        return isTaskNotActual();
    }

    private boolean delayIfNeed() {
        if (!this.options.shouldDelayBeforeLoading()) {
            return false;
        }
        log(LOG_DELAY_BEFORE_LOADING, Integer.valueOf(this.options.getDelayBeforeLoading()), this.memoryCacheKey);
        try {
            Thread.sleep((long) this.options.getDelayBeforeLoading());
            return isTaskNotActual();
        } catch (InterruptedException e) {
            L.e(LOG_TASK_INTERRUPTED, this.memoryCacheKey);
            return true;
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private android.graphics.Bitmap tryLoadBitmap() throws com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.TaskCancelledException {
        /*
        r6 = this;
        r1 = 0;
        r3 = r6.getImageFileInDiscCache();
        r0 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        r2 = r3.getAbsolutePath();	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        r2 = r0.wrap(r2);	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        r0 = r3.exists();	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        if (r0 == 0) goto L_0x00ab;
    L_0x0015:
        r0 = "Load image from disc cache [%s]";
        r6.log(r0);	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        r0 = com.nostra13.universalimageloader.core.assist.LoadedFrom.DISC_CACHE;	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        r6.loadedFrom = r0;	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        r6.checkTaskNotActual();	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
        r0 = r6.decodeImage(r2);	 Catch:{ IllegalStateException -> 0x0069, TaskCancelledException -> 0x0071, IOException -> 0x0073, OutOfMemoryError -> 0x0089, Throwable -> 0x0096 }
    L_0x0025:
        if (r0 == 0) goto L_0x0033;
    L_0x0027:
        r4 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        if (r4 <= 0) goto L_0x0033;
    L_0x002d:
        r4 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        if (r4 > 0) goto L_0x0065;
    L_0x0033:
        r4 = "Load image from network [%s]";
        r6.log(r4);	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        r4 = com.nostra13.universalimageloader.core.assist.LoadedFrom.NETWORK;	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        r6.loadedFrom = r4;	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        r4 = r6.options;	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        r4 = r4.isCacheOnDisc();	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        if (r4 == 0) goto L_0x0066;
    L_0x0044:
        r4 = r6.tryCacheImageOnDisc(r3);	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        if (r4 == 0) goto L_0x0066;
    L_0x004a:
        r6.checkTaskNotActual();	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        r0 = r6.decodeImage(r2);	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        if (r0 == 0) goto L_0x005f;
    L_0x0053:
        r2 = r0.getWidth();	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        if (r2 <= 0) goto L_0x005f;
    L_0x0059:
        r2 = r0.getHeight();	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        if (r2 > 0) goto L_0x0065;
    L_0x005f:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.DECODING_ERROR;	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        r4 = 0;
        r6.fireFailEvent(r2, r4);	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
    L_0x0065:
        return r0;
    L_0x0066:
        r2 = r6.uri;	 Catch:{ IllegalStateException -> 0x00a9, TaskCancelledException -> 0x0071, IOException -> 0x00a7, OutOfMemoryError -> 0x00a5, Throwable -> 0x00a3 }
        goto L_0x004a;
    L_0x0069:
        r0 = move-exception;
        r0 = r1;
    L_0x006b:
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.NETWORK_DENIED;
        r6.fireFailEvent(r2, r1);
        goto L_0x0065;
    L_0x0071:
        r0 = move-exception;
        throw r0;
    L_0x0073:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x0077:
        com.nostra13.universalimageloader.utils.L.e(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.IO_ERROR;
        r6.fireFailEvent(r2, r1);
        r1 = r3.exists();
        if (r1 == 0) goto L_0x0065;
    L_0x0085:
        r3.delete();
        goto L_0x0065;
    L_0x0089:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x008d:
        com.nostra13.universalimageloader.utils.L.e(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.OUT_OF_MEMORY;
        r6.fireFailEvent(r2, r1);
        goto L_0x0065;
    L_0x0096:
        r0 = move-exception;
        r5 = r0;
        r0 = r1;
        r1 = r5;
    L_0x009a:
        com.nostra13.universalimageloader.utils.L.e(r1);
        r2 = com.nostra13.universalimageloader.core.assist.FailReason.FailType.UNKNOWN;
        r6.fireFailEvent(r2, r1);
        goto L_0x0065;
    L_0x00a3:
        r1 = move-exception;
        goto L_0x009a;
    L_0x00a5:
        r1 = move-exception;
        goto L_0x008d;
    L_0x00a7:
        r1 = move-exception;
        goto L_0x0077;
    L_0x00a9:
        r2 = move-exception;
        goto L_0x006b;
    L_0x00ab:
        r0 = r1;
        goto L_0x0025;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.tryLoadBitmap():android.graphics.Bitmap");
    }

    private File getImageFileInDiscCache() {
        File file = this.configuration.discCache.get(this.uri);
        File parentFile = file.getParentFile();
        if (parentFile == null || !(parentFile.exists() || parentFile.mkdirs())) {
            file = this.configuration.reserveDiscCache.get(this.uri);
            parentFile = file.getParentFile();
            if (!(parentFile == null || parentFile.exists())) {
                parentFile.mkdirs();
            }
        }
        return file;
    }

    private Bitmap decodeImage(String str) throws IOException {
        String str2 = str;
        return this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, str2, this.targetSize, this.imageAware.getScaleType(), getDownloader(), this.options));
    }

    private boolean tryCacheImageOnDisc(File file) throws TaskCancelledException {
        boolean downloadImage;
        Throwable e;
        log(LOG_CACHE_IMAGE_ON_DISC);
        try {
            downloadImage = downloadImage(file);
            if (downloadImage) {
                try {
                    int i = this.configuration.maxImageWidthForDiscCache;
                    int i2 = this.configuration.maxImageHeightForDiscCache;
                    if (i > 0 || i2 > 0) {
                        log(LOG_RESIZE_CACHED_IMAGE_FILE);
                        downloadImage = resizeAndSaveImage(file, i, i2);
                    }
                    this.configuration.discCache.put(this.uri, file);
                } catch (IOException e2) {
                    e = e2;
                    L.e(e);
                    if (file.exists()) {
                        file.delete();
                    }
                    return downloadImage;
                }
            }
        } catch (Throwable e3) {
            Throwable th = e3;
            downloadImage = false;
            e = th;
            L.e(e);
            if (file.exists()) {
                file.delete();
            }
            return downloadImage;
        }
        return downloadImage;
    }

    private boolean downloadImage(File file) throws IOException {
        Closeable bufferedOutputStream;
        ImageDownloader downloader = getDownloader();
        String str = this.uri;
        boolean extraForDownloader = this.options.getExtraForDownloader();
        Closeable stream = downloader.getStream(str, extraForDownloader);
        try {
            extraForDownloader = new FileOutputStream(file);
            bufferedOutputStream = new BufferedOutputStream(extraForDownloader, 32768);
            extraForDownloader = IoUtils.copyStream(stream, bufferedOutputStream, this);
        } catch (Throwable th) {
            bufferedOutputStream = th;
        } finally {
            IoUtils.closeSilently(
/*
Method generation error in method: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.downloadImage(java.io.File):boolean
jadx.core.utils.exceptions.CodegenException: Error generate insn: 0x0029: INVOKE  (wrap: java.io.Closeable
  ?: MERGE  (r0_3 java.io.Closeable) = (r0_2 'bufferedOutputStream' java.io.Closeable), (r1_1 'stream' java.io.Closeable)) com.nostra13.universalimageloader.utils.IoUtils.closeSilently(java.io.Closeable):void type: STATIC in method: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.downloadImage(java.io.File):boolean
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:203)
	at jadx.core.codegen.RegionGen.makeSimpleBlock(RegionGen.java:100)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:50)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.RegionGen.makeRegionIndent(RegionGen.java:93)
	at jadx.core.codegen.RegionGen.makeTryCatch(RegionGen.java:297)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:63)
	at jadx.core.codegen.RegionGen.makeSimpleRegion(RegionGen.java:87)
	at jadx.core.codegen.RegionGen.makeRegion(RegionGen.java:53)
	at jadx.core.codegen.MethodGen.addInstructions(MethodGen.java:183)
	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:328)
	at jadx.core.codegen.ClassGen.addMethods(ClassGen.java:265)
	at jadx.core.codegen.ClassGen.addClassBody(ClassGen.java:228)
	at jadx.core.codegen.ClassGen.addClassCode(ClassGen.java:118)
	at jadx.core.codegen.ClassGen.makeClass(ClassGen.java:83)
	at jadx.core.codegen.CodeGen.visit(CodeGen.java:19)
	at jadx.core.ProcessClass.process(ProcessClass.java:43)
	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:306)
	at jadx.api.JavaClass.decompile(JavaClass.java:62)
	at jadx.api.JadxDecompiler$1.run(JadxDecompiler.java:199)
Caused by: jadx.core.utils.exceptions.CodegenException: Error generate insn: ?: MERGE  (r0_3 java.io.Closeable) = (r0_2 'bufferedOutputStream' java.io.Closeable), (r1_1 'stream' java.io.Closeable) in method: com.nostra13.universalimageloader.core.LoadAndDisplayImageTask.downloadImage(java.io.File):boolean
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:226)
	at jadx.core.codegen.InsnGen.addArg(InsnGen.java:101)
	at jadx.core.codegen.InsnGen.generateMethodArguments(InsnGen.java:679)
	at jadx.core.codegen.InsnGen.makeInvoke(InsnGen.java:649)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:343)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:220)
	... 21 more
Caused by: jadx.core.utils.exceptions.CodegenException: MERGE can be used only in fallback mode
	at jadx.core.codegen.InsnGen.fallbackOnlyInsn(InsnGen.java:530)
	at jadx.core.codegen.InsnGen.makeInsnBody(InsnGen.java:514)
	at jadx.core.codegen.InsnGen.makeInsn(InsnGen.java:211)
	... 26 more

*/

            private boolean resizeAndSaveImage(File file, int i, int i2) throws IOException {
                Bitmap decode = this.decoder.decode(new ImageDecodingInfo(this.memoryCacheKey, Scheme.FILE.wrap(file.getAbsolutePath()), new ImageSize(i, i2), ViewScaleType.FIT_INSIDE, getDownloader(), new Builder().cloneFrom(this.options).imageScaleType(ImageScaleType.IN_SAMPLE_INT).build()));
                if (!(decode == null || this.configuration.processorForDiscCache == null)) {
                    log(LOG_PROCESS_IMAGE_BEFORE_CACHE_ON_DISC);
                    decode = this.configuration.processorForDiscCache.process(decode);
                    if (decode == null) {
                        L.e(ERROR_PROCESSOR_FOR_DISC_CACHE_NULL, this.memoryCacheKey);
                    }
                }
                if (decode != null) {
                    Closeable bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file), 32768);
                    try {
                        decode.compress(this.configuration.imageCompressFormatForDiscCache, this.configuration.imageQualityForDiscCache, bufferedOutputStream);
                        decode.recycle();
                    } finally {
                        IoUtils.closeSilently(bufferedOutputStream);
                    }
                }
                return true;
            }

            public boolean onBytesCopied(int i, int i2) {
                return this.progressListener == null || fireProgressEvent(i, i2);
            }

            private boolean fireProgressEvent(final int i, final int i2) {
                if (this.options.isSyncLoading() || isTaskInterrupted() || isTaskNotActual()) {
                    return false;
                }
                runTask(new Runnable() {
                    public void run() {
                        LoadAndDisplayImageTask.this.progressListener.onProgressUpdate(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), i, i2);
                    }
                }, false, this.handler, this.engine);
                return true;
            }

            private void fireFailEvent(final FailType failType, final Throwable th) {
                if (!this.options.isSyncLoading() && !isTaskInterrupted() && !isTaskNotActual()) {
                    runTask(new Runnable() {
                        public void run() {
                            if (LoadAndDisplayImageTask.this.options.shouldShowImageOnFail()) {
                                LoadAndDisplayImageTask.this.imageAware.setImageDrawable(LoadAndDisplayImageTask.this.options.getImageOnFail(LoadAndDisplayImageTask.this.configuration.resources));
                            }
                            LoadAndDisplayImageTask.this.listener.onLoadingFailed(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView(), new FailReason(failType, th));
                        }
                    }, false, this.handler, this.engine);
                }
            }

            private void fireCancelEvent() {
                if (!this.options.isSyncLoading() && !isTaskInterrupted()) {
                    runTask(new Runnable() {
                        public void run() {
                            LoadAndDisplayImageTask.this.listener.onLoadingCancelled(LoadAndDisplayImageTask.this.uri, LoadAndDisplayImageTask.this.imageAware.getWrappedView());
                        }
                    }, false, this.handler, this.engine);
                }
            }

            private ImageDownloader getDownloader() {
                if (this.engine.isNetworkDenied()) {
                    return this.networkDeniedDownloader;
                }
                if (this.engine.isSlowNetwork()) {
                    return this.slowNetworkDownloader;
                }
                return this.downloader;
            }

            private void checkTaskNotActual() throws TaskCancelledException {
                checkViewCollected();
                checkViewReused();
            }

            private boolean isTaskNotActual() {
                return isViewCollected() || isViewReused();
            }

            private void checkViewCollected() throws TaskCancelledException {
                if (isViewCollected()) {
                    throw new TaskCancelledException();
                }
            }

            private boolean isViewCollected() {
                if (!this.imageAware.isCollected()) {
                    return false;
                }
                log(LOG_TASK_CANCELLED_IMAGEAWARE_COLLECTED);
                return true;
            }

            private void checkViewReused() throws TaskCancelledException {
                if (isViewReused()) {
                    throw new TaskCancelledException();
                }
            }

            private boolean isViewReused() {
                if (!(!this.memoryCacheKey.equals(this.engine.getLoadingUriForView(this.imageAware)))) {
                    return false;
                }
                log(LOG_TASK_CANCELLED_IMAGEAWARE_REUSED);
                return true;
            }

            private void checkTaskInterrupted() throws TaskCancelledException {
                if (isTaskInterrupted()) {
                    throw new TaskCancelledException();
                }
            }

            private boolean isTaskInterrupted() {
                if (!Thread.interrupted()) {
                    return false;
                }
                log(LOG_TASK_INTERRUPTED);
                return true;
            }

            String getLoadingUri() {
                return this.uri;
            }

            private void log(String str) {
                if (this.writeLogs) {
                    L.d(str, this.memoryCacheKey);
                }
            }

            private void log(String str, Object... objArr) {
                if (this.writeLogs) {
                    L.d(str, objArr);
                }
            }

            static void runTask(Runnable runnable, boolean z, Handler handler, ImageLoaderEngine imageLoaderEngine) {
                if (z) {
                    runnable.run();
                } else if (handler == null) {
                    imageLoaderEngine.fireCallback(runnable);
                } else {
                    handler.post(runnable);
                }
            }
        }
