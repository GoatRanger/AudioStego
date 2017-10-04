package dji.pilot.joystick.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;
import dji.pilot.R;

public class OnScreenJoystick2 extends SurfaceView implements OnTouchListener {
    private a a;
    private boolean b = true;
    private Paint c;
    private float d;
    private float e;
    private float f;
    private float g;

    public OnScreenJoystick2(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        a(attributeSet);
        if (!isInEditMode()) {
            a();
        }
    }

    private void a(AttributeSet attributeSet) {
    }

    private void a() {
        this.c = new Paint();
        this.c.setAntiAlias(true);
        this.c.setColor(getResources().getColor(R.color.l));
        this.c.setAlpha(80);
        setZOrderOnTop(true);
        setOnTouchListener(this);
        setEnabled(true);
        setAutoCentering(true);
    }

    public void setAutoCentering(boolean z) {
        this.b = z;
    }

    public boolean isAutoCentering() {
        return this.b;
    }

    public void setJoystickListener(a aVar) {
        this.a = aVar;
    }

    protected void onDraw(Canvas canvas) {
        if (!isInEditMode()) {
            if (this.d == 0.0f) {
                this.d = (float) (getWidth() / 2);
                this.e = (float) (getHeight() / 2);
                this.f = this.d;
                this.g = this.e;
            }
            canvas.drawCircle(this.f, this.g, 30.0f, this.c);
            super.onDraw(canvas);
        }
    }

    public boolean performClick() {
        return super.performClick();
    }

    public boolean onTouch(View view, MotionEvent motionEvent) {
        this.f = motionEvent.getX();
        this.g = motionEvent.getY();
        switch (motionEvent.getAction()) {
            case 1:
            case 3:
                if (isAutoCentering()) {
                    this.f = this.d;
                    this.g = this.e;
                }
                view.performClick();
                setVisibility(8);
                if (this.a != null) {
                    this.a.a();
                    break;
                }
                break;
            default:
                getRelX();
                getRelY();
                break;
        }
        if (this.f < 0.0f) {
            this.f = 0.0f;
        } else if (this.f > ((float) getWidth())) {
            this.f = (float) getWidth();
        }
        if (this.g < 0.0f) {
            this.g = 0.0f;
        } else if (this.g > ((float) getHeight())) {
            this.g = (float) getHeight();
        }
        if (this.a != null) {
            this.a.a(getJoyScaleX(), getJoyScaleY());
        }
        invalidate();
        return true;
    }

    private float getJoyScaleY() {
        return (this.g - this.e) / this.e;
    }

    private float getJoyScaleX() {
        return (this.f - this.d) / this.d;
    }

    private void getRelY() {
        if (this.f <= 0.0f) {
            setX(getX() + this.f);
            this.f = 0.0f;
        } else if (this.f > ((float) getWidth())) {
            setX((getX() + this.f) - ((float) getWidth()));
            this.f = (float) getWidth();
        }
    }

    private void getRelX() {
        if (this.g <= 0.0f) {
            setY(getY() + this.g);
            this.g = 0.0f;
        } else if (this.g > ((float) getHeight())) {
            setY((getY() + this.g) - ((float) getHeight()));
            this.g = (float) getHeight();
        }
    }
}
