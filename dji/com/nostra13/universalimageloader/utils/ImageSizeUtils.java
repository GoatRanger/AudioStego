package com.nostra13.universalimageloader.utils;

import android.opengl.GLES10;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.core.imageaware.ImageAware;

public final class ImageSizeUtils {
    private static final int DEFAULT_MAX_BITMAP_DIMENSION = 2048;
    private static ImageSize maxBitmapSize;

    static {
        int[] iArr = new int[1];
        GLES10.glGetIntegerv(3379, iArr, 0);
        int max = Math.max(iArr[0], 2048);
        maxBitmapSize = new ImageSize(max, max);
    }

    private ImageSizeUtils() {
    }

    public static ImageSize defineTargetSizeForView(ImageAware imageAware, ImageSize imageSize) {
        int width = imageAware.getWidth();
        if (width <= 0) {
            width = imageSize.getWidth();
        }
        int height = imageAware.getHeight();
        if (height <= 0) {
            height = imageSize.getHeight();
        }
        return new ImageSize(width, height);
    }

    public static int computeImageSampleSize(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        int width2 = imageSize2.getWidth();
        int height2 = imageSize2.getHeight();
        int i = width / width2;
        int i2 = height / height2;
        switch (viewScaleType) {
            case FIT_INSIDE:
                if (!z) {
                    height = Math.max(i, i2);
                    break;
                }
                i = width;
                width = height;
                height = 1;
                while (true) {
                    if (i / 2 < width2 && width / 2 < height2) {
                        break;
                    }
                    i /= 2;
                    width /= 2;
                    height *= 2;
                }
                break;
            case CROP:
                if (!z) {
                    height = Math.min(i, i2);
                    break;
                }
                i = width;
                width = height;
                height = 1;
                while (i / 2 >= width2 && width / 2 >= height2) {
                    i /= 2;
                    width /= 2;
                    height *= 2;
                }
                break;
            default:
                height = 1;
                break;
        }
        if (height < 1) {
            return 1;
        }
        return height;
    }

    public static int computeMinImageSampleSize(ImageSize imageSize) {
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        return Math.max((int) Math.ceil((double) (((float) width) / ((float) maxBitmapSize.getWidth()))), (int) Math.ceil((double) (((float) height) / ((float) maxBitmapSize.getHeight()))));
    }

    public static float computeImageScale(ImageSize imageSize, ImageSize imageSize2, ViewScaleType viewScaleType, boolean z) {
        int i;
        int width = imageSize.getWidth();
        int height = imageSize.getHeight();
        int width2 = imageSize2.getWidth();
        int height2 = imageSize2.getHeight();
        float f = ((float) width) / ((float) width2);
        float f2 = ((float) height) / ((float) height2);
        if ((viewScaleType != ViewScaleType.FIT_INSIDE || f < f2) && (viewScaleType != ViewScaleType.CROP || f >= f2)) {
            i = (int) (((float) width) / f2);
            width2 = height2;
        } else {
            i = width2;
            width2 = (int) (((float) height) / f);
        }
        if ((z || i >= width || r1 >= height) && (!z || i == width || r1 == height)) {
            return 1.0f;
        }
        return ((float) i) / ((float) width);
    }
}
