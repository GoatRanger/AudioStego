package dji.pilot.visual.view;

import android.content.Context;
import android.graphics.RectF;
import android.support.v4.widget.AutoScrollHelper;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup.MarginLayoutParams;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import dji.midware.data.model.P3.DataOsdGetPushCommon;
import dji.midware.data.model.P3.DataOsdGetPushHome;
import dji.pilot.R;
import dji.pilot.fpv.d.b;
import dji.pilot.visual.a.c;
import dji.pilot.visual.a.c$a;
import dji.pilot.visual.a.d;
import dji.publics.DJIUI.DJIImageView;
import dji.publics.DJIUI.DJIRelativeLayout;

public class DJIVisualHorizontalView extends DJIRelativeLayout {
    private static final float a = 47.0f;
    private static final float b = 20.0f;
    private static final boolean c = true;
    private static float d = 0.0f;
    private DJIImageView e = null;
    private DJIImageView f = null;
    private c g;
    private float h = AutoScrollHelper.NO_MAX;
    private float i = AutoScrollHelper.NO_MAX;
    private double j = 0.0d;
    private double k = 0.0d;
    private double l = 0.0d;
    private double m = 0.0d;
    private float n = 0.0f;
    private float o = 0.0f;
    private boolean p = false;
    private float q = 0.0f;
    private final RectF r = new RectF();
    private Animation s = null;

    public DJIVisualHorizontalView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        if (!isInEditMode()) {
            this.g = c.getInstance();
        }
    }

    public void show() {
        super.show();
        onEventMainThread(this.g.i());
        if (DataOsdGetPushCommon.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushCommon.getInstance());
        }
        if (DataOsdGetPushHome.getInstance().isGetted()) {
            onEventMainThread(DataOsdGetPushHome.getInstance());
        }
    }

    public final RectF getLocationSelf() {
        float x;
        float y;
        if (0.0f == d) {
            x = getX();
            y = getY();
            this.r.set(x, y - d, ((float) getWidth()) + x, (y + ((float) getHeight())) + d);
        } else {
            x = getX();
            y = getY();
            this.r.set(x, y - d, ((float) getWidth()) + x, (y + ((float) getHeight())) + d);
        }
        return this.r;
    }

    public void handleTouchEvent(MotionEvent motionEvent, boolean z) {
        if (z || motionEvent.getAction() == 3 || motionEvent.getAction() == 1) {
            setAlpha(1.0f);
        } else if (getLocationSelf().contains(motionEvent.getX(), motionEvent.getY())) {
            setAlpha(0.3f);
        } else {
            setAlpha(1.0f);
        }
    }

    public void blinkByClick() {
        if (this.s == null) {
            this.s = new AlphaAnimation(1.0f, 0.3f);
            this.s.setDuration(100);
            this.s.setRepeatCount(6);
            this.s.setRepeatMode(2);
        }
        startAnimation(this.s);
    }

    public void onEventMainThread(c$a dji_pilot_visual_a_c_a) {
        if (dji_pilot_visual_a_c_a != null && dji_pilot_visual_a_c_a.a != AutoScrollHelper.NO_MAX) {
            float f = dji_pilot_visual_a_c_a.a;
            float f2 = dji_pilot_visual_a_c_a.b;
            f2 = f2 > 0.0f ? 180.0f - f2 : -f2;
            f = (f / dji.pilot.visual.util.c.a(this.g.i)) + d.c;
            if (f != this.h) {
                this.h = f;
                setY(((f * ((float) this.g.e)) - (((float) this.e.getBackground().getIntrinsicHeight()) * d.c)) - ((float) getPaddingTop()));
            }
            if (f2 != this.i) {
                this.i = f2;
                setRotation(f2);
            }
            if (this.n != dji_pilot_visual_a_c_a.d) {
                this.n = dji_pilot_visual_a_c_a.d;
                b();
            }
        }
    }

    public void onEventMainThread(DataOsdGetPushCommon dataOsdGetPushCommon) {
        Object obj;
        float yaw = (((float) dataOsdGetPushCommon.getYaw()) * 0.1f) + 360.0f;
        if (yaw >= 360.0f) {
            yaw -= 360.0f;
        }
        if (this.q != yaw) {
            this.q = yaw;
            obj = 1;
        } else {
            obj = null;
        }
        double latitude = dataOsdGetPushCommon.getLatitude();
        double longitude = dataOsdGetPushCommon.getLongitude();
        if (!(this.j == latitude && this.k == longitude)) {
            this.j = latitude;
            this.k = longitude;
            a();
            obj = 2;
        }
        if (obj == 1) {
            b();
        }
    }

    public void onEventMainThread(DataOsdGetPushHome dataOsdGetPushHome) {
        double latitude = dataOsdGetPushHome.getLatitude();
        double longitude = dataOsdGetPushHome.getLongitude();
        if (this.l != latitude || this.m != longitude) {
            this.l = latitude;
            this.m = longitude;
            a();
        }
    }

    private void a() {
        if (b.a(this.l) && b.b(this.m) && b.a(this.j) && b.b(this.k)) {
            float[] c = b.c(this.l, this.m, this.j, this.k);
            if (c[1] >= b) {
                this.p = true;
                this.o = c[0] + 180.0f;
                while (this.o >= 360.0f) {
                    this.o -= 360.0f;
                }
            } else {
                this.p = false;
            }
        } else {
            this.p = false;
        }
        b();
    }

    private void b() {
        float f = 0.0f;
        if (this.p) {
            float f2;
            Object obj;
            int i;
            this.f.show();
            float f3 = (this.o - this.n) + this.q;
            while (f3 < 0.0f) {
                f3 += 360.0f;
            }
            while (f3 >= 360.0f) {
                f3 -= 360.0f;
            }
            if (180.0f <= f3 && f3 < 270.0f) {
                f = 180.0f;
            } else if (270.0f > f3 || f3 > 360.0f) {
                f = f3;
            }
            if (f <= 90.0f) {
                f2 = 90.0f - f;
                obj = null;
            } else if (f > 90.0f) {
                f2 = f - 90.0f;
                i = 1;
            } else {
                f2 = f;
                obj = null;
            }
            int intrinsicWidth = this.e.getBackground().getIntrinsicWidth();
            int intrinsicWidth2 = this.f.getBackground().getIntrinsicWidth();
            float f4 = ((float) (intrinsicWidth - intrinsicWidth2)) / 2.0f;
            MarginLayoutParams marginLayoutParams = (MarginLayoutParams) this.f.getLayoutParams();
            if (f2 < a) {
                f2 /= a;
                if (obj != null) {
                    i = (int) ((1.0f - f2) * f4);
                } else {
                    i = (int) ((1.0f + f2) * f4);
                }
            } else if (obj != null) {
                i = 0;
            } else {
                i = intrinsicWidth - intrinsicWidth2;
            }
            marginLayoutParams.leftMargin = i;
            this.f.setLayoutParams(marginLayoutParams);
            return;
        }
        this.f.hide();
    }

    protected void onFinishInflate() {
        if (!isInEditMode()) {
            this.e = (DJIImageView) findViewById(R.id.d9l);
            this.f = (DJIImageView) findViewById(R.id.d9m);
        }
    }
}
