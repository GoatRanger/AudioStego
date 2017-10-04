package dji.pilot2.cutmoment;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.widget.RelativeLayout.LayoutParams;
import com.fasterxml.jackson.core.util.MinimalPrettyPrinter;
import dji.log.DJILogHelper;
import dji.midware.util.a.b;
import dji.pilot.R;
import dji.pilot.fpv.d.c$l;
import dji.pilot.fpv.d.e;
import dji.pilot2.utils.p;
import dji.publics.DJIUI.DJIOriLayout;
import dji.publics.DJIUI.DJIOriLayout.DJIDeviceType;

public class DJIMovingSurfaceView extends SurfaceView implements c$l {
    private static final String p = "DJIClipSurfaceView";
    private int A;
    private int B;
    private int C;
    private int D;
    private boolean E = true;
    private float F;
    private float G;
    private int H;
    private int I;
    private double[] J;
    private Context q;
    private a r;
    private boolean s;
    private int t;
    private int u;
    private float v;
    private int w;
    private int x;
    private float y;
    private int z;

    public enum a {
        DEFAULT,
        VERTICAL,
        HORIZONTAL
    }

    public DJIMovingSurfaceView(Context context) {
        super(context);
    }

    public DJIMovingSurfaceView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = context;
        a();
    }

    private void a() {
        this.v = b.a;
        this.I = p.e(this.q)[0];
        this.w = ((DJICutMomentActivity) this.q).h();
        this.x = ((DJICutMomentActivity) this.q).i();
        this.y = (((float) this.w) * 1.0f) / ((float) this.x);
        DJILogHelper.getInstance().LOGD(p, "video w h: " + this.w + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.x + ", video ratio: " + this.y);
        if (this.y >= this.v) {
            e.b(c$l.j);
            this.r = a.HORIZONTAL;
            setMovingOnOff(true);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.C = this.q.getResources().getDimensionPixelSize(R.dimen.c9);
                this.D = this.q.getResources().getDimensionPixelSize(R.dimen.fm);
                this.t = this.q.getResources().getDimensionPixelSize(R.dimen.a7w);
                this.u = (this.t * 9) / 16;
                this.z = (int) (((float) this.u) * this.y);
                this.A = this.u;
                this.B = this.z - this.t;
            } else {
                this.C = this.q.getResources().getDimensionPixelSize(R.dimen.ah);
                this.D = this.q.getResources().getDimensionPixelSize(R.dimen.a9);
                this.t = this.q.getResources().getDimensionPixelSize(R.dimen.ag);
                this.u = (this.t * 9) / 16;
                this.z = (int) (((float) this.u) * this.y);
                this.A = this.u;
                this.B = this.z - this.t;
            }
        } else {
            this.r = a.VERTICAL;
            setMovingOnOff(true);
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.C = this.q.getResources().getDimensionPixelSize(R.dimen.c9);
                this.D = this.q.getResources().getDimensionPixelSize(R.dimen.h8);
                this.t = this.I;
                this.u = (this.t * 9) / 16;
                this.z = this.t;
                this.A = (int) (((float) this.t) / this.y);
                this.B = this.A - this.u;
            } else {
                this.C = this.q.getResources().getDimensionPixelSize(R.dimen.ah);
                this.D = this.q.getResources().getDimensionPixelSize(R.dimen.a9);
                this.t = this.q.getResources().getDimensionPixelSize(R.dimen.ag);
                this.u = (this.t * 9) / 16;
                this.z = this.t;
                this.A = (int) (((float) this.t) / this.y);
                this.B = this.A - this.u;
            }
        }
        if (this.B <= 5) {
            this.r = a.DEFAULT;
            setMovingOnOff(false);
            backToNormalLayout();
        }
        this.H = (this.I - this.z) / 2;
        DJILogHelper.getInstance().LOGD(p, "window w h: " + this.t + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.u);
        DJILogHelper.getInstance().LOGD(p, "surface w h: " + this.z + MinimalPrettyPrinter.DEFAULT_ROOT_VALUE_SEPARATOR + this.A);
        DJILogHelper.getInstance().LOGD(p, "surplus: " + this.B);
    }

    private void a(int i, int i2) {
        LayoutParams layoutParams = (LayoutParams) getLayoutParams();
        layoutParams.topMargin = i;
        layoutParams.leftMargin = i2;
        setLayoutParams(layoutParams);
    }

    protected void onMeasure(int i, int i2) {
        setMeasuredDimension(this.z, this.A);
    }

    protected void onLayout(boolean z, int i, int i2, int i3, int i4) {
        super.onLayout(z, i, i2, i3, i4);
        DJILogHelper.getInstance().LOGD(p, "changed: " + z + " left: " + i + " top:" + i2 + " right:" + i3 + " bottom:" + i4);
        if (this.E) {
            this.E = false;
            if (this.r == a.VERTICAL) {
                a((this.C + this.D) - (this.B / 2), this.H);
            } else if (this.r == a.HORIZONTAL) {
                a(this.C + this.D, this.H);
            } else {
                a(this.C, this.H);
            }
        }
    }

    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (!this.s) {
            return super.onTouchEvent(motionEvent);
        }
        int i;
        switch (motionEvent.getAction()) {
            case 0:
                this.F = (float) a(motionEvent);
                DJILogHelper.getInstance().LOGD(p, "-> touch down positon at:" + this.F);
                break;
            case 1:
                this.G = (float) a(motionEvent);
                i = (int) (this.G - this.F);
                DJILogHelper.getInstance().LOGD(p, "-> touch up positon at:" + this.F + " dx:" + i);
                a(i);
                break;
            case 2:
                this.G = (float) a(motionEvent);
                i = (int) (this.G - this.F);
                DJILogHelper.getInstance().LOGD(p, "-> touch move positon from:" + this.F + " to:" + this.G + " dx:" + i);
                a(i);
                this.F = this.G;
                break;
        }
        return super.onTouchEvent(motionEvent);
    }

    private int a(MotionEvent motionEvent) {
        int i = 0;
        if (this.r == a.VERTICAL) {
            i = (int) motionEvent.getRawY();
        } else if (this.r == a.HORIZONTAL) {
            i = (int) motionEvent.getRawX();
        }
        DJILogHelper.getInstance().LOGD(p, "dirction(Enum):" + this.r + " pos:" + i);
        return i;
    }

    private void a(int i) {
        LayoutParams layoutParams;
        int i2;
        if (this.r == a.VERTICAL) {
            layoutParams = (LayoutParams) getLayoutParams();
            i2 = layoutParams.topMargin;
            if (b(i2 + i)) {
                layoutParams.topMargin = i2 + i;
                DJILogHelper.getInstance().LOGD(p, "topMargin:" + layoutParams.topMargin + " dValue:" + i);
                setLayoutParams(layoutParams);
            }
        } else if (this.r == a.HORIZONTAL) {
            layoutParams = (LayoutParams) getLayoutParams();
            i2 = layoutParams.leftMargin;
            if (b(i2 + i)) {
                layoutParams.leftMargin = i2 + i;
                DJILogHelper.getInstance().LOGD(p, "leftMargin:" + layoutParams.leftMargin + " dValue:" + i);
                setLayoutParams(layoutParams);
            }
        } else if (this.r != a.DEFAULT) {
        }
    }

    private boolean b(int i) {
        int i2;
        if (this.r == a.VERTICAL) {
            i2 = (this.C + this.D) - i;
            if (i2 <= 0 || i2 > this.B) {
                return false;
            }
            return true;
        } else if (this.r == a.HORIZONTAL) {
            i2 = this.H - i;
            if (i2 <= 0 || i2 > this.B) {
                return false;
            }
            return true;
        } else {
            if (this.r == a.DEFAULT) {
            }
            return false;
        }
    }

    public double[] getClipParameter() {
        if (this.J == null) {
            double[] dArr = new double[4];
            if (this.r == a.DEFAULT) {
                dArr[0] = 0.0d;
                dArr[1] = 0.0d;
                dArr[2] = 1.0d;
                dArr[3] = 1.0d;
            } else if (this.r == a.VERTICAL) {
                r2 = this.A - ((this.C + this.D) - ((LayoutParams) getLayoutParams()).topMargin);
                r0 = r2 - this.u;
                if (r2 > this.A) {
                    r2 = this.A;
                }
                if (r0 < 0) {
                    r0 = 0;
                }
                dArr[0] = 0.0d;
                dArr[1] = (((double) r0) * 1.0d) / ((double) this.A);
                dArr[2] = 1.0d;
                dArr[3] = (((double) r2) * 1.0d) / ((double) this.A);
            } else if (this.r == a.HORIZONTAL) {
                r0 = this.H - ((LayoutParams) getLayoutParams()).leftMargin;
                r2 = this.t + r0;
                if (r0 < 0) {
                    r0 = 0;
                }
                if (r2 > this.z) {
                    r2 = this.z;
                }
                dArr[0] = (((double) r0) * 1.0d) / ((double) this.z);
                dArr[1] = 0.0d;
                dArr[2] = (((double) r2) * 1.0d) / ((double) this.z);
                dArr[3] = 1.0d;
            }
            this.J = dArr;
        }
        DJILogHelper.getInstance().LOGD(p, "getClipParameter: " + this.J[0] + ", " + this.J[1] + ", " + this.J[2] + ", " + this.J[3]);
        return this.J;
    }

    private void b() {
    }

    public a getMovingDrection() {
        return this.r;
    }

    public void setMovingOnOff(boolean z) {
        this.s = z;
    }

    public boolean getMovingOnOff() {
        return this.s;
    }

    public void backToNormalLayout() {
        setMovingOnOff(false);
        double[] clipParameter = getClipParameter();
        if (this.r == a.DEFAULT) {
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.t = this.I;
                this.u = (this.t * 9) / 16;
                this.z = this.t;
                this.A = this.u;
                return;
            }
            this.z = this.t;
            this.A = this.u;
        } else if (this.r == a.VERTICAL) {
            r0 = (LayoutParams) getLayoutParams();
            r0.topMargin -= this.D;
            setLayoutParams(r0);
        } else if (this.r != a.HORIZONTAL) {
        } else {
            if (DJIOriLayout.getDeviceType() == DJIDeviceType.Phone) {
                this.t = this.I;
                this.u = (this.t * 9) / 16;
                this.z = (int) (((float) this.u) * this.y);
                this.A = this.u;
                r0 = (LayoutParams) getLayoutParams();
                r0.topMargin -= this.D;
                r0.leftMargin = 0 - ((int) (((double) this.z) * clipParameter[0]));
                setLayoutParams(r0);
                return;
            }
            r0 = (LayoutParams) getLayoutParams();
            r0.topMargin -= this.D;
            setLayoutParams(r0);
        }
    }
}
