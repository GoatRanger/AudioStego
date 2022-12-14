package uk.co.senab.photoview;

import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.graphics.RectF;
import android.view.GestureDetector.OnDoubleTapListener;
import android.view.View.OnLongClickListener;
import android.widget.ImageView.ScaleType;
import uk.co.senab.photoview.PhotoViewAttacher.OnMatrixChangedListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnPhotoTapListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnScaleChangeListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnSingleFlingListener;
import uk.co.senab.photoview.PhotoViewAttacher.OnViewTapListener;

public interface IPhotoView {
    public static final float DEFAULT_MAX_SCALE = 3.0f;
    public static final float DEFAULT_MID_SCALE = 1.75f;
    public static final float DEFAULT_MIN_SCALE = 1.0f;
    public static final int DEFAULT_ZOOM_DURATION = 200;

    boolean canZoom();

    void getDisplayMatrix(Matrix matrix);

    RectF getDisplayRect();

    IPhotoView getIPhotoViewImplementation();

    float getMaximumScale();

    float getMediumScale();

    float getMinimumScale();

    float getScale();

    ScaleType getScaleType();

    Bitmap getVisibleRectangleBitmap();

    void setAllowParentInterceptOnEdge(boolean z);

    boolean setDisplayMatrix(Matrix matrix);

    void setMaximumScale(float f);

    void setMediumScale(float f);

    void setMinimumScale(float f);

    void setOnDoubleTapListener(OnDoubleTapListener onDoubleTapListener);

    void setOnLongClickListener(OnLongClickListener onLongClickListener);

    void setOnMatrixChangeListener(OnMatrixChangedListener onMatrixChangedListener);

    void setOnPhotoTapListener(OnPhotoTapListener onPhotoTapListener);

    void setOnScaleChangeListener(OnScaleChangeListener onScaleChangeListener);

    void setOnSingleFlingListener(OnSingleFlingListener onSingleFlingListener);

    void setOnViewTapListener(OnViewTapListener onViewTapListener);

    void setRotationBy(float f);

    void setRotationTo(float f);

    void setScale(float f);

    void setScale(float f, float f2, float f3, boolean z);

    void setScale(float f, boolean z);

    void setScaleLevels(float f, float f2, float f3);

    void setScaleType(ScaleType scaleType);

    void setZoomTransitionDuration(int i);

    void setZoomable(boolean z);
}
