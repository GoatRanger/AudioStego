package com.nostra13.universalimageloader.core.decode;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Matrix;
import android.os.Build.VERSION;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme;
import com.nostra13.universalimageloader.utils.ImageSizeUtils;
import com.nostra13.universalimageloader.utils.IoUtils;
import com.nostra13.universalimageloader.utils.L;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;

public class BaseImageDecoder implements ImageDecoder {
    protected static final String ERROR_CANT_DECODE_IMAGE = "Image can't be decoded [%s]";
    protected static final String LOG_FLIP_IMAGE = "Flip image horizontally [%s]";
    protected static final String LOG_ROTATE_IMAGE = "Rotate image on %1$d° [%2$s]";
    protected static final String LOG_SABSAMPLE_IMAGE = "Subsample original image (%1$s) to %2$s (scale = %3$d) [%4$s]";
    protected static final String LOG_SCALE_IMAGE = "Scale subsampled image (%1$s) to %2$s (scale = %3$.5f) [%4$s]";
    protected final boolean loggingEnabled;

    protected static class ExifInfo {
        public final boolean flipHorizontal;
        public final int rotation;

        protected ExifInfo() {
            this.rotation = 0;
            this.flipHorizontal = false;
        }

        protected ExifInfo(int i, boolean z) {
            this.rotation = i;
            this.flipHorizontal = z;
        }
    }

    protected static class ImageFileInfo {
        public final ExifInfo exif;
        public final ImageSize imageSize;

        protected ImageFileInfo(ImageSize imageSize, ExifInfo exifInfo) {
            this.imageSize = imageSize;
            this.exif = exifInfo;
        }
    }

    public BaseImageDecoder(boolean z) {
        this.loggingEnabled = z;
    }

    public Bitmap decode(ImageDecodingInfo imageDecodingInfo) throws IOException {
        Closeable imageStream = getImageStream(imageDecodingInfo);
        try {
            ImageFileInfo defineImageSizeAndRotation = defineImageSizeAndRotation(imageStream, imageDecodingInfo);
            imageStream = resetStream(imageStream, imageDecodingInfo);
            Bitmap decodeStream = BitmapFactory.decodeStream(imageStream, null, prepareDecodingOptions(defineImageSizeAndRotation.imageSize, imageDecodingInfo));
            if (decodeStream != null) {
                return considerExactScaleAndOrientaiton(decodeStream, imageDecodingInfo, defineImageSizeAndRotation.exif.rotation, defineImageSizeAndRotation.exif.flipHorizontal);
            }
            L.e(ERROR_CANT_DECODE_IMAGE, imageDecodingInfo.getImageKey());
            return decodeStream;
        } finally {
            IoUtils.closeSilently(imageStream);
        }
    }

    protected InputStream getImageStream(ImageDecodingInfo imageDecodingInfo) throws IOException {
        return imageDecodingInfo.getDownloader().getStream(imageDecodingInfo.getImageUri(), imageDecodingInfo.getExtraForDownloader());
    }

    protected ImageFileInfo defineImageSizeAndRotation(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        ExifInfo defineExifOrientation;
        Options options = new Options();
        options.inJustDecodeBounds = true;
        BitmapFactory.decodeStream(inputStream, null, options);
        String imageUri = imageDecodingInfo.getImageUri();
        if (imageDecodingInfo.shouldConsiderExifParams() && canDefineExifParams(imageUri, options.outMimeType)) {
            defineExifOrientation = defineExifOrientation(imageUri);
        } else {
            defineExifOrientation = new ExifInfo();
        }
        return new ImageFileInfo(new ImageSize(options.outWidth, options.outHeight, defineExifOrientation.rotation), defineExifOrientation);
    }

    private boolean canDefineExifParams(String str, String str2) {
        return VERSION.SDK_INT >= 5 && "image/jpeg".equalsIgnoreCase(str2) && Scheme.ofUri(str) == Scheme.FILE;
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.nostra13.universalimageloader.core.decode.BaseImageDecoder.ExifInfo defineExifOrientation(java.lang.String r6) {
        /*
        r5 = this;
        r1 = 0;
        r0 = 1;
        r2 = new android.media.ExifInterface;	 Catch:{ IOException -> 0x002a }
        r3 = com.nostra13.universalimageloader.core.download.ImageDownloader.Scheme.FILE;	 Catch:{ IOException -> 0x002a }
        r3 = r3.crop(r6);	 Catch:{ IOException -> 0x002a }
        r2.<init>(r3);	 Catch:{ IOException -> 0x002a }
        r3 = "Orientation";
        r4 = 1;
        r2 = r2.getAttributeInt(r3, r4);	 Catch:{ IOException -> 0x002a }
        switch(r2) {
            case 1: goto L_0x0017;
            case 2: goto L_0x0018;
            case 3: goto L_0x0022;
            case 4: goto L_0x0023;
            case 5: goto L_0x0027;
            case 6: goto L_0x001e;
            case 7: goto L_0x001f;
            case 8: goto L_0x0026;
            default: goto L_0x0017;
        };
    L_0x0017:
        r0 = r1;
    L_0x0018:
        r2 = new com.nostra13.universalimageloader.core.decode.BaseImageDecoder$ExifInfo;
        r2.<init>(r1, r0);
        return r2;
    L_0x001e:
        r0 = r1;
    L_0x001f:
        r1 = 90;
        goto L_0x0018;
    L_0x0022:
        r0 = r1;
    L_0x0023:
        r1 = 180; // 0xb4 float:2.52E-43 double:8.9E-322;
        goto L_0x0018;
    L_0x0026:
        r0 = r1;
    L_0x0027:
        r1 = 270; // 0x10e float:3.78E-43 double:1.334E-321;
        goto L_0x0018;
    L_0x002a:
        r2 = move-exception;
        r2 = "Can't read EXIF tags from file [%s]";
        r0 = new java.lang.Object[r0];
        r0[r1] = r6;
        com.nostra13.universalimageloader.utils.L.w(r2, r0);
        goto L_0x0017;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.nostra13.universalimageloader.core.decode.BaseImageDecoder.defineExifOrientation(java.lang.String):com.nostra13.universalimageloader.core.decode.BaseImageDecoder$ExifInfo");
    }

    protected Options prepareDecodingOptions(ImageSize imageSize, ImageDecodingInfo imageDecodingInfo) {
        int computeMinImageSampleSize;
        ImageScaleType imageScaleType = imageDecodingInfo.getImageScaleType();
        if (imageScaleType == ImageScaleType.NONE) {
            computeMinImageSampleSize = ImageSizeUtils.computeMinImageSampleSize(imageSize);
        } else {
            boolean z;
            ImageSize targetSize = imageDecodingInfo.getTargetSize();
            if (imageScaleType == ImageScaleType.IN_SAMPLE_POWER_OF_2) {
                z = true;
            } else {
                z = false;
            }
            computeMinImageSampleSize = ImageSizeUtils.computeImageSampleSize(imageSize, targetSize, imageDecodingInfo.getViewScaleType(), z);
        }
        if (computeMinImageSampleSize > 1 && this.loggingEnabled) {
            L.d(LOG_SABSAMPLE_IMAGE, imageSize, imageSize.scaleDown(computeMinImageSampleSize), Integer.valueOf(computeMinImageSampleSize), imageDecodingInfo.getImageKey());
        }
        Options decodingOptions = imageDecodingInfo.getDecodingOptions();
        decodingOptions.inSampleSize = computeMinImageSampleSize;
        return decodingOptions;
    }

    protected InputStream resetStream(InputStream inputStream, ImageDecodingInfo imageDecodingInfo) throws IOException {
        try {
            inputStream.reset();
            return inputStream;
        } catch (IOException e) {
            IoUtils.closeSilently(inputStream);
            return getImageStream(imageDecodingInfo);
        }
    }

    protected Bitmap considerExactScaleAndOrientaiton(Bitmap bitmap, ImageDecodingInfo imageDecodingInfo, int i, boolean z) {
        Matrix matrix = new Matrix();
        ImageScaleType imageScaleType = imageDecodingInfo.getImageScaleType();
        if (imageScaleType == ImageScaleType.EXACTLY || imageScaleType == ImageScaleType.EXACTLY_STRETCHED) {
            float computeImageScale = ImageSizeUtils.computeImageScale(new ImageSize(bitmap.getWidth(), bitmap.getHeight(), i), imageDecodingInfo.getTargetSize(), imageDecodingInfo.getViewScaleType(), imageScaleType == ImageScaleType.EXACTLY_STRETCHED);
            if (Float.compare(computeImageScale, 1.0f) != 0) {
                matrix.setScale(computeImageScale, computeImageScale);
                if (this.loggingEnabled) {
                    L.d(LOG_SCALE_IMAGE, r2, r2.scale(computeImageScale), Float.valueOf(computeImageScale), imageDecodingInfo.getImageKey());
                }
            }
        }
        if (z) {
            matrix.postScale(-1.0f, 1.0f);
            if (this.loggingEnabled) {
                L.d(LOG_FLIP_IMAGE, imageDecodingInfo.getImageKey());
            }
        }
        if (i != 0) {
            matrix.postRotate((float) i);
            if (this.loggingEnabled) {
                L.d(LOG_ROTATE_IMAGE, Integer.valueOf(i), imageDecodingInfo.getImageKey());
            }
        }
        Bitmap createBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);
        if (createBitmap != bitmap) {
            bitmap.recycle();
        }
        return createBitmap;
    }
}
