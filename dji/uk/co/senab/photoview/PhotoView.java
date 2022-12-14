package uk.co.senab.photoview;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.util.AttributeSet;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnScaleChangeListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnSingleFlingListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public class PhotoView extends ImageView implements IPhotoView {
    private PhotoViewAttacher mAttacher;
    private ScaleType mPendingScaleType;

    public PhotoView(Context context) {
        this(context, null);
    }

    public PhotoView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public PhotoView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        super.setScaleType(ScaleType.MATRIX);
        init();
    }

    protected void init() {
        if (this.mAttacher == null || this.mAttacher.getImageView() == null) {
            this.mAttacher = new PhotoViewAttacher(this);
        }
        if (this.mPendingScaleType != null) {
            setScaleType(this.mPendingScaleType);
            this.mPendingScaleType = null;
        }
    }

    public void setRotationTo(float f) {
        this.mAttacher.setRotationTo(f);
    }

    public void setRotationBy(float f) {
        this.mAttacher.setRotationBy(f);
    }

    public boolean canZoom() {
        return this.mAttacher.canZoom();
    }

    public RectF getDisplayRect() {
        return this.mAttacher.getDisplayRect();
    }

    public void getDisplayMatrix(Matrix matrix) {
        this.mAttacher.getDisplayMatrix(matrix);
    }

    public boolean setDisplayMatrix(Matrix matrix) {
        return this.mAttacher.setDisplayMatrix(matrix);
    }

    public float getMinimumScale() {
        return this.mAttacher.getMinimumScale();
    }

    public float getMediumScale() {
        return this.mAttacher.getMediumScale();
    }

    public float getMaximumScale() {
        return this.mAttacher.getMaximumScale();
    }

    public float getScale() {
        return this.mAttacher.getScale();
    }

    public ScaleType getScaleType() {
        return this.mAttacher.getScaleType();
    }

    public Matrix getImageMatrix() {
        return this.mAttacher.getImageMatrix();
    }

    public void setAllowParentInterceptOnEdge(boolean z) {
        this.mAttacher.setAllowParentInterceptOnEdge(z);
    }

    public void setMinimumScale(float f) {
        this.mAttacher.setMinimumScale(f);
    }

    public void setMediumScale(float f) {
        this.mAttacher.setMediumScale(f);
    }

    public void setMaximumScale(float f) {
        this.mAttacher.setMaximumScale(f);
    }

    public void setScaleLevels(float f, float f2, float f3) {
        this.mAttacher.setScaleLevels(f, f2, f3);
    }

    public void setImageDrawable(Drawable drawable) {
        super.setImageDrawable(drawable);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    public void setImageURI(Uri uri) {
        super.setImageURI(uri);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
    }

    protected boolean setFrame(int i, int i2, int i3, int i4) {
        boolean frame = super.setFrame(i, i2, i3, i4);
        if (this.mAttacher != null) {
            this.mAttacher.update();
        }
        return frame;
    }

    public void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener) {
        this.mAttacher.setOnMatrixChangeListener(onMatrixChangedListener);
    }

    public void setOnLongClickListener(OnLongClickListener onLongClickListener) {
        this.mAttacher.setOnLongClickListener(onLongClickListener);
    }

    public void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener) {
        this.mAttacher.setOnPhotoTapListener(onPhotoTapListener);
    }

    public void setOnViewTapListener(OnViewTapListener onViewTapListener) {
        this.mAttacher.setOnViewTapListener(onViewTapListener);
    }

    public void setScale(float f) {
        this.mAttacher.setScale(f);
    }

    public void setScale(float f, boolean z) {
        this.mAttacher.setScale(f, z);
    }

    public void setScale(float f, float f2, float f3, boolean z) {
        this.mAttacher.setScale(f, f2, f3, z);
    }

    public void setScaleType(ScaleType scaleType) {
        if (this.mAttacher != null) {
            this.mAttacher.setScaleType(scaleType);
        } else {
            this.mPendingScaleType = scaleType;
        }
    }

    public void setZoomable(boolean z) {
        this.mAttacher.setZoomable(z);
    }

    public Bitmap getVisibleRectangleBitmap() {
        return this.mAttacher.getVisibleRectangleBitmap();
    }

    public void setZoomTransitionDuration(int i) {
        this.mAttacher.setZoomTransitionDuration(i);
    }

    public IPhotoView getIPhotoViewImplementation() {
        return this.mAttacher;
    }

    public void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener) {
        this.mAttacher.setOnDoubleTapListener(onDoubleTapListener);
    }

    public void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener) {
        this.mAttacher.setOnScaleChangeListener(onScaleChangeListener);
    }

    public void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener) {
        this.mAttacher.setOnSingleFlingListener(onSingleFlingListener);
    }

    protected void onDetachedFromWindow() {
        this.mAttacher.cleanup();
        this.mAttacher = null;
        super.onDetachedFromWindow();
    }

    protected void onAttachedToWindow() {
        init();
        super.onAttachedToWindow();
    }
}
