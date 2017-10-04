package dji.pilot.fpv.camera.roi;

import android.content.Context;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import com.dji.frame.c.e;
import dji.pilot.R;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIAreaRoiView extends DJIRelativeLayout {
    public static int a = Integer.MIN_VALUE;
    public static int b = Integer.MIN_VALUE;
    private DJIImageView c = null;
    private final RectF d = new RectF();

    public DJIAreaRoiView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode() && Integer.MIN_VALUE == a) {
            Drawable drawable = context.getResources().getDrawable(R.drawable.camera_roi_expand_icon);
            int b = e.b(context, 1.0f);
            a = drawable.getIntrinsicWidth() + (b * 2);
            b = drawable.getIntrinsicHeight() + (b * 2);
        }
    }

    public void setExpandListener(OnClickListener onClickListener) {
        this.c.setOnClickListener(onClickListener);
    }

    public boolean pointInView2(float f, float f2) {
        float x = getX();
        float y = getY();
        LayoutParams layoutParams = getLayoutParams();
        return f >= x && f < x + ((float) layoutParams.width) && f2 >= y && f2 < ((float) layoutParams.height) + y;
    }

    public final RectF getLocalRect() {
        this.d.setEmpty();
        float x = getX();
        float y = getY();
        LayoutParams layoutParams = getLayoutParams();
        this.d.set(x, y, ((float) layoutParams.width) + x, ((float) layoutParams.height) + y);
        return this.d;
    }

    public void dragByDelta(float f, float f2, int i, int i2) {
        float f3;
        float f4 = 0.0f;
        int width = getWidth();
        int height = getHeight();
        float x = getX();
        float y = getY();
        x += f;
        if (x < 0.0f) {
            f3 = 0.0f;
        } else if (x > ((float) (i - width))) {
            f3 = (float) (i - width);
        } else {
            f3 = x;
        }
        x = y + f2;
        if (x >= 0.0f) {
            f4 = x > ((float) (i2 - height)) ? (float) (i2 - height) : x;
        }
        setX(f3);
        setY(f4);
    }

    public void updateParam(float f, float f2, int i, int i2) {
        show();
        setX(f);
        setY(f2);
        if (i < a) {
            i = a;
        }
        if (i2 < b) {
            i2 = b;
        }
        LayoutParams layoutParams = getLayoutParams();
        if (layoutParams.width != i || layoutParams.height != i2) {
            layoutParams.width = i;
            layoutParams.height = i2;
            setLayoutParams(layoutParams);
        }
    }

    public void updateLocation(float f, float f2, float f3, float f4) {
        show();
        int abs = (int) Math.abs(f - f3);
        if (f <= f3) {
            f3 = f;
        }
        int abs2 = (int) Math.abs(f2 - f4);
        if (f2 <= f4) {
            f4 = f2;
        }
        setX(f3);
        setY(f4);
        if (abs < a) {
            abs = a;
        }
        if (abs2 < b) {
            abs2 = b;
        }
        LayoutParams layoutParams = getLayoutParams();
        if (layoutParams.width != abs || layoutParams.height != abs2) {
            layoutParams.width = abs;
            layoutParams.height = abs2;
            setLayoutParams(layoutParams);
        }
    }

    protected void onFinishInflate() {
        this.c = (DJIImageView) findViewById(R.id.k8);
    }
}
