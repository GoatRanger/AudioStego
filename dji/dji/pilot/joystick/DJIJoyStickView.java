package dji.pilot.joystick;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RelativeLayout;
import dji.log.WM220LogUtil;
import dji.logic.c.b;
import dji.midware.data.config.P3.ProductType;
import dji.midware.data.manager.P3.i;
import dji.midware.data.manager.P3.o;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams;
import dji.midware.data.model.P3.DataFlycSetJoyStickParams.FlycMode;
import dji.pilot.R;
import dji.pilot.fpv.control.v;
import dji.pilot.fpv.control.v$a;
import dji.pilot.fpv.control.v$b;
import dji.pilot.fpv.control.v$c;
import dji.pilot.fpv.view.DJIErrorPopView;
import dji.pilot.fpv.view.DJIErrorPopView.d;
import dji.pilot.joystick.widget.OnScreenJoystick2;
import dji.pilot.newfpv.topbar.DJIFpvTopBarBaseView;
import dji.pilot.publics.objects.g;
import dji.publics.DJIUI.DJIRelativeLayout;
import dji.thirdparty.a.c;

public class DJIJoyStickView extends DJIRelativeLayout {
    private static final String r = "key_first_use_joystick";
    protected OnScreenJoystick2 a;
    protected OnScreenJoystick2 b;
    protected RadioGroup c;
    protected RadioButton d;
    protected RadioButton e;
    protected RadioButton f;
    protected DataFlycSetJoyStickParams g = null;
    protected int h = 0;
    protected int i = 0;
    protected int j = 0;
    protected int k = 0;
    protected FlycMode l = FlycMode.b;
    private a m;
    private View n;
    private RelativeLayout o;
    private RelativeLayout p;
    private DJIFpvTopBarBaseView q;
    private boolean s = false;
    private int t = 0;
    private boolean u = false;

    public interface a {
        void a();

        void b();

        void c();

        void d();
    }

    public DJIJoyStickView(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public DJIJoyStickView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
    }

    public void setTutorialView(RelativeLayout relativeLayout, DJIFpvTopBarBaseView dJIFpvTopBarBaseView) {
        this.p = relativeLayout;
        this.q = dJIFpvTopBarBaseView;
        this.s = g.b(getContext(), r, true);
    }

    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (!isInEditMode()) {
            if (!c.a().c(this)) {
                c.a().a(this);
            }
            if (b.getInstance().a(i.getInstance().c())) {
                b();
            }
        }
    }

    protected void onDetachedFromWindow() {
        if (c.a().c(this)) {
            c.a().d(this);
        }
        super.onDetachedFromWindow();
    }

    private void b() {
        v.getInstance().a(new v$a(this) {
            final /* synthetic */ DJIJoyStickView a;

            {
                this.a = r1;
            }

            public void a() {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.c = this.a.getContext().getString(R.string.wm220_wifi_cnnt_height_larger_tip, new Object[]{Integer.valueOf(50)});
                bVar.a = d.b;
                bVar.f = DJIErrorPopView.c.c;
                c.a().e(bVar);
            }

            public void b() {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.c = this.a.getContext().getString(R.string.wm220_wifi_cnnt_dist_larger_tip, new Object[]{Integer.valueOf(80)});
                bVar.a = d.b;
                bVar.f = DJIErrorPopView.c.c;
                c.a().e(bVar);
            }

            public void c() {
                DJIErrorPopView.b bVar = new DJIErrorPopView.b();
                bVar.c = this.a.getContext().getString(R.string.wm220_wifi_cnnt_height_dist_larger_tip, new Object[]{Integer.valueOf(50), Integer.valueOf(80)});
                bVar.a = d.b;
                bVar.f = DJIErrorPopView.c.c;
                c.a().e(bVar);
            }
        });
    }

    protected void onFinishInflate() {
        super.onFinishInflate();
        this.g = DataFlycSetJoyStickParams.getInstance();
        if (!isInEditMode()) {
            a();
        }
    }

    protected void a() {
        this.a = (OnScreenJoystick2) findViewById(R.id.a36);
        this.b = (OnScreenJoystick2) findViewById(R.id.a35);
        this.n = findViewById(R.id.a34);
        this.c = (RadioGroup) findViewById(R.id.a38);
        this.c.setVisibility(8);
        this.d = (RadioButton) findViewById(R.id.a39);
        this.e = (RadioButton) findViewById(R.id.a3_);
        this.f = (RadioButton) findViewById(R.id.a3a);
        this.o = (RelativeLayout) findViewById(R.id.a37);
        this.e.setChecked(true);
        c();
        v.getInstance().a(this.l);
        this.t = getResources().getDisplayMetrics().widthPixels;
        this.c.setOnCheckedChangeListener(new OnCheckedChangeListener(this) {
            final /* synthetic */ DJIJoyStickView a;

            {
                this.a = r1;
            }

            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.a39:
                        this.a.l = FlycMode.a;
                        break;
                    case R.id.a3_:
                        this.a.l = FlycMode.b;
                        break;
                    case R.id.a3a:
                        this.a.l = FlycMode.c;
                        break;
                }
                v.getInstance().a(this.a.l);
            }
        });
        this.a.setJoystickListener(new dji.pilot.joystick.widget.a(this) {
            final /* synthetic */ DJIJoyStickView a;

            {
                this.a = r1;
            }

            public void a(float f, float f2) {
                float f3 = 0.0f;
                if (f == Float.NEGATIVE_INFINITY || f == Float.POSITIVE_INFINITY) {
                    f2 = 0.0f;
                    f = 0.0f;
                }
                if (((double) Math.abs(f)) < 0.02d) {
                    f = 0.0f;
                }
                if (((double) Math.abs(f2)) >= 0.02d) {
                    f3 = f2;
                }
                this.a.h = ((int) (((float) v.b) * f)) + 1024;
                this.a.k = ((int) ((-f3) * ((float) v.b))) + 1024;
                v.getInstance().a(this.a.k, this.a.h);
            }

            public void a() {
                if (this.a.m != null) {
                    this.a.m.d();
                }
                if (this.a.b.getVisibility() != 0) {
                    this.a.n.setVisibility(4);
                }
            }
        });
        this.b.setJoystickListener(new dji.pilot.joystick.widget.a(this) {
            final /* synthetic */ DJIJoyStickView a;

            {
                this.a = r1;
            }

            public void a(float f, float f2) {
                float f3 = 0.0f;
                if (f == Float.NEGATIVE_INFINITY || f == Float.POSITIVE_INFINITY) {
                    f2 = 0.0f;
                    f = 0.0f;
                }
                if (((double) Math.abs(f)) < 0.02d) {
                    f = 0.0f;
                }
                if (((double) Math.abs(f2)) >= 0.02d) {
                    f3 = f2;
                }
                this.a.j = ((int) ((-f3) * ((float) v.b))) + 1024;
                this.a.i = ((int) (((float) v.b) * f)) + 1024;
                v.getInstance().b(this.a.j, this.a.i);
            }

            public void a() {
                if (this.a.m != null) {
                    this.a.m.b();
                }
                if (this.a.a.getVisibility() != 0) {
                    this.a.n.setVisibility(4);
                }
            }
        });
        this.o.setOnTouchListener(new OnTouchListener(this) {
            final /* synthetic */ DJIJoyStickView a;

            {
                this.a = r1;
            }

            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == 0) {
                    if (motionEvent.getX() < ((float) (this.a.t / 2))) {
                        this.a.setLeftPos(motionEvent.getX(), motionEvent.getY());
                    } else {
                        this.a.setRightPos(motionEvent.getX(), motionEvent.getY());
                    }
                }
                return false;
            }
        });
    }

    private void c() {
        if (this.a != null && this.b != null) {
            int b = g.b(getContext(), v.a, v$b.AmericanMode.ordinal());
            if (b == v$b.AmericanMode.ordinal()) {
                this.a.setBackgroundResource(R.drawable.joystick_left);
                this.b.setBackgroundResource(R.drawable.joystick_right);
            } else if (b == v$b.ChineseMode.ordinal()) {
                this.a.setBackgroundResource(R.drawable.joystick_right);
                this.b.setBackgroundResource(R.drawable.joystick_left);
            } else if (b == v$b.JapaneseMode.ordinal()) {
                this.a.setBackgroundResource(R.drawable.btn_joystick_left_japan);
                this.b.setBackgroundResource(R.drawable.btn_joystick_right_japan);
            }
        }
    }

    public void setLeftPos(float f, float f2) {
        if (this.a.getVisibility() != 0) {
            this.a.setVisibility(0);
            this.a.setX(f - ((float) (this.a.getLayoutParams().width / 2)));
            this.a.setY(f2 - ((float) (this.a.getLayoutParams().height / 2)));
            if (this.m != null) {
                this.m.c();
            }
            d();
            this.n.setVisibility(0);
        }
    }

    public void setRightPos(float f, float f2) {
        if (this.b.getVisibility() != 0) {
            this.b.setVisibility(0);
            this.b.setX(f - ((float) (this.b.getLayoutParams().width / 2)));
            this.b.setY(f2 - ((float) (this.b.getLayoutParams().height / 2)));
            if (this.m != null) {
                this.m.a();
            }
            d();
            this.n.setVisibility(0);
        }
    }

    private void d() {
        if (this.s && this.p != null) {
            this.p.setVisibility(8);
            this.s = false;
            g.a(getContext(), r, this.s);
        }
    }

    private void e() {
        if ((this.q == null || !this.q.hasDlgShowing()) && getVisibility() == 0 && this.p != null && this.s) {
            this.p.setVisibility(0);
        }
    }

    public void showJoyStick() {
        this.a.setVisibility(0);
        this.b.setVisibility(0);
        this.a.setVisibility(4);
        this.b.setVisibility(4);
        this.u = true;
        e();
    }

    public void hideJoyStick() {
        this.a.setVisibility(4);
        this.b.setVisibility(4);
        this.u = false;
    }

    public boolean isJoystickViewEnable() {
        return this.u;
    }

    public boolean isLeftShowed() {
        return this.a.getVisibility() == 0;
    }

    public boolean isRightShowed() {
        return this.b.getVisibility() == 0;
    }

    public void setVisibility(int i) {
        super.setVisibility(i);
        if (!isInEditMode()) {
            if (i == 0) {
                v.getInstance().a(14);
            } else if (i == 8 || i == 4) {
                v.getInstance().a();
            }
        }
    }

    public void setOnJoystickVisibilityChangedListenner(a aVar) {
        this.m = aVar;
    }

    public void onEventMainThread(DJIFpvTopBarBaseView.c cVar) {
        if (cVar.a == dji.pilot.newfpv.topbar.DJIFpvTopBarBaseView.c.a.HIDE_DIALOG) {
            e();
        }
    }

    public void onEventMainThread(v$c dji_pilot_fpv_control_v_c) {
        c();
    }

    public void onEventMainThread(o oVar) {
        WM220LogUtil.LOGD("joystickview into DataCameraEvent");
        ProductType c = i.getInstance().c();
        if (oVar == o.b) {
            WM220LogUtil.LOGD("joystickview into DataCameraEvent checkDistHeightLimit");
            if (b.getInstance().a(c)) {
                b();
            } else {
                hide();
            }
        }
    }
}
