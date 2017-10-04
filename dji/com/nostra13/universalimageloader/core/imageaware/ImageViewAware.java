package com.nostra13.universalimageloader.core.imageaware;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Looper;
import android.view.ViewGroup.LayoutParams;
import android.widget.ImageView;
import com.nostra13.universalimageloader.core.assist.ViewScaleType;
import com.nostra13.universalimageloader.utils.L;
import java.lang.ref.Reference;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;

public class ImageViewAware implements ImageAware {
    public static final String WARN_CANT_SET_BITMAP = "Can't set a bitmap into view. You should call ImageLoader on UI thread for it.";
    public static final String WARN_CANT_SET_DRAWABLE = "Can't set a drawable into view. You should call ImageLoader on UI thread for it.";
    protected boolean checkActualViewSize;
    protected Reference<ImageView> imageViewRef;

    public ImageViewAware(ImageView imageView) {
        this(imageView, true);
    }

    public ImageViewAware(ImageView imageView, boolean z) {
        this.imageViewRef = new WeakReference(imageView);
        this.checkActualViewSize = z;
    }

    public int getWidth() {
        int i = 0;
        ImageView imageView = (ImageView) this.imageViewRef.get();
        if (imageView == null) {
            return 0;
        }
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (!(!this.checkActualViewSize || layoutParams == null || layoutParams.width == -2)) {
            i = imageView.getWidth();
        }
        if (i <= 0 && layoutParams != null) {
            i = layoutParams.width;
        }
        if (i <= 0) {
            return getImageViewFieldValue(imageView, "mMaxWidth");
        }
        return i;
    }

    public int getHeight() {
        int i = 0;
        ImageView imageView = (ImageView) this.imageViewRef.get();
        if (imageView == null) {
            return 0;
        }
        LayoutParams layoutParams = imageView.getLayoutParams();
        if (!(!this.checkActualViewSize || layoutParams == null || layoutParams.height == -2)) {
            i = imageView.getHeight();
        }
        if (i <= 0 && layoutParams != null) {
            i = layoutParams.height;
        }
        if (i <= 0) {
            return getImageViewFieldValue(imageView, "mMaxHeight");
        }
        return i;
    }

    public ViewScaleType getScaleType() {
        ImageView imageView = (ImageView) this.imageViewRef.get();
        if (imageView != null) {
            return ViewScaleType.fromImageView(imageView);
        }
        return null;
    }

    public ImageView getWrappedView() {
        return (ImageView) this.imageViewRef.get();
    }

    public boolean isCollected() {
        return this.imageViewRef.get() == null;
    }

    public int getId() {
        ImageView imageView = (ImageView) this.imageViewRef.get();
        return imageView == null ? super.hashCode() : imageView.hashCode();
    }

    private static int getImageViewFieldValue(Object obj, String str) {
        try {
            Field declaredField = ImageView.class.getDeclaredField(str);
            declaredField.setAccessible(true);
            int intValue = ((Integer) declaredField.get(obj)).intValue();
            if (intValue > 0 && intValue < Integer.MAX_VALUE) {
                return intValue;
            }
        } catch (Throwable e) {
            L.e(e);
        }
        return 0;
    }

    public boolean setImageDrawable(Drawable drawable) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ImageView imageView = (ImageView) this.imageViewRef.get();
            if (imageView != null) {
                imageView.setImageDrawable(drawable);
                return true;
            }
        }
        L.w(WARN_CANT_SET_DRAWABLE, new Object[0]);
        return false;
    }

    public boolean setImageBitmap(Bitmap bitmap) {
        if (Looper.myLooper() == Looper.getMainLooper()) {
            ImageView imageView = (ImageView) this.imageViewRef.get();
            if (imageView != null) {
                imageView.setImageBitmap(bitmap);
                return true;
            }
        }
        L.w(WARN_CANT_SET_BITMAP, new Object[0]);
        return false;
    }
}
