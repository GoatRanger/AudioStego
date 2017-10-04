package dji.device.common.view;

import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class DJIAutoRotateImageView extends ImageView {
    private Matrix a = new Matrix();

    public DJIAutoRotateImageView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a();
    }

    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        a(configuration.orientation);
    }

    private void a() {
        if (getResources().getConfiguration().orientation == 1) {
            b(90);
        } else {
            b(0);
        }
    }

    private void a(int i) {
        int i2 = 0;
        if (i == 1) {
            i2 = 90;
        } else {
            b(-90);
        }
        b(i2);
    }

    private void b(int i) {
        Bitmap bitmap = ((BitmapDrawable) getDrawable()).getBitmap();
        this.a.setRotate((float) i);
        setImageBitmap(Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), this.a, true));
    }

    public void setImageResource(int i) {
        super.setImageResource(i);
        a();
    }

    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
